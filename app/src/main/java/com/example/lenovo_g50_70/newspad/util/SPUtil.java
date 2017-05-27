package com.example.lenovo_g50_70.newspad.util;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreferences存储标记
 * Created by lenovo-G50-70 on 2017/5/18.
 */

public class SPUtil {
    private final static String FIRST_SP ="FIRST_SP";  //指定SharedPreferences文件的名称
    private final static String FIRST_RUN="FIRST_RUN"; //SharedPreferences存数据的键值名

    /**
     *
     * @param context 获取导航页上下文
     * @return 是否第一次使用应用
     */
    public static boolean getIsFirstRun(Context context){
        SharedPreferences sp=context.getSharedPreferences(FIRST_SP,Context.MODE_PRIVATE);
        return sp.getBoolean(FIRST_RUN,true);
    }

    /**第一次使用后添加使用标记*/
    public static void setIsFirstRun(Context context,boolean b){
        SharedPreferences sp=context.getSharedPreferences(FIRST_SP,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sp.edit();
        editor.putBoolean(FIRST_RUN,b);//传入数据通过键值匹配
        editor.commit();
    }
}





























