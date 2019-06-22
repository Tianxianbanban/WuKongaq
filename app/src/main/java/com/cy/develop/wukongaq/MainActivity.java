package com.cy.develop.wukongaq;

import android.graphics.Color;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.bottomnavigation.LabelVisibilityMode;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private BottomNavigationView bottomNavigationView;

    private Fragment homeFragment;
    private Fragment attentionFragment;
    private Fragment messageFragment;
    private Fragment mineFragment;
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

        //设置透明状态栏并且让状态栏的显示信息颜色与白色背景有所区别
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }



        initView();
        replaceFragment(currentFragment,homeFragment);
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
                        replaceFragment(currentFragment,mineFragment);
                        changeTitle(4);
                        return true;
                }
                return false;
            }
        });
    }


    public void replaceFragment(Fragment mContent,Fragment to){
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

        homeFragment=new HomeFragment();
        attentionFragment=new AttentionFragment();
        messageFragment=new MessageFragment();
        mineFragment=new MineFragment();

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
                ToastUtil.showToast(this,"查询");
                break;
            case R.id.home_top_title_center_tv:
                ToastUtil.showToast(this,"推荐");
                break;
            case R.id.home_top_title_ask_tv:
                ToastUtil.showToast(this,"提问");
                break;
            case R.id.mine_top_title_left_tx:
                ToastUtil.showToast(this,"好友");
                break;
            case R.id.mine_top_title_right_btn1:
                ToastUtil.showToast(this,"微信");
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
