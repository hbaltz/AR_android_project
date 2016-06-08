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
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.esri.core.geometry.GeometryEngine;
import com.esri.core.geometry.LinearUnit;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.SpatialReference;
import com.esri.core.geometry.Unit;
import com.esri.core.map.Feature;
import com.example.hbaltz.sub.Class.BuildingPOI;
import com.example.hbaltz.sub.Class.User;
import com.example.hbaltz.sub.Class.Utilities;
import com.example.hbaltz.sub.View.CameraView;
import com.example.hbaltz.sub.View.DrawSurfaceView;
import com.example.hbaltz.sub.View.uoMapView;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationServices;

import java.util.ArrayList;

public class MainActivity extends FragmentActivity {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// VARIABLES: ////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////// ArcGIS Elements : /////////////////////////////////////////
    private final String extern = Environment.getExternalStorageDirectory().getPath();
    private final String chDb = "/Android/data/com.example.hbaltz.sub/sub";

    ////////////////////////////////////// GPS: ////////////////////////////////////////////////////
    private LocationManager locMgr;
    private Point locUser = new Point(-8425358, 5688505); // By default : 70 Laurier Street, Ottawa
    private User user = new User(locUser);

    ////////////////////////////////////// Compass: ////////////////////////////////////////////////
    private SensorManager mSensorManager;
    private Sensor mSensor;

    //////////////////////////////////// Spatial reference: ////////////////////////////////////////
    private SpatialReference WGS_1984_WMAS = SpatialReference.create(102100);

    //////////////////////////////////// Buildings: ///////////////////////////////////////////////
    private BuildingPOI[] buildings;
    private ArrayList<BuildingPOI> NN;
    ArrayList<Double> distances;

    //////////////////////////////////// Geometrie Engine: /////////////////////////////////////////
    private GeometryEngine geomen;

    //////////////////////////////////// Unit: /////////////////////////////////////////////////////
    private Unit meter = Unit.create(LinearUnit.Code.METER);

    //////////////////////////////////// Azimuth: //////////////////////////////////////////////////
    private double azimuthReal = 0;
    private static double AZIMUTH_ACCURACY = 60; // 120 degrees is the human visual field

    /////////////////////////////////// Views: /////////////////////////////////////////////////////
    private DrawSurfaceView DrawView;
    private uoMapView uoMap;
    private CameraView cameraView;
    //////////////////////////////////// Widgets: //////////////////////////////////////////////////
    private CheckBox checkBoxCam;

    /////////////////////////////////// Google: ////////////////////////////////////////////////////
    private GoogleApiClient mGoogleApiClient;
    private Location mLocation;

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
        cameraView = (CameraView) findViewById(R.id.CameraView);
        if (cameraView != null) {
            cameraView.setVisibility(View.INVISIBLE);
            }
        uoMap = (uoMapView) findViewById(R.id.uoMap) ;

        /////////////////////////////// Listeners: /////////////////////////////////////////////////
        setupListeners();

        /////////////////////////////// Database: //////////////////////////////////////////////////
        accessDb();

