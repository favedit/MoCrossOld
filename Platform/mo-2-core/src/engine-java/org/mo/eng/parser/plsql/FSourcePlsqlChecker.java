package org.mo.eng.parser.plsql;

import org.mo.com.collections.FRow;
import org.mo.com.lang.FStrings;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.eng.data.common.FSqlQuery;
import org.mo.eng.data.common.ISqlContext;

public class FSourcePlsqlChecker
{
   private ISqlContext _sqlContext;

   private static ILogger _logger = RLogger.find(FSourcePlsqlChecker.class);

   private final FSourcePlsqlParser _parser = new FSourcePlsqlParser();

   public void checkAll(){
      long start = System.currentTimeMillis();
      FSqlQuery query = new FSqlQuery(_sqlContext, FSourcePlsqlParser.class, "packages.list");
      for(FRow row : query.fetchDataset()){
         checkPackage(row.get("package_name"));
      }
      _logger.debug(this, "buildPackages", "Check all package in {0}ms", System.currentTimeMillis() - start);
      //_parser.buildTables();
      //_parser.buildPackages();
   }

   public void checkPackage(String packageName){
      long start = System.currentTimeMillis();
      _parser.buildPackage(packageName);
      FSourcePlsqlPackage sqlPackage = _parser.packages().get(packageName);
      checkPackageHead(packageName, sqlPackage.heads());
      checkPackageBody(packageName, sqlPackage.bodies());
      _logger.debug(this, "buildPackages", "Check package {0} in {1}ms", packageName, System.currentTimeMillis() - start);
   }

   public void checkPackageBody(String packageName,
                                FStrings lines){
      int count = lines.count();
      for(int n = 0; n < count; n++){
         String line = lines.get(n);
         if(line.contains("\t")){
            _logger.warn(this, "checkPackageBody", "Package body {0} has tab key. (line={1}:{2})", packageName, n, line);
         }
      }
   }

   public void checkPackageHead(String packageName,
                                FStrings lines){
      int count = lines.count();
      for(int n = 0; n < count; n++){
         String line = lines.get(n);
         if(line.contains("\t")){
            _logger.warn(this, "checkPackageHead", "Package head {0} has tab key. (line={1}:{2})", packageName, n, line);
         }
      }
   }

   public void setSqlContext(ISqlContext sqlContext){
      _sqlContext = sqlContext;
      _parser.setSqlContext(sqlContext);
   }

   public ISqlContext sqlContext(){
      return _sqlContext;
   }
}
