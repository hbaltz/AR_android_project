package com.example.hbaltz.aton.utilities;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.example.hbaltz.aton.renderer.PointCollection;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.ArrayList;

/**
 * Created by hbaltz on 7/20/2016.
 */
public class Various {

    /**
     * Displays on the screen the CharSequence text
     *
     * @param context: The context of the app
     * @param text:    the text that we want to display
     */
    public static void makeToast(Context context, CharSequence text) {
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Lists the rooms' names in memory
     *
     * @param context: the activity context
     * @return the rooms' names in memory
     */
    public static ArrayList<String> recoverListOfFiles(Context context) {
        ArrayList<String> nameRooms = new ArrayList<String>();

        String[] fileSplit;
        String flSplt, room;

        for (String file : context.getCacheDir().list()) {
            fileSplit = file.split("-");

            if (fileSplit.length >= 2) {
                flSplt = fileSplit[1];

                if (flSplt.contains(".txt")) {
                    room = flSplt.split("\\.")[0];
                    nameRooms.add(room);
                }

            }
        }
        return nameRooms;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * converts an arrayList of string in an a table of charSequence
     *
     * @param List: the arrayList that we want to convert
     * @return a table of charSequence
     */
    public static CharSequence[] ArrayList2CharSeq(ArrayList<String> List) {
        int lenLs = List.size();

        CharSequence[] charSeq = new CharSequence[lenLs];

        ArrayList<String> nameRooms = new ArrayList<String>();

        for (int i = 0; i < lenLs; i++) {
            charSeq[i] = List.get(i);
        }

        return charSeq;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Read the information in the file
     *
     * @param context: the activity's context
     * @param file:    the file that we want to read
     * @return a string that contains all the information int the file
     */
    public static FloatBuffer readFromFile(Context context, String file) {
        int size = 0;

        try {
            size = countLines(context, file);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.d("nbLines", "" + size);

        FloatBuffer openedFB = ByteBuffer.allocateDirect(12*size).asFloatBuffer(); // 12*size because in documentation 4*size and we have 3 pieces of inforamtion by line.

        Log.d("CpaFB","" + openedFB.capacity());

        try {
            FileInputStream fis = new FileInputStream(new File(context.getCacheDir(), file));

            InputStreamReader inputStreamReader = new InputStreamReader(fis);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String receiveString = "";
            String[] strngSplt;
            float[] xyz;

            while ((receiveString = bufferedReader.readLine()) != null) {
                strngSplt = new String[3];
                strngSplt = receiveString.split(" ");

                xyz = new float[3];
                xyz[0] = Float.parseFloat(strngSplt[0]);
                xyz[1] = Float.parseFloat(strngSplt[1]);
                xyz[2] = Float.parseFloat(strngSplt[2]);

                openedFB.put(xyz);
            }

            fis.close();

        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }


        return openedFB;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * counts the number of lines in a file
     *
     * @param context: the context
     * @param fileName: the file's name
     * @return an integer: the number of lines in a file
     * @throws IOException
     */
    public static int countLines(Context context, String fileName) throws IOException {
        int count = 0;
        FileInputStream fis = new FileInputStream(new File(context.getCacheDir(), fileName));
        InputStreamReader inputStreamReader = new InputStreamReader(fis);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String receiveString = "";

        while ((receiveString = bufferedReader.readLine()) != null) {
           count++;
        }

        return count;
    }


}
