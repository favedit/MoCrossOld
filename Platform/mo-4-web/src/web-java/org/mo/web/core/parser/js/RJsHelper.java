package org.mo.web.core.parser.js;

import org.mo.com.io.RDirectory;
import org.mo.com.io.RFile;
import org.mo.com.lang.FString;
import org.mo.com.lang.FStrings;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.FXmlNode;
import org.mo.core.aop.RAop;
import org.mo.eng.template.ITemplateConsole;
import org.mo.eng.template.ITemplateParser;

public class RJsHelper
{

   private static ILogger _logger = RLogger.find(FJsHelper.class);

   private static String _directory = "D:/Workspace/eUIS/webroot/ajs";

   private static String _helpDir = "D:/workspace/eUIS/help/src/javascript";

   private static String _encoding = "UTF-8";

   public static void main(String[] args){
      try{
         // 加载设置
         String config = "D:/Workspace/eUIS/src/eUIS-config/application.xml";
         RAop.configConsole().loadFile(config);
         // 分解全部JS类
         FJsHelper helper = new FJsHelper();
         FStrings dirs = RDirectory.listDirectory(_directory, true);
         for(String dir : dirs){
            if(dir.contains("6-help")){
               continue;
            }
            int find = dir.lastIndexOf('-');
            if(-1 != find){
               String space = dir.substring(find + 1);
               helper.parseSpace(space, dir);
            }
         }
         // 获得模板控制台
         ITemplateConsole tc = RAop.find(ITemplateConsole.class);
         // 建立标题页
         ITemplateParser parser = tc.findParser("helper.javascript", "title");
         FXmlNode spacesNode = helper.makeSpace();
         parser.define("classes", spacesNode);
         FString source = parser.parse();
         RFile.saveToFile(_helpDir + "/Title.html", source.toString(), _encoding);
         // 建立包页
         for(FXmlNode spaceNode : spacesNode.nodes()){
            String space = spaceNode.get("name");
            parser = tc.findParser("helper.javascript", "list");
            parser.define("classes", helper.makeCatalog(space));
            source = parser.parse();
            RFile.saveToFile(_helpDir + "/space/" + space + ".html", source.toString(), _encoding);
         }
         // 建立类页
         for(FJsClass jsClass : helper.classes().toObjects()){
            String name = jsClass.name();
            parser = tc.findParser("helper.javascript", "detail");
            FXmlNode node = jsClass.makeNode();
            String space = jsClass.space();
            node.set("space", space);
            parser.define("class", node);
            source = parser.parse();
            RFile.saveToFile(_helpDir + "/class/" + name + ".html", source.toString(), _encoding);
         }
         // 建立常量页
         for(FJsClass jsClass : helper.classes().toObjects()){
            String name = jsClass.name();
            FXmlNode node = jsClass.makeNode().findNode("Attributes");
            if(node != null){
               for(FXmlNode attributeNode : node.toObjects()){
                  parser = tc.findParser("helper.javascript", "attribute");
                  String attributeName = attributeNode.attributes().get("name");
                  attributeNode.set("package_name", jsClass.space());
                  attributeNode.set("class_name", name);
                  parser.define("attributes", attributeNode);
                  source = parser.parse();
                  // 常量： att_类名_常量名
                  RFile.saveToFile(_helpDir + "/attribute/" + name + "_" + attributeName + ".html", source.toString(), _encoding);
               }
            }
         }
         // 建立属性页
         for(FJsClass jsClass : helper.classes().toObjects()){
            String name = jsClass.name();
            FXmlNode node = jsClass.makeNode().findNode("Properties");
            if(node != null){
               for(FXmlNode propertyNode : node.toObjects()){
                  parser = tc.findParser("helper.javascript", "property");
                  String propertyName = propertyNode.attributes().get("name");
                  propertyNode.set("package_name", jsClass.space());
                  propertyNode.set("class_name", name);
                  parser.define("properties", propertyNode);
                  source = parser.parse();
                  // 常量： att_类名_常量名
                  RFile.saveToFile(_helpDir + "/property/" + name + "_" + propertyName + ".html", source.toString(), _encoding);
               }
            }
         }
         // 建立函数页
         for(FJsClass jsClass : helper.classes().toObjects()){
            String name = jsClass.name();
            FXmlNode node = jsClass.makeNode().findNode("Methods");
            if(node != null){
               for(FXmlNode methodNode : node.toObjects()){
                  parser = tc.findParser("helper.javascript", "method");
                  String methodName = methodNode.attributes().get("name");
                  methodNode.set("package_name", jsClass.space());
                  methodNode.set("class_name", name);
                  parser.define("methods", methodNode);
                  source = parser.parse();
                  // 常量： att_类名_常量名
                  RFile.saveToFile(_helpDir + "/method/" + name + "_" + methodName + ".html", source.toString(), _encoding);
               }
            }
         }
         // 释放设置
         RAop.release();
      }catch(Exception e){
         _logger.error(null, "main", e);
      }
   }
}
