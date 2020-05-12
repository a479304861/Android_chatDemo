package com.example.retrofit.UI.adapter;

import com.example.retrofit.domain.MessageRespose;

import java.util.ArrayList;
import java.util.List;

public class MessageData {
    private List<DataBean> data;

    public MessageData() {
        data=new ArrayList<>();
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
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
