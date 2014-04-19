/*
 * @(#)FProcedureHelper.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.data.procedure;

import org.mo.com.collections.FDataset;
import org.mo.com.collections.FRow;
import org.mo.com.data.FSql;
import org.mo.com.data.ISqlConnection;
import org.mo.com.io.RFile;
import org.mo.com.lang.FString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.resource.IResource;
import org.mo.com.resource.RResource;
import org.mo.com.xml.FXmlNode;
import org.mo.core.aop.face.ALink;
import org.mo.data.procedure.common.FSqlPackageParser;
import org.mo.eng.data.IDatabaseConsole;
import org.mo.eng.data.common.FSqlQuery;
import org.mo.eng.help.FAbstractBuildHelper;
import org.mo.eng.help.common.XAction;
import org.mo.eng.help.common.XHelp;
import org.mo.eng.template.ITemplateParser;

/**
 * <T>生成Sql package的帮助文档</T>
 *
 * @author ZENGD
 * @version 1.0.1
 */
public class FProcedureHelper
      extends FAbstractBuildHelper
      implements
         IProcedureHelper
{
   // 日志
   private static ILogger _logger = RLogger.find(FProcedureHelper.class);

   // 资源
   private static IResource _resource = RResource.find(FSqlPackageParser.class);

   // 数据库容器
   @ALink
   protected IDatabaseConsole _databaseConsole;

   /* (non-Javadoc)
    * @see org.mo.eng.help.IBuildHelper#buildCatalog(org.mo.eng.help.common.XHelp)
    */
   @Override
   public void buildCatalog(XHelp xhelp){
      buildCatalog(xhelp, makeSqlPackageNode());
   }

   /* (non-Javadoc)
    * @see org.mo.eng.help.IBuildHelper#buildIndex(org.mo.eng.help.common.XHelp)
    */
   @Override
   public void buildIndex(XHelp xhelp){
      buildIndex(xhelp, makeSqlPackageNode());
   }

   /* (non-Javadoc)
    * @see org.mo.data.procedure.IProcedureHelper#buildPackage(org.mo.eng.help.common.XHelp, org.mo.eng.help.common.XAction)
    */
   @Override
   public void buildPackage(XHelp xhelp,
                            XAction xaction){
      /// 取得处理编码
      String encoding = xhelp.getProcessEncoding();
      /// 取得处理路径
      String path = xhelp.getOutputPath();
      _logger.debug(this, "buildPackage", "build package  (file={0})", path);
      ISqlConnection connection = null;
      try{
         /// 建立数据库连接
         connection = _databaseConsole.alloc();
         /// 检索所有包名
         FSqlQuery query = new FSqlQuery(connection, FSqlPackageParser.class, "package.list");
         /// 取得资源记录集
         FDataset dataset = query.fetchDataset();
         for(FRow row : dataset){
            /// 获取包名
            String packageName = row.get("PACKAGE_NAME");
            /// 过滤以HI结尾的包
            if(!packageName.endsWith("_HI")){
               FSqlPackageParser parser = new FSqlPackageParser(connection, packageName);
               /// 取得该包的XML节点信息
               FXmlNode node = parser.makeXmlNode();
               /// 取得包页面的生成模板
               ITemplateParser templateparser = _templateConsole.getParser(xaction.getTemplateName());
               templateparser.define("path", xaction.getProcessPath());
               /// 定义节点的访问名称
               templateparser.define("class", node);
               FString source = templateparser.parse();
               /// 创建包的页面
               RFile.saveToFile(path + "/package/" + packageName + ".html", source.toString(), encoding);
            }
         }
         //         System.out.println("*****************************************");
         //         FSqlPackageParser parser = new FSqlPackageParser(connection, "CM_AREA_DI");
         //         System.out.println(parser.makeXmlNode().xml());
         //         System.out.println("*****************************************");
      }catch(Exception e){
         _logger.error(null, "buildPackage", e);
      }finally{
         if(null != connection){
            _databaseConsole.free(connection);
         }
      }
   }

   /* (non-Javadoc)
    * @see org.mo.data.procedure.IProcedureHelper#buildFunction(org.mo.eng.help.common.XHelp, org.mo.eng.help.common.XAction)
    */
   @Override
   public void buildFunction(XHelp xhelp,
                             XAction xaction){
      /// 取得处理编码
      String encoding = xhelp.getProcessEncoding();
      /// 取得处理路径
      String path = xhelp.getOutputPath();
      _logger.debug(this, "buildFunction", "build funciton  (file={0})", path);
      ISqlConnection connection = null;
      try{
         /// 建立数据库连接
         connection = _databaseConsole.alloc();
         /// 检索所有包名
         FSqlQuery query = new FSqlQuery(connection, FSqlPackageParser.class, "package.list");
         /// 取得资源记录集
         FDataset dataset = query.fetchDataset();
         // 建立函数页
         for(FRow row : dataset){
            /// 获取包名
            String packageName = row.get("PACKAGE_NAME");
            /// 过滤以HI结尾的包
            if(!packageName.endsWith("_HI")){
               FSqlPackageParser parser = new FSqlPackageParser(connection, packageName);
               /// 从包节点中取出方法节点
               FXmlNode methods = parser.makeXmlNode().findNode("Methods");
               for(FXmlNode functionNode : methods.toObjects()){
                  /// 判断节点的名称是否为Function
                  if("Function".equals(functionNode.name())){
                     /// 取得函数的名称ID
                     String id = functionNode.get("name_id");
                     /// 取得函数的名称
                     String name = functionNode.get("name");
                     functionNode.set("package_name", packageName);
                     ITemplateParser templateparser = _templateConsole.getParser(xaction.getTemplateName());
                     templateparser.define("path", xaction.getProcessPath());
                     templateparser.define("function", functionNode);
                     FString source = templateparser.parse();
                     String param = "";
                     param = "" + id;
                     /// 创建函数的页面
                     RFile.saveToFile(path + "/function/" + packageName + "_" + name + param + ".html", source.toString(), encoding);
                  }
               }
            }
         }
      }catch(Exception e){
         _logger.error(null, "buildFunction", e);
      }finally{
         if(null != connection){
            _databaseConsole.free(connection);
         }
      }
   }

   /* (non-Javadoc)
    * @see org.mo.data.procedure.IProcedureHelper#buildProcedure(org.mo.eng.help.common.XHelp, org.mo.eng.help.common.XAction)
    */
   @Override
   public void buildProcedure(XHelp xhelp,
                              XAction xaction){
      String encoding = xhelp.getProcessEncoding();
      String path = xhelp.getOutputPath();
      _logger.debug(this, "buildProcedure", "build procedure  (file={0})", path);
      ISqlConnection connection = null;
      try{
         connection = _databaseConsole.alloc();
         FSqlQuery query = new FSqlQuery(connection, FSqlPackageParser.class, "package.list");
         FDataset dataset = query.fetchDataset();
         // 建立函数页
         for(FRow row : dataset){
            String packageName = row.get("PACKAGE_NAME");
            if(!packageName.endsWith("_HI")){
               FSqlPackageParser parser = new FSqlPackageParser(connection, packageName);
               /// 从包节点中取出方法节点
               FXmlNode methods = parser.makeXmlNode().findNode("Methods");
               for(FXmlNode procedureNode : methods.toObjects()){
                  /// 判断节点的名称是否为Procedure
                  if("Procedure".equals(procedureNode.name())){
                     String id = procedureNode.get("name_id");
                     String name = procedureNode.get("name");
                     procedureNode.set("package_name", packageName);
                     ITemplateParser templateparser = _templateConsole.getParser(xaction.getTemplateName());
                     templateparser.define("path", xaction.getProcessPath());
                     templateparser.define("procedure", procedureNode);
                     FString source = templateparser.parse();
                     String param = "";
                     param = "" + id;
                     /// 创建过程的页面
                     RFile.saveToFile(path + "/procedure/" + packageName + "_" + name + param + ".html", source.toString(), encoding);
                  }
               }
            }
         }
      }catch(Exception e){
         _logger.error(null, "buildProcedure", e);
      }finally{
         if(null != connection){
            _databaseConsole.free(connection);
         }
      }
   }

   /* (non-Javadoc)
    * @see org.mo.data.procedure.IProcedureHelper#buildProperty(org.mo.eng.help.common.XHelp, org.mo.eng.help.common.XAction)
    */
   @Override
   public void buildProperty(XHelp xhelp,
                             XAction xaction){
      String encoding = xhelp.getProcessEncoding();
      String path = xhelp.getOutputPath();
      _logger.debug(this, "buildProperty", "build property  (file={0})", path);
      ISqlConnection connection = null;
      try{
         connection = _databaseConsole.alloc();
         FSqlQuery query = new FSqlQuery(connection, FSqlPackageParser.class, "package.list");
         FDataset dataset = query.fetchDataset();
         // 建立函数页
         for(FRow row : dataset){
            String packageName = row.get("PACKAGE_NAME");
            if(!packageName.endsWith("_HI")){
               FSqlPackageParser parser = new FSqlPackageParser(connection, packageName);
               String isProperties = parser.makeXmlNode().get("has_properties");
               /// 判断属性节点是否不为空
               if("Y".equals(isProperties)){
                  /// 从包节点中取出属性节点
                  FXmlNode properties = parser.makeXmlNode().findNode("Properties");
                  for(FXmlNode propertyNode : properties.toObjects()){
                     String name = propertyNode.get("name");
                     propertyNode.set("package_name", packageName);
                     ITemplateParser templateparser = _templateConsole.getParser(xaction.getTemplateName());
                     templateparser.define("path", xaction.getProcessPath());
                     templateparser.define("property", propertyNode);
                     FString source = templateparser.parse();
                     /// 创建属性的页面
                     RFile.saveToFile(path + "/property/" + packageName + "_" + name + ".html", source.toString(), encoding);
                  }
               }
            }
         }
      }catch(Exception e){
         _logger.error(null, "buildProperty", e);
      }finally{
         if(null != connection){
            _databaseConsole.free(connection);
         }
      }
   }

   /* (non-Javadoc)
    * @see org.mo.data.procedure.IProcedureHelper#buildList(org.mo.eng.help.common.XHelp, org.mo.eng.help.common.XAction)
    */
   @Override
   public void buildList(XHelp xhelp,
                         XAction xaction){
      String encoding = xhelp.getProcessEncoding();
      String path = xhelp.getOutputPath();
      _logger.debug(this, "buildList", "build list  (file={0})", path);
      ISqlConnection connection = null;
      FXmlNode config = new FXmlNode();
      try{
         connection = _databaseConsole.alloc();
         FSqlQuery query = new FSqlQuery(connection, FSqlPackageParser.class, "package.list");
         FDataset dataset = query.fetchDataset();
         for(FRow row : dataset){
            String packageName = row.get("PACKAGE_NAME");
            FXmlNode node = new FXmlNode("package");
            if(!packageName.endsWith("_HI")){
               node.set("name", packageName);
               config.push(node);
            }
         }
         ITemplateParser templateparser = _templateConsole.getParser(xaction.getTemplateName());
         templateparser.define("path", xaction.getProcessPath());
         templateparser.define("packages", config);
         FString source = templateparser.parse();
         /// 创建包列表的页面
         RFile.saveToFile(path + "/list.html", source.toString(), encoding);
      }catch(Exception e){
         _logger.error(null, "buildList", e);
      }finally{
         if(null != connection){
            _databaseConsole.free(connection);
         }
      }
   }

   /* (non-Javadoc)
    * @see org.mo.eng.help.IBuildHelper#buildProject(org.mo.eng.help.common.XHelp)
    */
   @Override
   public void buildProject(XHelp xhelp){
      buildProject(xhelp, makeSqlPackageNode());
   }

   /**
    * <T>获得当前数据库包的名称的Sql语句</T>
    * 
    * @return SQL语句
    */
   protected FString bodySql(){
      String source = _resource.findString("package.list");
      return new FSql(source);
   }

   /**
    * <T>生成制作文档目录及索引所需要的XML</T>
    * 
    * @return XML节点
    */
   protected FXmlNode makeSqlPackageNode(){
      // 建立数据集
      FXmlNode config = new FXmlNode();
      ISqlConnection connection = null;
      try{
         connection = _databaseConsole.alloc();
         FSqlQuery query = new FSqlQuery(connection, FSqlPackageParser.class, "package.list");
         FDataset dataset = query.fetchDataset();
         // 建立函数页
         for(FRow row : dataset){
            String packageName = row.get("PACKAGE_NAME");
            if(!packageName.endsWith("_HI")){
               FSqlPackageParser parser = new FSqlPackageParser(connection, packageName);
               config.push(parser.makeXmlNode());
            }
         }
      }catch(Exception e){
         _logger.error(null, "makeSqlPackageNode", e);
      }finally{
         if(null != connection){
            _databaseConsole.free(connection);
         }
      }
      return config;
   }
}
