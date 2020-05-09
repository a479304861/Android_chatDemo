package com.example.retrofit.UI.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.example.retrofit.Interface.Api;
import com.example.retrofit.R;
import com.example.retrofit.UI.adapter.MessageAdapter;
import com.example.retrofit.UI.viewmodel.FriendViewModel;
import com.example.retrofit.UI.viewmodel.MessageViewModel;
import com.example.retrofit.UI.viewmodel.UserviewModel;
import com.example.retrofit.domain.MessageRespose;
import com.example.retrofit.utile.RetrofitManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CommunicateActivity extends AppCompatActivity {



    private Retrofit retrofit;
    private Api api;
    private UserviewModel myviewmodel;
    private RecyclerView mRecyclerView;
    private static MessageViewModel messageViewModel;
    private MessageAdapter messageAdapter;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communicate);
        messageViewModel= new ViewModelProvider(this).get(MessageViewModel.class);
            init();
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private  void  init(){
        Intent intent=getIntent();
        int data=intent.getIntExtra("data",0);
        retrofit= RetrofitManager.getRetrofit();
        api=retrofit.create(Api.class);
        myviewmodel = RequestActivity.getMyviewmodel();
        mRecyclerView=findViewById(R.id.CommunicateActivity_RecycleView);
        getMeasure(data);
        observe();
//        List<MessageRespose.DataBean> dataBeans = new ArrayList<>();
//        for (int i = 0; i < 10; i++) {
//            MessageRespose.DataBean dataBean = new MessageRespose.DataBean();
//            dataBean.setContent("我是内容----->"+String.valueOf(i));
//            dataBeans.add(dataBean);
//        }
//        System.out.println(dataBeans);
//
//
    }

    private  void getMeasure(int data){
        Map<String, Object> params = new HashMap<>();
        params.put("userId",String.valueOf(myviewmodel.getId().getValue()));
        params.put("receiveId",data);
        System.out.println(params);
        Call<MessageRespose> messuage = api.getMessage(params);
        messuage.enqueue(new Callback<MessageRespose>() {
            @Override
            public void onResponse(Call<MessageRespose> call, Response<MessageRespose> response) {
                MessageViewModel.getmData().setValue(response.body().getData());

            }

            @Override
            public void onFailure(Call<MessageRespose> call, Throwable t) {
            }
        });
    }

    public void addMessage(View view){
        List<MessageRespose.DataBean> list = MessageViewModel.getmData().getValue();
        MessageRespose.DataBean dataBean = new MessageRespose.DataBean();
        dataBean.setContent("1231231");
        list.add(dataBean);
        MessageViewModel.getmData().setValue(list);
        System.out.println(MessageViewModel.getmData().getValue().toString());
    }
    public  void  setBack(View view){
        this.finish();
    }

    public  void observe(){
        MessageViewModel.getmData().observe(this, new Observer<List<MessageRespose.DataBean>>() {
            @Override
            public void onChanged(List<MessageRespose.DataBean> dataBeans) {
                System.out.println("调用！！！！！");
                messageAdapter = new MessageAdapter(dataBeans);
                mRecyclerView.setAdapter(messageAdapter);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getParent());
                mRecyclerView.setLayoutManager(linearLayoutManager);
            }
        });
    }
}
