package com.example.daria.translate;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.daria.translate.Tasks.DownloadImagesTask;
import com.example.daria.translate.Tasks.TranslatorTask;

import java.util.ArrayList;


public class ResultActivity extends Activity {
    TranslatorTask translate;
    DownloadImagesTask download;
    public int pos = 0;
    public ImageView image;
    ArrayList<Bitmap> images;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Intent i = getIntent();
        String query = i.getStringExtra("query");
        image = (ImageView) findViewById(R.id.imageView);

        download = new DownloadImagesTask();
        download.execute(query);

        translate = new TranslatorTask(query);
        translate.execute();
        ((TextView) findViewById(R.id.word_query)).setText(query);
        try {
            ((TextView) findViewById(R.id.translation)).setText(translate.get());
        } catch (Exception e) {
            ((TextView) findViewById(R.id.translation)).setText("Error");
        }

        try {
            images = download.get();
            image.setImageBitmap(images.get(pos));
            image.setOnClickListener(new ImageView.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pos = (pos + 1) % 10;
                    image.setImageBitmap(images.get(pos));
                }
            });

        } catch (Exception e) {
            image.setImageResource(R.drawable.error);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)  {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            Intent i = new Intent(this, SearchActivity.class);
            startActivity(i);
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
