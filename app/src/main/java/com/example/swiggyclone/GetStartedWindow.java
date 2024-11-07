package com.example.swiggyclone;

import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.VideoView;

public class GetStartedWindow extends AppCompatActivity
{
    VideoView bgvideo;
    TextView txt_food,txt_instamart,txt_dineout,txt_onFocusTxtDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        setContentView(R.layout.activity_get_started_window);
        bgvideo = findViewById(R.id.bgvideo);
        txt_food = findViewById(R.id.txt_food);
        txt_instamart = findViewById(R.id.txt_instamart);
        txt_dineout = findViewById(R.id.txt_dineout);
        txt_onFocusTxtDisplay = findViewById(R.id.txt_onFocusTxtDisplay);


        //Background Video
        String path = "android.resource://com.example.swiggyclone/"+R.raw.foodvideo;
        Uri uri= Uri.parse(path);
        bgvideo.setVideoURI(uri);
        bgvideo.start();
        bgvideo.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.setLooping(true);
            }
        });

        //moveToNext();

    }

    /*private void moveToNext()
    {
        txt_food.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                txt_food.setTextColor(Color.parseColor("#FFFFFFFF"));
                txt_onFocusTxtDisplay.setText("Order From Top Restaurants");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                txt_instamart.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        txt_instamart.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                txt_instamart.setTextColor(Color.parseColor("#FFFFFFFF"));
                txt_onFocusTxtDisplay.setText("Order From Top Restaurants");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                txt_dineout.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });

        txt_dineout.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2)
            {
                txt_dineout.setTextColor(Color.parseColor("#FFFFFFFF"));
                txt_onFocusTxtDisplay.setText("Order From Top Restaurants");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                txt_food.requestFocus();
            }

            @Override
            public void afterTextChanged(Editable editable) {
            }
        });
    }*/

    public void getStarted(View view)
    {
        startActivity(new Intent(GetStartedWindow.this, Homepage.class));
    }

    @Override
    protected void onResume()
    {
        bgvideo.resume();
        super.onResume();
    }

    @Override
    protected void onPause()
    {
        bgvideo.suspend();
        super.onPause();
    }

    @Override
    protected void onDestroy()
    {
        bgvideo.stopPlayback();
        super.onDestroy();
    }
}