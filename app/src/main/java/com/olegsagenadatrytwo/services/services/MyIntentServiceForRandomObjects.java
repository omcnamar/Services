package com.olegsagenadatrytwo.services.services;

import android.app.IntentService;
import android.content.Intent;

import com.olegsagenadatrytwo.services.TV;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Random;


public class MyIntentServiceForRandomObjects extends IntentService {


    public MyIntentServiceForRandomObjects() {
        super("MyIntentServiceForRandomObjects");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        switch (intent.getAction()){
            case "generateRandomListOfTVS":
                generateRandomTVList();
                break;

        }
    }

    private void generateRandomTVList() {

        ArrayList<TV> list = new ArrayList<>();
        Random random = new Random();
        for(int i = 0; i < random.nextInt(100); i++){
            TV tv = new TV(new BigInteger(130, random).toString(5),
                    new BigInteger(130, random).toString(5),
                    new BigInteger(130, random).toString(5),
                    new BigInteger(130, random).toString(5));
            list.add(tv);
        }
        Intent intent = new Intent();
        intent.setAction("receivedList");
        intent.putExtra("TV'S", list);
        sendBroadcast(intent);
    }

}
