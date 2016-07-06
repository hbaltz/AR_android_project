package com.example.hbaltz.sub.Class;

import android.util.Log;

import com.esri.core.geometry.Geometry;
import com.esri.core.geometry.GeometryEngine;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.Polygon;
import com.esri.core.geometry.Polyline;
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
    private double distToFault;

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

    public double getDistToFault() {
        return this.distToFault;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// SETTERS: //////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void setLocation(Point location) {
        this.Location = location;
    }

    public void setDistToFault(double distToFault) {
        this.distToFault = distToFault;
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
                                                GeoInfo[] geoInfos, SpatialReference spaRef,
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
                        build.setGeoInfo(geomen,geoInfos,spaRef);
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
     * Function which finds the footprints below a distance of radius unit from the point
     *
     * @param geomen: A geometry engine (Esri)
     * @param footprints: The array of footprints
     * @param spaRef: the spatial reference
     * @param radius: The distance to consider a geometries like a NN (Nearest Neighbor)
     * @param unit: The distance's unit
     * @return an ArrayList of footprints which are the nearest geometries to the point
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
     * Function which recovers the geological close to the user, it simplifies geometries
     *
     * @param geomen: A geometry engine (Esri)
     * @param geoInfos: The general geological information
     * @param spaRef: the spatial reference
     * @param radius: the distance around the user where we recover geological information
     * @param unit: The distance's unit
     * @return
     */
    public ArrayList<GeoInfo> simplifyGeoInfo(GeometryEngine geomen,
                                                GeoInfo[] geoInfos,
                                                SpatialReference spaRef,
                                                double radius, Unit unit){

        ArrayList<GeoInfo> simpGeoInfos = new ArrayList<>();

        Point loc = this.getLocation();
        Geometry buffer = geomen.buffer(loc, spaRef, radius, unit);
        GeoInfo geoTemp;
        Polygon simpShape;

        for(GeoInfo geoInfo : geoInfos){
            simpShape = (Polygon) geomen.intersect(geoInfo.getShape(),buffer,spaRef);
            if(simpShape.calculateArea2D() !=0) {
                geoInfo.setShape(simpShape);
                simpGeoInfos.add(geoInfo);
            }
        }

        return simpGeoInfos;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Function which recovers the fault lines close to the user, it simplifies geometries
     *
     * @param geomen: A geometry engine (Esri)
     * @param fault: The fault polyline
     * @param spaRef: the spatial reference
     * @param radius: the distance around the user where we recover geological information
     * @param unit: The distance's unit
     * @return
     */
    public Polyline simplifyFault(GeometryEngine geomen,
                                      Polyline fault,
                                      SpatialReference spaRef,
                                      double radius, Unit unit){

        Polyline simpFault = new Polyline();

        Point loc = this.getLocation();
        Geometry buffer = geomen.buffer(loc, spaRef, radius, unit);
        GeoInfo geoTemp;

        simpFault = (Polyline) geomen.intersect(fault,buffer,spaRef);

        return simpFault;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Caulcates the dsitance between the POI and the nearest faultLine
     *
     * @param fault: the fault line
     * @param geomen: a geometry engine
     * @param spaRef: the spatial reference
     */
    public void calculateDistToFault(Geometry fault, GeometryEngine geomen, SpatialReference spaRef){
        this.distToFault = geomen.distance(this.Location, fault, spaRef);
    }
}
