package com.example.lenovo_g50_70.newspad.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.example.lenovo_g50_70.newspad.R;

/**
 * 主界面显示
 */

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private LinearLayout newsLayout;    //新闻
    private LinearLayout jokeLayout;    //笑话
    private LinearLayout weatherLayout; //天气
    private LinearLayout mineLayout;    //我的

    private Fragment[] mFragments;                      //存放
    private FragmentManager fragmentManager;            //管理者
    private FragmentTransaction fragmentTransaction;    //事务处理

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initLayouts();
        initFragments();
    }

    private void initFragments() {
        if (mFragments == null) {
            mFragments = new Fragment[4];
        }
        //获取fragment实例
        fragmentManager = getSupportFragmentManager();
        mFragments[0] = fragmentManager.findFragmentById(R.id.fragement_news);
        mFragments[1] = fragmentManager.findFragmentById(R.id.fragement_joke);
        mFragments[2] = fragmentManager.findFragmentById(R.id.fragement_weather);
        mFragments[3] = fragmentManager.findFragmentById(R.id.fragement_mine);
        //获取fragmentTransaction实例
        //通过hide隐藏fragment,通过show显示fragment，通过commit()提交刚才的操作
        fragmentTransaction = fragmentManager.beginTransaction()
                .hide(mFragments[0]).hide(mFragments[1]).hide(mFragments[2]).hide(mFragments[3]);
        fragmentTransaction.show(mFragments[0]).commit();//默认显示第一个底部Tab
    }

    private void initLayouts() {
        newsLayout = (LinearLayout) findViewById(R.id.news_layout);
        jokeLayout = (LinearLayout) findViewById(R.id.joke_layout);
        weatherLayout = (LinearLayout) findViewById(R.id.weather_layout);
        mineLayout = (LinearLayout) findViewById(R.id.mine_layout);
        newsLayout.setOnClickListener(this);
        jokeLayout.setOnClickListener(this);
        weatherLayout.setOnClickListener(this);
        mineLayout.setOnClickListener(this);
        newsLayout.setSelected(true);//默认主页字体图片均为红色
    }

    @Override
    public void onClick(View v) {
        fragmentTransaction = fragmentManager.beginTransaction().hide(mFragments[0]).
                hide(mFragments[1]).hide(mFragments[2]).hide(mFragments[3]);
        switch (v.getId()) {
            case R.id.news_layout:
                //设置点击按钮后，文字和图片颜色的变换
                this.newsLayout.setSelected(true);//设置选择器
                this.jokeLayout.setSelected(false);
                this.weatherLayout.setSelected(false);
                this.mineLayout.setSelected(false);
                fragmentTransaction.show(mFragments[0]).commit();//显示第一fragment
                break;
            case R.id.joke_layout:
                this.newsLayout.setSelected(false);
                this.jokeLayout.setSelected(true);
                this.weatherLayout.setSelected(false);
                this.mineLayout.setSelected(false);
                fragmentTransaction.show(mFragments[1]).commit();//显示第二fragment
                break;
            case R.id.weather_layout:
                this.newsLayout.setSelected(false);
                this.jokeLayout.setSelected(false);
                this.weatherLayout.setSelected(true);
                this.mineLayout.setSelected(false);
                fragmentTransaction.show(mFragments[2]).commit();//显示第三fragment
                break;
            case R.id.mine_layout:
                this.newsLayout.setSelected(false);
                this.jokeLayout.setSelected(false);
                this.weatherLayout.setSelected(false);
                this.mineLayout.setSelected(true);
                fragmentTransaction.show(mFragments[3]).commit();//显示第四fragment
                break;
        }
    }
}
