package org.mo.web.tag.util;

import org.mo.com.io.FStringFile;
import org.mo.com.io.RFile;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObjects;
import org.mo.com.lang.FString;
import org.mo.com.lang.RString;
import org.mo.com.xml.FXmlDocument;
import org.mo.com.xml.FXmlNode;
import org.mo.core.aop.RAop;
import org.mo.eng.template.FTemplateParser;
import org.mo.eng.template.ITemplateConsole;

public class FTagBuilder
{

   private String _path;

   private FObjects<FTagInfo> _tags = new FObjects<FTagInfo>(FTagInfo.class);

   public FTagBuilder(){
      _path = RAop.configConsole().parseDefine("home.source.web.tag");
   }

   public FTagInfo append(String name){
      FTagInfo tagInfo = new FTagInfo();
      tagInfo.setName(name);
      _tags.push(tagInfo);
      return tagInfo;
   }

   public FString build(){
      FString xml = new FString();
      build(xml);
      return xml;
   }

   public void build(FString xml){
      xml.appendLine("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>");
      xml.appendLine("<!DOCTYPE taglib");
      xml.appendLine("      PUBLIC \"-//Sun Microsystems, Inc.//DTD JSP Tag Library 1.1//EN\"");
      xml.appendLine("      \"http://java.sun.com/j2ee/dtds/web-jsptaglibrary_1_1.dtd\">");
      xml.appendLine();
      xml.appendLine("<taglib>");
      xml.appendLine("<tlibversion>1.0</tlibversion>");
      xml.appendLine("<jspversion>1.1</jspversion>");
      xml.appendLine("<shortname>jfax</shortname>");
      xml.appendLine("<info>Html Tag</info>");
      xml.appendLine();
      for(FTagInfo tagInfo : _tags){
         tagInfo.build(xml);
         xml.appendLine();
      }
      xml.appendLine("</taglib>");
   }

   public void buildFromFile(String filename){
      _tags.clear();
      FXmlDocument doc = new FXmlDocument(filename);
      FXmlNode root = doc.root();
      for(FXmlNode node : root.nodes()){
         if(node.isName("Tag")){
            FTagInfo tagInfo = new FTagInfo();
            tagInfo.fromNode(node);
            _tags.push(tagInfo);
         }
      }
   }

   protected void buildJavaSource(FXmlNode config){
      String base = config.get("base");
      String output = RFile.makeFilename(_path, RString.replace(base, '.', '/')) + ".java";

      ITemplateConsole tc = RAop.find(ITemplateConsole.class);
      FXmlNode root = tc.findTemplate("tag.java", "base");
      FTemplateParser parser = new FTemplateParser(root);

      String[] bases = RString.splitLastTwo(base, '.');
      parser.define("package", bases[0]);
      parser.define("class", bases[1]);
      parser.define("tag", config);
      parser.define("attributes", config.nodes());

      FStringFile file = new FStringFile();
      file.append(parser.parse());
      file.saveFile(output, "UTF-8");
   }

   public void buildJavaSource(String filename){
      FXmlDocument doc = new FXmlDocument(filename);

      for(FXmlNode node : doc.root().nodes()){
         if(node.isName("Tag") && !RString.isEmpty(node.get("base"))){
            buildJavaSource(node);
         }
      }
   }

   public void saveToFile(String filename){
      FStringFile file = new FStringFile();
      try{
         build(file);
         file.saveFile(filename, "UTF-8");
      }catch(Throwable t){
         throw new FFatalError(t, "Save to file: {0}", filename);
      }
   }

}
