package com.example.retrofit.UI.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.example.retrofit.Interface.Api;
import com.example.retrofit.R;
import com.example.retrofit.UI.viewmodel.UserviewModel;
import com.example.retrofit.domain.BaseRespose;
import com.example.retrofit.domain.User;
import com.example.retrofit.socket.SocketTest;
import com.example.retrofit.utile.RetrofitManager;
import com.example.retrofit.utile.StaticUtils;
import com.example.retrofit.webSocket.WebSocketTest;

import org.java_websocket.enums.ReadyState;

import java.io.File;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

@RequiresApi(api = Build.VERSION_CODES.M)
public class RequestActivity extends AppCompatActivity {

    private static final String TAG = "RequestActivity";
    private ExecutorService mExecutorService = null;
    SocketTest socketTest = null;
    private static boolean isConnect = false;
    private static boolean isLoad = false;
    private boolean isConneting = false;
    private EditText mEditTextName, mEditTextPassword;
    private Api api;
    private Retrofit retrofit;
    private static UserviewModel myviewmodel;

    public static UserviewModel getMyviewmodel() {
        return myviewmodel;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        mExecutorService = Executors.newCachedThreadPool();
        mEditTextName = findViewById(R.id.editTextName);
        mEditTextPassword = findViewById(R.id.editTextPassword);
        myviewmodel = new ViewModelProvider(this).get(UserviewModel.class);
        checkPermission();
        init();
    }

    private void init() {
        retrofit = RetrofitManager.getRetrofit();
        api = retrofit.create(Api.class);
        if (myviewmodel.getIsLoad().getValue() == true) {
            Intent intent = new Intent(this, UIactivity.class);
            startActivity(intent);
        }
    }

