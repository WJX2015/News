package com.example.lenovo_g50_70.newspad.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by lenovo-G50-70 on 2017/3/30.
 */

public class FragmentAdapter extends FragmentPagerAdapter {
    private List<Fragment> fragments;
    private List<String> strings;

    public FragmentAdapter(FragmentManager fm, List<Fragment> fragments , List<String> strings) {
        super(fm);
        this.fragments = fragments;
        this.strings = strings;
    }

    //返回Tab对应的Fragment
    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    //返回Tab数目
    @Override
    public int getCount() {
        return strings.size();
    }

    //返回Tab文字
    @Override
    public CharSequence getPageTitle(int position) {
        return strings.get(position);
    }

}
