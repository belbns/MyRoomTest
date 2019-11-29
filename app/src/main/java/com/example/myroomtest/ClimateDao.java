package com.example.myroomtest;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Update;

import java.util.List;

import inventory.techiediaries.com.models.Item;

public class ClimateDao {
    @Query("SELECT * FROM climate_table")
    List<HomeClimate> getAll();

    @Query("SELECT * FROM climate_table WHERE tm = :measure_time")
    HomeClimate getById(long tm);

    @Insert
    void insert(HomeClimate climate);

    @Update
    void update(HomeClimate climate);

    @Delete
    void delete(HomeClimate climate);
}

