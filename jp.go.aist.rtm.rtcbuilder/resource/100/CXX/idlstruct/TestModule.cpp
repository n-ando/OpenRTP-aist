// -*- C++ -*-
// <rtc-template block="description">
/*!
 * @file  TestModule.cpp
 * @brief ModuleDescription
 *
 */
// </rtc-template>
#include "TestModule.h"
// Module specification
// <rtc-template block="module_spec">
#if RTM_MAJOR_VERSION >= 2
static const char* const testmodule_spec[] =
#else
static const char* testmodule_spec[] =
#endif
  {
    "implementation_id", "TestModule",
    "type_name",         "TestModule",
    "description",       "ModuleDescription",
    "version",           "1.0.0",
    "vendor",            "ysuga_net",
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
TestModule::TestModule(RTC::Manager* manager)
    // <rtc-template block="initializer">
  : RTC::DataFlowComponentBase(manager),
    m_svcPortPort("svcPort")
    // </rtc-template>
{
}
/*!
 * @brief destructor
 */
TestModule::~TestModule()
{
}
RTC::ReturnCode_t TestModule::onInitialize()
{
  // Registration: InPort/OutPort/Service
  // <rtc-template block="registration">
  // Set InPort buffers
  // Set OutPort buffer
  // Set service provider to Ports
  m_svcPortPort.registerProvider("inf0", "ysuga_net::Hoge", m_inf0);
  // Set service consumers to Ports
  // Set CORBA Service Ports
  addPort(m_svcPortPort);
  // </rtc-template>
  // <rtc-template block="bind_config">
  // </rtc-template>
  return RTC::RTC_OK;
}
/*
RTC::ReturnCode_t TestModule::onFinalize()
{
  return RTC::RTC_OK;
}
*/
//RTC::ReturnCode_t TestModule::onStartup(RTC::UniqueId /*ec_id*/)
//{
//  return RTC::RTC_OK;
//}
//RTC::ReturnCode_t TestModule::onShutdown(RTC::UniqueId /*ec_id*/)
//{
//  return RTC::RTC_OK;
//}
//RTC::ReturnCode_t TestModule::onActivated(RTC::UniqueId /*ec_id*/)
//{
//  return RTC::RTC_OK;
//}
//RTC::ReturnCode_t TestModule::onDeactivated(RTC::UniqueId /*ec_id*/)
//{
//  return RTC::RTC_OK;
//}
//RTC::ReturnCode_t TestModule::onExecute(RTC::UniqueId /*ec_id*/)
//{
//  return RTC::RTC_OK;
//}
//RTC::ReturnCode_t TestModule::onAborting(RTC::UniqueId /*ec_id*/)
//{
//  return RTC::RTC_OK;
//}
//RTC::ReturnCode_t TestModule::onError(RTC::UniqueId /*ec_id*/)
//{
//  return RTC::RTC_OK;
//}
//RTC::ReturnCode_t TestModule::onReset(RTC::UniqueId /*ec_id*/)
//{
//  return RTC::RTC_OK;
//}
//RTC::ReturnCode_t TestModule::onStateUpdate(RTC::UniqueId /*ec_id*/)
//{
//  return RTC::RTC_OK;
//}
//RTC::ReturnCode_t TestModule::onRateChanged(RTC::UniqueId /*ec_id*/)
//{
//  return RTC::RTC_OK;
//}
extern "C"
{
  void TestModuleInit(RTC::Manager* manager)
  {
    coil::Properties profile(testmodule_spec);
    manager->registerFactory(profile,
                             RTC::Create<TestModule>,
                             RTC::Delete<TestModule>);
  }
}
