// -*- C++ -*-
/*!
 * @file  foo.cpp
 * @brief MDesc
 * @date $Date$
 *
 * @author Noriaki Ando <n-ando@aist.go.jp> one two three four
 * five six seven eight nine ten
 *
 * Copyright (C) 2006-2008
 * ライセンス12345678901234567890123456789012345678901234567890
 *
 * $Id$
 */

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
    "conf.default.int_param1", "1",
    "conf.default.double_param0", "0.11",
    "conf.default.str_param0", "hoge",
    "conf.default.str_param1", "dara",

    // Widget
    // Constraints

    "conf.__type__.int_param0", "int",
    "conf.__type__.int_param1", "int",
    "conf.__type__.double_param0", "double",
    "conf.__type__.str_param0", "std::string",
    "conf.__type__.str_param1", "std::string",

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
    m_InName1In("InP1", m_InName1),
    m_InNm2In("InP2", m_InNm2),
    m_OutName1Out("OutP1", m_OutName1),
    m_OutNme2Out("OutP2", m_OutNme2),
    m_svPortPort("svPort"),
    m_cmPortPort("cmPort")
    // </rtc-template>
{
}

/*!
 * @brief destructor
 */
foo::~foo()
{
}


/*!
 * on_initialize概要説明123456789012345678901234567890123456789012
 * 3456789012345678901234567890
 */
RTC::ReturnCode_t foo::onInitialize()
{
  // Registration: InPort/OutPort/Service
  // <rtc-template block="registration">
  // Set InPort buffers
  addInPort("InP1", m_InName1In);
  addInPort("InP2", m_InNm2In);
  
  // Set OutPort buffer
  addOutPort("OutP1", m_OutName1Out);
  addOutPort("OutP2", m_OutNme2Out);

  
  // Set service provider to Ports
  m_svPortPort.registerProvider("acc", "MyService", m_acc);
  
  // Set service consumers to Ports
  m_cmPortPort.registerConsumer("rate", "DAQService", m_rate);
  
  // Set CORBA Service Ports
  addPort(m_svPortPort);
  addPort(m_cmPortPort);
  
  // </rtc-template>

  // <rtc-template block="bind_config">
  // Bind variables and configuration variable
  bindParameter("int_param0", m_int_param0, "0");
  bindParameter("int_param1", m_int_param1, "1");
  bindParameter("double_param0", m_double_param0, "0.11");
  bindParameter("str_param0", m_str_param0, "hoge");
  bindParameter("str_param1", m_str_param1, "dara");
  // </rtc-template>

  
  return RTC::RTC_OK;
}

/*!
 * on_finalize概要説明12345678901234567890123456789012345678901234
 * 56789012345678901234567890
 */
/*
RTC::ReturnCode_t foo::onFinalize()
{
  return RTC::RTC_OK;
}
*/

/*!
 * on_startup概要説明123456789012345678901234567890123456789012345
 * 6789012345678901234567890
 */
/*
RTC::ReturnCode_t foo::onStartup(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
*/

/*!
 * on_shutdown概要説明12345678901234567890123456789012345678901234
 * 56789012345678901234567890
 */
/*
RTC::ReturnCode_t foo::onShutdown(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
*/

/*!
 * on_activated概要説明1234567890123456789012345678901234567890123
 * 456789012345678901234567890
 */
/*
RTC::ReturnCode_t foo::onActivated(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
*/

/*!
 * on_deactivated概要説明12345678901234567890123456789012345678901
 * 23456789012345678901234567890
 */
/*
RTC::ReturnCode_t foo::onDeactivated(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
*/

/*!
 * on_execute概要説明123456789012345678901234567890123456789012345
 * 6789012345678901234567890
 */
/*
RTC::ReturnCode_t foo::onExecute(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
*/

/*!
 * on_aborting概要説明12345678901234567890123456789012345678901234
 * 56789012345678901234567890
 */
/*
RTC::ReturnCode_t foo::onAborting(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
*/

/*!
 * on_error概要説明12345678901234567890123456789012345678901234567
 * 89012345678901234567890
 */
/*
RTC::ReturnCode_t foo::onError(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
*/

/*!
 * on_reset概要説明12345678901234567890123456789012345678901234567
 * 89012345678901234567890
 */
/*
RTC::ReturnCode_t foo::onReset(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
*/

/*!
 * on_state_update概要説明1234567890123456789012345678901234567890
 * 123456789012345678901234567890
 */
/*
RTC::ReturnCode_t foo::onStateUpdate(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
*/

/*!
 * on_rate_changed概要説明1234567890123456789012345678901234567890
 * 123456789012345678901234567890
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


