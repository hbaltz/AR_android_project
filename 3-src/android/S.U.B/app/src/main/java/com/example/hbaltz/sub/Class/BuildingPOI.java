package com.example.hbaltz.sub.Class;

import com.esri.core.geometry.Point;

/**
 * Created by hbaltz on 6/1/2016.
 */
public class BuildingPOI {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// FIELDS: ///////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private String Name;
    private String Structure;
    private String Deteration;
    private String Type;
    private String Address;
    private String Notes;
    private Point Location;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// CONSTRUCTORS: /////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public BuildingPOI(String name, String structure, String deteration, String type,
                       String address, String notes, Point location) {
        this.Name = name;
        this.Structure = structure;
        this.Deteration = deteration;
        this.Type = type;
        this.Address = address;
        this.Notes = notes;
        this.Location = location;
    }

    public BuildingPOI(){
        this.Name = "";
        this.Structure = "";
        this.Deteration = "";
        this.Type = "";
        this.Address = "";
        this.Notes = "";
        this.Location = new Point();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// GETTERS: //////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public String getName() {
        return this.Name;
    }

    public String getStructure() {
        return this.Structure;
    }

    public String getDeteration() {
        return this.Deteration;
    }

    public String getType() {
        return this.Type;
    }

    public String getAddress() {
        return this.Address;
    }

    public String getNotes() {
        return this.Notes;
    }

    public Point getLocation() {
        return this.Location;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// SETTERS: //////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void setName(String name) {
        this.Name = name;
    }

    public void setStructure(String structure) {
        this.Structure = structure;
    }

    public void setDeteration(String deteration) {
        this.Deteration = deteration;
    }

    public void setType(String type) {
        this.Type = type;
    }

    public void setAddress(String address) {
        this.Address = address;
    }

    public void setNotes(String notes) {
        this.Notes = notes;
    }

    public void setLocation(Point location) {
        this.Location = location;
    }
    
}
