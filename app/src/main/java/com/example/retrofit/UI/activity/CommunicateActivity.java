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
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofit.Interface.Api;
import com.example.retrofit.Interface.DataManagerObserve;
import com.example.retrofit.Interface.UpdateListener;
import com.example.retrofit.R;
import com.example.retrofit.UI.adapter.MessageAdapter;
import com.example.retrofit.UI.adapter.MessageData;
import com.example.retrofit.UI.viewmodel.MessageViewModel;
import com.example.retrofit.UI.viewmodel.UserViewModel;
import com.example.retrofit.domain.BaseRespose;
import com.example.retrofit.domain.MessageRespose;
import com.example.retrofit.socketClient.SocketClient;
import com.example.retrofit.utile.RetrofitManager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CommunicateActivity extends AppCompatActivity {



    private Retrofit retrofit;
    private Api api;
    private UserViewModel myviewmodel;
    private RecyclerView mRecyclerView;
    private static MessageViewModel messageViewModel;
    private MessageAdapter messageAdapter;
    private TextView mTextView;
    private int receiveId;
    private boolean isSending=false;
    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communicate);
        messageViewModel = new ViewModelProvider(this).get(MessageViewModel.class);
        init();
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private void init() {
        Intent intent = getIntent();
        receiveId = intent.getIntExtra("data", 0);
        retrofit = RetrofitManager.getRetrofit();
        api = retrofit.create(Api.class);
        myviewmodel = RequestActivity.getMyviewmodel();
        mRecyclerView = findViewById(R.id.CommunicateActivity_RecycleView);
        mTextView=findViewById(R.id.activity_communicate_editText);
        getMeasure(receiveId);
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

    private void getMeasure(int data) {
        Map<String, Object> params = new HashMap<>();
        params.put("userId", String.valueOf(myviewmodel.getId().getValue()));
        params.put("receiveId", data);
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

    public void sentToOther(View view) {
        if (!isSending) {
            if (!mTextView.getText().toString().equals("")) {
                isSending=true;
                Map<String, Object> params2 = new HashMap<>();
                params2.put("sendId",String.valueOf(myviewmodel.getId().getValue()));
                params2.put("receiveId",String.valueOf(receiveId));
                params2.put("info",mTextView.getText().toString());
                mTextView.setText("");
                System.out.println(params2);
                Call<BaseRespose> sendToUser = api.sendToUser(params2);
                sendToUser.enqueue(new Callback<BaseRespose>() {
                    @Override
                    public void onResponse(Call<BaseRespose> call, Response<BaseRespose> response) {
                        if (response.body().isSuccess()) {
                            SocketClient.getSocket().emit("newMessage","1");
                            isSending=false;
//                            Map<String, Object> params = new HashMap<>();
//                            params.put("info", mTextView.getText().toString());
//                            Call<BaseRespose> broadcast = api.broadcast(params);
//                            broadcast.enqueue(new Callback<BaseRespose>() {
//                                @Override
//                                public void onResponse(Call<BaseRespose> call, Response<BaseRespose> response) {
//                                    isSending=false;
//                                }
//                                @Override
//                                public void onFailure(Call<BaseRespose> call, Throwable t) {
//                                }
//                            });
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseRespose> call, Throwable t) {

                    }
                });
            }
            else {
                Toast.makeText(this, "输入的内容不能为空，请重新输入", Toast.LENGTH_SHORT).show();
            }


        }





//        Map<String , Object> message = new HashMap<>();
//        message.put("sendId",myviewmodel.getId().getValue().intValue());
//        message.put("content",mTextView.getText().toString());
//        message.put("receiveId",receiveId);
//        System.out.println(message);
//        SocketClient.getSocket().emit("sendTo",message).on("newMessage", new Emitter.Listener() {
//            @Override
//            public void call(Object... args) {
//                System.out.println(args);
//            }
//        });


//
//        List<MessageRespose.DataBean> list = MessageViewModel.getmData().getValue();
//        MessageRespose.DataBean dataBean = new MessageRespose.DataBean();
//
//        dataBean.setContent("1231231");
//        list.add(dataBean);
//        MessageViewModel.getmData().setValue(list);
//        System.out.println(MessageViewModel.getmData().getValue().toString());
    }

    public void setBack(View view) {
        this.finish();
    }

    public void observe() {

        MessageViewModel.getmData().observe(this, new Observer<List<MessageRespose.DataBean>>() {
            @Override
            public void onChanged(List<MessageRespose.DataBean> dataBeans) {
                System.out.println("调!!!!!!!!!!!!!!!!!!!!!!!");
                //trans to
                MessageData messageData = new MessageData();
                if (dataBeans!=null) {
                    for (int i = 0; i < dataBeans.size(); i++) {
                        MessageData.DataBean dataBean = new MessageData.DataBean();
                        dataBean.setContent(dataBeans.get(i).getContent());
                        if (dataBeans.get(i).getSendId() == myviewmodel.getId().getValue()) {
                            dataBean.setUser(true);
                        }
                        messageData.getData().add(dataBean);
                    }
                }
                System.out.println(messageData.getData().toString());

                messageAdapter = new MessageAdapter(messageData.getData());
                mRecyclerView.setAdapter(messageAdapter);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getParent());
                mRecyclerView.setLayoutManager(linearLayoutManager);
                mRecyclerView.scrollToPosition(messageAdapter.getItemCount()-1);

            }


        });

        DataManagerObserve instance = DataManagerObserve.getInstance();
        instance.addUpdateListener(new UpdateListener() {
            @Override
            public void update(boolean b) {
                if (instance.getisHavingMessage()==true) {
                    System.out.println("监receive!!!!!!" );
                    instance.setHavingMessage(false);
                    getMeasure(receiveId);
                }
            }
        });
        instance.operation();
    }
}
