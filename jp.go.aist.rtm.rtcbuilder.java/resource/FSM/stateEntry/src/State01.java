  
import jp.go.aist.rtm.RTC.jfsm.Event;
import jp.go.aist.rtm.RTC.jfsm.State;

import RTC.TimedLong;

/**
 * 
 */
  
public class State01 extends Top {
    @Override
    public void Event01_02(TimedLong data) {
        setState(new State(State02.class));
    }

    // The onInit method provides a special kind of entry action.
    // On state transition, onEntry functions are called at the target state and its superstate.
    // But an onInit function is called only at the target state.
    @Override
    public void onInit() {
    
    }

    @Override
    public void onEntry() {
    }

    @Override
    public void onExit() {
    }

}