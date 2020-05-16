package com.example.retrofit.UI.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.retrofit.Interface.Api;
import com.example.retrofit.R;
import com.example.retrofit.utile.RetrofitManager;

import java.util.HashMap;
import java.util.Map;

import retrofit2.Retrofit;

public class AddFriendActivity extends AppCompatActivity {

    private TextView mTextView;
    private Retrofit retrofit;
    private Api api;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend);
        init();
    }

    private void init() {
        mTextView=findViewById(R.id.AddFriendActivityTextView);
        retrofit = RetrofitManager.getRetrofit();
        api = retrofit.create(Api.class);
    }

    public void AddFriendSetBack(View view){
        this.finish();
    }
    public void selectFriend(View view){
        if (!mTextView.getText().toString().equals("")) {
            Map<String , Object> params = new HashMap<>();
            params.put("name",mTextView.getText().toString());
            api.selectFriend(params);
        }
    }
}
