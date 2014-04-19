@CLS

@D:
@CD D:\ZW-Platform.WK

@SET JDK_PATH=C:\Program Files\Java\jdk1.7.0_25

@SET MO_PATH=D:\ZW-Platform.WK
@SET MO_LIBRARY=%MO_PATH%\library

@SET MO_CLASS=%MO_LIBRARY%\jdom-2.0.2.jar;
@SET MO_CLASS=%MO_CLASS%%MO_LIBRARY%\mysql-connector-java-5.1.24-bin.jar;

@SET MO_CLASS=%MO_CLASS%%MO_LIBRARY%\mo-common.jar;
@SET MO_CLASS=%MO_CLASS%%MO_LIBRARY%\mo-core.jar;
@SET MO_CLASS=%MO_CLASS%%MO_LIBRARY%\mo-logic.jar;
@SET MO_CLASS=%MO_CLASS%%MO_LIBRARY%\mo-web.jar;
@SET MO_CLASS=%MO_CLASS%%MO_LIBRARY%\mo-zq-logic.jar;

@SET MO_CLASS=%MO_CLASS%%MO_PATH%\mp-service\bin;

"%JDK_PATH%\bin\java.exe" -cp "%MO_CLASS%" com.zqinet.app.builder.RStartup -home "%MO_PATH%\mp-service\src\config" -config application-service-local.xml -logger info
