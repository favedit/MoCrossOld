package org.mo.game.editor.core.storage;

import org.mo.com.io.FStringFile;
import org.mo.com.io.RFile;
import org.mo.com.xml.FXmlNode;
import org.mo.eng.template.ITemplateConsole;
import org.mo.eng.template.ITemplateParser;

public class FStorageSourceBuilder{

   private final ITemplateConsole _templateConsole;

   public final static String HEAD_TEMPLATE = "game/editor/storage/head";

   public final static String BODY_TEMPLATE = "game/editor/storage/body";

   public FStorageSourceBuilder(ITemplateConsole templateConsole){
      _templateConsole = templateConsole;
   }

   public FStringFile makeCBody(FXmlNode config){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(BODY_TEMPLATE);
      // Define
      parser.define("group", config);
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   public FStringFile makeCHead(FXmlNode entityGroup){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(HEAD_TEMPLATE);
      // Define
      parser.define("group", entityGroup);
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   public FStringFile makecSource(String source,
                                  String templatePath,
                                  String define){
      String filename = RFile.format(templatePath);
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(filename);
      // Define
      parser.define(define, source);
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }
}
