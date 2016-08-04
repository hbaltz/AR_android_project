package com.example.hbaltz.aton.utilities;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Arrays;

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

                if (flSplt.contains(".xyz")) {
                    room = flSplt.split("\\.")[0];
                    nameRooms.add(room);
                }

            }
        }
        return nameRooms;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Converts an arrayList of string in an a table of charSequence
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
     * Reads the information in the file and create a FloatBuffer
     *
     * @param context: the activity's context
     * @param file:    the file that we want to read
     * @return a FloatBuffer that contains all the information in the file
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
     * Counts the number of lines in a file
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

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Finds the y max on a FloatBuffer of x,y,z
     *
     * @param fb: the FloatBuffer which contains xyz coordinates of points
     * @param sizeFB: the number of the triplet xyz
     * @return the y maximum
     */
    public static float findYMax(FloatBuffer fb, int sizeFB){
        // Initialize:
        float Ymax = 0f;
        float y;

        // We replace the pointer on the begin of the floatBuffer:
        fb.rewind();

        for(int i = 0; i < sizeFB; i++){
            // Recover only the y:
            fb.get();
            y = fb.get();
            fb.get();

            // If y is superior to yMax, y becomes Ymax
            if(y > Ymax){
                Ymax = y;
            }
        }

        return Ymax;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Finds the y min on a FloatBuffer of x,y,z
     *
     * @param fb: the FloatBuffer which contains xyz coordinates of points
     * @param sizeFB: the number of the triplet xyz
     * @return the y min
     */
    public static float findYMin(FloatBuffer fb, int sizeFB){
        // Initialize:
        float yMin = Float.MAX_VALUE;
        float y;

        // We replace the pointer on the begin of the floatBuffer:
        fb.rewind();

        for(int i = 0; i < sizeFB; i++){
            // Recover only the y:
            fb.get();
            y = fb.get();
            fb.get();

            // If y is superior to yMax, y becomes Ymax
            if(y < yMin){
                yMin = y;
            }
        }

        return yMin;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Detects the ceiling on a point cloud of a room
     *
     * @param fb: the FloatBuffer which contains xyz coordinates of points
     * @param sizeFB: the number of the triplet xyz
     * @param accuracy: the accuracy to be consider like a point of the ceiling
     * @return an arraylist of triplet xyz (the point detected like the ceiling)
     */
    public static ArrayList<float[]> detectCelling(FloatBuffer fb, int sizeFB, float accuracy){
        // Initialize:
        ArrayList<float[]> ceiling = new ArrayList<float[]>();
        float x,y,z;
        float[] pt;

        // We recover the yMax:
        float yMax = findYMax(fb,sizeFB);
        Log.d("yMax",""+yMax);

        // We replace the pointer on the begin of the floatBuffer:
        fb.rewind();

        for(int i = 0; i < sizeFB; i++){
            x = fb.get();
            y = fb.get();
            z = fb.get();

            // If the y is close to the yMax we add it to the ceiling:
            if ((yMax - y) <= accuracy) {
                pt = new float[3];
                pt[0] = x;
                pt[1] = y;
                pt[2] = z;

                ceiling.add(pt);
            }
        }

        return ceiling;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Find the y median of on array of XYZ coordinates
     *
     * @param cloudPoint: the Arraylist of XYZ coordinates
     * @return the median y
     */
    public static float findYMedian(ArrayList<float[]> cloudPoint){
        int sizeCP = cloudPoint.size();
        float[] arrayY = new float[sizeCP];

        for(int i =0; i<sizeCP;i++){
            arrayY[i] = cloudPoint.get(i)[1];
        }

        Arrays.sort(arrayY);

        float median;
        int lenArr = arrayY.length;

        if (lenArr % 2 == 0)
            median = (arrayY[lenArr/2] + arrayY[lenArr/2 - 1])/2;
        else
            median =  arrayY[lenArr/2];

        return median;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Converts an arraylist pf laot in a floatbuffer
     *
     * @param arrayList: an arraylist
     * @return a floatbuffer
     */
    public static FloatBuffer ArrayList2FloatBuffer(ArrayList<float[]> arrayList){
        int sizeAL = arrayList.size();
        FloatBuffer floatbuffer = ByteBuffer.allocateDirect(12*sizeAL).asFloatBuffer();

        for(int i = 0; i<sizeAL; i++){
            floatbuffer.put(arrayList.get(i));
        }

        return floatbuffer;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Creates a file on the internal memory
     *
     * @param context: the context
     * @param roomName: the name of the room
     * @param floatBuffer: the float buffer
     * @param size: the size of the float buffer
     */
    public static void createFile(Context context, String roomName, FloatBuffer floatBuffer, int size){

        String filePath;

        String fileName = String.format("pointcloud-%s.xyz", roomName);

        File f = new File(context.getCacheDir() + File.separator);
        if (!f.exists()) {
            f.mkdirs();
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

            floatBuffer.rewind();

            for (int i = 0; i < size; i++) {
                String row = String.valueOf(floatBuffer.get()) + " " + String.valueOf(floatBuffer.get()) + " " + String.valueOf(floatBuffer.get());
                pw.println(row);
            }

            pw.flush();
            pw.close();
        } catch (IOException e) {
            Log.e("Creation", "File not creates");
        }
    }


}
