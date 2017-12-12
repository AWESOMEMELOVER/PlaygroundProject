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

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText editText;
    Button button;
    RequestQueue queue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.sendTelephoneNumber);
        queue = Volley.newRequestQueue(this);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String number = editText.getText().toString();
                sendData(number);
            }
        });
    }

    void sendData(final String telephone){
        StringRequest myReq = new StringRequest(Request.Method.POST, "http://unix.trosha.dev.lumination.com.ua/user", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response!=null) {
                    Log.wtf("RESPONCE", response.toString());
                    Intent intent = new Intent(getApplicationContext(), ConfirmSmsActivity.class);
                    intent.putExtra("userId",response);
                    startActivity(intent);

                }
                else{
                    Log.wtf("RESPONCE","Is empty");
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.wtf("Error",error.toString());
            }
        }){
            @Override
            protected Map<String, String> getParams()
            {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("phoneNumber", "380984895779");
                params.put("firstName", "micka");
                params.put("lastName", "borscha");
                params.put("gender", "1");
                params.put("birthday","1993-05-23");
                return params;
            }
        };
        queue.add(myReq);
    }
}
