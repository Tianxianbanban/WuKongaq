package com.cy.develop.wukongaq.view.fragment.mine;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.cy.develop.wukongaq.view.activity.MineReplyTodoActivity;
import com.cy.develop.wukongaq.view.fragment.BaseFragment;
import com.cy.develop.wukongaq.R;
import com.cy.develop.wukongaq.util.ToastUtil;

public class MineReplyFragment extends BaseFragment implements View.OnClickListener{

    TextView tipsReply;
    Button toReply;

    @Override
    protected View initView() {
        View view=View.inflate(baseContext,R.layout.mine_fragment_reply,null);
        tipsReply=view.findViewById(R.id.mine_content_null_common_note);
        toReply=view.findViewById(R.id.mine_content_replay_todo_btn);
        return view;
    }


    @Override
    protected void initData() {
        super.initData();
        tipsReply.setText(R.string.mine_content_null_tip2);
    }

    @Override
    protected void setListener() {
        super.setListener();
        toReply.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        super.onClick(v);
        switch (v.getId()){
            case R.id.mine_content_replay_todo_btn:
                ToastUtil.showToast(baseContext,"去回答问题");
                Intent intent=new Intent(baseContext,MineReplyTodoActivity.class);
                startActivity(intent);
                break;
        }
    }
}