        ////////////////////////////// Nearest Neighbors: //////////////////////////////////////////
        updateNN();

    }

    @Override
    protected void onStart() {
        super.onStart();
        mGoogleApiClient.connect();
    }

    @Override
    protected void onResume() {
        if (DEBUG) {Log.d("onResume", "Ok");}
        super.onResume();

        mSensorManager.registerListener(mListener, mSensor, SensorManager.SENSOR_DELAY_NORMAL);
        mGoogleApiClient.connect();
    }

    @Override
    protected void onStop() {
        if (DEBUG) {Log.d("onStop", "Ok");}

        if (mGoogleApiClient.isConnected()) {
                mGoogleApiClient.disconnect();
            }

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
        ////////////////////////////////////// Widgets: ////////////////////////////////////////////
        checkBoxCam = (CheckBox) findViewById(R.id.checkBoxCam);
        String camTxt = getResources().getString(R.string.cam);
        checkBoxCam.setText(camTxt);
        checkBoxCam.setOnClickListener(new checkedCamListener());

        ////////////////////////////////////// GPS: ////////////////////////////////////////////////

        locMgr = (LocationManager) MainActivity.this.getSystemService(LOCATION_SERVICE);

        // Check the permission:
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
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

        ////////////////////////////// Google services: ////////////////////////////////////////////
        GoogleServices googleServices = new GoogleServices();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                                .addConnectionCallbacks(googleServices)
                                .addOnConnectionFailedListener(googleServices)
                                .addApiIfAvailable(LocationServices.API)
                                .build();

        mGoogleApiClient.connect();
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

        if (DEBUG) {
            Log.d("extern", extern);
        }

        try {
            //////////////////////////////////// Open  db: /////////////////////////////////////////
            // open a local geodatabase
            Geodatabase gdb = new Geodatabase(extern + networkPath);

            if (DEBUG) {
                Log.d("GbdTbs", "" + gdb.getGeodatabaseTables());
            }

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

            if (DEBUG) {
                Log.d("len_ff", "" + features_pois.length);
            }

            /////////////////////////////////// Recover POIs: //////////////////////////////////////
            // Initialize:
            int len0 = features_pois.length - 1;
            buildings = new BuildingPOI[len0 + 1];

            BuildingPOI acBul = new BuildingPOI(); // useful if no object in the db

            for (int k = 0; k < len0; k++) {

                Feature Footprint = features_pois[k];
                BuildingPOI buildTemp = new BuildingPOI();

                // Recover information about buildings :
                if (Footprint != null) {

                    // Location:
                    double lon = (double) Footprint.getAttributeValue("Longitude");
                    double lat = (double) Footprint.getAttributeValue("Latitude");
                    Point loc = new Point(lon, lat);
                    buildTemp.setLocation(loc);

                    // Name and description:
                    buildTemp.setName((String) Footprint.getAttributeValue("BUILDNAME"));
                    buildTemp.setDescription((String) Footprint.getAttributeValue("STRCTWALL"));
                } else {
                    buildTemp = acBul;
                }
                buildings[k] = buildTemp;
            }

            Log.d("buildings", "" + buildings.length);

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
            // We calculate the azimuth between all the NN and the user:
            ArrayList<Double> azTheos = user.theoreticalAzimuthToPOIs(NN);
            if(DEBUG) {Log.d("azTeo", "" + azTheos);}

            // We check if the user sees the NN:
            ArrayList<Boolean> visible = Utilities.isAzimuthsVisible(azTheos, azimuthReal, AZIMUTH_ACCURACY);
            if (DEBUG) {Log.d("visible", "" + visible);}

            // We update the display:
            if (DrawView != null) {
                DrawView.setVariables(NN, distances, azTheos, azimuthReal, visible);
                DrawView.invalidate();
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
        // We recover the NN of the user:
        NN = user.nearestNeighbors(geomen, buildings, WGS_1984_WMAS, 200, meter);

        if (DEBUG) {Log.d("NN200", "" + NN.size());}

        // We calculate the distance between all the NN and the user:
        if (NN != null) {
            distances = user.distanceToBuilds(geomen, NN, WGS_1984_WMAS);

            if (DEBUG) {Log.d("distances", "" + distances);}
        }

        // We update the map:
        if(uoMap != null) {
            uoMap.setUser(user.getLocation());
            uoMap.invalidate();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Functions whichi displays a message on the device's screen, if show = true
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
     * Listener for the camera checkBox
     */
    class checkedCamListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (((CheckBox) v).isChecked()) {
                    cameraView.setVisibility(View.VISIBLE);
                } else {
                    cameraView.setVisibility(View.INVISIBLE);
            }
        }
    }

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

            popToast("lat : " + locUser.getX() + ", long : " + locUser.getY()
                    + ", bearing : " + location.getBearing(), true);
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

                // We recover the orientation:
                SensorManager.getOrientation(mRotationMatrix, orientationVals);

                // Optionally convert the result from radians to degrees
                orientationVals[0] = (float) Math.toDegrees(orientationVals[0]);
                orientationVals[1] = (float) Math.toDegrees(orientationVals[1]);
                orientationVals[2] = (float) Math.toDegrees(orientationVals[2]);

                // The azimut:
                double oldAzimuthReal = azimuthReal;
                azimuthReal = (orientationVals[0] + 360) % 360;

                // We redraw uoMap only if diff between the old azimuth and the new is superior to 1:
                double difAzRe = Math.abs(azimuthReal - oldAzimuthReal);
                boolean updatedMapView = difAzRe > 1;

                // Update te view:
                updateView(updatedMapView);
            }
        }

        public void onAccuracyChanged(Sensor sensor, int accuracy) {
        }
    };

    ////////////////////////////////////////////////////////////////////////////////////////////////

    private class GoogleServices implements GoogleApiClient.ConnectionCallbacks,
                GoogleApiClient.OnConnectionFailedListener {

        @Override
        public void onConnected(@Nullable Bundle bundle) {
            if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                return;
            }

            mLocation = LocationServices.FusedLocationApi.getLastLocation(mGoogleApiClient);
            if (mLocation != null) {
                double lat = mLocation.getLatitude();
                double lng = mLocation.getLongitude();

                    String msg = "Lat: " + lat + ", lng : " + lng;
                    popToast(msg, true);
                    Log.d("Loc", msg);
            } else {
                popToast("Location not Detected", true);
            }
        }

        @Override
        public void onConnectionSuspended(int i) {
            Log.i("Google", "Connection Suspended");
            mGoogleApiClient.connect();
        }

        @Override
        public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
            Log.i("Google", "Connection failed. Error: " + connectionResult.getErrorCode());
        }
    }
}
