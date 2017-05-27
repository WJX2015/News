package com.example.lenovo_g50_70.newspad.bean;

import cn.bmob.v3.BmobObject;

/**
 * 我的收藏
 * Created by lenovo-G50-70 on 2017/5/23.
 */

public class Collection extends BmobObject {

    private String phone; //用手机号区分不同用户
    private String newsKey; //通过key指定特定的收藏新闻
    private String newsTitle; //收藏新闻的标题
    private String newsUrl; // 收藏新闻的链接

    public Collection(){

    }

    public Collection(String phone,String newsKey,String newsTitle,String newsUrl,boolean isCollection){
        this.phone=phone;
        this.newsKey=newsKey;
        this.newsTitle=newsTitle;
        this.newsUrl=newsUrl;
        this.isCollection=isCollection;
    }

    public boolean isCollection() {
        return isCollection;
    }

    public void setCollection(boolean collection) {
        isCollection = collection;
    }

    public String getNewsUrl() {
        return newsUrl;
    }

    public void setNewsUrl(String newsUrl) {
        this.newsUrl = newsUrl;
    }

    public String getNewsTitle() {
        return newsTitle;
    }

    public void setNewsTitle(String newsTitle) {
        this.newsTitle = newsTitle;
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

    private boolean isCollection; //判断是否收藏

}
