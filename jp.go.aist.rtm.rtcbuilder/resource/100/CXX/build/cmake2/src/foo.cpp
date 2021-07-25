// -*- C++ -*-
// <rtc-template block="description">
/*!
 * @file  foo.cpp
 * @brief test module
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
    "description",       "test module",
    "version",           "1.0.1",
    "vendor",            "TA",
    "category",          "sample",
    "activity_type",     "PERIODIC",
    "kind",              "DataFlowComponent",
    "max_instance",      "2",
    "language",          "C++",
    "lang_type",         "compile",
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
    m_MyServiceProviderPort("MyServiceProvider"),
    m_MyServiceRequirePort("MyServiceRequire")
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
  m_MyServiceProviderPort.registerProvider("MyServiceProvider", "MyServiceChild", m_MyServiceProvider);
  m_MyServiceProviderPort.registerProvider("MyServiceProvider2", "MyServiceWithTypeChild", m_MyServiceProvider2);
  // Set service consumers to Ports
  m_MyServiceRequirePort.registerConsumer("MyServiceRequire", "MyServiceChild", m_MyServiceRequire);
  m_MyServiceRequirePort.registerConsumer("MyServiceRequire2", "MyServiceWithTypeChild", m_MyServiceRequire2);
  // Set CORBA Service Ports
  addPort(m_MyServiceProviderPort);
  addPort(m_MyServiceRequirePort);
  // </rtc-template>
  // <rtc-template block="bind_config">
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
