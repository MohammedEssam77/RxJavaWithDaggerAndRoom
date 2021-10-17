package com.example.rxjava.repository;

import androidx.lifecycle.LiveData;

import com.example.rxjava.ApiService.NewsApiService;
import com.example.rxjava.db.NewsDao;
import com.example.rxjava.pojo.News;
import com.example.rxjava.pojo.NewsRseponse;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.rxjava3.core.Observable;

public class Repository {
    private NewsApiService newsApiService;
    private NewsDao newsDao;


    @Inject
    public Repository(NewsApiService newsApiService, NewsDao newsDao) {
        this.newsApiService = newsApiService;
        this.newsDao = newsDao ;
    }

    public Observable<NewsRseponse> getNews() {
        return NewsApiService.getNews();
    }
    public void insertNews(News news){newsDao.insertNews(news);}

    public void deleteNews(String newsName){newsDao.deleteNews(newsName);}

    public LiveData<List<News>> getFavNews(){
        return newsDao.getNews();
    }
}

