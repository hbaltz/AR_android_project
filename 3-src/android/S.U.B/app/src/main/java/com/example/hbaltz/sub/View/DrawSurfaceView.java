package com.example.hbaltz.sub.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.esri.core.geometry.Point;
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

    ////////////////////////////////////// Azimuth: ////////////////////////////////////////////////
    private double azimuthReal;

    ///////////////////////////////////// Paint: ///////////////////////////////////////////////////
    private Paint paint;
    private Paint paintRect;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// CONSTRUCTORS: /////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public DrawSurfaceView(Context context) {super(context);}

    public DrawSurfaceView(Context context, AttributeSet set) {
        super(context, set);

        paintRect = new Paint(Color.GRAY);
        paintRect.setAntiAlias(true);

        paint = new Paint();
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

            // Initialize:
            boolean isVisible;
            double dist, azTheo, angle, xPos, yPos;
            float xPosScreen, yPosScreen;
            float radius;
            float w;

            for(int i =0; i<len_pois; i++){

                BuildingPOI POI = POIs.get(i);
                isVisible = POI.isVisible();

                if(isVisible) {

                    // If the poi is visible we recover information about it :
                    dist = POI.getDistance();
                    azTheo = POI.getAzimut();
                    angle = azTheo - azimuthReal;

                    xPos = Math.sin(Math.toRadians(angle)) * dist;
                    yPos = Math.sqrt(Math.pow(dist, 2) - Math.pow(xPos, 2));

                    if (angle <= 45)
                        xPosScreen =(float) ((screenWidth / 2) + xPos);

                    else if (angle >= 315)
                        xPosScreen =(float) ((screenWidth / 2) - ((screenWidth*4) - xPos));

                    else
                        xPosScreen =(float) (screenWidth*9); //somewhere off the screen

                    yPosScreen = (float)(screenHeight/2);

                    radius = (float) (2000/dist);

                    String strct = POI.getStructure();

                    String structure = "Structure : " + strct;
                    String deteration = "Deteration : " + POI.getDeteration();
                    String type = "Type : " + POI.getType();
                    String address = "Address : " + POI.getAddress();
                    String notes = "Notes : " +POI.getNotes();

                    paint = initializedPaint(strct);
                    paint.setTextSize(radius);

                    canvas.drawCircle(xPosScreen, yPosScreen, radius, paint);

                    w = 100f;

                    canvas.drawRect(xPosScreen-radius, yPosScreen-5*(radius+1) - radius,
                            xPosScreen-radius + 2*w, yPosScreen-(radius+1), paintRect);

                    canvas.drawText(structure, xPosScreen-radius, yPosScreen-5*(radius+1), paint);
                    canvas.drawText(deteration, xPosScreen-radius, yPosScreen-4*(radius+1), paint);
                    canvas.drawText(type, xPosScreen-radius, yPosScreen-3*(radius+1), paint);
                    canvas.drawText(address, xPosScreen-radius, yPosScreen-2*(radius+1), paint);
                    canvas.drawText(notes, xPosScreen-radius, yPosScreen-1*(radius+1), paint);
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
     * @param azimuthreal: the real azimuth (double)
     */
    public void setVariables(ArrayList<BuildingPOI> pois,
                             double azimuthreal){

        this.POIs = pois;
        this.azimuthReal = azimuthreal;
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


        if(description.equals("Bare frame")) {
            paint.setColor(Color.MAGENTA);
        }else if(description.equals("Lightly reinforced masonry walls")){
            paint.setColor(Color.YELLOW);
        }else if(description.equals("Heavily reinforced masonry walls")) {
            paint.setColor(Color.GREEN);
        }else{
            paint.setColor(Color.GRAY);
        }

        paint.setAlpha(175);

        return paint;
    }
}
