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
     * @param azimuth: an azimuth
     * @param azimuth_accuracy: the azimuth's accuracy
     * @return minimal and maximum value of the azimuth around the azimuth's accuracy
     */
    public List<Double> azimuthAccuracy(double azimuth, double azimuth_accuracy ) {
        double minAngle = azimuth - azimuth_accuracy;
        double maxAngle = azimuth + azimuth_accuracy;
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
     * Function which verify if the azimuth if between minAngle and maxAngle
     *
     * @param minAngle: minimal angle value for the azimuth
     * @param maxAngle: maximal angle value for the azimuth
     * @param azimuth: the azimuth
     * @return a boolean(true if the azimuth if between minAngle and maxAngle, false else)
     */
    private boolean isBetween(double minAngle, double maxAngle, double azimuth) {
        if (minAngle > maxAngle) {
            if (isBetween(0, maxAngle, azimuth) && isBetween(minAngle, 360, azimuth))
                return true;
        } else {
            if (azimuth > minAngle && azimuth < maxAngle)
                return true;
        }
        return false;
    }

}
