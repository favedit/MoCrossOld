package org.mo.eng.persistence.common;

import org.mo.com.io.FStringFile;
import org.mo.com.xml.FXmlNode;
import org.mo.core.aop.RAop;
import org.mo.eng.template.FTemplateParser;
import org.mo.eng.template.ITemplateConsole;

//============================================================
// <T>持久化来源。</T>
//============================================================
public class FPersistenceSource
{
   // 模板控制台
   protected ITemplateConsole _templateConsole;

   //============================================================
   // <T>构造持久化来源。</T>
   //============================================================
   public FPersistenceSource(){
      _templateConsole = RAop.find(ITemplateConsole.class);
   }

   //============================================================
   // <T>生成DAO代码。</T>
   //
   // @paam tableNode 表节点
   // @return 字符串文件
   //============================================================
   public FStringFile makeDao(FXmlNode tableNode){
      FXmlNode root = _templateConsole.findTemplate("eng.persistence", "dao");
      FTemplateParser parser = new FTemplateParser(root);
      parser.define("table", tableNode);
      parser.define("fields", tableNode.nodes());
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   public FStringFile makeIFace(FXmlNode tableNode){
      FXmlNode root = _templateConsole.findTemplate("mysql.java", "iface");
      FTemplateParser parser = new FTemplateParser(root);
      parser.define("table", tableNode);
      parser.define("fields", tableNode.nodes());
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   public FStringFile makeIBase(FXmlNode tableNode){
      FXmlNode root = _templateConsole.findTemplate("mysql.java", "ibase");
      FTemplateParser parser = new FTemplateParser(root);
      parser.define("table", tableNode);
      parser.define("fields", tableNode.nodes());
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   public FStringFile makeLogic(FXmlNode tableNode){
      FXmlNode root = _templateConsole.findTemplate("mysql.java", "logic");
      FTemplateParser parser = new FTemplateParser(root);
      parser.define("table", tableNode);
      parser.define("fields", tableNode.nodes());
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   public FStringFile makeUnit(FXmlNode tableNode){
      FXmlNode root = _templateConsole.findTemplate("mysql.java", "unit");
      FTemplateParser parser = new FTemplateParser(root);
      parser.define("table", tableNode);
      parser.define("fields", tableNode.nodes());
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }
}
