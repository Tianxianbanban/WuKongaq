package com.cy.develop.wukongaq.view.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Matrix;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cy.develop.wukongaq.R;
import com.cy.develop.wukongaq.modle.bean.UserInfoBean;
import com.cy.develop.wukongaq.util.ToastUtil;
import com.cy.develop.wukongaq.view.activity.MinePersonalActivity;
import com.cy.develop.wukongaq.view.fragment.mine.MineAskFragment;
import com.cy.develop.wukongaq.util.adapter.MineContentFragmentPagerAdapter;
import com.cy.develop.wukongaq.view.fragment.mine.MineDynamicFragment;
import com.cy.develop.wukongaq.view.fragment.mine.MineReplyFragment;
import com.cy.develop.wukongaq.presenter.IMinePresenter;
import com.cy.develop.wukongaq.presenter.MinePresenter;
import com.cy.develop.wukongaq.util.SpUtil;
import com.cy.develop.wukongaq.view.IMineView;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * https://blog.csdn.net/lvyoujt/article/details/51130057#commentBox
 * https://blog.csdn.net/lvyoujt/article/details/51138744
 */

public class MineFragment extends BaseFragment implements IMineView,View.OnClickListener,
        ViewPager.OnPageChangeListener{

    private static final String TAG = "MineFragment";

    IMinePresenter iMinePresenter;
    private CircleImageView circleImageView;
    private TextView name;
    private TextView introduction;
    private TextView funs;
    private TextView attention;
    private TextView agree;

    private ViewPager viewpager;
    private ImageView imgCursor;//标题下标指示
    private TextView txOne;//三个板块标题
    private TextView txTwo;
    private TextView txThree;
    private int offset = 0;//移动条图片的偏移量
    private int currIndex = 0;//当前页面的编号
    private int one = 0; //移动条滑动一页的距离
    private int two = 0; //滑动条移动两页的距离

    @Override
    protected View initView() {
        View view=View.inflate(baseContext,R.layout.mine_fragment,null);

        circleImageView=view.findViewById(R.id.mine_user_head);
//        String url="https://wukongaq-1257009269.cos.ap-chengdu.myqcloud.com/head_photo/u2";
//        Glide.with(this).load(url).into(circleImageView);
        name=view.findViewById(R.id.mine_user_name);
        introduction=view.findViewById(R.id.mine_user_introduction);
        funs=view.findViewById(R.id.mine_user_funs);
        attention=view.findViewById(R.id.mine_user_attention);
        agree=view.findViewById(R.id.mine_user_agree);

        txOne=view.findViewById(R.id.mine_content_one_tx);
        txTwo=view.findViewById(R.id.mine_content_two_tx);
        txThree=view.findViewById(R.id.mine_content_three_tx);
        imgCursor=view.findViewById(R.id.mine_content_cursor_img);
        viewpager=view.findViewById(R.id.mine_content_viewpager);

        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        List<Fragment> list = new ArrayList<>();
        list.add(new MineDynamicFragment());
        list.add(new MineReplyFragment());
        list.add(new MineAskFragment());
        MineContentFragmentPagerAdapter adapter = new MineContentFragmentPagerAdapter(getChildFragmentManager(), list);
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(0);

        iMinePresenter=new MinePresenter(baseContext,this);
//        changeUserInfo(null);
        iMinePresenter.changeUserInfo(null);


        //下划线动画的相关设置：
//        bmpWidth = BitmapFactory.decodeResource(getResources(), R.mipmap.mine_mark).getWidth();// 获取图片宽度
        // 移动条图片的长度
        int bmpWidth = txOne.getWidth();
//        DisplayMetrics dm = new DisplayMetrics();
//        getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
        DisplayMetrics dm=getResources().getDisplayMetrics();
        int screenW = dm.widthPixels;// 获取分辨率宽度
        offset = (screenW / 3 - bmpWidth) / 2;// 计算偏移量
        Matrix matrix = new Matrix();
        matrix.postTranslate(offset, 0);
        imgCursor.setImageMatrix(matrix);// 设置动画初始位置
        //移动的距离
        one = (offset * 2 + bmpWidth)/2;// 移动一页的偏移量,比如1->2,或者2->3
        two = one * 2;// 移动两页的偏移量,比如1直接跳3
    }

    @Override
    protected void setListener() {
        super.setListener();
        name.setOnClickListener(this);
        introduction.setOnClickListener(this);
        txOne.setOnClickListener(this);
        txTwo.setOnClickListener(this);
        txThree.setOnClickListener(this);
        viewpager.addOnPageChangeListener(this);
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
                // arg0 :当前页面，及你点击滑动的页面；arg1:当前页面偏移的百分比；arg2:当前页面偏移的像素位置
                Log.e(TAG, "onPageScrolled------>arg0：" + i + "\nonPageScrolled------>arg1:" + v + "\nonPageScrolled------>arg2:" + i1);
            }

            @Override
            public void onPageSelected(int i) {
                // arg0是当前选中的页面的Position
                Log.e(TAG, "onPageSelected------>" + i);
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                //arg0 ==1的时表示正在滑动，arg0==2的时表示滑动完毕了，arg0==0的时表示什么都没做。
                if (i == 0) {
                    Log.e(TAG, "onPageScrollStateChanged------>0");
                } else if (i == 1) {
                    Log.e(TAG, "onPageScrollStateChanged------>1");
                } else if (i == 2) {
                    Log.e(TAG, "onPageScrollStateChanged------>2");
                }
            }
        });
    }

    @Override
    public void onPageSelected(int index) {
        Animation animation = null;
        switch (index) {
            case 0:
                if (currIndex == 1) {
                    animation = new TranslateAnimation(one, 0, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(two, 0, 0, 0);
                }
                break;
            case 1:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, one, 0, 0);
                } else if (currIndex == 2) {
                    animation = new TranslateAnimation(two, one, 0, 0);
                }
                break;
            case 2:
                if (currIndex == 0) {
                    animation = new TranslateAnimation(offset, two, 0, 0);
                } else if (currIndex == 1) {
                    animation = new TranslateAnimation(one, two, 0, 0);
                }
                break;
        }
        currIndex = index;
        assert animation != null;
        animation.setFillAfter(true);// true表示图片停在动画结束位置
        animation.setDuration(300); //设置动画时间为300毫秒
        imgCursor.startAnimation(animation);//开始动画
    }


    @Override
    public void onPageScrollStateChanged(int i) {

    }

    @Override
    public void onPageScrolled(int i, float v, int i1) {

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mine_user_name:
                Intent intent=new Intent(baseContext,MinePersonalActivity.class);
                startActivity(intent);
                break;
            case R.id.mine_user_introduction:
//                String nameText=name.getText().toString();
//                String introductionText=introduction.getText().toString();
//                iMinePresenter.changeUserInfo(name.getText().toString());
                iMinePresenter.changeUserInfo("personalProfile");
                break;
            case R.id.mine_content_one_tx:
                viewpager.setCurrentItem(0);
                break;
            case R.id.mine_content_two_tx:
                viewpager.setCurrentItem(1);
                break;
            case R.id.mine_content_three_tx:
                viewpager.setCurrentItem(2);
                break;
        }
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void changeUserInfo(final UserInfoBean userInfoBean) {
        if (userInfoBean!=null){
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    String nameText = userInfoBean.getData().getUserInfo().getNickName();
                    String introductionText = userInfoBean.getData().getUserInfo().getPersonalProfile();
                    name.setText(nameText);
                    introduction.setText(" " + introductionText + " ");
                    funs.setText(userInfoBean.getData().getUserInfo().getFans() + "粉丝");
                    attention.setText(userInfoBean.getData().getUserInfo().getAttention() + "关注");
                    agree.setText(userInfoBean.getData().getUserInfo().getApproval() + "赞同");
                }
            });
        }
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
}

