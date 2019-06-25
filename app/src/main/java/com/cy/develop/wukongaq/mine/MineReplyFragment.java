package com.cy.develop.wukongaq.mine;

import android.view.View;
import android.widget.TextView;

import com.cy.develop.wukongaq.BaseFragment;
import com.cy.develop.wukongaq.R;

public class MineReplyFragment extends BaseFragment {

    TextView tipsReply;

    @Override
    protected View initView() {
        View view=View.inflate(baseContext,R.layout.mine_fragment_reply,null);
        tipsReply=view.findViewById(R.id.mine_content_null_common_note);
        return view;
    }


    @Override
    protected void initData() {
        super.initData();
        tipsReply.setText(R.string.mine_content_null_tip2);
    }
}
