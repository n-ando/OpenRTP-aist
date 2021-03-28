// -*- C++ -*-
// <rtc-template block="description">
/*!
 * @file  foo.cpp
 * @brief MDesc
 * @date $Date$
 *
 * $Id$
 */
// </rtc-template>
#include "foo.h"
// Module specification
// <rtc-template block="module_spec">
static const char* const foo_spec[] =
  {
    "implementation_id", "foo",
    "type_name",         "foo",
    "description",       "MDesc",
    "version",           "1.0.1",
    "vendor",            "TA",
    "category",          "Manip",
    "activity_type",     "PERIODIC2",
    "kind",              "DataFlowComponent",
    "max_instance",      "5",
    "language",          "C++",
    "lang_type",         "compile",
    // Configuration variables
    "conf.default.int_param0", "0",
    // Widget
    // Constraints
    "conf.__type__.int_param0", "int",
    ""
  };
// </rtc-template>
/*!
 * @brief constructor
 * @param manager Maneger Object
 */
foo::foo(RTC::Manager* manager)
    // <rtc-template block="initializer">
  : RTC::DataFlowComponentBase(manager),
    p_InP1In("InP1", p_InP1),
    p_InP2In("InP2", p_InP2),
    p_OutP1Out("OutP1", p_OutP1),
    p_OutP2Out("OutP2", p_OutP2),
    p_svPortPort("svPort"),
    p_cmPortPort("cmPort")
    // </rtc-template>
{
}
/*!
 * @brief destructor
 */
foo::~foo()
{
}
RTC::ReturnCode_t foo::onInitialize()
{
  // Registration: InPort/OutPort/Service
  // <rtc-template block="registration">
  // Set InPort buffers
  addInPort("InP1", p_InP1In);
  addInPort("InP2", p_InP2In);
  // Set OutPort buffer
  addOutPort("OutP1", p_OutP1Out);
  addOutPort("OutP2", p_OutP2Out);
  // Set service provider to Ports
  p_svPortPort.registerProvider("acc", "MyService", p_acc);
  // Set service consumers to Ports
  p_cmPortPort.registerConsumer("rate", "DAQService", p_rate);
  // Set CORBA Service Ports
  addPort(p_svPortPort);
  addPort(p_cmPortPort);
  // </rtc-template>
  // <rtc-template block="bind_config">
  // Bind variables and configuration variable
  bindParameter("int_param0", p_int_param0, "0");
  // </rtc-template>
  return RTC::RTC_OK;
}
/*
RTC::ReturnCode_t foo::onFinalize()
{
  return RTC::RTC_OK;
}
*/
/*
RTC::ReturnCode_t foo::onStartup(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
*/
/*
RTC::ReturnCode_t foo::onShutdown(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
*/
/*
RTC::ReturnCode_t foo::onActivated(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
*/
/*
RTC::ReturnCode_t foo::onDeactivated(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
*/
/*
RTC::ReturnCode_t foo::onExecute(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
*/
/*
RTC::ReturnCode_t foo::onAborting(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
*/
/*
RTC::ReturnCode_t foo::onError(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
*/
/*
RTC::ReturnCode_t foo::onReset(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
*/
/*
RTC::ReturnCode_t foo::onStateUpdate(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
*/
/*
RTC::ReturnCode_t foo::onRateChanged(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
*/
extern "C"
{
  void fooInit(RTC::Manager* manager)
  {
    coil::Properties profile(foo_spec);
    manager->registerFactory(profile,
                             RTC::Create<foo>,
                             RTC::Delete<foo>);
  }
};
