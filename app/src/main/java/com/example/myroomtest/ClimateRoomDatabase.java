package com.example.myroomtest;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {ClimateItem.class}, version = 1, exportSchema = false)
public abstract class ClimateRoomDatabase extends RoomDatabase {
    private static final String DB_NAME = "climate_db";
    public static ClimateRoomDatabase sInstanse;

    public static synchronized  ClimateRoomDatabase getInstance(Context context) {
        if (sInstanse == null) {
            sInstanse = Room.databaseBuilder(context.getApplicationContext(),
                    ClimateRoomDatabase.class, DB_NAME)
            .fallbackToDestructiveMigration()
                    .build();
        }
        return sInstanse;
    }

    public abstract ClimateDao climateDao();
}
