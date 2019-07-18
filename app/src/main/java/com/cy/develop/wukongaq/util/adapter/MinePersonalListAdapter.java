package com.cy.develop.wukongaq.util.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.cy.develop.wukongaq.R;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class MinePersonalListAdapter extends ArrayAdapter<String> {

    private int resourceId;

    public MinePersonalListAdapter(Context context, int textViewResourceId, List<String> objects){
        super(context,textViewResourceId,objects);
        resourceId=textViewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String itemOfPersonalInfo=getItem(position);
        View view=LayoutInflater.from(getContext()).inflate(resourceId,parent,false);

        TextView item= view.findViewById(R.id.mine_personal_item_tv);
        CircleImageView head= view.findViewById(R.id.mine_personal_head_civ);
        TextView itemContent= view.findViewById(R.id.mine_personal_item_content_tv);

        switch (position){
            case 0:
                item.setText("用户头像");
                break;
            case 1:
                item.setText("用户名");
                itemContent.setText(itemOfPersonalInfo);
                head.setVisibility(View.INVISIBLE);
                break;
            case 2:
                item.setText("个人简介");
                itemContent.setText(itemOfPersonalInfo);
                head.setVisibility(View.INVISIBLE);
                break;
        }
        return view;
    }
}
