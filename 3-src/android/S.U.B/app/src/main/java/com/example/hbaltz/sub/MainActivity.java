package com.example.hbaltz.sub;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.SensorEventListener;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.esri.android.map.MapView;
import com.esri.core.geodatabase.Geodatabase;
import com.esri.core.geodatabase.GeodatabaseFeatureTable;
import com.esri.core.geometry.Geometry;
import com.esri.core.geometry.GeometryEngine;
import com.esri.core.geometry.Polygon;
import com.esri.core.geometry.Polyline;
import com.esri.core.geometry.SpatialReference;
import com.esri.core.map.Feature;
import com.esri.core.table.FeatureTable;
import com.esri.core.tasks.na.RouteTask;
import com.example.hbaltz.sub.Class.MyLocationListener;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Map;

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
    private final String chDb = "/Androi/data/com.example.hbaltz.sub/sub";

    ////////////////////////////////////// GPS: ////////////////////////////////////////////////////
    private LocationManager locMgr;

    //////////////////////////////////// Spatial reference: ////////////////////////////////////////
    private SpatialReference WGS_1984_WMAS = SpatialReference.create(102100);

    //////////////////////////////////// Features: /////////////////////////////////////////////////
    private Feature[] features_footprints;

    //////////////////////////////////// Geometries: ///////////////////////////////////////////////
    private Geometry[] geom_footprints;
    private Geometry all_geom_footprints;

    //////////////////////////////////// Geometries: ///////////////////////////////////////////////
    private GeometryEngine geomen;

    //////////////////////////////////// Debug: ////////////////////////////////////////////////////
    private final boolean DEBUG = true;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

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

        ////////////////////////////////////// GPS: ////////////////////////////////////////////////
        locMgr = (LocationManager) this.getSystemService(LOCATION_SERVICE);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            return;
        }

        LocationProvider high = locMgr.getProvider(locMgr.getBestProvider(MyLocationListener.createFineCriteria(), true));
        locMgr.requestLocationUpdates(high.getName(), 0, 0f, new MyLocationListener());

        /////////////////////////////// Database: //////////////////////////////////////////////////
        accessDb();

    }

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

        Log.d("extern", extern);

        //String networkName = "GRAPH_Final_ND";


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

            if (DEBUG) {
                Log.d("ff", "" + features_footprints.length);
            }


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

            if (DEBUG) {
                Log.d("len_geom_temp", "" + geomTemp.length);
            }
            if (DEBUG) {
                Log.d("len_geom_rem", "" + geomRem.length);
            }

            // We do the union of the array with a length below the limit
            array_union[0] = geomen.union(geomTemp, SpaRef);

            // While the length of the remaining geometries is not below the limit
            // we split the array and we do several union to never exceed the limit
            while (len_rem > 510) {
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
