package com.olegsagenadatrytwo.services;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.olegsagenadatrytwo.services.services.MyBoundService;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "Second Activity";
    MyBoundService myBoundService;
    private ArrayList<String> listFromMyBoundService;
    private RecyclerView rvRecyclerVeiw;
    private RecyclerView.LayoutManager layoutManager;
    private RecyclerView.ItemAnimator itemAnimator;
    private AdapterRandomList adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        Intent intent = getIntent();
        String passed = intent.getStringExtra("integer");
        int convertedPassed = Integer.parseInt(passed);

        //
        Intent boundIntent = new Intent(this, MyBoundService.class);
        boundIntent.putExtra("integer", convertedPassed);
        bindService(boundIntent, serviceConnection, Context.BIND_AUTO_CREATE);

        //Bind
        rvRecyclerVeiw = (RecyclerView) findViewById(R.id.rvRecyclerView);
        layoutManager = new LinearLayoutManager(this);
        itemAnimator = new DefaultItemAnimator();
        rvRecyclerVeiw.setLayoutManager(layoutManager);
        rvRecyclerVeiw.setItemAnimator(itemAnimator);


    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "onServiceConnected: ");
            MyBoundService.MyBinder myBinder = (MyBoundService.MyBinder) iBinder;
            myBoundService = myBinder.getService();
            listFromMyBoundService = myBoundService.getRandomArray();
            adapter = new AdapterRandomList(listFromMyBoundService);
            rvRecyclerVeiw.setAdapter(adapter);
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "onServiceDisconnected: ");
        }
    };
}
