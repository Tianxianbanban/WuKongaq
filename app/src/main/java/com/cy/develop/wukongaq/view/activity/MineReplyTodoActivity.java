package com.cy.develop.wukongaq.view.activity;

import android.support.v4.widget.SwipeRefreshLayout;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.cy.develop.wukongaq.R;

public class MineReplyTodoActivity extends BaseActivity implements View.OnClickListener{

    Button back;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_reply_todo_activity);

        initView();
        initData();
        setListener();
    }

    private void initView(){
        back=findViewById(R.id.mine_reply_todo_back_btn);
        swipeRefreshLayout=findViewById(R.id.mine_reply_todo_question_list_refresh_srl);
        recyclerView=findViewById(R.id.mine_reply_todo_question_list_rv);
    }

    private void initData(){
        back.setOnClickListener(this);
    }

    private void setListener(){

    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.mine_reply_todo_back_btn:
                finish();
                break;
        }
    }
}
