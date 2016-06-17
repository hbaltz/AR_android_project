package com.example.hbaltz.sub.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.esri.core.geometry.GeometryEngine;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.Polygon;
import com.esri.core.geometry.SpatialReference;
import com.example.hbaltz.sub.Class.BuildingPOI;
import com.example.hbaltz.sub.Class.User;
import com.example.hbaltz.sub.Class.Utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by hbaltz on 6/13/2016.
 */
public class GeoDrawSurfaceView  extends View {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// VARIABLES: ////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////// Screen size: ////////////////////////////////////////////
    private double screenWidth, screenHeight = 0d;

    //////////////////////////////////// Spatial reference: ////////////////////////////////////////
    private SpatialReference spaRef;

    //////////////////////////////////// Geometrie Engine: /////////////////////////////////////////
    private GeometryEngine geomen;

    ////////////////////////////////////// User: ///////////////////////////////////////////////////
    private User user;

    ////////////////////////////////////// POIs: ///////////////////////////////////////////////////
    private ArrayList<BuildingPOI> POIs = null;

    ////////////////////////////////////// Angles: /////////////////////////////////////////////////
    private double azimuthReal, pitchReal;

    ///////////////////////////////////// Paint: ///////////////////////////////////////////////////
    private Paint paint = new Paint();

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// CONSTRUCTORS: /////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public GeoDrawSurfaceView(Context context) {super(context);}

    public GeoDrawSurfaceView(Context context, AttributeSet set) {
        super(context, set);

        // We initialize the paint for the POIs:
        paint.setColor(Color.GREEN);
        paint.setAntiAlias(true);
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

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onDraw(Canvas canvas) {
        if(POIs != null) {
            int len_pois = POIs.size();

            // Initialize:
            boolean isVisible;
            Polygon footprint;
            int countPoint;
            Point pointTemp;
            BuildingPOI poiTemp = new BuildingPOI();
            double azimutTheo, pitchTheo, dist, angleHor=0d, angleVer;
            List<Float> posScreenTemp;
            Path wallpath;
            float xPos,yPos;
            boolean draw= false;
            float area;

            for(int i =0; i<len_pois; i++){
                // We recover the POI et the filed visible to know if the user sees the POI
                BuildingPOI POI = POIs.get(i);
                isVisible = POI.isVisible();

                if(isVisible) {
                    draw = false;

                    footprint = POI.getFootprint();
                    area = (float) footprint.calculateArea2D();

                    countPoint = footprint.getPointCount();

                    wallpath = new Path();
                    wallpath.reset(); // only needed when reusing this path for a new build

                    for(int j=0; j<countPoint; j++){
                        pointTemp = footprint.getPoint(j);
                        poiTemp.setLocation(pointTemp);

                        azimutTheo = user.theoreticalAzimuthToPOI(poiTemp);
                        angleVer = Math.toRadians(azimutTheo-azimuthReal);

                        pitchTheo = user.theoreticalPitchToPOI(poiTemp);
                        angleHor = Math.toRadians(pitchTheo-pitchReal);

                        dist = geomen.distance(user.getLocation(), pointTemp, spaRef);

                        posScreenTemp = Utilities.screenPosition(angleVer, angleHor, dist, screenWidth, screenHeight);

                        xPos=((posScreenTemp.get(0)));
                        yPos=((posScreenTemp.get(1)));
/*
                        if(xPos < (screenWidth/2) && yPos > (screenHeight/2)){
                            xPos = (float) (xPos - (area/dist));
                            yPos = (float) (yPos + (area/dist));
                        }else if((xPos > (screenWidth/2) && yPos > (screenHeight/2))){
                            xPos = (float) (xPos + (area/dist));
                            yPos = (float) (yPos + (area/dist));
                        }else if((xPos > (screenWidth/2) && yPos < (screenHeight/2))){
                            xPos = (float) (xPos + (area/dist));
                            yPos = (float) (yPos - (area/dist));
                        }else{
                            xPos = (float) (xPos - (area/dist));
                            yPos = (float) (yPos - (area/dist));
                        }
                        */

                        canvas.drawCircle(xPos, yPos,(float) (200/dist), paint);

                        if(j==0){
                            wallpath.moveTo(xPos, yPos);
                            draw = true;
                        }else wallpath.lineTo(xPos, yPos);

                    }

                    if(draw) {

                        wallpath.close();

                        //Log.d("wallpath",wallpath.toString());

                        canvas.drawPath(wallpath, paint);
                    }

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
     * @param pitchreal: the real pitch (double)
     * @param usr: the user
     * @param spRf: the spatial reference
     */
    public void setVariables(ArrayList<BuildingPOI> pois,
                             double azimuthreal,
                             double pitchreal,
                             User usr,
                             SpatialReference spRf){

        this.POIs = pois;
        this.azimuthReal = azimuthreal;
        this.pitchReal = pitchreal;
        this.user = usr;
        this.spaRef =spRf;
    }

}
