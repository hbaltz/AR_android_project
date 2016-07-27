package com.example.hbaltz.aton.utilities;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.hbaltz.aton.renderer.PointCollection;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
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
    private FloatBuffer fbCeiling;

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

            ArrayList<float[]> ceiling = Various.detectCelling(FBImp,FBImp.position()/3,1f);
            Log.d("ceiling", "" + ceiling.size());
            if(ceiling.size() !=0)Log.d("ceilingY", "" + ceiling.get(0)[1]);

            fbCeiling = Various.ArrayList2FloatBuffer(ceiling);

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            String nameFile = String.format("ceiling_%s", roomName);

            Various.createFile(context,nameFile,fbCeiling,fbCeiling.position()/3);
            Various.makeToast(context,"Point cloud opened!");
        }
    }
}
