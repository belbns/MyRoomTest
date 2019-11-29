package com.example.myroomtest;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import inventory.techiediaries.com.dao.ItemDAO;
import inventory.techiediaries.com.models.Item;

@Database(entities = {HomeClimate.class}, version = 1)
public abstract class ClimateRoomDatabase extends RoomDatabase {
    public abstract ItemDAO getItemDAO();
}
