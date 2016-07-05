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
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import android.widget.CheckBox;
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
import com.example.hbaltz.sub.Class.BuildingPOI;
import com.example.hbaltz.sub.Class.GeoInfo;
import com.example.hbaltz.sub.Class.User;
import com.example.hbaltz.sub.View.DrawSurfaceView;
import com.example.hbaltz.sub.View.FtDrawSurfaceView;
import com.example.hbaltz.sub.View.GeoDrawSurfaceView;
import com.example.hbaltz.sub.View.uoMapView;

import org.codehaus.jackson.JsonParser;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// VARIABLES: ////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////// ArcGIS Elements : /////////////////////////////////////////
    private final String extern = "/storage/sdcard1";
    private final String chDb = "/sub";

    ////////////////////////////////////// GPS: ////////////////////////////////////////////////////
    private LocationManager locMgr;
    private Point locUser = new Point(-8425218.888, 5688332.101);
    private User user = new User(locUser);

    ////////////////////////////////////// Compass: ////////////////////////////////////////////////
    private SensorManager mSensorManager;
    private Sensor mSensor;

    //////////////////////////////////// Spatial reference: ////////////////////////////////////////
    private SpatialReference WGS_1984_WMAS = SpatialReference.create(102100);

    //////////////////////////////////// Buildings: ///////////////////////////////////////////////
    private BuildingPOI[] buildings;
    private Polygon[] PoiFootprints;
    private GeoInfo[] InfoGeos;
    private ArrayList<BuildingPOI> NN;
    private ArrayList<GeoInfo> simpGeoInfos;

    //////////////////////////////////// Geometrie Engine: /////////////////////////////////////////
    private GeometryEngine geomen;

    //////////////////////////////////// Unit: /////////////////////////////////////////////////////
    private Unit meter = Unit.create(LinearUnit.Code.METER);

    //////////////////////////////////// Angles: ////////////////////////////////////////////////////
    private double azimuthReal = 0d, pitchReal=0d;
    private static double AZIMUTH_ACCURACY = 30d, PITCH_ACCURACY = 60d;
    float[] orientationVals = new float[3];

    /////////////////////////////////// Views: /////////////////////////////////////////////////////
    private DrawSurfaceView DrawView;
    private uoMapView uoMap;
    private FtDrawSurfaceView FtDrawView;
    private GeoDrawSurfaceView GeoDrawView;

    ////////////////////////////////////// Checkbox: ///////////////////////////////////////////////
    private CheckBox checkPoi, checkFt, checkGeo;

    //////////////////////////////////// Display: //////////////////////////////////////////////////
    private boolean displayPoi = true;
    private boolean displayFootprint = false;
    private boolean displayGeoInfo = false;

    //////////////////////////////////// Debug: ////////////////////////////////////////////////////
    private final boolean DEBUG = false;

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
        FtDrawView = (FtDrawSurfaceView) findViewById(R.id.ftDrawView);
        GeoDrawView = (GeoDrawSurfaceView) findViewById(R.id.geoDrawView);
        uoMap = (uoMapView) findViewById(R.id.uoMap);

        /////////////////////////////// Checkbox: //////////////////////////////////////////////////
        checkPoi = (CheckBox) findViewById(R.id.checkPoi);
        String txtPoi = getResources().getString(R.string.dispPoi);
        checkPoi.setText(txtPoi);

        checkFt = (CheckBox) findViewById(R.id.checkFootprint);
        String txtFt = getResources().getString(R.string.dispFt);
        checkFt.setText(txtFt);

        checkGeo = (CheckBox) findViewById(R.id.checkGeo);
        String txtGeo = getResources().getString(R.string.dispGeo);
        checkGeo.setText(txtGeo);

        /////////////////////////////// Listeners: /////////////////////////////////////////////////
        setupListeners();

        /////////////////////////////// Database: //////////////////////////////////////////////////
        accessDb();

        ////////////////////////////// Nearest Neighbors: //////////////////////////////////////////
        updateNN();

    }

    @Override
    protected void onResume() {
        if (DEBUG) {Log.d("onResume", "Ok");}
        super.onResume();

        mSensorManager.registerListener(mListener, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onStop() {
        if (DEBUG) Log.d("onStop", "Ok");

        mSensorManager.unregisterListener(mListener);
        super.onStop();
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// FUNCTIONS: ////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Function which setups the listeners
     */
    private void setupListeners() {

        ////////////////////////////////////// Checkbox: ///////////////////////////////////////////
        checkPoi.setOnClickListener(checkedListener);
        checkFt.setOnClickListener(checkedListener);
        checkGeo.setOnClickListener(checkedListener);

        checkPoi.setChecked(true);

        ////////////////////////////////////// GPS: ////////////////////////////////////////////////

        locMgr = (LocationManager) MainActivity.this.getSystemService(LOCATION_SERVICE);

        // Check the permission:
        if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        // Define which provider the application will use regarding which one is available
        if (locMgr.isProviderEnabled(LocationManager.NETWORK_PROVIDER)) {
            locMgr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000, 1, new GpsListener());
        } else {
            locMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000, 1, new GpsListener());
        }

        ////////////////////////////////////// Compass: ////////////////////////////////////////////
        mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        mSensor = mSensorManager.getDefaultSensor(Sensor.TYPE_ROTATION_VECTOR);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// FUNCTIONS: ////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Function which access to the db and collect all the information that we need
     */
    public void accessDb() {
        // Get the external directory
        String networkPath = chDb + "/uo_campus.geodatabase";

        if (DEBUG) Log.d("extern", extern);

        try {
            //////////////////////////////////// Open  db: /////////////////////////////////////////
            // open a local geodatabase
            Geodatabase gdb = new Geodatabase(extern + networkPath);

            if (DEBUG) Log.d("GbdTbs", "" + gdb.getGeodatabaseTables());

            //////////////////////////////////// Recover features from  db: ////////////////////////
            GeodatabaseFeatureTable pois = gdb.getGeodatabaseTables().get(0);

            long nbr_lignes = pois.getNumberOfFeatures();
            int nbr_int = (int) nbr_lignes;

            // We recover all the features in the gdb:
            Feature[] features_pois = new Feature[nbr_int];

            for (int l = 1; l <= nbr_lignes; l++) {
                if (pois.checkFeatureExists(l)) {
                    features_pois[l - 1] = pois.getFeature(l);
                } else {
                    features_pois[l - 1] = null;
                }
            }

            if (DEBUG) Log.d("len_ff", "" + features_pois.length);


            /////////////////////////////////// Recover POIs: //////////////////////////////////////
            // Initialize:
            int len0 = features_pois.length ;
            buildings = new BuildingPOI[len0];

            BuildingPOI acBul = new BuildingPOI(); // useful if no object in the db
            double lon, lat;
            Point loc;

            Feature POI;
            BuildingPOI buildTemp;

            for (int k = 0; k < len0; k++) {

                POI = features_pois[k];
                buildTemp = new BuildingPOI();

                // Recover information about buildings :
                if (POI != null) {

                    // Location:
                    lon = (double) POI.getAttributeValue("Longitude");
                    lat = (double) POI.getAttributeValue("Latitude");
                    loc = new Point(lon, lat);
                    buildTemp.setLocation(loc);

                    // Information:
                    buildTemp.setInformation(POI);

                } else {
                    buildTemp = acBul;
                }
                buildings[k] = buildTemp;
            }

            if (DEBUG) Log.d("buildings", "" + buildings.length);

            //////////////////////////////////// Recover features from  db: ////////////////////////
            GeodatabaseFeatureTable footprints = gdb.getGeodatabaseTables().get(1);

            long nbr_lig_ft = footprints.getNumberOfFeatures();
            int nbr_int_ft = (int) nbr_lig_ft;

            // We recover all the features in the gdb:
            Feature[] features_footprints = new Feature[nbr_int_ft];

            for (int r = 1; r <= nbr_lig_ft; r++) {
                features_footprints[r - 1] = footprints.getFeature(r);
            }

            /////////////////////////////////// Recover Footprints: ////////////////////////////////
            // Initialize:
            int len1 = features_footprints.length;
            PoiFootprints = new Polygon[len1];

            Polygon acFoot = new Polygon(); // useful if no object in the db
            Feature Footprint;

            for (int k = 0; k < len1; k++) {

                Footprint = features_footprints[k];

                // Recover information about buildings :
                if (Footprint != null) {
                    PoiFootprints[k]=(Polygon) Footprint.getGeometry();
                } else {
                    PoiFootprints[k] = acFoot;
                }
            }

            Log.d("PoiFootprints", "" + PoiFootprints.length);

            //////////////////////////////////// Recover features from  db: ////////////////////////
            GeodatabaseFeatureTable geoInfos = gdb.getGeodatabaseTables().get(2);

            long nbr_lig_geo = geoInfos.getNumberOfFeatures();
            int nbr_int_geo = (int) nbr_lig_geo;

            // We recover all the features in the gdb:
            Feature[] features_geos = new Feature[nbr_int_geo];

            for (int r = 1; r <= nbr_lig_geo; r++) {
                features_geos[r - 1] = geoInfos.getFeature(r);
            }

            /////////////////////////////////// Recover geoInfos: //////////////////////////////////
            // Initialize:
            int len2 = features_geos.length;
            InfoGeos = new GeoInfo[len2];
            ArrayList<GeoInfo> tst = new ArrayList<>();

            GeoInfo acGeo = new GeoInfo(); // useful if no object in the db
            Feature infoGeo;
            GeoInfo geoTemp;

            for (int l2 = 0; l2 < len2; l2++) {
                geoTemp = new GeoInfo();

                infoGeo = features_geos[l2];

                // Recover information about buildings :
                if (infoGeo != null) {
                    geoTemp.setType((String) infoGeo.getAttributeValue("CLASS_TXT"));
                    geoTemp.setShape((Polygon) infoGeo.getGeometry());
                } else {
                    geoTemp = acGeo;
                }
                InfoGeos[l2] = geoTemp;
            }

            Log.d("InfoGeo", "" + InfoGeos.length);

        } catch (Exception e) {
            popToast("Error while initializing :" + e.getMessage(), true);
            e.printStackTrace();
        }

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Function which launches all the calculations to update the view
     *
     * @param updatedMapView: boolean: true => updated MapView (redraw)
     */
    private void updateView(boolean updatedMapView) {
        if (NN != null) {
            new ViewThread().start();

            // We update the display:
            if (DrawView != null && displayPoi) {
                DrawView.setVariables(NN, orientationVals, user);
                DrawView.invalidate();
            }

            // We update the display:
            if (FtDrawView != null && displayFootprint) {
                FtDrawView.setVariables(NN, orientationVals, user);
                FtDrawView.invalidate();
            }

            // We update the display:
            if (GeoDrawView != null && displayGeoInfo) {
                GeoDrawView.setVariables(simpGeoInfos, orientationVals, user, azimuthReal, pitchReal);
                GeoDrawView.invalidate();
            }

            if(uoMap != null && updatedMapView) {
                uoMap.setAzimut(azimuthReal);
                uoMap.invalidate();
            }
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Function which launches the calculations of the nearest neighbors
     * and the distances between them and the user
     */
    private void updateNN() {
        new NNThread().start();

        // We update the map:
        if(uoMap != null) {
            uoMap.setUser(user.getLocation());
            uoMap.invalidate();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Functions which displays a message on the device's screen, if show = true
     *
     * @param message: String the  displayed message
     * @param show: Boolean true=> display
     */
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
    class GpsListener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            // We project the latLong in  WGS_1984_WMAS:
            locUser = geomen.project(location.getLongitude(), location.getLatitude(), WGS_1984_WMAS);

            // We set the location:
            user.setLocation(locUser);

            // We update the NN:
            updateNN();

            popToast("lat : " + locUser.getX() + ", long : " + locUser.getY(), true);
            Log.d("loc", "lat : " + locUser.getX() + ", long : " + locUser.getY());
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

        // Initialized
        float[] mRotationMatrix = new float[9];
        double oldAzimuthReal;
        double difAzRe;
        boolean updatedMapView;

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

                // We recover the orientation:
                SensorManager.getOrientation(mRotationMatrix, orientationVals);

                // Optionally convert the result from radians to degrees
                orientationVals[0] = (float) ((Math.toDegrees(orientationVals[0])));
                orientationVals[1] = (float) ((Math.toDegrees(orientationVals[1])));
                orientationVals[2] = (float) ((Math.toDegrees(orientationVals[2])));


                // The azimut:
                oldAzimuthReal = azimuthReal;
                azimuthReal = orientationVals[0];
                pitchReal = orientationVals[1];

                // We redraw uoMap only if diff between the old azimuth and the new is superior to 1:
                difAzRe = Math.abs(azimuthReal - oldAzimuthReal);
                updatedMapView = difAzRe > 1;

                // Update te view:
                updateView(updatedMapView);
            }
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
    };

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Footprint's Listener
     */
    private View.OnClickListener checkedListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            //Pois:
            if(checkPoi.isChecked()){
                displayPoi = true;
                DrawView.setVisibility(View.VISIBLE);
            }
            else if(!checkPoi.isChecked()){
                displayPoi = false;
                DrawView.setVisibility(View.INVISIBLE);
            }

            // Footprints:
            if(checkFt.isChecked()){
                displayFootprint = true;
                FtDrawView.setVisibility(View.VISIBLE);
            }
            else if(!checkFt.isChecked()) {
                displayFootprint = false;
                FtDrawView.setVisibility(View.INVISIBLE);
            }

            // Geological information:
            if(checkGeo.isChecked()){
                displayGeoInfo = true;
                GeoDrawView.setVisibility(View.VISIBLE);
            }
            else if(!checkGeo.isChecked()) {
                displayGeoInfo = false;
                GeoDrawView.setVisibility(View.INVISIBLE);
            }
        }
    };

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// THREAD: ///////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public class NNThread extends Thread {
        @Override
        public void run() {
            // We recover the NF:
            ArrayList<Polygon> NF =  user.nearestFootprints(geomen, PoiFootprints, WGS_1984_WMAS, 250, meter);
            if (DEBUG) Log.d("NF250", "" + NF.size());

            // We recover the NN of the user:
            NN = user.nearestNeighbors(geomen, buildings, NF, InfoGeos, WGS_1984_WMAS, 200, meter);
            if (DEBUG) Log.d("NN200", "" + NN.size());

            // we actualize the geological information:
            simpGeoInfos = user.simplifyGeoInfo(geomen, InfoGeos,WGS_1984_WMAS,200,meter);
        }
    }

    public class ViewThread extends Thread {
        @Override
        public void run() {
            // We calculate the azimuth between all the NN and the user:
            NN = user.theoreticalAngleToPOIs(NN, azimuthReal, AZIMUTH_ACCURACY, pitchReal,PITCH_ACCURACY );
            if(DEBUG) Log.d("azTeo", "" + NN);
        }
    }



}
