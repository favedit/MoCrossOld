package org.mo.util.xml;

import org.mo.com.io.RFile;
import org.mo.com.lang.FDictionary;
import org.mo.com.lang.RDump;
import org.mo.com.lang.RString;
import org.mo.com.xml.FXmlDocument;
import org.mo.com.xml.FXmlNode;

public class RUuidChecker{

   public static void main(String[] args){
      FDictionary<FXmlDocument> docs = new FDictionary<FXmlDocument>(FXmlDocument.class);
      FDictionary<FXmlNode> nodes = new FDictionary<FXmlNode>(FXmlNode.class);
      String dir = "D:/Workspace/eUIS/webroot/WEB-INF/config";
      for(String file : RFile.listAllFile(dir)){
         if(file.endsWith(".xml")){
            FXmlDocument xdoc = new FXmlDocument(file);
            for(FXmlNode node : xdoc.root().allNodes()){
               String uuid = node.get("_uuid");
               if(RString.isNotEmpty(uuid)){
                  if(!nodes.contains(uuid)){
                     docs.set(uuid, xdoc);
                     nodes.set(uuid, node);
                  }else{
                     FXmlDocument xdocOrg = docs.get(uuid);
                     FXmlNode xnodeOrg = nodes.get(uuid);
                     System.out.println(RDump.LINE_L2);
                     System.out.println("Doc = " + xdocOrg.fileName());
                     System.out.println(xnodeOrg.xml());
                     System.out.println(RDump.LINE_L2);
                     System.out.println("Doc = " + xdoc.fileName());
                     System.out.println(node.xml());
                     return;
                  }
               }
            }
         }
      }
      System.out.println("No error.");
   }
}
