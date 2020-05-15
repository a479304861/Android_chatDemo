package com.example.retrofit.UI.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.retrofit.domain.FriendRespose;

import java.util.List;

public class FriendViewModel extends ViewModel {
    private static MutableLiveData<List<FriendRespose.DataBean>> mData;


    public static MutableLiveData<List<FriendRespose.DataBean>> getmData() {
        if (mData == null) {
            mData = new MutableLiveData<>();
        }
        return mData;
    }


}
