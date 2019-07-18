package com.cy.develop.wukongaq.modle.bean;

import com.google.gson.Gson;

import org.json.JSONException;

public class UserInfoBean {

    Integer code;
    String msg;
    Data data;


    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data{

        UserInfo userInfo;

        public UserInfo getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfo userInfo) {
            this.userInfo = userInfo;
        }

        public class UserInfo{
            Integer id;
            String nickName;
            String headPhoto;
            String personalProfile;
            Integer fans;
            Integer attention;
            Integer approval;
            String updateTime;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public String getHeadPhoto() {
                return headPhoto;
            }

            public void setHeadPhoto(String headPhoto) {
                this.headPhoto = headPhoto;
            }

            public String getPersonalProfile() {
                return personalProfile;
            }

            public void setPersonalProfile(String personalProfile) {
                this.personalProfile = personalProfile;
            }

            public Integer getFans() {
                return fans;
            }

            public void setFans(Integer fans) {
                this.fans = fans;
            }

            public Integer getAttention() {
                return attention;
            }

            public void setAttention(Integer attention) {
                this.attention = attention;
            }

            public Integer getApproval() {
                return approval;
            }

            public void setApproval(Integer approval) {
                this.approval = approval;
            }

            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }
        }
    }

    public String toString(UserInfoBean userInfoBean) {
        Gson gson=new Gson();
        String json=gson.toJson(userInfoBean);
        return json;
    }

    static public UserInfoBean getUserInfoBean(String json) throws JSONException {
        Gson gson=new Gson();
        UserInfoBean userInfoBean=gson.fromJson(json,UserInfoBean.class);
        return userInfoBean;
    }
}
