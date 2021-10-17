package com.example.rxjava.db;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.rxjava.pojo.News;

@Database(entities = News.class, version = 1, exportSchema = false)
public abstract class NewsDB extends RoomDatabase {
    public abstract NewsDao newsDao();
}