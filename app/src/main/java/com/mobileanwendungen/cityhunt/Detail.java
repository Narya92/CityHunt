package com.mobileanwendungen.cityhunt;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.InputMismatchException;

public class Detail extends CityHuntActivity {

    private File kameraAufnahmeDatei = null;
    private static int REQUEST_IMAGE_CAPTURE = 42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        setTitle(AppDataExchange.currentSight.getName());

        TextView taskText = (TextView)findViewById(R.id.taskText);
        taskText.setText(AppDataExchange.currentSight.getTaskText());

        EditText taskAnswer = (EditText)findViewById(R.id.taskAnswer);
        taskAnswer.setText(AppDataExchange.currentSight.getTaskAwnser());

        EditText creationAgeText = (EditText)findViewById(R.id.creationAgeText);
        creationAgeText.setText(AppDataExchange.currentSight.getCreationAge());

        EditText addressText = (EditText) findViewById(R.id.addressText);
        addressText.setText(AppDataExchange.currentSight.getAddress());

        RelativeLayout detailLayout = (RelativeLayout) findViewById(R.id.detailLayout);
        detailLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                v.requestFocus();
                InputMethodManager imm = (InputMethodManager) v.getContext()
                        .getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                return false;
            }
        });
        
        ImageButton kameraButton = (ImageButton) findViewById(R.id.kameraButton);




        if (AppDataExchange.currentSight.getPhotos().length > 0) {
            GridView photoGrid = (GridView)findViewById(R.id.thumbnailGrid);
            ImageAdapter imgAdapter = new ImageAdapter(this, AppDataExchange.currentSight.getPhotos());
            photoGrid.setAdapter(imgAdapter);
        }


        kameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                try {
                    kameraAufnahmeDatei = createImageFile();
                    intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(kameraAufnahmeDatei));
                    startActivityForResult(intent,REQUEST_IMAGE_CAPTURE);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            AppDataExchange.currentSight.addPhoto(kameraAufnahmeDatei.getAbsolutePath());
            startActivity(new Intent(this, Detail.class));
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "CityHunt_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        return image;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EditText taskAnswer = (EditText)findViewById(R.id.taskAnswer);
        AppDataExchange.currentSight.setTaskAwnser(taskAnswer.getText().toString());

        EditText creationAgeText = (EditText)findViewById(R.id.creationAgeText);
        AppDataExchange.currentSight.setCreationAge(creationAgeText.getText().toString());

        EditText addressText = (EditText) findViewById(R.id.addressText);
        AppDataExchange.currentSight.setAddress(addressText.getText().toString());
    }
}
