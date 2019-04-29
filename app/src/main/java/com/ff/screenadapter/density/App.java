package com.ff.screenadapter.density;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

/**
 * description: 在Application中进行Density适配
 * author: FF
 * time: 2019-04-29 19:00
 */
public class App extends Application implements Application.ActivityLifecycleCallbacks {

    @Override
    public void onCreate() {
        super.onCreate();

        registerActivityLifecycleCallbacks(this);
    }

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        Density.setDensity(App.this, activity);
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

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Density.resetDensity(App.this, activity);
    }
}
