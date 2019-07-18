package com.cy.develop.wukongaq.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import com.cy.develop.wukongaq.R;
import com.cy.develop.wukongaq.util.adapter.HomeRecommendAdapter;
import com.cy.develop.wukongaq.modle.bean.json.HomeRecommendBean;
import com.cy.develop.wukongaq.util.OkHttpUtil;
import com.cy.develop.wukongaq.util.ToastUtil;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public class HomeFragment extends BaseFragment implements SwipeRefreshLayout.OnRefreshListener{
    private static final String TAG = "HomeFragment";

    SwipeRefreshLayout swipeRefreshLayout;//下拉刷新控件
    RecyclerView recyclerView;//列表显示
    HomeRecommendAdapter homeRecommendAdapter;//列表适配器

    @Override
    protected View initView() {//界面初始化
        View view= View.inflate(baseContext,R.layout.home_fragment,null);
        swipeRefreshLayout=view.findViewById(R.id.home_recommend_refresh_srl);
        recyclerView=view.findViewById(R.id.home_recommend_rv);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        onRefresh();
    }

    @Override
    protected void setListener() {
        super.setListener();
        swipeRefreshLayout.setOnRefreshListener(this);
    }

    /**
     * 进行刷新，更新数据，网络请求获取数据，
     * 如果获取失败，提示用户服务器故障，
     * 获取成功，则将json数据进行解析，整理出推荐内容列表，
     * 将List<HomeRecommendBean.Data.Item>中的内容设置进适配器中进行内容展示，
     * 并且设置点击事件，供用户对感兴趣的内容进行查看。
     */
    @Override
    public void onRefresh() {
        //数据刷新
        //网络请求获取数据
        OkHttpUtil.sendOkHttpRequest(baseContext, OkHttpUtil.recommend, OkHttpUtil.method[0], null, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        ToastUtil.showToast(baseContext, "服务器故障");
                    }
                });
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData;
                responseData = Objects.requireNonNull(response.body()).string();
                Log.d(TAG, "onResponse: 首页获取推荐内容" + responseData);
                if (response.code() == 200) {
                    final Gson gson = new Gson();
                    final HomeRecommendBean homeRecommendBean = gson.fromJson(responseData, HomeRecommendBean.class);
                    Objects.requireNonNull(getActivity()).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            List<HomeRecommendBean.Data.Item> recommendCurrent = homeRecommendBean.getData().getRecommends();
                            LinearLayoutManager layoutManager = new LinearLayoutManager(baseContext);
                            recyclerView.setLayoutManager(layoutManager);
                            homeRecommendAdapter = new HomeRecommendAdapter(recommendCurrent);
                            recyclerView.setAdapter(homeRecommendAdapter);
                            homeRecommendAdapter.notifyDataSetChanged();
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    });
                }
            }
        });
    }
}
