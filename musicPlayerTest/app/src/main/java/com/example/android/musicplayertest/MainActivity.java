package com.example.android.musicplayertest;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.media.audiofx.*;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button play = (Button) findViewById(R.id.button);
        play.setOnClickListener(new View.OnClickListener()){
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity,this,"Play",Toast.LENGTH_SHORT).show();
            }
        });

        Button pause = (Button) findViewById(R.id.button2);
        play.setOnClickListener(new View.OnClickListener()){
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity,this,"Pause",Toast.LENGTH_SHORT).show();
            }
        });
    }

    MediaPlayer mediaPlayer = MediaPlayer.create(this, R.raw.sample);

    public void play(View view) {

        mediaPlayer.start();
    }

    public void pause(View view) {
        mediaPlayer.pause();

    }
}
