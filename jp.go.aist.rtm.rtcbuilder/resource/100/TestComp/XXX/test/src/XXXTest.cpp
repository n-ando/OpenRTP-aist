// -*- C++ -*-
// <rtc-template block="description">
/*!
 * @file  XXXTest.cpp
 * @brief ModuleDescription (test code)
 *
 */
// </rtc-template>
#include "XXXTest.h"
// Module specification
// <rtc-template block="module_spec">
static const char* const xxx_spec[] =
  {
    "implementation_id", "XXXTest",
    "type_name",         "XXXTest",
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
XXXTest::XXXTest(RTC::Manager* manager)
    // <rtc-template block="initializer">
  : RTC::DataFlowComponentBase(manager),
    m_inOut("in", m_in),
    m_outIn("out", m_out),
    m_MyServidePort("MyServide")
    // </rtc-template>
{
}
/*!
 * @brief destructor
 */
XXXTest::~XXXTest()
{
}
RTC::ReturnCode_t XXXTest::onInitialize()
{
  // Registration: InPort/OutPort/Service
  // <rtc-template block="registration">
  // Set InPort buffers
  addInPort("out", m_outIn);
  // Set OutPort buffer
  addOutPort("in", m_inOut);
  // Set service provider to Ports
  // Set service consumers to Ports
  m_MyServidePort.registerConsumer("myservice0", "SimpleService::MyService", m_myservice0);
  // Set CORBA Service Ports
  addPort(m_MyServidePort);
  // </rtc-template>
  // <rtc-template block="bind_config">
  // </rtc-template>
  return RTC::RTC_OK;
}
/*
RTC::ReturnCode_t XXXTest::onFinalize()
{
  return RTC::RTC_OK;
}
*/
/*
RTC::ReturnCode_t XXXTest::onStartup(RTC::UniqueId /*ec_id*/)
{
  return RTC::RTC_OK;
}
*/
/*
RTC::ReturnCode_t XXXTest::onShutdown(RTC::UniqueId /*ec_id*/)
{
  return RTC::RTC_OK;
}
*/
RTC::ReturnCode_t XXXTest::onActivated(RTC::UniqueId /*ec_id*/)
{
  return RTC::RTC_OK;
}
RTC::ReturnCode_t XXXTest::onDeactivated(RTC::UniqueId /*ec_id*/)
{
  return RTC::RTC_OK;
}
RTC::ReturnCode_t XXXTest::onExecute(RTC::UniqueId /*ec_id*/)
{
  return RTC::RTC_OK;
}
/*
RTC::ReturnCode_t XXXTest::onAborting(RTC::UniqueId /*ec_id*/)
{
  return RTC::RTC_OK;
}
*/
/*
RTC::ReturnCode_t XXXTest::onError(RTC::UniqueId /*ec_id*/)
{
  return RTC::RTC_OK;
}
*/
/*
RTC::ReturnCode_t XXXTest::onReset(RTC::UniqueId /*ec_id*/)
{
  return RTC::RTC_OK;
}
*/
/*
RTC::ReturnCode_t XXXTest::onStateUpdate(RTC::UniqueId /*ec_id*/)
{
  return RTC::RTC_OK;
}
*/
/*
RTC::ReturnCode_t XXXTest::onRateChanged(RTC::UniqueId /*ec_id*/)
{
  return RTC::RTC_OK;
}
*/
bool XXXTest::runTest()
{
    return true;
}
extern "C"
{
  void XXXTestInit(RTC::Manager* manager)
  {
    coil::Properties profile(xxx_spec);
    manager->registerFactory(profile,
                             RTC::Create<XXXTest>,
                             RTC::Delete<XXXTest>);
  }
}
