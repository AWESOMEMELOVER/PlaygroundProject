package com.borscha.micka.playgroundproject.Activities.Activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.borscha.micka.playgroundproject.Activities.Activities.Fragments.BeaconsFragment;
import com.borscha.micka.playgroundproject.Activities.Activities.Fragments.PlayfroundFragment;
import com.borscha.micka.playgroundproject.Activities.Activities.Fragments.SettingsFragment;
import com.borscha.micka.playgroundproject.Activities.Activities.Fragments.TrackFragment;
import com.borscha.micka.playgroundproject.R;

public class MainActivity extends AppCompatActivity {



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


        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container,new PlayfroundFragment()).commit();
    }

}
