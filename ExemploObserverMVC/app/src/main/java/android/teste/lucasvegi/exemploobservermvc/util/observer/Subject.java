package android.teste.lucasvegi.exemploobservermvc.util.observer;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public abstract class Subject {
    private List<Observer> observers = new ArrayList<Observer>();

    public void addObserver(Observer o){
        this.observers.add(o);
        Log.i("Observer", "Adicionou observador");
    }

    public void removeObserver(Observer o){
        this.observers.remove(o);
        Log.i("Observer", "Removeu observador");
    }

    protected void notifyObservers(){
        for(Observer o : this.observers){
            Log.i("Observer", "Notificou observador");
            o.update(this);
        }
    }
}
