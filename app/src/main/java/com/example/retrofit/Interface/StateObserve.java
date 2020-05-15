package com.example.retrofit.Interface;

public class StateObserve extends AbstractSubject {
    private static volatile StateObserve INSTANCE;
    private boolean isInFriend=false;
    private boolean isInMessage=false;

    public boolean isInMessage() {
        return isInMessage;
    }

    public void setInMessage(boolean inMessage) {
        isInMessage = inMessage;
    }

    public boolean isInFriend() {
        return isInFriend;
    }

    public void setInFriend(boolean inFriend) {
        isInFriend = inFriend;
    }

    //初始化获得
    public static StateObserve getInstance() {
        if (INSTANCE == null) {
            synchronized (DataManagerObserve.class) {
                if (INSTANCE == null) {
                    INSTANCE = new StateObserve();
                }
            }
        }
        return INSTANCE;
    }

    @Override
    public void operation() {

    }
}
