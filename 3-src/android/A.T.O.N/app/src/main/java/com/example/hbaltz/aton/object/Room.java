package com.example.hbaltz.aton.object;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hbaltz.aton.MainActivity;
import com.example.hbaltz.aton.R;
import com.example.hbaltz.aton.hull.JarvisMarch;
import com.example.hbaltz.aton.polygon.Polygon;
import com.example.hbaltz.aton.utilities.PointCloudExporter;
import com.example.hbaltz.aton.utilities.Various;

import java.nio.FloatBuffer;
import java.util.ArrayList;

/**
 * Created by hbaltz on 8/4/2016.
 */
public class Room {
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// FIELDS: /////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private String name;
    private FloatBuffer pointCloud;
    private float volume;
    private float volumeOcc;
    private String type;
    private float height;
    private FloatBuffer floor;
    private FloatBuffer ceiling;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// CONSTRUCTORS: ///////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public Room(){}

    public Room(String name, FloatBuffer pc){
        this.name = name;
        this.pointCloud = pc;

        ArrayList<float[]> ceiling = Various.detectCelling(pointCloud,pointCloud.position()/3,1f);
        ArrayList<float[]> floor = Various.detectFloor(pointCloud,pointCloud.position()/3,1f);

        float yCeil = Various.findYMedian(ceiling);
        float yFloor = Various.findYMedian(floor);
        this.height = yCeil - yFloor;

        JarvisMarch jarvisMarch = new JarvisMarch();
        Polygon convCeiling = jarvisMarch.convexHull(ceiling);

        this.volume = height * convCeiling.getArea();

        this.ceiling = Various.ArrayList2FloatBuffer(ceiling);
        this.floor = Various.ArrayList2FloatBuffer(floor);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// SETTERS: ////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////


    public void setName(String name) {
        this.name = name;
    }

    public void setPointCloud(FloatBuffer pointCloud) {
        this.pointCloud = pointCloud;
    }

    public void setVolume(float volume) {
        this.volume = volume;
    }

    public void setVolumeOcc(float volumeOcc) {
        this.volumeOcc = volumeOcc;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setFloor(FloatBuffer floor) {
        this.floor = floor;
    }

    public void setCeiling(FloatBuffer ceiling) {
        this.ceiling = ceiling;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// GETTERS: ////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////


    public String getName() {
        return this.name;
    }

    public FloatBuffer getPointCloud() {
        return this.pointCloud;
    }

    public float getVolume() {
        return this.volume;
    }

    public float getVolumeOcc() {
        return this.volumeOcc;
    }

    public String getType() {
        return this.type;
    }

    public float getHeight() {
        return this.height;
    }

    public FloatBuffer getFloor() {
        return this.floor;
    }

    public FloatBuffer getCeiling() {
        return this.ceiling;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// METHODS: ////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @SuppressLint("SetTextI18n")
    public void displayInformation(final Context context){

        final Dialog dialog = new Dialog(context);
        dialog.setContentView(R.layout.dialog_display_info_room);
        dialog.setTitle(context.getString(R.string.titleInfoRoom));

        Button dialogButton = (Button) dialog.findViewById(R.id.buttonOkInfoRoom);

        final TextView tvName = (TextView) dialog.findViewById(R.id.textViewNameRoom);
        final TextView tvHeight = (TextView) dialog.findViewById(R.id.textViewHeightRoom);
        final TextView tvVolume = (TextView) dialog.findViewById(R.id.textViewVolumeRoom);
        final TextView tvVolumeOcc = (TextView) dialog.findViewById(R.id.textViewVolumeOccRoom);
        final TextView tvType = (TextView) dialog.findViewById(R.id.textViewTypeRoom);

        tvName.setText("Name: " + name);
        tvHeight.setText("Height: " + height + " m");
        tvVolume.setText("Volume: " + volume + " m^3");
        tvVolumeOcc.setText("Volume occupied: " + volumeOcc + " m^3");
        tvType.setText("Type: " + type);

        // if button is clicked, close the custom dialog
        dialogButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }

}
