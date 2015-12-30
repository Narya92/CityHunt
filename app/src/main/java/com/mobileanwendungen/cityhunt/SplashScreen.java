package com.mobileanwendungen.cityhunt;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.os.Handler;

import com.mobileanwendungen.cityhunt.model.SightList;

public class SplashScreen extends Activity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent startMapsActivity = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(startMapsActivity);
                finish();
            }
        }, 2000);

        AppDataExchange.listOfSights = SightList.loadFromFile(this, "data");
    }

}
