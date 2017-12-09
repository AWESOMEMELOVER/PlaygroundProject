package com.borscha.micka.playgroundproject.Activities.Activities.network;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.borscha.micka.playgroundproject.Activities.Activities.ApplicationContext;

/**
 * Created by micka on 05.12.2017.
 */

public class VolleySingleton {
    private static VolleySingleton mInstance;
    private RequestQueue mRequestQueue;
    private static Context mContext;

    private VolleySingleton(Context context){
        mContext = context;
        mRequestQueue = getRequestQueue();
    }
    public RequestQueue getRequestQueue(){
        if(mRequestQueue ==null)
            mRequestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
        return mRequestQueue;
    }
    public static synchronized VolleySingleton getInstance(Context context){
        if(mInstance==null)
            mInstance = new VolleySingleton(context);
        return mInstance;
    }
    public <T> void addToRequestQueue(Request<T> request){
        getRequestQueue().add(request);
    }

}
