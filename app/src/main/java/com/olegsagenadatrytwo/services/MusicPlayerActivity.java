package com.olegsagenadatrytwo.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.olegsagenadatrytwo.services.services.MyBoundService;

public class MusicPlayerActivity extends AppCompatActivity {

    MyBoundService myBoundService;

    MediaPlayer player;
    int pause;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music_player);
    }

    public void play(View view) {
        if(player == null) {
            Intent boundIntent = new Intent(this, MyBoundService.class);
            bindService(boundIntent, serviceConnection, Context.BIND_AUTO_CREATE);
        }else if(!player.isPlaying()){
            player.seekTo(pause);
            player.start();
        }
    }

    public void pause(View view) {
        if(player != null) {
            player.pause();
            pause = player.getCurrentPosition();
        }

    }

    public void stop(View view) {
        player.stop();
        player = null;
        unbindService(serviceConnection);
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            MyBoundService.MyBinder myBinder = (MyBoundService.MyBinder) iBinder;
            myBoundService = myBinder.getService();
            player = myBoundService.playMusic();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
        }
    };
}
