package com.example.picture_storing_app;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import java.io.File;

public class MyAdapter extends BaseAdapter {

    Context context;
    String path = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DCIM) + "/" + "IMAGES";
    File[] files;

    public MyAdapter(Context context) {
        this.context = context;
        File DCIM_IMAGES = new File(path);
        files = DCIM_IMAGES.listFiles();
    }

    @Override
    public int getCount() {
        return files.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View item = inflater.inflate(R.layout.gridview_items, null);
        ImageView imageView = item.findViewById(R.id.img);

        Bitmap image = BitmapFactory.decodeFile(files[i].getPath());
        imageView.setImageBitmap(image);

        return  item;

    }
}
