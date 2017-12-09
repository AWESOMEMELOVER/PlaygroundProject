package com.borscha.micka.playgroundproject.Activities.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

import com.borscha.micka.playgroundproject.Activities.Activities.RecycleViews.PlaygroundRecycleView.ListItem;
import com.borscha.micka.playgroundproject.R;
import com.squareup.picasso.Picasso;

public class EditBeaconActivity extends AppCompatActivity {
    ImageView profile_pic;
    EditText profile_name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_beacon);
        profile_pic = (ImageView)findViewById(R.id.user_profile_photo);
        profile_name = (EditText) findViewById(R.id.user_profile_short_bio);

        ListItem currentBeacon = getIntent().getExtras().getParcelable("object");
        Picasso.with(getApplicationContext()).load(currentBeacon.getImageUrl()).into(profile_pic);
        profile_name.setText(currentBeacon.getName());
    }

}
