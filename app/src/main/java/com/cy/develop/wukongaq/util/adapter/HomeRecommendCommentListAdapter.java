package com.cy.develop.wukongaq.util.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.cy.develop.wukongaq.R;
import com.cy.develop.wukongaq.modle.bean.json.GetCommentsFirstGradeBean;
import com.cy.develop.wukongaq.modle.bean.json.ProblemInfoBean;
import com.cy.develop.wukongaq.view.activity.HomeRecommendContentActivity;

import java.util.List;
import java.util.Objects;


public class HomeRecommendCommentListAdapter extends ArrayAdapter<ProblemInfoBean.Data.Problem.Answer.Comment> {

    private int resourceId;

    public HomeRecommendCommentListAdapter(Context context, int textViewResourceId, List<ProblemInfoBean.Data.Problem.Answer.Comment> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }


    @NonNull
    @SuppressLint("SetTextI18n")
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ProblemInfoBean.Data.Problem.Answer.Comment comment=getItem(position);
        @SuppressLint("ViewHolder") View view=LayoutInflater.from(getContext()).inflate(resourceId,parent,false);

        TextView replyPersonName= view.findViewById(R.id.home_recommend_comment_item_name_tv);
        TextView content= view.findViewById(R.id.home_recommend_comment_item_content_tv);
        TextView updateTime= view.findViewById(R.id.home_recommend_comment_item_time_tv);
        TextView SencondGradeCommentsNum= view.findViewById(R.id.home_recommend_comment_item_reply_num_tv);


        replyPersonName.setText(Objects.requireNonNull(comment).getUid()+"");
        content.setText(comment.getContent()+"");
        updateTime.setText(comment.getUpdateTime()+"");
        SencondGradeCommentsNum.setText(comment.getSubComments().size()+" 回复");

        return view;
    }
}

