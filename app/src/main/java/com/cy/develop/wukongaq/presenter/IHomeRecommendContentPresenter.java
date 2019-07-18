package com.cy.develop.wukongaq.presenter;

public interface IHomeRecommendContentPresenter {
    void getContent();
    void getComment(int aid,int pageNumber,int pageSize);
    void commitCommentsFirstGrade(int aid);
}
