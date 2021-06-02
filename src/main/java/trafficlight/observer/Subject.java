package trafficlight.observer;

import java.util.ArrayList;
import java.util.List;

public class Subject {
    List<Observer> allObservers = new ArrayList<Observer>();

    public void registerObserver(Observer o){
        allObservers.add(o);
    }

    public void unregisterObserver(Observer o){
        allObservers.remove(o);
    }

    public void notifyObservers(String s){
        for (Observer o: allObservers){
            o.update(s);
        }
    }
}
