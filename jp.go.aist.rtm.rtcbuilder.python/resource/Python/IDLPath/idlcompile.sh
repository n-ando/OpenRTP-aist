#!/bin/sh
IDL_PATH=`rtm-config --rtm-idldir`
omniidl -bpython -I$IDL_PATH idl/CalibrationService.idl idl/InterfaceDataTypes.idl idl/BasicDataType.idl idl/ExtendedDataTypes.idl 
