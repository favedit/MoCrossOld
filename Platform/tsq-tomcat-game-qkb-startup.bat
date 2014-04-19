@CLS

@D:
@CD D:\ZW-Platform.WK

@SET JAVA_HOME=C:\Program Files\Java\jdk1.7.0_25

@SET CATALINA_OPTS=-Xms256m -Xmx512m
@SET CATALINA_HOME=D:\ZW-Platform.WK\mt-server\tomcat-window-game-qkb-7.0.42
@SET MOBJ_MODE=Logic
@SET MOBJ_JS=Test
@SET MOBJ_CONFIG=application-local
@SET JAVA_OPTS=-Duser.mobj.mode=%MOBJ_MODE% -Duser.mobj.js=%MOBJ_JS% -Duser.mobj.config=%MOBJ_CONFIG%

@"%CATALINA_HOME%\bin\startup.bat"
