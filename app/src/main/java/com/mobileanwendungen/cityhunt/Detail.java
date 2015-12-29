package com.mobileanwendungen.cityhunt;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Detail extends AppCompatActivity {

    private File kameraAufnahmeDatei = null;
    private static int REQUEST_IMAGE_CAPTURE = 42;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        String title = getIntent().getStringExtra("title");
        if(title != null) {
            setTitle(title);
        }

        ImageButton kameraButton = (ImageButton) findViewById(R.id.kameraButton);

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
}
