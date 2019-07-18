package com.cy.develop.wukongaq.view.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.cy.develop.wukongaq.R;
import com.cy.develop.wukongaq.modle.bean.json.RetJson;
import com.cy.develop.wukongaq.util.OkHttpUtil;
import com.cy.develop.wukongaq.util.ToastUtil;
import com.google.gson.Gson;
import java.io.IOException;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.RequestBody;
import okhttp3.Response;

public class MineAskItemContentActivity extends BaseActivity implements View.OnClickListener{
    private static final String TAG = "MineAskItemContentActiv";

    Button back;
    Button more;
    TextView question;
    TextView description;
    TextView replyNum;
    TextView collectionNum;
    TextView toReply;

    int pid;
    String questionText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mine_ask_item_content_activity);
        initView();
        initData();
        setListener();
    }

    private void initView(){
        back=findViewById(R.id.mine_ask_item_content_back_btn);
        more=findViewById(R.id.mine_ask_item_content_more_btn);
        question=findViewById(R.id.mine_ask_item_content_question_tv);
        description=findViewById(R.id.mine_ask_item_content_description_tv);
        replyNum=findViewById(R.id.mine_ask_item_content_reply_num_tv);
        collectionNum=findViewById(R.id.mine_ask_item_content_collection_num_tv);
        toReply=findViewById(R.id.mine_ask_item_content_to_reply_tv);
    }

    private void initData(){
        Intent intent=getIntent();
        pid=intent.getIntExtra("pid",-1);
        questionText=intent.getStringExtra("question");
        String descriptionText=intent.getStringExtra("description");
        int replyNumText=intent.getIntExtra("replyNum",-1);
        int collectionNumText=intent.getIntExtra("collectionNum",-1);

        question.setText(questionText);
        description.setText(descriptionText);
        replyNum.setText(replyNumText+"个回答");
        collectionNum.setText(collectionNumText+"收藏");
    }

    private void setListener(){
        back.setOnClickListener(this);
        more.setOnClickListener(this);
        toReply.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mine_ask_item_content_back_btn:
                finish();
                break;
            case R.id.mine_ask_item_content_more_btn:
                ToastUtil.showToast(this,"更多");
                deleteProblem(this,pid);
                break;
            case R.id.mine_ask_item_content_to_reply_tv:
                ToastUtil.showToast(this,"去回答");
                Intent intent=new Intent(this,HomeRecommendReplyActivity.class);
                intent.putExtra("pid",pid);
                intent.putExtra("title",questionText);
                startActivity(intent);
                break;
        }
    }

    private void deleteProblem(Context context, int pid){

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
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    ToastUtil.showToast(MineAskItemContentActivity.this,"已经删除问题！");
                                    finish();
                                }
                            });
                        }
                    }catch (Exception e){

                    }
                }
            }
        });
    }



}
