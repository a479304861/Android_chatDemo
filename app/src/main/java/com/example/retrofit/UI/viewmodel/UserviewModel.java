package com.example.retrofit.UI.viewmodel;

import android.widget.TextView;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.retrofit.UI.activity.UIactivity;

public class UserviewModel extends ViewModel {
    private MutableLiveData<String> name;
    private MutableLiveData<Integer> id;
    private MutableLiveData<Integer> likeNum;
    private MutableLiveData<Integer> fansNum;
    private MutableLiveData<Integer> collectNum;
    private MutableLiveData<Integer> transmitNum;
    private MutableLiveData<Boolean> isLoad;


    public MutableLiveData<Integer> getid() {
        if (id == null) {
            id = new MutableLiveData<>();
            id.setValue(0);
        }
        return id;
    }

    public MutableLiveData<Boolean> getIsLoad() {
        if (isLoad == null) {
            isLoad = new MutableLiveData<>();
            isLoad.setValue(false);
        }
        return isLoad;
    }


    public UserviewModel() {
        this.name = new MutableLiveData<>();
        this.likeNum = new MutableLiveData<>();
        this.fansNum = new MutableLiveData<>();
        this.collectNum = new MutableLiveData<>();
        this.transmitNum = new MutableLiveData<>();
    }





    public MutableLiveData<Integer>  getCollectNum() {
        if (collectNum == null) {
            collectNum = new MutableLiveData<>();
            collectNum.setValue(0);
        }
        return collectNum;

    }
    public MutableLiveData<Integer> getLikeNum() {
        if (likeNum == null) {
            likeNum = new MutableLiveData<>();
            likeNum.setValue(0);
        }
        return likeNum;
    }
    public MutableLiveData<Integer> getTransmitNum() {
        if (transmitNum == null) {
            transmitNum = new MutableLiveData<>();
            transmitNum.setValue(0);
        }
        return transmitNum;
    }
    public MutableLiveData<String> getName() {
        if (name == null) {
            name = new MutableLiveData<>();
            name.setValue("123");
        }
        return name;
    }
    public MutableLiveData<Integer> getFansNum() {
        if (fansNum == null) {
            fansNum = new MutableLiveData<>();
            fansNum.setValue(0);
        }
        return fansNum;
    }
}
