package com.example.hbaltz.sub.Class;

import android.util.Log;

import com.esri.core.geometry.GeometryEngine;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.Polygon;
import com.esri.core.geometry.SpatialReference;
import com.esri.core.map.Feature;

import java.util.ArrayList;

/**
 * Created by hbaltz on 6/1/2016.
 */
public class BuildingPOI implements Comparable<BuildingPOI>{

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// FIELDS: ///////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////// Information: //////////////////////////////////////////////
    private String structure;
    private String occupancyClass;
    private String address;
    private String notes;
    private String buildingType;
    private String constructionYear;
    private String numberOfStories;
    private String verticalIrregularity;
    private String planIrregularity;
    //////////////////////////////////// Geometry: /////////////////////////////////////////////////
    private Point location;
    private Polygon footprint;
    //////////////////////////////////// Geological information: ///////////////////////////////////
    private ArrayList<GeoInfo> geologicalInfo;
    //////////////////////////////////// Relative to the user: /////////////////////////////////////
    private double distance;
    private double azimut;
    private double pitch;
    private boolean visible;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// CONSTRUCTORS: /////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public BuildingPOI(String structure, String deteration, String occupancyClass,
                       String address, String notes, Point location) {
        this.structure = structure;
        this.occupancyClass = occupancyClass;
        this.address = address;
        this.notes = notes;
        this.location = location;

        this.geologicalInfo = new ArrayList<>();
    }

    public BuildingPOI(){
        this.structure = "";
        this.occupancyClass = "";
        this.address = "";
        this.notes = "";
        this.buildingType = "";
        this.constructionYear = "";
        this.numberOfStories = "";
        this.verticalIrregularity = "";
        this.planIrregularity = "";

        this.location = new Point();
        this.footprint = new Polygon();
        this.geologicalInfo = new ArrayList<>();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// GETTERS: //////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public String getStructure() {
        return this.structure;
    }

    public String getOccupancyClass() {
        return this.occupancyClass;
    }

    public String getAddress() {
        return this.address;
    }

    public String getNotes() {
        return this.notes;
    }

    public String getBuildingType() {
        return this.buildingType;
    }

    public String getConstructionYear() {
        return this.constructionYear;
    }

    public String getNumberOfStories() {
        return this.numberOfStories;
    }

    public String getVerticalIrregularity() {
        return this.verticalIrregularity;
    }

    public String getPlanIrregularity() {
        return this.planIrregularity;
    }

    public Point getLocation() {
        return this.location;
    }

    public Polygon getFootprint() {
        return this.footprint;
    }

    public ArrayList<GeoInfo> getGeologicalInfo() {
        return this.geologicalInfo;
    }

    public double getDistance() {
        return this.distance;
    }

    public double getAzimut() {
        return this.azimut;
    }

    public double getPitch() {
        return this.pitch;
    }

    public boolean isVisible() {
        return this.visible;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// SETTERS: //////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void setStructure(String structure) {
        this.structure = structure;
    }

    public void setOccupancyClass(String occupancyClass) {
        this.occupancyClass = occupancyClass;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType;
    }

    public void setConstructionYear(String constructionYear) {
        this.constructionYear = constructionYear;
    }

    public void setNumberOfStories(String numberOfStories) {
        this.numberOfStories = numberOfStories;
    }

    public void setVerticalIrregularity(String verticalIrregularity) {
        this.verticalIrregularity = verticalIrregularity;
    }

    public void setPlanIrregularity(String planIrregularity) {
        this.planIrregularity = planIrregularity;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    public void setFootprint(Polygon footprint) {
        this.footprint = footprint;
    }

    public void setGeologicalInfo(ArrayList<GeoInfo> geologicalInfo) {
        this.geologicalInfo = geologicalInfo;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setAzimut(double azimut) {
        this.azimut = azimut;
    }

    public void setPitch(double pitch) {
        this.pitch = pitch;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// FUNCTIONS: ////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public int compareTo(BuildingPOI another) {
        return this.getDistance() > another.getDistance()? -1 : 1;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Sets the footprint
     *
     * @param geomen: geometry engine
     * @param footprints: ArrayList<Polygon>
     * @param spaRef: spatial reference
     */
    public void setPoly( GeometryEngine geomen,
                         ArrayList<Polygon> footprints,
                         SpatialReference spaRef){

        Polygon footprintPOI = new Polygon();

        for (Polygon footprint1 : footprints) {
            if(footprint1!=null) {
                if (geomen.intersects(this.location, footprint1, spaRef)) {
                    footprintPOI = footprint1;
                    break;
                }
            }
        }

        this.footprint = footprintPOI;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Sets the geological information
     *
     * @param geomen: geometry engine
     * @param geoInfos: the array of GeoInfo
     * @param spaRef: spatial reference
     */
    public void setGeoInfo(GeometryEngine geomen,
                      GeoInfo[] geoInfos,
                      SpatialReference spaRef){

        this.geologicalInfo = new ArrayList<>();
        Polygon shape;

        for (GeoInfo geoInfo : geoInfos) {
            shape = geoInfo.getShape();
            if (shape != null) {
                if (geomen.intersects(geoInfo.getShape(), this.footprint, spaRef)) {
                    this.geologicalInfo.add(geoInfo);
                }
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Sets the information of the gdb in the class
     *
     * @param POI: the feature recovered from the gdb
     */
    public void setInformation(Feature POI){
        this.structure = ((String) POI.getAttributeValue("STRCTWALL"));
        this.buildingType = ((String) POI.getAttributeValue("BUILDTYPE"));
        this.occupancyClass = ((String) POI.getAttributeValue("OCCUPCLASS"));
        this.constructionYear = ((String) POI.getAttributeValue("CONSTYEAR"));
        this.numberOfStories = ((String) POI.getAttributeValue("NUMSTORIES"));
        this.verticalIrregularity = ((String) POI.getAttributeValue("VERTIRREG"));
        this.planIrregularity = ((String) POI.getAttributeValue("PLANIRREG"));
        this.address = ((String) POI.getAttributeValue("ADDRESS"));
        this.notes = ((String) POI.getAttributeValue("NOTES"));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Recovers the information about the POI in a arrayList
     *
     * @return an ArrayList of String
     */
    public ArrayList<String> recoverInformation(){
        ArrayList<String> information = new ArrayList<>();

        information.add("Structure wall: " + this.structure);
        information.add("Building type: " + this.buildingType);
        information.add("Occupancy class: " + this.occupancyClass);
        information.add("Construction year: " + this.constructionYear);
        information.add("Number of stories: " + this.numberOfStories);
        information.add("Vertical irregularity: " + this.verticalIrregularity);
        information.add("Plan irregularity: " + this.planIrregularity);
        information.add("Geological information: " + this.recoverGeoInfo());
        information.add("Address: " + this.address);
        information.add("Notes: " +this.notes);
        information.add("Distance : " + ((int)(this.distance)) + " m");

        return information;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Recovers the geological information about the POI in a arrayList
     *
     * @return a string
     */
    public String recoverGeoInfo(){
        String informaGeo = "";
        String separator = "";

        for(GeoInfo geoInfo : this.geologicalInfo){
            informaGeo = informaGeo + separator + geoInfo.getType();
            separator = ", "; //Change at the end of the first iteration
        }

        return informaGeo;
    }
}
