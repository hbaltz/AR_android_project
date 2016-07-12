package com.example.hbaltz.aton;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import org.apache.commons.io.FileUtils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

/**
 * Created by hbaltz on 7/12/2016.
 */
public class TestWriteFile extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        File test = new File(getFilesDir(),"Yep.txt");
        createFile(test);
        addInfoToFile(test,"Pump");
        addInfoToFile(test,"Plomp");

        Log.d("Works?", readFromFile("Yep.txt"));
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

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
