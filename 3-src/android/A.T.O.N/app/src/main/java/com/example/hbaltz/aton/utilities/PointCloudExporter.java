package com.example.hbaltz.aton.utilities;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.example.hbaltz.aton.R;
import com.example.hbaltz.aton.rajawali.renderables.PointCloud;
import com.example.hbaltz.aton.renderer.PointCollection;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.FloatBuffer;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


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
