package com.example.hbaltz.aton.utilities;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.hbaltz.aton.hull.JarvisMarch;
import com.example.hbaltz.aton.object.Room;
import com.example.hbaltz.aton.polygon.Polygon;
import com.example.hbaltz.aton.renderer.PointCollection;

import java.nio.FloatBuffer;
import java.util.ArrayList;

/**
 * Created by hbaltz on 7/27/2016.
 */
public class PointCloudVolumeCalculator {
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// FIELDS: /////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private final Context context;
    private final String roomName;
    private Room room;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// CONSTRUCTORS: ///////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public PointCloudVolumeCalculator(Context context, String nameroom) {
        this.context = context;
        this.roomName = nameroom;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// METHODS: ////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void calculate() {new ExportAsyncTask().execute();}

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private class ExportAsyncTask extends AsyncTask<PointCollection, Integer, Void> {

        @Override
        protected Void doInBackground(PointCollection... params) {
            String fileName = String.format("pointcloud-%s.xyz", roomName);

            FloatBuffer FBImp = Various.readFromFile(context,fileName);
            Log.d("testRead",""+FBImp);

            room = new Room(roomName,FBImp);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            String nameFileCeiling = String.format("ceiling_%s", roomName);
            FloatBuffer fbCeiling = room.getCeiling();
            Various.createFile(context,nameFileCeiling,fbCeiling,fbCeiling.position()/3);

            String nameFileFloor = String.format("floor_%s", roomName);
            FloatBuffer fbFloor = room.getFloor();
            Various.createFile(context,nameFileFloor,fbFloor,fbFloor.position()/3);

            Log.d("Vol", "" + room.getVolume());

            room.displayInformation(context);
        }
    }
}
