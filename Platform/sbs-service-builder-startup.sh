clear

export MO_PATH="/root/ZW-Platform.WK"
export MO_LIBRARY="$MO_PATH/library"

export MO_CLASS="$MO_LIBRARY/jdom-2.0.2.jar:"
export MO_CLASS="$MO_CLASS$MO_LIBRARY/mysql-connector-java-5.1.24-bin.jar:"

export MO_CLASS="$MO_CLASS$MO_LIBRARY/mo-common.jar:"
export MO_CLASS="$MO_CLASS$MO_LIBRARY/mo-core.jar:"
export MO_CLASS="$MO_CLASS$MO_LIBRARY/mo-logic.jar:"
export MO_CLASS="$MO_CLASS$MO_LIBRARY/mo-web.jar:"
export MO_CLASS="$MO_CLASS$MO_LIBRARY/mo-zq-logic.jar:"

export MO_CLASS="$MO_CLASS$MO_LIBRARY/mp-service.jar"

java -cp "$MO_CLASS" com.zqinet.app.builder.RStartup -home "$MO_PATH/mp-service/src/config" -config application-service-linux.xml -logger info
