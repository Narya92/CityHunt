package com.mobileanwendungen.cityhunt;


import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobileanwendungen.cityhunt.gui.MapsActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class SammlungAdapter extends ArrayAdapter<JSONObject> {
    private final Context context;
    private final JSONObject[] values;

    public SammlungAdapter(Context context, JSONObject[] marker) {
        super(context, R.layout.sammlung_liste, marker);
        this.context = context;
        Log.e("Baka", "Blyat");
        values = marker;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.sammlung_liste, parent, false);
        TextView textView = (TextView) rowView.findViewById(R.id.sammlungName);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.sammlungLogo);

        try {
            switch (values[position].getString("type")){
                case "Denkmal":
                    imageView.setImageResource(R.drawable.logo_denkmal);
                    break;
                case "Kirche":
                    imageView.setImageResource(R.drawable.logo_kirche);
                    break;
                case "Museum":
                    imageView.setImageResource(R.drawable.logo_museum);
                    break;
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        try {
            textView.setText(values[position].getString("title"));
        } catch (JSONException e) {
            e.printStackTrace();
        }




//        if (s.equals("WindowsMobile")) {
//            imageView.setImageResource(R.drawable.windowsmobile_logo);
//        } else if (s.equals("iOS")) {
//            imageView.setImageResource(R.drawable.ios_logo);
//        } else if (s.equals("Blackberry")) {
//            imageView.setImageResource(R.drawable.blackberry_logo);
//        } else {
//            imageView.setImageResource(R.drawable.android_logo);
//        }
    return rowView;
    }
}
