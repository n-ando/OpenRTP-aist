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
    def Event01-02(self):
        pass

    """
    """
    def Event02-Final(self):
        pass


  
@StaticFSM.FSM_SUBSTATE(Top)
class State01(StaticFSM.Link):
    def Event01-02(self):
        self.set_state(StaticFSM.State(State02))
  
@StaticFSM.FSM_SUBSTATE(Top)
class State02(StaticFSM.Link):
    def Event02-Final(self):
        self.set_state(StaticFSM.State(FinalState))

