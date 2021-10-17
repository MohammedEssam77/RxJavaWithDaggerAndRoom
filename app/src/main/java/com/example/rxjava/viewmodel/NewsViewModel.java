package com.example.rxjava.viewmodel;

import android.util.Log;

import androidx.hilt.lifecycle.ViewModelInject;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.rxjava.pojo.News;
import com.example.rxjava.pojo.NewsRseponse;
import com.example.rxjava.repository.Repository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class NewsViewModel extends ViewModel {
    private Repository repository;
    MutableLiveData<ArrayList<News>> newsList = new MutableLiveData<>();
    private LiveData<List<News>> favList = null;

    public LiveData<List<News>> getFavList() {
        return favList;
    }
    @ViewModelInject
    public NewsViewModel(Repository repository) {
        this.repository = repository;
    }

    public MutableLiveData<ArrayList<News>> getNewsList() {
        return newsList;
    }

    public void getNews() {
        repository.getNews()
                .subscribeOn(Schedulers.io())
                .map(new Function<NewsRseponse, ArrayList<News>>() {
                    @Override
                    public ArrayList<News> apply(NewsRseponse newsResponse) throws Throwable {
                        ArrayList<News> list = newsResponse.getResults();

                        return list;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> newsList.setValue(result),
                        error -> Log.e("viwModel", error.getMessage()));
    }
    public void insertNews(News news) {
        repository.insertNews(news);
    }

    public void deleteNews(String newsName) {
        repository.deleteNews(newsName);
    }

    public void getFavNews() {
        favList = repository.getFavNews();
    }


}

