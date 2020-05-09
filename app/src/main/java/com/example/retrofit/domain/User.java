package com.example.retrofit.domain;

import java.util.List;
import java.util.Map;

public class User {

    private  boolean succeed;
    private List<DataBean> data;

    public User(boolean succeed, List<DataBean> data) {
        this.succeed = succeed;
        this.data = data;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "User{" +
                "succeed=" + succeed +
                ", data=" + data +
                '}';
    }
    public static class  DataBean{
        private int id;
        private String name;
        private  String password;
        private int likeNum;
        private int fansNum;

        public int getTransmitNum() {
            return transmitNum;
        }

        public void setTransmitNum(int transmitNum) {
            this.transmitNum = transmitNum;
        }

        private int collectNum;
        private int transmitNum;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getLikeNum() {
            return likeNum;
        }

        public void setLikeNum(int likeNum) {
            this.likeNum = likeNum;
        }

        public int getFansNum() {
            return fansNum;
        }

        public void setFansNum(int fansNum) {
            this.fansNum = fansNum;
        }

        public int getCollectNum() {
            return collectNum;
        }

        public void setCollectNum(int collectNum) {
            this.collectNum = collectNum;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", password='" + password + '\'' +
                    ", likeNum=" + likeNum +
                    ", fansNum=" + fansNum +
                    ", collectNum=" + collectNum +
                    ", transmitNum=" + transmitNum +
                    '}';
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
    public boolean isSucceed() {
        return succeed;
    }
    public void setSucceed(boolean succeed) {
        this.succeed = succeed;
    }

}
