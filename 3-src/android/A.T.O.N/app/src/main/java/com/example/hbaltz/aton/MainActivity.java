package com.example.hbaltz.aton;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.hbaltz.aton.rajawali.ar.TangoRajawaliView;
import com.example.hbaltz.aton.renderer.PointCloudARRenderer;
import com.example.hbaltz.aton.utilities.PointCloudManager;
import com.example.hbaltz.aton.utilities.Various;
import com.google.atap.tangoservice.Tango;
import com.google.atap.tangoservice.TangoCameraIntrinsics;
import com.google.atap.tangoservice.TangoConfig;
import com.google.atap.tangoservice.TangoCoordinateFramePair;
import com.google.atap.tangoservice.TangoEvent;
import com.google.atap.tangoservice.TangoPoseData;
import com.google.atap.tangoservice.TangoXyzIjData;

import java.util.ArrayList;

public class MainActivity extends Activity implements View.OnTouchListener {
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// VARIABLES: //////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private TangoRajawaliView glView;
    private PointCloudARRenderer renderer;
    private PointCloudManager pointCloudManager;
    private Tango tango;
    private boolean isConnected;
    private boolean isPermissionGranted;

    private TangoPoseData cloudPose;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// METHODS: ////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Initialize the renderer:
        glView = new TangoRajawaliView(this);
        renderer = new PointCloudARRenderer(this);
        glView.setSurfaceRenderer(renderer);
        glView.setOnTouchListener(this);
        setContentView(R.layout.activity_main);
        RelativeLayout wrapper = (RelativeLayout) findViewById(R.id.wrapper_view);
        tango = new Tango(this);
        startActivityForResult(Tango.getRequestPermissionIntent(Tango.PERMISSIONTYPE_MOTION_TRACKING), Tango.TANGO_INTENT_ACTIVITYCODE);
        wrapper.addView(glView);

        // Recover the name of the room already on the internal memory:
        ArrayList<String> roomsNames = Various.recoverListOfFiles(this);
        Log.d("roomsNames", roomsNames.toString());
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onPause() {
        super.onPause();
        if (isConnected) {
            glView.disconnectCamera();
            tango.disconnect();
            isConnected = false;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onResume() {
        super.onResume();
        if (!isConnected && isPermissionGranted) {
            startAugmentedReality();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
            renderer.capturePoints();
        }
        return true;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Result on the permmision of the motion tracking:
        if (requestCode == Tango.TANGO_INTENT_ACTIVITYCODE) {
            if (resultCode == RESULT_CANCELED) {
                Toast.makeText(this, "Motion Tracking Permissions Required!", Toast.LENGTH_SHORT).show();
                finish();
            } else {
                startAugmentedReality();
                isPermissionGranted = true;
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Starts the augmented reality
     */
    private void startAugmentedReality() {
        if (!isConnected) {
            // Initialize if the camera is not connected:
            isConnected = true;
            glView.connectToTangoCamera(tango, TangoCameraIntrinsics.TANGO_CAMERA_COLOR);
            TangoConfig config = tango.getConfig(TangoConfig.CONFIG_TYPE_DEFAULT);
            config.putBoolean(TangoConfig.KEY_BOOLEAN_LOWLATENCYIMUINTEGRATION, true);
            config.putBoolean(TangoConfig.KEY_BOOLEAN_DEPTH, true);// Allow the depth perception
            tango.connect(config);

            ArrayList<TangoCoordinateFramePair> framePairs = new ArrayList<TangoCoordinateFramePair>();
            // Set the listener
            tango.connectListener(framePairs, new Tango.OnTangoUpdateListener() {
                @Override
                public void onPoseAvailable(TangoPoseData pose) {
                }

                @Override
                public void onFrameAvailable(int cameraId) {
                    if (cameraId == TangoCameraIntrinsics.TANGO_CAMERA_COLOR) {
                        glView.onFrameAvailable();
                    }
                }

                // When the xyz information are available for the scene, we update he point cloud manager
                @Override
                public void onXyzIjAvailable(TangoXyzIjData xyzIj) {
                    TangoCoordinateFramePair framePair = new TangoCoordinateFramePair(
                            TangoPoseData.COORDINATE_FRAME_START_OF_SERVICE,
                            TangoPoseData.COORDINATE_FRAME_DEVICE);
                    cloudPose = tango.getPoseAtTime(xyzIj.timestamp, framePair);

                    pointCloudManager.updateXyzIjData(xyzIj, cloudPose);
                }

                @Override
                public void onTangoEvent(TangoEvent event) {

                }
            });

            // Setup the extrinsic:
            setupExtrinsic();

            // Associate the point cloud manager to the renderer:
            pointCloudManager = new PointCloudManager(tango.getCameraIntrinsics(TangoCameraIntrinsics.TANGO_CAMERA_COLOR));
            renderer.setPointCloudManager(pointCloudManager);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Setups the extrinsic
     */
    private void setupExtrinsic() {
        // Create Camera to IMU Transform
        TangoCoordinateFramePair framePair = new TangoCoordinateFramePair();
        framePair.baseFrame = TangoPoseData.COORDINATE_FRAME_IMU;
        framePair.targetFrame = TangoPoseData.COORDINATE_FRAME_CAMERA_COLOR;
        TangoPoseData imuTrgbPose = tango.getPoseAtTime(0.0, framePair);

        // Create Device to IMU Transform
        framePair.targetFrame = TangoPoseData.COORDINATE_FRAME_DEVICE;
        TangoPoseData imuTdevicePose = tango.getPoseAtTime(0.0, framePair);

        // Create Depth camera to IMU Transform
        framePair.targetFrame = TangoPoseData.COORDINATE_FRAME_CAMERA_DEPTH;
        TangoPoseData imuTdepthPose = tango.getPoseAtTime(0.0, framePair);

        renderer.setupExtrinsics(imuTdevicePose, imuTrgbPose, imuTdepthPose);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main_menu, menu);
        return true;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.activity_main_menu_export_pointcloud:
                renderer.exportPointCloud(this);
                return true;
            case R.id.activity_main_menu_clear:
                renderer.clearPointCloud();
                return true;
            case R.id.activity_main_menu_toggle_pointcloud:
                renderer.togglePointCloudVisibility();
                return true;
            case R.id.activity_main_menu_display_pointcloud:
                renderer.displayPointCloud(this);
                return true;
            case R.id.activity_main_menu_send_pointcloud:
                renderer.sendPointCloud(this);
                return true;
            case R.id.activity_main_menu_calculate_volume:
                renderer.calculateVolumeRoom(this);
                return true;
            case R.id.activity_main_menu_delete_file:
                renderer.deleteFile(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}