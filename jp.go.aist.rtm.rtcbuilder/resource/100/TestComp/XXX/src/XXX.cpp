// -*- C++ -*-
// <rtc-template block="description">
/*!
 * @file  XXX.cpp
 * @brief ModuleDescription
 *
 */
// </rtc-template>
#include "XXX.h"
// Module specification
// <rtc-template block="module_spec">
static const char* const xxx_spec[] =
  {
    "implementation_id", "XXX",
    "type_name",         "XXX",
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
XXX::XXX(RTC::Manager* manager)
    // <rtc-template block="initializer">
  : RTC::DataFlowComponentBase(manager),
    m_inIn("in", m_in),
    m_outOut("out", m_out),
    m_MyServidePort("MyServide")
    // </rtc-template>
{
}
/*!
 * @brief destructor
 */
XXX::~XXX()
{
}
RTC::ReturnCode_t XXX::onInitialize()
{
  // Registration: InPort/OutPort/Service
  // <rtc-template block="registration">
  // Set InPort buffers
  addInPort("in", m_inIn);
  // Set OutPort buffer
  addOutPort("out", m_outOut);
  // Set service provider to Ports
  m_MyServidePort.registerProvider("myservice0", "SimpleService::MyService", m_myservice0);
  // Set service consumers to Ports
  // Set CORBA Service Ports
  addPort(m_MyServidePort);
  // </rtc-template>
  // <rtc-template block="bind_config">
  // </rtc-template>
  return RTC::RTC_OK;
}
/*
RTC::ReturnCode_t XXX::onFinalize()
{
  return RTC::RTC_OK;
}
*/
//RTC::ReturnCode_t XXX::onStartup(RTC::UniqueId /*ec_id*/)
//{
//  return RTC::RTC_OK;
//}
//RTC::ReturnCode_t XXX::onShutdown(RTC::UniqueId /*ec_id*/)
//{
//  return RTC::RTC_OK;
//}
RTC::ReturnCode_t XXX::onActivated(RTC::UniqueId /*ec_id*/)
{
  return RTC::RTC_OK;
}
RTC::ReturnCode_t XXX::onDeactivated(RTC::UniqueId /*ec_id*/)
{
  return RTC::RTC_OK;
}
RTC::ReturnCode_t XXX::onExecute(RTC::UniqueId /*ec_id*/)
{
  return RTC::RTC_OK;
}
//RTC::ReturnCode_t XXX::onAborting(RTC::UniqueId /*ec_id*/)
//{
//  return RTC::RTC_OK;
//}
//RTC::ReturnCode_t XXX::onError(RTC::UniqueId /*ec_id*/)
//{
//  return RTC::RTC_OK;
//}
//RTC::ReturnCode_t XXX::onReset(RTC::UniqueId /*ec_id*/)
//{
//  return RTC::RTC_OK;
//}
//RTC::ReturnCode_t XXX::onStateUpdate(RTC::UniqueId /*ec_id*/)
//{
//  return RTC::RTC_OK;
//}
//RTC::ReturnCode_t XXX::onRateChanged(RTC::UniqueId /*ec_id*/)
//{
//  return RTC::RTC_OK;
//}
extern "C"
{
  void XXXInit(RTC::Manager* manager)
  {
    coil::Properties profile(xxx_spec);
    manager->registerFactory(profile,
                             RTC::Create<XXX>,
                             RTC::Delete<XXX>);
  }
}
