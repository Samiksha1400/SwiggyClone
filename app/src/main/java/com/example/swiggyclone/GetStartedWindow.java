package com.example.swiggyclone;

import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.VideoView;

public class GetStartedWindow extends AppCompatActivity
{
    VideoView bgvideo;
    TextView txt_food,txt_instamart,txt_dineout;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_started_window);
        bgvideo = findViewById(R.id.bgvideo);
        txt_food = findViewById(R.id.txt_food);
        txt_instamart = findViewById(R.id.txt_instamart);
        txt_dineout = findViewById(R.id.txt_dineout);

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

    }

    public void getStarted(View view)
    {
        startActivity(new Intent(GetStartedWindow.this, MobileNoVerification.class));
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