:::::::::::::::::::::::::::::::×Ô¶¯´¥·¢UAC::::::::::::::::::::::::::::::
@pushd "%temp%"
@echo.>%systemroot%\testfile.tmp
@if exist %systemroot%\testfile.tmp goto StartWithAdmin
@echo Set UAC = CreateObject^("Shell.Application"^)>getadm.vbs
@echo UAC.ShellExecute "%~0", "%*", "", "runas", 1 >>getadm.vbs
@getadm.vbs
@goto :eof
:StartWithAdmin
@del %systemroot%\testfile.tmp
@if exist getadm.vbs del getadm.vbs
@pushd "%~dp0"
:::::::::::::::::::::::::::::auto touch UAC:::::::::::::::::::::::::::::

@echo off 
move /y "hosts" "C:/WINDOWS/system32/drivers/etc/hosts" 
del %0