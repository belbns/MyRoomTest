package com.example.myroomtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.job.JobInfo;
import android.app.job.JobScheduler;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import static androidx.room.Room.*;

public class MainActivity extends AppCompatActivity {

    private long currTime = System.currentTimeMillis();
    private int Pressure = 0;
    private int TempBattery = 0;
    private int TempAir = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

	    scheduleAlarm();


        final EditText editPressure = (EditText)findViewById(R.id.editPressure);
        editPressure.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //Pressure = Integer.parseInt(editPressure.getText().toString());
                try {
                    Pressure = Integer.parseInt(editPressure.getText().toString());
                } catch (NumberFormatException nfe) {
                    editPressure.setText(Integer.toString(Pressure));
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        final EditText editTbattery = (EditText)findViewById(R.id.editTbattery);
        editTbattery.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //TempBattery = Integer.parseInt(editTbattery.getText().toString());
                try {
                    TempBattery = Integer.parseInt(editTbattery.getText().toString());
                } catch (NumberFormatException nfe) {
                    editTbattery.setText(Integer.toString(TempBattery));
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        final EditText editTair = (EditText)findViewById(R.id.editTair);
        editTair.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //TempAir = Integer.parseInt(editTair.getText().toString());
                try {
                    TempAir = Integer.parseInt(editTair.getText().toString());
                } catch (NumberFormatException nfe) {
                    editTair.setText(Integer.toString(TempAir));
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        final Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClimateRoomDatabase appDb = ClimateRoomDatabase.getInstance(getApplicationContext());
        /*
            ClimateItem item = new ClimateItem();
            item.measureTime = System.currentTimeMillis();
            item.Pressure = Pressure;
            item.tempBattery = TempBattery;
            item.tempAir = TempAir;
            appDb.climateDao().insert(item);
        */
                List<ClimateItem> items = appDb.climateDao().getAll();

                StringBuilder builder = new StringBuilder();
                for (ClimateItem details : items) {
                    builder.append(details.measureTime).append(", ").append(details.tempAir).append(", ").append(details.tempBattery).append(", ").append(details.Pressure).append("\n");
                }
                TextView tv = findViewById(R.id.textViewDB);

                tv.setText(builder.toString());

                cancelAlarm();
            }
        });

    }

// Setup a recurring alarm every half hour
    public void scheduleAlarm() {
	    // Construct an intent that will execute the AlarmReceiver
	    Intent intent = new Intent(getApplicationContext(), MyAlarmReceiver.class);
	    // Create a PendingIntent to be triggered when the alarm goes off
	    final PendingIntent pIntent = PendingIntent.getBroadcast(this,
                MyAlarmReceiver.REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
	    // Setup periodic alarm every every half hour from this point onwards
	    long firstMillis = System.currentTimeMillis(); // alarm is set right away
	    AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
	    // First parameter is the type: ELAPSED_REALTIME, ELAPSED_REALTIME_WAKEUP, RTC_WAKEUP
	    // Interval can be INTERVAL_FIFTEEN_MINUTES, INTERVAL_HALF_HOUR, INTERVAL_HOUR, INTERVAL_DAY
	    alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis,
    	    60000, pIntent);
            //AlarmManager.INTERVAL_HALF_HOUR, pIntent);
    }

    public void cancelAlarm() {
	    Intent intent = new Intent(getApplicationContext(), MyAlarmReceiver.class);
        final PendingIntent pIntent = PendingIntent.getBroadcast(this,
                MyAlarmReceiver.REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);
	    AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
	    alarm.cancel(pIntent);
    }

}
