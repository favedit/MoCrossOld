package org.mo.script.java.util;

import org.mo.script.java.converter.FJavaConverter;

//============================================================
// <T>Java到ActionScript转换器。</T>
//============================================================
public class RJava2ClientAsConverter
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
      FJavaConverter converter = new FJavaConverter();
      // 读取配置
      String configFileName = "D:/WP-Platform/mo-3-script/config/as-class.xml";
      converter.loadConfigFile(configFileName);
      // 转换处理
      converter.convert();
   }
}
