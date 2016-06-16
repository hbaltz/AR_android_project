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

        double phiAngle;
        double tanPhi;
        double azimuth;

        tanPhi = Math.abs(dY / dX);
        phiAngle = Math.atan(tanPhi);
        phiAngle = Math.toDegrees(phiAngle);

        // We calculate the azimuth :
        azimuth = phiAngle;

        if (dX > 0 && dY > 0) { // I quater
            azimuth = phiAngle;
        } else if (dX < 0 && dY > 0) { // II
            azimuth = 180 - phiAngle;
        } else if (dX < 0 && dY < 0) { // III
            azimuth = 180 + phiAngle;
        } else if (dX > 0 && dY < 0) { // IV
            azimuth = 360 - phiAngle;
        }

        return azimuth;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Function which calculates the theoretical azimuth between the user's location
     * and every poi in the arrayList Pois and if the poi is visible by the user
     *
     * @param Pois : the arrayList of poi
     * @return the pois with the theoretical azimuths
     */
    public ArrayList<BuildingPOI> theoreticalAzimuthToPOIs(ArrayList<BuildingPOI> Pois,
                                                           double azimuthRe,
                                                           double azimuth_accuracy){
        int len_pois = Pois.size();

        ArrayList<BuildingPOI> poisAzs = new ArrayList<>();

        BuildingPOI poiTemp;
        double azimuth, minAngle, maxAngle;
        boolean isVisible;

        for (int i=0; i<len_pois; i++){
            poiTemp = Pois.get(i);
            azimuth = this.theoreticalAzimuthToPOI(Pois.get(i));
            poiTemp.setAzimut(azimuth);

            List<Double> minMax = Utilities.azimuthAccuracy(azimuth,azimuth_accuracy);

            minAngle = minMax.get(0);
            maxAngle = minMax.get(1);

            isVisible = Utilities.isBetween(minAngle, maxAngle, azimuthRe);
            poiTemp.setVisible(isVisible);

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

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Function which calculates the theoretical pitch between the user's location and a POI
     *
     * @param Poi : a poin of a building
     * @return the theoretical pitch
     */
    public double theoreticalPitchToPOI(BuildingPOI Poi) {
        // Initialize
        Point locUsr = this.getLocation();
        Point locPoi = Poi.getLocation();

        double dx = locPoi.getX() - locUsr.getX();
        double dy = locPoi.getY() - locUsr.getY();
        double dz = locPoi.getZ() - locUsr.getZ();
        if(dz==0) dz = -1.8; // <-- PB found

        double pitch;

        pitch = Math.atan2(dy, Math.sqrt((Math.pow(dx,2))+(Math.pow(dz,2))));
        pitch = Math.toDegrees(pitch);

        return pitch; // degrees
    }
}
