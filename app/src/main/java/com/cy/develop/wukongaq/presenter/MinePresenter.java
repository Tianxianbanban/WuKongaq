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
import com.cy.develop.wukongaq.modle.bean.json.RetJson;
import com.cy.develop.wukongaq.util.OkHttpUtil;
import com.cy.develop.wukongaq.util.SpUtil;
import com.cy.develop.wukongaq.view.IMineView;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MinePresenter implements IMinePresenter{
    private static final String TAG = "MinePresenter";

    Context context;
    IMineView iMineView;

    Dialog dialog;

    public MinePresenter(Context context,IMineView iMineView){
        this.context=context;
        this.iMineView=iMineView;
    }

    @Override
    public void changeUserInfo(final String item) {
        if (item!=null){
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
            if (item.equals("nickName")){
                tip.setText("请输入你的昵称");
                content.setHint("为自己起一个特别的名字吧");
            }else if (item.equals("personalProfile")){
                tip.setText("请输入自我介绍");
            }
            cancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dialog.dismiss();
                }
            });
            commit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final String contentText=content.getText().toString();
                    RequestBody requestBody = new FormBody.Builder()
                            .add(item, contentText)
                            .build();
                    OkHttpUtil.sendOkHttpRequest(context, OkHttpUtil.changeInfo, OkHttpUtil.method[2], requestBody, new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Log.i(TAG, "onFailure: "+e.getMessage());
                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            String responseData=response.body().string();
                            Log.d(TAG, "show-test-onResponse: 修改个人信息"+responseData);
                            if (response.code()==200){
                                try {
                                    Gson gson=new Gson();
                                    RetJson retJson=gson.fromJson(responseData,RetJson.class);
                                    if (retJson.getCode()==0){
                                        iMineView.showServiceInfo("修改成功！");
                                        String jsonBefore=SpUtil.spOutput(context,"userInfo");
                                        UserInfoBean userInfoBean=gson.fromJson(jsonBefore,UserInfoBean.class);
                                        if (item.equals("nickName")){
                                            userInfoBean.getData().getUserInfo().setNickName(contentText);
                                        }else if (item.equals("personalProfile")){
                                            userInfoBean.getData().getUserInfo().setPersonalProfile(contentText);
                                        }
                                        iMineView.changeUserInfo(userInfoBean);
                                        SpUtil.spInput(context,"userInfo",gson.toJson(userInfoBean));
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });

                    dialog.dismiss();
                }
            });
        }else{
            String uid=SpUtil.spOutput(context,"uid").split("\\.")[0];
            OkHttpUtil.sendOkHttpRequest(context, OkHttpUtil.info+uid, OkHttpUtil.method[0], null, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.d(TAG, "onFailure: "+e.getMessage());
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    String responseData=response.body().string();
                    Log.d(TAG, "show-test-onResponse: 获取个人信息"+responseData);
                    if (response.code()==200){
                        try {
                            Gson gson=new Gson();
                            UserInfoBean userInfoBean=gson.fromJson(responseData,UserInfoBean.class);
                            if (userInfoBean.getCode()==0){
                                iMineView.changeUserInfo(userInfoBean);//这个地方只能够改掉个人介绍
                                SpUtil.spInput(context,"userInfo",responseData);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }
            });
        }

    }

}
