package com.cy.develop.wukongaq.view.fragment.mine;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.cy.develop.wukongaq.presenter.GetProblemListPresenter;
import com.cy.develop.wukongaq.presenter.IGetProblemAnswerListPresenter;
import com.cy.develop.wukongaq.util.OkHttpUtil;
import com.cy.develop.wukongaq.util.SpUtil;
import com.cy.develop.wukongaq.util.ToastUtil;
import com.cy.develop.wukongaq.util.adapter.MineAskListAdapter;
import com.cy.develop.wukongaq.view.IShowProblemAnswerListView;
import com.cy.develop.wukongaq.view.fragment.BaseFragment;
import com.cy.develop.wukongaq.R;
import java.util.List;

public class MineAskFragment extends BaseFragment implements IShowProblemAnswerListView {
    private static final String TAG = "MineAskFragment";

    ImageView imageView;
    TextView tipsAsk;

    RecyclerView recyclerView;
    MineAskListAdapter mineAskListAdapter;

    IGetProblemAnswerListPresenter iGetProblemAnswerListPresenter;

    @Override
    protected View initView() {
        View view=View.inflate(baseContext,R.layout.mine_fragment_ask,null);
        imageView=view.findViewById(R.id.mine_content_common_null_iv);
        tipsAsk=view.findViewById(R.id.mine_content_null_common_note);
        recyclerView=view.findViewById(R.id.mine_ask_list_rv);
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        tipsAsk.setText(R.string.mine_content_null_tip3);
        iGetProblemAnswerListPresenter=new GetProblemListPresenter(baseContext,this);
        String uid=SpUtil.spOutput(baseContext,"uid");
        iGetProblemAnswerListPresenter.getList(uid,OkHttpUtil.getProblemList,0,5);
    }

    @Override
    public void showList(final List list) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LinearLayoutManager layoutManager = new LinearLayoutManager(baseContext);
                recyclerView.setLayoutManager(layoutManager);
                mineAskListAdapter = new MineAskListAdapter(list);
                recyclerView.setAdapter(mineAskListAdapter);
                mineAskListAdapter.notifyDataSetChanged();
                if (list.size()>0){
                    imageView.setVisibility(View.INVISIBLE);
                    tipsAsk.setVisibility(View.INVISIBLE);
                }else {
                imageView.setVisibility(View.VISIBLE);
                tipsAsk.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void showServiceInfo(final String info) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtil.showToast(baseContext,info);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }
}
