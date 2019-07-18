package com.cy.develop.wukongaq.view.activity;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.bottomnavigation.LabelVisibilityMode;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.cy.develop.wukongaq.modle.UserState;
import com.cy.develop.wukongaq.presenter.IMainPresenter;
import com.cy.develop.wukongaq.presenter.MainPresenter;
import com.cy.develop.wukongaq.view.IMainView;
import com.cy.develop.wukongaq.R;
import com.cy.develop.wukongaq.util.ToastUtil;
import com.cy.develop.wukongaq.view.fragment.AttentionFragment;
import com.cy.develop.wukongaq.view.fragment.HomeFragment;
import com.cy.develop.wukongaq.view.fragment.LoginFragment;
import com.cy.develop.wukongaq.view.fragment.MessageFragment;
import com.cy.develop.wukongaq.view.fragment.MineFragment;
import com.cy.develop.wukongaq.util.NetWorkUtils;


//主活动部分，用于切换各个碎片，供用户进行分类查询浏览
public class MainActivity extends BaseActivity implements IMainView, View.OnClickListener{
    IMainPresenter iMainPresenter;

    private BottomNavigationView bottomNavigationView;

    private Fragment homeFragment;
    private Fragment attentionFragment;
    private Fragment messageFragment;
    private Fragment mineFragment;
    private LoginFragment loginFragment;//在登录之前
    private Fragment currentFragment=null;

    //顶部标题栏引入的布局
    private View homeTitle;
    private View mineTitle;
    //首页
    private ImageView home_top_title_inquire_iv;
    private TextView home_top_title_center_tv;
    private TextView home_top_title_ask_tv;
    //我的
    private TextView mine_top_title_left_tx;
    private Button mine_top_title_right_btn1;
    private Button mine_top_title_right_btn2;
    private Button mine_top_title_right_btn3;


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        initView();
        initData();
        replaceFragment(currentFragment,homeFragment);
        NetWorkUtils.isNetworkAvailableToast(this);//检查网络状态
        setListener();


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.bnv_item_home:
                        replaceFragment(currentFragment,homeFragment);
                        changeTitle(1);
                        return true;
                    case R.id.bnv_item_attention:
                        replaceFragment(currentFragment,attentionFragment);
                        changeTitle(2);
                        return true;
                    case R.id.bnv_item_message:
                        replaceFragment(currentFragment,messageFragment);
                        changeTitle(3);
                        return true;
                    case R.id.bnv_item_mine:
//                        replaceFragment(currentFragment,loginFragment);
                        iMainPresenter.replaceFragment();
//                        replaceFragment(currentFragment,mineFragment);
//                        replaceFragment(currentFragment,loginFragment);
                        changeTitle(4);
                        return true;
                }
                return false;
            }
        });
    }

    @Override
    public void replaceMineLoginFragment() {
        if (UserState.isLogin){
            replaceFragment(currentFragment,mineFragment);
        }else{
            replaceFragment(currentFragment,loginFragment);
        }
    }

    private void initData() {
        iMainPresenter=new MainPresenter(this);
    }

//    @Override
    public void replaceFragment(Fragment mContent, Fragment to) {
        if (mContent!= to){
            currentFragment = to;
            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            //判断有没有添加
            if(!to.isAdded()){
                if (mContent != null){
                    ft.hide(mContent);
                }
                ft.add(R.id.fl,to).commit();
            }else{
                if (mContent != null){
                    ft.hide(mContent);
                }
                ft.show(to).commit();
            }
        }
    }



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void initView(){
        bottomNavigationView=findViewById(R.id.bnv);
        //使得label可见
        bottomNavigationView.setLabelVisibilityMode(LabelVisibilityMode.LABEL_VISIBILITY_LABELED);

        homeTitle=findViewById(R.id.include_home_title);
        mineTitle=findViewById(R.id.include_mine_title);

        home_top_title_inquire_iv=findViewById(R.id.home_top_title_inquire_iv);
        home_top_title_center_tv=findViewById(R.id.home_top_title_center_tv);
        home_top_title_ask_tv=findViewById(R.id.home_top_title_ask_tv);

        mine_top_title_left_tx=findViewById(R.id.mine_top_title_left_tx);
        mine_top_title_right_btn1=findViewById(R.id.mine_top_title_right_btn1);
        mine_top_title_right_btn2=findViewById(R.id.mine_top_title_right_btn2);
        mine_top_title_right_btn3=findViewById(R.id.mine_top_title_right_btn3);

        homeFragment=new HomeFragment();//首页碎片
        attentionFragment=new AttentionFragment();//关注碎片
        messageFragment=new MessageFragment();//消息碎片
        mineFragment=new MineFragment();//我的碎片
        loginFragment=new LoginFragment();//未登录前占位个人部分的登录碎片
    }

//    通过include引入布局的动态切换
//    private void changeTitle(int resourceId){
//        viewInclude.removeAllViews();
//        viewInclude.inflate(this,resourceId,viewInclude);
//    }

    private void setListener(){
        home_top_title_inquire_iv.setOnClickListener(this);
        home_top_title_center_tv.setOnClickListener(this);
        home_top_title_ask_tv.setOnClickListener(this);

        mine_top_title_left_tx.setOnClickListener(this);
        mine_top_title_right_btn1.setOnClickListener(this);
        mine_top_title_right_btn2.setOnClickListener(this);
        mine_top_title_right_btn3.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_top_title_inquire_iv:
//                ToastUtil.showToast(this,"查询");
//                Intent recomintent=new Intent(v.getContext(),HomeRecommendContentActivity.class);
//                v.getContext().startActivity(recomintent);
                break;
            case R.id.home_top_title_center_tv:
                ToastUtil.showToast(this,"推荐");
                break;
            case R.id.home_top_title_ask_tv:
//                ToastUtil.showToast(this,"提问");
                Intent intent=new Intent(this,HomeAskActivity.class);
                startActivity(intent);
                break;
            case R.id.mine_top_title_left_tx:
                ToastUtil.showToast(this,"好友");
                break;
            case R.id.mine_top_title_right_btn1:
//                ToastUtil.showToast(this,"微信");
                break;
            case R.id.mine_top_title_right_btn2:
                ToastUtil.showToast(this,"朋友圈");
                break;
            case R.id.mine_top_title_right_btn3:
                ToastUtil.showToast(this,"更多");
                break;
        }
    }



    private void changeTitle(int position){

        switch (position){
            case 1:
                homeTitle.setVisibility(View.VISIBLE);
                home_top_title_center_tv.setText("推荐");
                home_top_title_inquire_iv.setVisibility(View.VISIBLE);
                home_top_title_ask_tv.setVisibility(View.VISIBLE);
                mineTitle.setVisibility(View.INVISIBLE);
                break;
            case 2:
                homeTitle.setVisibility(View.VISIBLE);
                mineTitle.setVisibility(View.INVISIBLE);
                home_top_title_center_tv.setText("关注");
                home_top_title_inquire_iv.setVisibility(View.INVISIBLE);
                home_top_title_ask_tv.setVisibility(View.INVISIBLE);
                break;
            case 3:
                homeTitle.setVisibility(View.VISIBLE);
                mineTitle.setVisibility(View.INVISIBLE);
                home_top_title_center_tv.setText(R.string.message_title_center);
                home_top_title_inquire_iv.setVisibility(View.INVISIBLE);
                home_top_title_ask_tv.setVisibility(View.INVISIBLE);
                break;
            case 4:
                mineTitle.setVisibility(View.VISIBLE);
                homeTitle.setVisibility(View.INVISIBLE);
                break;
        }
    }


}
