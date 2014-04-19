package org.mo.game.editor.core.processor;

import org.mo.com.io.FStringFile;
import org.mo.com.xml.FXmlNode;
import org.mo.eng.template.ITemplateConsole;
import org.mo.eng.template.ITemplateParser;

public class FProcessorSourceBuilder{

   private final ITemplateConsole _templateConsole;

   public final static String HEAD_TEMPLATE = "game/editor/processor/head";

   public final static String BODY_TEMPLATE = "game/editor/processor/body";

   //============================================================
   public FProcessorSourceBuilder(ITemplateConsole templateConsole){
      _templateConsole = templateConsole;
   }

   //============================================================
   public FStringFile makeCppBody(FXmlNode project,
                                  FXmlNode config){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(BODY_TEMPLATE);
      // Define
      parser.define("project", project);
      parser.define("processor", config);
      parser.define("config", config);
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   //============================================================
   public FStringFile makeCppHead(FXmlNode project,
                                  FXmlNode config){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(HEAD_TEMPLATE);
      // Define
      parser.define("project", project);
      parser.define("processor", config);
      parser.define("config", config);
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }
}
