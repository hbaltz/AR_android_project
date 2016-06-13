package com.example.hbaltz.sub.Renderer;

import javax.microedition.khronos.egl.EGLConfig;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;

import javax.microedition.khronos.opengles.GL10;


/**
 * Created by hbaltz on 6/13/2016.
 */
public class GeoGLRenderer implements GLSurfaceView.Renderer {

    @Override
    public void onSurfaceCreated(GL10 gl, EGLConfig config) {
        // Set the background frame color
        GLES30.glClearColor(0.0f, 0.0f, 0.0f, 1.0f);
    }

    @Override
    public void onSurfaceChanged(GL10 unused, int width, int height) {
        GLES30.glViewport(0, 0, width, height);
    }

    @Override
    public void onDrawFrame(GL10 unused) {
        // Redraw background color
        GLES30.glClear(GLES30.GL_COLOR_BUFFER_BIT);
    }
}
