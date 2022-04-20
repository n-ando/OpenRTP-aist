// -*- C++ -*-
// <rtc-template block="description">
/*!
 * @file  ModuleNameTest.cpp
 * @brief ModuleDescription (test code)
 *
 */
// </rtc-template>
#include "ModuleNameTest.h"
// Module specification
// <rtc-template block="module_spec">
#if RTM_MAJOR_VERSION >= 2
static const char* const modulename_spec[] =
#else
static const char* modulename_spec[] =
#endif
  {
    "implementation_id", "ModuleNameTest",
    "type_name",         "ModuleNameTest",
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
ModuleNameTest::ModuleNameTest(RTC::Manager* manager)
    // <rtc-template block="initializer">
  : RTC::DataFlowComponentBase(manager)
,
    m_Event01_02Out("Event01_02", m_Event01_02),
    m_Event02_FinalOut("Event02_Final", m_Event02_Final)
    // </rtc-template>
{
}
/*!
 * @brief destructor
 */
ModuleNameTest::~ModuleNameTest()
{
}
RTC::ReturnCode_t ModuleNameTest::onInitialize()
{
  // Registration: InPort/OutPort/Service
  // <rtc-template block="registration">
  // Set InPort buffers
  // Set OutPort buffer
  addOutPort("Event01_02", m_Event01_02Out);
  addOutPort("Event02_Final", m_Event02_FinalOut);
  // Set service provider to Ports
  // Set service consumers to Ports
  // Set CORBA Service Ports
  // </rtc-template>
  // <rtc-template block="bind_config">
  // </rtc-template>
  return RTC::RTC_OK;
}
/*
RTC::ReturnCode_t ModuleNameTest::onFinalize()
{
  return RTC::RTC_OK;
}
*/
//RTC::ReturnCode_t ModuleNameTest::onStartup(RTC::UniqueId /*ec_id*/)
//{
//  return RTC::RTC_OK;
//}
//RTC::ReturnCode_t ModuleNameTest::onShutdown(RTC::UniqueId /*ec_id*/)
//{
//  return RTC::RTC_OK;
//}
//RTC::ReturnCode_t ModuleNameTest::onActivated(RTC::UniqueId /*ec_id*/)
//{
//  return RTC::RTC_OK;
//}
//RTC::ReturnCode_t ModuleNameTest::onDeactivated(RTC::UniqueId /*ec_id*/)
//{
//  return RTC::RTC_OK;
//}
//RTC::ReturnCode_t ModuleNameTest::onExecute(RTC::UniqueId /*ec_id*/)
//{
//  return RTC::RTC_OK;
//}
//RTC::ReturnCode_t ModuleNameTest::onAborting(RTC::UniqueId /*ec_id*/)
//{
//  return RTC::RTC_OK;
//}
//RTC::ReturnCode_t ModuleNameTest::onError(RTC::UniqueId /*ec_id*/)
//{
//  return RTC::RTC_OK;
//}
//RTC::ReturnCode_t ModuleNameTest::onReset(RTC::UniqueId /*ec_id*/)
//{
//  return RTC::RTC_OK;
//}
//RTC::ReturnCode_t ModuleNameTest::onStateUpdate(RTC::UniqueId /*ec_id*/)
//{
//  return RTC::RTC_OK;
//}
//RTC::ReturnCode_t ModuleNameTest::onRateChanged(RTC::UniqueId /*ec_id*/)
//{
//  return RTC::RTC_OK;
//}
bool ModuleNameTest::runTest()
{
    return true;
}
extern "C"
{
  void ModuleNameTestInit(RTC::Manager* manager)
  {
    coil::Properties profile(modulename_spec);
    manager->registerFactory(profile,
                             RTC::Create<ModuleNameTest>,
                             RTC::Delete<ModuleNameTest>);
  }
}
