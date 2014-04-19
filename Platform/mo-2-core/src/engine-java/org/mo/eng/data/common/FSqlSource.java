package org.mo.eng.data.common;

import org.mo.com.collections.FAttributesList;
import org.mo.com.data.ISqlConnect;
import org.mo.com.data.MSqlConnect;
import org.mo.com.io.FStringFile;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FString;
import org.mo.com.lang.RString;
import org.mo.com.text.RDatabaseFormat;
import org.mo.core.aop.RAop;
import org.mo.eng.template.ITemplateConsole;
import org.mo.eng.template.ITemplateParser;

public class FSqlSource
      extends MSqlConnect
{
   public final static String DEFINE_BEGIN = "-------------------- Define Begin --------------------------";

   public final static String DEFINE_END = "-------------------- Define End ----------------------------";

   public final static String HISTORY_BEGIN = "-------------------- History Begin -------------------------";

   public final static String HISTORY_END = "-------------------- History End ---------------------------";

   protected FAttributes _item = null;

   protected FAttributesList _list = null;

   private String _packageName = null;

   private String _sequenceName = null;

   private String _tableName = null;

   private String _viewName = null;

   public FSqlSource(ISqlConnect connect,
                     String tableName){
      super(connect);
      onBeforeFetchInfo(tableName);
      fetchInfo();
      onAfterFetchInfo();
   }

   protected void fetchInfo(){
      FSqlTableInfo ti = new FSqlTableInfo(this, _tableName);
      _item = ti.fetchTableInfo();
      _list = ti.fetchFieldsInfo();
   }

   protected ITemplateParser findParser(String path,
                                        String name){
      ITemplateConsole tc = RAop.find(ITemplateConsole.class);
      return tc.findParser(path, name);
   }

   protected FString innerSqlCmd(FString command,
                                 String begin,
                                 String end){
      if(command.isEmpty()){
         return new FString();
      }
      String sql = command.toString();
      int nStart = sql.indexOf(begin);
      int nEnd = sql.indexOf(end);
      if(nStart == -1 || nEnd == -1){
         throw new FFatalError("Start or end position is not found. [{0}/{1}]", nStart, nEnd);
      }
      sql = sql.substring(nStart + begin.length(), nEnd);
      sql = sql.trim();
      return new FString(sql);
   }

   public FAttributesList list(){
      return _list;
   }

   public FString makeDaoSource(){
      ITemplateParser parser = findParser("database.oracle", "java.dao");
      //parser.setItem(_item);
      //parser.setList(_list);
      return parser.parse();
   }

   public FString makeDatasetInterfaceSource(){
      ITemplateParser parser = findParser("database.oracle", "java.interface");
      //parser.setItem(_item);
      //parser.setList(_list);
      return parser.parse();
   }

   public FString makeDatasetSource(){
      ITemplateParser parser = findParser("database.oracle", "java.database");
      //parser.setItem(_item);
      //parser.setList(_list);
      return parser.parse();
   }

   public FString makeLuSource(){
      ITemplateParser parser = findParser("database.oracle", "java.logic");
      //parser.setItem(_item);
      //parser.setList(_list);
      return parser.parse();
   }

   public FString makePackageBodySource(){
      FSqlPackageParser pkgParse = new FSqlPackageParser(this, _packageName);
      FString sPkgSqlCmd = pkgParse.fetchBodyString();
      // 解析定义信息
      FString histortSql = innerSqlCmd(sPkgSqlCmd, DEFINE_BEGIN, DEFINE_END);
      String histort = "";
      if(!histortSql.isEmpty()){
         histort = histortSql.toString() + "\n";
      }
      FString defineSql = innerSqlCmd(sPkgSqlCmd, DEFINE_BEGIN, DEFINE_END);
      String define = "";
      if(!defineSql.isEmpty()){
         define = defineSql.toString() + "\n";
      }
      _item.set("old_history", histort);
      _item.set("old_define", define);
      // 生成代码
      ITemplateParser parser = findParser("database.oracle", "package.body");
      //parser.setItem(_item);
      //parser.setList(_list);
      return parser.parse();
   }

   public FString makePackageHeadSource(){
      FSqlPackageParser pkgParse = new FSqlPackageParser(this, _packageName);
      FString sPkgSqlCmd = pkgParse.fetchHeadString();
      // 解析定义信息
      FString histortSql = innerSqlCmd(sPkgSqlCmd, DEFINE_BEGIN, DEFINE_END);
      String histort = "";
      if(!histortSql.isEmpty()){
         histort = histortSql.toString() + "\n";
      }
      FString defineSql = innerSqlCmd(sPkgSqlCmd, DEFINE_BEGIN, DEFINE_END);
      String define = "";
      if(!defineSql.isEmpty()){
         define = defineSql.toString() + "\n";
      }
      _item.set("old_history", histort);
      _item.set("old_define", define);
      // 生成代码
      ITemplateParser parser = findParser("database.oracle", "package.head");
      //parser.setItem(_item);
      //parser.setList(_list);
      return parser.parse();
   }

   public FString makeSequenceBodySource(){
      ITemplateParser parser = findParser("database.oracle", "sequence.body");
      //parser.setItem(_item);
      //parser.setList(_list);
      return parser.parse();
   }

   public FString makeSequenceHeadSource(){
      ITemplateParser parser = findParser("database.oracle", "sequence.head");
      //parser.setItem(_item);
      //parser.setList(_list);
      return parser.parse();
   }

   public FString makeUnitSource(){
      ITemplateParser parser = findParser("database.oracle", "java.unit");
      //parser.setItem(_item);
      //parser.setList(_list);
      return parser.parse();
   }

   public FString makeViewSource(){
      ITemplateParser parser = findParser("database.oracle", "view");
      //parser.setItem(_item);
      //parser.setList(_list);
      return parser.parse();
   }

   protected void onAfterFetchInfo(){
      if(RString.isEmpty(_item.get("catalog"))){
         _item.set("catalog", _viewName.replaceAll("_", ".").toUpperCase());
      }
      _item.set("logic", _viewName);
      _item.set("view_name", _viewName);
      _item.set("package_name", _packageName);
      _item.set("sequence_name", _viewName + "_SQ");
      _item.set("sequence_pkgname", _viewName + "_SI");
      String sDiName = RDatabaseFormat.toJavaClassName(_viewName) + "Di";
      _item.set("data_interface", sDiName);
   }

   protected void onBeforeFetchInfo(String sTableName){
      _tableName = sTableName.toUpperCase();
      _viewName = _tableName;
      if(_viewName.endsWith("_DS")){
         _viewName = _viewName.substring(0, _viewName.length() - 3);
      }
      if(_viewName.endsWith("_SI")){
         _viewName = _viewName.substring(0, _viewName.length() - 3);
      }
      if(_viewName.endsWith("_SQ")){
         _viewName = _viewName.substring(0, _viewName.length() - 3);
      }
      _packageName = _viewName + "_DI";
      _sequenceName = _viewName + "_SI";
   }

   public String packageName(){
      return _packageName;
   }

   public FAttributes row(){
      return _item;
   }

   public void saveToPath(String path){
      FString sqlSource = makePackageHeadSource();
      String fileName = path + viewName().toLowerCase() + "_di.pkh";
      FStringFile file = new FStringFile(fileName, false);
      file.append(sqlSource);
      file.store();
      sqlSource = makePackageBodySource();
      fileName = path + viewName().toLowerCase() + "_di.pky";
      file = new FStringFile(fileName, false);
      file.append(sqlSource);
      file.store();
      //      sqlSource = makeSequenceHeadSource();
      //      fileName = path + viewName().toLowerCase() + "_si.pkh";
      //      file = new FStringFile(fileName, false);
      //      file.append(sqlSource);
      //      file.store();
      //
      //      sqlSource = makeSequenceBodySource();
      //      fileName = path + viewName().toLowerCase() + "_si.pky";
      //      file = new FStringFile(fileName, false);
      //      file.append(sqlSource);
      //      file.store();
   }

   public String sequenceName(){
      return _sequenceName;
   }

   public String tableName(){
      return _tableName;
   }

   public String viewName(){
      return _viewName;
   }
}
