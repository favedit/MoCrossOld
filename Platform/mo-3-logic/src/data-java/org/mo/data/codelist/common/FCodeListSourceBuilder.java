package org.mo.data.codelist.common;

import org.mo.com.io.FStringFile;
import org.mo.com.lang.RString;
import org.mo.com.xml.FXmlNode;
import org.mo.data.procedure.common.FSqlPackageParser;
import org.mo.eng.template.ITemplateConsole;
import org.mo.eng.template.ITemplateParser;

public class FCodeListSourceBuilder
{
   private final ITemplateConsole _templateConsole;

   public final static String TPL_PACKAGE_HEAD = "database.codelist.package.head";

   public final static String TPL_PACKAGE_BODY = "database.codelist.package.body";

   public FCodeListSourceBuilder(ITemplateConsole templateConsole){
      _templateConsole = templateConsole;
   }

   public FStringFile makePackageBody(FXmlNode dataset){
      ITemplateParser parser = _templateConsole.findParser(TPL_PACKAGE_BODY);
      // Define
      parser.define("codelist", dataset);
      parser.define("codes", dataset.nodes());
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
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
      ITemplateParser parser = _templateConsole.findParser(TPL_PACKAGE_HEAD);
      // Define
      parser.define("codelist", dataset);
      parser.define("codes", dataset.nodes());
      // Build
      FStringFile file = new FStringFile();
      file.append(parser.parse());
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

   private String parsePackageSql(String sql,
                                  FXmlNode dataset){
      // 替换预定义内容
      String name = RString.toUpper(dataset.get("name"));
      String dataName = RString.toUpper(dataset.get("data_name"));
      sql = RString.replace(sql, "&DF_LOGIC..", name + ".");
      sql = RString.replace(sql, "&DF_LOGIC.", name);
      sql = RString.replace(sql, "&DF_LOGIC", name);
      sql = RString.replace(sql, "&DF_PACKAGE..", dataName + "_EI.");
      sql = RString.replace(sql, "&DF_PACKAGE.", dataName + "_EI");
      sql = RString.replace(sql, "&DF_PACKAGE", dataName + "_EI");
      return sql;
   }
}
