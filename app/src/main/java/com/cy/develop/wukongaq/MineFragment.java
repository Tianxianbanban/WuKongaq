package com.cy.develop.wukongaq;

import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.cy.develop.wukongaq.mine.MineAskFragment;
import com.cy.develop.wukongaq.mine.MineContentFragmentPagerAdapter;
import com.cy.develop.wukongaq.mine.MineDynamicFragment;
import com.cy.develop.wukongaq.mine.MineReplyFragment;

import java.util.ArrayList;
import java.util.List;



/**
 * https://blog.csdn.net/lvyoujt/article/details/51130057#commentBox
 * https://blog.csdn.net/lvyoujt/article/details/51138744
 */




public class MineFragment extends BaseFragment implements View.OnClickListener,
        ViewPager.OnPageChangeListener{

    private static final String TAG = "MineFragment";

    private ViewPager viewpager;
    private MineContentFragmentPagerAdapter adapter;
    private List<Fragment> list;
    private ImageView imgCursor;//标题下标指示
    private TextView txOne;//三个板块标题
    private TextView txTwo;
    private TextView txThree;
    private int offset = 0;//移动条图片的偏移量
    private int currIndex = 0;//当前页面的编号
    private int bmpWidth;// 移动条图片的长度
    private int one = 0; //移动条滑动一页的距离
    private int two = 0; //滑动条移动两页的距离


    @Override
    protected View initView() {
        View view=View.inflate(baseContext,R.layout.mine_fragment,null);

        txOne=view.findViewById(R.id.mine_content_one_tx);
        txTwo=view.findViewById(R.id.mine_content_two_tx);
        txThree=view.findViewById(R.id.mine_content_three_tx);
        imgCursor=view.findViewById(R.id.mine_content_cursor_img);
        viewpager=view.findViewById(R.id.mine_content_viewpager);

        Log.d(TAG, "initView: ");
        return view;
    }

    @Override
    protected void initData() {
        super.initData();
        list = new ArrayList<>();
        list.add(new MineDynamicFragment());
        list.add(new MineReplyFragment());
        list.add(new MineAskFragment());
        adapter=new MineContentFragmentPagerAdapter(getChildFragmentManager(),list);
        viewpager.setAdapter(adapter);
        viewpager.setCurrentItem(0);


        //下划线动画的相关设置：
//        bmpWidth = BitmapFactory.decodeResource(getResources(), R.mipmap.mine_mark).getWidth();// 获取图片宽度
        bmpWidth = txOne.getWidth();// 获取标题宽度
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

        Log.d(TAG, "initData: ");
    }

    @Override
    protected void setListener() {
        super.setListener();

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


        Log.d(TAG, "setListener: ");

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
}

