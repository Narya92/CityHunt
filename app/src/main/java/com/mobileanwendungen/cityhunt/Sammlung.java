package com.mobileanwendungen.cityhunt;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Sammlung extends CityHuntActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sammlung);
        setTitle(getResources().getString(R.string.title_activity_sammlung));
        ListView sammlungsListe = (ListView) findViewById(R.id.listView);
        sammlungsListe.setAdapter(new SammlungAdapter(this, AppDataExchange.listOfSights));
    }
}
