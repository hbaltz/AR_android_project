package com.example.hbaltz.aton.utilities;


import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.Toast;

import com.example.hbaltz.aton.AtonActivity;
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
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;


public class PointCloudExporter extends Activity{
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
            File f = new File(getFilesDir() , fileName);

            boolean created = createFile(f);

            if(created) {

                int size = pointCollection.getCount();
                FloatBuffer floatBuffer = pointCollection.getBuffer();
                floatBuffer.rewind();
                int progressCounter = 0;

                for (int i = 0; i < size; i++) {
                    String row = String.valueOf(floatBuffer.get()) + " " + String.valueOf(floatBuffer.get()) + " " + String.valueOf(floatBuffer.get()) + "\n";

                    addInfoToFile(f,row);

                    progressCounter++;
                    if (progressCounter % (int) ((double) size / 200.0) == 0) {
                        publishProgress(progressCounter);
                    }
                }

            } else {
                Log.e("Creation", "File not creates");
            }

            return null;

        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Creates a file
     *
     * @param file: the file that we want to create
     * @return true if the file has been created, false if not
     */
    private boolean createFile(File file){
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return true;
        }

        return false;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Adds the string info to the File file
     *
     * @param file: the file that we want to modify
     * @param info: the string that we want ot add to the file
     * @return true if the file has been modified, false if not
     */
    private boolean addInfoToFile(File file, String info){

        FileOutputStream writer = null;
        try {
            writer = openFileOutput(file.getName(), Context.MODE_APPEND);

            Log.d("fle", file.toString());

            Log.d("content",writer.toString());

            writer.write(info.getBytes());
            writer.flush();
            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Adds the list of strings data to the File file
     *
     * @param file: the file that we want to modify
     * @param data: the list of strings that we want ot add to the file
     * @return true if the file has been modified, false if not
     */
    private boolean addDataToFile(File file, ArrayList<String> data){

        FileOutputStream writer = null;
        try {
            writer = openFileOutput(file.getName(), Context.MODE_APPEND);

            Log.d("fle", file.toString());

            Log.d("content",writer.toString());

            for (String string: data){
                writer.write(string.getBytes());
                writer.flush();
            }

            writer.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
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
            InputStream inputStream = openFileInput(file);

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
