  
import jp.go.aist.rtm.RTC.jfsm.Event;
import jp.go.aist.rtm.RTC.jfsm.State;

import RTC.TimedString;

/**
 * 
 */
  
public class State02 extends Top {
    @Override
    public void Event02-Final(TimedString data) {
        setState(new State(FinalState.class));
    }

//    @Override
//    public void onInit() {
//    }

      @Override
      public void onEntry() {
      }

      @Override
      public void onExit() {
      }

//    @Override
//    public void Event01-02() {
//    }

}