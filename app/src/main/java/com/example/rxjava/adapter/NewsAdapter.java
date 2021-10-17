package com.example.rxjava.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.rxjava.R;
import com.example.rxjava.pojo.News;
import com.example.rxjava.pojo.NewsRseponse;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {
    private ArrayList<News> mList = new ArrayList<>();
    private Context mContext;

    public NewsAdapter(Context mContext ) {
        this.mContext = mContext;

    }
    public void setList(ArrayList<News> mList) {
        this.mList = mList;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new NewsViewHolder(v);    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.NewsViewHolder holder, int position) {
        NewsRseponse newsRseponse = mList.get(position);
        NewsViewHolder newsViewHolder = (NewsViewHolder) holder ;
        newsViewHolder.textTitle.setText(newsRseponse.getTitle());
        newsViewHolder.sourceTxt.setText(newsRseponse.getSourceTitle());
        newsViewHolder.btnTitle.setText(newsRseponse.getSectionTitle());
        setImageHolder(newsViewHolder.coverImg, newsRseponse);
        Picasso.with(mContext)
                .load(Uri.parse(Constants.loadSourceImg(newsRseponse.getSourceID())))
                .into(newsViewHolder.sourceImg);

    }
    private void setImageHolder(ImageView image, NewsRseponse postModel) {
        if (postModel.getRatio() == null || postModel.getRatio().equals("") ||
                postModel.getRatio().equals("1.55") || postModel.getRatio().equals("0")) {
            image.setVisibility(View.GONE);
        } else {
            WindowManager wm = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
            Display display = wm.getDefaultDisplay();
            DisplayMetrics outMetrics = new DisplayMetrics();
            display.getMetrics(outMetrics);
            int width = outMetrics.widthPixels;
            int height = (int) (width / Double.parseDouble(postModel.getRatio()));
            image.setVisibility(View.VISIBLE);
            image.getLayoutParams().width = width;
            image.getLayoutParams().height = height;
            Picasso.with(mContext)
                    .load(Uri.parse(Constants.mediaURL + postModel.getImg()))
                    .placeholder(R.drawable.ic_launcher_background)
                    .into(image);
        }
    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public News getNewsAt(int position) {
        return mList.get(position);
    }

    public class NewsViewHolder extends RecyclerView.ViewHolder {
        ImageView sourceImg , coverImg;
        TextView sourceTxt, textTitle, btnTitle;
        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            sourceImg = itemView.findViewById(R.id.sourceImg);
            coverImg = itemView.findViewById(R.id.coverImage);
            sourceTxt = itemView.findViewById(R.id.sourceTxt);
            textTitle =itemView.findViewById(R.id.textTitle);
            btnTitle = itemView.findViewById(R.id.btnTitle);

        }
    }
}
