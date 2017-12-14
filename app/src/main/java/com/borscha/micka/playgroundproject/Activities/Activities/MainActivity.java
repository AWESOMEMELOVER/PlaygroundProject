package com.borscha.micka.playgroundproject.Activities.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.borscha.micka.playgroundproject.Activities.Activities.Entities.GlobalUser;
import com.borscha.micka.playgroundproject.Activities.Activities.Fragments.BeaconsFragment;
import com.borscha.micka.playgroundproject.Activities.Activities.Fragments.PlayfroundFragment;
import com.borscha.micka.playgroundproject.Activities.Activities.Fragments.SettingsFragment;
import com.borscha.micka.playgroundproject.Activities.Activities.Fragments.TrackFragment;
import com.borscha.micka.playgroundproject.Activities.Activities.network.VolleySingleton;
import com.borscha.micka.playgroundproject.R;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private String reqId,reqName,reqLast,reqGender,reqBirthday, reqNum,userId;
    final String URLUser = "http://unix.trosha.dev.lumination.com.ua/user";
    GlobalUser globalUser;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            switch (item.getItemId()) {
                case R.id.navigation_playground:
                    transaction.replace(R.id.container,new BeaconsFragment()).commit();
                    return true;
                case R.id.navigation_track:
                    transaction.replace(R.id.container,new TrackFragment()).commit();
                    return true;
                case R.id.navigation_settings:
                    transaction.replace(R.id.container,new SettingsFragment()).commit();
                    return true;
            }
            return false;
        }

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        globalUser = (GlobalUser) getApplicationContext();
        Intent intent = getIntent();
        userId =intent.getStringExtra("userId");

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container,new BeaconsFragment()).commit();

        getUser();
    }

    private void getUser(){
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, URLUser + userId, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    reqNum = response.getString("phoneNumber");
                    reqName  = response.getString("firstName");
                    reqLast = response.getString("lastName");
                    reqGender = response.getString("gender");
                    reqBirthday = response.getString("birthday");

                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.wtf("Error is",error.toString());
            }
        });
        VolleySingleton.getInstance(this).addToRequestQueue(request);

        globalUser.setUserId(reqId);
        globalUser.setFirstName(reqName);
        globalUser.setLastName(reqLast);
        globalUser.setTelephoneNumber(reqNum);
        globalUser.setBirthday(reqBirthday);
        globalUser.setGender(reqGender);
    }


}
