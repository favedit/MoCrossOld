package org.mo.jfa.face.database.dataset;

import org.mo.com.io.RFile;
import org.mo.com.lang.FString;
import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.IXmlObject;
import org.mo.com.xml.RXmlObject;
import org.mo.core.aop.RAop;
import org.mo.data.dataset.IDatasetConsole;
import org.mo.eng.template.ITemplateConsole;
import org.mo.eng.template.ITemplateParser;
import org.mo.web.core.parser.js.FJsHelper;

public class RHelpTest
{

   private static ILogger _logger = RLogger.find(FJsHelper.class);

   private static String _help = "D:/Workspace/eUIS/help";

   private static String _directory = "D:/Workspace/eUIS/help/src/database.dataset";

   private static String _encoding = "GB2312";

   public static void main(String[] args){
      try{
         boolean buildList = true;
         boolean buildDataset = true;
         boolean buildProject = true;
         // 变量
         FString source = null;
         ITemplateParser parser = null;
         // 加载设置
         String appConfig = "D:/Workspace/eUIS/src/eUIS-config/application.xml";
         RAop.configConsole().loadFile(appConfig);
         // 获得模板控制台
         IDatasetConsole dc = RAop.find(IDatasetConsole.class);
         ITemplateConsole tc = RAop.find(ITemplateConsole.class);

         if(buildList){
            // 建立标题页
            parser = tc.findParser("helper.database", "list");
            FXmlNode config = new FXmlNode();
            for(IXmlObject xds : dc.list()){
               FXmlNode node = RXmlObject.convertToNode(xds, EXmlConfig.Simple);
               config.push(node);
            }
            _logger.debug(null, "main", "Build dataset list");
            parser.define("path", "../..");
            parser.define("config", config);
            source = parser.parse();
            RFile.saveToFile(_directory + "/list.html", source.toString(), _encoding);
         }

         if(buildDataset){
            // 建立全部数据集对象
            parser = tc.findParser("helper.database", "dataset");
            for(IXmlObject xds : dc.list()){
               String name = xds.innerGet("name");
               _logger.debug(null, "main", "Build dataset help (dataset={0})", name);
               name = RString.replace(name, '.', '_').toLowerCase();
               FXmlNode node = RXmlObject.convertDeepToNode(xds, EXmlConfig.Simple);
               parser.reset();
               parser.define("path", "../../..");
               parser.define("config", node);
               source = parser.parse();
               RFile.saveToFile(_directory + "/dataset/" + name + ".html", source.toString(), _encoding);
            }
         }

         if(buildProject){
            FXmlNode config = new FXmlNode();
            for(IXmlObject xds : dc.list()){
               FXmlNode node = RXmlObject.convertToNode(xds, EXmlConfig.Simple);
               config.push(node);
            }
            // 建立帮助项目文件
            parser = tc.findParser("helper.database", "hhp");
            parser.define("config", config);
            source = parser.parse();
            RFile.saveToFile(_help + "/dataset.hhp", source.toString(), _encoding);
            // 建立帮助目录文件
            parser = tc.findParser("helper.database", "hhc");
            parser.define("config", config);
            source = parser.parse();
            RFile.saveToFile(_directory + "/dataset.hhc", source.toString(), _encoding);
            // 建立帮助索引文件
            parser = tc.findParser("helper.database", "hhk");
            parser.define("config", config);
            source = parser.parse();
            RFile.saveToFile(_directory + "/dataset.hhk", source.toString(), _encoding);
         }

         //         FXmlNode spacesNode = helper.makeSpace();
         //         // 建立目录页
         //         for(FXmlNode spaceNode : spacesNode.nodes()){
         //            String id = spaceNode.get("id");
         //            String space = spaceNode.get("name");
         //            parser = tc.findParser("helper.javascript", "catalog");
         //            parser.define("classes", helper.makeCatalog(space));
         //            source = parser.parse();
         //            RFile.saveToFile(_helpDir + "/Catalog" + id + ".html", source.toString(), _encoding);
         //            break;
         //         }
         //         // 建立函数页
         //         for(FJsClass jsClass : helper.classes().toObjects()){
         //            String name = jsClass.name();
         //            parser = tc.findParser("helper.javascript", "detail");
         //            FXmlNode node = jsClass.makeNode();
         //            System.out.println(node);
         //            parser.define("class", node);
         //            source = parser.parse();
         //            RFile.saveToFile(_helpDir + "/detail/" + name + ".html", source.toString(), _encoding);
         //         }
         // 释放设置
         RAop.release();
      }catch(Exception e){
         _logger.error(null, "main", e);
      }
   }
}
