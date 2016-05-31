package com.example.hbaltz.sub;

import android.Manifest;
import android.content.Context;
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
import com.esri.core.geometry.Proximity2DResult;
import com.esri.core.geometry.SpatialReference;
import com.esri.core.geometry.Unit;
import com.esri.core.map.Feature;

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
    private Point user = new Point(-8425348,5688505); // By default : 70 Laurier Street, Ottawa

    ////////////////////////////////////// Compass: ////////////////////////////////////////////////
    private SensorManager mSensorManager;
    private Sensor mSensor;

    //////////////////////////////////// Spatial reference: ////////////////////////////////////////
    private SpatialReference WGS_1984_WMAS = SpatialReference.create(102100);

    //////////////////////////////////// Features: /////////////////////////////////////////////////
    private Feature[] features_footprints;

    //////////////////////////////////// Geometries: ///////////////////////////////////////////////
    private Geometry[] geom_footprints;
    private Geometry all_geom_footprints;

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

        /////////////////////////////// Database: //////////////////////////////////////////////////
        accessDb();

        /////////////////////////////// Full screen: ///////////////////////////////////////////////
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        winParams.flags |= 0x80000000;
        win.setAttributes(winParams);

        setContentView(R.layout.activity_main);

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
            user.setXY(location.getLatitude(), location.getLongitude());

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


            /////////////////////////////////// Recover  geometries: ///////////////////////////////
            // Initialize:
            int len0 = features_footprints.length - 1;
            geom_footprints = new Geometry[len0 + 1];

            Geometry acme = new Polygon(); // useful if no object in the db
            for (int k = 0; k < len0; k++) {

                Feature Footprint = features_footprints[k];
                // Recover geometries :
                if (Footprint != null) {
                    geom_footprints[k] = Footprint.getGeometry();
                } else {
                    geom_footprints[k] = acme;
                }
            }

            //////////////////////////////////// Union of geometries: //////////////////////////////
            all_geom_footprints = unionGeoms(geom_footprints, WGS_1984_WMAS);

            ArrayList<Geometry> NN = nearestNeighbors(user, geom_footprints,WGS_1984_WMAS,200,meter);
            double[] distances = distancePointToGeoms(user, NN, WGS_1984_WMAS);

            Log.d("NN200",""+NN.size());
            Log.d("distances", ""+distances.length);
            Log.d("distance1", ""+distances[1]);

        } catch (Exception e) {
            popToast("Error while initializing :" + e.getMessage(), true);
            e.printStackTrace();
        }

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Function which make the union of an array of geometries regardless of his size
     *
     * @param geoms:  the array of geometries
     * @param SpaRef: the spatial reference of these geometries
     * @return union: a geometry resulting from the union
     */
    private Geometry unionGeoms(Geometry[] geoms, SpatialReference SpaRef) {
        // Initialize
        Geometry union = null;
        int len_geoms = geoms.length;

        int len_lim = 500; // Limit length of the geometry array (limit for the union)

        // If the array have a length below the limit we can do the union,
        // else we have to split the array in several parts and do the union in several times
        if (len_geoms < len_lim) {
            union = geomen.union(geoms, SpaRef);
        } else {
            // Initialize:
            Geometry[] array_union = new Geometry[2]; // temporary array for the union
            Geometry[] geomTemp = new Geometry[len_lim];
            Geometry[] geomRem = new Geometry[len_geoms - len_lim]; // The remaining geometries
            int k = 1; // useful for the while loop

            // We split geometries in two for the union:
            int len_temp = geomTemp.length;
            int len_rem = geomRem.length;
            System.arraycopy(geoms, 0, geomTemp, 0, len_temp);
            System.arraycopy(geoms, len_temp, geomRem, 0, len_rem);

            if (DEBUG) {Log.d("len_geom_temp", "" + geomTemp.length);}
            if (DEBUG) {Log.d("len_geom_rem", "" + geomRem.length);}

            // We do the union of the array with a length below the limit
            array_union[0] = geomen.union(geomTemp, SpaRef);

            // While the length of the remaining geometries is not below the limit
            // we split the array and we do several union to never exceed the limit
            while (len_rem > len_lim) {
                // We split the array in two, we use geomTemp:
                System.arraycopy(geomRem, 0, geomTemp, 0, len_temp);
                k = k + 1;

                // geomRem recover the remaining geometries:
                geomRem = new Geometry[len_rem - len_lim];
                len_rem = geomRem.length;
                System.arraycopy(geoms, k * len_temp, geomRem, 0, len_rem);

                // We do the union of the geometries we have stock in geomTemp
                // and the union between the two geometries resulting from the unions
                array_union[1] = geomen.union(geomTemp, SpaRef);
                array_union[0] = geomen.union(array_union, SpaRef);
            }

            // We do the union of the remaining geometries
            // and the union between the two geometries resulting from the unions
            array_union[1] = geomen.union(geomRem, SpaRef);
            union = geomen.union(array_union, SpaRef);
        }

        return union;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Function which find the geometries below a distance of radius unit from the point
     *
     * @param point : The departure point
     * @param geoms : The array of geometries
     * @param spaRef : the spatial reference
     * @param radius : The distance to consider a geometries like a NN (Nearest Neighbor)
     * @param unit : The distance's unit
     * @return an ArrayList of geometries which qre the nearest geometries to the point
     */
    private ArrayList<Geometry> nearestNeighbors(Point point, Geometry[] geoms, SpatialReference spaRef,
                                      double radius, Unit unit){

        ArrayList<Geometry> NN = new ArrayList<>();
        int len_geoms = geoms.length;

        Geometry buffer = geomen.buffer(point, spaRef, radius, unit);

        for (int i=0; i<len_geoms; i++){
            if(geoms[i]!=null) {
                if (geomen.intersects(buffer, geoms[i], spaRef)) {
                    NN.add(geoms[i]);
                }
            }
        }
        return NN;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Function which calculate the distance between a point and all the geometries in an array
     *
     * @param point : The departure point
     * @param geoms : The arrayList of geometries
     * @param spaRef : The spatial reference
     * @return an array of double which are the distance between the point and the geometries
     */
    private double[] distancePointToGeoms(Point point, ArrayList<Geometry> geoms, SpatialReference spaRef){
        int len_geoms = geoms.size();
        double[] distances = new double[len_geoms];

        for (int i=0; i<len_geoms; i++){
            distances[i] = geomen.distance(point, geoms.get(i), spaRef);
        }

        return distances;
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
