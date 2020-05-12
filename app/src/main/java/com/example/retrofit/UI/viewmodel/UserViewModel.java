package com.example.retrofit.UI.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class UserViewModel extends ViewModel {
    private static MutableLiveData<String> name;
    private static MutableLiveData<Integer> id;
    private static MutableLiveData<Integer> likeNum;
    private static MutableLiveData<Integer> fansNum;
    private static MutableLiveData<Integer> collectNum;
    private static MutableLiveData<Integer> transmitNum;
    private static MutableLiveData<Boolean> isLoad;


    public static MutableLiveData<Integer> getId() {
        if (id == null) {
            id = new MutableLiveData<>();
            id.setValue(0);
        }
        return id;
    }

    public static MutableLiveData<Boolean> getIsLoad() {
        if (isLoad == null) {
            isLoad = new MutableLiveData<>();
            isLoad.setValue(false);
        }
        return isLoad;
    }


    public UserViewModel() {
        name = new MutableLiveData<>();
        likeNum = new MutableLiveData<>();
        fansNum = new MutableLiveData<>();
        collectNum = new MutableLiveData<>();
        transmitNum = new MutableLiveData<>();
    }


    public static void addLikeNum(){
        if (likeNum.getValue()!=null) {
        likeNum.setValue(likeNum.getValue()+1);
        }
    }


    public static MutableLiveData<Integer>  getCollectNum() {
        if (collectNum == null) {
            collectNum = new MutableLiveData<>();
            collectNum.setValue(0);
        }
        return collectNum;

    }
    public static MutableLiveData<Integer> getLikeNum() {
        if (likeNum == null) {
            likeNum = new MutableLiveData<>();
            likeNum.setValue(0);
        }
        return likeNum;
    }
    public static MutableLiveData<Integer> getTransmitNum() {
        if (transmitNum == null) {
            transmitNum = new MutableLiveData<>();
            transmitNum.setValue(0);
        }
        return transmitNum;
    }
    public static MutableLiveData<String> getName() {
        if (name == null) {
            name = new MutableLiveData<>();
            name.setValue("123");
        }
        return name;
    }
    public static MutableLiveData<Integer> getFansNum() {
        if (fansNum == null) {
            fansNum = new MutableLiveData<>();
            fansNum.setValue(0);
        }
        return fansNum;
    }
}
