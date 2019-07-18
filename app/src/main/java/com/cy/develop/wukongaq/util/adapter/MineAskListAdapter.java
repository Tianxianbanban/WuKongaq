package com.cy.develop.wukongaq.util.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.cy.develop.wukongaq.R;
import com.cy.develop.wukongaq.modle.UserState;
import com.cy.develop.wukongaq.modle.bean.json.GetProblemListBean;
import com.cy.develop.wukongaq.modle.bean.json.RetJson;
import com.cy.develop.wukongaq.util.OkHttpUtil;
import com.cy.develop.wukongaq.util.ToastUtil;
import com.cy.develop.wukongaq.view.activity.MineAskItemContentActivity;
import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MineAskListAdapter extends RecyclerView.Adapter<MineAskListAdapter.ViewHolder> {
    private static final String TAG = "MineAskListAdapter";

    private List<GetProblemListBean.Data.ProblemList.Problem> getProblemListBeans;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View mineAskItemView;
        TextView title;
        TextView toReply;
        TextView collection;
        TextView replyNum;

        public ViewHolder(View view){
            super(view);
            mineAskItemView=view;
            title=view.findViewById(R.id.mine_ask_title);
            toReply=view.findViewById(R.id.home_ask_to_answer_tv);
            collection=view.findViewById(R.id.home_ask_collection_tv);
            replyNum=view.findViewById(R.id.home_ask_answer_tv);
        }
    }

    public MineAskListAdapter(List<GetProblemListBean.Data.ProblemList.Problem> list){
        getProblemListBeans=list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.mine_ask_item,viewGroup,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                GetProblemListBean.Data.ProblemList.Problem problem=getProblemListBeans.get(position);
                Intent intent=new Intent(v.getContext(),MineAskItemContentActivity.class);
                intent.putExtra("pid",problem.getId());
                intent.putExtra("question",problem.getTitle());
                intent.putExtra("description",problem.getDescription());
                intent.putExtra("replyNum",problem.getAnswerNum());
                intent.putExtra("collectionNum",problem.getCollectionNum());
                v.getContext().startActivity(intent);
//                ToastUtil.showToast(v.getContext(),"点击了"+problem.getTitle());
            }
        });

//        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                int position=holder.getAdapterPosition();
//                GetProblemListBean.Data.ProblemList.Problem problem=getProblemListBeans.get(position);
////                ToastUtil.showToast(v.getContext(),"点击了"+problem.getDescription());
//                deleteProblem(v,v.getContext(),problem.getId());
//
//                return true;
//            }
//        });

        return holder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        GetProblemListBean.Data.ProblemList.Problem problem=getProblemListBeans.get(i);
        viewHolder.title.setText(problem.getTitle());
        viewHolder.toReply.setText("去回答");
        viewHolder.collection.setText(problem.getCollectionNum()+"收藏");
        viewHolder.replyNum.setText(problem.getAnswers().size()+"回答");
    }

    @Override
    public int getItemCount() {
        return getProblemListBeans.size();
    }

    private void deleteProblem(View v,Context context,int pid){

        RequestBody requestBody=new FormBody.Builder()
                .add("pid",pid+"").build();
        OkHttpUtil.sendOkHttpRequest(context, OkHttpUtil.deleteProblem, OkHttpUtil.method[3], requestBody, new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.i(TAG, "onFailure: "+e.getMessage());
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData=response.body().string();
                Log.d(TAG, "show-test-onResponse: 删除问题"+responseData);
                if (response.code()==200){
                    try{
                        Gson gson=new Gson();
                        RetJson retJson=gson.fromJson(responseData,RetJson.class);
                        if (retJson.getCode()==0){
//                            notifyDataSetChanged();
                            notifyDataSetChanged();
                        }
                    }catch (Exception e){

                    }
                }
            }
        });
    }


}
