#!/bin/sh
RTM_CONFIG="rtm2-config"
if type "rtm-config" > /dev/null 2>&1; then
    RTM_CONFIG_CMD="rtm-config"
fi
if type "rtm-config2" > /dev/null 2>&1; then
    RTM_CONFIG_CMD="rtm-config2"
fi
if type "rtm2-config" > /dev/null 2>&1; then
    RTM_CONFIG_CMD="rtm2-config"
fi
if test "x$RTM_CONFIG_CMD" = "x" ; then
    echo "rtm-config/rtm-config2/rtm2-config command not found."
    exit 1
fi
IDL_PATH=`$RTM_CONFIG_CMD --rtm-idldir`
omniidl -bpython -I$IDL_PATH idl/CalibrationService.idl idl/InterfaceDataTypes.idl idl/BasicDataType.idl idl/ExtendedDataTypes.idl 
