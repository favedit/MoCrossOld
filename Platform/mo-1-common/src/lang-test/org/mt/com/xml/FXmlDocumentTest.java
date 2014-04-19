package org.mt.com.xml;

import org.mo.com.xml.FXmlDocument;

public class FXmlDocumentTest
{
   public static void main(String[] args){
      FXmlDocument xdoc = new FXmlDocument();
      xdoc.loadFile("D:/WP-Platform/mo-1-common/src/lang-test/org/mt/com/xml/mo-core.xml");
      System.out.println(xdoc.dump());
   }
}
