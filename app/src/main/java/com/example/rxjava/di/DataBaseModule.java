package com.example.rxjava.di;

import android.app.Application;

import androidx.room.Room;

import com.example.rxjava.db.NewsDB;
import com.example.rxjava.db.NewsDao;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@Module
@InstallIn(ApplicationComponent.class)
public class DataBaseModule {

    @Provides
    @Singleton
    public static NewsDB provideDB(Application application){
        return Room.databaseBuilder(application, NewsDB.class, "fav_DB")
                .fallbackToDestructiveMigration()
                .allowMainThreadQueries()
                .build();
    }

    @Provides
    @Singleton
    public static NewsDao provideDao(NewsDB newsDB){
        return newsDB.newsDao();
    }

}
