package org.mo.game.editor.core.instance;

import org.mo.com.io.FStringFile;
import org.mo.com.xml.FXmlNode;
import org.mo.eng.template.ITemplateConsole;
import org.mo.eng.template.ITemplateParser;
import org.mo.game.editor.common.source.REditorAsSource;
import org.mo.game.editor.common.source.REditorCsSource;

public class FInstanceSourceBuilder
{
   private final ITemplateConsole _templateConsole;

   public final static String TPL_CPP_INCLUDE = "game/editor/instance/cpp-include";

   public final static String TPL_CPP_HEAD = "game/editor/instance/cpp-head";

   public final static String TPL_CPP_BODY = "game/editor/instance/cpp-body";

   public final static String TPL_CS_ENTITY = "game/editor/instance/cs-entity";

   public final static String TPL_AS_ENTITY = "game/editor/instance/as-entity";

   public final static String TPL_JAVA_ENTITY = "game/editor/instance/java-entity";

   //============================================================
   public FInstanceSourceBuilder(ITemplateConsole templateConsole){
      _templateConsole = templateConsole;
   }

   //============================================================
   public FStringFile makeAsInstance(FXmlNode xentity){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(TPL_AS_ENTITY);
      // 设置语言类型
      for(FXmlNode xitem : xentity.allNodes()){
         if(xitem.isName("Item")){
            REditorAsSource.convertNodeType(xitem);
         }
      }
      // Define
      parser.define("entity", xentity);
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   //============================================================
   public FStringFile makeCppBody(FXmlNode xgroup){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(TPL_CPP_BODY);
      // Define
      parser.define("group", xgroup);
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   //============================================================
   public FStringFile makeCppHead(FXmlNode xgroup){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(TPL_CPP_HEAD);
      // Define
      parser.define("group", xgroup);
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   //============================================================
   public FStringFile makeCppInclude(FXmlNode xsource){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(TPL_CPP_INCLUDE);
      // Define
      parser.define("source", xsource);
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   //============================================================
   public FStringFile makeCsInstance(FXmlNode xentity){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(TPL_CS_ENTITY);
      // 设置语言类型
      for(FXmlNode xitem : xentity.allNodes()){
         if(xitem.isName("Item")){
            REditorCsSource.convertNodeType(xitem);
         }
      }
      // Define
      parser.define("entity", xentity);
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   //============================================================
   public FStringFile makeJavaInstance(FXmlNode xentity){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(TPL_JAVA_ENTITY);
      // 设置语言类型
      for(FXmlNode xitem : xentity.allNodes()){
         if(xitem.isName("Item")){
            REditorAsSource.convertNodeType(xitem);
         }
      }
      // Define
      parser.define("entity", xentity);
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }
}
