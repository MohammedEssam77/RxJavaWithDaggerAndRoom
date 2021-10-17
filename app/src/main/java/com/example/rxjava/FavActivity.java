package com.example.rxjava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.rxjava.adapter.NewsAdapter;
import com.example.rxjava.pojo.News;
import com.example.rxjava.pojo.NewsRseponse;
import com.example.rxjava.viewmodel.NewsViewModel;

import java.util.ArrayList;
import java.util.List;

public class FavActivity extends AppCompatActivity {
    private NewsViewModel viewModel;
    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav2);

        recyclerView = findViewById(R.id.fav_recyclerView);
        adapter = new NewsAdapter(this);
        recyclerView.setAdapter(adapter);
        setupSwipe();

        Button toHomeBtn = findViewById(R.id.to_home_button);
        toHomeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(FavActivity.this, MainActivity.class));
            }
        });

        viewModel = new ViewModelProvider(this).get(NewsViewModel.class);

        viewModel.getFavNews();

        viewModel.getFavList().observe(this, new Observer<List<News>>() {
            @Override
            public void onChanged(List<News> news) {
                ArrayList<News> list = new ArrayList<>();
                list.addAll(news);
                adapter.setList(list);
            }
        });
    }

    private void setupSwipe() {
        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                int swipedNewsPosition = viewHolder.getAdapterPosition();
                News swipedNews = adapter.getNewsAt(swipedNewsPosition);
                viewModel.deleteNews(swipedNews.getName());
                adapter.notifyDataSetChanged();
                Toast.makeText(FavActivity.this, "news deleted from database", Toast.LENGTH_SHORT).show();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(recyclerView);
    }
}