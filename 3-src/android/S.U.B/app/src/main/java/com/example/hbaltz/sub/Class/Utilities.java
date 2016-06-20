package com.example.hbaltz.sub.Class;

import android.util.Log;

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
                                            double dist, double W, double H){
        List<Float> pos = new ArrayList<>();

        double xPos = Math.sin(angVer) * dist;
        double yPos = Math.sin(angHor) * dist;

        //Log.d("coord", "X: " + xPos + ", Y: " + yPos);

        double ratio = W/H;

        // TODO : orientation pas bonne
        pos.add((float) ((W/2) + (ratio*xPos)));
        pos.add((float) ((H/2) - (ratio*yPos)));

        return pos;
    }
}
