package com.example.hbaltz.sub.Class;

import android.util.Log;

import com.esri.core.geometry.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hbaltz on 6/1/2016.
 */

public final class Utilities {

    /**
     * Function which calculates the minimal and maximum value of the azimuth
     * around the azimuth's accuracy
     *
     * @param azimuthTheo: an azimuth
     * @param azimuth_accuracy: the azimuth's accuracy
     * @return minimal and maximum value of the azimuth around the azimuth's accuracy
     */
    public static List<Double> azimuthAccuracy(double azimuthTheo, double azimuth_accuracy) {
        double minAngle = azimuthTheo - azimuth_accuracy;
        double maxAngle = azimuthTheo + azimuth_accuracy;
        List<Double> minMax = new ArrayList<>();

        if (minAngle < 0) {minAngle += 360;}
        if (maxAngle >= 360){maxAngle -= 360;}

        minMax.clear();
        minMax.add(minAngle);
        minMax.add(maxAngle);

        return minMax;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Function which verifies if the real azimuth if between minAngle and maxAngle of the theoretical azimuth
     *
     * @param minAngle: minimal angle value for the azimuth
     * @param maxAngle: maximal angle value for the azimuth
     * @param azimuthRe: the real azimuth
     * @return a boolean(true if the theoretical azimuth if between minAngle and maxAngle, false else)
     */
    public static boolean isBetween(double minAngle, double maxAngle, double azimuthRe) {
        if (minAngle > maxAngle) {
            if (isBetween(0, maxAngle, azimuthRe) && isBetween(minAngle, 360, azimuthRe))
                return true;
        } else {
            if (azimuthRe > minAngle && azimuthRe < maxAngle)
                return true;
        }
        return false;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Recovers the length of string in an array list.
     *
     * @param list: an ArrayList of strings
     * @return: an ArrayList of lengths
     */
    public static ArrayList<Integer> lengths(ArrayList<String> list) {
        ArrayList<Integer> lengthList = new ArrayList<Integer>();
        for (String s : list)
            lengthList.add(s.length());
        return lengthList;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Calculates the position on the screen of a point with an angle ang and a distance dist
     * between it and the user.
     *
     * @param angVer: the vertical angle between the point and the user in radian
     * @param angHor: the horizontalical angle between the point and the user in radian
     * @param dist: the distance between the point and the user in meters
     * @param W: the screen's width
     * @param H: the screen's height
     * @return the point's position on the screen
     */
    public static List<Float> screenPosition(double angVer, double angHor,
                                             double dist, double W, double H,
                                             double camW, double camH){
        List<Float> pos = new ArrayList<>();

        double xPos = Math.sin(angVer) * dist;
        double yPos = Math.sin(angHor) * dist;

        //Log.d("coord", "X: " + xPos + ", Y: " + yPos);

        double ratio = ((H*W)/(240*320));

        // TODO : orientation pas bonne
        pos.add((float) ((W/2) + (xPos)));
        pos.add((float) ((H/2) - (yPos)));

        return pos;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////


    public static List<Float> screenPositionMatOr(Point locUser, Point pt, float[] orMat,
                                                  float W, float H, float camW, float camH, float dist){
        List<Float> pos = new ArrayList<>();

        float x = (float)(pt.getX()-locUser.getX());
        float y = (float)(pt.getY()-locUser.getY());
        float z = (float)(pt.getZ()-locUser.getZ());
        if(z == 0) z = 3f;

        //Log.d("XYZ", "X: " + x + ", Y: " + y + ", Z: " + z);

        float ThetaX = (float)Math.toRadians(orMat[0]);
        float ThetaY = (float)Math.toRadians(orMat[1]);
        float ThetaZ = (float)Math.toRadians(orMat[2]);
        //float ThetaZ = (float)(Math.PI/4);

        float Cx = (float)Math.cos(ThetaX);
        float Cy = (float)Math.cos(ThetaY);
        float Cz = (float)Math.cos(ThetaZ);

        float Sx = (float)Math.sin(ThetaX);
        float Sy = (float)Math.sin(ThetaY);
        float Sz = (float)Math.sin(ThetaZ);
/*
        float Dx = Cy*(Sz*y + Cz*x) - Sy*z;
        float Dy = Sx*(Cy*z + Sy*(Sz*y+Cz*x)) + Cx*(Cz*y - Sz*x);
        float Dz = Cx*(Cy*z + Sy*(Sz*y+Cz*x)) - Sx*(Cz*y - Sz*x);

        Log.d("elDz", "Cx:" + Cx + "+ Cy:" + Cy + "* z:" + z + "+ Sy:" + Sy + "*( Sz:" + Sz + "* y:"
                + y + "+ Cz:" + Cz + "* x:" + x + ") Sx:" + Sx + "*( Cz:" + Cz + "* y" + y + "- Sz:"
                + Sz + "* x:" + x);

*/
        float Dx = Cy*x + Sy*z;
        float Dy = Cx*y - Sx*(-Sy*x+Cy*z);
        float Dz = Sx*y + Cx*(-Sy*x+Cy*z);

        //float FOV = (float)(2 * Math.atan(Math.sqrt((W/(2*4.14f))*(W/(2*4.14f)) + (H/(2*6f))*(H/(2*6f)))));
        float FOV = (float)(2 * Math.atan(0.5 * W / 4.14));
        float distEq = (float)((W/2)/Math.tan(Math.toRadians(40)));


        //Log.d("D", "X: " + Dx + ", Dy: " + Dy + ", Dz: " + Dz);

        float xPos = (distEq)*Dx/Dz;
        float yPos = (distEq)*Dy/Dz;

        //Log.d("pos", "X: " + xPos + ", Y: " + yPos);

        pos.add((W/2)+xPos);
        pos.add((H/2)-yPos);

        return pos;
    }
}
