package com.borscha.micka.playgroundproject.Activities.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.borscha.micka.playgroundproject.R;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    EditText mTelephoneLogin;
    Button mButtonLogin;
    TextView link_signup;
    RequestQueue queue;
    String vTelephone;
    final String URL = "http://unix.trosha.dev.lumination.com.ua/login";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        queue = Volley.newRequestQueue(this);
        mTelephoneLogin = (EditText) findViewById(R.id.input_login_telephone);
        mButtonLogin = (Button) findViewById(R.id.btn_login);
        link_signup = (TextView) findViewById(R.id.link_signup);

        link_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),RegisterActivity.class));
            }
        });
        mButtonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mTelephoneLogin.getText().toString()!=null) {
                    vTelephone = mTelephoneLogin.getText().toString();
                    sendData();
                }else {
                    Toast.makeText(getApplicationContext(),"Something wrong, try again",Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    void sendData(){
        StringRequest myReq = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.wtf("Responce is",response.toString());
                if(response!=null){
                    Intent intent = new Intent(getApplicationContext(),ConfirmSmsActivity.class);
                    intent.putExtra("userId",RegisterActivity.getStringValue(response));
                    startActivity(intent);
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.wtf("Error is",error.toString());
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("phoneNumber",vTelephone);
                return params;
            }
        };
        queue.add(myReq);
    }


}
