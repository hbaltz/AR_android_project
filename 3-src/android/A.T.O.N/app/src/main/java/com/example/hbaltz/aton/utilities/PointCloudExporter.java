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
import java.nio.FloatBuffer;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


public class PointCloudExporter {
    private final Context context;
    private final PointCollection pointCollection;
    private String filePath;

    public PointCloudExporter(Context context, PointCollection pointCollection) {
        this.context = context;
        this.pointCollection = pointCollection;

    }


    public void export() {
        new ExportAsyncTask().execute(pointCollection);
    }

    private class ExportAsyncTask extends AsyncTask<PointCollection, Integer, Void> {

        @Override
        protected Void doInBackground(PointCollection... params) {
            if (params.length == 0) {
                return null;
            }
            PointCollection pointCollection = params[0];
            Format formatter = new SimpleDateFormat("yyyy-MM-dd_HH-mm", Locale.FRENCH);

            String fileName = "pointcloud-" + formatter.format(new Date()) + ".xyz";
            File f = new File(context.getFilesDir() + "");
            if (!f.exists()) {
                f.mkdirs();
                Log.d("created", "yeah");
            }
            final File file = new File(f, fileName);
            filePath = file.getPath();

            Log.d("fPath", filePath);
            try {
                OutputStream os = new FileOutputStream(file);
                int size = pointCollection.getCount();
                FloatBuffer floatBuffer = pointCollection.getBuffer();
                floatBuffer.rewind();
                int progressCounter = 0;
                for (int i = 0; i < size; i++) {
                    String row = String.valueOf(floatBuffer.get()) + " " + String.valueOf(floatBuffer.get()) + " " + String.valueOf(floatBuffer.get()) + "\n";
                    os.write(row.getBytes());
                    progressCounter++;
                    if (progressCounter % (int) ((double) size / 200.0) == 0) {
                        publishProgress(progressCounter);
                    }
                }
                os.close();

                Log.d("Works?",""+ readFromFile(fileName).length());

            } catch (IOException e) {
                Log.e("Creation", "File not creates");
            }
            return null;
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Read the information in the file
     *
     * @param file: the file that we want to read
     * @return a string that contains all the information int the file
     */
    private String readFromFile(String file) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput(file);

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }
}
