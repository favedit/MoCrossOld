package org.mo.script.as.util;

import org.mo.com.xml.FXmlDocument;
import org.mo.com.xml.FXmlNode;
import org.mo.script.as.converter.FAsConverter;

//============================================================
// <T>ActionScript到Java转换器。</T>
//============================================================
public class RAs2JavaConverter
{
   //============================================================
   // <T>启动函数。</T>
   //
   // @param args 参数集合
   //============================================================
   public static void main(String[] args){
      String fileName = "D:/WP-Platform/mo-3-script/src/script-as-util/org/mo/script/as/util/RAs2JavaConverter.xml";
      // 加载设置文件
      FXmlDocument xdocument = new FXmlDocument();
      xdocument.loadFile(fileName);
      // 创建转换器
      FAsConverter converter = new FAsConverter();
      String configFileName = "D:/WP-Platform/mo-3-script/config/as-class.xml";
      converter.loadConfigFile(configFileName);
      // 转换节点
      for(FXmlNode xpath : xdocument.root()){
         String asPath = xpath.get("as_path");
         String javaPath = xpath.get("java_path");
         for(FXmlNode xconvert : xpath.nodes()){
            String asFullPath = asPath + xconvert.get("as_path");
            String javaFullPath = javaPath + xconvert.get("java_path");
            converter.parseDirectory(asFullPath, javaFullPath);
         }
      }
      // 转换所有数据
      converter.convert();
      //String configFile = "D:/WP-Platform/mo-3-script/src/script-config/application.xml";
      //RAop.configConsole().loadFile(configFile);
   }
}
