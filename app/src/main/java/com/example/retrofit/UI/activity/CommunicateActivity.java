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
import com.example.retrofit.utile.RetrofitManager;

import retrofit2.Retrofit;

public class CommunicateActivity extends AppCompatActivity {



    private Retrofit retrofit;
    private Api api;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_communicate);

            init();
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    private  void  init(){
        Intent i = getIntent();  //直接获取传过来的intent
        i.getStringExtra("data");
        retrofit= RetrofitManager.getRetrofit();
        api=retrofit.create(Api.class);
        UserviewModel myviewmodel = RequestActivity.getMyviewmodel();

    }
    public  void  setBack(View view){
        this.finish();
    }
}
