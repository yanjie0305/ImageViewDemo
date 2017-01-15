package com.example.administrator.imageviewdemo;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView img;
    String path = "http://img.dianfu.net/img/20151223/2aecc4396687179dba44fb208b397418.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        img = (ImageView) findViewById(R.id.img);
    }

    public void getPic(View view) {
        Bitmap bitmap = PicCompressUtils.compressPicInSize(getWindowManager(),
                path);
        img.setImageBitmap(bitmap);
    }
}
