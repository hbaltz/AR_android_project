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

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hbaltz.aton.R;
import com.example.hbaltz.aton.provider.MailProvider;
import com.example.hbaltz.aton.rajawali.Pose;
import com.example.hbaltz.aton.rajawali.TouchViewHandler;
import com.example.hbaltz.aton.rajawali.ar.TangoRajawaliRenderer;
import com.example.hbaltz.aton.rajawali.renderables.primitives.Points;
import com.example.hbaltz.aton.MainActivity;
import com.example.hbaltz.aton.utilities.PointCloudExporter;
import com.example.hbaltz.aton.utilities.PointCloudManager;
import com.example.hbaltz.aton.utilities.Various;

import java.io.File;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * Renderer for Point Cloud data.
 */
public class PointCloudARRenderer extends TangoRajawaliRenderer {
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// FIELDS: /////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private static final int MAX_POINTS = 100000;
    private static final int MAX_COLLECTED_POINTS = 300000;
    private static final int MAX_OPENED_POINTS = 300000;

    private Points currentPoints;
    private PointCollection collectedPoints;
    private PointCollection openedPoints;
    private PointCloudManager pointCloudManager;
    private boolean collectPoints;
    private boolean openPoints;

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

        openedPoints = new PointCollection(MAX_OPENED_POINTS);
        openedPoints.setMaterial(Materials.getRedPointCloudMaterial());
        getCurrentScene().addChild(openedPoints);
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

            /*
            if(openPoints) {
                openPoints = false;
                pointCloudManager.fillCollectedPoints(openedPoints, pose);
            }
            */
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Writes on the internal memory the point cloud in the format .txt
     *
     * @param mainActivity: : the main activity
     */
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
                    Log.d("sizeCP", "" + collectedPoints.getCount());
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

    /**
     * displays the selected point cloud
     *
     * @param mainActivity: the main activity
     */
    public void displayPointCloud(final MainActivity mainActivity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
        builder.setTitle(mainActivity.getString(R.string.chooseRoom));

        ArrayList<String> listTemp = Various.recoverListOfFiles(mainActivity);

        final CharSequence[] nameRoom = Various.ArrayList2CharSeq(listTemp);

        builder.setItems(nameRoom,new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        clearPointCloud();

                        String fileName = String.format("pointcloud-%s.txt", nameRoom[which]);

                        FloatBuffer FBImp = Various.readFromFile(mainActivity,fileName);
                        Log.d("testRead",""+FBImp);

                        openedPoints.addPoints(FBImp,FBImp.position()/3);

                        openPoints = true;

                        Various.makeToast(mainActivity, "Display point cloud of " + nameRoom[which]);
                    }
        });

        builder.show();

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Sends the selected point cloud by email
     *
     * @param mainActivity: the main activity
     */
    public void sendPointCloud(final MainActivity mainActivity) {
        AlertDialog.Builder builder = new AlertDialog.Builder(mainActivity);
        builder.setTitle(mainActivity.getString(R.string.chooseRoom));

        ArrayList<String> listTemp = Various.recoverListOfFiles(mainActivity);

        final CharSequence[] nameRoom = Various.ArrayList2CharSeq(listTemp);

        builder.setItems(nameRoom,new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                        final int pos = which;

                        final Dialog dialogMail = new Dialog(mainActivity);
                        dialogMail.setContentView(R.layout.dialog_send);
                        dialogMail.setTitle(mainActivity.getString(R.string.enter_email));

                        Button dialogButton = (Button) dialogMail.findViewById(R.id.dialogMailButtonOK);
                        final EditText email = (EditText) dialogMail.findViewById(R.id.emailText);

                        // if button is clicked, close the custom dialog
                        dialogButton.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String mail = email.getText().toString();
                                String filename = String.format("pointcloud-%s.txt", nameRoom[pos]);

                                mainActivity.startActivity(getSendEmailIntent(mail,
                                        "Point Cloud of the room: " + nameRoom[pos],
                                        "In attachment the point cloud of the room: " + nameRoom[pos],
                                        filename));

                                dialogMail.dismiss();
                            }
                        });

                        dialogMail.show();
                    }
        });

        builder.show();

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

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Creates the Intent to send a email with a file in attachment (the file is on internal memory)
     *
     * @param email: the address mail where you went to send the email
     * @param subject: the email's subject
     * @param body: the email's body
     * @param fileName: the name of the file that you want to attached
     * @return an Intent
     */
    public static Intent getSendEmailIntent(String email, String subject, String body, String fileName) {

        final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);

        //Explicitly only use Gmail to send
        emailIntent.setClassName("com.google.android.gm","com.google.android.gm.ComposeActivityGmail");

        emailIntent.setType("plain/text");

        //Add the recipients
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[] { email });

        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject);

        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, body);

        //Add the attachment by specifying a reference to our custom ContentProvider
        //and the specific file of interest
        emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse("content://" + MailProvider.AUTHORITY + "/" + fileName));

        return emailIntent;
    }
}
