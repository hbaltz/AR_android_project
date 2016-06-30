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
import com.example.hbaltz.sub.Class.BuildingPOI;
import com.example.hbaltz.sub.Class.GeoInfo;
import com.example.hbaltz.sub.Class.User;
import com.example.hbaltz.sub.Class.Utilities;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by hbaltz on 6/29/2016.
 */
public class GeoDrawSurfaceView extends View {
    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// VARIABLES: ////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////// Screen size: ////////////////////////////////////////////
    private double screenWidth, screenHeight = 0d;

    //////////////////////////////////// Geometrie Engine: /////////////////////////////////////////
    private GeometryEngine geomen;

    ////////////////////////////////////// User: ///////////////////////////////////////////////////
    private User user;

    ////////////////////////////////////// POIs: ///////////////////////////////////////////////////
    private ArrayList<GeoInfo> geoInfos = null;

    ////////////////////////////////////// Angles: /////////////////////////////////////////////////
    float[] orMat;
    double azimuthRe, pitchRe;

    ///////////////////////////////////// Paint: ///////////////////////////////////////////////////
    private Paint paint = new Paint();

    ///////////////////////////////////// Debug: ///////////////////////////////////////////////////
    private boolean DEBUG = false;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// CONSTRUCTORS: /////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public GeoDrawSurfaceView(Context context) {super(context);}

    public GeoDrawSurfaceView(Context context, AttributeSet set) {
        super(context, set);

        // We initialize the paint for the POIs:
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
        if(geoInfos != null) {
            int len_pois = geoInfos.size();

            // Initialize:
            String type; // the type of geological underground
            Polygon shape; // the footprint of the building
            int countPoint; // the number of point in the shape
            Point pointTemp; // the point that we project
            List<Float> posScreenTemp; // the position on the screen of the point which has been projected
            Path wallpath; // the path
            float xPos,yPos; // the position on the screen of the point which has been projected
            boolean draw= false; // useful to know if we draw or not the path

            BuildingPOI poiTemp;
            List<Double> minMaxAz, minMaxPt;
            double azimuth, pitch, minAngleAz, maxAngleAz, minAnglePt, maxAnglePt;
            boolean isVisibleAz, isVisiblePt, isVisible;

            for(int i =0; i<len_pois; i++){
                // We recover the geoInfo et the filed visible to know if the user sees the POI
                GeoInfo geoInfo = geoInfos.get(i);

                draw = false;

                // We recover the shape
                shape = geoInfo.getShape();

                // We recover the type:
                type = geoInfo.getType();

                // We count the number of point in the shape
                countPoint = shape.getPointCount();

                //We initialize the path (which will served to draw the shape)
                wallpath = new Path();
                wallpath.reset(); // only needed when reusing this path for a new build

                // We project each point of the shape on the screen with a perspective projection
                for(int j=0; j<countPoint; j++){
                    pointTemp = shape.getPoint(j);
                    poiTemp = new BuildingPOI();
                    poiTemp.setLocation(pointTemp);

                    azimuth = user.theoreticalAzimuthToPOI(poiTemp);
                    pitch = user.theoreticalPitchToPOI(poiTemp);

                    // We calculate if the user sees or not the poi:
                    // Azimuth:
                    minMaxAz = Utilities.angleAccuracy(azimuth,360);
                    minAngleAz = minMaxAz.get(0);
                    maxAngleAz = minMaxAz.get(1);
                    isVisibleAz = Utilities.isBetween(minAngleAz, maxAngleAz, azimuthRe);


                    // Pitch:
                    minMaxPt = Utilities.angleAccuracy(180,360);
                    minAnglePt = minMaxPt.get(0);
                    maxAnglePt = minMaxPt.get(1);
                    isVisiblePt = Utilities.isBetween(minAnglePt, maxAnglePt, pitchRe);

                    isVisible = isVisibleAz && isVisiblePt; //Tjrs vraie

                    Log.d("isVis", "" + isVisible);

                    posScreenTemp = Utilities.screenPositionMatOr(user.getLocation(),pointTemp,orMat,
                            (float)screenWidth,(float)screenHeight, -50f);

                    if(posScreenTemp !=null && isVisible) {
                        xPos = posScreenTemp.get(0);
                        yPos = posScreenTemp.get(1);


                        if (j == 0) {
                            wallpath.moveTo(xPos, yPos);
                            draw = true;
                        } else wallpath.lineTo(xPos, yPos);
                    }
                }

                if(draw) {
                    paint = initializedPaint(type);
                    wallpath.close();
                    canvas.drawPath(wallpath, paint);
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
     * @param geoinfos: the arrayList of geological infornmation that we want to draw
     * @param ormat: the orientation matrix
     * @param usr: the user
     */
    public void setVariables(ArrayList<GeoInfo> geoinfos,
                             float[] ormat,
                             User usr,
                             double az, double pt){

        this.geoInfos = geoinfos;
        this.orMat = ormat;
        this.user = usr;
        this.azimuthRe = az;
        this.pitchRe = pt;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Functions which defines the color of the paint regarding the type
     *
     * @param type: the type of geological
     * @return a Paint with the color defined regarding the type
     */
    public Paint initializedPaint( String type ){

        Paint paint = new Paint();

        paint.setAntiAlias(true);

        // We set the color regarding the structure information:
        switch (type) {
            case "A":
                paint.setColor(Color.GREEN);
                break;
            case "B":
                paint.setColor(Color.YELLOW);
                break;
            case "C":
                paint.setColor(Color.rgb(255,69,0)); // Orange
                break;
            case "D":
                paint.setColor(Color.RED);
                break;
            default:
                paint.setColor(Color.GRAY);
                break;
        }

        // We set the opacity:
        paint.setAlpha(130);

        return paint;
    }
}

