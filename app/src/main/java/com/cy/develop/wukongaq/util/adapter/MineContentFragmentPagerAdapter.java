package com.cy.develop.wukongaq.util.adapter;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.ViewGroup;

import java.util.List;




public class MineContentFragmentPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> list;
    private FragmentTransaction ft;

    public MineContentFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public MineContentFragmentPagerAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm);
        this.list = list;
        this.ft=fm.beginTransaction();
    }

    @Override
    public Fragment getItem(int postion) {
        Log.d("yyy", "getItem: ");
        return list.get(postion);
    }

    @Override
    public int getCount() {
        Log.d("yyy", "getCount: ");
        return list.size();
    }


    //
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Log.d("yyy", "instantiateItem: ");
        // 将实例化的fragment进行显示即可。
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
//        ft.show(fragment).commit();
        ft.show(fragment);
        return fragment;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        Log.d("yyy", "destroyItem: ");
//            super.destroyItem(container, position, object);// 注释父类方法
        Fragment fragment = list.get(position);// 获取要销毁的fragment
        ft.hide(fragment);// 将其隐藏即可，并不需要真正销毁，这样fragment状态就得到了保存
    }

    @Override
    public void finishUpdate(@NonNull ViewGroup container) {
        super.finishUpdate(container);
    }
}


//public class MineContentFragmentPagerAdapter extends FragmentPagerAdapter {
//
//    private List<Fragment> list;
//    private FragmentTransaction ft;
//
////    public MineContentFragmentPagerAdapter(FragmentManager fm) {
////        super(fm);
////    }
//
//    public MineContentFragmentPagerAdapter(FragmentManager fm, List<Fragment> list) {
//        super(fm);
//        this.list = list;
//        this.ft=fm.beginTransaction();
//    }
//
//    @Override
//    public Fragment getItem(int postion) {
//        return list.get(postion);
//    }
//
//    @Override
//    public int getCount() {
//        return list.size();
//    }
//
//
//    //
//    @NonNull
//    @Override
//    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        Log.d("yyy", "instantiateItem: ");
//        // 将实例化的fragment进行显示即可。
//        Fragment fragment = (Fragment) super.instantiateItem(container, position);
////        ft.show(fragment).commit();
//        ft.show(fragment);
//        return fragment;
//    }
//
//    @Override
//    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        Log.d("yyy", "destroyItem: ");
////            super.destroyItem(container, position, object);// 注释父类方法
//        Fragment fragment = list.get(position);// 获取要销毁的fragment
//        ft.hide(fragment);// 将其隐藏即可，并不需要真正销毁，这样fragment状态就得到了保存
//    }
//
//}
