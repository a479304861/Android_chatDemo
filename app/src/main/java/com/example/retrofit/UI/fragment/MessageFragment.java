package com.example.retrofit.UI.fragment;

import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.retrofit.Interface.Api;
import com.example.retrofit.Interface.DataManagerObserve;
import com.example.retrofit.Interface.StateObserve;
import com.example.retrofit.Interface.UpdateListener;
import com.example.retrofit.R;
import com.example.retrofit.UI.activity.RequestActivity;
import com.example.retrofit.UI.adapter.FriendAdapter;
import com.example.retrofit.UI.viewmodel.FriendViewModel;
import com.example.retrofit.UI.viewmodel.UserViewModel;
import com.example.retrofit.domain.FriendRespose;
import com.example.retrofit.utile.RetrofitManager;
import com.example.retrofit.webSocket.WebSocketTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

/**
 * A simple {@link Fragment} subclass.
 */
public class MessageFragment extends Fragment {

    private static final String TAG = "UIactivity";
    private Api api;
    private Retrofit retrofit;

    private FriendAdapter friendAdapter;
    private static WebSocketTest client;

    UserViewModel myviewModel;
    TextView mCollectNum, mLikeNum, mFansNum, mTransmit, mName;
    private static RecyclerView mRecyclerView;
    private static FriendViewModel friendViewModel;

    public MessageFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        friendViewModel = new ViewModelProvider(this).get(FriendViewModel.class);
//        mCollectNum = view.findViewById(R.id.textViewCollect);
//        mLikeNum = view.findViewById(R.id.textView_Like);
//        mFansNum = view.findViewById(R.id.textViewFans);
//        mTransmit = view.findViewById(R.id.textViewTransmit);
        mName = view.findViewById(R.id.textViewName);
        mRecyclerView = view.findViewById(R.id.recyclerView);
        init();
        return view;
    }

//    @Override
//    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
//        inflater.inflate(R.menu.addfriend_menu, menu);
//        super.onCreateOptionsMenu(menu, inflater);
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        switch (item.getItemId()) {
//            case R.id.option_normal_1:
//                return true;
//            case R.id.option_normal_2:
//                return true;
//            case R.id.option_normal_3:
//                return true;
//            case R.id.option_normal_4:
//                return true;
//            default:
//                return super.onOptionsItemSelected(item);
//        }
//    }


    @Override
    public void onResume() {
        StateObserve instance=StateObserve.getInstance();
        instance.setInFriend(true);
        getFriend();
        super.onResume();
    }

    @Override
    public void onPause() {
        StateObserve instance=StateObserve.getInstance();
        instance.setInFriend(false);
        Log.d(TAG, "onPause: ");
        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d(TAG, "onStop: ");
    }



    @RequiresApi(api = Build.VERSION_CODES.M)
        private void init () {

            retrofit = RetrofitManager.getRetrofit();
            api = retrofit.create(Api.class);
            myviewModel = RequestActivity.getMyviewmodel();
            observe();
            getFriend();

//            mLikeNum.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    myviewModel.addLikeNum();
//                }
//            });

        }
//
        private void getFriend () {
            Map<String, Object> params = new HashMap<>();
            params.put("id", String.valueOf(myviewModel.getId().getValue()));
            Call<FriendRespose> friend = api.getFriend(params);
            friend.enqueue(new Callback<FriendRespose>() {
                @Override
                public void onResponse(Call<FriendRespose> call, Response<FriendRespose> response) {

                    friendViewModel.getmData().setValue(response.body().getData());
                }

                @Override
                public void onFailure(Call<FriendRespose> call, Throwable t) {
                    Log.d(TAG, "onFailure: ");
                }
            });
        }

        private void observe () {
            DataManagerObserve instance = DataManagerObserve.getInstance();
            StateObserve stateObserve =StateObserve.getInstance();
            instance.addUpdateListener(new UpdateListener() {
                @Override
                public void update(boolean b) {
                    if (stateObserve.isInFriend()) {
                        if (instance.getIsHavingUpdate() == true) {
                            instance.setHavingUpdate(false);
                            getFriend();
                        }
                    }

                }
            });
            instance.operation();

            myviewModel.getName().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(String s) {
                    mName.setText(s);
                }
            });
//            myviewModel.getLikeNum().observe(getViewLifecycleOwner(), new Observer<Integer>() {
//                @Override
//                public void onChanged(Integer integer) {
//                    mLikeNum.setText(String.valueOf(integer));
//                }
//            });
//            myviewModel.getCollectNum().observe(getViewLifecycleOwner(), new Observer<Integer>() {
//                @Override
//                public void onChanged(Integer integer) {
//                    mCollectNum.setText(String.valueOf(integer));
//                }
//            });
//            myviewModel.getFansNum().observe(getViewLifecycleOwner(), new Observer<Integer>() {
//                @Override
//                public void onChanged(Integer integer) {
//                    mFansNum.setText(String.valueOf(integer));
//                }
//            });
//            myviewModel.getTransmitNum().observe(getViewLifecycleOwner(), new Observer<Integer>() {
//                @Override
//                public void onChanged(Integer integer) {
//                    mTransmit.setText(String.valueOf(integer));
//                }
//            });
            friendViewModel.getmData().observe(getViewLifecycleOwner(), new Observer<List<FriendRespose.DataBean>>() {
                @Override
                public void onChanged(List<FriendRespose.DataBean> dataBeans) {

                    friendAdapter = new FriendAdapter(dataBeans);
                    mRecyclerView.setAdapter(friendAdapter);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
                    mRecyclerView.setLayoutManager(linearLayoutManager);
                }
            });

        }

}