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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.borscha.micka.playgroundproject.Activities.Activities.Entities.GlobalUser;
import com.borscha.micka.playgroundproject.R;

import java.text.NumberFormat;
import java.text.ParsePosition;
import java.util.HashMap;
import java.util.Map;

import static java.nio.file.Paths.get;

public class RegisterActivity extends AppCompatActivity {

    EditText mRegInputName,mRegInputLast,mRegTelephoneNumber;
    TextView link_login;
    Button btn_signup;
    RequestQueue queue;
    final String birthday = "1993-05-23";
    final String gender = "1";
    final String URL = "http://unix.trosha.dev.lumination.com.ua/user";
    String vTelephone,vName,vLast;
    GlobalUser globalUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        globalUser = (GlobalUser) getApplicationContext();

        queue = Volley.newRequestQueue(this);
        mRegInputName = (EditText) findViewById(R.id.input_name);
        mRegInputLast = (EditText) findViewById(R.id.input_last);
        mRegTelephoneNumber = (EditText) findViewById(R.id.input_number);
        link_login = (TextView) findViewById(R.id.link_login);
        btn_signup = (Button) findViewById(R.id.btn_signup);




        btn_signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vTelephone = mRegTelephoneNumber.getText().toString();
                vName = mRegInputName.getText().toString();
                vLast = mRegInputLast.getText().toString();
                sendData();
            }
        });

        link_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(),LoginActivity.class));
            }
        });





    }

    void sendData() {
        StringRequest postRequest = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if (response != null) {
                    Log.wtf("RESPONCE", response.toString());
                    globalUser.setUserId(getStringValue(response));
                    globalUser.setFirstName(vName);
                    globalUser.setLastName(vLast);
                    globalUser.setGender(gender);
                    globalUser.setBirthday(birthday);

                    startActivity(new Intent(getApplicationContext(),MainActivity.class));
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"Something wrong, try again",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {
                Map<String, String>  params = new HashMap<String, String>();
                params.put("phoneNumber", vTelephone);
                params.put("firstName", vName);
                params.put("lastName", vLast);
                params.put("gender", gender);
                params.put("birthday",birthday);

                return params;
            }
        };


        queue.add(postRequest);
    }




    public static String getStringValue(String responce){
        String result="";
        String [] bufStrings = responce.split("\\:");
        result=bufStrings[1].substring(1,bufStrings[1].length()-2);

        return result;
    }
    public static boolean isNumeric(String str)
    {
        NumberFormat formatter = NumberFormat.getInstance();
        ParsePosition pos = new ParsePosition(0);
        formatter.parse(str, pos);
        return str.length() == pos.getIndex();
    }

}
