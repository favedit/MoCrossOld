package org.mo.com.system;

import java.util.Enumeration;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FString;
import org.mo.com.lang.IStringPair;
import org.mo.com.lang.REnum;
import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;

//============================================================
// <T>系统工具类。</T>
//============================================================
public class RSystem
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(RSystem.class);

   //============================================================
   // <T>获得系统运行模式。</T>
   //
   // @param 运行模式
   //============================================================
   public static ESystemMode modeCd(){
      String systemMode = System.getProperty("user.mo.mode");
      return REnum.parse(ESystemMode.class, systemMode);
   }

   //============================================================
   // <T>获得系统变量。</T>
   //
   // @param name 名称
   // @return 变量
   //============================================================
   public static String property(String name){
      return System.getProperty(name);
   }

   //============================================================
   // <T>打印出系统环境变量。</T>
   //============================================================
   public static void printProperties(){
      // 获得系统变量
      String name = null;
      Enumeration<?> properties = System.getProperties().keys();
      FAttributes attrs = new FAttributes();
      while(properties.hasMoreElements()){
         name = (String)properties.nextElement();
         attrs.set(name, System.getProperty(name));
      }
      // 输出信息
      FString dump = new FString();
      int length = attrs.nameLengthMax();
      for(IStringPair pair : attrs){
         name = RString.rightPad(pair.name(), length, ' ');
         dump.append(RString.rightPad(pair.name(), length, ' '));
         dump.append(" [");
         dump.append(pair.value());
         dump.append("]\n");
      }
      _logger.debug(null, "printProperties", "Properties:\n{0}", dump);
   }
}
