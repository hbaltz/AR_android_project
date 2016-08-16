package com.example.hbaltz.sub.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.hbaltz.sub.Class.BuildingPOI;
import com.example.hbaltz.sub.Class.User;
import com.example.hbaltz.sub.Class.Utilities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

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

    ////////////////////////////////////// User: ///////////////////////////////////////////////////
    private User user;

    ////////////////////////////////////// Angles: /////////////////////////////////////////////////
    float[] orMat;

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

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onDraw(Canvas canvas) {
        if(POIs != null) {
            int len_pois = POIs.size();

            // Initialize:
            boolean isVisible; // true is the poi is visible by the user
            double dist;// the distance between the poi and the user
            List<Float> posScreen;// the position on the screen of the point which has been projected
            float xPosScreen, yPosScreen;// the position on the screen of the point which has been projected
            float radius;// the size og the dote on the screen
            float w; //the size of the text
            String strct;// useful to initialize the paint
            ArrayList<String> information;// the information that we want to display
            ArrayList<Integer> sizeStrings;// the size of the string included in information


            for(int i =0; i<len_pois; i++) {
                // We recover the POI et the filed visible to know if the user sees the POI
                BuildingPOI POI = POIs.get(i);

                isVisible = POI.isVisible();

                if (isVisible) {
                    // If the poi is visible we recover information about it :
                    dist = POI.getDistance();

                    /////////////////////////////////// Calculate location: ////////////////////////
                    // We calculate where the point have to be draw
                    posScreen = Utilities.positionMatOr(user.getLocation(), POI.getLocation(), orMat, 0f);
                    posScreen = Utilities.positionScreen(posScreen, (float) screenWidth, (float) screenHeight);

                    if (posScreen != null) {
                        xPosScreen = posScreen.get(0);

                        // We draw in the middle of the y-axis
                        yPosScreen = (float) ((screenHeight / 2));

                        // We calculate the radius of the circle and of the text regarding the distance
                        radius = (float) (2000 / dist);

                        /////////////////////////////////// Recover info: //////////////////////////
                        // We recover the information about the Poi that we want to display
                        strct = POI.getStructure();
                        information = POI.recoverInformation();
                        sizeStrings = Utilities.lengths(information); // Useful for the size of the rectangle

                        // We initialize the paint regarding the structure field:
                        paint = initializedPaint(strct);
                        paint.setTextSize(radius);

                        /////////////////////////////////// Draw: //////////////////////////////////
                        // We draw the circle:
                        canvas.drawCircle(xPosScreen, yPosScreen, radius, paint);

                        // We define the size of the rectangle:
                        w = radius * Collections.max(sizeStrings) / 2;

                        // We draw the rectangle and the texts:
                        drawInformation(canvas, information, xPosScreen, yPosScreen, radius, w, paint, paintRect);
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
            infoTemp = information.get(sizeInfo-k-1); // To draw the first the array in the ArrayLIst the higher
            c.drawText(infoTemp, x-r, y-(k+1)*(r+1), paint);
        }
    }
}
