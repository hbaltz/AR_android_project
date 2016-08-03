package com.example.hbaltz.aton.utilities;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.example.hbaltz.aton.hull.JarvisMarch;
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

            ArrayList<float[]> ceiling = Various.detectCelling(FBImp,FBImp.position()/3,0.7f);
            Log.d("ceiling", "" + ceiling.size());

            ArrayList<float[]> testCeil = new ArrayList<float[]>();
            for(int i = 0; i<5 ; i++){
                testCeil.add(ceiling.get(i));
            }

            JarvisMarch jarvisMarch = new JarvisMarch();
            Polygon convCeiling = jarvisMarch.convexHull(testCeil);
            Log.d("ceilArea", "" + convCeiling.getArea());

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
