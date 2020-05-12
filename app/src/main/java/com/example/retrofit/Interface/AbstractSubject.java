package com.example.retrofit.Interface;

import java.util.Iterator;
import java.util.Vector;

public abstract class AbstractSubject implements Subject {

    private Vector<UpdateListener> vector = new Vector<UpdateListener>();

    @Override
    public void addUpdateListener(UpdateListener updateListener) {
        vector.add(updateListener);
    }

    @Override
    public void delUpdateListener(UpdateListener updateListener) {
        vector.remove(updateListener);
    }




    @Override
    public void notifyAllUpdateListener(boolean b) {
        Iterator<UpdateListener> iterator = vector.iterator();
        while (iterator.hasNext()) {
            UpdateListener listener = iterator.next();
            listener.update(b);
        }
    }

}
