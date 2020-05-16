package com.example.retrofit.UI.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofit.Interface.Api;
import com.example.retrofit.Interface.DataManagerObserve;
import com.example.retrofit.Interface.UpdateListener;
import com.example.retrofit.UI.adapter.FriendAdapter;
import com.example.retrofit.UI.viewmodel.FriendViewModel;
import com.example.retrofit.UI.viewmodel.UserViewModel;
import com.example.retrofit.R;
import com.example.retrofit.domain.FriendRespose;
import com.example.retrofit.utile.RetrofitManager;
import com.example.retrofit.webSocket.WebSocketTest;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class UiActivity extends AppCompatActivity {

    private long exitTime;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_u_iactivity);
        BottomNavigationView bottomNavigationView=findViewById(R.id.UiActivity_BottomNavigationView);
        NavController navigation = Navigation.findNavController(this,R.id.UiActivity_fragment);
        NavigationUI.setupWithNavController(bottomNavigationView,navigation);
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode== KeyEvent.KEYCODE_BACK){
            if((System.currentTimeMillis()-exitTime)>2000){
                Toast.makeText(UiActivity.this,"再按一次退出",Toast.LENGTH_SHORT).show();
                exitTime=System.currentTimeMillis();
            }else{
                System.exit(0);
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }


}
