package com.akhil.galaria;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;


import com.bumptech.glide.Glide;

public class FullScreenImageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_screen_image);

        ImageView fullScreeImageView = (ImageView) findViewById(R.id.fullScreenImageView);

        Intent callingActivityIntent = getIntent();
        Bundle extras = getIntent().getExtras();






        byte[] b = extras.getByteArray("picture");
        Bitmap bmp = BitmapFactory.decodeByteArray(b, 0, b.length);
//        ImageView image = (ImageView) findViewById(R.id.imageView1);
        fullScreeImageView.setImageBitmap(bmp);




//        if (callingActivityIntent != null){
//            Uri imageUri = callingActivityIntent.getData();
//            Log.d("ID", "Success");
//            if(imageUri != null && fullScreeImageView != null){
//                Glide.with(this).load(imageUri).into(fullScreeImageView);
//            }
//        }
    }
}
