package org.mo.util.dataset;

import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.FXmlDocument;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.RAop;
import org.mo.data.dataset.IDatasetConsole;
import org.mo.util.data.RDataImport;

public class RLogicUnitCopy
{

   private final static ILogger _logger = RLogger.find(RDataImport.class);

   public static void main(String[] args){
      try{
         String config = "D:/Workspace/eUIS/src/eUIS-config/application.xml";
         RAop.configConsole().loadFile(config);

         String path = "D:/Workspace/eUIS/webroot/WEB-INF/config/database.logicunit";

         IDatasetConsole dc = RAop.find(IDatasetConsole.class);
         // Load data
         for(IXmlObject xobject : dc.list()){
            String name = xobject.innerGet("name");
            FXmlDocument xdoc = new FXmlDocument();
            FXmlNode rootNode = xdoc.root();
            rootNode.setName("LogicUnit");
            rootNode.set("name", name);
            rootNode.set("is_valid", "Y");
            rootNode.set("label", xobject.innerGet("label"));
            rootNode.set("note", xobject.innerGet("note"));
            // Dataset node
            FXmlNode node = rootNode.createNode("Dataset");
            node.set("name", name);
            node.set("is_valid", "Y");
            node.set("label", "数据集定义");
            node.set("note", xobject.innerGet("label"));
            // Table 	
            node = rootNode.createNode("Table");
            node.set("name", xobject.innerGet("data_name") + "_DS");
            node.set("is_valid", "Y");
            node.set("label", "数据表");
            node.set("note", xobject.innerGet("label"));
            // Table history
            node = rootNode.createNode("Table");
            node.set("name", xobject.innerGet("data_name") + "_HS");
            node.set("is_valid", "Y");
            node.set("label", "历史表");
            node.set("note", xobject.innerGet("label"));
            // View
            node = rootNode.createNode("View");
            node.set("name", xobject.innerGet("data_name"));
            node.set("is_valid", "Y");
            node.set("label", "数据视图");
            node.set("note", xobject.innerGet("label"));
            // View history
            node = rootNode.createNode("View");
            node.set("name", xobject.innerGet("data_name") + "_HV");
            node.set("is_valid", "Y");
            node.set("label", "历史视图");
            node.set("note", xobject.innerGet("label"));
            // View history
            node = rootNode.createNode("Sequence");
            node.set("name", xobject.innerGet("data_name") + "_SQ");
            node.set("is_valid", "Y");
            node.set("label", "数据队列");
            node.set("note", xobject.innerGet("label"));
            // View history
            node = rootNode.createNode("Sequence");
            node.set("name", xobject.innerGet("data_name") + "_HQ");
            node.set("is_valid", "Y");
            node.set("label", "历史队列");
            node.set("note", xobject.innerGet("label"));
            // Dataset node
            node = rootNode.createNode("Package");
            node.set("name", xobject.innerGet("data_name") + "_DI");
            node.set("is_valid", "Y");
            node.set("label", "数据包");
            node.set("note", xobject.innerGet("label"));
            // Dataset node
            node = rootNode.createNode("Package");
            node.set("name", xobject.innerGet("data_name") + "_HI");
            node.set("is_valid", "Y");
            node.set("label", "历史包");
            node.set("note", xobject.innerGet("label"));
            xdoc.saveFile(path + "/" + RString.replace(name, '.', '/') + ".xml");
         }

         RAop.release();
      }catch(Exception e){
         _logger.error(null, "testLoadConfig", e);
      }
   }
}
