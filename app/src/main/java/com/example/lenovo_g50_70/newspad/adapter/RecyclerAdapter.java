package com.example.lenovo_g50_70.newspad.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo_g50_70.newspad.R;
import com.example.lenovo_g50_70.newspad.activity.DetailActivity;
import com.example.lenovo_g50_70.newspad.bean.News;

import java.util.List;

/**
 * Created by lenovo-G50-70 on 2017/5/16.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {
    private List<News.ResultBean.DataBean> mNewsList;
    private Context mContext;

    public void changData(List<News.ResultBean.DataBean> newsList) {
        mNewsList=newsList;
        notifyDataSetChanged();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        View mView;
        ImageView mImageView;
        TextView mTextView;
        TextView tvDate;
        TextView tvAutor;

        public MyViewHolder(View view) {
            super(view);
            mView = view;
            mImageView = (ImageView) view.findViewById(R.id.image_item);
            mTextView = (TextView) view.findViewById(R.id.news_item);
            tvDate = (TextView) view.findViewById(R.id.tv_date);
            tvAutor = (TextView) view.findViewById(R.id.tv_author);
        }
    }

    public RecyclerAdapter(List<News.ResultBean.DataBean> list) {
        mNewsList = list;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
        mContext=parent.getContext();
        final MyViewHolder holder = new MyViewHolder(view);
        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                News.ResultBean.DataBean news = mNewsList.get(position);
                Intent intent =new Intent(mContext, DetailActivity.class);
                intent.putExtra("URL",news.getUrl());
                intent.putExtra("KEY",news.getUniquekey());
                intent.putExtra("TITLE",news.getTitle());
                mContext.startActivity(intent);
                ((Activity) mContext).overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        News.ResultBean.DataBean news = mNewsList.get(position);
        Glide.with(holder.mImageView.getContext())
                .load(news.getThumbnail_pic_s())
                .into(holder.mImageView);
        holder.mTextView.setText(news.getTitle());
        holder.tvDate.setText(news.getDate());
        holder.tvAutor.setText(news.getAuthor_name());
    }

    @Override
    public int getItemCount() {
        return mNewsList.size();
    }
}
