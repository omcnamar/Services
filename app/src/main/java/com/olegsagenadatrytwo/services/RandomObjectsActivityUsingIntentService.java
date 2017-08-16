package com.olegsagenadatrytwo.services;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.olegsagenadatrytwo.services.services.MyIntentServiceForRandomObjects;

import java.util.ArrayList;

public class RandomObjectsActivityUsingIntentService extends AppCompatActivity {

    private MyDynamicReceiver myDynamicReceiver =  new MyDynamicReceiver();
    private IntentFilter intentFilter;
    private ArrayList<TV> TVList;
    private RecyclerView rvRecyclerVeiwTV;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.ItemAnimator itemAnimator;
    private AdapterRandomObjects adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random_objects_using_intent_service);
        TVList = new ArrayList<>();
        Intent intentService = new Intent(this, MyIntentServiceForRandomObjects.class);
        intentService.setAction("generateRandomListOfTVS");
        startService(intentService);

        rvRecyclerVeiwTV = (RecyclerView) findViewById(R.id.rvRecyclerViewTV);
        layoutManager = new LinearLayoutManager(this);
        itemAnimator = new DefaultItemAnimator();
        rvRecyclerVeiwTV.setLayoutManager(layoutManager);
        rvRecyclerVeiwTV.setItemAnimator(itemAnimator);
    }

    @Override
    protected void onStart() {
        super.onStart();
        intentFilter = new IntentFilter();
        intentFilter.addAction("receivedList");
        registerReceiver(myDynamicReceiver, intentFilter);
    }

    public class MyDynamicReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            switch (intent.getAction()){
                case "receivedList":
                    TVList = (ArrayList<TV>) intent.getSerializableExtra("TV'S");
                    adapter = new AdapterRandomObjects(TVList);
                    rvRecyclerVeiwTV.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
                    break;
            }
        }
    }

}
