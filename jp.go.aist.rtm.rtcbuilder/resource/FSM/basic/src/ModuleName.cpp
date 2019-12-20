// -*- C++ -*-
/*!
 * @file  ModuleName.cpp
 * @brief ModuleDescription
 * @date $Date$
 *
 * $Id$
 */

#include "ModuleName.h"

// Module specification
// <rtc-template block="module_spec">
static const char* modulename_spec[] =
  {
    "implementation_id", "ModuleName",
    "type_name",         "ModuleName",
    "description",       "ModuleDescription",
    "version",           "1.0.0",
    "vendor",            "VenderName",
    "category",          "Category",
    "activity_type",     "PERIODIC",
    "kind",              "DataFlowComponent",
    "max_instance",      "1",
    "language",          "C++",
    "lang_type",         "compile",
    ""
  };
// </rtc-template>

/*!
 * @brief constructor
 * @param manager Maneger Object
 */
ModuleName::ModuleName(RTC::Manager* manager)
    // <rtc-template block="initializer">
  : RTC::DataFlowComponentBase(manager),
    m_fsm(this),
    m_FSMEventIn("event", m_fsm)

    // </rtc-template>
{
}

/*!
 * @brief destructor
 */
ModuleName::~ModuleName()
{
}



RTC::ReturnCode_t ModuleName::onInitialize()
{
#ifdef ORB_IS_TAO
  ::CORBA_Util::toRepositoryIdOfStruct<TimedLong>();
#endif
  // Registration: InPort/OutPort/Service
  // <rtc-template block="registration">
  // Set InPort buffers
  
  // Set OutPort buffer

  // Set EventPort buffer
  addInPort("event", m_FSMEventIn);
  
  // Set service provider to Ports
  
  // Set service consumers to Ports
  
  // Set CORBA Service Ports
  
  // </rtc-template>

  // <rtc-template block="bind_config">
  // </rtc-template>
  // <rtc-template block="bind_event">
  // </rtc-template>

  
  return RTC::RTC_OK;
}

/*
RTC::ReturnCode_t ModuleName::onFinalize()
{
  return RTC::RTC_OK;
}
*/

/*
RTC::ReturnCode_t ModuleName::onStartup(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
*/

/*
RTC::ReturnCode_t ModuleName::onShutdown(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
*/

/*
RTC::ReturnCode_t ModuleName::onActivated(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
*/

/*
RTC::ReturnCode_t ModuleName::onDeactivated(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
*/

/*
RTC::ReturnCode_t ModuleName::onExecute(RTC::UniqueId ec_id)
{
  m_fsm.run_event();
  return RTC::RTC_OK;
}
*/

/*
RTC::ReturnCode_t ModuleName::onAborting(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
*/

/*
RTC::ReturnCode_t ModuleName::onError(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
*/

/*
RTC::ReturnCode_t ModuleName::onReset(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
*/

/*
RTC::ReturnCode_t ModuleName::onStateUpdate(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
*/

/*
RTC::ReturnCode_t ModuleName::onRateChanged(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
*/



extern "C"
{
 
  void ModuleNameInit(RTC::Manager* manager)
  {
    coil::Properties profile(modulename_spec);
    manager->registerFactory(profile,
                             RTC::Create<ModuleName>,
                             RTC::Delete<ModuleName>);
  }
  
};


