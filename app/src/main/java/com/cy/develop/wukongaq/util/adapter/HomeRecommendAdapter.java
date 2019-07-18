package com.cy.develop.wukongaq.util.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.cy.develop.wukongaq.R;
import com.cy.develop.wukongaq.view.activity.HomeRecommendContentActivity;
import com.cy.develop.wukongaq.modle.bean.json.HomeRecommendBean;
import com.cy.develop.wukongaq.util.SpUtil;
import com.cy.develop.wukongaq.util.ToastUtil;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class HomeRecommendAdapter extends RecyclerView.Adapter<HomeRecommendAdapter.ViewHolder> {

    private List<HomeRecommendBean.Data.Item> homeRecommendItemBeans;


    static class ViewHolder extends RecyclerView.ViewHolder{
        View homeRecommendItemView;
        TextView title;
        CircleImageView head;
        TextView user;
        TextView content;
        TextView approval;
        TextView comment;

        private ViewHolder(View view){
            super(view);
            homeRecommendItemView=view;
            title=view.findViewById(R.id.home_recommend_title_tv);
            head=view.findViewById(R.id.home_recommend_user_head_civ);
            user=view.findViewById(R.id.home_recommend_user_name_tx);
            content=view.findViewById(R.id.home_recommend_content_tv);
            approval=view.findViewById(R.id.home_recommend_approval_num_tv);
            comment=view.findViewById(R.id.home_recommend_comment_num_tv);
        }
    }

    public HomeRecommendAdapter(List<HomeRecommendBean.Data.Item> homeRecommendItemBeanList){
        homeRecommendItemBeans=homeRecommendItemBeanList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.home_recommend_item,viewGroup,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                HomeRecommendBean.Data.Item homeRecommendItemBean=homeRecommendItemBeans.get(position);
                int pid=homeRecommendItemBean.getPid();
                SpUtil.spInput(v.getContext(),"pid",String.valueOf(pid));
                ToastUtil.showToast(v.getContext(),"查看问题id为"+pid);
                Intent intent=new Intent(v.getContext(),HomeRecommendContentActivity.class);
//                intent.putExtra("pid",pid);
                v.getContext().startActivity(intent);
            }
        });

        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        HomeRecommendBean.Data.Item homeRecommendItemBean=homeRecommendItemBeans.get(i);
        viewHolder.title.setText(homeRecommendItemBean.getProblemTitle());
//        viewHolder.head.setImageURI(URI.create(homeRecommendItemBean.getUserHeadPhoto()));
        viewHolder.user.setText(homeRecommendItemBean.getUserName());
        viewHolder.content.setText(homeRecommendItemBean.getAnswerContent());
        viewHolder.approval.setText(homeRecommendItemBean.getApprovalNum()+"赞同");
        viewHolder.comment.setText(homeRecommendItemBean.getCommentNum()+"评论");
    }

    @Override
    public int getItemCount() {
        return homeRecommendItemBeans.size();
    }
}





