package com.cy.develop.wukongaq.view.fragment;


import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cy.develop.wukongaq.R;
import com.cy.develop.wukongaq.util.adapter.MessageMenuAdapter;
import com.cy.develop.wukongaq.modle.bean.MessageMenuItemBean;

import java.util.ArrayList;
import java.util.List;

public class MessageFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private List<MessageMenuItemBean> messageMenuItemBeans=new ArrayList<>();

    @Override
    protected View initView() {
        View view= View.inflate(baseContext,R.layout.message_fragment,null);
        recyclerView=view.findViewById(R.id.message_menu_rv);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        messageMenuItemBeans.add(new MessageMenuItemBean(R.drawable.message_1,R.string.message_item_1));
        messageMenuItemBeans.add(new MessageMenuItemBean(R.drawable.message_2,R.string.message_item_2));
        messageMenuItemBeans.add(new MessageMenuItemBean(R.drawable.message_3,R.string.message_item_3));
        messageMenuItemBeans.add(new MessageMenuItemBean(R.drawable.message_4,R.string.message_item_4));
        LinearLayoutManager layoutManager=new LinearLayoutManager(baseContext);
        recyclerView.setLayoutManager(layoutManager);
        MessageMenuAdapter adapter=new MessageMenuAdapter(messageMenuItemBeans);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void setListener() {
        super.setListener();
    }
}
