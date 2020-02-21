#!/usr/bin/env python
# -*- coding: utf-8 -*-

"""
 @file ModuleNameFSM.py
 @brief ModuleDescription
 @date $Date$


"""
import sys

import RTC
import OpenRTM_aist.StaticFSM as StaticFSM

@StaticFSM.FSM_TOPSTATE
class Top(StaticFSM.Link):
    def onInit(self):
        self.set_state(StaticFSM.State(State01))
        return RTC.RTC_OK
        
    """
    Abst01-02
    - Type: Type01-02
     - Number: Num01-02
     - Semantics: Detail01-02
    - Unit: Unit01-02
     - Operation Cycle: Period01-02
    """
    def Event01_02(self, data):
        pass

    """
    Abst02-Final
    - Type: Type02-Final
     - Number: Num02-Final
     - Semantics: Detail02-Final
    - Unit: Unit02-Final
     - Operation Cycle: Period02-Final
    """
    def Event02_Final(self, data):
        pass


  
@StaticFSM.FSM_SUBSTATE(Top)
class State01(StaticFSM.Link):
    # The onInit method provides a special kind of entry action.
    # On state transition, onEntry functions are called at the target state and its superstate.
    # But an onInit function is called only at the target state.
    def onInit(self):
        return RTC.RTC_OK

    def onEntry(self):
        return RTC.RTC_OK

    def onExit(self):
        return RTC.RTC_OK

    def Event01_02(self, data):
        self.set_state(StaticFSM.State(State02))
  
@StaticFSM.FSM_SUBSTATE(Top)
class State02(StaticFSM.Link):
    # The onInit method provides a special kind of entry action.
    # On state transition, onEntry functions are called at the target state and its superstate.
    # But an onInit function is called only at the target state.
    def onInit(self):
        return RTC.RTC_OK

    def onEntry(self):
        return RTC.RTC_OK

    def onExit(self):
        return RTC.RTC_OK

    def Event02_Final(self, data):
        self.set_state(StaticFSM.State(FinalState))
  
@StaticFSM.FSM_SUBSTATE(Top)
class FinalState(StaticFSM.Link):
    # The onInit method provides a special kind of entry action.
    # On state transition, onEntry functions are called at the target state and its superstate.
    # But an onInit function is called only at the target state.
    def onInit(self):
        return RTC.RTC_OK

