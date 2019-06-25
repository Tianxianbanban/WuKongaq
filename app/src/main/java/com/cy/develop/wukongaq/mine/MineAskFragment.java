package com.cy.develop.wukongaq.mine;


import android.view.View;
import android.widget.TextView;

import com.cy.develop.wukongaq.BaseFragment;
import com.cy.develop.wukongaq.R;

public class MineAskFragment extends BaseFragment {

    TextView tipsAsk;

    @Override
    protected View initView() {
        View view=View.inflate(baseContext,R.layout.mine_fragment_ask,null);
        tipsAsk=view.findViewById(R.id.mine_content_null_common_note);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        tipsAsk.setText(R.string.mine_content_null_tip3);
    }
}
