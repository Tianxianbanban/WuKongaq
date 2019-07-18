package com.cy.develop.wukongaq.view;

import android.annotation.SuppressLint;
import android.app.Activity;
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
import com.cy.develop.wukongaq.modle.UserState;
import com.cy.develop.wukongaq.presenter.ILoginDialogPresenter;
import com.cy.develop.wukongaq.presenter.IMainPresenter;
import com.cy.develop.wukongaq.presenter.LoginDialogPresenter;
import com.cy.develop.wukongaq.presenter.MainPresenter;
import com.cy.develop.wukongaq.util.ToastUtil;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;
import java.util.Objects;

public class LoginDialog implements ILoginDialog,View.OnClickListener{
    private static final String TAG = "LoginDialog";
    private Context context;
    private EditText phoneInput;
    private EditText passwordInput;
    private TextView todo;
    private Dialog dialog;
    private ILoginDialogPresenter iLoginDialogPresenter;
    private IMainPresenter iMainPresenter;


    public LoginDialog(Context context){
        this.context=context;
        iMainPresenter=new MainPresenter((IMainView) getCurrentActivity());
    }

    public void show(){
        //    int resourceId;
        @SuppressLint("InflateParams") View view = LayoutInflater.from(context).inflate(R.layout.login_dialog_login, null);
        dialog=new Dialog(context);

        Window window = dialog.getWindow();
        window.setGravity(Gravity.BOTTOM );
        window.setContentView(view);

        WindowManager.LayoutParams lp = window.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        window.setAttributes(lp);
        dialog.show();

        //在弹框内部
        phoneInput= view.findViewById(R.id.login_common_phone_et);
        passwordInput= view.findViewById(R.id.login_common_password_et);
        Button accessBtn = view.findViewById(R.id.login_common_access_btn);
        todo= view.findViewById(R.id.login_dialog_tip);
        iLoginDialogPresenter=new LoginDialogPresenter(context,this);


        accessBtn.setOnClickListener(this);
        todo.setOnClickListener(this);
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_common_access_btn:
                String todoText=todo.getText().toString();
                String phoneText=phoneInput.getText().toString().trim();
                String passwordText=passwordInput.getText().toString().trim();
                iLoginDialogPresenter.doLogin(phoneText,passwordText,todoText);
//                iLoginDialogPresenter.doLogin("18574651516","123456",todoText);
//                iLoginDialogPresenter.doLogin("18100738792","123456",todoText);
                break;
            case R.id.login_dialog_tip:
                if (todo.getText().equals("先去注册")){
                    todo.setText(R.string.login_dialog_login_other);
                }else{
                    todo.setText(R.string.login_dialog_register);
                }
                break;
        }
    }


    @Override
    public void onClearText() {
        phoneInput.setText("");
        passwordInput.setText("");
        ToastUtil.showToast(context,"请重新输入!");
    }

    @Override
    public void onLoginResult(final Boolean result, final int code) {
        Objects.requireNonNull(getCurrentActivity()).runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (code==100){//登录
                    if (result){
                        Log.d(TAG, "doLogin4: "+UserState.isLogin);
                        ToastUtil.showToast(context,"登录成功"+code);
                        dialog.dismiss();
                        iMainPresenter.replaceFragment();
                    }else {
                        ToastUtil.showToast(context,"登录失败"+code);
                        onClearText();
                    }
                }else if (code==101){//注册
                    if (result){
                        ToastUtil.showToast(context,"注册成功"+code);
                    }else {
                        ToastUtil.showToast(context,"注册失败"+code);
                        onClearText();
                    }
                }
            }
        });

    }


    private static Activity getCurrentActivity() {
        try {
            @SuppressLint("PrivateApi") Class activityThreadClass = Class.forName("android.app.ActivityThread");
            Object activityThread = activityThreadClass.getMethod("currentActivityThread").invoke(
                    null);
            Field activitiesField = activityThreadClass.getDeclaredField("mActivities");
            activitiesField.setAccessible(true);
            Map activities = (Map) activitiesField.get(activityThread);
            for (Object activityRecord : activities.values()) {
                Class activityRecordClass = activityRecord.getClass();
                Field pausedField = activityRecordClass.getDeclaredField("paused");
                pausedField.setAccessible(true);
                if (!pausedField.getBoolean(activityRecord)) {
                    Field activityField = activityRecordClass.getDeclaredField("activity");
                    activityField.setAccessible(true);
                    Activity activity = (Activity) activityField.get(activityRecord);
                    return activity;
                }
            }
        } catch (ClassNotFoundException | InvocationTargetException | NoSuchMethodException | NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }


}




