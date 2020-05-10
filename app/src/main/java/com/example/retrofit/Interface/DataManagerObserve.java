package com.example.retrofit.Interface;

public class DataManagerObserve extends  AbstractSubject{

    private static volatile DataManagerObserve INSTANCE;
    private boolean isHavingUpdate;
    public boolean getIsHavingUpdate() {
        return isHavingUpdate;
    }

    public void setHavingUpdate(boolean havingUpdate) {
        isHavingUpdate = havingUpdate;
    }
    private DataManagerObserve() {
    }
    public static DataManagerObserve getInstance() {
        if (INSTANCE == null) {
            synchronized (DataManagerObserve.class) {
                if (INSTANCE == null) {
                    INSTANCE = new DataManagerObserve();
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
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
