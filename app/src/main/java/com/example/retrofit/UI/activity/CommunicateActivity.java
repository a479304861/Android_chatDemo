package com.example.retrofit.UI.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.retrofit.Interface.Api;
import com.example.retrofit.R;
import com.example.retrofit.UI.viewmodel.UserviewModel;
import com.example.retrofit.domain.MeassureRespose;
import com.example.retrofit.utile.RetrofitManager;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CommunicateActivity extends AppCompatActivity {



    private Retrofit retrofit;
    private Api api;
    UserviewModel myviewmodel;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communicate);

            init();
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private  void  init(){
        Intent intent=getIntent();
        int data=intent.getIntExtra("data",0);
        retrofit= RetrofitManager.getRetrofit();
        api=retrofit.create(Api.class);
        myviewmodel = RequestActivity.getMyviewmodel();
        getMeasure(data);
    }

    private  void getMeasure(int data){
        Map<String, Object> params = new HashMap<>();
        params.put("userId",myviewmodel.getId().getValue());
        params.put("receiveId",data);
        Call<MeassureRespose> measure = api.getMeasure(params);
        measure.enqueue(new Callback<MeassureRespose>() {
            @Override
            public void onResponse(Call<MeassureRespose> call, Response<MeassureRespose> response) {
                System.out.println("!!!!!!!!!!!");
            }

            @Override
            public void onFailure(Call<MeassureRespose> call, Throwable t) {
            }
        });
    }

    public  void  setBack(View view){
        this.finish();
    }
}
