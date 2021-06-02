package trafficlight.ctrl;

import trafficlight.gui.TrafficLightGui;
import trafficlight.states.State;

public class TrafficLightCtrl {

    private State greenState;

    private State redState;

    private State yellowState;

    private State currentState;

    private State previousState;

    private final TrafficLightGui gui;

    private boolean doRun = true;

    // Singleton Pattern: private constructor and instance, Getter to access (creation of only one instance allowed)
    private static TrafficLightCtrl control = null;

    public static TrafficLightCtrl getControl() {
        if (control == null){
            control = new TrafficLightCtrl();
        }
        return control;
    }

    private TrafficLightCtrl() {
        super();
        initStates();
        gui = new TrafficLightGui(this);
        gui.setVisible(true);
        //TODO used to update the current state -> register Observers
        greenState.registerObserver(gui);
        yellowState.registerObserver(gui);
        redState.registerObserver(gui);
    }

    private void initStates() {
        greenState = new State() {
            @Override
            public State getNextState() {
                previousState = currentState;
                //TODO used to update the current state and the old one -> notifyObservers
                notifyObservers(getColor());
                return yellowState;
            }
            @Override
            public String getColor() {
                return "green";
            }
        };

        redState = new State() {
            @Override
            public State getNextState() {
                previousState = currentState;
                //TODO used to update the current state and the old one -> notifyObservers
                notifyObservers(getColor());
                return yellowState;
            }
            @Override
            public String getColor() {
                return "red";
            }
        };

        yellowState = new State() {
            @Override
            public State getNextState() {
                if (previousState.equals(greenState)) {
                    previousState = currentState;
                    //TODO used to update the current state and the old one -> notifyObservers
                    notifyObservers(getColor());
                    return redState;
                }else {
                    previousState = currentState;
                    //TODO used to update the current state and the old one -> notifyObservers
                    notifyObservers(getColor());
                    return greenState;
                }
            }
            @Override
            public String getColor() {
                return "yellow";
            }
        };
        currentState = greenState;
        previousState = yellowState;
    }

    public State getGreenState() {
        return greenState;
    }

    public State getRedState() {
        return redState;
    }

    public State getYellowState() {
        return yellowState;
    }

    public void run()  {
        int intervall = 1500;
        while (doRun) {
            try {
                Thread.sleep(intervall);
                nextState();
            } catch (InterruptedException e) {
                gui.showErrorMessage(e);
            }
        }
        System.out.println("Stopped");
        System.exit(0);
    }

    public void nextState() {
        currentState = currentState.getNextState();
    }

    public void stop() {
        doRun = false;
    }
}