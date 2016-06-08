package com.example.hbaltz.sub.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import com.esri.android.map.GraphicsLayer;
import com.esri.android.map.MapView;
import com.esri.android.map.TiledLayer;
import com.esri.android.map.ags.ArcGISLocalTiledLayer;
import com.esri.core.geometry.Point;
import com.esri.core.map.Graphic;
import com.esri.core.symbol.PictureMarkerSymbol;
import com.esri.core.symbol.SimpleMarkerSymbol;
import com.esri.core.symbol.Symbol;
import com.example.hbaltz.sub.R;

/**
 * Created by hbaltz on 6/3/2016.
 */
public class uoMapView extends MapView {

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// VARIABLES: ////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    //////////////////////////////////// ArcGIS Elements : /////////////////////////////////////////
    private final String extern = "/storage/sdcard1";
    private final String chTpk = "/sub/";
    private final String tpkPath = chTpk + "uO.tpk";
    //////////////////////////////////// Debug: ////////////////////////////////////////////////////
    private final boolean DEBUG = true;
    //////////////////////////////////// Graphic Layer: ////////////////////////////////////////////
    private GraphicsLayer mGraphicsLayer;
    private PictureMarkerSymbol symbolImg;
    //////////////////////////////////// Location: /////////////////////////////////////////////////
    private Point locUser = new Point();
    /////////////////////////////////// Orientation: ///////////////////////////////////////////////
    private double azimut=-90;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// CONSTRUCTORS: /////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public uoMapView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // Initialize:
        TiledLayer UoTileLayer = new ArcGISLocalTiledLayer(extern + tpkPath);

        mGraphicsLayer = new GraphicsLayer(GraphicsLayer.RenderingMode.DYNAMIC);

        Drawable logImg = getResources().getDrawable(R.drawable.ic_action_name);
        symbolImg = new PictureMarkerSymbol(logImg);

        // We add the layers to the mapView:
        this.addLayer(UoTileLayer);
        this.addLayer(mGraphicsLayer);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// METHODS: //////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onDraw(Canvas canvas) {
        // We remove any previous points:
        mGraphicsLayer.removeAll();

        // We set the zoom:
        for (int i = 0; i < 3; i++) {zoomout(false);}
        for (int j = 0; j < 3; j++) {zoomin(false);}

        // We center the map on the location User:
        this.centerAt(locUser, true);

        // TODO change time to redraw
        // We change the orientation:
        float angle = (float) (-90+azimut);
        symbolImg.setAngle(angle);

        // We add the point to the graphicLayer:
        Graphic loc = new Graphic(locUser, symbolImg);
        mGraphicsLayer.addGraphic(loc);

        this.getCallout().hide();

        super.onDraw(canvas);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// FUNCTIONS: ////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void setUser(Point usr) {
        this.locUser = usr;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void setAzimut(double az){
        this.azimut = az;
    }
}
