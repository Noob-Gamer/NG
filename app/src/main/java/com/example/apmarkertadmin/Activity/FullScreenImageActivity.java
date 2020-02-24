package com.example.apmarkertadmin.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.ContactsContract;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.apmarkertadmin.R;

import java.io.File;

public class FullScreenImageActivity extends AppCompatActivity {
 private ImageView imgview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image);
        imgview = findViewById(R.id.FullImage);
        byte[] byteArray = getIntent().getByteArrayExtra("img");
       /* File root = Environment.getExternalStorageDirectory();
        Bitmap bMap = BitmapFactory.decodeFile(root+"/images/01.jpg");*/
        Bitmap bmp = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);
        imgview.setImageBitmap(bmp);
        //Intent intent = getIntent();
       /* Bitmap bitmap = (Bitmap) intent.getParcelableExtra("img");*/
        /*Bitmap bmp = (Bitmap) getIntent().getParcelableExtra("imagebitmap");
            if(imgview != null){
                Glide.with(this)
                        .load(bmp)
                        .into(imgview);
            }*/
        }
}
