#!/usr/bin/env python
# -*- coding: utf-8 -*-
# -*- Python -*-
# <rtc-template block="description">
"""
 @file foo.py
 @brief MDesc
 @date $Date$
"""
# </rtc-template>
import sys
import time
sys.path.append(".")
# Import RTM module
import RTC
import OpenRTM_aist
import MyServiceChildMulti_idl
import MyServiceChildWithType_idl
# Import Service implementation class
# <rtc-template block="service_impl">
from MyServiceChildMulti_idl_example import *
from MyServiceChildWithType_idl_example import *
# </rtc-template>
# Import Service stub modules
# <rtc-template block="consumer_import">
import _GlobalIDL, _GlobalIDL__POA
# </rtc-template>
# This module's spesification
# <rtc-template block="module_spec">
foo_spec = ["implementation_id", "foo", 
         "type_name",         "foo", 
         "description",       "MDesc", 
         "version",           "1.0.1", 
         "vendor",            "TA", 
         "category",          "Manip", 
         "activity_type",     "STATIC2", 
         "max_instance",      "5", 
         "language",          "Python", 
         "lang_type",         "SCRIPT",
         ""]
# </rtc-template>
# <rtc-template block="component_description">
##
# @class foo
# @brief MDesc
# 
# 
# </rtc-template>
class foo(OpenRTM_aist.DataFlowComponentBase):
	
    ##
    # @brief constructor
    # @param manager Maneger Object
    # 
    def __init__(self, manager):
        OpenRTM_aist.DataFlowComponentBase.__init__(self, manager)
        """
        """
        self._MyServiceProviderPort = OpenRTM_aist.CorbaPort("MyServiceProvider")
        """
        """
        self._MyServiceRequirePort = OpenRTM_aist.CorbaPort("MyServiceRequire")
        """
        """
        self._MyServiceProvider = MyServiceChild_i()
        """
        """
        self._MyServiceProvider2 = MyServiceWithTypeChild_i()
		
        """
        """
        self._MyServiceRequire = OpenRTM_aist.CorbaConsumer(interfaceType=_GlobalIDL.MyServiceChild)
        """
        """
        self._MyServiceRequire2 = OpenRTM_aist.CorbaConsumer(interfaceType=_GlobalIDL.MyServiceWithTypeChild)
        # initialize of configuration-data.
        # <rtc-template block="init_conf_param">
		
        # </rtc-template>
		 
    ##
    #
    # The initialize action (on CREATED->ALIVE transition)
    # 
    # @return RTC::ReturnCode_t
    # 
    #
    def onInitialize(self):
        # Bind variables and configuration variable
		
        # Set InPort buffers
		
        # Set OutPort buffers
		
        # Set service provider to Ports
        self._MyServiceProviderPort.registerProvider("MyServiceProvider", "MyServiceChild", self._MyServiceProvider)
        self._MyServiceProviderPort.registerProvider("MyServiceProvider2", "MyServiceWithTypeChild", self._MyServiceProvider2)
		
        # Set service consumers to Ports
        self._MyServiceRequirePort.registerConsumer("MyServiceRequire", "MyServiceChild", self._MyServiceRequire)
        self._MyServiceRequirePort.registerConsumer("MyServiceRequire2", "MyServiceWithTypeChild", self._MyServiceRequire2)
		
        # Set CORBA Service Ports
        self.addPort(self._MyServiceProviderPort)
        self.addPort(self._MyServiceRequirePort)
		
        return RTC.RTC_OK
	
    ###
    ## 
    ## The finalize action (on ALIVE->END transition)
    ## 
    ## @return RTC::ReturnCode_t
    #
    ## 
    #def onFinalize(self):
    #
    #    return RTC.RTC_OK
	
    ###
    ##
    ## The startup action when ExecutionContext startup
    ## 
    ## @param ec_id target ExecutionContext Id
    ##
    ## @return RTC::ReturnCode_t
    ##
    ##
    #def onStartup(self, ec_id):
    #
    #    return RTC.RTC_OK
	
    ###
    ##
    ## The shutdown action when ExecutionContext stop
    ##
    ## @param ec_id target ExecutionContext Id
    ##
    ## @return RTC::ReturnCode_t
    ##
    ##
    #def onShutdown(self, ec_id):
    #
    #    return RTC.RTC_OK
	
    ###
    ##
    ## The activated action (Active state entry action)
    ##
    ## @param ec_id target ExecutionContext Id
    ## 
    ## @return RTC::ReturnCode_t
    ##
    ##
    #def onActivated(self, ec_id):
    #
    #    return RTC.RTC_OK
	
    ###
    ##
    ## The deactivated action (Active state exit action)
    ##
    ## @param ec_id target ExecutionContext Id
    ##
    ## @return RTC::ReturnCode_t
    ##
    ##
    #def onDeactivated(self, ec_id):
    #
    #    return RTC.RTC_OK
	
    ###
    ##
    ## The execution action that is invoked periodically
    ##
    ## @param ec_id target ExecutionContext Id
    ##
    ## @return RTC::ReturnCode_t
    ##
    ##
    #def onExecute(self, ec_id):
    #
    #    return RTC.RTC_OK
	
    ###
    ##
    ## The aborting action when main logic error occurred.
    ##
    ## @param ec_id target ExecutionContext Id
    ##
    ## @return RTC::ReturnCode_t
    ##
    ##
    #def onAborting(self, ec_id):
    #
    #    return RTC.RTC_OK
	
    ###
    ##
    ## The error action in ERROR state
    ##
    ## @param ec_id target ExecutionContext Id
    ##
    ## @return RTC::ReturnCode_t
    ##
    ##
    #def onError(self, ec_id):
    #
    #    return RTC.RTC_OK
	
    ###
    ##
    ## The reset action that is invoked resetting
    ##
    ## @param ec_id target ExecutionContext Id
    ##
    ## @return RTC::ReturnCode_t
    ##
    ##
    #def onReset(self, ec_id):
    #
    #    return RTC.RTC_OK
	
    ###
    ##
    ## The state update action that is invoked after onExecute() action
    ##
    ## @param ec_id target ExecutionContext Id
    ##
    ## @return RTC::ReturnCode_t
    ##
    ##
    #def onStateUpdate(self, ec_id):
    #
    #    return RTC.RTC_OK
	
    ###
    ##
    ## The action that is invoked when execution context's rate is changed
    ##
    ## @param ec_id target ExecutionContext Id
    ##
    ## @return RTC::ReturnCode_t
    ##
    ##
    #def onRateChanged(self, ec_id):
    #
    #    return RTC.RTC_OK
	
def fooInit(manager):
    profile = OpenRTM_aist.Properties(defaults_str=foo_spec)
    manager.registerFactory(profile,
                            foo,
                            OpenRTM_aist.Delete)
def MyModuleInit(manager):
    fooInit(manager)
    # create instance_name option for createComponent()
    instance_name = [i for i in sys.argv if "--instance_name=" in i]
    if instance_name:
        args = instance_name[0].replace("--", "?")
    else:
        args = ""
  
    # Create a component
    comp = manager.createComponent("foo" + args)
def main():
    # remove --instance_name= option
    argv = [i for i in sys.argv if not "--instance_name=" in i]
    # Initialize manager
    mgr = OpenRTM_aist.Manager.init(sys.argv)
    mgr.setModuleInitProc(MyModuleInit)
    mgr.activateManager()
    mgr.runManager()
if __name__ == "__main__":
    main()
