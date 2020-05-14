package com.example.retrofit.domain;

import java.util.ArrayList;
import java.util.List;

public class FriendRespose {
    private boolean succeed;

    private List<DataBean> data;

    public FriendRespose() {
        this.succeed = false;
        this.data = new ArrayList<DataBean>();
    }



    public boolean getSucceed() {
        return succeed;
    }
    public void setSucceed(boolean succeed) {
        this.succeed = succeed;
    }
    public List<DataBean> getData() {
        return data;
    }
    public void addData(DataBean dataBean) {
        data.add(dataBean);
    }



    public static class DataBean{
        private String lastMessage;
        private int friendId;
        private String isOnline;
        private String name;
        private String time;

        @Override
        public String toString() {
            return "DataBean{" +
                    "lastMessage='" + lastMessage + '\'' +
                    ", friendId=" + friendId +
                    ", isOnline='" + isOnline + '\'' +
                    ", name='" + name + '\'' +
                    ", lastTime='" + time + '\'' +
                    '}';
        }

        public String getLastTime() {
            return time;
        }

        public void setLastTime(String lastTime) {
            this.time = lastTime;
        }

        public String getLastMessage() {
            return lastMessage;
        }

        public void setLastMessage(String lastMessage) {
            this.lastMessage = lastMessage;
        }

        public String getIsOnline() {
            return isOnline;
        }

        public void setIsOnline(String isOnline) {
            this.isOnline = isOnline;
        }

        public int getFriendId() {
            return friendId;
        }
        public void setFriendId(int friendId) {
            this.friendId = friendId;
        }
        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}
