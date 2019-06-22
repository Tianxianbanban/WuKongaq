package com.cy.develop.wukongaq;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;



abstract public class BaseFragment extends Fragment{
    protected Context baseContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        baseContext=getActivity();
    }
}
