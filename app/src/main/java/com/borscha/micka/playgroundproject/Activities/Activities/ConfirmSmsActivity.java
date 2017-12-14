package com.borscha.micka.playgroundproject.Activities.Activities;

import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.borscha.micka.playgroundproject.Activities.Activities.Entities.GlobalUser;
import com.borscha.micka.playgroundproject.Activities.Activities.network.VolleySingleton;
import com.borscha.micka.playgroundproject.R;

import org.json.JSONObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ConfirmSmsActivity extends AppCompatActivity implements View.OnClickListener {


    String userId;
    EditText mSmsCode;
    Button mResendSms,mSendSms;
    String vCode;
    final String URL = "http://unix.trosha.dev.lumination.com.ua/login/";

    GlobalUser globalUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_sms);
        globalUser = (GlobalUser) getApplicationContext();

        Intent intent = getIntent();
        userId = intent.getStringExtra("userId");
        mSmsCode = (EditText) findViewById(R.id.input_code);
        mResendSms = (Button) findViewById(R.id.btn_refresh_sms);
        mSendSms = (Button) findViewById(R.id.btn_confirm_sms);


        mResendSms.setOnClickListener(this);
        mSendSms.setOnClickListener(this);


    }

    private void refreshSms(){
        StringRequest myReq = new StringRequest(Request.Method.GET, URL + userId + "/resend", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.wtf("Resend is",response.toString());
                if (response!=null){
                    Toast.makeText(getApplicationContext(),"Code resended",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.wtf("Resend is",error.toString());
            }
        });
       VolleySingleton.getInstance(this).addToRequestQueue(myReq);
    }

    private void sendCode(){
        vCode = mSmsCode.getText().toString();
        StringRequest myReq = new StringRequest(Request.Method.POST, URL + userId, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.wtf("Responce is",response.toString());

                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        intent.putExtra("userId",userId);
                        startActivity(intent);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.wtf("Error is",error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> params = new HashMap<>();
                params.put("smsCode",vCode);

                return params;
            }
            @Override
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                if (response.headers == null)
                {
                    // cant just set a new empty map because the member is final.
                    response = new NetworkResponse(
                            response.statusCode,
                            response.data,
                            Collections.<String, String>emptyMap(), // this is the important line, set an empty but non-null map.
                            response.notModified,
                            response.networkTimeMs);


                }

                return super.parseNetworkResponse(response);
            }
        };
        VolleySingleton.getInstance(this).addToRequestQueue(myReq);
    }

    @Override
    public void onClick(View view) {
        switch(view.getId()) {
            case R.id.btn_refresh_sms:
                refreshSms();
                break;
            case R.id.btn_confirm_sms:
                sendCode();
                break;
        }
    }


}
