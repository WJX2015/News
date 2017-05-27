package com.example.lenovo_g50_70.newspad.activity;

import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.lenovo_g50_70.newspad.R;
import com.example.lenovo_g50_70.newspad.bean.Collection;
import com.example.lenovo_g50_70.newspad.bean.Comment;
import com.tencent.smtt.sdk.WebChromeClient;
import com.tencent.smtt.sdk.WebView;
import com.tencent.smtt.sdk.WebViewClient;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

/**
 * 新闻的详情页
 * 单位为Item
 */
public class DetailActivity extends AppCompatActivity {

    private com.tencent.smtt.sdk.WebView mWebView;
    private EditText mEditText;//输入的评论内容
    private Button mButton; //发表评论
    private ImageView mImageView; //收藏页面

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        //视频为了避免闪屏和透明问题,腾讯X5内核的WebView
        getWindow().setFormat(PixelFormat.TRANSLUCENT);
        initView();
    }

    private void initView() {
        mWebView = (WebView) findViewById(R.id.webView);
        mWebView.loadUrl(getIntent().getStringExtra("URL"));
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView webView, String s) {
                webView.loadUrl(s);
                return super.shouldOverrideUrlLoading(webView, s);
            }
        });
        mWebView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView webView, int i) {
                super.onProgressChanged(webView, i);
            }
        });
        mEditText = (EditText) findViewById(R.id.edt_comment);
        mButton = (Button) findViewById(R.id.btn_comment);
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLogin()) {//已登陆
                    CommentSucceed();//评论成功
                } else {//未登陆
                    startActivity(new Intent(DetailActivity.this, LoginActivity.class));
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                }
            }
        });
        mImageView = (ImageView) findViewById(R.id.btn_collect);
        mImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isLogin()) {//已登陆
                    CollectSucceed();//收藏成功
                } else {//未登陆
                    startActivity(new Intent(DetailActivity.this, LoginActivity.class));
                    overridePendingTransition(R.anim.slide_in_right,R.anim.slide_out_left);
                }
            }
        });
    }

    private void CollectSucceed() {
        String phone = BmobUser.getCurrentUser().getMobilePhoneNumber();//手机号
        String title =getIntent().getStringExtra("TITLE");//新闻标题
        String key =getIntent().getStringExtra("KEY");//新闻特定的KEY
        String url =getIntent().getStringExtra("URL");//新闻详情页的URL
        Collection collection =new Collection(phone,key,title,url,true);
        collection.save(new SaveListener<String>() { //我的评论上传Bmob
            @Override
            public void done(String s, BmobException e) {//方法在添加数据
                if(e==null){   //没有错就证明上传成功
                    Toast.makeText(getApplicationContext(),"收藏成功",Toast.LENGTH_SHORT).show();
                    mImageView.setImageResource(R.drawable.coll_orange);
                    mEditText.setText("");
                }else {
                    Toast.makeText(getApplicationContext(),"收藏失败,"+e.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void CommentSucceed() {
        String phone = BmobUser.getCurrentUser().getMobilePhoneNumber();//手机号
        String content =mEditText.getText().toString().trim();//评论内容
        String title =getIntent().getStringExtra("TITLE");//新闻标题
        String key =getIntent().getStringExtra("KEY");//新闻特定的KEY
        Comment myComment =new Comment(phone,content,key,title);//评论表的对象
        myComment.save(new SaveListener<String>() { //我的评论上传Bmob
            @Override
            public void done(String s, BmobException e) {//方法在添加数据
                if(e==null){   //没有错就证明上传成功
                    Toast.makeText(getApplicationContext(),"评论成功",Toast.LENGTH_SHORT).show();
                    mEditText.setText("");
                }else {
                    Toast.makeText(getApplicationContext(),"评论失败,"+e.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    /**
     * 判断用户是否登陆
     * 用户登录后会有一个缓存对象
     * 下次登陆无须再次验证，提升用户体现
     */
    private boolean isLogin() {
        BmobUser user = BmobUser.getCurrentUser();
        if (user == null) {//未登陆
            return false;
        }
        return true;
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (mWebView.canGoBack()) {
                mWebView.goBack();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);
    }
}
