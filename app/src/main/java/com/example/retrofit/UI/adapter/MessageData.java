package com.example.retrofit.UI.adapter;

import com.example.retrofit.domain.MessageRespose;

import java.util.ArrayList;
import java.util.List;

public class MessageData {
    private static List<DataBean> data;

    public MessageData() {
        data=new ArrayList<>();
    }

    public static List<DataBean> getData() {
        if (data==null){
            data=new ArrayList<>();
        }
        return data;
    }

    public static void setData(List<DataBean> datatemp) {
        data = datatemp;
    }

    @Override
    public String toString() {
        return "MessageData{" +
                "data=" + data +
                '}';
    }

    public static class DataBean{
        public DataBean() {
            this.content = "";
            this.isUser = false;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "content='" + content + '\'' +
                    ", isUser=" + isUser +
                    '}';
        }

        private String content;
        private boolean isUser;

        public String getContent() {
            return content;
        }

        public boolean getIsUser() {
            return isUser;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public void setUser(boolean user) {
            isUser = user;
        }
    }
}
