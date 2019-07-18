package com.cy.develop.wukongaq.modle.bean.json;

import java.util.List;

public class CommentsBean {
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
            List comments;

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

            public List getComments() {
                return comments;
            }

            public void setComments(List comments) {
                this.comments = comments;
            }
        }
    }
}
