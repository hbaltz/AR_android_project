package com.example.hbaltz.sub;

import android.Manifest;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.esri.core.geodatabase.Geodatabase;
import com.esri.core.geodatabase.GeodatabaseFeatureTable;
import com.esri.core.geometry.GeometryEngine;
import com.esri.core.geometry.LinearUnit;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.SpatialReference;
import com.esri.core.geometry.Unit;
import com.esri.core.map.Feature;
import com.example.hbaltz.sub.Class.BuildingPOI;
import com.example.hbaltz.sub.Class.User;
import com.example.hbaltz.sub.Class.Utilities;
import com.example.hbaltz.sub.View.DrawSurfaceView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// VARIABLES: ////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////// ArcGIS Elements : /////////////////////////////////////////
    private final String extern = Environment.getExternalStorageDirectory().getPath();
    //private final String extern = "/storage/sdcard1";

    // TODO : auto-detect the chDb

    // With Sd card :
    //private final String chDb = "/sub";

    // Without sd card :
    private final String chDb = "/Android/data/com.example.hbaltz.sub/sub";

    ////////////////////////////////////// GPS: ////////////////////////////////////////////////////
    private LocationManager locMgr;
    private Point locUser = new Point(-8425348,5688505); // By default : 70 Laurier Street, Ottawa
    private User user = new User(locUser);

    ////////////////////////////////////// Compass: ////////////////////////////////////////////////
    private SensorManager mSensorManager;
    private Sensor mSensor;

    //////////////////////////////////// Spatial reference: ////////////////////////////////////////
    private SpatialReference WGS_1984_WMAS = SpatialReference.create(102100);

    //////////////////////////////////// Buildings: ///////////////////////////////////////////////
    private BuildingPOI[] buildings;
    private  ArrayList<BuildingPOI> NN;
    ArrayList<Double> distances;

    //////////////////////////////////// Geometrie Engine: /////////////////////////////////////////
    private GeometryEngine geomen;

    //////////////////////////////////// Unit: /////////////////////////////////////////////////////
    private Unit meter = Unit.create(LinearUnit.Code.METER);

    //////////////////////////////////// Azimuth: //////////////////////////////////////////////////
    private double azimuthReal = 0;
    private static double AZIMUTH_ACCURACY = 60; // 120 degrees is the human visual field

    /////////////////////////////////// Views: /////////////////////////////////////////////////////
    private ImageView pointerIcon;
    private DrawSurfaceView DrawView;

    //////////////////////////////////// Debug: ////////////////////////////////////////////////////
    private final boolean DEBUG = true;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// METHODS: //////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /////////////////////////////// Full screen: ///////////////////////////////////////////////
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        winParams.flags |= 0x80000000;
        win.setAttributes(winParams);

        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        ////////////////////////////////////// Views: //////////////////////////////////////////////
        DrawView = (DrawSurfaceView) findViewById(R.id.drawSurfaceView);

        /////////////////////////////// Listeners: /////////////////////////////////////////////////
        setupListeners();

        /////////////////////////////// Database: //////////////////////////////////////////////////
        accessDb();

        ////////////////////////////// Nearest Neighbors: //////////////////////////////////////////
        updateNN();
    }

    @Override
    protected void onResume() {
        if (DEBUG){Log.d("onResume", "Ok");}
        super.onResume();

        mSensorManager.registerListener(mListener, mSensor, SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onStop() {
        if (DEBUG) {Log.d("onStop", "Ok");}

        mSensorManager.unregisterListener(mListener);
        super.onStop();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// FUNCTIONS: ////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Function which setups the listeners
     */
    private void setupListeners(){

        ////////////////////////////////////// GPS: ////////////////////////////////////////////////
        locMgr = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        // Define which provider the application will use regarding which one is available
        if (locMgr.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            locMgr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0,  new GpsListener());
        } else {
            locMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, new GpsListener());
        }

        ////////////////////////////////////// Compass: ////////////////////////////////////////////
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Function which access to the db and collect all the information that we need
     */
    public void accessDb() {
        // Get the external directory
        String networkPath = chDb + "/poiuo.geodatabase";

        if(DEBUG){Log.d("extern", extern);}

        try {
            //////////////////////////////////// Open  db: /////////////////////////////////////////
            // open a local geodatabase
            Geodatabase gdb = new Geodatabase(extern + networkPath);

            if (DEBUG) {Log.d("GbdTbs", "" + gdb.getGeodatabaseTables());}

            //////////////////////////////////// Recover features from  db: ////////////////////////
            GeodatabaseFeatureTable pois = gdb.getGeodatabaseTables().get(0);

            long nbr_lignes = pois.getNumberOfFeatures();
            int nbr_int = (int) nbr_lignes;

            Feature[] features_pois = new Feature[nbr_int];

            for (int l = 1; l <= nbr_lignes; l++) {
                if (pois.checkFeatureExists(l)) {
                    features_pois[l - 1] = pois.getFeature(l);
                } else {
                    features_pois[l - 1] = null;
                }
            }

            if (DEBUG) {Log.d("len_ff", "" + features_pois.length);}

            /////////////////////////////////// Recover buildings: /////////////////////////////////
            // Initialize:
            int len0 = features_pois.length - 1;
            buildings = new BuildingPOI[len0 + 1];

            BuildingPOI acBul = new BuildingPOI(); // useful if no object in the db

            for (int k = 0; k < len0; k++) {

                Feature Footprint = features_pois[k];
                BuildingPOI buildTemp = new BuildingPOI();

                // Recover information about buildings :
                if (Footprint != null) {

                    double lon = (double) Footprint.getAttributeValue("Longitude");
                    double lat = (double) Footprint.getAttributeValue("Latitude");
                    Point loc = new Point(lon,lat);

                    buildTemp.setLocation(loc);
                    buildTemp.setName((String) Footprint.getAttributeValue("Name"));
                    buildTemp.setDescription((String) Footprint.getAttributeValue("Type"));
                } else {
                    buildTemp = acBul;
                }
                buildings[k]=buildTemp;
            }

            Log.d("buildings","" + buildings.length);

        } catch (Exception e) {
            popToast("Error while initializing :" + e.getMessage(), true);
            e.printStackTrace();
        }

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Function which launches all the calculations to update the view
     */
    private void updateView(){
        if(NN!=null) {
            ArrayList<Double> azTheos = user.theoreticalAzimuthToPOIs(NN);
            if (DEBUG) {Log.d("azTeo", "" + azTheos);}

            ArrayList<Boolean> visible = Utilities.isAzimuthsVisible(azTheos, azimuthReal, AZIMUTH_ACCURACY);
            if (DEBUG) {Log.d("visible", "" + visible);}

            if (DrawView !=null) {
                DrawView.setVariables(NN, distances, azTheos, azimuthReal, visible);
                DrawView.invalidate();
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Function which launches the calculations of the nearest neighbors
     * and the distances between them and the user
     */
    private void updateNN(){
        NN = user.nearestNeighbors(geomen, buildings,WGS_1984_WMAS,200,meter);
        if(DEBUG){Log.d("NN200",""+NN.size());}
        if(NN!=null) {
            distances = user.distanceToBuilds(geomen, NN, WGS_1984_WMAS);
            if (DEBUG) {
                Log.d("distances", "" + distances);
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private void popToast(final String message, final boolean show) {
        // Simple helper method for showing toast on the main thread
        if (!show)
            return;

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
            }
        });
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// LISTENERS : ///////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Listener for the location
     */
    class GpsListener implements LocationListener{
        @Override
        public void onLocationChanged(Location location) {
            user.setLocation(new Point(location.getLatitude(), location.getLongitude()));

            popToast("lat : " + location.getLatitude() + ", long : " + location.getLongitude()
                    + ", bearing : " + location.getBearing(), true);
            Log.d("loc", "lat : " + location.getLatitude() + ", long : " + location.getLongitude());
        }

        @Override
        public void onStatusChanged(String s, int i, Bundle bundle) {
            popToast("GPS status changed", true);
        }

        @Override
        public void onProviderEnabled(String s) {
            popToast("GPS enabled", true);
        }

        @Override
        public void onProviderDisabled(String s) {
            popToast("GPS disabled", true);
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Listener for the compass
     */
    private final SensorEventListener mListener = new SensorEventListener() {


        float[] mRotationMatrix = new float[9];
        float[] orientationVals = new float[3];

        @Override
        public void onSensorChanged(SensorEvent event) {

            if (event.sensor.getType() == Sensor.TYPE_ROTATION_VECTOR) {
                // Convert the rotation-vector to a 4x4 matrix.
                SensorManager.getRotationMatrixFromVector(mRotationMatrix,
                        event.values);
                SensorManager
                        .remapCoordinateSystem(mRotationMatrix,
                                SensorManager.AXIS_X, SensorManager.AXIS_Z,
                                mRotationMatrix); // Screen orientation Landscape
                SensorManager.getOrientation(mRotationMatrix, orientationVals);

                // Optionally convert the result from radians to degrees
                orientationVals[0] = (float) Math.toDegrees(orientationVals[0]);
                orientationVals[1] = (float) Math.toDegrees(orientationVals[1]);
                orientationVals[2] = (float) Math.toDegrees(orientationVals[2]);

                azimuthReal = (orientationVals[0]+360)%360;

                updateView();
            }
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
    };
}
