package com.example.myroomtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;
import androidx.room.RoomDatabase;

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

        final ClimateRoomDatabase appDb = ClimateRoomDatabase.getInstance(this);

        final EditText editPressure = (EditText)findViewById(R.id.editPressure);
        editPressure.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Pressure = Integer.parseInt(editPressure.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        final EditText editTbattery = (EditText)findViewById(R.id.editTbattery);
        editPressure.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                TempBattery = Integer.parseInt(editTbattery.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        final EditText editTair = (EditText)findViewById(R.id.editTair);
        editPressure.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                TempAir = Integer.parseInt(editTair.getText().toString());
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        final Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            ClimateItem item =
                new ClimateItem(System.currentTimeMillis(), Pressure, TempBattery, TempAir);
            appDb.climateDao().insert(item);

            List<ClimateItem> items = appDb.climateDao().getAll();

            StringBuilder builder = new StringBuilder();
            for (ClimateItem details : items) {
                    builder.append(details + "\n");
            }
            TextView tv = (TextView)findViewById(R.id.textViewDB);

            tv.setText(builder.toString());

            }
        });

    }
}
