/*
 * Copyright 2014 Google Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.hbaltz.aton.renderer;

import android.content.Context;
import android.util.Log;
import android.view.MotionEvent;

import com.example.hbaltz.aton.rajawali.Pose;
import com.example.hbaltz.aton.rajawali.TouchViewHandler;
import com.example.hbaltz.aton.rajawali.ar.TangoRajawaliRenderer;
import com.example.hbaltz.aton.rajawali.renderables.primitives.Points;
import com.example.hbaltz.aton.MainActivity;
import com.example.hbaltz.aton.utilities.PointCloudExporter;
import com.example.hbaltz.aton.utilities.PointCloudManager;

/**
 * Renderer for Point Cloud data.
 */
public class PointCloudARRenderer extends TangoRajawaliRenderer {
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// FIELDS: /////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private static final int MAX_POINTS = 100000;
    private static final int MAX_COLLECTED_POINTS = 300000;

    private Points currentPoints;
    private PointCollection collectedPoints;
    private PointCloudManager pointCloudManager;
    private boolean collectPoints;

    private TouchViewHandler mTouchViewHandler;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// CONSTRUCTORS: ///////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public PointCloudARRenderer(Context context) {
        super(context);
        mTouchViewHandler = new TouchViewHandler(mContext, getCurrentCamera());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// METHODS: ////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void initScene() {
        super.initScene();

        currentPoints = new Points(MAX_POINTS, true);
        currentPoints.setMaterial(Materials.getGreenPointCloudMaterial());
        getCurrentScene().addChild(currentPoints);

        collectedPoints = new PointCollection(MAX_COLLECTED_POINTS);
        collectedPoints.setMaterial(Materials.getBluePointCloudMaterial());
        getCurrentScene().addChild(collectedPoints);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void capturePoints() {
        collectPoints = true;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onRender(long ellapsedRealtime, double deltaTime) {
        super.onRender(ellapsedRealtime, deltaTime);
        if (pointCloudManager != null && pointCloudManager.hasNewPoints()) {
            Pose pose = mScenePoseCalculator.toOpenGLPointCloudPose(pointCloudManager.getDevicePoseAtCloudTime());
            if (collectPoints) {
                collectPoints = false;
                pointCloudManager.fillCollectedPoints(collectedPoints, pose);
            }
            pointCloudManager.fillCurrentPoints(currentPoints, pose);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void exportPointCloud(MainActivity mainActivity) {

        // TODO add dialog

        PointCloudExporter exporter = new PointCloudExporter(mainActivity, collectedPoints);
        exporter.export();
        Log.d("Export", "Ok");
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onOffsetsChanged(float v, float v1, float v2, float v3, int i, int i1) {
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onTouchEvent(MotionEvent motionEvent) {
        mTouchViewHandler.onTouchEvent(motionEvent);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void setPointCloudManager(PointCloudManager pointCloudManager) {
        this.pointCloudManager = pointCloudManager;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void togglePointCloudVisibility() {
        currentPoints.setVisible(!currentPoints.isVisible());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void clearPointCloud() {
        collectedPoints.clear();
    }
}
