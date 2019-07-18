package com.cy.develop.wukongaq.presenter;

import android.content.Context;
import android.util.Log;

import com.cy.develop.wukongaq.modle.bean.json.RetJson;
import com.cy.develop.wukongaq.util.OkHttpUtil;
import com.cy.develop.wukongaq.view.IHomeAskOrAnswerView;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HomeAnswerPersenter implements IHomeAskOrAnswerPresenter {
    private static final String TAG = "HomeAnswerPersenter";

    Context context;
    IHomeAskOrAnswerView iHomeAskOrAnswerView;

    public HomeAnswerPersenter(Context context, IHomeAskOrAnswerView iHomeAskOrAnswerView) {
        this.context = context;
        this.iHomeAskOrAnswerView = iHomeAskOrAnswerView;
    }

    @Override
    public void commitQuestion(String pid, String answer) {
        RequestBody requestBody=new FormBody.Builder()
                .add("pid",pid)
                .add("content",answer)
                .build();
        OkHttpUtil.sendOkHttpRequest(context, OkHttpUtil.answer, OkHttpUtil.method[1], requestBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                iHomeAskOrAnswerView.showServiceInfo("服务器故障");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData=response.body().string();
                Log.d(TAG, "show-test-onResponse: 提交答案"+responseData);
                if (response.code()==200){
                    try {
                        Gson gson=new Gson();
                        RetJson retJson=gson.fromJson(responseData,RetJson.class);
                        if (retJson.getCode()==0){
                            iHomeAskOrAnswerView.finishView();
                        }
                    }catch (Exception e){
                        Log.i(TAG, "onResponse: "+e.getMessage());
                    }
                }else {
                    iHomeAskOrAnswerView.showServiceInfo("请稍后再试");
                }
            }
        });
    }
}
