package com.cy.develop.wukongaq.presenter;

import android.content.Context;

import com.cy.develop.wukongaq.util.OkHttpUtil;
import com.cy.develop.wukongaq.view.IHomeAskOrAnswerView;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class HomeAskPresenter implements IHomeAskOrAnswerPresenter {
    Context context;
    IHomeAskOrAnswerView iHomeAskOrAnswerView;

    public HomeAskPresenter(Context context, IHomeAskOrAnswerView iHomeAskOrAnswerView) {
        this.context = context;
        this.iHomeAskOrAnswerView = iHomeAskOrAnswerView;
    }

    @Override
    public void commitQuestion(String title,String questionContent) {
        RequestBody requestBody = new FormBody.Builder()
                .add("title", title)
                .add("description", questionContent)
                .build();
        OkHttpUtil.callSendOkHttpRequest(context,OkHttpUtil.commitProblem,OkHttpUtil.urlName[3],"post",requestBody);
        iHomeAskOrAnswerView.finishView();
    }
}
