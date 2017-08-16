package com.olegsagenadatrytwo.services.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;


//creates worker thread on the main thread ... runs on different thread
public class MyIntentService extends IntentService {

    public static final String TAG = "MyIntentService";

    public MyIntentService() {
        super("MyIntentService");
        Log.d(TAG, "MyIntentService: ");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    @Override
    protected void onHandleIntent(Intent intent) {


        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        switch (intent.getAction()){
            case "getRepo":
                Log.d(TAG, "onHandleIntent: " + intent.getStringExtra("data"));
                break;
            case "getProfile":
                Log.d(TAG, "onHandleIntent: " + intent.getStringExtra("data"));
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

}
