package com.example.hbaltz.aton.utilities;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by hbaltz on 7/20/2016.
 */
public class Various {

    /**
     * Displays on the screen the CharSequence text
     *
     * @param context: The context of the app
     * @param text: the text that we want to display
     */
    public static void makeToast(Context context, CharSequence text) {
        int duration = Toast.LENGTH_LONG;

        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     *
     * Lists the rooms' names in memory
     *
     * @param context: the activity context
     * @return the rooms' names in memory
     */
    public static ArrayList<String> recoverListOfFiles(Context context){
        ArrayList<String> nameRooms = new ArrayList<String>();

        String[] fileSplit;
        String flSplt, room;

        for (String file:context.getFilesDir().list()) {
            fileSplit = file.split("-");

            if(fileSplit.length!=0){
                flSplt =  fileSplit[1];

                if(flSplt.contains(".xyz")){
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
    public static CharSequence[] ArrayList2CharSeq(ArrayList<String> List){
        int lenLs = List.size();

        CharSequence[] charSeq = new CharSequence[lenLs];

        ArrayList<String> nameRooms = new ArrayList<String>();

        for (int i=0; i<lenLs; i++) {
            charSeq[i] = List.get(i);
        }

         return charSeq;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Read the information in the file
     *
     * @param context: the activity's context
     * @param file: the file that we want to read
     * @return a string that contains all the information int the file
     */
    public static String readFromFile(Context context,String file) {

        String ret = "";

        try {
            InputStream inputStream = context.openFileInput(file);

            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ((receiveString = bufferedReader.readLine()) != null) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        } catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }
        return ret;
    }
}
