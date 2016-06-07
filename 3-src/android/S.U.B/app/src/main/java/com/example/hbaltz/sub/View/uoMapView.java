package com.example.hbaltz.sub.View;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;

import com.esri.android.map.GraphicsLayer;
import com.esri.android.map.MapView;
import com.esri.android.map.TiledLayer;
import com.esri.android.map.ags.ArcGISLocalTiledLayer;
import com.esri.core.geometry.GeometryEngine;
import com.esri.core.geometry.Point;
import com.esri.core.geometry.SpatialReference;
import com.esri.core.map.Graphic;
import com.esri.core.symbol.SimpleMarkerSymbol;
import com.esri.core.symbol.Symbol;

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
    private final String tpkPath  = chTpk +"uO.tpk";

    //////////////////////////////////// Tiled Layer: //////////////////////////////////////////////
    private TiledLayer UoTileLayer =  new ArcGISLocalTiledLayer(extern + tpkPath);

    //////////////////////////////////// Graphic Layer: ////////////////////////////////////////////
    private GraphicsLayer mGraphicsLayer = new GraphicsLayer(GraphicsLayer.RenderingMode.DYNAMIC);
    private Symbol symbol = new SimpleMarkerSymbol(Color.RED, 10, SimpleMarkerSymbol.STYLE.CROSS);

    //////////////////////////////////// Geometry engine : /////////////////////////////////////////
    private GeometryEngine geomen = new GeometryEngine();

    //////////////////////////////////// Location: /////////////////////////////////////////////////
    private Point locUser = new Point();

    //////////////////////////////////// Debug: ////////////////////////////////////////////////////
    private final boolean DEBUG = true;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// CONSTRUCTORS: /////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public uoMapView(Context context, AttributeSet attrs) {
        super(context, attrs);

        // we add the layers
        this.addLayer(UoTileLayer);
        this.addLayer(mGraphicsLayer);
        this.zoomin(true);
        this.zoomout(true);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// METHODS: //////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onDraw(Canvas canvas) {
        Graphic loc = new Graphic(locUser, symbol);

        mGraphicsLayer.addGraphic(loc);

        super.onDraw(canvas);
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////// FUNCTIONS: ////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void setLayer(TiledLayer tiledLayer){
        this.UoTileLayer = tiledLayer;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////

    public void setUser(Point usr){
        SpatialReference mapRef = this.getSpatialReference();

        this.locUser = geomen.project(usr.getX(),usr.getY(),mapRef);
    }
}
