package org.mo.script.java.util;

import org.mo.script.as.converter.FAsConverter;

//============================================================
// <T>Java到Cpp转换器。</T>
//============================================================
public class RJava2MobileCppConverter
{
   //============================================================
   // <T>启动函数。</T>
   //
   // @param args 参数集合
   //============================================================
   public static void main(String[] args){
      //String configFile = "D:/WP-Platform/mo-3-script/src/script-config/application.xml";
      //RAop.configConsole().loadFile(configFile);
      // 创建转换器
      String configFileName = "D:/WP-Platform/mo-3-script/config/as-class.xml";
      FAsConverter converter = new FAsConverter();
      converter.loadConfigFile(configFileName);
      converter.convert();
   }
}
