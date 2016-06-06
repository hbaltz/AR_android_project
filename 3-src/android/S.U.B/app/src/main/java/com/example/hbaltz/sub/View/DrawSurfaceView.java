package com.example.hbaltz.sub.View;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.hbaltz.sub.Class.BuildingPOI;

import java.util.ArrayList;

/**
 * Created by hbaltz on 6/2/2016.
 */
public class DrawSurfaceView extends View {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// VARIABLES: ////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////// Screen size: ////////////////////////////////////////////
    private double screenWidth, screenHeight = 0d;

    ////////////////////////////////////// POIs: ///////////////////////////////////////////////////
    private ArrayList<BuildingPOI> POIs = null;
    private ArrayList<Double> Distances = null;
    private ArrayList<Boolean> Visibles = null;
    private ArrayList<Double> azTheos = null;

    ////////////////////////////////////// Azimuth: ////////////////////////////////////////////////
    private double azimuthReal;

    ///////////////////////////////////// Paint: ///////////////////////////////////////////////////
    private Paint paint = new Paint(Color.GREEN);

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// CONSTRUCTORS: /////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public DrawSurfaceView(Context context) {super(context);}

    public DrawSurfaceView(Context context, AttributeSet set) {
        super(context, set);

        paint.setColor(Color.GREEN);
        paint.setTextSize(50);
        paint.setAntiAlias(true);
        paint.setLinearText(true);
        paint.setTextAlign(Paint.Align.CENTER);

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// METHODS: //////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Method which actualizes the screen size when it changes
     *
     * @param w: the screen's width
     * @param h: the screen's height
     * @param oldw: the former screen's width
     * @param oldh: : the former screen's height
     */
    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.d("onSizeChanged", "in here w=" + w + " h=" + h);
        screenWidth = (double) w;
        screenHeight = (double) h;

    }

    @Override
    public void onDraw(Canvas canvas) {

        if(POIs != null) {
            int len_pois = POIs.size();
            for(int i =0; i<len_pois; i++){
                boolean isVisible = Visibles.get(i);
                if(isVisible) {

                    // If the poi is visible we recover information about it :
                    BuildingPOI POI = POIs.get(i);
                    double dist = Distances.get(i);
                    double azTheo = azTheos.get(i);

                    double angle = azTheo - azimuthReal;
                    double xPos, yPos;

                    xPos = Math.sin(Math.toRadians(angle)) * dist;
                    yPos = Math.sqrt(Math.pow(dist, 2) - Math.pow(xPos, 2));

                    float xPosScreen, yPosScreen;

                    if (angle <= 45)
                        xPosScreen =(float) ((screenWidth / 2) + xPos);

                    else if (angle >= 315)
                        xPosScreen =(float) ((screenWidth / 2) - ((screenWidth*4) - xPos));

                    else
                        xPosScreen =(float) (screenWidth*9); //somewhere off the screen

                    yPosScreen = (float)(screenHeight/2);

                    //canvas.drawBitmap(test,xPosScreen,yPosScreen,paint);
                    float radius = (float) (2000/dist);

                    String descritpion = POI.getDescription();

                    paint = initializedPaint(descritpion);
                    paint.setTextSize(radius);

                    canvas.drawCircle(xPosScreen, yPosScreen, radius, paint);

                    canvas.drawText(descritpion, xPosScreen-radius, yPosScreen-(radius+1), paint);

                }
            }
        }

        super.onDraw(canvas);

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// FUNCTIONS: ////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * * Function which sets the variables
     *
     * @param pois: the arrayList of POI that we want to draw
     * @param distances: the arrayList of distances between POIs and te user
     * @param aztheos: the arrayList of theoretical azimuths
     * @param azimuthreal: the real azimuth (double)
     * @param visibles: the arrayList of boolena to know if the user see the POIs
     */
    public void setVariables(ArrayList<BuildingPOI> pois,
                             ArrayList<Double> distances,
                             ArrayList<Double> aztheos,
                             double azimuthreal,
                             ArrayList<Boolean> visibles){

        this.POIs = pois;
        this.Distances = distances;
        this.azTheos = aztheos;
        this.azimuthReal = azimuthreal;
        this.Visibles = visibles;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Functions which defines the color of the paint regarding the description
     *
     * @param description: the description of the POI
     * @return a Paint with the color defined regarding the description
     */
    public Paint initializedPaint( String description ){

        Paint paint = new Paint();

        paint.setAntiAlias(true);
        paint.setTextSize(50);


        if(description.equals("Extremely good") || description.equals("Good")) {
            paint.setColor(Color.GREEN);
        }else if(description.equals("Moderate")){
            paint.setColor(Color.YELLOW);
        }else if(description.equals("Extremely severe") || description.equals("Severe")) {
            paint.setColor(Color.RED);
        }else{
            paint.setColor(Color.GRAY);
        }

        return paint;
    }
}
