package com.example.szamol.quoter;

import android.app.Application;
import android.content.Context;

import com.squareup.leakcanary.LeakCanary;

public class GlobalContext extends Application {
    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        appContext = getApplicationContext();
    }

    public static Context getAppContext() {
        return appContext;
    }
}
