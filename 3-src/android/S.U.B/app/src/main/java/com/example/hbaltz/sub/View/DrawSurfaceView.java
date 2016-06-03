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
    Bitmap test;

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
                    paint.setTextSize(radius);
                    canvas.drawCircle(xPosScreen, yPosScreen, radius, paint);

                    drawSpacedText(canvas,POI.getDescription(),xPosScreen-(radius/2),yPosScreen-(2*radius), paint, radius/1.5f);
                    /*
                    String type = POI.getDescription();
                    canvas.drawText(type , xPosScreen, yPosScreen-(radius+10), paint);
                    */

                }
            }
        }

        super.onDraw(canvas);

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// FUNCTIONS: ////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Function which sets the variables
     *
     * @param pois: the arrayList of POI that we want to draw
     * @param distances: the arrayList of distances between POIs and te user
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
     * Function which draws a text in the canvas with spacing between each letter.
     * Basically what this method does is it split's the given text into individual letters
     * and draws each letter independently using Canvas.drawText with a separation of
     * {@code spacingX} between each letter.
     *
     * @param canvas the canvas where the text will be drawn
     * @param text the text what will be drawn
     * @param left the left position of the text
     * @param top the top position of the text
     * @param paint holds styling information for the text
     * @param spacingPx the number of pixels between each letter that will be drawn
     */
    public static void drawSpacedText(Canvas canvas, String text, float left, float top, Paint paint, float spacingPx){

        float currentLeft = left;

        for (int i = 0; i < text.length(); i++) {
            String c = text.charAt(i)+"";
            canvas.drawText(c, currentLeft, top, paint);
            currentLeft += spacingPx;
        }
    }



}
