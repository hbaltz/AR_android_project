package com.example.hbaltz.sub.Class;

import com.esri.core.geometry.Polygon;

/**
 * Created by hbaltz on 5/27/2016.
 */
public class Point {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// FIELDS: ///////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private float X;
    private float Y;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// CONSTRUCTORS: /////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public Point(float x, float y){
        this.setX(x);
        this.setY(y);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// GETTERS: //////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public float getX() {
        return this.X;
    }

    public float getY() {
        return this.Y;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// SETTERS: //////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void setX(float x) {
        this.X = x;
    }

    public void setY(float y) {
        this.Y = y;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// METHODS: //////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public double distanceTo(Polygon polygon){
        // TODO : Calculate distance point to polygon

        return 0;
    }
}
