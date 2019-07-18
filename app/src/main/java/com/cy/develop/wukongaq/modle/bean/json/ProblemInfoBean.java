package com.cy.develop.wukongaq.modle.bean.json;

import java.util.List;

public class ProblemInfoBean {
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
        Problem problem;

        public Problem getProblem() {
            return problem;
        }

        public void setProblem(Problem problem) {
            this.problem = problem;
        }

        public class Problem{
            Integer id;
            Integer uid;
            String title;
            String description;
            Integer answerNum;
            List<Answer> answers;
            String updateTime;//

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public Integer getUid() {
                return uid;
            }

            public void setUid(Integer uid) {
                this.uid = uid;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public Integer getAnswerNum() {
                return answerNum;
            }

            public void setAnswerNum(Integer answerNum) {
                this.answerNum = answerNum;
            }

            public List<Answer> getAnswers() {
                return answers;
            }

            public void setAnswers(List<Answer> answers) {
                this.answers = answers;
            }



            public String getUpdateTime() {
                return updateTime;
            }

            public void setUpdateTime(String updateTime) {
                this.updateTime = updateTime;
            }

            public class Answer{
                Integer id;
                Integer pid;
                List<Comment> comments;
                Integer uid;
                String content;
                Integer readNum;
                Integer commentNum;
                Integer approvalNum;
                Integer oppositionNum;
                String updateTime;

                public Integer getId() {
                    return id;
                }

                public void setId(Integer id) {
                    this.id = id;
                }

                public Integer getPid() {
                    return pid;
                }

                public void setPid(Integer pid) {
                    this.pid = pid;
                }

                public List<Comment> getComments() {
                    return comments;
                }

                public void setComments(List<Comment> comments) {
                    this.comments = comments;
                }

                public Integer getUid() {
                    return uid;
                }

                public void setUid(Integer uid) {
                    this.uid = uid;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public Integer getReadNum() {
                    return readNum;
                }

                public void setReadNum(Integer readNum) {
                    this.readNum = readNum;
                }

                public Integer getCommentNum() {
                    return commentNum;
                }

                public void setCommentNum(Integer commentNum) {
                    this.commentNum = commentNum;
                }

                public Integer getApprovalNum() {
                    return approvalNum;
                }

                public void setApprovalNum(Integer approvalNum) {
                    this.approvalNum = approvalNum;
                }

                public Integer getOppositionNum() {
                    return oppositionNum;
                }

                public void setOppositionNum(Integer oppositionNum) {
                    this.oppositionNum = oppositionNum;
                }

                public String getUpdateTime() {
                    return updateTime;
                }

                public void setUpdateTime(String updateTime) {
                    this.updateTime = updateTime;
                }




                public class Comment{
                    Integer id;
                    Integer aid;
                    Integer uid;
                    List subComments;
                    String content;
                    String updateTime;

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
}
