package com.mobileanwendungen.cityhunt;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.net.Uri;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.IOException;

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

    public String getItem(int position) {
        return uriArray[position];
    }

    public long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    public View getView(final int position, View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // if it's not recycled, initialize some attributes
            imageView = new ImageView(mContext);
            //imageView.setLayoutParams(new GridView.LayoutParams(85, 85));
            imageView.setPadding(8, 8, 8, 8);
            imageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        } else {
            imageView = (ImageView) convertView;
        }

        imageView.setImageBitmap(getBitmapFromFile(getItem(position)));

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setDataAndType(Uri.parse("file://"+getItem(position)),"image/*");
                v.getContext().startActivity(intent);
            }
        });

        return imageView;
    }

    public static Bitmap getBitmapFromFile(String file){
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inSampleSize = 2;
        Bitmap btmp = BitmapFactory.decodeFile(file, opts);
        btmp = Bitmap.createScaledBitmap(btmp, 128, 128, false);

        try {
            Matrix rotationMatrix = new Matrix();
            ExifInterface jpegMetaData = new ExifInterface(file);
            switch(jpegMetaData.getAttributeInt(ExifInterface.TAG_ORIENTATION, 1)){
                case 3:
                    rotationMatrix.postRotate(180);
                    break;
                case 8:
                    rotationMatrix.postRotate(270);
                    break;
                case 6:
                    rotationMatrix.postRotate(90);
                    break;
            }
            btmp = Bitmap.createBitmap(btmp, 0, 0, 128, 128, rotationMatrix, true);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return btmp;
    }

}