package com.mobileanwendungen.cityhunt;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mobileanwendungen.cityhunt.model.Sight;
import com.mobileanwendungen.cityhunt.model.SightList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;


public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppDataExchange.listOfSights = SightList.loadFromFile(this, "sightData");

        setContentView(R.layout.activity_maps);
        setTitle(getResources().getString(R.string.title_activity_maps));

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {

        for(Sight sight : AppDataExchange.listOfSights){
            map.addMarker(new MarkerOptions()
                    .position(new LatLng(sight.getLatitude(), sight.getLongitude()))
                    .title(sight.getName()));
        }
    }

    static public JSONArray loadMarkertoList(Context derKontext) {
//        List<Marker> markerlist = new ArrayList<Marker>();
        try {
            Log.e("Baka", "Blyat^2");
            JSONObject obj = new JSONObject(loadJSONFromAsset(derKontext));

            JSONArray m_jArry = obj.getJSONArray("marker");
            return m_jArry;

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    static public String loadJSONFromAsset(Context derKontext) {
        String json = null;
        try {
            InputStream is = derKontext.getResources().openRawResource(R.raw.marker) ;
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
        return json;
    }
}