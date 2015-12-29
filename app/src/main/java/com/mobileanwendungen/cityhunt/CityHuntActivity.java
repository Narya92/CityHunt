package com.mobileanwendungen.cityhunt;

import android.support.v7.app.AppCompatActivity;

/**
 * Created by Narya on 29.12.2015.
 */
public class CityHuntActivity extends AppCompatActivity {

    @Override
    protected void onPause() {
        super.onPause();
        AppDataExchange.listOfSights.saveToFile(this, "data");
    }

    @Override
    protected void onStop() {
        super.onStop();
        AppDataExchange.listOfSights.saveToFile(this, "data");
    }


}
