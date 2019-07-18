package com.cy.develop.wukongaq.util;

import android.content.Context;
import android.content.SharedPreferences;
import static android.content.Context.MODE_PRIVATE;

public class SpUtil {


    /**
     * 将数据以String类型进行存储
     * @param context 上下文
     * @param key key值
     * @param data 待存储数据
     */
    public static void spInput(Context context,String key,String data){
        SharedPreferences.Editor editor=context.getSharedPreferences(key,MODE_PRIVATE).edit();
        editor.putString(key,data);
        editor.apply();
    }

    /**
     * 将数据取出，取出时为String类型
     * @param context 上下文
     * @param key key值
     * @return 取出存储数据
     */
    public static String spOutput(Context context,String key){
        return context.getSharedPreferences(key, MODE_PRIVATE).getString(key, null);
    }
}
