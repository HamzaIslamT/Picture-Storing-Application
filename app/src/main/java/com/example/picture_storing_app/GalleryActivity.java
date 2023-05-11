package com.example.picture_storing_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.GridView;

public class GalleryActivity extends AppCompatActivity {

    GridView image_gv;
    MyAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);

        image_gv = findViewById(R.id.image_gv);
        adapter = new MyAdapter(this);

        image_gv.setAdapter(adapter);

    }
}