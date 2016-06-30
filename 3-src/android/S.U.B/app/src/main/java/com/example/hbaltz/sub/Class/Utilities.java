package com.example.hbaltz.sub.Class;

import android.util.Log;

import com.esri.core.geometry.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hbaltz on 6/1/2016.
 */

public final class Utilities {

    private final static boolean DEBUG = false;

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Function which calculates the minimal and maximum value of the angle
     * around the angle's accuracy
     *
     * @param angleTheo: an angle
     * @param angle_accuracy: the angle's accuracy
     * @return minimal and maximum value of the angle around the angle's accuracy
     */
    public static List<Double> angleAccuracy(double angleTheo, double angle_accuracy) {
        double minAngle = angleTheo - angle_accuracy;
        double maxAngle = angleTheo + angle_accuracy;
        List<Double> minMax = new ArrayList<>();
  
        minMax.clear();
        minMax.add(minAngle);
        minMax.add(maxAngle);

        return minMax;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Function which verifies if the angle is between minAngle and maxAngle of the theoretical azimuth
     *
     * @param minAngle: minimal angle value for the azimuth
     * @param maxAngle: maximal angle value for the azimuth
     * @param angle: the angle
     * @return a boolean(true if angle is between minAngle and maxAngle, false else)
     */
    public static boolean isBetween(double minAngle, double maxAngle, double angle) {
        if (minAngle > maxAngle) {
            if  (angle < minAngle && angle > maxAngle)
                return true;
        } else {
            if (angle > minAngle && angle < maxAngle)
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
     * Function which projects the point pt on the screen using a perspective projection defined by
     * the information about the location of the user and the orientation of the device
     * (based on http://aldream.net/article/2013-04-13-painter-s-algorithm/)
     *
     * @param locUser: the location of the user/device
     * @param pt: the location of the point that we want to project
     * @param orMat: the orientation matrix of the device
     * @param W: the screen's width
     * @param H: the screen/s heigth
     * @return the position on the screen of the poi
     */
    public static List<Float> screenPositionMatOr(Point locUser, Point pt, float[] orMat,
                                                  float W, float H, float zDef){
        List<Float> pos = new ArrayList<>();

        float x = (float)(pt.getX()-locUser.getX());
        float y = (float)(pt.getY()-locUser.getY());
        float z = (float)(pt.getZ()-locUser.getZ());
        if(z == 0) z = zDef;

        if(DEBUG)Log.d("XYZ", "X: " + x + ", Y: " + y + ", Z: " + z);

        float ThetaYaw = (float)Math.toRadians(orMat[0]);
        float ThetaPitch = (float)Math.toRadians(orMat[1]);
        float ThetaRoll = (float)Math.toRadians(orMat[2] );

        float Cyaw = (float)Math.cos(ThetaYaw);
        float Cpitch = (float)Math.cos(ThetaPitch);
        float Croll = (float)Math.cos(ThetaRoll);

        float Syaw = (float)Math.sin(ThetaYaw);
        float Spitch = (float)Math.sin(ThetaPitch);
        float Sroll = (float)Math.sin(ThetaRoll);

        float temp1 = Cyaw*y - Syaw*x;
        float temp2 = Croll*z + Sroll*(Syaw*y + Cyaw*x);

        float Dx = Croll*(Syaw*y + Cyaw*x) - Sroll*z;
        float Dy = Spitch*temp2 + Cpitch*temp1;
        float Dz = Cpitch*temp2 - Spitch*temp1;

        if(DEBUG) Log.d("D", "Dx: " + Dx + ", Dy: " + Dy + ", Dz: " + Dz);
        
        float xPos = (W/2)+((H/2)*Dx/Dz);
        float yPos = (H/2)+((H/2)*Dy/Dz);

        if(DEBUG)Log.d("pos", "X: " + xPos + ", Y: " + yPos);

        pos.add(xPos);
        pos.add(yPos);

        return pos;
    }
}
