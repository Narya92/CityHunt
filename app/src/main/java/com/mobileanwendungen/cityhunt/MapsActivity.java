package com.mobileanwendungen.cityhunt;

import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.mobileanwendungen.cityhunt.model.Sight;
import com.mobileanwendungen.cityhunt.model.SightList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;


public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener, GoogleMap.OnMyLocationChangeListener, GoogleMap.OnMapClickListener {

    Map<Marker, Sight> markerSightMap = new HashMap<>();
    Marker activatedMarker = null;
    Marker ownLocationMarker = null;
    GoogleMap googleMaps = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        setContentView(R.layout.activity_maps);
        setTitle(getResources().getString(R.string.title_activity_maps));

        MapFragment mapFragment = (MapFragment) getFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.maps_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.sammlungs_liste:
                startActivity(new Intent(this, Sammlung.class));
                break;

        }
        return true;
    }


    @Override
    public void onMapReady(GoogleMap map) {

        googleMaps = map;

        for(Sight sight : AppDataExchange.listOfSights){

            MarkerOptions markerOps = new MarkerOptions()
                    .position(new LatLng(sight.getLatitude(), sight.getLongitude()))
                    .title(sight.getName());

            switch(sight.getType()){
                case MONUMENT:
                    markerOps.icon(BitmapDescriptorFactory.fromResource(R.drawable.logo_denkmal));
                    break;
                case MUSEUM:
                    markerOps.icon(BitmapDescriptorFactory.fromResource(R.drawable.logo_museum));
                    break;
                case CHURCH:
                    markerOps.icon(BitmapDescriptorFactory.fromResource(R.drawable.logo_kirche));
                    break;
            }

            Marker addedMarker = map.addMarker(markerOps);
            markerSightMap.put(addedMarker, sight);
        }

        map.setOnMarkerClickListener(this);
        map.setOnMyLocationChangeListener(this);
        map.setOnMapClickListener(this);
        map.setMyLocationEnabled(true);
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

    @Override
    public boolean onMarkerClick(Marker marker) {
        if(marker.equals(activatedMarker) && markerSightMap.containsKey(marker)) {
            Intent startMapsActivity = new Intent(this, Detail.class);
            AppDataExchange.currentSight = markerSightMap.get(marker);
            startActivity(startMapsActivity);
            return true;
        }else{
            activatedMarker = marker;
            if(googleMaps.getCameraPosition().zoom < 11) {
                googleMaps.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(marker.getPosition()).zoom(14).build()));
            }
            return false;
        }
    }

    @Override
    public void onMyLocationChange(Location location) {

        LatLng position = new LatLng(location.getLatitude(), location.getLongitude());

        if(ownLocationMarker == null){
            ownLocationMarker = googleMaps.addMarker(new MarkerOptions()
                    .position(position)
                    .title(getResources().getString(R.string.ownLocationMarkerText))
                    .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_BLUE))
            );
            googleMaps.moveCamera(CameraUpdateFactory.newCameraPosition(new CameraPosition.Builder().target(position).zoom(14).build()));
        }else {
            ownLocationMarker.setPosition(position);
        }
    }

    @Override
    public void onMapClick(LatLng latLng) {
        activatedMarker = null;
    }
}