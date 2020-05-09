package com.example.retrofit.UI.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofit.Interface.Api;
import com.example.retrofit.UI.adapter.TestAdapter;
import com.example.retrofit.UI.viewmodel.UserviewModel;
import com.example.retrofit.R;
import com.example.retrofit.domain.FriendRespose;
import com.example.retrofit.utile.RetrofitManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UIactivity extends AppCompatActivity {

    private static final String TAG ="UIactivity" ;
    private Api api;
    private  Retrofit retrofit;
    private long exitTime;

    UserviewModel myviewModel;
    Bundle bundle;
    TextView mCollectNum,mLikeNum,mFansNum,mTransmit,mName;
    RecyclerView mRecyclerView;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u_iactivity);

        init();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_BACK){
            if((System.currentTimeMillis()-exitTime)>2000){
                Toast.makeText(UIactivity.this,"Retry and Exit()",Toast.LENGTH_SHORT).show();
                exitTime=System.currentTimeMillis();
            }else{
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void  init(){
        mCollectNum = findViewById(R.id.textViewCollect);
        mLikeNum = findViewById(R.id.textView_Like);
        mFansNum = findViewById(R.id.textViewFans);
        mTransmit = findViewById(R.id.textViewTransmit);
        mName=findViewById(R.id.textViewName);
        mRecyclerView=findViewById(R.id.recyclerView);
        retrofit= RetrofitManager.getRetrofit();
        api=retrofit.create(Api.class);
        myviewModel=RequestActivity.getMyviewmodel();

        observe();
        getFriend();
    }

   private  void getFriend(){
       Map<String,Object> params= new HashMap<>();
       params.put("id",17);
       Call<FriendRespose> friend = api.getFriend(params);
       friend.enqueue(new Callback<FriendRespose>() {
           @Override
           public void onResponse(Call<FriendRespose> call, Response<FriendRespose> response) {
                TestAdapter testAdapter = new TestAdapter( response.body().getData());
                mRecyclerView.setAdapter(testAdapter);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getParent());
                mRecyclerView.setLayoutManager(linearLayoutManager);

           }
           @Override
           public void onFailure(Call<FriendRespose> call, Throwable t) {
               Log.d(TAG, "onFailure: ");
           }
       });
   }
    private void observe(){
        myviewModel.getName().observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                    mName.setText(s);
            }
        });
        myviewModel.getLikeNum().observe(this, new Observer<Integer>() {
            @Override
            public void onChanged(Integer integer) {
                mLikeNum.setText(String.valueOf(integer));
            }
        });
        myviewModel.getCollectNum().observe(this, new Observer<Integer>() {
                @Override
                public void onChanged(Integer integer) {
                    mCollectNum.setText(String.valueOf(integer));
                }
            });
        myviewModel.getFansNum().observe(this, new Observer<Integer>() {
                @Override
                public void onChanged(Integer integer) {
                    mFansNum.setText(String.valueOf(integer));
                }
            });
        myviewModel.getTransmitNum().observe(this, new Observer<Integer>() {
                @Override
                public void onChanged(Integer integer) {
                    mTransmit.setText(String.valueOf(integer));
                }
        });

    }
}
