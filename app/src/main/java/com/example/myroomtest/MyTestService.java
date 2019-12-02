package com.example.myroomtest;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import java.util.Random;

public class MyTestService extends IntentService {
    public MyTestService() {
        super("test-service");
    }
/*
    @Override
    public void onCreate() {
        super.onCreate();
        // if Context is needed  call getApplicationContext() here
    }
*/
    @Override
    protected void onHandleIntent(Intent intent) {
	// This describes what will happen when service is triggered
        ClimateRoomDatabase appDb = ClimateRoomDatabase.getInstance(getApplicationContext());
        ClimateItem item = new ClimateItem();
        Random r = new Random();
        item.measureTime = System.currentTimeMillis();
        item.Pressure = r.nextInt(10) + 10;
        item.tempBattery = r.nextInt(30) + 10;
        item.tempAir = r.nextInt(10) + 18;
        appDb.climateDao().insert(item);

        Log.i("MyTestService", "Service running");
    }

}

