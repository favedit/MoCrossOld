package org.mo.game.editor.core.dataset.common;

import org.mo.com.data.FSql;
import org.mo.com.io.FStringFile;
import org.mo.com.lang.FString;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.xml.FXmlNode;
import org.mo.core.aop.face.ALink;
import org.mo.eng.data.IDatabaseConsole;
import org.mo.eng.template.FTemplateParser;
import org.mo.eng.template.ITemplateConsole;
import org.mo.eng.template.ITemplateParser;

public class FDatasetSourceBuilder
{

   private final ITemplateConsole _templateConsole;

   @ALink
   protected IDatabaseConsole _databaseConsole;

   public final static String TPL_SQL_TABLE = "game.editor.dataset.sql_table";

   public final static String TPL_CPP_DEFINE = "game.editor.dataset.cpp_define";

   public final static String TPL_CPP_HEAD = "game.editor.dataset.cpp_head";

   public final static String TPL_CPP_BODY = "game.editor.dataset.cpp_body";

   public final static String TPL_JAVA_UNIT = "game.editor.dataset.java_unit";

   public final static String TPL_JAVA_LOGIC = "game.editor.dataset.java_logic";

   public FDatasetSourceBuilder(ITemplateConsole templateConsole){
      _templateConsole = templateConsole;
   }

   public FStringFile makeCppBody(FXmlNode dataset){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(TPL_CPP_BODY);
      // Define
      parser.define("dataset", dataset);
      parser.define("fields", dataset.node("fields").nodes());
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   public FStringFile makeCppDefine(FXmlNode datasets,
                                    String dataGroup,
                                    FString source){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(TPL_CPP_DEFINE);
      // Define
      parser.define("datasets", datasets);
      parser.define("data_group", dataGroup);
      parser.define("source", source);
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   public FStringFile makeCppHead(FXmlNode dataset){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(TPL_CPP_HEAD);
      // Define
      parser.define("dataset", dataset);
      parser.define("fields", dataset.node("fields").nodes());
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
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

   public FStringFile makeSqlTable(FXmlNode dataset){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(TPL_SQL_TABLE);
      // Define
      parser.define("dataset", dataset);
      parser.define("fields", dataset.node("fields").nodes());
      parser.define("keys", dataset.node("keys").nodes());
      parser.define("indexs", dataset.node("indexs").nodes());
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   public FStringFile makeSqlTableDelete(FXmlNode dataset){
      FSql sql = new FSql();
      sql.append("DROP TABLE " + dataset.get("data_name") + "_DS");
      // Build
      FStringFile file = new FStringFile();
      file.append(sql);
      return file;
   }

   private void parseCheckDataType(FXmlNode xpackage){
      for(FXmlNode xmethod : xpackage.syncNode("Methods").nodes()){
         xmethod.set("has_unknown", RBoolean.FALSE_STR);
         if("Unknown".equals(xmethod.syncNode("Return").get("java_type"))){
            xmethod.set("has_unknown", RBoolean.TRUE_STR);
            continue;
         }
         for(FXmlNode paramNode : xmethod.syncNode("Parameters").nodes()){
            if("Unknown".equals(paramNode.get("java_type"))){
               xmethod.set("has_unknown", RBoolean.TRUE_STR);
               break;
            }
         }
      }
   }

   public String parsePackageSql(String sql,
                                 FXmlNode dataset){
      // 替换预定义内容
      String name = RString.toUpper(dataset.get("name"));
      String dataName = RString.toUpper(dataset.get("data_name"));
      sql = RString.replace(sql, "&DF_LOGIC..", name + ".");
      sql = RString.replace(sql, "&DF_LOGIC.", name);
      sql = RString.replace(sql, "&DF_LOGIC", name);
      sql = RString.replace(sql, "&DF_VIEW..", dataName + ".");
      sql = RString.replace(sql, "&DF_VIEW.", dataName);
      sql = RString.replace(sql, "&DF_VIEW", dataName);
      sql = RString.replace(sql, "&DF_DATASET..", dataName + "_DS.");
      sql = RString.replace(sql, "&DF_DATASET.", dataName + "_DS");
      sql = RString.replace(sql, "&DF_DATASET", dataName + "_DS");
      sql = RString.replace(sql, "&DF_PACKAGE..", dataName + "_DI.");
      sql = RString.replace(sql, "&DF_PACKAGE.", dataName + "_DI");
      sql = RString.replace(sql, "&DF_PACKAGE", dataName + "_DI");
      sql = RString.replace(sql, "&DF_SEQUENCE..", dataName + "_SQ.");
      sql = RString.replace(sql, "&DF_SEQUENCE.", dataName + "_SQ");
      sql = RString.replace(sql, "&DF_SEQUENCE", dataName + "_SQ");
      return sql;
   }

   public FStringFile makeJavaUnit(FXmlNode dataset){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(TPL_JAVA_UNIT);
      // Define
      parser.define("dataset", dataset);
      parser.define("fields", dataset.node("fields").nodes());
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   public FStringFile makeJavaLogic(FXmlNode dataset){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(TPL_JAVA_LOGIC);
      // Define
      parser.define("dataset", dataset);
      parser.define("fields", dataset.node("fields").nodes());
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }
}
