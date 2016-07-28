package com.example.hbaltz.aton.utilities;


import android.content.Context;
import android.os.AsyncTask;

import com.example.hbaltz.aton.renderer.PointCollection;

import java.nio.FloatBuffer;



public class PointCloudExporter {
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// FIELDS: /////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    private final Context context;
    private final PointCollection pointCollection;
    private String roomName = "";

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// CONSTRUCTORS: ///////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public PointCloudExporter(Context context, String roomname, PointCollection pointCollection) {
        this.context = context;
        this.roomName = roomname;
        this.pointCollection = pointCollection;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////// METHODS: ////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void export() {new ExportAsyncTask().execute(pointCollection);}

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private class ExportAsyncTask extends AsyncTask<PointCollection, Integer, Void> {

        @Override
        protected Void doInBackground(PointCollection... params) {
            if (params.length == 0) {
                return null;
            }
            PointCollection pointCollection = params[0];
            FloatBuffer floatBuffer = pointCollection.getBuffer();
            int size = pointCollection.getCount();

            Various.createFile(context, roomName, floatBuffer, size);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Various.makeToast(context,"Point cloud exported!");
        }
    }
}
