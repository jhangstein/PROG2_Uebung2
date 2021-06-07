import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import trafficlight.ctrl.TrafficLightCtrl;

import static org.junit.jupiter.api.Assertions.*;

class MCPTest {
    // Testing Singleton Pattern
    @Test
    @DisplayName("TrafficLightCtrl can't be null")
    public void checkSingletonPattern(){
        TrafficLightCtrl ctrl = TrafficLightCtrl.getControl();
        assertNotEquals(ctrl,null);
    }

    @Test
    @DisplayName("No two different TrafficLightCtrl allowed")
    public void checkSingletonPattern2(){
        TrafficLightCtrl ctrl = TrafficLightCtrl.getControl();
        TrafficLightCtrl ctrl2 = TrafficLightCtrl.getControl();
        assertEquals(ctrl,ctrl2);
    }


    // Testing State Transition
    @Test
    @DisplayName("Check State Transition Green to Yellow")
    public void checkStateTransitionGY(){
        TrafficLightCtrl ctrl = TrafficLightCtrl.getControl();
        ctrl.nextState();
        assertEquals(ctrl.getYellowState(), ctrl.getCurrentState());
    }
}