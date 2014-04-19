package org.mo.util.dataset;

import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.util.data.RDataImport;

public class RDataset
{

   @SuppressWarnings("unused")
   private final static ILogger _logger = RLogger.find(RDataImport.class);

   //   public static void main(String[] args){
   //      try{
   //         String config = "D:/Workspace/eUIS/webroot/WEB-INF/config/database.dataset";
   //         for(String fileName : RFile.listAllFile(config)){
   //            if(fileName.endsWith(".xml")){
   //               boolean isChanged = false;
   //               FXmlDocument xdoc = new FXmlDocument(fileName);
   //               // DataStore 
   //               FXmlNode rootNode = xdoc.root();
   //               if(rootNode.isName("DataStore")){
   //                  String dataLogic = rootNode.get("data_logic");
   //                  //if(RString.isEmpty(dataLogic)){
   //                  rootNode.set("data_logic", rootNode.get("data_name") + "_DI");
   //                  isChanged = true;
   //                  //}
   //               }
   //               // Node
   //               for(FXmlNode node : xdoc.root().allNodes()){
   //                  if(node.isName("Field")){
   //                     String type = node.get("type");
   //                     if("bool".equals(type)){
   //                        node.setName("FieldBoolean");
   //                        node.set("data_type", "Boolean");
   //                     }else if("int".equals(type) || "integer".equals(type)){
   //                        node.setName("FieldInteger");
   //                        node.set("data_type", "Integer");
   //                     }else if("float".equals(type)){
   //                        node.setName("FieldFloat");
   //                        node.set("data_type", "Float");
   //                     }else if("date".equals(type)){
   //                        node.setName("FieldDate");
   //                        node.set("data_type", "Date");
   //                     }else if("char".equals(type)){
   //                        node.setName("FieldChar");
   //                        node.set("data_type", "Char");
   //                     }else if("string".equals(type)){
   //                        node.setName("FieldString");
   //                        node.set("data_type", "String");
   //                     }else if("mstring".equals(type)){
   //                        node.setName("FieldContent");
   //                        node.set("data_type", "Content");
   //                     }else if("multiString".equals(type)){
   //                        node.setName("FieldContent");
   //                        node.set("data_type", "Content");
   //                     }else if("enum".equals(type)){
   //                        node.setName("FieldEnum");
   //                        node.set("data_type", "Enum");
   //                     }else if("set".equals(type)){
   //                        node.setName("FieldSet");
   //                        node.set("data_type", "Set");
   //                     }else if("stream".equals(type)){
   //                        node.setName("FieldStream");
   //                        node.set("data_type", "Stream");
   //                     }
   //                  }
   //               }
   //               if(isChanged){
   //                  System.out.println(fileName);
   //                  xdoc.saveToFile();
   //               }
   //            }
   //         }
   //      }catch(Exception e){
   //         _logger.error(null, "testLoadConfig", e);
   //      }
   //   }
}
