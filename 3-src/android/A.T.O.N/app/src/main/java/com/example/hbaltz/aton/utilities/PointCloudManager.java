package com.example.hbaltz.aton.utilities;

import com.example.hbaltz.aton.rajawali.Pose;
import com.example.hbaltz.aton.rajawali.renderables.primitives.Points;
import com.example.hbaltz.aton.renderer.PointCollection;
import com.google.atap.tangoservice.TangoCameraIntrinsics;
import com.google.atap.tangoservice.TangoPoseData;
import com.google.atap.tangoservice.TangoXyzIjData;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

/**
 * Created by hbaltz on 7/14/2016.
 */
public class PointCloudManager {
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// FIELDS: /////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private static final String tag = PointCloudManager.class.getSimpleName();


    private final TangoCameraIntrinsics tangoCameraIntrinsics;
    private final TangoXyzIjData xyzIjData;
    private TangoPoseData devicePoseAtCloudTime;
    private double lastCloudTime = 0;
    private double newCloudTime = 0;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// CONSTRUCTORS: ///////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Constructors
     *
     * @param intrinsics: the intrinsics parameters
     */
    public PointCloudManager(TangoCameraIntrinsics intrinsics) {
        tangoCameraIntrinsics = intrinsics;
        xyzIjData = new TangoXyzIjData();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// SETTERS: ////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void setDevicePoseAtCloudTime(TangoPoseData devicePoseAtCloudTime) {
        this.devicePoseAtCloudTime = devicePoseAtCloudTime;
    }

    public void setLastCloudTime(double lastCloudTime) {
        this.lastCloudTime = lastCloudTime;
    }

    public void setNewCloudTime(double newCloudTime) {
        this.newCloudTime = newCloudTime;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// GETTERS: ////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public TangoCameraIntrinsics getTangoCameraIntrinsics() {
        return tangoCameraIntrinsics;
    }

    public TangoPoseData getDevicePoseAtCloudTime() {
        return devicePoseAtCloudTime;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// FUNCTIONS: //////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Updates the XuzIjData
     *
     * @param from: the new data
     * @param xyzIjPose: the pose
     */
    public synchronized void updateXyzIjData(TangoXyzIjData from, TangoPoseData xyzIjPose) {
        devicePoseAtCloudTime = xyzIjPose;
        this.newCloudTime = from.timestamp;

        if (xyzIjData.xyz == null || xyzIjData.xyz.capacity() < from.xyzCount * 3) {
            xyzIjData.xyz = ByteBuffer.allocateDirect(from.xyzCount * 3 * 4)
                    .order(ByteOrder.nativeOrder()).asFloatBuffer();
        } else {
            xyzIjData.xyz.rewind();
        }

        xyzIjData.xyzCount = from.xyzCount;
        xyzIjData.timestamp = from.timestamp;

        from.xyz.rewind();
        xyzIjData.xyz.put(from.xyz);
        xyzIjData.xyz.rewind();
        from.xyz.rewind();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Fills the current point
     *
     * @param currentPoints: the old current point
     * @param pose: the pose
     */
    public synchronized void fillCurrentPoints(Points currentPoints, Pose pose) {
        currentPoints.updatePoints(xyzIjData.xyzCount, xyzIjData.xyz);
        currentPoints.setPosition(pose.getPosition());
        currentPoints.setOrientation(pose.getOrientation());
        lastCloudTime = newCloudTime;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Fills the current point
     *
     * @param collectedPoints: the old collected point
     * @param pose: the pose
     */
    public synchronized void fillCollectedPoints(PointCollection collectedPoints, Pose pose) {
        collectedPoints.updatePoints(xyzIjData.xyz, xyzIjData.xyzCount, pose);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Tests if the application has acquired new point
     *
     * @return true if the the application has acquired new point, false if not
     */
    public synchronized boolean hasNewPoints() {
        return newCloudTime != lastCloudTime;
    }
}
