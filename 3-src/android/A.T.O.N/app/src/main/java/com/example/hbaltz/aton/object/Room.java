package com.example.hbaltz.aton.object;

import com.example.hbaltz.aton.hull.JarvisMarch;
import com.example.hbaltz.aton.polygon.Polygon;
import com.example.hbaltz.aton.utilities.Various;

import java.nio.FloatBuffer;
import java.util.ArrayList;

/**
 * Created by hbaltz on 8/4/2016.
 */
public class Room {
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// FIELDS: /////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private String name;
    private FloatBuffer pointCloud;
    private float volume;
    private float volumeOcc;
    private String type;
    private float height;
    private FloatBuffer floor;
    private FloatBuffer ceiling;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// CONSTRUCTORS: ///////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public Room(){}

    public Room(String name, FloatBuffer pc){
        this.name = name;
        this.pointCloud = pc;

        ArrayList<float[]> ceiling = Various.detectCelling(pointCloud,pointCloud.position()/3,1f);
        ArrayList<float[]> floor = Various.detectFloor(pointCloud,pointCloud.position()/3,1f);

        float yCeil = Various.findYMedian(ceiling);
        float yFloor = Various.findYMedian(floor);
        this.height = yCeil - yFloor;

        JarvisMarch jarvisMarch = new JarvisMarch();
        Polygon convCeiling = jarvisMarch.convexHull(ceiling);

        this.volume = height * convCeiling.getArea();

        this.ceiling = Various.ArrayList2FloatBuffer(ceiling);
        this.floor = Various.ArrayList2FloatBuffer(floor);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// SETTERS: ////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////


    public void setName(String name) {
        this.name = name;
    }

    public void setPointCloud(FloatBuffer pointCloud) {
        this.pointCloud = pointCloud;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public void setVolumeOcc(float volumeOcc) {
        this.volumeOcc = volumeOcc;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setFloor(FloatBuffer floor) {
        this.floor = floor;
    }

    public void setCeiling(FloatBuffer ceiling) {
        this.ceiling = ceiling;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// GETTERS: ////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////


    public String getName() {
        return this.name;
    }

    public FloatBuffer getPointCloud() {
        return this.pointCloud;
    }

    public float getVolume() {
        return this.volume;
    }

    public float getVolumeOcc() {
        return this.volumeOcc;
    }

    public String getType() {
        return this.type;
    }

    public float getHeight() {
        return this.height;
    }

    public FloatBuffer getFloor() {
        return this.floor;
    }

    public FloatBuffer getCeiling() {
        return this.ceiling;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// METHODS: ////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////


}
