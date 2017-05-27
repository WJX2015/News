package com.example.lenovo_g50_70.newspad.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lenovo_g50_70.newspad.R;

/**
 * 底部Tab之一，天气预报
 * Created by lenovo-G50-70 on 2017/5/27.
 */

public class WeatherFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_weather,null);
        return view;
    }
}
