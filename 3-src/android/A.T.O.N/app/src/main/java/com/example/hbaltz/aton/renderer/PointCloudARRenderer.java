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

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hbaltz.aton.R;
import com.example.hbaltz.aton.rajawali.Pose;
import com.example.hbaltz.aton.rajawali.TouchViewHandler;
import com.example.hbaltz.aton.rajawali.ar.TangoRajawaliRenderer;
import com.example.hbaltz.aton.rajawali.renderables.primitives.Points;
import com.example.hbaltz.aton.MainActivity;
import com.example.hbaltz.aton.utilities.PointCloudExporter;
import com.example.hbaltz.aton.utilities.PointCloudManager;
import com.example.hbaltz.aton.utilities.Various;

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

    public void exportPointCloud(final MainActivity mainActivity) {

        final Dialog dialog = new Dialog(mainActivity);
        dialog.setContentView(R.layout.dialog_export);
        dialog.setTitle(mainActivity.getString(R.string.titleName));

        Button dialogButton = (Button) dialog.findViewById(R.id.dialogButtonOK);
        final EditText nameRoom = (EditText) dialog.findViewById(R.id.nameRoom);

        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String name = nameRoom.getText().toString();

                if(!name.equals("")) {
                    PointCloudExporter exporter = new PointCloudExporter(mainActivity, name, collectedPoints);
                    exporter.export();

                    dialog.dismiss();
                } else {
                    Various.makeToast(mContext,mainActivity.getString(R.string.noName));
                }
            }
        });

        dialog.show();

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
