package org.mo.data.dataset.common;

import org.mo.com.data.FSql;
import org.mo.com.io.FStringFile;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;
import org.mo.com.xml.FXmlNode;
import org.mo.core.aop.face.ALink;
import org.mo.data.procedure.common.FSqlPackageParser;
import org.mo.eng.data.IDatabaseConsole;
import org.mo.eng.template.FTemplateParser;
import org.mo.eng.template.ITemplateConsole;
import org.mo.eng.template.ITemplateParser;

public class FDatasetSourceBuilder
{
   private final ITemplateConsole _templateConsole;

   //private static IResource _resource = RResource.find(FDatasetSourceBuilder.class);
   @ALink
   protected IDatabaseConsole _databaseConsole;

   public final static String TYPE_ORACLE = "database/logicunit/sqlTable";

   public final static String TYPE_ORACLE_WORK = "database/logicunit/work/sqlTable";

   public final static String TYPE_ORACLE_MEMORY = "database/logicunit/memory/sqlTable";

   public final static String TYPE_ORACLE_CACHE = "database/logicunit/cache/sqlTable";

   public final static String ITEM_SQL_TABLE = "database.logicunit.sqlTable";

   public final static String ITEM_SQL_VIEW = "database.logicunit.sqlView";

   public final static String ITEM_C_DEFINE = "database.logicunit.sourceDefine";

   public final static String ITEM_C_IMPLEMENT = "database.logicunit.sourceImplement";

   public final static String ITEM_SQL_VIEW_VIEW = "database.logicunit.view.sqlView";

   public final static String ITEM_SQL_VIEW_PROPERTY = "database.logicunit.property.sqlView";

   public final static String ITEM_PACKAGE_HEAD = "database.logicunit.sqlHead";

   public final static String ITEM_PACKAGE_PROPERTY_HEAD = "database.logicunit.property.sqlHead";

   public final static String ITEM_PACKAGE_BODY = "database.logicunit.sqlBody";

   public final static String ITEM_PROPERTY_PACKAGE_BODY = "database.logicunit.property.sqlBody";

   public final static String ITEM_SQL_LIST = "database.logicunit.sqlSequence";

   public final static String HIS_SQL_TABLE = "database.logicunit.history.sqlTable";

   public final static String HIS_SQL_VIEW = "database.logicunit.history.sqlView";

   public final static String HIS_PACKAGE_HEAD = "database.logicunit.history.sqlHead";

   public final static String HIS_PACKAGE_BODY = "database.logicunit.history.sqlBody";

   public final static String HIS_SQL_LIST = "database.logicunit.history.sqlSequence";

   public FDatasetSourceBuilder(ITemplateConsole templateConsole){
      _templateConsole = templateConsole;
   }

   /**
    * <T>增加字段</T>
    */
   public FStringFile addField(FXmlNode dataset){
      /// 检查字段处理
      /// 增加字段处理
      ///  alter table 表名 add(字段名称 type（size） default 默认值)
      FSql sql = new FSql();
      sql.append("ALTER TABLE " + dataset.get("data_name") + "_DS ADD(" + "字段名称" + " TYPE(" + "长度" + ")");
      // Build
      FStringFile file = new FStringFile();
      file.append(sql);
      return file;
   }

   /**
    * <T>增加主键</T>
    */
   public FStringFile addPrimaryKey(FXmlNode dataset){
      return null;
   }

   /**
    * <T>检查字段类型</T>
    */
   public FStringFile checkFieldDataType(FXmlNode dataset){
      return null;
   }

   /**
    * <T>检查表字段</T>
    */
   public FStringFile checkFieldName(FXmlNode dataset){
      return null;
   }

   /**
    * <T>检查字段大小</T>
    */
   public FStringFile checkFieldSize(FXmlNode dataset){
      return null;
   }

   /**
    * <T>检查索引</T>
    */
   public FStringFile checkIndex(FXmlNode dataset){
      return null;
   }

   /**
    * <T>检查主键</T>
    */
   public FStringFile checkPrimaryKey(FXmlNode dataset){
      return null;
   }

