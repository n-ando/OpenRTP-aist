// -*- C++ -*-
// <rtc-template block="description">
/*!
 * @file  foo.cpp
 * @brief MDesc
 *
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
    "version",           "1.0.3",
    "vendor",            "TA2",
    "category",          "manip2",
    "activity_type",     "PERIODIC2",
    "kind",              "DataFlowComponent",
    "max_instance",      "3",
    "language",          "C++",
    "lang_type",         "compile",
    // Configuration variables
    "conf.default.test", "0",
    // Widget
    "conf.__widget__.test", "slider.0.2",
    // Constraints
    "conf.__constraints__.test", "-1.0<x<1.0",
    "conf.__type__.test", "double",
    ""
  };
// </rtc-template>
/*!
 * @brief constructor
 * @param manager Maneger Object
 */
foo::foo(RTC::Manager* manager)
    // <rtc-template block="initializer">
  : RTC::DataFlowComponentBase(manager)
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
  // Set OutPort buffer
  // Set service provider to Ports
  // Set service consumers to Ports
  // Set CORBA Service Ports
  // </rtc-template>
  // <rtc-template block="bind_config">
  // Bind variables and configuration variable
  bindParameter("test", m_test, "0");
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
RTC::ReturnCode_t foo::onStartup(RTC::UniqueId /*ec_id*/)
{
  return RTC::RTC_OK;
}
*/
/*
RTC::ReturnCode_t foo::onShutdown(RTC::UniqueId /*ec_id*/)
{
  return RTC::RTC_OK;
}
*/
/*
RTC::ReturnCode_t foo::onActivated(RTC::UniqueId /*ec_id*/)
{
  return RTC::RTC_OK;
}
*/
/*
RTC::ReturnCode_t foo::onDeactivated(RTC::UniqueId /*ec_id*/)
{
  return RTC::RTC_OK;
}
*/
/*
RTC::ReturnCode_t foo::onExecute(RTC::UniqueId /*ec_id*/)
{
  return RTC::RTC_OK;
}
*/
/*
RTC::ReturnCode_t foo::onAborting(RTC::UniqueId /*ec_id*/)
{
  return RTC::RTC_OK;
}
*/
/*
RTC::ReturnCode_t foo::onError(RTC::UniqueId /*ec_id*/)
{
  return RTC::RTC_OK;
}
*/
/*
RTC::ReturnCode_t foo::onReset(RTC::UniqueId /*ec_id*/)
{
  return RTC::RTC_OK;
}
*/
/*
RTC::ReturnCode_t foo::onStateUpdate(RTC::UniqueId /*ec_id*/)
{
  return RTC::RTC_OK;
}
*/
/*
RTC::ReturnCode_t foo::onRateChanged(RTC::UniqueId /*ec_id*/)
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
}
