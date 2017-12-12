package com.borscha.micka.playgroundproject.Activities.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.borscha.micka.playgroundproject.R;

public class ConfirmSmsActivity extends AppCompatActivity {

    EditText editText;
    Button button,confirmButton;
    RequestQueue queue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirm_sms);
        editText = (EditText) findViewById(R.id.confirmSms);
        button = (Button) findViewById(R.id.refreshSmsCode);
        Intent intent = getIntent();
        String responce = intent.getStringExtra("userId");
        Log.wtf("String responce",responce);
        queue = Volley.newRequestQueue(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendData();
            }
        });
    }

    void sendData(){
        StringRequest myReq = new StringRequest(Request.Method.GET, "http://unix.trosha.dev.lumination.com.ua/login/4/resend", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.wtf("Responce is",response.toString());
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.wtf("Responce is",error.toString());
            }
        });
        queue.add(myReq);
    }
}
