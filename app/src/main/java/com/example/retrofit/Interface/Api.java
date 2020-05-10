package com.example.retrofit.Interface;

import com.example.retrofit.domain.BaseRespose;
import com.example.retrofit.domain.FriendRespose;
import com.example.retrofit.domain.MessageRespose;
import com.example.retrofit.domain.User;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

public interface Api {



    @GET("/get/p")
    Call<BaseRespose> getString(@QueryMap Map<String,Object> params);

    @GET("/user")
    Call<User> getHello();

    //more params
    @GET("/user/login")
    Call<User> login(@QueryMap Map<String,Object> params);

    @GET("/user/register")
    Call<User> register(@QueryMap Map<String,Object> params);


    //one params
    @GET("/user/getFriend")
    Call<FriendRespose> getFriend(@QueryMap Map<String, Object> params);

    @GET("/websocket/broadcast")
    Call<BaseRespose> broadcast(@QueryMap Map<String, Object> params);

    @GET("/websocket/login")
    Call<BaseRespose> WebSocketlogin(@QueryMap Map<String, Object> params);

    @GET("/websocket/sendToUser")
    Call<BaseRespose>sendToUser(@QueryMap Map<String, Object> params);
//获得消息记录
    @GET("/user/getMessage")
    Call<MessageRespose>getMessage(@QueryMap Map<String, Object> params);

    @GET("/websocket/server")
    Call<MessageRespose> test();





//    @POST
//    Call<> getWithParam(@Url String url);
}
