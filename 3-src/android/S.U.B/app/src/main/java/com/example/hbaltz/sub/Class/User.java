package com.example.hbaltz.sub.Class;

import android.util.Log;

import com.esri.core.geometry.Geometry;
import com.esri.core.geometry.GeometryEngine;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.Polygon;
import com.esri.core.geometry.SpatialReference;
import com.esri.core.geometry.Unit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Created by hbaltz on 6/1/2016.
 */
public class User {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// FIELDS: ///////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private Point Location;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// CONSTRUCTORS: /////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public User(Point location){
        this.Location = location;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// GETTERS: //////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public Point getLocation() {
        return this.Location;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// SETTERS: //////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void setLocation(Point location) {
        this.Location = location;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// FUNCTIONS: ////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Function which finds the POIs below a distance of radius unit from the point
     * and calculates the distances between the user and the POIs
     *
     * @param geomen : A geometry engine (Esri)
     * @param builds : The array of buildings
     * @param spaRef : the spatial reference
     * @param radius : The distance to consider a geometries like a NN (Nearest Neighbor)
     * @param unit : The distance's unit
     * @return an ArrayList of buldings which qre the nearest geometries to the point
     */
    public ArrayList<BuildingPOI> nearestNeighbors(GeometryEngine geomen,
                                                BuildingPOI[] builds, ArrayList<Polygon> footprints,
                                                SpatialReference spaRef,
                                                double radius, Unit unit){

        ArrayList<BuildingPOI> NN = new ArrayList<>();

        Point loc = this.getLocation();

        Geometry buffer = geomen.buffer(loc, spaRef, radius, unit);

        for (BuildingPOI build : builds) {
            if (build != null) {
                Geometry locPOI = build.getLocation();
                if (locPOI != null) {
                    if (geomen.intersects(buffer, locPOI, spaRef)) {
                        build.setDistance(geomen.distance(loc, build.getLocation(), spaRef));
                        build.setPoly(geomen,footprints,spaRef);
                        NN.add(build);
                    }
                }
            }
        }

        // Sort (useful when we draw)
        Collections.sort(NN);

        return NN;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Function which calculates the theoretical azimuth between the user's location and a POI
     *
     * @param Poi : a poin of a building
     * @return the theoretical azimuth
     */
    public double theoreticalAzimuthToPOI(BuildingPOI Poi) {
        // Initialize
        Point locUsr = this.getLocation();
        Point locPoi = Poi.getLocation();

        double dX = locPoi.getX() - locUsr.getX();
        double dY = locPoi.getY() - locUsr.getY();

        double azimuth;

        azimuth = Math.atan2(dY,dX);
        azimuth = (Math.toDegrees(azimuth) + 360) % 360 ; // degrees between 0 and 360


        return azimuth;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Function which calculates the theoretical pitch between the user's location and a POI
     *
     * @param Poi : a poi
     * @return the theoretical pitch
     */
    public double theoreticalPitchToPOI(BuildingPOI Poi) {
        // Initialize
        Point locUsr = this.getLocation();
        Point locPoi = Poi.getLocation();

        double dx = locPoi.getX() - locUsr.getX();
        double dy = locPoi.getY() - locUsr.getY();
        double dz = locPoi.getZ() - locUsr.getZ();
        if(dz==0) dz =20d;

        double pitch;

        pitch = Math.atan2(dy,Math.sqrt((Math.pow(dx,2))+(Math.pow(dz,2))));
        pitch = (Math.toDegrees(pitch)+360)%360;

        return pitch; // degrees between 0 and 360
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Function which calculates the theoretical azimuth between the user's location
     * and every poi in the arrayList Pois and if the poi is visible by the user
     *
     * @param Pois : the arrayList of poi
     * @return the pois with the theoretical azimuths
     */
    public ArrayList<BuildingPOI> theoreticalAngleToPOIs(ArrayList<BuildingPOI> Pois,
                                                           double azimuthRe,
                                                           double azimuth_accuracy,
                                                           double pitchRe,
                                                           double pitch_accuracy){
        int len_pois = Pois.size();

        ArrayList<BuildingPOI> poisAzs = new ArrayList<>();

        BuildingPOI poiTemp;
        List<Double> minMaxAz, minMaxPt;
        double azimuth, pitch, minAngleAz, maxAngleAz, minAnglePt, maxAnglePt;
        boolean isVisibleAz, isVisiblePt;

        for (int i=0; i<len_pois; i++){
            // We calculate the angle of the poi:
            poiTemp = Pois.get(i);

            azimuth = this.theoreticalAzimuthToPOI(poiTemp);
            poiTemp.setAzimut(azimuth);

            pitch = this.theoreticalPitchToPOI(poiTemp);
            poiTemp.setPitch(pitch);

            // We calculate if the user sees or not the poi:

            // Azimuth:
            minMaxAz = Utilities.angleAccuracy(azimuth,azimuth_accuracy);

            minAngleAz = minMaxAz.get(0);
            maxAngleAz = minMaxAz.get(1);

            isVisibleAz = Utilities.isBetween(minAngleAz, maxAngleAz, azimuthRe);

            // Pitch:
            minMaxPt = Utilities.angleAccuracy(pitch,pitch_accuracy);

            minAnglePt = minMaxPt.get(0);
            maxAnglePt = minMaxPt.get(1);

            isVisiblePt = Utilities.isBetween(minAnglePt, maxAnglePt, pitchRe);

            poiTemp.setVisible((isVisibleAz && isVisiblePt));

            poisAzs.add(poiTemp);
        }

        return poisAzs;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Function which finds the footprints below a distance of radius unit from the point
     *
     * @param geomen : A geometry engine (Esri)
     * @param footprints : The array of footprints
     * @param spaRef : the spatial reference
     * @param radius : The distance to consider a geometries like a NN (Nearest Neighbor)
     * @param unit : The distance's unit
     * @return an ArrayList of buldings which qre the nearest geometries to the point
     */
    public ArrayList<Polygon> nearestFootprints(GeometryEngine geomen,
                                                   Polygon[] footprints,
                                                   SpatialReference spaRef,
                                                   double radius, Unit unit){

        ArrayList<Polygon> NF = new ArrayList<>();

        Point loc = this.getLocation();

        Geometry buffer = geomen.buffer(loc, spaRef, radius, unit);

        for (Polygon footprint : footprints) {
            if (footprint != null) {
                if (geomen.intersects(buffer, footprint, spaRef)) {
                    NF.add(footprint);
                }
            }
        }

        return NF;
    }
}
