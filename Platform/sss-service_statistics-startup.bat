@CLS

@D:
@CD D:\ZW-Platform.WK

@SET JDK_PATH=C:\Program Files\Java\jdk1.7.0_25

@SET MO_PATH=D:\ZW-Platform.WK

@SET MO_CLASS_PATH=%MO_PATH%\library\jdom-2.0.2.jar;
@SET MO_CLASS_PATH=%MO_CLASS_PATH%%MO_PATH%\library\mysql-connector-java-5.1.24-bin.jar;

@SET MO_CLASS_PATH=%MO_CLASS_PATH%%MO_PATH%\mo-1-common\bin;
@SET MO_CLASS_PATH=%MO_CLASS_PATH%%MO_PATH%\mo-2-core\bin;
@SET MO_CLASS_PATH=%MO_CLASS_PATH%%MO_PATH%\mo-3-logic\bin;
@SET MO_CLASS_PATH=%MO_CLASS_PATH%%MO_PATH%\mo-zq-logic\bin;
@SET MO_CLASS_PATH=%MO_CLASS_PATH%%MO_PATH%\mp-service\bin;

"%JDK_PATH%\bin\java.exe" -cp %MO_CLASS_PATH% com.zqinet.app.analysis.RStartup -home %MO_PATH%/mp-service/src/config -config application-statistics-local.xml -logger info
