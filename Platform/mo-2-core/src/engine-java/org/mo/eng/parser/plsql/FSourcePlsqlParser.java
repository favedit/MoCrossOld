package org.mo.eng.parser.plsql;

import org.mo.com.collections.FDataset;
import org.mo.com.collections.FRow;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.eng.data.common.FSqlQuery;
import org.mo.eng.data.common.ISqlContext;

public class FSourcePlsqlParser
{
   private ISqlContext _sqlContext;

   private static ILogger _logger = RLogger.find(FSourcePlsqlParser.class);

   private final FSourcePlsqlTables _tables = new FSourcePlsqlTables();

   private final FSourcePlsqlPackages _packages = new FSourcePlsqlPackages();

   private void buildPackage(FDataset dataset){
      for(FRow row : dataset){
         // 设置表格信息
         String name = row.get("name");
         FSourcePlsqlPackage sqlPackage = _packages.get(name);
         if(null == sqlPackage){
            sqlPackage = new FSourcePlsqlPackage();
            _packages.set(name, sqlPackage);
         }
         // 设置字段信息
         //         String type = row.get("type");
         //         String line = row.get("line");
         //         String text = row.get("text");
         //         if("PACKAGE".equals(type)){
         //            //sqlPackage.heads().ensureSet(Integer.parseInt(line) - 1, text);
         //         }else if("PACKAGE BODY".equals(type)){
         //            //sqlPackage.bodies().ensureSet(Integer.parseInt(line) - 1, text);
         //         }
      }
   }

   public void buildPackage(String packageName){
      long start = System.currentTimeMillis();
      FSqlQuery query = new FSqlQuery(_sqlContext, getClass(), "package.source");
      query.bindString("package_name", packageName);
      buildPackage(query.fetchDataset());
      _logger.debug(this, "buildPackages", "Build package {0} in {1}ms", packageName, System.currentTimeMillis() - start);
   }

   public void buildPackages(){
      long start = System.currentTimeMillis();
      FSqlQuery query = new FSqlQuery(_sqlContext, getClass(), "packages.source");
      buildPackage(query.fetchDataset());
      _logger.debug(this, "buildPackages", "Build all package in {0}ms", System.currentTimeMillis() - start);
   }

   public void buildTables(){
      long start = System.currentTimeMillis();
      FSqlQuery query = new FSqlQuery(_sqlContext, getClass(), "table.fields");
      for(FRow row : query.fetchDataset()){
         // 设置表格信息
         String tableName = row.get("table_name");
         FSourcePlsqlTable table = _tables.get(tableName);
         if(null == table){
            table = new FSourcePlsqlTable();
            //table.loadConfig(row);
            _tables.set(tableName, table);
         }
         // 设置字段信息
         String columnName = row.get("column_name");
         FSourcePlsqlTableField field = table.fields().get(columnName);
         if(null == field){
            field = new FSourcePlsqlTableField();
            //field.loadConfig(row);
            table.fields().set(columnName, field);
         }
      }
      _logger.debug(this, "buildTables", "Build all table in {0}ms", System.currentTimeMillis() - start);
   }

   public FSourcePlsqlPackages packages(){
      return _packages;
   }

   public FSourcePlsqlPackage parse(String packageName){
      return null;
   }

   public void setSqlContext(ISqlContext sqlContext){
      _sqlContext = sqlContext;
      _sqlContext.activeConnection().setMaxFetch(0);
   }

   public ISqlContext sqlContext(){
      return _sqlContext;
   }

   public FSourcePlsqlTables tables(){
      return _tables;
   }
}
