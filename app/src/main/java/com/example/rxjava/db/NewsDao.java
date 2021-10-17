package com.example.rxjava.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.rxjava.pojo.News;

import java.util.List;

@Dao
public interface NewsDao {
    @Insert
    public void insertNews(News news);

    @Query("delete from News where name =:newsName")
    public void deleteNews(String newsName);

    @Query("select * from News")
    public LiveData<List<News>> getNews();
}


