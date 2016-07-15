package com.example.hbaltz.aton.renderer;

import android.graphics.Color;
import android.opengl.GLES10;
import android.opengl.GLES20;

import com.example.hbaltz.aton.rajawali.Pose;

import org.rajawali3d.Object3D;
import org.rajawali3d.materials.Material;
import org.rajawali3d.math.Matrix4;
import org.rajawali3d.math.vector.Vector3;

import java.nio.FloatBuffer;

/**
 * Created by hbaltz on 7/13/2016.
 */
public class PointCollection extends Object3D {
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// FIELDS: /////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private FloatBuffer buffer;
    private int mMaxNumberOfVertices;
    private int count = 0;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// CONSTRUCTORS: ///////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public PointCollection(int numberOfPoints) {
        super();
        mMaxNumberOfVertices = numberOfPoints;
        init();
        Material m = new Material();
        m.setColor(Color.GREEN);
        setMaterial(m);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// GETTERS: ////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public int getCount() {
        return this.count;
    }

    public FloatBuffer getBuffer() {
        return this.buffer;
    }

    public int getmMaxNumberOfVertices() {
        return this.mMaxNumberOfVertices;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// SETTERS: ////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void setBuffer(FloatBuffer buffer) {
        this.buffer = buffer;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setmMaxNumberOfVertices(int mMaxNumberOfVertices) {
        this.mMaxNumberOfVertices = mMaxNumberOfVertices;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// METHODS: ////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    protected void init() {
        count = 0;
        buffer = FloatBuffer.allocate(mMaxNumberOfVertices * 3);
        float[] vertices = new float[mMaxNumberOfVertices * 3];
        int[] indices = new int[mMaxNumberOfVertices];
        for (int i = 0; i < indices.length; ++i) {
            indices[i] = i;
        }
        setData(vertices, GLES20.GL_STATIC_DRAW,
                null, GLES20.GL_STATIC_DRAW,
                null, GLES20.GL_STATIC_DRAW,
                null, GLES20.GL_STATIC_DRAW,
                indices, GLES20.GL_STATIC_DRAW,
                true);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void updatePoints(FloatBuffer pointCloudBuffer, int pointCount, Pose pose) {
        if (count + pointCount < mMaxNumberOfVertices) {
            pointCloudBuffer.position(0);
            FloatBuffer transformedPoints = FloatBuffer.allocate(pointCount * 3);
            for (int i = 0; i < pointCount; i++) {
                double x = pointCloudBuffer.get();
                double y = pointCloudBuffer.get();
                double z = pointCloudBuffer.get();
                Vector3 v = new Vector3(x, y, z);
                Matrix4 transformation = Matrix4.createTranslationMatrix(pose.getPosition()).rotate(pose.getOrientation());
                v.multiply(transformation);
                transformedPoints.put((float) v.x);
                transformedPoints.put((float) v.y);
                transformedPoints.put((float) v.z);
                buffer.put((float) v.x);
                buffer.put((float) v.y);
                buffer.put((float) v.z);
            }

            mGeometry.setNumIndices(pointCount + count);
            mGeometry.getVertices().position(0);
            mGeometry.changeBufferData(mGeometry.getVertexBufferInfo(), transformedPoints, count * 3, pointCount * 3);
            count += pointCount;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void preRender() {
        super.preRender();
        setDrawingMode(GLES20.GL_POINTS);
        GLES10.glPointSize(3.0f);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void clear() {
        count = 0;
        mGeometry.setNumIndices(0);
    }

}
