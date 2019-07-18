package com.cy.develop.wukongaq.view;

import java.util.List;

public interface IHomeRecommendContentView {
    void setContent(String data);
    void setCommentContent(List list);
    void showFailure(String info);
}
