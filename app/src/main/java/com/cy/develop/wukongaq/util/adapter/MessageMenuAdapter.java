package com.cy.develop.wukongaq.util.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.cy.develop.wukongaq.R;
import com.cy.develop.wukongaq.util.ToastUtil;
import com.cy.develop.wukongaq.modle.bean.MessageMenuItemBean;

import java.util.List;

public class MessageMenuAdapter extends RecyclerView.Adapter<MessageMenuAdapter.ViewHolder> {

    private List<MessageMenuItemBean> messageMenuItemBeans;

    static class ViewHolder extends RecyclerView.ViewHolder{
        View messageItemView;
        ImageView imageView;
        TextView textView;

        public ViewHolder(View view){
            super(view);
            messageItemView=view;
            imageView=view.findViewById(R.id.message_menu_item_iv);
            textView=view.findViewById(R.id.message_menu_item_tx);
        }
    }

    public MessageMenuAdapter(List<MessageMenuItemBean> messageMenuItemBeanList){
        messageMenuItemBeans=messageMenuItemBeanList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        final View view=LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.message_menu_item,viewGroup,false);
        final ViewHolder holder=new ViewHolder(view);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position=holder.getAdapterPosition();
                MessageMenuItemBean messageMenuItemBean=messageMenuItemBeans.get(position);
                ToastUtil.showToast(v.getContext(),v.getContext().getString(messageMenuItemBean.getItemId()));
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        MessageMenuItemBean messageMenuItemBean=messageMenuItemBeans.get(i);
        viewHolder.imageView.setImageResource(messageMenuItemBean.getImageId());
        viewHolder.textView.setText(messageMenuItemBean.getItemId());
    }

    @Override
    public int getItemCount() {
        return messageMenuItemBeans.size();
    }
}
