package com.example.retrofit.UI.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.retrofit.domain.FriendRespose;

import java.util.List;

public class FriendViewModel extends ViewModel {
    private static MutableLiveData<List<FriendRespose.DataBean>> mData;
    private static MutableLiveData<Boolean> isUpdate;

    public static MutableLiveData<List<FriendRespose.DataBean>> getmData() {
        if (mData == null) {
            mData = new MutableLiveData<>();
            mData.setValue(null);
        }
        return mData;
    }
    public static MutableLiveData<Boolean> getisUpdate() {
        if (isUpdate == null) {
            isUpdate = new MutableLiveData<>();
            isUpdate.setValue(false);
        }
        return isUpdate;
    }

}
