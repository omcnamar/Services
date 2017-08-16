package com.olegsagenadatrytwo.services;

import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.olegsagenadatrytwo.services.services.MyBoundService;
import com.olegsagenadatrytwo.services.services.MyIntentService;
import com.olegsagenadatrytwo.services.services.MyJobService;
import com.olegsagenadatrytwo.services.services.MyNormalService;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    MyBoundService myBoundService;
    private boolean isConnected = false;
    private EditText etNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //bind Edit Text
        etNumber = (EditText) findViewById(R.id.etNumber);

    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void startService(View view) {
        Intent normalIntent = new Intent(this, MyNormalService.class);
        Intent intentService = new Intent(this, MyIntentService.class);

        Intent boundIntent = new Intent(this, MyBoundService.class);

        switch (view.getId()){

            case R.id.btnStartNormalService:
                normalIntent.putExtra("data", "this is normal service");
                startService(normalIntent);
                break;

            case R.id.btnStopNormalService:
                stopService(normalIntent);
                break;

            case R.id.btnStartIntentService:
                intentService.putExtra("data", "this is intent service repo");
                intentService.setAction("getRepo");
                startService(intentService);
                break;

            case R.id.btnBindService:
                Log.d(TAG, "startService: " + "clicked");
                bindService(boundIntent, serviceConnection, Context.BIND_AUTO_CREATE);
                break;

            case R.id.btnUnbindService:
                if(isConnected) {
                    unbindService(serviceConnection);
                    isConnected = false;
                }
                break;
            case R.id.btnGoToSecond:
                Intent second = new Intent(this, SecondActivity.class);
                second.putExtra("integer", etNumber.getText().toString());
                startActivity(second);
                break;
            case R.id.btnGoToMusic:
                Intent music = new Intent(this, MusicPlayerActivity.class);
                startActivity(music);
                break;
            case R.id.btnScheduleService:

                Log.d(TAG, "startService: ");
                ComponentName serviceComponent = new ComponentName(MainActivity.this, MyJobService.class);
                JobInfo.Builder jobInfo = new JobInfo.Builder(0, serviceComponent);

                jobInfo.setMinimumLatency(1000);
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.M) {
                    JobScheduler jobScheduler = (JobScheduler) getSystemService(Context.JOB_SCHEDULER_SERVICE);
                    jobScheduler.schedule(jobInfo.build());
                    Log.d(TAG, "startService: " + "inside if");
                }else{
                    Log.d(TAG, "startService: " +  " outside if");
                }

                break;
            case R.id.btnGoToRandomObject:
                Intent randObj = new Intent(this, RandomObjectsActivityUsingIntentService.class);
                startActivity(randObj);
                break;
        }
    }

    ServiceConnection serviceConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.d(TAG, "onServiceConnected: ");
            MyBoundService.MyBinder myBinder = (MyBoundService.MyBinder) iBinder;
            myBoundService = myBinder.getService();
            isConnected = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            Log.d(TAG, "onServiceDisconnected: ");
            isConnected = false;
        }
    };
}
