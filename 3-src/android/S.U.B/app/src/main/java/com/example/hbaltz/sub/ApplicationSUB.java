package com.example.hbaltz.sub;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

/**
 * Created by hbaltz on 6/7/2016.
 */
public class ApplicationSUB extends Application {

    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
