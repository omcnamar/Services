package com.olegsagenadatrytwo.services.services;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.RemoteViews;

import com.olegsagenadatrytwo.services.MainActivity;
import com.olegsagenadatrytwo.services.R;


public class MyMusicService extends Service {

    //
    public static final String TAG = "MyMusicService";
    //media player
    private MediaPlayer player;
    //current position
    private int positionOfSong;

    //constructor
    public MyMusicService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //initialize
        player = MediaPlayer.create(this, R.raw.skillet);
        positionOfSong = 0;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand: " + "top");
        switch (intent.getAction()) {
            case "forGround":
                //make layout for the notification
                RemoteViews contentView = new RemoteViews(getPackageName(), R.layout.notification_layout_music_player);
                //call the method to set up the onClicks for the buttons in the notification
                setUpOnClickForButtons(contentView);
                NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                        .setSmallIcon(R.drawable.ic_music)
                        .setContent(contentView);

                Intent intent1 = new Intent(this, MainActivity.class);
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent1, 0);
                mBuilder.setContentIntent(pendingIntent);

                Notification notification = mBuilder.build();
                startForeground(1, notification);
                player.start();
                break;
            case "stopMusic":
                Log.d(TAG, "onStartCommand: " + " stop music");
                player.stop();
                break;
        }

        return START_STICKY; //super.onStartCommand(intent, flags, startId);
    }

    //this method will set up the onClick for the buttons in the notification
    public void setUpOnClickForButtons(RemoteViews contentView){
        //onClick for Play Button
        //Create an intent filter for broadcastReceiver
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("stopMusic");
        intentFilter.addAction("playMusic");
        intentFilter.addAction("pauseMusic");
        intentFilter.addAction("killService");
        //register this service to a broadcast receiver to listen for the buttons
        this.registerReceiver(new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                switch (intent.getAction()) {
                    case "playMusic":
                        if (player == null) {
                            player = MediaPlayer.create(getApplicationContext(), R.raw.skillet);
                            positionOfSong = 0;
                            player.start();
                        } else if (!player.isPlaying()) {
                            player.seekTo(positionOfSong);
                            player.start();
                        }
                        break;
                    case "pauseMusic":
                        if (player != null) {
                            player.pause();
                            positionOfSong = player.getCurrentPosition();
                        }
                        break;
                    case "stopMusic":
                        player.stop();
                        player = null;
                        break;
                    case "killService":
                        unregisterReceiver(this);
                        player.stop();
                        player = null;
                        stopSelf();
                        break;
                }
            }
        }, intentFilter);
        //pending intent for the stop music button
        PendingIntent pendingStop = PendingIntent.getBroadcast(this, 0, new Intent("stopMusic"), 0);
        //pending intent for the play music button
        PendingIntent pendingPlay = PendingIntent.getBroadcast(this, 0, new Intent("playMusic"), 0);
        //pending intent for the pause music button
        PendingIntent pendingPause = PendingIntent.getBroadcast(this, 0, new Intent("pauseMusic"), 0);
        //pending intent for the kill service
        PendingIntent pendingKillService = PendingIntent.getBroadcast(this, 0, new Intent("killService"), 0);

        contentView.setOnClickPendingIntent(R.id.btnStop, pendingStop);
        contentView.setOnClickPendingIntent(R.id.btnPause, pendingPause);
        contentView.setOnClickPendingIntent(R.id.btnPlay, pendingPlay);
        contentView.setOnClickPendingIntent(R.id.btnKillService, pendingKillService);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }



}
