package org.mo.util.source;

import org.mo.com.io.FStringFile;
import org.mo.com.lang.FString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.system.RApplication;
import org.mo.com.xml.FXmlDocument;
import org.mo.com.xml.FXmlNode;
import org.mo.core.aop.RAop;
import org.mo.eng.template.ITemplateConsole;
import org.mo.eng.template.ITemplateParser;
import org.mt.core.aop.RAopTest;

//============================================================
// <T>AS对象转DotNet类对象代码工具类。</T>
//============================================================
public class RAsToNetClassSource
{
   // 日志输出接口
   private final static ILogger _logger = RLogger.find(RAsToNetClassSource.class);

   //============================================================
   // <T>启动处理。</T>
   //============================================================
   public static void main(String[] args){
      try{
         // 关联参数
         RApplication.linkArgements(args);
         // 获得设置
         String homeOption = RApplication.findArgement("-home", "D:/ZW-Platform.WK/mp-platform/webroot/WEB-INF/classes");
         String configOption = RApplication.findArgement("-config", "application-local") + ".xml";
         // 加载设置文件
         String configFileName = homeOption + "\\" + configOption;
         RAop.configConsole().loadFile(configFileName);
         // 建立所有枚举
         ITemplateConsole console = RAop.find(ITemplateConsole.class);
         // 打开定义
         FXmlDocument xdoc = new FXmlDocument("D:/ZW-Tools.WK/Configuration/as_class.xml");
         int index = 0;
         for(FXmlNode xclass : xdoc.root().nodes()){
            if(xclass.isName("Class")){
               xclass.set("index", ++index);
               for(FXmlNode xproperty : xclass.nodes()){
                  if(xproperty.isName("Property")){
                     // 调整属性
                     if(!xproperty.contains("group")){
                        xproperty.set("group", "保留");
                     }
                     if(!xproperty.contains("type")){
                        xproperty.set("type", "string");
                     }
                     if(!xproperty.contains("default")){
                        xproperty.set("default", "null");
                     }
                     // 设置类型
                     String type = xproperty.get("type");
                     if(type.equals("bool")){
                        xproperty.set("full_type", "Boolean");
                     }else if(type.equals("int")){
                        xproperty.set("full_type", "Integer");
                     }else if(type.equals("uint")){
                        xproperty.set("full_type", "Integer");
                     }else if(type.equals("string")){
                        xproperty.set("full_type", "");
                     }else if(type.equals("color")){
                        xproperty.set("full_type", "uint");
                     }else{
                        xproperty.set("full_type", type);
                     }
                     // 设置CS类型
                     if(type.equals("bool")){
                        xproperty.set("cs_type", "bool");
                     }else if(type.equals("int")){
                        xproperty.set("cs_type", "int");
                     }else if(type.equals("uint")){
                        xproperty.set("cs_type", "uint");
                     }else if(type.equals("string")){
                        xproperty.set("cs_type", "string");
                     }else if(type.equals("color")){
                        xproperty.set("cs_type", "System.Drawing.Color");
                     }else if(type.equals("resource")){
                        xproperty.set("cs_type", "SUiPictureResource");
                     }else{
                        String csType = xproperty.get("cs_type");
                        xproperty.set("cs_type", csType);
                     }
                  }
               }
            }
         }
         //............................................................
         // 生成组件枚举CS代码
         String directory = "D:/ZW-Tools.WK/3 - Design2d/MoDesign2dFace/Design/Common";
         ITemplateParser parser = console.findParser("game.ui.cs-enum-component");
         parser.define("classes", xdoc.root());
         FString source = parser.parse();
         // 文件保存
         String fileName = directory + "/EUiComponent.cs";
         FStringFile file = new FStringFile();
         file.append(source);
         file.saveFile(fileName);
         _logger.debug(null, "main", "Build dotnet enum cs source. (file_name={1})", fileName);
         //............................................................
         // 生成组件枚举AS代码
         parser = console.findParser("game.ui.as-enum-component");
         parser.define("classes", xdoc.root());
         source = parser.parse();
         // 文件保存
         fileName = "D:/ZW-QKB-Work/Client/mo-2-core/4-ui/mo/cr/ui/common/EUiComponent.as";
         file = new FStringFile();
         file.append(source);
         file.saveFile(fileName);
         _logger.debug(null, "main", "Build dotnet enum as source. (file_name={1})", fileName);
         //............................................................
         // 生成控件代码
         directory = "D:/ZW-Tools.WK/3 - Design2d/MoDesign2dFace/Design/Base";
         for(FXmlNode xclass : xdoc.root().nodes()){
            if(xclass.isName("Class")){
               // 调整属性
               String className = xclass.get("name");
               if(!xclass.contains("parent")){
                  xclass.set("parent", "FUiControl");
               }
               // 生成代码
               parser = console.findParser("game.ui.cs-proprty");
               parser.define("class", xclass);
               source = parser.parse();
               // 文件保存
               fileName = directory + "/FUiBase" + className + ".cs";
               file = new FStringFile();
               file.append(source);
               file.saveFile(fileName);
               _logger.debug(null, "main", "Build dotnet source. (class={1}, file_name={2})", className, fileName);
            }
         }
         // 释放资源
         RAop.release();
      }catch(Exception e){
         _logger.error(RAopTest.class, "main", e);
      }
   }
}
