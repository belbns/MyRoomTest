package com.example.myroomtest;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import java.util.Random;

import static androidx.core.app.NotificationCompat.PRIORITY_MIN;

public class MyTestService extends IntentService {

    private static final int ID_SERVICE = 101;

    public MyTestService() {
        super("test-service");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        super.onStartCommand(intent, flags, startId);
        return START_STICKY;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // do stuff like register for BroadcastReceiver, etc.

        // Create the Foreground Service
        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        String channelId =
                Build.VERSION.SDK_INT >= Build.VERSION_CODES.O ? createNotificationChannel(notificationManager) : "";
        NotificationCompat.Builder notificationBuilder =
                new NotificationCompat.Builder(this, channelId);
        Notification notification = notificationBuilder.setOngoing(true)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setPriority(PRIORITY_MIN)
                .setCategory(NotificationCompat.CATEGORY_SERVICE)
                .build();

        startForeground(ID_SERVICE, notification);
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private String createNotificationChannel(NotificationManager notificationManager){
        String channelId = "my_service_channelid";
        String channelName = "My Foreground Service";
        NotificationChannel channel = new NotificationChannel(channelId, channelName,
                NotificationManager.IMPORTANCE_HIGH);
        // omitted the LED color
        channel.setImportance(NotificationManager.IMPORTANCE_NONE);
        channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);
        notificationManager.createNotificationChannel(channel);
        return channelId;
    }


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

