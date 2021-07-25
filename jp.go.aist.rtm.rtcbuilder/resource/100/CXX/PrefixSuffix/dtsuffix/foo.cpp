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
    p_dtInP1Inds_s("InP1", p_dtInP1ds_s),
    p_dtInP2Inds_s("InP2", p_dtInP2ds_s),
    p_dtOutP1Outds_s("OutP1", p_dtOutP1ds_s),
    p_dtOutP2Outds_s("OutP2", p_dtOutP2ds_s),
    p_svPortPort_s("svPort"),
    p_cmPortPort_s("cmPort")
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
  addInPort("InP1", p_dtInP1Inds_s);
  addInPort("InP2", p_dtInP2Inds_s);
  // Set OutPort buffer
  addOutPort("OutP1", p_dtOutP1Outds_s);
  addOutPort("OutP2", p_dtOutP2Outds_s);
  // Set service provider to Ports
  p_svPortPort_s.registerProvider("acc", "MyService", p_acc_s);
  // Set service consumers to Ports
  p_cmPortPort_s.registerConsumer("rate", "DAQService", p_rate_s);
  // Set CORBA Service Ports
  addPort(p_svPortPort_s);
  addPort(p_cmPortPort_s);
  // </rtc-template>
  // <rtc-template block="bind_config">
  // Bind variables and configuration variable
  bindParameter("int_param0", p_int_param0_s, "0");
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
