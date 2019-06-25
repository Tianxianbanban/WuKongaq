package com.cy.develop.wukongaq.mine;

import android.view.View;
import android.widget.TextView;

import com.cy.develop.wukongaq.BaseFragment;
import com.cy.develop.wukongaq.R;

public class MineDynamicFragment extends BaseFragment {

    TextView tipsDynamic;

    @Override
    protected View initView() {
        View view= View.inflate(baseContext,R.layout.mine_fragment_dynamic,null);
        tipsDynamic=view.findViewById(R.id.mine_content_null_common_note );
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        tipsDynamic.setText(R.string.mine_content_null_tip1);
    }
}
