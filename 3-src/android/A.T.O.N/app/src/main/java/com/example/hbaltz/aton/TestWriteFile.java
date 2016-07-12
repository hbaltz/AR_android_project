package com.example.hbaltz.aton;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;

import org.apache.commons.io.FileUtils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 * Created by hbaltz on 7/12/2016.
 */
public class TestWriteFile extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("dir", "" + getFilesDir());

        BufferedWriter bufferedWriter = null;
        try {
            bufferedWriter = new BufferedWriter(new FileWriter(new
                    File(getFilesDir()+File.separator+"MyFile.txt")));
            bufferedWriter.write("lalit poptani");
            bufferedWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
