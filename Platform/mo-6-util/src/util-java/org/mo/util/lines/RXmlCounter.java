package org.mo.util.lines;

import org.mo.com.io.RFile;
import org.mo.com.xml.FXmlDocument;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.FXmlNodes;

public class RXmlCounter{

   public static void main(String[] args){
      int xmlCount = 0;
      int nodes = 0;
      int attributeCount = 0;
      String path = "D:/Workspace/eUIS/webroot/WEB-INF/config";
      for(String fileName : RFile.listAllFile(path)){
         if(fileName.toString().endsWith(".xml")){
            System.out.println(fileName);
            xmlCount++;
            FXmlDocument document = new FXmlDocument(fileName);
            FXmlNodes allNodes = document.root().allNodes();
            nodes += allNodes.count();
            for(FXmlNode node : allNodes){
               attributeCount += node.attributes().count();
            }
         }
      }
      System.out.println("xml=" + xmlCount + " nodes=" + nodes + " attrs=" + attributeCount);
   }
}
