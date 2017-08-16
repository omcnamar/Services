package com.olegsagenadatrytwo.services;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.olegsagenadatrytwo.services.services.MyMusicService;


public class MusicPlayerActivity extends AppCompatActivity {

    private Intent musicIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
    }

    public void play(View view) {
        if(musicIntent == null) {
            musicIntent = new Intent(this, MyMusicService.class);
            musicIntent.setAction("forGround");
            startService(musicIntent);
        }
    }

}
