package com.cy.develop.wukongaq.view.fragment;

import android.view.View;
import android.widget.ImageView;

import com.cy.develop.wukongaq.view.LoginDialog;
import com.cy.develop.wukongaq.R;

public class LoginFragment extends BaseFragment {
    ImageView phone;

    LoginDialog loginDialogLogin;

    @Override
    protected View initView() {
        View view=View.inflate(baseContext,R.layout.login_fragment,null);
        phone=view.findViewById(R.id.login_menu_1);
        return view;
    }

    @Override
    protected void setListener() {
        super.setListener();
        phone.setOnClickListener(this);

    }

    @Override
    protected void initData() {
        super.initData();
        loginDialogLogin=new LoginDialog(baseContext);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.login_menu_1:
                loginDialogLogin.show();
                break;
        }
    }



}
