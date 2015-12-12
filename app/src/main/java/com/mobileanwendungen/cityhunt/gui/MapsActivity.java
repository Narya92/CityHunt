package com.mobileanwendungen.cityhunt.gui;

import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mobileanwendungen.cityhunt.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;


public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        map.addMarker(new MarkerOptions()
                .position(new LatLng(52.16071, 10.48442))
                .title("Hello world"));

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