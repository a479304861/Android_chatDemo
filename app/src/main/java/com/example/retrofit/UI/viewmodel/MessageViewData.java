package com.example.retrofit.UI.viewmodel;

import com.example.retrofit.domain.MessageRespose;

import java.util.ArrayList;
import java.util.List;

public class MessageViewData {
    private  List<MessageRespose.DataBean> mData;

    public List<MessageRespose.DataBean> getmData() {
        if (mData == null) {
            mData = new ArrayList<>();
        }
        return mData;
    }

    public void setData(List<MessageRespose.DataBean> data) {
        mData = data;
    }

    public  void addDataBean(MessageRespose.DataBean dataBean){
        mData.add(dataBean);
    }
}
