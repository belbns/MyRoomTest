package com.example.myroomtest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private long currTime = System.currentTimeMillis();
    private int Pressure = 0;
    private int TempBattery = 0;
    private int TempAir = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText editPressure = (EditText)findViewById(R.id.editPressure);
        editPressure.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if(event.getAction() == KeyEvent.ACTION_DOWN ||
                        (keyCode == KeyEvent.KEYCODE_ENTER))
                {
                    // сохраняем текст, введенный до нажатия Enter в переменную
                    Pressure = Integer.parseInt(editPressure.getText().toString());
                    return true;
                }
                return false;
            }
        });

        final EditText editTbattery = (EditText)findViewById(R.id.editTbattery);
        editPressure.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if(event.getAction() == KeyEvent.ACTION_DOWN ||
                        (keyCode == KeyEvent.KEYCODE_ENTER))
                {
                    // сохраняем текст, введенный до нажатия Enter в переменную
                    TempBattery = Integer.parseInt(editTbattery.getText().toString());
                    return true;
                }
                return false;
            }
        });

        final EditText editTair = (EditText)findViewById(R.id.editTair);
        editPressure.setOnKeyListener(new View.OnKeyListener()
        {
            public boolean onKey(View v, int keyCode, KeyEvent event)
            {
                if(event.getAction() == KeyEvent.ACTION_DOWN ||
                        (keyCode == KeyEvent.KEYCODE_ENTER))
                {
                    // сохраняем текст, введенный до нажатия Enter в переменную
                    TempAir = Integer.parseInt(editTair.getText().toString());
                    return true;
                }
                return false;
            }
        });

        final Button button = (Button)findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currTime = System.currentTimeMillis();
                TextView tv = (TextView)findViewById(R.id.textViewDB);
                String st = String.valueOf(currTime) + " | " +
                        String.valueOf(Pressure) + " | " +
                        String.valueOf(TempBattery) + " | " +
                        String.valueOf(TempAir);
                tv.setText(st);

            }
        });

    }
}
