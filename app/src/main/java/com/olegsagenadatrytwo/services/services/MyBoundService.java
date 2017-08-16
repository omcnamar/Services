package com.olegsagenadatrytwo.services.services;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.util.Log;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;

public class MyBoundService extends Service {

    private static final String TAG = "MyBoundService";
    public IBinder iBinder = new MyBinder();
    public int passed;

    public MyBoundService() {
        Log.d(TAG, "MyBoundService: ");
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public IBinder onBind(Intent intent) {
        passed = intent.getIntExtra("integer", 0);
        Log.d(TAG, "onBind: ");
        return iBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onUnbind: ");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy: ");
    }

    //inner class MyBinder
    public class MyBinder extends Binder {

        public MyBoundService getService(){
            return MyBoundService.this;
        }
    }

    public ArrayList<String> getRandomArray(){

        Random random = new Random();
        ArrayList<String> list = new ArrayList<>();
        for(int i = 0; i < passed; i++){
            list.add(new BigInteger(130, random).toString(32));
        }
        return list;
    }

}
