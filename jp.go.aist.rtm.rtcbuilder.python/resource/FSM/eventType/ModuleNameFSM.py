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
    """
    def Event01_02(self, data):
        pass

    """
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

    def Event01_02(self, data):
        self.set_state(StaticFSM.State(State02))
  
@StaticFSM.FSM_SUBSTATE(Top)
class State02(StaticFSM.Link):
    # The onInit method provides a special kind of entry action.
    # On state transition, onEntry functions are called at the target state and its superstate.
    # But an onInit function is called only at the target state.
    def onInit(self):
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

