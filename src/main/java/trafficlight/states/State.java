package trafficlight.states;

//TODO implement a part of the pattern here -> State = Subject

import trafficlight.observer.Subject;

public abstract class State extends Subject {

    public abstract State getNextState();

    public abstract String getColor();

    public String getString(){
        return getColor();
    }
}