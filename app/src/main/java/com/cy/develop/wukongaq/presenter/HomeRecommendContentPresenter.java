package com.cy.develop.wukongaq.presenter;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.cy.develop.wukongaq.R;
import com.cy.develop.wukongaq.modle.bean.UserInfoBean;
import com.cy.develop.wukongaq.modle.bean.json.GetCommentsFirstGradeBean;
import com.cy.develop.wukongaq.modle.bean.json.RetJson;
import com.cy.develop.wukongaq.util.OkHttpUtil;
import com.cy.develop.wukongaq.util.SpUtil;
import com.cy.develop.wukongaq.view.IHomeRecommendContentView;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import java.io.IOException;
import java.util.Objects;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

public class HomeRecommendContentPresenter implements IHomeRecommendContentPresenter{
    private static final String TAG = "HomeRecommendContentPre";

    Dialog dialog;
    Context context;
    IHomeRecommendContentView iHomeRecommendContentView;

    public HomeRecommendContentPresenter(Context context, IHomeRecommendContentView iHomeRecommendContentView) {
        this.context = context;
        this.iHomeRecommendContentView = iHomeRecommendContentView;
    }

    @Override
    public void getContent() {
        String pid = SpUtil.spOutput(context, "pid");
        OkHttpUtil.sendOkHttpRequest(context, OkHttpUtil.problemInfo + pid, OkHttpUtil.method[0], null, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                iHomeRecommendContentView.showFailure("服务器故障");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData=Objects.requireNonNull(response.body()).string();
                Log.d(TAG, "show-test-onResponse: 获取问题详细信息"+responseData);
                if (response.code()==200){
                    try{
                        iHomeRecommendContentView.setContent(responseData);
                    }catch (JsonSyntaxException e){
                        e.printStackTrace();
                    }

                }
            }
        });

    }

    @Override
    public void getComment(int aid,int pageNumber,int pageSize) {

        String url=OkHttpUtil.commentList + aid+"?"+"pageNum="+pageNumber+"&pageSize="+pageSize;
        OkHttpUtil.sendOkHttpRequest(context, url, OkHttpUtil.method[0], null, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                iHomeRecommendContentView.showFailure("服务器故障");
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData=response.body().string();
                Log.d(TAG, "show-test-onResponse: 获取问题与答案的评论信息"+responseData);
                if (response.code()==200){
                    try {
                        Gson gson=new Gson();
                        GetCommentsFirstGradeBean getCommentsFirstGradeBean=gson.fromJson(responseData,GetCommentsFirstGradeBean.class);
                        iHomeRecommendContentView.setCommentContent(getCommentsFirstGradeBean.getData().getComment().getComments());
                        Log.d(TAG, "onResponse:某个评论"+getCommentsFirstGradeBean.getData().getComment().getComments().get(0).getContent());
                    }catch (Exception e){
                        Log.i(TAG, "onResponse: "+e.getMessage());
                    }
                }
            }
        });
    }


    @Override
    public void commitCommentsFirstGrade(final int aid) {
        View view = LayoutInflater.from(context).inflate(R.layout.mine_user_change_dialog, null);
        dialog=new Dialog(context);
        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM );
        window.setContentView(view);
        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
        window.setAttributes(lp);
        dialog.show();
        TextView tip=view.findViewById(R.id.mine_user_change_dialog_tip);
        final EditText content=view.findViewById(R.id.mine_user_change_dialog_et);
        Button cancel=view.findViewById(R.id.mine_user_change_dialog_cancel_btn);
        Button commit=view.findViewById(R.id.mine_user_change_dialog_commit_btn);
        tip.setText("");
        content.setHint("友善发言更容易收获小心心");


        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        commit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String contentText = content.getText().toString();
                RequestBody requestBody = new FormBody.Builder()
                        .add("aid", aid + "")
                        .add("content", contentText)
                        .build();
                OkHttpUtil.sendOkHttpRequest(context, OkHttpUtil.comment, OkHttpUtil.method[1], requestBody, new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        Log.i(TAG, "onFailure: " + e.getMessage());
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        String responseData = response.body().string();
                        Log.d(TAG, "show-test-onResponse: 添加一级评论" + responseData);
                        if (response.code() == 200) {
                            try {
                                Gson gson = new Gson();
                                RetJson retJson = gson.fromJson(responseData, RetJson.class);
                                if (retJson.getCode() == 0) {
                                    iHomeRecommendContentView.showFailure("评论成功！");
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
                dialog.dismiss();
                getContent();
            }
        });
    }

}