    //请求权限
    private void checkPermission() {
        int readExStoragePermissionRest = checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE);
        if (readExStoragePermissionRest != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, StaticUtils.PERMISSION_REQUEST_CORD);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == StaticUtils.PERMISSION_REQUEST_CORD) {
            if (grantResults.length == 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //
            } else {

            }
        }
    }

    public void pickImage(View view) {
        startActivity(new Intent(this, PicketActivity.class));
    }

    public void socket(View view) {
        if (isConnect == false) {
            Log.d(TAG, "socket");
            socketTest = new SocketTest();
            socketTest.connect();
            isConnect = true;
        } else {
            socketTest.disconnect();
            isConnect = false;
        }
    }

    //参数
    public void get(View view) {
        Map<String, Object> params = new HashMap<>();
        params.put("keyword", "123");
        params.put("page", 10);
        params.put("order", 0);
        Call<BaseRespose> json = api.getString(params);
        json.enqueue(new Callback<BaseRespose>() {
            @Override
            public void onResponse(Call<BaseRespose> call, Response<BaseRespose> response) {
                Log.d(TAG, " /get/p ==>" + response.body().toString());
            }

            @Override
            public void onFailure(Call<BaseRespose> call, Throwable t) {
                Log.d(TAG, " false");
            }
        });

    }

    public void hello(View view) {

        Call<User> hello = api.getHello();
        hello.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.d(TAG, " response.body ==>" + response.body().getData());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.d(TAG, " false--->" + t.toString());
            }
        });
    }

    public void register(View view) {
        if (!mEditTextName.getText().toString().equals("") && !mEditTextPassword.getText().toString().equals("")) {
            Map<String, Object> params = new HashMap<>();
            params.put("name", mEditTextName.getText().toString());
            params.put("password", mEditTextPassword.getText().toString());
            Call<User> register = api.register(params);
            register.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    Log.d(TAG, response.body().toString());
                    if (response.body().isSucceed() == true) {
                        if (myviewmodel.getIsLoad().getValue() == false) {
                            myviewmodel.getIsLoad().setValue(true);
                            isConneting = false;
                            myviewmodel.getName().setValue(response.body().getData().get(0).getName());
                            myviewmodel.getLikeNum().setValue(response.body().getData().get(0).getLikeNum());
                            myviewmodel.getFansNum().setValue(response.body().getData().get(0).getFansNum());
                            myviewmodel.getTransmitNum().setValue(response.body().getData().get(0).getTransmitNum());
                            myviewmodel.getName().setValue(response.body().getData().get(0).getName());
                            Intent intent = new Intent(view.getContext(), UIactivity.class);
                            startActivity(intent);
                        }

                    }
                }
                @Override
                public void onFailure(Call<User> call, Throwable t) {
                }
            });
        } else {
            Toast.makeText(this, "请输入账号密码", Toast.LENGTH_SHORT).show();
        }

    }

    public void login(View view) {
        isConneting = true;
        if (isConneting == true) {
            Retrofit retrofit = RetrofitManager.getRetrofit();
            Api api = retrofit.create(Api.class);
            Map<String, Object> params = new HashMap<>();
            if (!mEditTextName.getText().toString().equals("") && !mEditTextPassword.getText().toString().equals("")) {
                params.put("Name", mEditTextName.getText().toString());
                params.put("Password", mEditTextPassword.getText().toString());
                Call<User> login = api.login(params);
                login.enqueue(new Callback<User>() {
                    @Override
                    public void onResponse(Call<User> call, Response<User> response) {
//                        Log.d(TAG, response.body().toString());
                        if (response.body().isSucceed() == true) {
                            if (myviewmodel.getIsLoad().getValue() == false) {
                                myviewmodel.getIsLoad().setValue(true);
                                isConneting = false;
                                myviewmodel.getName().setValue(response.body().getData().get(0).getName());
                                myviewmodel.getLikeNum().setValue(response.body().getData().get(0).getLikeNum());
                                myviewmodel.getFansNum().setValue(response.body().getData().get(0).getFansNum());
                                myviewmodel.getTransmitNum().setValue(response.body().getData().get(0).getTransmitNum());
                                myviewmodel.getName().setValue(response.body().getData().get(0).getName());
                                Intent intent = new Intent(view.getContext(), UIactivity.class);
                                startActivity(intent);
                            }

                        } else {
                            Toast.makeText(RequestActivity.this, "登入失败，密码或账号错误", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<User> call, Throwable t) {

                    }
                });
            } else {
                Toast.makeText(this, "请输入账号密码", Toast.LENGTH_SHORT).show();
            }
        }


    }

    public void postFile(View view) {
        File file = new File("");
        RequestBody body = RequestBody.create(MediaType.parse("image/jpeg"), file);
        MultipartBody.Part part = MultipartBody.Part.createFormData("file", "", body);
//        api.postFile(part);
    }

    public void WebSocketTest(View view) throws InterruptedException, URISyntaxException {
        WebSocketTest client = new WebSocketTest("ws://10.0.2.2:8080/websocket/server");
        client.connect();
        while (client.getReadyState() != ReadyState.OPEN) {
            System.out.println("连接状态：" + client.getReadyState());
            Thread.sleep(100);
        }
        client.send("测试数据！");
        Toast.makeText(this, "链接成功", Toast.LENGTH_SHORT).show();
//        client.close();
        Map<String, Object> params = new HashMap<>();
        params.put("username", "123");
        params.put("password", "1");
        Call<BaseRespose> hello = api.WebSocketlogin(params);
        hello.enqueue(new Callback<BaseRespose>() {
            @Override
            public void onResponse(Call<BaseRespose> call, Response<BaseRespose> response) {
                Log.d(TAG, response.body().toString());
//                Log.d(TAG, "！！！！！！！！！！！！！！！！！1 ");
            }

            @Override
            public void onFailure(Call<BaseRespose> call, Throwable t) {

            }
        });

        Map<String, Object> params2 = new HashMap<>();
        params2.put("info", "123");
        Call<BaseRespose> baseResposeCall = api.broadcast(params2);
        baseResposeCall.enqueue(new Callback<BaseRespose>() {
            @Override
            public void onResponse(Call<BaseRespose> call, Response<BaseRespose> response) {
                Log.d(TAG, "!!!!!!!!!!");
            }

            @Override
            public void onFailure(Call<BaseRespose> call, Throwable t) {

            }
        });

    }

    public void hello() {

    }


//    public void sendReq(Action action, Object req, ICallback callback) {
//        sendReq(action, req, callback, StaticUtils.REQUEST_TIMEOUT);
//    }
//    public void sendReq(Action action, Object req, ICallback callback, long timeout) {
//        sendReq(action, req, callback, timeout);
//    }


}
