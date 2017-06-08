package com.example.lenovo_g50_70.newspad.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo_g50_70.newspad.R;
import com.example.lenovo_g50_70.newspad.adapter.JokesAdapter;
import com.example.lenovo_g50_70.newspad.bean.Jokes;
import com.example.lenovo_g50_70.newspad.bean.NewsApplication;
import com.example.lenovo_g50_70.newspad.util.HttpUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;

/**
 * 底部Tab之一，笑话
 * Created by lenovo-G50-70 on 2017/5/27.
 */

public class JokeFragment extends Fragment {
    private static final int MSG_GET_JOKES = 1001;
    private static final int MSG_RRFRESH_JOKES = 1002;
    private int page=1;
    private String mAddress="http://japi.juhe.cn/joke/content/list.from?" +
            "key=7eb409893cbccbdfbb42480aaf9ac143" +
            "&page="+(++page)+"&pagesize=20&sort=asc&time=1451577600";
    private List<Jokes.ResultBean.DataBean> mBeanList=new ArrayList<>();
    private SwipeRefreshLayout mRefreshLayout;
    private RecyclerView mRecyclerView;
    private JokesAdapter mAdapter;
    private Handler mHandler;
    private View mView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_joke,null);
        initView(mView);
        return mView;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initHandler();
        LoadDatas(mAddress);
    }

    private void initHandler() {
        //异步更新界面，每次加载数据后自动更新
        mHandler =new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                switch (msg.what){
                    case MSG_GET_JOKES:
                        mAdapter.changeData(mBeanList);
                        return true;
                    case MSG_RRFRESH_JOKES:
                        LoadDatas(mAddress);
                        mRefreshLayout.setRefreshing(false);
                        return true;
                }
                return false;
            }
        });
    }
    private void initView(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.joke_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(NewsApplication.getmContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new JokesAdapter(mBeanList);
        mRecyclerView.setAdapter(mAdapter);
        mRefreshLayout = (SwipeRefreshLayout) view.findViewById(R.id.swipe_refresh);
        mRefreshLayout.setColorSchemeResources(R.color.colorPrimary);
        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshJokes();
            }
        });
    }

    private void refreshJokes() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    Thread.sleep(2000);
                    mHandler.sendEmptyMessage(MSG_RRFRESH_JOKES);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }).start();
    }

    private void LoadDatas(String address) {
        HttpUtil.sendOkHttpRequest(address, new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                //访问失败回调
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                new Thread(new Runnable() {
                    String mString = null;
                    @Override
                    public void run() {
                        try {
                            mString = response.body().string();
                            Gson gson = new Gson();
                            Jokes jokes = gson.fromJson(mString,Jokes.class);
                            mBeanList = jokes.getResult().getData();
                            mHandler.sendEmptyMessage(MSG_GET_JOKES);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }
}
