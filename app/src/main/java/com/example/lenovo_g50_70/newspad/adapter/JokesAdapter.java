package com.example.lenovo_g50_70.newspad.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lenovo_g50_70.newspad.R;
import com.example.lenovo_g50_70.newspad.bean.Jokes;

import java.util.List;

/**
 * Created by lenovo-G50-70 on 2017/5/28.
 */

public class JokesAdapter extends RecyclerView.Adapter<JokesAdapter.JokeViewHolder>{
    private List<Jokes.ResultBean.DataBean> mBeanList;
    static class JokeViewHolder extends RecyclerView.ViewHolder{
        View mView;
        TextView jokeContent;
        public JokeViewHolder(View view){
            super(view);
            mView=view;
            jokeContent = (TextView) view.findViewById(R.id.joke_text);
        }
    }
    public JokesAdapter(List<Jokes.ResultBean.DataBean> beanList){
        mBeanList=beanList;
    }

    @Override
    public JokeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.joke_item,parent,false);
        final JokeViewHolder holder=new JokeViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(JokeViewHolder holder, int position) {
        Jokes.ResultBean.DataBean joke =mBeanList.get(position);
        holder.jokeContent.setText(joke.getContent());
    }

    @Override
    public int getItemCount() {
        return mBeanList.size();
    }

    public void changeData(List<Jokes.ResultBean.DataBean> beanList) {
        mBeanList=beanList;
        notifyDataSetChanged();
    }
}
