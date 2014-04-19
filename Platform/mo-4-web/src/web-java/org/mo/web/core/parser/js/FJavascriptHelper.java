/*
 * @(#)IJavascriptHelper.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.web.core.parser.js;

import org.mo.com.io.RDirectory;
import org.mo.com.io.RFile;
import org.mo.com.lang.FString;
import org.mo.com.lang.FStrings;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.FXmlNode;
import org.mo.eng.help.FAbstractBuildHelper;
import org.mo.eng.help.common.XAction;
import org.mo.eng.help.common.XHelp;
import org.mo.eng.template.ITemplateParser;

/**
 * <T>生成JavaScript的帮助文档的接口</T>
 *
 * @author ZENGD
 * @version 1.0.1
 */
public class FJavascriptHelper
      extends FAbstractBuildHelper
      implements
         IJavascriptHelper
{

   private static ILogger _logger = RLogger.find(FJavascriptHelper.class);

   // JS文档所在的路径
   private static String _directory = "D:/Workspace/eUIS/webroot/ajs";

   @Override
   public void buildAttribute(XHelp xhelp,
                              XAction xaction){
      // 获取模板中的编码
      String encoding = xhelp.getProcessEncoding();
      // 获取文件输出的路径
      String path = xhelp.getOutputPath();
      _logger.debug(this, "buildAttribute", "Build attribute  (file={0})", path);
      // 建立常量页
      FJsHelper helper = getHelper();
      for(FJsClass jsClass : helper.classes().toObjects()){
         String name = jsClass.name();
         // 建立变量的根节点
         FXmlNode node = jsClass.makeNode().findNode("Attributes");
         if(node != null){
            for(FXmlNode attributeNode : node.toObjects()){
               // 获取生成HTML包的XML模板
               ITemplateParser parser = _templateConsole.getParser(xaction.getTemplateName());
               String attributeName = attributeNode.attributes().get("name");
               String space = jsClass.space();
               // 将包名称中包含'.'的转换成下划线
               if(space.indexOf(".") != -1){
                  space = space.replace(".", "_");
               }
               attributeNode.set("package_name", space);
               attributeNode.set("class_name", name);
               parser.define("path", xaction.getProcessPath());
               // 定义根节点的名称
               parser.define("attributes", attributeNode);
               FString source = parser.parse();
               // 保存HTML文件到指定的路径
               RFile.saveToFile(path + "/attribute/" + name + "_" + attributeName + ".html", source.toString(), encoding);
            }
         }
      }
   }

   /* (non-Javadoc)
    * @see org.mo.eng.help.IBuildHelper#buildCatalog(org.mo.eng.help.common.XHelp)
    */
   @Override
   public void buildCatalog(XHelp xhelp){
      buildCatalog(xhelp, makeJavascriptNode());
   }

   /* (non-Javadoc)
    * @see org.mo.web.core.parser.js.IJavascriptHelper#buildClass(org.mo.eng.help.common.XHelp, org.mo.eng.help.common.XAction)
    */
   @Override
   public void buildClass(XHelp xhelp,
                          XAction xaction){
      // 获取模板中的编码
      String encoding = xhelp.getProcessEncoding();
      // 获取文件输出的路径
      String path = xhelp.getOutputPath();
      _logger.debug(this, "buildAttribute", "Build attribute  (file={0})", path);
      // 建立类页
      FJsHelper helper = getHelper();
      for(FJsClass jsClass : helper.classes().toObjects()){
         String name = jsClass.name();
         // 获取生成HTML包的XML模板
         ITemplateParser parser = _templateConsole.getParser(xaction.getTemplateName());
         FXmlNode node = jsClass.makeNode();
         String space = jsClass.space();
         // 将包名称中包含'.'的转换成下划线
         if(space.indexOf(".") != -1){
            space = space.replace(".", "_");
         }
         node.set("space", space);
         parser.define("path", xaction.getProcessPath());
         // 定义根节点的名称
         parser.define("class", node);
         FString source = parser.parse();
         // 保存HTML文件到指定的路径
         RFile.saveToFile(path + "/class/" + name + ".html", source.toString(), encoding);
      }
   }

   /* (non-Javadoc)
    * @see org.mo.eng.help.IBuildHelper#buildIndex(org.mo.eng.help.common.XHelp)
    */
   @Override
   public void buildIndex(XHelp xhelp){
      buildIndex(xhelp, makeJavascriptNode());
   }

   /* (non-Javadoc)
    * @see org.mo.web.core.parser.js.IJavascriptHelper#buildMethod(org.mo.eng.help.common.XHelp, org.mo.eng.help.common.XAction)
    */
   @Override
   public void buildMethod(XHelp xhelp,
                           XAction xaction){
      // 获取模板中的编码
      String encoding = xhelp.getProcessEncoding();
      // 获取文件输出的路径
      String path = xhelp.getOutputPath();
      _logger.debug(this, "buildAttribute", "Build attribute  (file={0})", path);
      FJsHelper helper = getHelper();
      // 建立函数页
      for(FJsClass jsClass : helper.classes().toObjects()){
         String name = jsClass.name();
         // 建立函数的根节点
         FXmlNode node = jsClass.makeNode().findNode("Methods");
         if(node != null){
            for(FXmlNode methodNode : node.toObjects()){
               // 获取生成HTML包的XML模板
               ITemplateParser parser = _templateConsole.getParser(xaction.getTemplateName());
               String methodName = methodNode.attributes().get("name");
               String space = jsClass.space();
               if(space.indexOf(".") != -1){
                  space = space.replace(".", "_");
               }
               methodNode.set("package_name", space);
               methodNode.set("class_name", name);
               parser.define("path", xaction.getProcessPath());
               // 定义根节点的名称
               parser.define("methods", methodNode);
               FString source = parser.parse();
               // 保存HTML文件到指定的路径
               RFile.saveToFile(path + "/method/" + name + "_" + methodName + ".html", source.toString(), encoding);
            }
         }
      }
   }

   /* (non-Javadoc)
    * @see org.mo.eng.help.IBuildHelper#buildProject(org.mo.eng.help.common.XHelp)
    */
   @Override
   public void buildProject(XHelp xhelp){
      buildProject(xhelp, makeJavascriptNode());
   }

   /* (non-Javadoc)
    * @see org.mo.web.core.parser.js.IJavascriptHelper#buildProperty(org.mo.eng.help.common.XHelp, org.mo.eng.help.common.XAction)
    */
   @Override
   public void buildProperty(XHelp xhelp,
                             XAction xaction){
      // 获取模板中的编码
      String encoding = xhelp.getProcessEncoding();
      // 获取文件输出的路径
      String path = xhelp.getOutputPath();
      _logger.debug(this, "buildAttribute", "Build attribute  (file={0})", path);
      FJsHelper helper = getHelper();
      // 建立属性页
      for(FJsClass jsClass : helper.classes().toObjects()){
         String name = jsClass.name();
         // 建立属性的根节点
         FXmlNode node = jsClass.makeNode().findNode("Properties");
         if(node != null){
            for(FXmlNode propertyNode : node.toObjects()){
               // 获取生成HTML包的XML模板
               ITemplateParser parser = _templateConsole.getParser(xaction.getTemplateName());
               String propertyName = propertyNode.attributes().get("name");
               String space = jsClass.space();
               // 将包名称中包含'.'的转换成下划线
               if(space.indexOf(".") != -1){
                  space = space.replace(".", "_");
               }
               propertyNode.set("package_name", space);
               propertyNode.set("class_name", name);
               parser.define("path", xaction.getProcessPath());
               // 定义根节点的名称
               parser.define("properties", propertyNode);
               FString source = parser.parse();
               // 保存HTML文件到指定的路径
               RFile.saveToFile(path + "/property/" + name + "_" + propertyName + ".html", source.toString(), encoding);
            }
         }
      }
   }

   /* (non-Javadoc)
    * @see org.mo.web.core.parser.js.IJavascriptHelper#buildSpace(org.mo.eng.help.common.XHelp, org.mo.eng.help.common.XAction)
    */
   @Override
   public void buildSpace(XHelp xhelp,
                          XAction xaction){
      // 获取模板中的编码
      String encoding = xhelp.getProcessEncoding();
      // 获取文件输出的路径
      String path = xhelp.getOutputPath();
      _logger.debug(this, "buildSpace", "Build attribute  (file={0})", path);
      FJsHelper helper = getHelper();
      // 建立包页
      FXmlNode spacesNode = helper.makeSpace();
      for(FXmlNode spaceNode : spacesNode.nodes()){
         String space = spaceNode.get("name");
         // 获取生成HTML包的XML模板
         ITemplateParser parser = _templateConsole.getParser(xaction.getTemplateName());
         parser.define("path", xaction.getProcessPath());
         // 定义根节点的名称
         parser.define("classes", helper.makeCatalog(space));
         FString source = parser.parse();
         // 将包名称中包含'.'的转换成下划线
         if(space.indexOf(".") != -1){
            space = space.replace(".", "_");
         }
         // 保存HTML文件到指定的路径
         RFile.saveToFile(path + "/space/" + space + ".html", source.toString(), encoding);
      }
   }

   /**
    * <T>获取生成各个项的帮助对象</T>
    * 
    * @return 文档帮助对象
    */
   private FJsHelper getHelper(){
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
      return helper;
   }

   /**
    * <T>生成制作文档目录及索引所需要的XML</T>
    * 
    * @return XML节点
    */
   protected FXmlNode makeJavascriptNode(){
      // 建立数据集
      FJsHelper helper = getHelper();
      FXmlNode config = new FXmlNode();
      FXmlNode spacesNode = helper.makeSpace();
      for(FXmlNode spaceNode : spacesNode.nodes()){
         String space = spaceNode.get("name");
         FXmlNode catalogNode = helper.makeCatalog(space);
         FXmlNode classesNode = new FXmlNode("Classes");
         for(FXmlNode classNode : catalogNode.nodes()){
            String className = classNode.get("name");
            FXmlNode paramNode = new FXmlNode("Class");
            paramNode.set("name", className);
            for(FJsClass jsClass : helper.classes().toObjects()){
               String name = jsClass.name();
               if(className.equals(name)){
                  // 获取方法节点
                  FXmlNode methodNode = jsClass.makeNode().findNode("Methods");
                  if(methodNode != null){
                     paramNode.push(methodNode);
                  }
                  // 获取属性节点
                  FXmlNode propertyNode = jsClass.makeNode().findNode("Properties");
                  if(propertyNode != null){
                     paramNode.push(propertyNode);
                  }
                  // 获取变量节点
                  FXmlNode attributeNode = jsClass.makeNode().findNode("Attributes");
                  if(attributeNode != null){
                     paramNode.push(attributeNode);
                  }
                  classesNode.push(paramNode);
               }
            }
            spaceNode.push(classesNode);
         }
         spaceNode.node("Classes").sort(true, "name");
         config.push(spaceNode);
      }
      return config;
   }

}
