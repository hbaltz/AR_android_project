package com.example.hbaltz.sub.Class;

import android.util.Log;

import com.esri.core.geometry.Geometry;
import com.esri.core.geometry.GeometryEngine;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.SpatialReference;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hbaltz on 6/1/2016.
 */

public final class Utilities {

    private final static boolean DEBUG = false;

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Function which makes the union of an array of geometries regardless of his size
     *
     * @param geoms:  the array of geometries
     * @param SpaRef: the spatial reference of these geometries
     * @return union: a geometry resulting from the union
     */
    public static Geometry unionGeoms(Geometry[] geoms, SpatialReference SpaRef, GeometryEngine geomen) {
        // Initialize
        Geometry union = null;
        int len_geoms = geoms.length;

        int len_lim = 500; // Limit length of the geometry array (limit for the union)

        // If the array have a length below the limit we can do the union,
        // else we have to split the array in several parts and do the union in several times
        if (len_geoms < len_lim) {
            union = geomen.union(geoms, SpaRef);
        } else {
            // Initialize:
            Geometry[] array_union = new Geometry[2]; // temporary array for the union
            Geometry[] geomTemp = new Geometry[len_lim];
            Geometry[] geomRem = new Geometry[len_geoms - len_lim]; // The remaining geometries
            int k = 1; // useful for the while loop

            // We split geometries in two for the union:
            int len_temp = geomTemp.length;
            int len_rem = geomRem.length;
            System.arraycopy(geoms, 0, geomTemp, 0, len_temp);
            System.arraycopy(geoms, len_temp, geomRem, 0, len_rem);

            if (DEBUG) {Log.d("len_geom_temp", "" + geomTemp.length);}
            if (DEBUG) {Log.d("len_geom_rem", "" + geomRem.length);}

            // We do the union of the array with a length below the limit
            array_union[0] = geomen.union(geomTemp, SpaRef);

            // While the length of the remaining geometries is not below the limit
            // we split the array and we do several union to never exceed the limit
            while (len_rem > len_lim) {
                // We split the array in two, we use geomTemp:
                System.arraycopy(geomRem, 0, geomTemp, 0, len_temp);
                k = k + 1;

                // geomRem recover the remaining geometries:
                geomRem = new Geometry[len_rem - len_lim];
                len_rem = geomRem.length;
                System.arraycopy(geoms, k * len_temp, geomRem, 0, len_rem);

                // We do the union of the geometries we have stock in geomTemp
                // and the union between the two geometries resulting from the unions
                array_union[1] = geomen.union(geomTemp, SpaRef);
                array_union[0] = geomen.union(array_union, SpaRef);
            }

            // We do the union of the remaining geometries
            // and the union between the two geometries resulting from the unions
            array_union[1] = geomen.union(geomRem, SpaRef);
            union = geomen.union(array_union, SpaRef);
        }

        return union;
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
            if (isBetween(0, maxAngle, angle) && isBetween(minAngle, 360, angle))
                return true;
        } else {
            if (angle > minAngle && angle < maxAngle)
                return true;
        }
        return false;
    }
}
