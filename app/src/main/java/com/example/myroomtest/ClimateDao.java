package com.example.myroomtest;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Update;


import androidx.room.Query;

import java.util.List;

@Dao
public interface ClimateDao {
    @Query("SELECT * FROM climate_table")
    List<ClimateItem> getAll();

    @Query("SELECT * FROM climate_table WHERE measure_time = :tm")
    ClimateItem getById(long tm);

    @Insert
    void insert(ClimateItem item);

    @Update
    void update(ClimateItem item);

    @Delete
    void delete(ClimateItem item);
}