   /**
    * <T>检查唯一键</T>
    */
   public FStringFile checkUniquekey(FXmlNode dataset){
      return null;
   }

   /**
    * <T>创建索引</T>
    */
   public FStringFile createIndex(FXmlNode dataset){
      return null;
   }

   /**
    * <T>删除字段</T>
    */
   public FStringFile dropField(FXmlNode dataset){
      return null;
   }

   /**
    * <T>删除索引</T>
    */
   public FStringFile dropIndex(FXmlNode dataset){
      return null;
   }

   /**
    * <T>删除主键</T>
    */
   public FStringFile dropPrimaryKey(FXmlNode dataset){
      return null;
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

   public FStringFile makeDefineC(FXmlNode dataset){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(ITEM_C_DEFINE);
      // Define
      parser.define("dataset", dataset);
      parser.define("fields", dataset.node("fields").nodes());
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

   /**
    * <p>导入历史包体模板生成历史包体</p>
    * @param dataset要生成包体的xml节点
    *            
    * @return 生成的包体
    */
   public FStringFile makeHsPackageBody(FXmlNode dataset){
      // 导入模板
      ITemplateParser parser = _templateConsole.getParser(HIS_PACKAGE_BODY);
      // Define
      parser.define("dataset", dataset);
      parser.define("fields", dataset.node("fields").nodes());
      parser.define("keys", dataset.node("keys").nodes());
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   public FStringFile makeHsPackageBodyDelete(FXmlNode dataset){
      FSql sql = new FSql();
      sql.append("DROP PACKAGE BODY " + dataset.get("data_name") + "_HI");
      // Build
      FStringFile file = new FStringFile();
      file.append(sql);
      return file;
   }

   public FStringFile makeHsPackageBodySql(FXmlNode dataset){
      // 获得生成后的代码
      FStringFile source = makeHsPackageBody(dataset);
      // 获得可执行的PLSQL代码
      String sql = RString.mid(source.toString(), FSqlPackageParser.PACKAGE_BEGIN, FSqlPackageParser.PACKAGE_END);
      // 替换预定义内容
      sql = parseHsPackageSql(sql, dataset);
      // 返回结果
      source.clear();
      source.append(sql);
      return source;
   }

   /**
    * <p>导入历史包头模板生成历史包头</p>
    * 
    * @param dataset 要生成包头的xml节点
    *           
    * @return 生成的包头
    */
   public FStringFile makeHsPackageHead(FXmlNode dataset){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(HIS_PACKAGE_HEAD);
      // Define
      parser.define("dataset", dataset);
      parser.define("fields", dataset.node("fields").nodes());
      parser.define("keys", dataset.node("keys").nodes());
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   public FStringFile makeHsPackageHeadDelete(FXmlNode dataset){
      FSql sql = new FSql();
      sql.append("DROP PACKAGE " + dataset.get("data_name") + "_HI");
      // Build
      FStringFile file = new FStringFile();
      file.append(sql);
      return file;
   }

   public FStringFile makeHsPackageHeadSql(FXmlNode dataset){
      // 获得生成后的代码
      FStringFile source = makeHsPackageHead(dataset);
      // 获得可执行的PLSQL代码
      String sql = RString.mid(source.toString(), FSqlPackageParser.PACKAGE_BEGIN, FSqlPackageParser.PACKAGE_END);
      // 替换预定义内容
      sql = parseHsPackageSql(sql, dataset);
      // 返回结果
      source.clear();
      source.append(sql);
      return source;
   }

   /**
    * <p>导入历史序列模板生成历史序列</p>
    *  
    * @param dataset 要生成序列的xml节点
    * @return 生成的序列
    */
   public FStringFile makeHsSqlSequence(FXmlNode dataset){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(HIS_SQL_LIST);
      // Define
      parser.define("dataset", dataset);
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   public FStringFile makeHsSqlSequenceDelete(FXmlNode dataset){
      FSql sql = new FSql();
      sql.append("DROP SEQUENCE " + dataset.get("data_name") + "_HQ");
      // Build
      FStringFile file = new FStringFile();
      file.append(sql);
      return file;
   }

   /**
    * <p>
    * 导入历史表模板生成历史表
    * </p>
    * 
    * @param dataset
    *            要生成表的xml节点
    * @return 生成的表
    */
   public FStringFile makeHsSqlTable(FXmlNode dataset){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(HIS_SQL_TABLE);
      // Define
      parser.define("dataset", dataset);
      parser.define("fields", dataset.node("fields").nodes());
      parser.define("keys", dataset.node("keys").nodes());
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   public FStringFile makeHsSqlTableDelete(FXmlNode dataset){
      FSql sql = new FSql();
      sql.append("DROP TABLE " + dataset.get("data_name") + "_HS");
      // Build
      FStringFile file = new FStringFile();
      file.append(sql);
      return file;
   }

   /**
    * <p>导入历史视图模板生成历史视图</p>
    * 
    * @param dataset要生成视图的xml节点
    * @return 生成的视图
    */
   public FStringFile makeHsSqlView(FXmlNode dataset){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(HIS_SQL_VIEW);
      // Define
      parser.define("dataset", dataset);
      parser.define("fields", dataset.node("fields").nodes());
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   public FStringFile makeHsSqlViewDelete(FXmlNode dataset){
      FSql sql = new FSql();
      sql.append("DROP VIEW " + dataset.get("data_name") + "_HV");
      // Build
      FStringFile file = new FStringFile();
      file.append(sql);
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

   public FStringFile makeImplementC(FXmlNode dataset){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(ITEM_C_IMPLEMENT);
      // Define
      parser.define("dataset", dataset);
      parser.define("fields", dataset.node("fields").nodes());
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   // 生成包
   public FStringFile makeJavaPackage(FXmlNode xpackage){
      // 查找模板
      ITemplateParser parser = _templateConsole.findParser("database.logicunit.javaClass");
      parseCheckDataType(xpackage);
      // Define
      parser.define("package", xpackage);
      parser.define("properties", xpackage.findNode("Properties"));
      parser.define("methods", xpackage.findNode("Methods"));
      // Build
      String lines = parser.parse().toString();
      String[] line = lines.split("\n");
      RString.trimRight(line);
      FStringFile file = new FStringFile();
      for(int i = 0; i < line.length; ++i){
         file.append(line[i]);
         file.append("\n");
      }
      return file;
   }

   // 生成face包
   public FStringFile makeJavaPackageFace(FXmlNode xpackage){
      ITemplateParser parser = _templateConsole.findParser("database.logicunit.javaFace");
      parseCheckDataType(xpackage);
      // Define
      parser.define("package", xpackage);
      parser.define("properties", xpackage.findNode("Properties"));
      parser.define("methods", xpackage.findNode("Methods"));
      // Build
      String lines = parser.parse().toString();
      String[] line = lines.split("\n");
      RString.trimRight(line);
      FStringFile file = new FStringFile();
      for(int i = 0; i < line.length; ++i){
         file.append(line[i]);
         file.append("\n");
      }
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

   public FStringFile makePackageBody(FXmlNode dataset){
      // 导入模板
      ITemplateParser parser = _templateConsole.getParser(ITEM_PACKAGE_BODY);
      // Define
      parser.define("dataset", dataset);
      parser.define("fields", dataset.node("fields").nodes());
      parser.define("keys", dataset.node("keys").nodes());
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   public FStringFile makePackageBodyDelete(FXmlNode dataset){
      FSql sql = new FSql();
      sql.append("DROP PACKAGE BODY " + dataset.get("data_name") + "_DI");
      // Build
      FStringFile file = new FStringFile();
      file.append(sql);
      return file;
   }

   public FStringFile makePackageBodySql(FXmlNode dataset){
      // 获得生成后的代码
      FStringFile source = makePackageBody(dataset);
      // 获得可执行的PLSQL代码
      String sql = RString.mid(source.toString(), FSqlPackageParser.PACKAGE_BEGIN, FSqlPackageParser.PACKAGE_END);
      // 替换预定义内容
      sql = parsePackageSql(sql, dataset);
      // 返回结果
      source.clear();
      source.append(sql);
      return source;
   }

   public FStringFile makePackageHead(FXmlNode dataset){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(ITEM_PACKAGE_HEAD);
      // Define
      parser.define("dataset", dataset);
      parser.define("fields", dataset.node("fields").nodes());
      parser.define("keys", dataset.node("keys").nodes());
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   public FStringFile makePackageHeadDelete(FXmlNode dataset){
      FSql sql = new FSql();
      sql.append("DROP PACKAGE " + dataset.get("data_name") + "_DI");
      // Build
      FStringFile file = new FStringFile();
      file.append(sql);
      return file;
   }

   public FStringFile makePackageHeadSql(FXmlNode dataset){
      // 获得生成后的代码
      FStringFile source = makePackageHead(dataset);
      // 获得可执行的PLSQL代码
      String sql = RString.mid(source.toString(), FSqlPackageParser.PACKAGE_BEGIN, FSqlPackageParser.PACKAGE_END);
      // 替换预定义内容
      sql = parsePackageSql(sql, dataset);
      // 返回结果
      source.clear();
      source.append(sql);
      return source;
   }

   public FStringFile makePackagePropertyBody(FXmlNode dataset){
      // 导入模板
      ITemplateParser parser = _templateConsole.getParser(ITEM_PROPERTY_PACKAGE_BODY);
      // Define
      parser.define("dataset", dataset);
      parser.define("fields", dataset.node("fields").nodes());
      parser.define("keys", dataset.node("keys").nodes());
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   public FStringFile makePackagePropertyBodyDelete(FXmlNode dataset){
      FSql sql = new FSql();
      sql.append("DROP PACKAGE BODY " + dataset.get("data_name") + "_DI");
      // Build
      FStringFile file = new FStringFile();
      file.append(sql);
      return file;
   }

   public FStringFile makePackagePropertyBodySql(FXmlNode dataset){
      // 获得生成后的代码
      FStringFile source = makePackagePropertyBody(dataset);
      // 获得可执行的PLSQL代码
      String sql = RString.mid(source.toString(), FSqlPackageParser.PACKAGE_BEGIN, FSqlPackageParser.PACKAGE_END);
      // 替换预定义内容
      sql = parsePackageSql(sql, dataset);
      // 返回结果
      source.clear();
      source.append(sql);
      return source;
   }

   public FStringFile makePackagePropertyHead(FXmlNode dataset){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(ITEM_PACKAGE_PROPERTY_HEAD);
      // Define
      parser.define("dataset", dataset);
      parser.define("fields", dataset.node("fields").nodes());
      parser.define("keys", dataset.node("keys").nodes());
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   public FStringFile makePackagePropertyHeadDelete(FXmlNode dataset){
      FSql sql = new FSql();
      sql.append("DROP PACKAGE " + dataset.get("data_name") + "_DI");
      // Build
      FStringFile file = new FStringFile();
      file.append(sql);
      return file;
   }

   public FStringFile makePackagePropertyHeadSql(FXmlNode dataset){
      // 获得生成后的代码
      FStringFile source = makePackagePropertyHead(dataset);
      // 获得可执行的PLSQL代码
      String sql = RString.mid(source.toString(), FSqlPackageParser.PACKAGE_BEGIN, FSqlPackageParser.PACKAGE_END);
      // 替换预定义内容
      sql = parsePackageSql(sql, dataset);
      // 返回结果
      source.clear();
      source.append(sql);
      return source;
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

   // 生成SQL序列
   public FStringFile makeSqlSequence(FXmlNode dataset){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(ITEM_SQL_LIST);
      // Define
      parser.define("dataset", dataset);
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   public FStringFile makeSqlSequenceDelete(FXmlNode dataset){
      FSql sql = new FSql();
      sql.append("DROP SEQUENCE " + dataset.get("data_name") + "_SQ");
      // Build
      FStringFile file = new FStringFile();
      file.append(sql);
      return file;
   }

   public FStringFile makeSqlTable(FXmlNode dataset){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(TYPE_ORACLE);
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

   public FStringFile makeSqlTableCache(FXmlNode dataset){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(TYPE_ORACLE_CACHE);
      // Define
      parser.define("dataset", dataset);
      parser.define("fields", dataset.node("fields").nodes());
      parser.define("keys", dataset.node("keys").nodes());
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

   public FStringFile makeSqlTableMemory(FXmlNode dataset){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(TYPE_ORACLE_MEMORY);
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

   public FStringFile makeSqlTableWork(FXmlNode dataset){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(TYPE_ORACLE_WORK);
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

   public FStringFile makeSqlView(FXmlNode dataset){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(ITEM_SQL_VIEW);
      // Define
      parser.define("dataset", dataset);
      parser.define("fields", dataset.node("fields").nodes());
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   public FStringFile makeSqlViewDelete(FXmlNode dataset){
      FSql sql = new FSql();
      sql.append("DROP VIEW " + dataset.get("data_name"));
      // Build
      FStringFile file = new FStringFile();
      file.append(sql);
      return file;
   }

   public FStringFile makeSqlViewProperty(FXmlNode dataset){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(ITEM_SQL_VIEW_PROPERTY);
      // Define
      parser.define("dataset", dataset);
      parser.define("fields", dataset.node("fields").nodes());
      // Build
      String lines = parser.parse().toString();
      String[] line = lines.split("\n");
      RString.trimRight(line);
      FStringFile file = new FStringFile();
      for(int i = 0; i < line.length; ++i){
         file.append(line[i]);
         file.append("\n");
      }
      return file;
   }

   public FStringFile makeSqlViewView(FXmlNode dataset){
      // 导入模板
      ITemplateParser parser = _templateConsole.findParser(ITEM_SQL_VIEW_VIEW);
      // Define
      parser.define("dataset", dataset);
      parser.define("fields", dataset.node("fields").nodes());
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
      return file;
   }

   /**
    * <T>修改字段缺省值</T>
    */
   public FStringFile modifyFieldDefalutValue(FXmlNode dataset){
      return null;
   }

   /**
    * <T>修改字段非空</T>
    */
   public FStringFile modifyFieldEmpty(FXmlNode dataset){
      return null;
   }

   /**
    * <T>修改字段</T>
    */
   public FStringFile modifyFieldName(FXmlNode dataset){
      return null;
   }

   /**
    * <T>修改字段大小</T>
    */
   public FStringFile modifyFieldSize(FXmlNode dataset){
      return null;
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

   private String parseHsPackageSql(String sql,
                                    FXmlNode dataset){
      // 替换预定义内容
      String name = RString.toUpper(dataset.get("name"));
      String dataName = RString.toUpper(dataset.get("data_name"));
      sql = RString.replace(sql, "&DF_LOGIC..", name + ".");
      sql = RString.replace(sql, "&DF_LOGIC.", name);
      sql = RString.replace(sql, "&DF_LOGIC", name);
      sql = RString.replace(sql, "&DF_VIEW_OLD..", dataName + ".");
      sql = RString.replace(sql, "&DF_VIEW_OLD.", dataName);
      sql = RString.replace(sql, "&DF_VIEW_OLD", dataName);
      sql = RString.replace(sql, "&DF_VIEW..", dataName + "_HV.");
      sql = RString.replace(sql, "&DF_VIEW.", dataName + "_HV");
      sql = RString.replace(sql, "&DF_VIEW", dataName + "_HV");
      sql = RString.replace(sql, "&DF_DATASET..", dataName + "_HS.");
      sql = RString.replace(sql, "&DF_DATASET.", dataName + "_HS");
      sql = RString.replace(sql, "&DF_DATASET", dataName + "_HS");
      sql = RString.replace(sql, "&DF_PACKAGE..", dataName + "_HI.");
      sql = RString.replace(sql, "&DF_PACKAGE.", dataName + "_HI");
      sql = RString.replace(sql, "&DF_PACKAGE", dataName + "_HI");
      sql = RString.replace(sql, "&DF_SEQUENCE..", dataName + "_HQ.");
      sql = RString.replace(sql, "&DF_SEQUENCE.", dataName + "_HQ");
      sql = RString.replace(sql, "&DF_SEQUENCE", dataName + "_HQ");
      return sql;
   }

   private String parsePackageSql(String sql,
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
}
