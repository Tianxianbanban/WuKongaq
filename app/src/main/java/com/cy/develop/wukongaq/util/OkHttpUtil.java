package com.cy.develop.wukongaq.util;

import android.content.Context;
import android.util.Log;
import com.cy.develop.wukongaq.modle.bean.json.RetJson;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.util.Objects;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpUtil {
    private static final String TAG = "OkHttpUtil";
    public static String[] urlName={"login","info","changeInfo","commitProblem","recommend","problemInfo","commentList","answer","getProblemList","comment","deleteProblem","register"};
    public static String[] method={"get","post","put","delete"};
//    public static String url="https://wukongaq-1257009269.cos.ap-chengdu.myqcloud.com";
    public static String login="http://134.175.68.103:5213/user/login/";
    public static String register="http://134.175.68.103:5213/user/register";
    public static String info="http://134.175.68.103:5213/user/info/uid/";
    public static String changeInfo="http://134.175.68.103:5213/user/info/";
    public static String commitProblem="http://134.175.68.103:5213/problem";
    public static String recommend="http://134.175.68.103:5213/recommend";
    public static String problemInfo="http://134.175.68.103:5213/problem/info/";
    public static String commentList="http://134.175.68.103:5213/comment/list/";
    public static String answer="http://134.175.68.103:5213/answer/";
    public static String getProblemList="http://134.175.68.103:5213/problem/";
    public static String comment="http://134.175.68.103:5213/comment";
    public static String deleteProblem="http://134.175.68.103:5213/problem";


    /**
     * 发送网络请求，OkHttp封装
     * @param context 上下文
     * @param url url
     * @param method 请求方法
     * @param requestBody 请求体
     * @param callback 请求回调
     */
    public static void sendOkHttpRequest(Context context,String url,String method,RequestBody requestBody, Callback callback){
        OkHttpClient client=new OkHttpClient();
        String token=SpUtil.spOutput(context,"token");
        switch (method){
            case "get":
                if (token!=null){
                    Request requestGet=new Request.Builder()
                            .addHeader("token",token)
                            .url(url)
                            .build();
                    client.newCall(requestGet).enqueue(callback);
                }
                break;
            case "post":
                if (token==null){
                    Request requestPost=new Request.Builder()
                            .url(url)
                            .post(requestBody)
                            .build();
                    client.newCall(requestPost).enqueue(callback);
                }else{
                    Request requestPost=new Request.Builder()
                            .addHeader("token",token)
                            .url(url)
                            .post(requestBody)
                            .build();
                    client.newCall(requestPost).enqueue(callback);
                }
                break;
            case "put":
                Request requestPut=new Request.Builder()
                        .addHeader("token",token)
                        .url(url)
                        .put(requestBody)
                        .build();
                client.newCall(requestPut).enqueue(callback);
                break;
            case "delete":
                Request requestDelete=new Request.Builder()
                        .addHeader("token",token)
                        .url(url)
                        .delete(requestBody)
                        .build();
                client.newCall(requestDelete).enqueue(callback);
                break;
        }
    }

    public static void callSendOkHttpRequest(final Context context, final String url,
                                             final String urlName, String method, RequestBody requestBody){

        OkHttpUtil.sendOkHttpRequest(context,url, method, requestBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.d(TAG, "onFailure: "+e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData;
                responseData = Objects.requireNonNull(response.body()).string();
                Log.d(TAG, "show-test-onResponse: "+responseData);
                //判断解析,根据urlName分别解析
                if (response.code()==200) {
                    try {
                        Gson gson = new Gson();
                        RetJson retJson = gson.fromJson(responseData, new TypeToken<RetJson>() {}.getType());
                        if (retJson.getCode() == 0) {
                            switch (urlName){
                                case "login":
                                    if (retJson.getData().get("token")!=null){//token与uid
                                        String token=Objects.requireNonNull(retJson.getData().get("token")).toString();
                                        String uid=Objects.requireNonNull(retJson.getData().get("uid")).toString();
                                        SpUtil.spInput(context, "token", token);
                                        SpUtil.spInput(context, "uid", uid);
                                    }
                                    break;
                                case "info":
                                    if (retJson.getData().get("userInfo")!=null){//userInfo用户信息
                                        SpUtil.spInput(context, "userInfo", responseData);
                                    }
                                    break;
                                case "commitProblem":
                                    break;
                                case "problemInfo":
                                    SpUtil.spInput(context,"problemInfo",responseData);
                                    break;
                            }

                        }
                    } catch (JsonSyntaxException e) {
                        e.printStackTrace();
                        Log.i(TAG, "onResponse: "+e.getMessage());
                    }catch (Exception e){
                        Log.i(TAG, "onResponse: "+e.getMessage());
                    }
                }
            }
        });
    }

}
