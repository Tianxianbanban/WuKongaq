package com.cy.develop.wukongaq.presenter;


import android.content.Context;
import android.util.Log;

import com.cy.develop.wukongaq.modle.UserState;
import com.cy.develop.wukongaq.modle.bean.json.RetJson;
import com.cy.develop.wukongaq.view.ILoginDialog;
import com.cy.develop.wukongaq.util.OkHttpUtil;
import com.cy.develop.wukongaq.util.SpUtil;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

public class LoginDialogPresenter implements ILoginDialogPresenter {
    private static final String TAG = "LoginDialogPresenter";

    ILoginDialog iLoginDialog;
    Context context;

    public LoginDialogPresenter(Context context,ILoginDialog iLoginDialog){
        this.context=context;
        this.iLoginDialog=iLoginDialog;
    }


    @Override
    public void doLogin(String name, String password, final String todo) {
        if (todo.equals("先去注册")) {//登录
            RequestBody requestBody = new FormBody.Builder()
                    .add("password", password)
                    .build();
            String url=OkHttpUtil.login + name;
            OkHttpUtil.sendOkHttpRequest(context, url, OkHttpUtil.method[1], requestBody, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String responseData;
                    responseData = Objects.requireNonNull(response.body()).string();
                    Log.d(TAG, "show-test-onResponse: 登录"+responseData);
                    if (response.code()==200){
                        try{
                            Gson gson = new Gson();
                            RetJson retJson = gson.fromJson(responseData, new TypeToken<RetJson>() {}.getType());
                            String token=Objects.requireNonNull(retJson.getData().get("token")).toString();
                            String uid=Objects.requireNonNull(retJson.getData().get("uid")).toString();
                            if (token!=null){
                                UserState.isLogin=true;
                            }
                            SpUtil.spInput(context, "token", token);
                            SpUtil.spInput(context, "uid", uid);
                            Log.d(TAG, "onResponse: token"+token);
                            Log.d(TAG, "doLogin2: "+UserState.isLogin);

                            if (UserState.isLogin&&SpUtil.spOutput(context, "token") != null) {
                                Log.d(TAG, "doLogin3: "+UserState.isLogin);
                                iLoginDialog.onLoginResult(true, 100);
                                //登录成功后需要获取用户的基本信息适配进去
                                RequestBody infoRequestBody = new FormBody.Builder().build();
                                OkHttpUtil.callSendOkHttpRequest(context, OkHttpUtil.info + SpUtil.spOutput(context,"uid").split("\\.")[0],"info", "get", infoRequestBody);
                            } else {
                                iLoginDialog.onLoginResult(false, 100);
                            }

                        }catch (Exception e){
                            Log.i(TAG, "onResponse: "+e.getMessage());
                        }
                    }
                }
            });


        } else if (todo.equals("免密码登录")) {//注册
            RequestBody requestBody = new FormBody.Builder()
                    .add("username", name)
                    .add("password", password)
                    .build();
            OkHttpUtil.sendOkHttpRequest(context, OkHttpUtil.register, OkHttpUtil.method[1], requestBody, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {

                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String responseData;
                    responseData = Objects.requireNonNull(response.body()).string();
                    Log.d(TAG, "show-test-onResponse: 注册"+responseData);
                    if (response.code()==200){
                        try{
                            Gson gson = new Gson();
                            RetJson retJson = gson.fromJson(responseData, new TypeToken<RetJson>() {}.getType());
                            if (retJson.getCode()==0){
                                iLoginDialog.onLoginResult(true, 101);
                            }else{
                                iLoginDialog.onLoginResult(false, 101);
                            }
                        }catch (Exception e){
                            Log.i(TAG, "onResponse: "+e.getMessage());
                        }
                    }
                }
            });
        }
    }
}
