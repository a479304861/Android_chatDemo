package com.example.retrofit.Interface;

public interface Subject {
    public void addUpdateListener(UpdateListener updateListener);

    public void delUpdateListener(UpdateListener updateListener);

    public void notifyAllUpdateListener(boolean b);

    public void operation();


}
