package com.example.hbaltz.sub.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import com.esri.core.geometry.Geometry;
import com.esri.core.geometry.GeometryEngine;
import com.esri.core.geometry.Line;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.Polygon;
import com.esri.core.geometry.Polyline;
import com.example.hbaltz.sub.Class.User;
import com.example.hbaltz.sub.Class.Utilities;

import java.util.List;

/**
 * Created by hbaltz on 7/6/2016.
 */
public class FaultDrawSurfaceView extends View {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// VARIABLES: ////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    ////////////////////////////////////// Screen size: ////////////////////////////////////////////
    private double screenWidth, screenHeight = 0d;

    //////////////////////////////////// Geometrie Engine: /////////////////////////////////////////
    private GeometryEngine geomen;

    ////////////////////////////////////// User: ///////////////////////////////////////////////////
    private User user;

    ////////////////////////////////////// Fault: //////////////////////////////////////////////////
    private Polyline fault = null;

    ////////////////////////////////////// Angles: /////////////////////////////////////////////////
    float[] orMat;

    ///////////////////////////////////// Paint: ///////////////////////////////////////////////////
    private Paint paint = new Paint();

    ///////////////////////////////////// Debug: ///////////////////////////////////////////////////
    private boolean DEBUG = false;
    private float zDef = -4f;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// CONSTRUCTORS: /////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public FaultDrawSurfaceView(Context context) {
        super(context);
    }

    public FaultDrawSurfaceView(Context context, AttributeSet set) {
        super(context, set);

        // We initialize the paint for the POIs:
        paint.setAntiAlias(true);
        paint.setColor(Color.MAGENTA);
        paint.setAlpha(140);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// METHODS: //////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Method which actualizes the screen size when it changes
     *
     * @param w:    the screen's width
     * @param h:    the screen's height
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
        if(fault != null){

            // Initialize:
            String type; // the type of geological underground
            int countPoint = fault.getPointCount(); // the number of point in the fault
            Point pointTemp; // the point that we project
            List<Float> pos, posScreenTemp; // the position on the screen of the point which has been projected
            float[] pts = new float[2*countPoint]; // the points in the fault
            float xPos,yPos; // the position on the screen of the point which has been projected
            boolean draw= false; // useful to know if we draw or not the path

            // We project each point of the shape on the screen with a perspective projection
            for(int j=0; j<2*countPoint; j=j+2){
                pointTemp = fault.getPoint(j/2);

                pos = Utilities.positionMatOr(user.getLocation(),pointTemp,orMat,zDef);
                posScreenTemp = Utilities.positionScreen(pos, (float) screenWidth, (float) screenHeight);

                // To draw we add the point to the path, then we draw the path:
                if(posScreenTemp !=null) {
                    xPos = posScreenTemp.get(0);
                    yPos = posScreenTemp.get(1);

                    pts[j] = xPos;
                    pts[j+1] = yPos;
                }
            }

            if(draw) {
                canvas.drawLines(pts, paint);
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
     * @param fault: the geometry of the fault lines
     * @param ormat: the orientation matrix
     * @param usr: the user
     */
    public void setVariables(Polyline fault,
                             float[] ormat,
                             User usr){

        this.fault = fault;
        this.orMat = ormat;
        this.user = usr;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void setZdef(float zdef){
        this.zDef = zdef;
    }

}
