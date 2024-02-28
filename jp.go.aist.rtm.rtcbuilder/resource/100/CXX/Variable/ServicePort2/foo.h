// -*- C++ -*-
// <rtc-template block="description">
/*!
 * @file  foo.h
 * @brief MDesc
 *
 */
// </rtc-template>
#ifndef FOO_H
#define FOO_H
#include <rtm/idl/BasicDataTypeSkel.h>
#include <rtm/idl/ExtendedDataTypesSkel.h>
#include <rtm/idl/InterfaceDataTypesSkel.h>
// Service implementation headers
// <rtc-template block="service_impl_h">
#include "MyServiceSVC_impl.h"
// </rtc-template>
// Service Consumer stub headers
// <rtc-template block="consumer_stub_h">
#include "DAQServiceStub.h"
// </rtc-template>
#include <rtm/Manager.h>
#include <rtm/DataFlowComponentBase.h>
#include <rtm/CorbaPort.h>
#include <rtm/DataInPort.h>
#include <rtm/DataOutPort.h>
// <rtc-template block="component_description">
/*!
 * @class foo
 * @brief MDesc
 *
 */
// </rtc-template>
class foo
  : public RTC::DataFlowComponentBase
{
 public:
  /*!
   * @brief constructor
   * @param manager Maneger Object
   */
  foo(RTC::Manager* manager);
  /*!
   * @brief destructor
   */
  ~foo() override;
  // <rtc-template block="public_attribute">
  // </rtc-template>
  // <rtc-template block="public_operation">
  // </rtc-template>
  // <rtc-template block="activity">
  /***
   *
   * The initialize action (on CREATED->ALIVE transition)
   *
   * @return RTC::ReturnCode_t
   *   *   */
   RTC::ReturnCode_t onInitialize() override;
  /***
   *
   * The finalize action (on ALIVE->END transition)
   *
   * @return RTC::ReturnCode_t
   *   *   */
  // RTC::ReturnCode_t onFinalize() override;
  /***
   *
   * The startup action when ExecutionContext startup
   *
   * @param ec_id target ExecutionContext Id
   *
   * @return RTC::ReturnCode_t
   *   *   */
  // RTC::ReturnCode_t onStartup(RTC::UniqueId ec_id) override;
  /***
   *
   * The shutdown action when ExecutionContext stop
   *
   * @param ec_id target ExecutionContext Id
   *
   * @return RTC::ReturnCode_t
   *   *   */
  // RTC::ReturnCode_t onShutdown(RTC::UniqueId ec_id) override;
  /***
   *
   * The activated action (Active state entry action)
   *
   * @param ec_id target ExecutionContext Id
   *
   * @return RTC::ReturnCode_t
   *   *   */
  // RTC::ReturnCode_t onActivated(RTC::UniqueId ec_id) override;
  /***
   *
   * The deactivated action (Active state exit action)
   *
   * @param ec_id target ExecutionContext Id
   *
   * @return RTC::ReturnCode_t
   *   *   */
  // RTC::ReturnCode_t onDeactivated(RTC::UniqueId ec_id) override;
  /***
   *
   * The execution action that is invoked periodically
   *
   * @param ec_id target ExecutionContext Id
   *
   * @return RTC::ReturnCode_t
   *   *   */
  // RTC::ReturnCode_t onExecute(RTC::UniqueId ec_id) override;
  /***
   *
   * The aborting action when main logic error occurred.
   *
   * @param ec_id target ExecutionContext Id
   *
   * @return RTC::ReturnCode_t
   *   *   */
  // RTC::ReturnCode_t onAborting(RTC::UniqueId ec_id) override;
  /***
   *
   * The error action in ERROR state
   *
   * @param ec_id target ExecutionContext Id
   *
   * @return RTC::ReturnCode_t
   *   *   */
  // RTC::ReturnCode_t onError(RTC::UniqueId ec_id) override;
  /***
   *
   * The reset action that is invoked resetting
   *
   * @param ec_id target ExecutionContext Id
   *
   * @return RTC::ReturnCode_t
   *   *   */
  // RTC::ReturnCode_t onReset(RTC::UniqueId ec_id) override;
  /***
   *
   * The state update action that is invoked after onExecute() action
   *
   * @param ec_id target ExecutionContext Id
   *
   * @return RTC::ReturnCode_t
   *   *   */
  // RTC::ReturnCode_t onStateUpdate(RTC::UniqueId ec_id) override;
  /***
   *
   * The action that is invoked when execution context's rate is changed
   *
   * @param ec_id target ExecutionContext Id
   *
   * @return RTC::ReturnCode_t
   *   *   */
  // RTC::ReturnCode_t onRateChanged(RTC::UniqueId ec_id) override;
  // </rtc-template>
 protected:
  // <rtc-template block="protected_attribute">
  // </rtc-template>
  // <rtc-template block="protected_operation">
  // </rtc-template>
  // Configuration variable declaration
  // <rtc-template block="config_declare">
  // </rtc-template>
  // DataInPort declaration
  // <rtc-template block="inport_declare">
  RTC::TimedShort m_InP1;
  /*!
   */
  RTC::InPort<RTC::TimedShort> m_InP1In;
  RTC::TimedLong m_InP2;
  /*!
   */
  RTC::InPort<RTC::TimedLong> m_InP2In;
  // </rtc-template>
  // DataOutPort declaration
  // <rtc-template block="outport_declare">
  RTC::TimedLong m_OutP1;
  /*!
   */
  RTC::OutPort<RTC::TimedLong> m_OutP1Out;
  RTC::TimedFloat m_OutP2;
  /*!
   */
  RTC::OutPort<RTC::TimedFloat> m_OutP2Out;
  // </rtc-template>
  // CORBA Port declaration
  // <rtc-template block="corbaport_declare">
  /*!
   */
  RTC::CorbaPort m_svPortPort;
  /*!
   */
  RTC::CorbaPort m_cmPortPort;
  // </rtc-template>
  // Service declaration
  // <rtc-template block="service_declare">
  /*!
   */
  MyServiceSVC_impl m_acvaria;
  // </rtc-template>
  // Consumer declaration
  // <rtc-template block="consumer_declare">
  /*!
   */
  RTC::CorbaConsumer<DAQService> m_rvaria;
  // </rtc-template>
 private:
  // <rtc-template block="private_attribute">
  // </rtc-template>
  // <rtc-template block="private_operation">
  // </rtc-template>
};
extern "C"
{
  DLL_EXPORT void fooInit(RTC::Manager* manager);
};
#endif // FOO_H
