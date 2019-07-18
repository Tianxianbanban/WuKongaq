package com.cy.develop.wukongaq.modle.bean.json;

import java.util.List;

public class HomeRecommendBean {
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

        public List<HomeRecommendBean.Data.Item> recommends;

        public List<Item> getRecommends() {
            return recommends;
        }

        public void setRecommends(List<Item> recommends) {
            this.recommends = recommends;
        }

        public class Item{
            Integer pid;
            Integer aid;
            String problemTitle;
            String userHeadPhoto;
            String userName;
            String answerContent;
            String approvalNum;
            String commentNum;

            public Integer getPid() {
                return pid;
            }

            public void setPid(Integer pid) {
                this.pid = pid;
            }

            public Integer getAid() {
                return aid;
            }

            public void setAid(Integer aid) {
                this.aid = aid;
            }

            public String getProblemTitle() {
                return problemTitle;
            }

            public void setProblemTitle(String problemTitle) {
                this.problemTitle = problemTitle;
            }

            public String getUserHeadPhoto() {
                return userHeadPhoto;
            }

            public void setUserHeadPhoto(String userHeadPhoto) {
                this.userHeadPhoto = userHeadPhoto;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getAnswerContent() {
                return answerContent;
            }

            public void setAnswerContent(String answerContent) {
                this.answerContent = answerContent;
            }

            public String getApprovalNum() {
                return approvalNum;
            }

            public void setApprovalNum(String approvalNum) {
                this.approvalNum = approvalNum;
            }

            public String getCommentNum() {
                return commentNum;
            }

            public void setCommentNum(String commentNum) {
                this.commentNum = commentNum;
            }
        }

    }
}
