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
    m_VarInP1In("InP1", m_VarInP1),
    m_InP2In("InP2", m_InP2),
    m_OutP1Out("OutP1", m_OutP1),
    m_VarOutP2Out("OutP2", m_VarOutP2)
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
  addInPort("InP1", m_VarInP1In);
  addInPort("InP2", m_InP2In);
  // Set OutPort buffer
  addOutPort("OutP1", m_OutP1Out);
  addOutPort("OutP2", m_VarOutP2Out);
  // Set service provider to Ports
  // Set service consumers to Ports
  // Set CORBA Service Ports
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
