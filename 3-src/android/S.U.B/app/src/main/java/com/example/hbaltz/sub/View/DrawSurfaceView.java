package com.example.hbaltz.sub.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.example.hbaltz.sub.Class.BuildingPOI;

import java.util.ArrayList;
import java.util.Collections;

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
    private Paint paint = new Paint();
    private Paint paintRect = new Paint();

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// CONSTRUCTORS: /////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public DrawSurfaceView(Context context) {super(context);}

    public DrawSurfaceView(Context context, AttributeSet set) {
        super(context, set);

        // We initialize the paint for the rectangles:
        paintRect.setColor(Color.GRAY);
        paintRect.setAntiAlias(true);
        paintRect.setAlpha(190);

        // We initialize the paint for the POIs:
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
        screenWidth = (double) w;
        screenHeight = (double) h;
    }

    @Override
    public void onDraw(Canvas canvas) {
        if(POIs != null) {
            int len_pois = POIs.size();

            // Initialize:
            boolean isVisible;
            double dist, azTheo, angDeg, angRad, xPos, yPos;
            float xPosScreen, yPosScreen;
            float radius;
            float w;
            String strct, structure, occupancyClass, buildingType, constructionYear;
            String numberOfStories, verticalIrregularity, planIrregularity, address, notes, distanc;
            ArrayList<Integer> sizeStrings;
            ArrayList<String> information;

            for(int i =0; i<len_pois; i++){
                // We recover the POI et the filed visible to know if the user sees the POI
                BuildingPOI POI = POIs.get(i);
                isVisible = POI.isVisible();

                if(isVisible) {

                    // If the poi is visible we recover information about it :
                    dist = POI.getDistance();
                    azTheo = POI.getAzimut();
                    angDeg = azTheo - azimuthReal;

                    /////////////////////////////////// Calculate location: ////////////////////////

                    // We calculate where the point have to be draw
                    angRad = Math.toRadians(angDeg);
                    xPos = Math.sin(angRad) * dist;
                    yPos = Math.cos(angRad) * dist;

                    if (angDeg <= 45)
                        xPosScreen =(float) ((screenWidth / 2) + xPos);
                    else if (angDeg >= 315)
                        xPosScreen =(float) ((screenWidth / 2) - ((screenWidth*4) - xPos));
                    else
                        xPosScreen =(float) (screenWidth*9); //somewhere off the screen

                    // We draw in the middle of the Y-axis
                    yPosScreen = (float)(screenHeight/2)  ;

                    // We calculate the radius of the circle and of the text regarding the distance
                    radius = (float) (2000/dist);

                    /////////////////////////////////// Recover info: //////////////////////////////

                    sizeStrings = new ArrayList<Integer>(); // Useful for the size of the rectangle
                    information = new ArrayList<String>();

                    // We recover the informations about the Poi that we want to display
                    strct = POI.getStructure();

                    structure = "Structure wall: " + strct;
                    information.add(structure);
                    sizeStrings.add(structure.length());

                    buildingType = "Building type: " + POI.getBuildingType();
                    information.add(buildingType);
                    sizeStrings.add(buildingType.length());

                    occupancyClass = "Occupancy class: " + POI.getOccupancyClass();
                    information.add(occupancyClass);
                    sizeStrings.add(occupancyClass.length());

                    constructionYear = "Construction year: " + POI.getConstructionYear();
                    information.add(constructionYear);
                    sizeStrings.add(constructionYear.length());

                    numberOfStories = "Number of stories: " + POI.getNumberOfStories();
                    information.add(numberOfStories);
                    sizeStrings.add(numberOfStories.length());

                    verticalIrregularity = "Vertical irregularity: " + POI.getVerticalIrregularity();
                    information.add(verticalIrregularity);
                    sizeStrings.add(verticalIrregularity.length());

                    planIrregularity = "Plan irregularity: " + POI.getPlanIrregularity();
                    information.add(planIrregularity);
                    sizeStrings.add(planIrregularity.length());

                    address = "Address: " + POI.getAddress();
                    information.add(address);
                    sizeStrings.add(address.length());

                    notes = "Notes: " +POI.getNotes();
                    information.add(notes);
                    sizeStrings.add(notes.length());

                    distanc = "Distance : " + ((int)(dist)) + " m";
                    information.add(distanc);
                    sizeStrings.add(distanc.length());

                    // We initialize the paint regarding the structure field:
                    paint = initializedPaint(strct);
                    paint.setTextSize(radius);

                    /////////////////////////////////// Draw: //////////////////////////////////////

                    // We draw the circle:
                    canvas.drawCircle(xPosScreen, yPosScreen, radius, paint);

                    // We define the size of the rectangle:
                    w = radius*Collections.max(sizeStrings)/2;

                    // We draw the rectangle and the texts:
                    drawInformation(canvas,information,xPosScreen,yPosScreen,radius,w,paint,paintRect);
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

        // We set the color regarding the structure information:
        switch (description) {
            case "Bare frame":
                paint.setColor(Color.MAGENTA);
                break;
            case "Lightly reinforced masonry walls":
                paint.setColor(Color.YELLOW);
                break;
            case "Heavily reinforced masonry walls":
                paint.setColor(Color.GREEN);
                break;
            default:
                paint.setColor(Color.GRAY);
                break;
        }

        // We set the opacity:
        paint.setAlpha(175);

        return paint;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Functions which draws the information in the ArrayList on the canvas enclosed in a rectangle
     *
     * @param c: the canvas
     * @param information: arrayList of string that we want to draw
     * @param x: the initial x-location
     * @param y: the initial y-location
     * @param r: the size of the text
     * @param w: the weigth of the rectangle
     * @param paint: the paint
     * @param paintRect: the paint of the rectangle
     */
    public void drawInformation(Canvas c, ArrayList<String> information,
                                float x, float y, float r, float w, Paint paint, Paint paintRect){
        int sizeInfo = information.size();

        // We draw the rectangle
        c.drawRect(x-r, y-sizeInfo*(r+1) - r, x + r + w, y-(r+1), paintRect);

        String infoTemp;

        // We draw the texts:
        for(int k=0; k < sizeInfo; k++){
            infoTemp = information.get(sizeInfo-k-1); // To draw in the first in the ArrayLIst the higher
            c.drawText(infoTemp, x-r, y-(k+1)*(r+1), paint);
        }
    }
}
