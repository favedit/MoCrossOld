SET JDK_PATH=C:\Program Files\Java\jdk1.7.0_21
SET PRJ_HOME=D:\WP-Platform.DP

D:
cd %PRJ_HOME%

"%JDK_PATH%\bin\jar" cvfm mo-common.jar -C %PRJ_HOME%/mo-1-common/bin -C %PRJ_HOME%/mo-1-common/lib