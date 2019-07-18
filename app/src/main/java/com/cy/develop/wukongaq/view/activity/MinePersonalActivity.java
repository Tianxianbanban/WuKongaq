package com.cy.develop.wukongaq.view.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import com.cy.develop.wukongaq.R;
import com.cy.develop.wukongaq.modle.bean.UserInfoBean;
import com.cy.develop.wukongaq.presenter.IMinePresenter;
import com.cy.develop.wukongaq.presenter.MinePresenter;
import com.cy.develop.wukongaq.util.SpUtil;
import com.cy.develop.wukongaq.util.ToastUtil;
import com.cy.develop.wukongaq.util.adapter.MinePersonalListAdapter;
import com.cy.develop.wukongaq.view.IMineView;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

public class MinePersonalActivity extends BaseActivity implements
        View.OnClickListener,IMineView {
    private static final String TAG = "MinePersonalActivity";

    List<String> itemList;
    IMinePresenter iMinePresenter;
    Button back;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_personal_activity);
        initView();
        initData();
        setListener();
    }

    private void initView(){
        back=findViewById(R.id.mine_personal_back_btn);
        listView=findViewById(R.id.mine_personal_item_lv);
    }

    private void setListener() {
        back.setOnClickListener(this);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String content=itemList.get(position);
                ToastUtil.showToast(MinePersonalActivity.this,content);
                if (position==1){
                    iMinePresenter.changeUserInfo("nickName");
                }else if (position==2){
                    iMinePresenter.changeUserInfo("personalProfile");
                }
            }
        });
    }

    private void initData(){
        try{
            String userInfoJson=SpUtil.spOutput(this,"userInfo");
            Log.d(TAG, "initData: 取出个人信息"+userInfoJson);
            Gson gson=new Gson();
            UserInfoBean userInfoBean=gson.fromJson(userInfoJson,UserInfoBean.class);
            String headUrl=userInfoBean.getData().getUserInfo().getHeadPhoto();
            String name=userInfoBean.getData().getUserInfo().getNickName();
            String introduction=userInfoBean.getData().getUserInfo().getPersonalProfile();

            iMinePresenter=new MinePresenter(this,this);

            itemList=new ArrayList<>();
            itemList.add(headUrl);
            itemList.add(name);
            itemList.add(introduction);

            MinePersonalListAdapter minePersonalListAdapter=new MinePersonalListAdapter(this,R.layout.mine_personal_item,itemList);
            listView.setAdapter(minePersonalListAdapter);

        }catch (Exception e){
            Log.i(TAG, "initData: "+e.getMessage());
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mine_personal_back_btn:
                finish();
                break;
        }
    }



    @Override
    public void changeUserInfo(UserInfoBean userInfoBean) {

    }

    @Override
    public void showServiceInfo(String info) {

    }
}
