package com.example.lenovo_g50_70.newspad.util;

import okhttp3.OkHttpClient;
import okhttp3.Request;

/**
 * 访问网络的工具
 * Created by lenovo-G50-70 on 2017/5/17.
 */

public class HttpUtil {
    public static void sendOkHttpRequest(final String address, final okhttp3.Callback callback) {
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(address)
                .build();
        client.newCall(request).enqueue(callback);
    }
}
