package com.example.retrofit.Interface;

import java.util.HashMap;

public class DataManager extends  AbstractSubject{

    private static volatile DataManager INSTANCE;
    private boolean isHavingUpdate;
    public boolean getIsHavingUpdate() {
        return isHavingUpdate;
    }

    public void setHavingUpdate(boolean havingUpdate) {
        isHavingUpdate = havingUpdate;
    }

    private DataManager() {
    }

    public static DataManager getInstance() {
        if (INSTANCE == null) {
            synchronized (DataManager.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DataManager();
                }
            }
        }
        return INSTANCE;
    }


    @Override
    public void operation() {
        System.out.println("isHavingUpdate——————>" + isHavingUpdate);
        notifyAllUpdateListener(isHavingUpdate);
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }
}
