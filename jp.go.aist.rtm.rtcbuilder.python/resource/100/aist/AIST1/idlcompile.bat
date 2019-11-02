@echo off
cd /d %~dp0
setlocal
for %%I in (python.exe) do if exist %%~$path:I set f=%%~$path:I
if exist %f% (
  @echo off
  omniidl.exe -bpython -I"%RTM_ROOT%\rtm\idl" idl/MyService.idl 
) else (
  echo "python.exe" can not be found.
  echo Please modify PATH environmental variable for python command.
)
endlocal
