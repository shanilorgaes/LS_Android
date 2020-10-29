package com.orgaes.ls.AppData;

import android.app.Activity;
import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.OnLifecycleEvent;
import androidx.multidex.MultiDex;

//import io.fabric.sdk.android.Fabric;

public class Appclass extends Application implements LifecycleObserver,Application.ActivityLifecycleCallbacks {

    public static final String TAG = Appclass.class.getSimpleName();
    static Context mContext;
    private static Appclass mInstance;
    private static boolean activityVisible;
    long counterlong;

    public static Context getContext() {
        return mContext;
    }

    public static synchronized Appclass getInstance() {
        return mInstance;
    }

    public static boolean isActivityVisible() {
        return activityVisible;
    }

    public static void activityResumed() {
        activityVisible = true;
    }

    public static void activityPaused() {
        activityVisible = false;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
//        CustomActivityOnCrash.install(this);
        registerActivityLifecycleCallbacks(this);
        mContext = getApplicationContext();
        mInstance = this;

//        Crashlytics crashlyticsKit = new Crashlytics.Builder()
//                .core(new CrashlyticsCore.Builder().disabled(false).build())
//                .build();
//        Fabric.with(this, crashlyticsKit);
//        CrashlyticsCore core = new CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build();
//        Fabric.with(this, new Crashlytics.Builder().core(core).build());

        logUser();

    }



    private static boolean isBackground= false;
    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void onStopped() {
        isBackground=true;
    }
    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onStarted() {
        isBackground=false;
    }

    private void logUser() {

        SharedPreferences prefs = getSharedPreferences("LS", MODE_PRIVATE);
        String parentPh = prefs.getString("userPhoneNumber", "8848960252");
        counterlong = prefs.getLong("counterlong", 0);

//        Crashlytics.setUserName(parentPh);
    }


    public static boolean isBackground() {
        return isBackground;
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle bundle) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {

    }

    @Override
    public ComponentName startForegroundService(Intent service) {
        return super.startForegroundService(service);
    }
}
