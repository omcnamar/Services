package com.olegsagenadatrytwo.services.services;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

@RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
public class MyJobService extends JobService {
    public static final String TAG = "MyJobService";
    @Override
    public boolean onStartJob(JobParameters params) {

        //simple call to start the service
        Log.d(TAG, "onStartJob: ");
        Intent intent = new Intent(getApplicationContext(), MyScheduleServices.class);
        getApplicationContext().startService(intent);

        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {

        //if you return true onStopJob the job will restart
        return false;
    }
}
