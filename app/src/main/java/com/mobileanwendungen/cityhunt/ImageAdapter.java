package com.mobileanwendungen.cityhunt;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

/**
 * Created by Narya on 29.12.2015.
 */
public class ImageAdapter extends BaseAdapter {
    private Context mContext;
    private String[] uriArray;
    public ImageAdapter(Context c, String[] u) {
        mContext = c;
        uriArray = u;
    }

    public int getCount() {
        return uriArray.length;
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
            imageView.setPadding(8, 8, 8, 8);
        } else {
            imageView = (ImageView) convertView;
        }

        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inSampleSize = 2;
        Bitmap btmp = BitmapFactory.decodeFile(uriArray[position], opts);
        btmp = Bitmap.createScaledBitmap(btmp, 50, 50, false);

        imageView.setImageBitmap(btmp);
        return imageView;
    }

}