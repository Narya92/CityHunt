package com.mobileanwendungen.cityhunt;

import android.content.Intent;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

public class Detail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        String title = getIntent().getStringExtra("title");
        setTitle(title);

        ImageButton kameraButton = (ImageButton) findViewById(R.id.kameraButton);

        kameraButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent itent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(itent,0);
            }
        });
    }
}
