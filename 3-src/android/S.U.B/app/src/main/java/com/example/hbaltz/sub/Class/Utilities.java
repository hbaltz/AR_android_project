package com.example.hbaltz.sub.Class;

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
     * Function which verifies if the real azimuths is between the limit of the theoretical azimuth
     *
     * @param pois: the list of pois
     * @param azimuth_accuracy: the real azimuth's accuracy
     * @return the POIS with completed field visible which is a boolean
     * (true if the theoretical azimuth if between minAngle and maxAngle, false else)
     */
    public static ArrayList<BuildingPOI> isAzimuthsVisible(ArrayList<BuildingPOI> pois,
                                                       double azimuthRe,
                                                       double azimuth_accuracy){

        ArrayList<BuildingPOI> poiVisible = new ArrayList<>();
        int len_azimuths = pois.size();

        // Initialize
        BuildingPOI poiTemp;
        double azimuth, minAngle, maxAngle;
        boolean isVisible;

        for(int i=0; i<len_azimuths; i++){
            poiTemp = pois.get(i);
            azimuth = poiTemp.getAzimut();

            List<Double> minMax = Utilities.azimuthAccuracy(azimuth,azimuth_accuracy);

            minAngle = minMax.get(0);
            maxAngle = minMax.get(1);

            isVisible = Utilities.isBetween(minAngle, maxAngle, azimuthRe);
            poiTemp.setVisible(isVisible);

            poiVisible.add(poiTemp);
        }

        return  poiVisible;
    }
}
