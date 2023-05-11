package com.example.picture_storing_app;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button camerabtn, gallerybtn;
    public Intent camera_intent, gallery_intent;
    int camera_request = 1;
    String IMAGES_FOLDER_NAME = "IMAGES";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        camerabtn = findViewById(R.id.camerabtn);
        gallerybtn = findViewById(R.id.gallerybtn);

        camerabtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                camera_intent = new Intent("android.media.action.IMAGE_CAPTURE");
                startActivityForResult(camera_intent,camera_request);

            }
        });

        gallerybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gallery_intent = new Intent(MainActivity.this,GalleryActivity.class);
                startActivity(gallery_intent);
            }
        });
    }

    // # For line 34
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == camera_request){
            if(camera_request == Activity.RESULT_OK){
                Bitmap image = (Bitmap) data.getExtras().get("data");
                try {
                    if(save_image(image)){
                        Toast.makeText(this, " Image saved! ", Toast.LENGTH_SHORT).show(); }
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public boolean save_image(Bitmap bitmap) throws IOException {
        boolean saved = false;
        OutputStream fos;

        //Unique Names for every Image
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String name = "Image_" + timestamp;

        ContentResolver resolver = this.getContentResolver();
        ContentValues values = new ContentValues();
        values.put(MediaStore.MediaColumns.DISPLAY_NAME,name);
        values.put(MediaStore.MediaColumns.MIME_TYPE,"image/png");
        values.put(MediaStore.MediaColumns.RELATIVE_PATH,"DCIM/"+IMAGES_FOLDER_NAME);

        Uri imageuri = resolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,values);
        fos = resolver.openOutputStream(imageuri);

        saved = bitmap.compress(Bitmap.CompressFormat.PNG,100, fos);
        fos.flush();
        fos.close();

        return saved;
    }
}