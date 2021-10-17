package com.example.rxjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.rxjava.adapter.NewsAdapter;
import com.example.rxjava.pojo.News;
import com.example.rxjava.pojo.NewsRseponse;
import com.example.rxjava.viewmodel.NewsViewModel;

import java.util.ArrayList;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity  {
    private NewsViewModel viewModel ;
    private RecyclerView recyclerView ;
    private NewsAdapter adapter ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView =findViewById(R.id.news_recyclerView);
        adapter =new NewsAdapter(this);
        recyclerView.setAdapter(adapter);
        viewModel = new ViewModelProvider(this).get(NewsViewModel.class);
        viewModel.getNews();
        setupSwipe();
        viewModel.getNewsList().observe(this, (Observer<? super ArrayList<News>>) new Observer<ArrayList<News>>() {
            @Override
            public void onChanged(ArrayList<News> news) {

            adapter.setList(news);
        }
    });


}

    private void setupSwipe(){
        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int swipedNewsPosition = viewHolder.getAdapterPosition();
                NewsRseponse swipedNews = adapter.getNewsAt(swipedNewsPosition);
                viewModel.insertNews(swipedNews);
                adapter.notifyDataSetChanged();
                Toast.makeText(MainActivity.this, "News added to database", Toast.LENGTH_SHORT).show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }

}