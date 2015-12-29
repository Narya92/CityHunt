package com.mobileanwendungen.cityhunt;


import android.content.Context;
import android.content.Intent;
import android.database.DataSetObserver;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.mobileanwendungen.cityhunt.model.Sight;
import com.mobileanwendungen.cityhunt.model.SightList;

import org.json.JSONException;
import org.json.JSONObject;

public class SammlungAdapter implements ListAdapter {
    private final Context context;
    private final SightList values;

    public SammlungAdapter(Context context, SightList marker) {
           // super(context, R.layout.sammlung_liste, marker);
        this.context = context;
        Log.e("Baka", "Blyat");
        values = marker;
    }

    @Override
    public void registerDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public void unregisterDataSetObserver(DataSetObserver observer) {

    }

    @Override
    public int getCount() {

        return  values.size();
    }

    @Override
    public Sight getItem(int position) {
        return values.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.sammlung_liste, parent, false);

        TextView textView = (TextView) rowView.findViewById(R.id.sammlungName);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.sammlungLogo);


            switch (getItem(position).getType()){
                case MONUMENT:
                    imageView.setImageResource(R.drawable.logo_denkmal);
                    break;
                case CHURCH:
                    imageView.setImageResource(R.drawable.logo_kirche);
                    break;
                case MUSEUM:
                    imageView.setImageResource(R.drawable.logo_museum);
                    break;
            }


        String name = getItem(position).getName();

        textView.setText(name);

        final Sight currentSight = getItem(position);
        rowView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startMapsActivity = new Intent(context, Detail.class);
                AppDataExchange.currentSight = currentSight;
                context.startActivity(startMapsActivity);
            }
        });


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

    @Override
    public int getItemViewType(int position) {
        return 1;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public boolean isEmpty() {
        return values.isEmpty();
    }

    @Override
    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }
}
