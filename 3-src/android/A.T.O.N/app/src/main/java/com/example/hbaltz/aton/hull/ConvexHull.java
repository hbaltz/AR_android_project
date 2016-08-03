package com.example.hbaltz.aton.hull;

import com.example.hbaltz.aton.polygon.Polygon;

import java.util.ArrayList;


/**
 * Created by hbaltz on 8/3/2016.
 */


public interface ConvexHull {

    /**
     * Creates the polygon which is the convex hull of a set of points
     *
     * @param points: the set of points
     * @return  the convex hull of a set of points
     */
    public abstract Polygon convexHull(ArrayList<float[]> points);
}
