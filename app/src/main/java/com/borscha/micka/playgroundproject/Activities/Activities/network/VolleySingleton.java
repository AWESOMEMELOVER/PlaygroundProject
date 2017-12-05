package com.borscha.micka.playgroundproject.Activities.Activities.network;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.borscha.micka.playgroundproject.Activities.Activities.ApplicationContext;

/**
 * Created by micka on 05.12.2017.
 */

public class VolleySingleton {
    private static VolleySingleton sInstance=null;
    private RequestQueue mRequestQueue;
    private VolleySingleton(){
        mRequestQueue = Volley.newRequestQueue(ApplicationContext.getAppContext());
    }
    public static VolleySingleton getsInstance(){
        if (sInstance==null)
            sInstance = new VolleySingleton();
        return sInstance;
    }

}
