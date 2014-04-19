package org.mo.util.data;

import org.mo.com.collections.FDataset;
import org.mo.com.data.ISqlConnection;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RInteger;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.core.aop.RAop;
import org.mo.eng.data.IDatabaseConsole;
import org.mo.eng.data.common.FSqlQuery;

public class RDataSequence
{

   // 日志输出对象
   private final static ILogger _logger = RLogger.find(RDataSequence.class);

   /**
    * <T>版本导入导出工具。</T>
    * 
    * @param args 输入参数
    *    <L value='type'>export/import:导出/导入</L>
    *    <L value='config'>设置文件的全路径</L>
    */
   @SuppressWarnings("unused")
   public static void main(String[] args){
      String targetDb = "EUISDP";
      if(1 == args.length){
         targetDb = args[0];
      }else if(0 != args.length){
         throw new FFatalError("Parameters is invalid.");
      }
      try{
         // 加载设置
         String path = "D:/Workspace/eUIS/src/eUIS-config";
         RAop.configConsole().loadFile(path + "/application-develop.xml");
         // 收集数据库链接
         IDatabaseConsole databaseConsole = RAop.find(IDatabaseConsole.class);
         ISqlConnection cnn = databaseConsole.alloc(targetDb);
         _logger.debug(null, "main", "Target connection is {0}={1}", targetDb, cnn);
         if(null == cnn){
            throw new FFatalError("Source connection is not found.");
         }
         // 同步所有枚举包
         FSqlQuery tableQuery = new FSqlQuery(cnn, RDataSequence.class, "tables");
         //         for(FRow row : tableQuery.fetchDataset()){
         //            String tableName = row.get("TABLE_NAME").toUpperCase();
         //            if(tableName.endsWith("_DS")){
         //               String sequenceName = RString.replace(tableName, "_DS", "_SQ");
         //               syncSequence(cnn, "OUID", tableName, sequenceName);
         //            }
         //            if(tableName.endsWith("_HS")){
         //               String sequenceName = RString.replace(tableName, "_HS", "_HQ");
         //               syncSequence(cnn, "OUHD", tableName, sequenceName);
         //            }
         //         }
         // 释放数据库链接
         databaseConsole.free(cnn);
         // 释放环境
         RAop.release();
      }catch(Exception e){
         _logger.error(null, "main", e);
         RAop.release();
      }
   }

   public static void syncSequence(ISqlConnection cnn,
                                   String field,
                                   String dataName,
                                   String sequenceName){
      // 查询序列存在性
      FSqlQuery existsQuery = new FSqlQuery(cnn, RDataSequence.class, "sequence");
      existsQuery.bindString("sequence_name", sequenceName);
      if(!existsQuery.fetchDataset().isEmpty()){
         // 获得最大OUID
         FSqlQuery tableQuery = new FSqlQuery(cnn, RDataSequence.class, "ouid.max");
         tableQuery.bindSql("field_name", field);
         tableQuery.bindSql("table_name", dataName);
         FDataset tableDataset = tableQuery.fetchDataset();
         if(!tableDataset.isEmpty()){
            String maxOuidValue = tableDataset.get(0).value(0);
            int maxOuid = RInteger.parse(maxOuidValue);
            //            // 获得当前序列
            //            FSqlQuery sequenceCurQuery = new FSqlQuery(cnn, RDataSequence.class, "sequence.cur");
            //            sequenceCurQuery.bindSql("sequence_name", sequenceName);
            //            String sequenceValue = sequenceCurQuery.fetchRow().value(0);
            //            if(RInteger.parse(sequenceValue) <= maxOuid){
            // 获得最大序列
            FSqlQuery sequenceQuery = new FSqlQuery(cnn, RDataSequence.class, "sequence.max");
            sequenceQuery.bindSql("sequence_name", sequenceName);
            while(true){
               String sequenceValue = sequenceQuery.fetchRow().value(0);
               int sequence = RInteger.parse(sequenceValue);
               if(sequence > maxOuid){
                  break;
               }
            }
            _logger.debug(null, "syncSequence", "Sync sequence for {0}:{1}", dataName, sequenceName);
            //            }
         }
      }
   }
}
