export MOBJ_MODE=Logic
export MOBJ_JS=Test
export MOBJ_CONFIG=application-linux

export JAVA_HOME=/usr/java/jdk1.7.0_25
export JAVA_OPTS="-Duser.mobj.mode=$MOBJ_MODE -Duser.mobj.js=$MOBJ_JS -Duser.mobj.config=$MOBJ_CONFIG"

export CATALINA_OPTS="-Xms256m -Xmx512m"
export CATALINA_HOME="/root/ZW-Platform.WK/mt-server/tomcat-linux-game-qkb-7.0.42"

echo "---- [Platform] Shutdown - begin ---------------------------"
"$CATALINA_HOME/bin/shutdown.sh"
echo "---- [Platform] Shutdown - End -----------------------------"
echo "MOBJ_MODE     = [$MOBJ_MODE"]
echo "MOBJ_JS       = [$MOBJ_JS"]
echo "MOBJ_CONFIG   = [$MOBJ_CONFIG"]
echo "JAVA_OPTS     = [$JAVA_OPTS"]
echo "CATALINA_OPTS = [$CATALINA_OPTS"]
echo "CATALINA_HOME = [$CATALINA_HOME"]
echo "---- [Platform] Startup  - begin ---------------------------"
"$CATALINA_HOME/bin/catalina.sh" start
echo "---- [Platform] Startup  - end -----------------------------"
