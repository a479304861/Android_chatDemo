package com.example.retrofit.Interface;

public class DataManagerObserve extends  AbstractSubject{

    private static volatile DataManagerObserve INSTANCE;
    private boolean isHavingUpdate;
    private boolean isHavingMessage;

    public boolean getisHavingMessage() {
        return isHavingMessage;
    }

    public boolean getIsHavingUpdate() {
        return isHavingUpdate;
    }

    public void setHavingMessage(boolean havingMessage) {
        isHavingMessage = havingMessage;
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
        System.out.println("isHavingUpdate——————>" + isHavingMessage);
        notifyAllUpdateListener(isHavingUpdate);
        notifyAllUpdateListener(isHavingMessage);
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
