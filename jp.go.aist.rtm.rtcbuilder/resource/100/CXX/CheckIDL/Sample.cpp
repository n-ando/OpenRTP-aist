// -*- C++ -*-
// <rtc-template block="description">
/*!
 * @file  Sample.cpp
 * @brief Sample Comp
 *
 */
// </rtc-template>
#include "Sample.h"
// Module specification
// <rtc-template block="module_spec">
static const char* const sample_spec[] =
  {
    "implementation_id", "Sample",
    "type_name",         "Sample",
    "description",       "Sample Comp",
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
Sample::Sample(RTC::Manager* manager)
    // <rtc-template block="initializer">
  : RTC::DataFlowComponentBase(manager),
    m_SamplePort("Sample")
    // </rtc-template>
{
}
/*!
 * @brief destructor
 */
Sample::~Sample()
{
}
RTC::ReturnCode_t Sample::onInitialize()
{
  // Registration: InPort/OutPort/Service
  // <rtc-template block="registration">
  // Set InPort buffers
  // Set OutPort buffer
  // Set service provider to Ports
  m_SamplePort.registerProvider("SampleIF", "MyModule::MyInterface", m_SampleIF);
  // Set service consumers to Ports
  // Set CORBA Service Ports
  addPort(m_SamplePort);
  // </rtc-template>
  // <rtc-template block="bind_config">
  // </rtc-template>
  return RTC::RTC_OK;
}
/*
RTC::ReturnCode_t Sample::onFinalize()
{
  return RTC::RTC_OK;
}
*/
/*
RTC::ReturnCode_t Sample::onStartup(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
*/
/*
RTC::ReturnCode_t Sample::onShutdown(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
*/
RTC::ReturnCode_t Sample::onActivated(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
RTC::ReturnCode_t Sample::onDeactivated(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
RTC::ReturnCode_t Sample::onExecute(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
/*
RTC::ReturnCode_t Sample::onAborting(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
*/
/*
RTC::ReturnCode_t Sample::onError(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
*/
/*
RTC::ReturnCode_t Sample::onReset(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
*/
/*
RTC::ReturnCode_t Sample::onStateUpdate(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
*/
/*
RTC::ReturnCode_t Sample::onRateChanged(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
*/
extern "C"
{
  void SampleInit(RTC::Manager* manager)
  {
    coil::Properties profile(sample_spec);
    manager->registerFactory(profile,
                             RTC::Create<Sample>,
                             RTC::Delete<Sample>);
  }
};
