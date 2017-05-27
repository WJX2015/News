package com.example.lenovo_g50_70.newspad.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ViewFlipper;

import com.example.lenovo_g50_70.newspad.R;

/**
 * 应用的导航页
 * 只有在第一次运行才启动
 */
public class GuideActivity extends AppCompatActivity implements View.OnTouchListener {
    private ViewFlipper mFlipper;
    private GestureDetector mDetector;
    private Button btnGuide;
    // 图片小点，表示当前播到第几张
    private LinearLayout poinstLayout;
    private ImageView[] points;
    private int currentPosition = 0 ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
        initViews();
    }

    private void initViews() {
        mFlipper = (ViewFlipper) findViewById(R.id.viewflipper);
        mFlipper.setOnTouchListener(this);
        mDetector = new GestureDetector(new simpleGestureListener());
        btnGuide = (Button) findViewById(R.id.btnGuide);
        btnGuide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GuideActivity.this, MainActivity.class));
                finish();
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
            }
        });
        //小图标的对象获取
        poinstLayout = (LinearLayout) findViewById(R.id.layout_point_wrap);
        int pointsSize = poinstLayout.getChildCount();
        points = new ImageView[pointsSize] ;
        for (int i = 0; i < pointsSize; i++) {
            points[i] =(ImageView) poinstLayout.getChildAt(i) ;
            //points[i].setEnabled(true);
        }
        setCurrentPoint(currentPosition);
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {
        return mDetector.onTouchEvent(event);
    }

    private class simpleGestureListener extends GestureDetector.SimpleOnGestureListener {
        final int FLING_MIN_DISTANCE = 100, FLING_MIN_VELOCITY = 200;
        //不知道为什么，不加上onDown函数的话，onFling就不会响应，真是奇怪
        @Override
        public boolean onDown(MotionEvent e) {
            return true;
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            if (e1.getX() - e2.getX() > FLING_MIN_DISTANCE && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
                // 向左滑动
                mFlipper.setInAnimation(GuideActivity.this, R.anim.slide_in_right);
                mFlipper.setOutAnimation(GuideActivity.this, R.anim.slide_out_left);
                mFlipper.showNext();
            } else if (e2.getX() - e1.getX() > FLING_MIN_DISTANCE && Math.abs(velocityX) > FLING_MIN_VELOCITY) {
                // 向右滑动
                mFlipper.setInAnimation(GuideActivity.this, R.anim.slide_in_left);
                mFlipper.setOutAnimation(GuideActivity.this, R.anim.slide_out_right);
                mFlipper.showPrevious();
            }
            //获取当前显示View的位置 potsition
            int viewFlipperCurrentDisplayViewPosition = mFlipper.getDisplayedChild();
            setCurrentPoint(viewFlipperCurrentDisplayViewPosition);
            return true;
        }
    }

    /**
     * 显示当前View的位置
     * @param postion
     */
    public void setCurrentPoint(int postion) {
        //把上一次显示的View的位置设置灰色
        points[currentPosition].setImageResource(R.drawable.write);
        //把当前显示的View的位置设置成黄色，表示ViewFlipper下子元素（View）显示的是第几个View
        points[postion].setImageResource(R.drawable.red);
        currentPosition = postion ;
        if(currentPosition==points.length-1){
            //最后一个，实现动画浮现
            btnGuide.setVisibility(View.VISIBLE);
            AlphaAnimation aa=new AlphaAnimation(0,1f);
            aa.setDuration(500);
            btnGuide.startAnimation(aa);
        }else {
            //防止在最后一页返回上一页，Button还在
            btnGuide.setVisibility(View.GONE);
        }
    }
}
