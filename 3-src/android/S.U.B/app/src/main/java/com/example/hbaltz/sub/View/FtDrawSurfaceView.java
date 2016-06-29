package com.example.hbaltz.sub.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.esri.core.geometry.GeometryEngine;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.Polygon;
import com.example.hbaltz.sub.Class.BuildingPOI;
import com.example.hbaltz.sub.Class.User;
import com.example.hbaltz.sub.Class.Utilities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hbaltz on 6/13/2016.
 */
public class FtDrawSurfaceView extends View {

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
    private ArrayList<BuildingPOI> POIs = null;

    ////////////////////////////////////// Angles: /////////////////////////////////////////////////
    float[] orMat;

    ///////////////////////////////////// Paint: ///////////////////////////////////////////////////
    private Paint paint = new Paint();

    ///////////////////////////////////// Debug: ///////////////////////////////////////////////////
    private boolean DEBUG = false;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// CONSTRUCTORS: /////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public FtDrawSurfaceView(Context context) {super(context);}

    public FtDrawSurfaceView(Context context, AttributeSet set) {
        super(context, set);

        // We initialize the paint for the POIs:
        paint.setColor(Color.RED);
        paint.setAntiAlias(true);
        paint.setAlpha(130);
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
            boolean isVisible; // true is the poi is visible by the user
            Polygon footprint; // the footprint of the building
            int countPoint; // the number of point in the footprint
            Point pointTemp; // the point that we project
            List<Float> posScreenTemp; // the position on the screen of the point which has been projected
            Path wallpath; // the path
            float xPos,yPos; // the position on the screen of the point which has been projected
            boolean draw= false; // useful to know if we draw or not the path

            for(int i =0; i<len_pois; i++){
                // We recover the POI et the filed visible to know if the user sees the POI
                BuildingPOI POI = POIs.get(i);
                isVisible = POI.isVisible();

                // If the POI is visible by the user, we draw his footprint
                if(isVisible) {
                    draw = false;

                    // We recover the footprint
                    footprint = POI.getFootprint();

                    // We count the number of point in the footprint
                    countPoint = footprint.getPointCount();

                    //We initialize the path (which will served to draw the footprint)
                    wallpath = new Path();
                    wallpath.reset(); // only needed when reusing this path for a new build

                    // We project each point of the footprint on the screen with a perspective projection
                    for(int j=0; j<countPoint; j++){
                        pointTemp = footprint.getPoint(j);

                        posScreenTemp = Utilities.screenPositionMatOr(user.getLocation(),pointTemp,orMat,
                                (float)screenWidth,(float)screenHeight, -2f);

                        xPos=posScreenTemp.get(0);
                        yPos=posScreenTemp.get(1);

                        if(j==0){
                            wallpath.moveTo(xPos, yPos);
                            draw = true;
                        }else wallpath.lineTo(xPos, yPos);
                    }

                    if(draw) {
                        wallpath.close();
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
     * @param ormat: the orientation matrix
     * @param usr: the user
     */
    public void setVariables(ArrayList<BuildingPOI> pois,
                             float[] ormat,
                             User usr){

        this.POIs = pois;
        this.orMat = ormat;
        this.user = usr;
    }
}
