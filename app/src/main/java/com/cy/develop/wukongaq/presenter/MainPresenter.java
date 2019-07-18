package com.cy.develop.wukongaq.presenter;

import com.cy.develop.wukongaq.view.IMainView;

public class MainPresenter implements IMainPresenter{
    IMainView iMainView;

    public MainPresenter(IMainView iMainView){
        this.iMainView=iMainView;
    }

    @Override
    public void replaceFragment() {
        iMainView.replaceMineLoginFragment();
    }
}
