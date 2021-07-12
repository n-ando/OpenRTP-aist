// -*- C++ -*-
// <rtc-template block="description">
/*!
 * @file  MarkerPosition.cpp
 * @brief ModuleDescription
 *
 */
// </rtc-template>
#include "MarkerPosition.h"
// Module specification
// <rtc-template block="module_spec">
static const char* const markerposition_spec[] =
  {
    "implementation_id", "MarkerPosition",
    "type_name",         "MarkerPosition",
    "description",       "ModuleDescription",
    "version",           "1.0.0",
    "vendor",            "Mayuka_Shii",
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
MarkerPosition::MarkerPosition(RTC::Manager* manager)
    // <rtc-template block="initializer">
  : RTC::DataFlowComponentBase(manager),
    m_arUcoPoint2DIn("arUcoPoint2D", m_arUcoPoint2D),
    m_CenterPositionOut("CenterPosition", m_CenterPosition)
    // </rtc-template>
{
}
/*!
 * @brief destructor
 */
MarkerPosition::~MarkerPosition()
{
}
RTC::ReturnCode_t MarkerPosition::onInitialize()
{
  // Registration: InPort/OutPort/Service
  // <rtc-template block="registration">
  // Set InPort buffers
  addInPort("arUcoPoint2D", m_arUcoPoint2DIn);
  // Set OutPort buffer
  addOutPort("CenterPosition", m_CenterPositionOut);
  // Set service provider to Ports
  // Set service consumers to Ports
  // Set CORBA Service Ports
  // </rtc-template>
  // <rtc-template block="bind_config">
  // </rtc-template>
  return RTC::RTC_OK;
}
/*
RTC::ReturnCode_t MarkerPosition::onFinalize()
{
  return RTC::RTC_OK;
}
*/
/*
RTC::ReturnCode_t MarkerPosition::onStartup(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
*/
/*
RTC::ReturnCode_t MarkerPosition::onShutdown(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
*/
RTC::ReturnCode_t MarkerPosition::onActivated(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
RTC::ReturnCode_t MarkerPosition::onDeactivated(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
RTC::ReturnCode_t MarkerPosition::onExecute(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
/*
RTC::ReturnCode_t MarkerPosition::onAborting(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
*/
/*
RTC::ReturnCode_t MarkerPosition::onError(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
*/
/*
RTC::ReturnCode_t MarkerPosition::onReset(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
*/
/*
RTC::ReturnCode_t MarkerPosition::onStateUpdate(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
*/
/*
RTC::ReturnCode_t MarkerPosition::onRateChanged(RTC::UniqueId ec_id)
{
  return RTC::RTC_OK;
}
*/
extern "C"
{
  void MarkerPositionInit(RTC::Manager* manager)
  {
    coil::Properties profile(markerposition_spec);
    manager->registerFactory(profile,
                             RTC::Create<MarkerPosition>,
                             RTC::Delete<MarkerPosition>);
  }
};
