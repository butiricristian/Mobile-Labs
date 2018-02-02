package ro.ubb.cristian.examskeletonimproved.observer;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by crist on 29-Jan-18.
 */

public class Subject {
    private List<Observer> observers;

    public Subject(){
        observers = new ArrayList<>();
    }

    public void notifyObservers(){
        for(Observer o : observers){
            o.updateMe();
        }
    }

    public void subscribe(Observer o){
        observers.add(o);
        Log.d("Subject: ", "New subscriber");
    }
}
