package com.cy.develop.wukongaq.view.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.cy.develop.wukongaq.R;
import com.cy.develop.wukongaq.modle.bean.json.GetCommentsFirstGradeBean;
import com.cy.develop.wukongaq.modle.bean.json.ProblemInfoBean;
import com.cy.develop.wukongaq.presenter.HomeRecommendContentPresenter;
import com.cy.develop.wukongaq.presenter.IHomeRecommendContentPresenter;
import com.cy.develop.wukongaq.util.ToastUtil;
import com.cy.develop.wukongaq.util.adapter.HomeRecommendCommentListAdapter;
import com.cy.develop.wukongaq.view.IHomeRecommendContentView;
import com.google.gson.Gson;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeRecommendContentActivity extends BaseActivity
        implements IHomeRecommendContentView , View.OnClickListener{

    private static final String TAG = "HomeRecommendContentAct";
    IHomeRecommendContentPresenter iHomeRecommendContentPresenter;

    Button back;
    ImageView attention;
    ImageView share;

    TextView title;
    TextView toAnswer;
    TextView lookAnswer;

    CircleImageView head;
    TextView name;
    TextView dateAndCertification;
    TextView attentionToName;
    TextView content;
    TextView readerNum;

    CircleImageView writerHead;
    TextView WriterName;

    TextView conmentsNum;
    ListView listView;//一级评论列表

    EditText commentsInput;
    TextView messageNum;
    TextView likeNum;


    int pid;
    String titleText;
    String descriptionText;
    int answerNumText;

    List<ProblemInfoBean.Data.Problem.Answer> answers;
    int currenAid;
    int currentIndex=0;
    int currentCommentPage=0;
    int commentPageSize=10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_recommend_content_activity);
        initView();
        initData();
        setListener();
    }

    private void initView(){
        back=findViewById(R.id.home_recommend_activity_back_btn);
        attention=findViewById(R.id.home_recommend_activity_attention_iv);
        share=findViewById(R.id.home_recommend_activity_share_iv);

        title=findViewById(R.id.home_recommend_activity_title_tv);
        toAnswer=findViewById(R.id.home_recommend_activity_to_answer_tv);
        lookAnswer=findViewById(R.id.home_recommend_activity_look_answer_tv);

        head=findViewById(R.id.home_recommend_activity_content_head_civ);
        name=findViewById(R.id.home_recommend_activity_content_name_tv);
        dateAndCertification=findViewById(R.id.home_recommend_activity_content_date_and_certification_tv);
        attentionToName=findViewById(R.id.home_recommend_activity_content_attention_tv);
        content=findViewById(R.id.home_recommend_activity_content_tv);
        readerNum=findViewById(R.id.home_recommend_activity_content_reader_num_tv);

        writerHead=findViewById(R.id.home_recommend_activity_writer_head_civ);
        WriterName=findViewById(R.id.home_recommend_activity_writer_name_tv);

        conmentsNum=findViewById(R.id.home_recommend_activity_comment_start_tv);
        listView=findViewById(R.id.home_recommend_activity_comment_lv);

        commentsInput=findViewById(R.id.home_recommend_activity_bottom_input_et);
        messageNum=findViewById(R.id.home_recommend_activity_bottom_message_num_tv);
        likeNum=findViewById(R.id.home_recommend_activity_bottom_like_num_tv);
    }

    private void initData(){
//        Intent intent=getIntent();
//        pid=intent.getIntExtra("pid",-1);
        iHomeRecommendContentPresenter=new HomeRecommendContentPresenter(this,this);
        iHomeRecommendContentPresenter.getContent();
    }

    public void setListener(){
        back.setOnClickListener(this);
        attention.setOnClickListener(this);
        share.setOnClickListener(this);

        title.setOnClickListener(this);
        toAnswer.setOnClickListener(this);
        lookAnswer.setOnClickListener(this);

        commentsInput.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.home_recommend_activity_back_btn:
                finish();
                break;
            case R.id.home_recommend_activity_attention_iv:
                ToastUtil.showToast(this,"关注");
                break;
            case R.id.home_recommend_activity_share_iv:
                ToastUtil.showToast(this,"分享");
                break;
            case R.id.home_recommend_activity_title_tv:
                ToastUtil.showToast(this,"查看问题描述");
                title.setText(titleText+"\n"+descriptionText);
                break;
            case R.id.home_recommend_activity_to_answer_tv:
                ToastUtil.showToast(this,"去回答这个问题");
                Intent intent=new Intent(this,HomeRecommendReplyActivity.class);
                intent.putExtra("pid",pid);
                intent.putExtra("title",titleText);
                startActivity(intent);
                break;
            case R.id.home_recommend_activity_look_answer_tv:
                ToastUtil.showToast(this,"下一条回答");
//                if (currentIndex<answers.size()-1){
//                    currentIndex+=1;
//                    ProblemInfoBean.Data.Problem.Answer answer=answers.get(currentIndex);
//                    setContentAndConments(answer);
//                    int aid=answer.getId();
//                    iHomeRecommendContentPresenter.getComment(aid,currentCommentPage,commentPageSize);
//                }else{
//                    ToastUtil.showToast(this,"已经是最后一条了");
//                }
                break;
            case R.id.home_recommend_activity_bottom_input_et:
//                iHomeRecommendContentPresenter.commitCommentsFirstGrade(currenAid);
                iHomeRecommendContentPresenter.commitCommentsFirstGrade(currenAid);
                break;
        }
    }


    @Override
    public void setContent(final String data) {
        runOnUiThread(new Runnable() {
            @SuppressLint("SetTextI18n")
            @Override
            public void run() {
                try{
                    Gson gson=new Gson();
                    ProblemInfoBean problemInfoBean=gson.fromJson(data,ProblemInfoBean.class);
                    //与问题有关的信息
                    pid=problemInfoBean.getData().getProblem().getId();
                    titleText=problemInfoBean.getData().getProblem().getTitle();
                    descriptionText=problemInfoBean.getData().getProblem().getDescription();
                    answerNumText=problemInfoBean.getData().getProblem().getAnswerNum();
                    //获取该问题的答案列表
                    answers=problemInfoBean.getData().getProblem().getAnswers();
                    //与第一个答案有关
                    currenAid=answers.get(0).getId();
                    String contentText=answers.get(0).getContent();
                    int messageNumText=answers.get(0).getCommentNum();
                    int likeNumText=answers.get(0).getApprovalNum();
                    int readNumText=answers.get(0).getReadNum();
//                    Log.d(TAG, "run:一些评论"+answers.get(0).getComments().get(0).getContent());
                    //信息显示设置
                    title.setText(titleText);
                    lookAnswer.setText("查看"+answers.size()+"条回答>");
                    content.setText(contentText);
                    readerNum.setText(readNumText+"阅读");
                    messageNum.setText(messageNumText+"");
                    likeNum.setText(likeNumText+"");
                    //评论部分
                    int commentsNumText=answers.get(0).getComments().size();
                    Log.d(TAG, "run:当前评论数量"+commentsNumText);
                    if (commentsNumText>0){
                        conmentsNum.setText("--共"+commentsNumText+"条评论--");
                        HomeRecommendCommentListAdapter homeRecommendCommentListAdapter=
                                new HomeRecommendCommentListAdapter(
                                        HomeRecommendContentActivity.this,R.layout.home_recommend_conment_item,answers.get(0).getComments());
                        listView.setAdapter(homeRecommendCommentListAdapter);
//                        setCommentContent(answers.get(0).getComments());
                    }else{
                        conmentsNum.setText("--还没有评论呢--");
                    }
//                    iHomeRecommendContentPresenter.getComment(answers.get(0).getId(),0,30);
                }catch (Exception e){
                    e.printStackTrace();
                    Log.i(TAG, "run: "+e.getMessage());
                }
            }
        });
    }



    @Override
    public void setCommentContent(List list) {
        HomeRecommendCommentListAdapter homeRecommendCommentListAdapter= new HomeRecommendCommentListAdapter(this,R.layout.home_recommend_conment_item,list);
        listView.setAdapter(homeRecommendCommentListAdapter);
    }

    @Override
    public void showFailure(final String info) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ToastUtil.showToast(HomeRecommendContentActivity.this,info);
            }
        });
    }
}
