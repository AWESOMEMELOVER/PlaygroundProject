package com.borscha.micka.playgroundproject.Activities.Activities;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.borscha.micka.playgroundproject.Activities.Activities.Entities.GlobalUser;
import com.borscha.micka.playgroundproject.Activities.Activities.network.VolleySingleton;
import com.borscha.micka.playgroundproject.R;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class AddBeaconActivity extends AppCompatActivity {

    ImageView imageView;
    Button button , sendDataButton;
    private static final int PICK_IMAGE =100;
    Uri imageUrl;
    EditText childName;
    String URL = "http://unix.trosha.dev.lumination.com.ua/user/";
    String URL2 = "/beacon/";
    GlobalUser globalUser;
    String userId,beaconId,vChildName;
    String testId = "1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_beacon);
        Intent intent = getIntent();
        beaconId = intent.getStringExtra("qrCodeValue");
        userId = ((GlobalUser)this.getApplicationContext()).getUserId();
        imageView = (ImageView) findViewById(R.id.imageView3);
        button = (Button) findViewById(R.id.button2);
        sendDataButton = (Button) findViewById(R.id.sendDataButton);
        childName = (EditText) findViewById(R.id.editText2);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery();
            }
        });
        sendDataButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                vChildName = childName.getText().toString();
                loadDataToServer();
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void openGallery(){
        Intent gallery = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
        startActivityForResult(gallery,PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK && requestCode == PICK_IMAGE){
            imageUrl = data.getData();
            imageView.setImageURI(imageUrl);
        }
    }
    private void loadDataToServer(){
        StringRequest myReq = new StringRequest(Request.Method.POST, URL + testId + URL2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.wtf("Responce is", response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.wtf("Responce is", error);
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                HashMap<String,String> params = new HashMap<>();
                     params.put("name",vChildName);
                     params.put("beaconId",beaconId);
                    //params.put("imageUrl",imageUrl.toString());
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

}
