package com.cy.develop.wukongaq.view.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.cy.develop.wukongaq.R;
import com.cy.develop.wukongaq.presenter.HomeAskPresenter;
import com.cy.develop.wukongaq.presenter.IHomeAskOrAnswerPresenter;
import com.cy.develop.wukongaq.util.ToastUtil;
import com.cy.develop.wukongaq.view.IHomeAskOrAnswerView;

public class HomeAskActivity extends BaseActivity implements IHomeAskOrAnswerView, View.OnClickListener{

    TextView cancel;
    TextView commit;
    EditText title;
    EditText content;

    IHomeAskOrAnswerPresenter iHomeAskOrAnswerPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_ask_activity);

        cancel=findViewById(R.id.home_ask_cancel_tx);
        commit=findViewById(R.id.home_ask_commit_tx);
        title=findViewById(R.id.home_ask_content_title_et);
        content=findViewById(R.id.home_ask_content_et);

        initData();

        cancel.setOnClickListener(this);
        commit.setOnClickListener(this);
    }


    private void initData(){
        iHomeAskOrAnswerPresenter =new HomeAskPresenter(this,this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_ask_cancel_tx:
                finish();
                break;
            case R.id.home_ask_commit_tx:
                String titleText=title.getText().toString();
                String contentText=content.getText().toString();
                if (titleText==null||contentText==null){
                    ToastUtil.showToast(this,"标题或者描述不能为空哦~");
                }else{
                    iHomeAskOrAnswerPresenter.commitQuestion(titleText,contentText);
                }
                break;
        }
    }

    @Override
    public void finishView() {
//        if (){
            ToastUtil.showToast(this,"问题已提交！");
//        }else {
//            ToastUtil.showToast(this,"未知错误！");
//        }
        finish();
    }

    @Override
    public void showServiceInfo(String info) {

    }
}
