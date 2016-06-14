package com.example.hbaltz.sub.Class;

import com.esri.core.geometry.Geometry;
import com.esri.core.geometry.GeometryEngine;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.SpatialReference;
import com.esri.core.geometry.Unit;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

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
     * Function which finds the buildings below a distance of radius unit from the point
     *
     * @param geomen : A geometry engine (Esri)
     * @param builds : The array of buildings
     * @param spaRef : the spatial reference
     * @param radius : The distance to consider a geometries like a NN (Nearest Neighbor)
     * @param unit : The distance's unit
     * @return an ArrayList of buldings which qre the nearest geometries to the point
     */
    public ArrayList<BuildingPOI> nearestNeighbors(GeometryEngine geomen,
                                                BuildingPOI[] builds,
                                                SpatialReference spaRef,
                                                double radius, Unit unit){

        ArrayList<BuildingPOI> NN = new ArrayList<>();
        int len_builds = builds.length;

        Point loc = this.getLocation();

        Geometry buffer = geomen.buffer(loc, spaRef, radius, unit);

        for (int i=0; i<len_builds; i++){
            if(builds[i]!=null) {
                Geometry locPOI = builds[i].getLocation();
                if (locPOI != null) {
                    if (geomen.intersects(buffer, locPOI, spaRef)) {
                        NN.add(builds[i]);
                    }
                }
            }
        }
        return NN;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Function which calculates the distance between a point and all the buildings in an array
     *
     * @param geomen : A geometry engine (Esri)
     * @param builds : The arrayList of buildings
     * @param spaRef : The spatial reference
     * @return the builds with distance
     */
    public ArrayList<BuildingPOI> distanceToPOIs(GeometryEngine geomen,
                                          ArrayList<BuildingPOI> builds,
                                          SpatialReference spaRef){
        int len_builds = builds.size();
        ArrayList<BuildingPOI> buildsDist = new ArrayList<>();

        Point loc = this.getLocation();

        BuildingPOI buildTemp;

        for (int i=0; i<len_builds; i++){
            buildTemp = builds.get(i);
            buildTemp.setDistance(geomen.distance(loc, builds.get(i).getLocation(), spaRef));
            buildsDist.add(buildTemp);
        }

        // Sort (useful when we draw)
        Collections.sort(buildsDist);

        return buildsDist;
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
     * and every poi in the arrayList Pois
     *
     * @param Pois : the arrayList of poi
     * @return the pois with the theoretical azimuths
     */
    public ArrayList<BuildingPOI> theoreticalAzimuthToPOIs(ArrayList<BuildingPOI> Pois){
        int len_pois = Pois.size();

        ArrayList<BuildingPOI> poisAzs = new ArrayList<>();

        BuildingPOI poiTemp;
        double azimuth;

        for (int i=0; i<len_pois; i++){
            poiTemp = Pois.get(i);
            azimuth = this.theoreticalAzimuthToPOI(Pois.get(i));
            poiTemp.setAzimut(azimuth);
            poisAzs.add(poiTemp);
        }

        return poisAzs;
    }
}
