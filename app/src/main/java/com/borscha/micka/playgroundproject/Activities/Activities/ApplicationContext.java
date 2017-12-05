package com.borscha.micka.playgroundproject.Activities.Activities;

import android.app.Application;
import android.content.Context;

/**
 * Created by micka on 05.12.2017.
 */

public class ApplicationContext extends Application {
    private static ApplicationContext sInstance;

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance=this;
    }

    public static ApplicationContext getInstance(){
        return sInstance;
    }
    public static Context getAppContext(){
        return sInstance.getApplicationContext();
    }
}
