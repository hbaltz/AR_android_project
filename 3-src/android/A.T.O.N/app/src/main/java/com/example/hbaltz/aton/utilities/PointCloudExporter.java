package com.example.hbaltz.aton.utilities;


import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.example.hbaltz.aton.R;
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
    private String filePath;
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

    public void export() {
        new ExportAsyncTask().execute(pointCollection);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private class ExportAsyncTask extends AsyncTask<PointCollection, Integer, Void> {

        @Override
        protected Void doInBackground(PointCollection... params) {
            if (params.length == 0) {
                return null;
            }
            PointCollection pointCollection = params[0];

            String fileName = String.format("pointcloud-%s.txt", roomName);

            File f = new File(context.getCacheDir() + File.separator);
            if (!f.exists()) {
                f.mkdirs();
                Log.d("created", "yeah");
            }
            final File file = new File(f, fileName);
            filePath = file.getPath();

            Log.d("fPath", filePath);

            try {

                File cacheFile = new File(context.getCacheDir() + File.separator + fileName);
                cacheFile.createNewFile();

                FileOutputStream fos = new FileOutputStream(cacheFile);
                OutputStreamWriter osw = new OutputStreamWriter(fos, "UTF8");
                PrintWriter pw = new PrintWriter(osw);

                int size = pointCollection.getCount();
                FloatBuffer floatBuffer = pointCollection.getBuffer();
                floatBuffer.rewind();

                for (int i = 0; i < size; i++) {
                    String row = String.valueOf(floatBuffer.get()) + " " + String.valueOf(floatBuffer.get()) + " " + String.valueOf(floatBuffer.get()) + "\n";
                    pw.println(row);
                }

                pw.flush();
                pw.close();
            } catch (IOException e) {
                Log.e("Creation", "File not creates");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            Various.makeToast(context,"Point cloud exported!");
        }
    }
}
