package com.example.hbaltz.sub;

import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.esri.android.map.MapView;
import com.esri.core.geodatabase.Geodatabase;
import com.esri.core.geodatabase.GeodatabaseFeatureTable;
import com.esri.core.geometry.Geometry;
import com.esri.core.geometry.Polygon;
import com.esri.core.geometry.Polyline;
import com.esri.core.geometry.SpatialReference;
import com.esri.core.map.Feature;
import com.esri.core.table.FeatureTable;
import com.esri.core.tasks.na.RouteTask;

import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// VARIABLES: ////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////// ArcGIS Elements : /////////////////////////////////////////
    //private final String extern = Environment.getExternalStorageDirectory().getPath();
    private final String extern = "/storage/sdcard1";

    // TODO : auto-detect the chDb

    // With Sd card :
    private final String chDb = "/sub";
/*
    // Without sd card :
    private final String chTpk = "/Android/data/com.example.formation.androidprojet_v1/ArcGIS/";
*/

    //////////////////////////////////// Spatial reference: ////////////////////////////////////////
    private SpatialReference WGS_1984_WMAS = SpatialReference.create(102100);

    //////////////////////////////////// Features: /////////////////////////////////////////////////
    private Feature[] features_footprints;

    //////////////////////////////////// Features: /////////////////////////////////////////////////
    private Geometry[] geom_footprints;

    //////////////////////////////////// Debug: /////////////////////////////////////////////////
    private final boolean DEBUG = true;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// METHODS: //////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

            if(DEBUG){Log.d("GbdTbs", "" + gdb.getGeodatabaseTables());}

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

            if(DEBUG){Log.d("ff", ""+features_footprints.length);}


            /////////////////////////////////// Recover  geometries: ///////////////////////////////
            // Initialize:
            int len0 = features_footprints.length - 1;
            geom_footprints= new Geometry[len0+1];

            Geometry acme = new Polygon(); // useful if no object in the db
            for (int k = 0; k < len0; k++) {

                Feature Footprint = features_footprints[k];
                // Recover geometries :
                if(Footprint !=null){
                    geom_footprints[k] = Footprint.getGeometry();
                }else{
                    geom_footprints[k] = acme;
                }
            }

            /*
            //////////////////////////////////// Union des magasins : //////////////////////////////
            mag_niveau0 = geomen.union(mag_niv0_geom, WGS_1984_WMAS);
            */

        } catch (Exception e) {
            popToast("Error while initializing :" + e.getMessage(), true);
            e.printStackTrace();
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
}
