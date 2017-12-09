package com.borscha.micka.playgroundproject.Activities.Activities;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.borscha.micka.playgroundproject.R;

public class AddBeaconActivity extends AppCompatActivity {

    ImageView imageView;
    Button button , sendDataButton;
    private static final int PICK_IMAGE =100;
    Uri imageUrl;
    EditText childName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_beacon);
        Intent intent = getIntent();
        String test = intent.getStringExtra("qrCodeValue");
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
                //loadDataTOServer();
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
}
