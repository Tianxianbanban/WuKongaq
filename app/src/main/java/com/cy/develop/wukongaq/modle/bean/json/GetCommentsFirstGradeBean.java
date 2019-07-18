package com.cy.develop.wukongaq.modle.bean.json;

import java.util.List;

public class GetCommentsFirstGradeBean {

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

        Comment comment;

        public Comment getComment() {
            return comment;
        }

        public void setComment(Comment comment) {
            this.comment = comment;
        }

        public class Comment{

            Integer totalPage;
            Integer totalElements;
            Integer pageNumber;
            Integer size;
            List<Comments> comments;

            public Integer getTotalPage() {
                return totalPage;
            }

            public void setTotalPage(Integer totalPage) {
                this.totalPage = totalPage;
            }

            public Integer getTotalElements() {
                return totalElements;
            }

            public void setTotalElements(Integer totalElements) {
                this.totalElements = totalElements;
            }

            public Integer getPageNumber() {
                return pageNumber;
            }

            public void setPageNumber(Integer pageNumber) {
                this.pageNumber = pageNumber;
            }

            public Integer getSize() {
                return size;
            }

            public void setSize(Integer size) {
                this.size = size;
            }


            public List<Comments> getComments() {
                return comments;
            }

            public void setComments(List<Comments> comments) {
                this.comments = comments;
            }

            public class Comments{
                private Integer id;
                private Integer aid;
                private Integer uid;
                private List subComments;
                private String content;
                private String updateTime;

                public Integer getId() {
                    return id;
                }

                public void setId(Integer id) {
                    this.id = id;
                }

                public Integer getAid() {
                    return aid;
                }

                public void setAid(Integer aid) {
                    this.aid = aid;
                }

                public Integer getUid() {
                    return uid;
                }

                public void setUid(Integer uid) {
                    this.uid = uid;
                }

                public List getSubComments() {
                    return subComments;
                }

                public void setSubComments(List subComments) {
                    this.subComments = subComments;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public String getUpdateTime() {
                    return updateTime;
                }

                public void setUpdateTime(String updateTime) {
                    this.updateTime = updateTime;
                }
            }
        }
    }
}
