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
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.esri.core.geodatabase.Geodatabase;
import com.esri.core.geodatabase.GeodatabaseFeatureTable;
import com.esri.core.geometry.Geometry;
import com.esri.core.geometry.GeometryEngine;
import com.esri.core.geometry.LinearUnit;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.Polygon;
import com.esri.core.geometry.SpatialReference;
import com.esri.core.geometry.Unit;
import com.esri.core.map.Feature;
import com.example.hbaltz.sub.Class.Building;
import com.example.hbaltz.sub.Class.User;

import java.util.ArrayList;

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

    //////////////////////////////////// Features: /////////////////////////////////////////////////
    private Feature[] features_footprints;

    //////////////////////////////////// Buildings: ///////////////////////////////////////////////
    private Building[] buildings;

    //////////////////////////////////// Geometrie Engine: /////////////////////////////////////////
    private GeometryEngine geomen;

    //////////////////////////////////// Unit: /////////////////////////////////////////////////////
    Unit meter = Unit.create(LinearUnit.Code.METER);

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

        ////////////////////////////////////// Listeners: //////////////////////////////////////////
        setupListeners();

        /////////////////////////////// Database: //////////////////////////////////////////////////
        accessDb();
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

    /**
     * Listener for the compass
     */
    private final SensorEventListener mListener = new SensorEventListener() {
        public void onSensorChanged(SensorEvent event) {

            //Log.d("Rotation Vector", "sensorChanged (" + event.values[0] + ", " + event.values[1] + ", " + event.values[2] + ")");

            // Redraw its canvas every time the compass reports a change
            // TODO : check to see if it has moved more than a degree or something similar
            /*if (mDrawView != null) {
                mDrawView.setOffset(event.values[0]);
                mDrawView.invalidate();
            }
            */
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
    };

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// FUNCTIONS: ////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Function which access to the db and collect all the information that we need
     */
    public void accessDb() {
        // Get the external directory

        //String locatorPath = chDb + "/MGRS.loc";
        String networkPath = chDb + "/footprintsuo.geodatabase";

        if(DEBUG){Log.d("extern", extern);}

        try {
            //////////////////////////////////// Open  db: /////////////////////////////////////////
            // open a local geodatabase
            Geodatabase gdb = new Geodatabase(extern + networkPath);

            if (DEBUG) {
                Log.d("GbdTbs", "" + gdb.getGeodatabaseTables());
            }

            //////////////////////////////////// Recover features from  db: ////////////////////////
            GeodatabaseFeatureTable footprints = gdb.getGeodatabaseTables().get(0);

            long nbr_lignes = footprints.getNumberOfFeatures();
            int nbr_int = (int) nbr_lignes;
            features_footprints = new Feature[nbr_int];
            for (int l = 1; l <= nbr_lignes; l++) {
                if (footprints.checkFeatureExists(l)) {
                    features_footprints[l - 1] = footprints.getFeature(l);
                } else {
                    features_footprints[l - 1] = null;
                }
            }

            if (DEBUG) {Log.d("len_ff", "" + features_footprints.length);}


            /////////////////////////////////// Recover buildings: /////////////////////////////////
            // Initialize:
            int len0 = features_footprints.length - 1;
            buildings = new Building[len0 + 1];

            Geometry acme = new Polygon(); // useful if no object in the db
            Building acBul = new Building();
            for (int k = 0; k < len0; k++) {

                Feature Footprint = features_footprints[k];
                Building buildTemp = new Building();

                // Recover informations about buildings :
                if (Footprint != null) {
                    buildTemp.setFootprint(Footprint.getGeometry());
                    buildTemp.setName((String) Footprint.getAttributeValue("Name"));
                    buildTemp.setDescription((String) Footprint.getAttributeValue("Type"));
                } else {
                    buildTemp = acBul;
                }
                buildings[k]=buildTemp;
            }

            Log.d("buildings","" + buildings.length);

            ArrayList<Building> NN = user.nearestNeighbors(geomen, buildings,WGS_1984_WMAS,200,meter);
            Log.d("NN200",""+NN.size());

            double[] distances = user.distanceToBuilds(geomen, NN, WGS_1984_WMAS);
            Log.d("distances", ""+distances.length);
            Log.d("distance1", ""+distances[1]);

        } catch (Exception e) {
            popToast("Error while initializing :" + e.getMessage(), true);
            e.printStackTrace();
        }

    }
    
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Function which setup the listeners
     */
    private void setupListeners(){

        ////////////////////////////////////// GPS: ////////////////////////////////////////////////
        locMgr = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
}
