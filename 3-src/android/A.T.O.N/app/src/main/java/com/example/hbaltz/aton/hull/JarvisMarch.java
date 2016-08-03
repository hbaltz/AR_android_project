package com.example.hbaltz.aton.hull;

import android.util.Log;

import com.example.hbaltz.aton.polygon.Angle;
import com.example.hbaltz.aton.polygon.Point;
import com.example.hbaltz.aton.polygon.Polygon;

import java.util.ArrayList;

/**
 * Created by hbaltz on 8/3/2016.
 */
public class JarvisMarch implements ConvexHull{

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////// CONSTRUCTORS: ////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public JarvisMarch(){}

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////// METHODS: /////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public Polygon convexHull(ArrayList<float[]> points) {
        // Initialize:
        Point pointLow = new Point();
        float y;
        float ymin = Float.MAX_VALUE;

        // we find the point with the lowest z
        for(float[] point : points){
            y = point[2];
            if(y < ymin){
                ymin = y;
                pointLow.setXY(point[0], y);
            }
        }

        // Initialize iteration on points
        Point currentPoint = pointLow;
        Point nextPoint = new Point();
        double angle = 0;
        Polygon.Builder convexHullBuilder = new Polygon.Builder();

        // Iterate on point set to find point with smallest angle with respect
        // to previous line
        do {
            nextPoint = new Point();

            convexHullBuilder.addVertex(currentPoint);
            nextPoint = findNextPoint(currentPoint, angle, points);
            angle = Angle.horizontalAngle(currentPoint, nextPoint);
            currentPoint = nextPoint;

        } while (!currentPoint.equals(pointLow));

        // Create a polygon with points located on the convex hull
        return convexHullBuilder.build();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private Point findNextPoint(Point basePoint, double startAngle,
                                ArrayList<float[]> points) {
        Point minPoint = new Point();
        Point curPoint;
        double minAngle = Double.MAX_VALUE;
        double angle;

        for (float[] point : points) {
            curPoint = new Point(point[0],point[2]);

            // Avoid to test same point
            if (basePoint.equals(curPoint))
                continue;

            // Compute angle between current direction and next point
            angle = Angle.horizontalAngle(basePoint, curPoint);
            angle = Angle.formatAngle(angle - startAngle);

            // Keep current point if angle is minimal
            if (angle < minAngle) {
                minAngle = angle;
                minPoint = curPoint;
            }
        }

        return minPoint;
    }
}
