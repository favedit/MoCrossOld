package org.mo.game.editor.core.message;

import org.mo.com.io.FStringFile;
import org.mo.com.xml.FXmlNode;
import org.mo.eng.template.ITemplateConsole;
import org.mo.eng.template.ITemplateParser;
import org.mo.game.editor.common.source.REditorCsSource;

public class FMessageSourceBuilder{

   private final ITemplateConsole _templateConsole;

   public final static String TPL_CPP_INCLUDE_HEAD = "game.editor.message.cpp-include-head";

   public final static String TPL_CPP_INCLUDE_BODY = "game.editor.message.cpp-include-body";

   public final static String TPL_CPP_HEAD = "game.editor.message.cpp-head";

   public final static String TPL_CPP_BODY = "game.editor.message.cpp-body";

   public final static String TPL_AS_CONSOLE = "game.editor.message.as-console";

   public final static String TPL_AS_PROVIDER = "game.editor.message.as-provider";

   public final static String TPL_AS_GROUP = "game.editor.message.as-group";

   public final static String TPL_AS_MESSAGE = "game.editor.message.as-message";

   public final static String TPL_AS_STRUCT = "game.editor.message.as-struct";

   public final static String TPL_JAVA_CONSOLE = "game.editor.message.java-console";

   public final static String TPL_JAVA_PROVIDER = "game.editor.message.java-provider";

   public final static String TPL_JAVA_GROUP = "game.editor.message.java-group";

   public final static String TPL_JAVA_MESSAGE = "game.editor.message.java-message";

   public final static String TPL_JAVA_STRUCT = "game.editor.message.java-struct";

   public final static String TPL_CS_MESSAGE = "game.editor.message.cs-message";

   //============================================================
   public FMessageSourceBuilder(ITemplateConsole templateConsole){
      _templateConsole = templateConsole;
   }

   //============================================================
   public FStringFile makeAllAsProviderSource(FXmlNode config){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(TPL_AS_PROVIDER);
      // Define
      parser.define("config", config);
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   //============================================================
   public FStringFile makeAllAsSource(FXmlNode config){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(TPL_AS_CONSOLE);
      // Define
      parser.define("config", config);
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   //============================================================
   public FStringFile makeAllJavaProviderSource(FXmlNode config){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(TPL_JAVA_PROVIDER);
      // Define
      parser.define("config", config);
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   //============================================================
   public FStringFile makeAllJavaSource(FXmlNode config){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(TPL_JAVA_CONSOLE);
      // Define
      parser.define("config", config);
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   //============================================================
   public FStringFile makeAsGroupSource(FXmlNode group){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(TPL_AS_GROUP);
      // Define
      parser.define("group", group);
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   //============================================================
   public FStringFile makeAsMessageSource(FXmlNode group,
                                          FXmlNode message){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(TPL_AS_MESSAGE);
      // Define
      parser.define("group", group);
      parser.define("message", message);
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   //============================================================
   public FStringFile makeAsStructSource(FXmlNode group,
                                         FXmlNode message,
                                         FXmlNode struct){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(TPL_AS_STRUCT);
      // Define
      parser.define("group", group);
      parser.define("message", message);
      parser.define("struct", struct);
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   //============================================================
   public FStringFile makeCppBody(FXmlNode messageGroup){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(TPL_CPP_BODY);
      // Define
      parser.define("group", messageGroup);
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   //============================================================
   public FStringFile makeCppHead(FXmlNode messageGroup){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(TPL_CPP_HEAD);
      // Define
      parser.define("group", messageGroup);
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   //============================================================
   public FStringFile makeCppIncludeBody(FXmlNode config){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(TPL_CPP_INCLUDE_BODY);
      // Define
      parser.define("config", config);
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   //============================================================
   public FStringFile makeCppIncludeHead(FXmlNode config){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(TPL_CPP_INCLUDE_HEAD);
      // Define
      parser.define("config", config);
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   //============================================================
   public FStringFile makeCsMessageSource(FXmlNode group,
                                          FXmlNode message){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(TPL_CS_MESSAGE);
      // 设置语言类型
      for(FXmlNode item : message.allNodes()){
         if(item.isName("Item")){
            REditorCsSource.convertNodeType(item);
         }
      }
      // Define
      parser.define("group", group);
      parser.define("message", message);
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   //============================================================
   public FStringFile makeJavaGroupSource(FXmlNode group){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(TPL_JAVA_GROUP);
      // Define
      parser.define("group", group);
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   //============================================================
   public FStringFile makeJavaMessageSource(FXmlNode group,
                                            FXmlNode message){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(TPL_JAVA_MESSAGE);
      // Define
      parser.define("group", group);
      parser.define("message", message);
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   //============================================================
   public FStringFile makeJavaStructSource(FXmlNode group,
                                           FXmlNode message,
                                           FXmlNode struct){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(TPL_JAVA_STRUCT);
      // Define
      parser.define("group", group);
      parser.define("message", message);
      parser.define("struct", struct);
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }
}
