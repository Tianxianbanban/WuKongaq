package com.cy.develop.wukongaq.modle.bean.json;

import java.util.List;

public class GetProblemListBean {
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
        ProblemList problemList;

        public ProblemList getProblemList() {
            return problemList;
        }

        public void setProblemList(ProblemList problemList) {
            this.problemList = problemList;
        }

        public class ProblemList{
            Integer totalPage;
            Integer totalElements;
            Integer pageNumber;
            Integer size;
            List<Problem> problems;

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

            public List<Problem> getProblems() {
                return problems;
            }

            public void setProblems(List<Problem> problems) {
                this.problems = problems;
            }

            public class Problem{
                Integer id;
                Integer uid;
                String title;
                String description;
                Integer answerNum;
                Integer collectionNum;
                List answers;
                String updateTime;

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

                public Integer getCollectionNum() {
                    return collectionNum;
                }

                public void setCollectionNum(Integer collectionNum) {
                    this.collectionNum = collectionNum;
                }

                public List getAnswers() {
                    return answers;
                }

                public void setAnswers(List answers) {
                    this.answers = answers;
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
