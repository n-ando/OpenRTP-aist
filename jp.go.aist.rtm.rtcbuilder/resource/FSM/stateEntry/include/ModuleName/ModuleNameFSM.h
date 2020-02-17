// -*- C++ -*-
/*!
 * @file  ModuleNameFSM.h
 * @date  $Date$
 * $Id$
 */

#ifndef MODULENAMEFSM_H
#define MODULENAMEFSM_H

#include <rtm/StaticFSM.h>
#include <rtm/idl/BasicDataTypeSkel.h>
#include <rtm/RTC.h>

class ModuleName;

namespace ModuleNameFsm {

  /*!
   * @if jp
   * @class TOP状態
   *
   *
   * @else
   * @brief TOP state
   *
   * @endif
   */
  FSM_TOPSTATE(Top) {
    // Top state variables (visible to all substates)
  
    FSM_STATE(Top);

    // Machine's event protocol
    /*!
     * Abst01-02
     * - Type: Type01-02
     * - Number: Num01-02
     * - Semantics: Detail01-02
     * - Unit: Unit01-02
     * - Operation Cycle: Period01-02
     */
    virtual void Event01_02(RTC::TimedLong data) {}
    
    /*!
     * Abst02-Final
     * - Type: Type02-Final
     * - Number: Num02-Final
     * - Semantics: Detail02-Final
     * - Unit: Unit02-Final
     * - Operation Cycle: Period02-Final
     */
    virtual void Event02_Final(RTC::TimedString data) {}
    
  
   private:
     RTC::ReturnCode_t onInit() override;
     RTC::ReturnCode_t onEntry() override;
     RTC::ReturnCode_t onExit() override;
  };

  FSM_SUBSTATE(State01, Top) {
    FSM_STATE(State01);
  

    // Event handler
    void Event01_02(RTC::TimedLong data) override;

    private:
      // The onInit method provides a special kind of entry action.
      // On state transition, onEntry functions are called at the target state and its superstate.
      // But an onInit function is called only at the target state.
      RTC::ReturnCode_t onInit() override;
      RTC::ReturnCode_t onEntry() override;
      RTC::ReturnCode_t onExit() override;
  };

  FSM_SUBSTATE(State02, Top) {
    FSM_STATE(State02);
  

    // Event handler
    void Event02_Final(RTC::TimedString data) override;

    private:
      // The onInit method provides a special kind of entry action.
      // On state transition, onEntry functions are called at the target state and its superstate.
      // But an onInit function is called only at the target state.
      RTC::ReturnCode_t onInit() override;
      RTC::ReturnCode_t onEntry() override;
      RTC::ReturnCode_t onExit() override;
  };

  FSM_SUBSTATE(FinalState, Top) {
    FSM_STATE(FinalState);
  

    // Event handler

    private:
      // The onInit method provides a special kind of entry action.
      // On state transition, onEntry functions are called at the target state and its superstate.
      // But an onInit function is called only at the target state.
      RTC::ReturnCode_t onInit() override;
  };


} //end namespace 'ModuleNameFSM'

#endif // MODULENAMEFSM_H
