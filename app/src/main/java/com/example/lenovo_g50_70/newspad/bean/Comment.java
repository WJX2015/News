package com.example.lenovo_g50_70.newspad.bean;

import cn.bmob.v3.BmobObject;

/**
 * 我的评论
 * Created by lenovo-G50-70 on 2017/5/23.
 */

public class Comment extends BmobObject {

    private String phone;  //通过手机号区分不同用户
    private String content; //我的评论内容
    private String newsKey; //通过key指定特定的新闻
    private String newsTitle; //新闻的标题
    private boolean isDel; //是否删除我的评论

    public Comment(){

    }

    public Comment(String phone,String content,String newsKey,String newsTitle){
        this.phone=phone;
        this.content=content;
        this.newsKey=newsKey;
        this.newsTitle=newsTitle;
    }

    public String getNewsKey() {
        return newsKey;
    }

    public void setNewsKey(String newsKey) {
        this.newsKey = newsKey;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
    }

    public boolean isDel() {
        return isDel;
    }

    public void setDel(boolean del) {
        isDel = del;
    }
}
