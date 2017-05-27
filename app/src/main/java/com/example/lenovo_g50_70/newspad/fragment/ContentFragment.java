package com.example.lenovo_g50_70.newspad.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo_g50_70.newspad.R;
import com.example.lenovo_g50_70.newspad.adapter.RecyclerAdapter;
import com.example.lenovo_g50_70.newspad.bean.News;
import com.example.lenovo_g50_70.newspad.bean.NewsApplication;
import com.example.lenovo_g50_70.newspad.util.HttpUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Response;


/**
 * 主界面的内容
 * Created by lenovo-G50-70 on 2017/3/30.
 */

public class ContentFragment extends Fragment {
    private static final int MSG_GET_NEWS = 1001;
    private int mType;
    private Handler mHandler;
    private RecyclerView mRecyclerView;
    private List<News.ResultBean.DataBean> mNewsList = new ArrayList<>();
    private RecyclerAdapter mAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mType = getArguments().getInt("NEWSTYPE");
        initHandler();
        getNewsDatas(mType);
    }

    private void initHandler() {
        //异步更新界面，每次加载数据后自动更新
        mHandler =new Handler(new Handler.Callback() {
            @Override
            public boolean handleMessage(Message msg) {
                if(msg.what == MSG_GET_NEWS){
                    mAdapter.changData(mNewsList);
                    return  true;
                }
                return false;
            }
        });
    }

    private void getNewsDatas(int type) {
        switch (type) {
            case 1:
                LoadDatas("http://v.juhe.cn/toutiao/index?type=guonei&key=a2a7a2c0c7968be77dae6f6fc73a484a");
                break;
            case 2:
                LoadDatas("http://v.juhe.cn/toutiao/index?type=top&key=a2a7a2c0c7968be77dae6f6fc73a484a");
                break;
            case 3:
                LoadDatas("http://v.juhe.cn/toutiao/index?type=shishang&key=a2a7a2c0c7968be77dae6f6fc73a484a");
                break;
            case 4:
                LoadDatas("http://v.juhe.cn/toutiao/index?type=yule&key=a2a7a2c0c7968be77dae6f6fc73a484a");
                break;
            case 5:
                LoadDatas("http://v.juhe.cn/toutiao/index?type=caijing&key=a2a7a2c0c7968be77dae6f6fc73a484a");
                break;
            case 6:
                LoadDatas("http://v.juhe.cn/toutiao/index?type=tiyu&key=a2a7a2c0c7968be77dae6f6fc73a484a");
                break;
            case 7:
                LoadDatas("http://v.juhe.cn/toutiao/index?type=shehui&key=a2a7a2c0c7968be77dae6f6fc73a484a");
                break;
            case 8:
                LoadDatas("http://v.juhe.cn/toutiao/index?type=keji&key=a2a7a2c0c7968be77dae6f6fc73a484a");
                break;
            case 9:
                LoadDatas("http://v.juhe.cn/toutiao/index?type=junshi&key=a2a7a2c0c7968be77dae6f6fc73a484a");
                break;
            case 10:
                LoadDatas("http://v.juhe.cn/toutiao/index?type=guoji&key=a2a7a2c0c7968be77dae6f6fc73a484a");
                break;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, null);
        initViews(view);
        return view;
    }

    private void initViews(View view) {
        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(NewsApplication.getmContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setLayoutManager(layoutManager);
        mAdapter = new RecyclerAdapter(mNewsList);
        mRecyclerView.setAdapter(mAdapter);
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
                            News newsData = gson.fromJson(mString, News.class);
                            mNewsList = newsData.getResult().getData();
                            mHandler.sendEmptyMessage(MSG_GET_NEWS);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }
}
