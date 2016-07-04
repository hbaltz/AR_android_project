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
     * @return the position on the captor of the poi
     */
    public static List<Float> positionMatOr(Point locUser, Point pt, float[] orMat,
                                            float zDef) {

        //TODO separate in two functions


        List<Float> pos = new ArrayList<>();

        float x = (float) (pt.getX() - locUser.getX());
        float y = (float) (pt.getY() - locUser.getY());
        float z = (float) (pt.getZ() - locUser.getZ());
        if (z == 0) z = zDef;

        if (DEBUG) Log.d("XYZ", "X: " + x + ", Y: " + y + ", Z: " + z);

        float ThetaYaw = (float) Math.toRadians(orMat[0]);
        float ThetaPitch = (float) Math.toRadians(orMat[1]);
        float ThetaRoll = (float) Math.toRadians(orMat[2]);

        float Cyaw = (float) Math.cos(ThetaYaw);
        float Cpitch = (float) Math.cos(ThetaPitch);
        float Croll = (float) Math.cos(ThetaRoll);

        float Syaw = (float) Math.sin(ThetaYaw);
        float Spitch = (float) Math.sin(ThetaPitch);
        float Sroll = (float) Math.sin(ThetaRoll);

        float temp1 = Cyaw * y - Syaw * x;
        float temp2 = Croll * z + Sroll * (Syaw * y + Cyaw * x);

        float Dx = Croll * (Syaw * y + Cyaw * x) - Sroll * z;
        float Dy = Spitch * temp2 + Cpitch * temp1;
        float Dz = Cpitch * temp2 - Spitch * temp1;

        pos.add(Dx);
        pos.add(Dy);
        pos.add(Dz);

        if (DEBUG) Log.d("D", "Dx: " + Dx + ", Dy: " + Dy + ", Dz: " + Dz);

        return pos;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Function which changes the coordinate system
     *
     * @param pos: the position ont the camera's captor
     * @param W: the screen's width
     * @param H: the screen's height
     * @return: the X,Y position on the device's screen
     */
    public static List<Float> positionScreen(List<Float> pos, float W, float H) {
        List<Float> posScreen = new ArrayList<>();

        float Dx = pos.get(0);
        float Dy = pos.get(1);
        float Dz = pos.get(2);

        if(Dz>0){

            float xPos = (W / 2) + ((H / 2) * Dx / Dz);
            float yPos = (H / 2) + ((H / 2) * Dy / Dz);

            if (DEBUG) Log.d("pos", "X: " + xPos + ", Y: " + yPos);

            posScreen.add(xPos);
            posScreen.add(yPos);

            return posScreen;
        }

        return null;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public static List<Float> nearestPointOnScreen(List<Float> point1, List<Float> point2, float near){
        List<Float> nearestPoint = new ArrayList<>();

        float Dx1 = point1.get(0);
        float Dy1 = point1.get(1);
        float Dz1 = point1.get(2);

        float Dx2 = point2.get(0);
        float Dy2 = point2.get(1);
        float Dz2 = point2.get(2);

        float n;

        // If v1 and v2 are both behind then don't draw the line.
        // If v1 is in front and v2 is behind then you need to find vc where the line intersects the near clip plane
        // If v2 is in front and v1 is behind then you need to find vc where the line intersects the near clip plane

        Log.d("Dz", "1: " + Dz1 + ", 2: " + Dz2);
        if(Dz1>= near && Dz2< near) {
            Log.d("Useful?", "Yep");

            n = (Dz1 - near) / (Dz1 - Dz2);
            nearestPoint.add((n * Dx1) + ((1 - n) * Dx2));
            nearestPoint.add((n * Dy1) + ((1 - n) * Dy2));
            nearestPoint.add(near);
        }else if(Dz2>= near && Dz1< near) {
            Log.d("Useful?", "Yep");

            n = (Dz2 - near) / (Dz2 - Dz1);
            nearestPoint.add((n * Dx2) + ((1 - n) * Dx1));
            nearestPoint.add((n * Dy2) + ((1 - n) * Dy1));
            nearestPoint.add(near);
        } else {
            nearestPoint = null;
        }

        return nearestPoint;
    }

}
