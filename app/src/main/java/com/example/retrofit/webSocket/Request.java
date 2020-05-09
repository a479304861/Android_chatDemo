package com.example.retrofit.webSocket;

import com.google.gson.annotations.SerializedName;

public class Request<T> {
    @Override
    public String toString() {
        return "Request{" +
                "action='" + action + '\'' +
                ", reqEvent=" + reqEvent +
                ", seqId=" + seqId +
                ", req=" + req +
                ", reqCount=" + reqCount +
                '}';
    }

    @SerializedName("action")
    private String action;
    @SerializedName("req_event")
    private int reqEvent;
    @SerializedName("seq_id")
    private long seqId;
    @SerializedName("req")
    private T req;
    private transient int reqCount;
    public Request(String action, int reqEvent, long seqId, T req, int reqCount) {
        this.action = action;
        this.reqEvent = reqEvent;
        this.seqId = seqId;
        this.req = req;
        this.reqCount = reqCount;
    }

    //这里还有各个参数对应get、set方法,为节省篇幅省略了

    public static class Builder<T> {
        private String action;
        private int reqEvent;
        private long seqId;
        private T req;
        private int reqCount;

        public Builder action(String action) {
            this.action = action;
            return this;
        }

        public Builder reqEvent(int reqEvent) {
            this.reqEvent = reqEvent;
            return this;
        }

        public Builder seqId(long seqId) {
            this.seqId = seqId;
            return this;
        }

        public Builder req(T req) {
            this.req = req;
            return this;
        }

        public Builder reqCount(int reqCount) {
            this.reqCount = reqCount;
            return this;
        }

        public Request build() {
            return new Request<T>(action, reqEvent, seqId, req, reqCount);
        }

    }
}
