package com.cy.develop.wukongaq.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class NetWorkUtils {
    /**
     * 对网络状态进行判断
     * @param context 上下文
     */
    public static void isNetworkAvailableToast(Context context){
        if (context != null) {
            // 获取手机所有连接管理对象(包括对wi-fi,net等连接的管理)
            ConnectivityManager manager =(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
            // 获取NetworkInfo对象
            NetworkInfo networkInfo =manager.getActiveNetworkInfo();//获取当前连接的可用网络
            //判断NetworkInfo对象是否为空
            if (networkInfo != null) {
                if (!networkInfo.isAvailable()){
                    ToastUtil.showToast(context,"当前无可用网络！");
                }
            }
        }
    }

}
