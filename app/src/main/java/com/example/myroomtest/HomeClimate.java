package com.example.myroomtest;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

@Entity(tableName = "climate_table")
public class HomeClimate {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "measure_time")
    private long measureTime;

    @ColumnInfo(name = "pressure")
    private int Pressure;

    @ColumnInfo(name = "temp_battery")
    private int tempBattery;

    @ColumnInfo(name = "temp_air")
    private int tempAir;

}
