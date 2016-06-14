package com.example.hbaltz.sub.Class;

import com.esri.core.geometry.Point;
import com.esri.core.geometry.Polygon;

import java.util.Comparator;

/**
 * Created by hbaltz on 6/1/2016.
 */
public class BuildingPOI implements Comparable<BuildingPOI>{

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// FIELDS: ///////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private String structure;
    private String occupancyClass;
    private String address;
    private String notes;
    private String buildingType;
    private String constructionYear;
    private String numberOfStories;
    private String verticalIrregularity;
    private String planIrregularity;
    private Point location;
    private Polygon footprint;
    private double distance;
    private double azimut;
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
    }

    public BuildingPOI(){
        this.structure = "";
        this.occupancyClass = "";
        this.buildingType="";
        this.address = "";
        this.notes = "";
        this.location = new Point();
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

    public double getDistance() {
        return this.distance;
    }

    public double getAzimut() {
        return this.azimut;
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

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public void setAzimut(double azimut) {
        this.azimut = azimut;
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
}
