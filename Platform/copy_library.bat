SET HOME=D:\ZW-Platform.WK
SET SOURCE_HOME=%HOME%\library

SET TARGET_HOME=%HOME%\mp-platform\webroot\WEB-INF\lib
COPY /Y %SOURCE_HOME%\mo-common.jar     %TARGET_HOME%\mo-common.jar
COPY /Y %SOURCE_HOME%\mo-core.jar       %TARGET_HOME%\mo-core.jar
COPY /Y %SOURCE_HOME%\mo-logic.jar      %TARGET_HOME%\mo-logic.jar
COPY /Y %SOURCE_HOME%\mo-web.jar        %TARGET_HOME%\mo-web.jar
COPY /Y %SOURCE_HOME%\mo-gm-develop.jar %TARGET_HOME%\mo-gm-develop.jar
COPY /Y %SOURCE_HOME%\mo-zq-game.jar    %TARGET_HOME%\mo-zq-game.jar
COPY /Y %SOURCE_HOME%\mo-zq-logic.jar   %TARGET_HOME%\mo-zq-logic.jar

SET TARGET_HOME=%HOME%\mp-game-cczdy\webroot\WEB-INF\lib
COPY /Y %SOURCE_HOME%\mo-common.jar     %TARGET_HOME%\mo-common.jar
COPY /Y %SOURCE_HOME%\mo-core.jar       %TARGET_HOME%\mo-core.jar
COPY /Y %SOURCE_HOME%\mo-logic.jar      %TARGET_HOME%\mo-logic.jar
COPY /Y %SOURCE_HOME%\mo-web.jar        %TARGET_HOME%\mo-web.jar

SET TARGET_HOME=%HOME%\mp-game-tz\webroot\WEB-INF\lib
COPY /Y %SOURCE_HOME%\mo-common.jar     %TARGET_HOME%\mo-common.jar
COPY /Y %SOURCE_HOME%\mo-core.jar       %TARGET_HOME%\mo-core.jar
COPY /Y %SOURCE_HOME%\mo-logic.jar      %TARGET_HOME%\mo-logic.jar
COPY /Y %SOURCE_HOME%\mo-web.jar        %TARGET_HOME%\mo-web.jar
COPY /Y %SOURCE_HOME%\mo-gm-develop.jar %TARGET_HOME%\mo-gm-develop.jar

SET TARGET_HOME=%HOME%\mp-game-qkb\webroot\WEB-INF\lib
COPY /Y %SOURCE_HOME%\mo-common.jar     %TARGET_HOME%\mo-common.jar
COPY /Y %SOURCE_HOME%\mo-core.jar       %TARGET_HOME%\mo-core.jar
COPY /Y %SOURCE_HOME%\mo-logic.jar      %TARGET_HOME%\mo-logic.jar
COPY /Y %SOURCE_HOME%\mo-web.jar        %TARGET_HOME%\mo-web.jar
COPY /Y %SOURCE_HOME%\mo-gm-develop.jar %TARGET_HOME%\mo-gm-develop.jar

SET TARGET_HOME=D:\ZW-QKB-Console.WK\mp-game\webroot\WEB-INF\lib
COPY /Y %SOURCE_HOME%\mo-common.jar     %TARGET_HOME%\mo-common.jar
COPY /Y %SOURCE_HOME%\mo-core.jar       %TARGET_HOME%\mo-core.jar
COPY /Y %SOURCE_HOME%\mo-logic.jar      %TARGET_HOME%\mo-logic.jar
COPY /Y %SOURCE_HOME%\mo-web.jar        %TARGET_HOME%\mo-web.jar
COPY /Y %SOURCE_HOME%\mo-gm-develop.jar %TARGET_HOME%\mo-gm-develop.jar

SET TARGET_HOME=D:\ZW-QKB-Work\Server\Config\lib
COPY /Y %SOURCE_HOME%\mo-common.jar     %TARGET_HOME%\mo-common.jar
COPY /Y %SOURCE_HOME%\mo-core.jar       %TARGET_HOME%\mo-core.jar
COPY /Y %SOURCE_HOME%\mo-logic.jar      %TARGET_HOME%\mo-logic.jar
COPY /Y %SOURCE_HOME%\mo-web.jar        %TARGET_HOME%\mo-web.jar
COPY /Y %SOURCE_HOME%\mo-gm-develop.jar %TARGET_HOME%\mo-develop.jar
