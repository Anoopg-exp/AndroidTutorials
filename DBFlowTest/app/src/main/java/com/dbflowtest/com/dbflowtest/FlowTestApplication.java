package com.dbflowtest.com.dbflowtest;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

/**
 * Created by COMP on 14-01-2016.
 */
public class FlowTestApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        // This is for initiating DB flow...
        FlowManager.init(this);
    }
}
