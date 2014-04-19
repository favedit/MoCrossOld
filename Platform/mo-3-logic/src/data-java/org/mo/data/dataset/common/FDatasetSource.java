package org.mo.data.dataset.common;

import org.mo.com.io.FStringFile;
import org.mo.com.xml.FXmlNode;
import org.mo.eng.template.FTemplateParser;
import org.mo.eng.template.ITemplateConsole;

public class FDatasetSource
{
   private ITemplateConsole _templateConsole;

   public FDatasetSource(ITemplateConsole templateConsole){
      _templateConsole = templateConsole;
   }

   public FStringFile makeDao(FXmlNode dataset){
      FXmlNode root = _templateConsole.findTemplate("mysql.java", "dao");
      FTemplateParser parser = new FTemplateParser(root);
      // Define
      parser.define("dataset", dataset);
      parser.define("fields", dataset.nodes());
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   public FStringFile makeEntity(FXmlNode dataset){
      FXmlNode root = _templateConsole.findTemplate("mysql.java", "entity");
      FTemplateParser parser = new FTemplateParser(root);
      // Define
      parser.define("dataset", dataset);
      parser.define("fields", dataset.nodes());
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   public FStringFile makeIBase(FXmlNode dataset){
      FXmlNode root = _templateConsole.findTemplate("mysql.java", "ibase");
      FTemplateParser parser = new FTemplateParser(root);
      // Define
      parser.define("dataset", dataset);
      parser.define("fields", dataset.nodes());
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   public FStringFile makeIFace(FXmlNode dataset){
      FXmlNode root = _templateConsole.findTemplate("mysql.java", "iface");
      FTemplateParser parser = new FTemplateParser(root);
      // Define
      parser.define("dataset", dataset);
      parser.define("fields", dataset.nodes());
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   public FStringFile makeLogic(FXmlNode dataset){
      FXmlNode root = _templateConsole.findTemplate("mysql.java", "logic");
      FTemplateParser parser = new FTemplateParser(root);
      // Define
      parser.define("dataset", dataset);
      parser.define("fields", dataset.nodes());
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   public FStringFile makeRow(FXmlNode dataset){
      FXmlNode root = _templateConsole.findTemplate("mysql.java", "row");
      FTemplateParser parser = new FTemplateParser(root);
      // Define
      parser.define("dataset", dataset);
      parser.define("fields", dataset.nodes());
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }
}
