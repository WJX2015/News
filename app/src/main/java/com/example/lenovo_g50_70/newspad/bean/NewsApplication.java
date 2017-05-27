package com.example.lenovo_g50_70.newspad.bean;

import android.app.Application;
import android.content.Context;

import cn.bmob.v3.Bmob;

/**
 * 获取全局上下文
 * Created by lenovo-G50-70 on 2017/5/16.
 */

public class NewsApplication extends Application {
    private static Context mContext;

    @Override
    public void onCreate() {
        mContext=getApplicationContext();
        Bmob.initialize(this,"1a6eb85c8bacec0dbd2c07a6b05971e6");
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    public static Context getmContext(){
        return mContext;
    }
}
