package com.example.hbaltz.sub.Class;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hbaltz on 6/1/2016.
 */

public final class Utilities {

    /**
     * Function which calculate the minimal and maximum value of the azimuth
     * around the azimuth's accuracy
     *
     * @param azimuthTheo: an azimuth
     * @param azimuth_accuracy: the azimuth's accuracy
     * @return minimal and maximum value of the azimuth around the azimuth's accuracy
     */
    public static List<Double> azimuthAccuracy(double azimuthTheo, double azimuth_accuracy) {
        double minAngle = azimuthTheo - azimuth_accuracy;
        double maxAngle = azimuthTheo + azimuth_accuracy;
        List<Double> minMax = new ArrayList<Double>();

        if (minAngle < 0) {minAngle += 360;}
        if (maxAngle >= 360){maxAngle -= 360;}

        minMax.clear();
        minMax.add(minAngle);
        minMax.add(maxAngle);

        return minMax;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Function which verify if the real azimuth if between minAngle and maxAngle of the theoretical azimuth
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
     * Function which verify if the real azimuths is between the limit of the theoretical azimuth
     *
     * @param azimuthTheos: the theoretical azimuths
     * @param azimuth_accuracy: the real azimuth's accuracy
     * @return an ArrayList of boolean
     * (true if the theoretical azimuth if between minAngle and maxAngle, false else)
     */
    public static ArrayList<Boolean> isAzimuthsVisible(ArrayList<Double> azimuthTheos,
                                                       double azimuthRe,
                                                       double azimuth_accuracy){

        ArrayList<Boolean> visible = new ArrayList<Boolean>();
        int len_azimuths = azimuthTheos.size();

        for(int i=0; i<len_azimuths; i++){
            double azimuth = azimuthTheos.get(i);
            List<Double> minMax = Utilities.azimuthAccuracy(azimuthTheos.get(i),azimuth_accuracy);
            double minAngle = minMax.get(0);
            double maxAngle = minMax.get(1);

            boolean isVisible = Utilities.isBetween(minAngle, maxAngle, azimuthRe);
            visible.add(isVisible);
        }

        return  visible;
    }
}
