package org.mo.game.editor.core.module;

import org.mo.com.io.FStringFile;
import org.mo.com.xml.FXmlNode;
import org.mo.eng.template.ITemplateConsole;
import org.mo.eng.template.ITemplateParser;

public class FModuleSourceBuilder{

   private final ITemplateConsole _templateConsole;

   public final static String TPL_SHARED_CPP_HEAD = "game/editor/module/shared-cpp-head";

   public final static String TPL_SHARED_CPP_BODY = "game/editor/module/shared-cpp-body";

   public final static String TPL_MODULE_CPP_HEAD = "game/editor/module/module-cpp-head";

   public final static String TPL_MODULE_CPP_ENTITY = "game/editor/module/module-cpp-entity";

   public final static String TPL_MODULE_CPP_MODULE = "game/editor/module/module-cpp-module";

   //============================================================
   public FModuleSourceBuilder(ITemplateConsole templateConsole){
      _templateConsole = templateConsole;
   }

   //============================================================
   public FStringFile makeModuleCppEntity(FXmlNode entityGroup,
                                          FXmlNode entity){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(TPL_MODULE_CPP_ENTITY);
      // Define
      parser.define("project", entityGroup);
      parser.define("module", entity);
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   //============================================================
   public FStringFile makeModuleCppHead(FXmlNode entityGroup,
                                        FXmlNode entity){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(TPL_MODULE_CPP_HEAD);
      // Define
      parser.define("project", entityGroup);
      parser.define("module", entity);
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   //============================================================
   public FStringFile makeModuleCppModule(FXmlNode entityGroup,
                                          FXmlNode entity){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(TPL_MODULE_CPP_MODULE);
      // Define
      parser.define("project", entityGroup);
      parser.define("module", entity);
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   //============================================================
   public FStringFile makeSharedCppBody(FXmlNode entityGroup){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(TPL_SHARED_CPP_BODY);
      // Define
      parser.define("project", entityGroup);
      // Build 
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   //============================================================
   public FStringFile makeSharedCppHead(FXmlNode entityGroup){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(TPL_SHARED_CPP_HEAD);
      // Define
      parser.define("project", entityGroup);
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }
}
