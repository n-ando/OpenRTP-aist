@echo off
omniidl.exe -bpython -I"%RTM_ROOT%\rtm\idl" idl/CalibrationService.idl idl/InterfaceDataTypes.idl idl/BasicDataType.idl idl/ExtendedDataTypes.idl 
