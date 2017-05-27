package com.example.lenovo_g50_70.newspad.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.lenovo_g50_70.newspad.R;
import com.example.lenovo_g50_70.newspad.util.SPUtil;

/**
 * 应用的启动页
 */
public class HomeActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        if(Build.VERSION.SDK_INT>=21){
            View decorView =getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        isFirst();
    }

    private void isFirst() {
        // 如果是第一次运行  就显示引导页
        if(SPUtil.getIsFirstRun(HomeActivity.this)) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        Thread.sleep(3000);
                        SPUtil.setIsFirstRun(HomeActivity.this, false);
                        startActivity(new Intent(HomeActivity.this, GuideActivity.class));
                        finish();
                        overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }else {
            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    startActivity(new Intent(HomeActivity.this, MainActivity.class));
                    finish();
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                }
            },3000);
        }
    }
}
