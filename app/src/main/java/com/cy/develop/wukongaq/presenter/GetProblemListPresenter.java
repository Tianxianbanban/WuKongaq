package com.cy.develop.wukongaq.presenter;

import android.content.Context;
import android.util.Log;

import com.cy.develop.wukongaq.modle.bean.json.GetProblemListBean;
import com.cy.develop.wukongaq.util.OkHttpUtil;
import com.cy.develop.wukongaq.view.IShowProblemAnswerListView;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GetProblemListPresenter implements IGetProblemAnswerListPresenter {
    private static final String TAG = "GetProblemListPresenter";

    Context context;
    IShowProblemAnswerListView iShowProblemAnswerListView;

    public GetProblemListPresenter(Context context, IShowProblemAnswerListView iShowProblemAnswerListView) {
        this.context = context;
        this.iShowProblemAnswerListView = iShowProblemAnswerListView;
    }

    @Override
    public void getList(String id, String url, int pageNum, int pageSize) {
        RequestBody requestBody=new FormBody.Builder()
                .add("pageNum",pageNum+"")
                .add("pageSize",pageSize+"")
                .build();
        OkHttpUtil.sendOkHttpRequest(context, OkHttpUtil.getProblemList + id.split("\\.")[0] + "?pageNum="+pageNum+"&pageSize="+pageSize, OkHttpUtil.method[0], null, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                iShowProblemAnswerListView.showServiceInfo("服务器故障");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData=response.body().string();
                Log.d(TAG, "show-test-onResponse: 获取提问列表"+responseData);
                if (response.code()==200){
                    try{
                        Gson gson=new Gson();
                        GetProblemListBean getProblemListBean=gson.fromJson(responseData,GetProblemListBean.class);
                        iShowProblemAnswerListView.showList(getProblemListBean.getData().getProblemList().getProblems());
                    }catch (Exception e){
                        Log.i(TAG, "onResponse: "+e.getMessage());
                    }
                }
            }
        });
    }
}
