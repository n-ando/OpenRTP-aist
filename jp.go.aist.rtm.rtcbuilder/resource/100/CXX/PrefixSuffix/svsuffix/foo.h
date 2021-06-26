// -*- C++ -*-
// <rtc-template block="description">
/*! \mainpage
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
  /*!
   *   * - Name:  int_param0
   * - DefaultValue: 0
   */
  int p_int_param0_s;
  // </rtc-template>
  // DataInPort declaration
  // <rtc-template block="inport_declare">
  RTC::TimedShort p_dtInP1ds_s;
  /*!
   */
  RTC::InPort<RTC::TimedShort> p_dtInP1Inds_s;
  RTC::TimedLong p_dtInP2ds_s;
  /*!
   */
  RTC::InPort<RTC::TimedLong> p_dtInP2Inds_s;
  // </rtc-template>
  // DataOutPort declaration
  // <rtc-template block="outport_declare">
  RTC::TimedOctet p_dtOutP1ds_s;
  /*!
   */
  RTC::OutPort<RTC::TimedOctet> p_dtOutP1Outds_s;
  RTC::TimedFloat p_dtOutP2ds_s;
  /*!
   */
  RTC::OutPort<RTC::TimedFloat> p_dtOutP2Outds_s;
  // </rtc-template>
  // CORBA Port declaration
  // <rtc-template block="corbaport_declare">
  /*!
   */
  RTC::CorbaPort p_spsvPortPortss_s;
  /*!
   */
  RTC::CorbaPort p_spcmPortPortss_s;
  // </rtc-template>
  // Service declaration
  // <rtc-template block="service_declare">
  /*!
   */
  MyServiceSVC_impl p_acc_s;
  // </rtc-template>
  // Consumer declaration
  // <rtc-template block="consumer_declare">
  /*!
   */
  RTC::CorbaConsumer<DAQService> p_rate_s;
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
