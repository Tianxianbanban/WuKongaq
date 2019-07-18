package com.cy.develop.wukongaq.view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.cy.develop.wukongaq.R;
import com.cy.develop.wukongaq.presenter.HomeAnswerPersenter;
import com.cy.develop.wukongaq.presenter.IHomeAskOrAnswerPresenter;
import com.cy.develop.wukongaq.util.ToastUtil;
import com.cy.develop.wukongaq.view.IHomeAskOrAnswerView;

public class HomeRecommendReplyActivity extends BaseActivity implements IHomeAskOrAnswerView, View.OnClickListener{

    TextView cancel;
    TextView commit;
    TextView question;
    TextView answerInput;
    int pid;
    String title;
    IHomeAskOrAnswerPresenter iHomeAskOrAnswerPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_recommend_reply_activity);
        initView();
        initData();
        setListener();
    }

    private void initView(){
        cancel=findViewById(R.id.home_recommend_reply_cancel_tv);
        commit=findViewById(R.id.home_recommend_reply_commit_tv);
        question=findViewById(R.id.home_recommend_reply_question_tv);
        answerInput=findViewById(R.id.home_recommend_reply_answer_et);
    }

    private void initData(){
        iHomeAskOrAnswerPresenter=new HomeAnswerPersenter(this,this);
        Intent intent=getIntent();
        pid=intent.getIntExtra("pid",-1);
        title=intent.getStringExtra("title");
        question.setText(title);
    }

    private void setListener(){
        cancel.setOnClickListener(this);
        commit.setOnClickListener(this);
        question.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_recommend_reply_cancel_tv:
                finish();
                break;
            case R.id.home_recommend_reply_commit_tv:
                String answerInputText=answerInput.getText().toString();
                iHomeAskOrAnswerPresenter.commitQuestion(pid+"",answerInputText);
                break;
        }
    }

    @Override
    public void showServiceInfo(final String info) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtil.showToast(HomeRecommendReplyActivity.this,info);
            }
        });
    }

    @Override
    public void finishView() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtil.showToast(HomeRecommendReplyActivity.this,"答案提交成功！");
                finish();
            }
        });
    }
}
