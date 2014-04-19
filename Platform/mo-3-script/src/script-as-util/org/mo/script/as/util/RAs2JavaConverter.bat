SET     JAVA_HOME=C:\Program Files\Java\jdk1.7.0_06
SET PLATFORM_HOME=D:\WP-Platform

"%JAVA_HOME%\bin\java.exe" -cp "%PLATFORM_HOME%\mo-1-common\library\jdom-2.0.2.jar;%PLATFORM_HOME%\mo-1-common\bin;%PLATFORM_HOME%\mo-2-core\bin;%PLATFORM_HOME%\mo-3-script\bin;" org.mo.script.as.util.RAs2JavaConverter
