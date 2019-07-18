package com.cy.develop.wukongaq.modle.bean;


public class MessageMenuItemBean {

    private int imageId;
    private int itemId;

    public MessageMenuItemBean(int imageId,int itemId){
        this.imageId=imageId;
        this.itemId=itemId;
    }

    public int getImageId() {
        return imageId;
    }

//    public void setImageId(int imageId) {
//        this.imageId = imageId;
//    }

    public int getItemId() {
        return itemId;
    }

//    public void setItemId(int itemId) {
//        this.itemId = itemId;
//    }
}
