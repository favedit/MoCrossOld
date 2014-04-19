package org.mo.data.procedure.common;

import org.mo.com.data.ESqlDataType;
import org.mo.com.data.FSqlDatasetMeta;
import org.mo.com.data.FSqlField;
import org.mo.com.data.ISqlConnection;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FFatalError;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.core.aop.RAop;
import org.mo.eng.data.IDatabaseConsole;

public class FSqlParserContext
{
   private final static ILogger _logger = RLogger.find(FSqlParserContext.class);

   // 属性集合
   private final FAttributes _typeMap = new FAttributes();

   /**
    * <T>实现把包中的数据类型转化成对应JAVA中数据类型</T>
    * 
    * @param paramType 包中的数据类型
    * @return JAVA中数据类型
    */
   protected ESqlDataType databaseType(String paramType){
      try{
         if(null == paramType){
            return ESqlDataType.Unknown;
         }
         if(paramType.equals("BOOLEAN")){
            return ESqlDataType.Boolean;
         }else if(paramType.equals("INTEGER")){
            return ESqlDataType.Integer;
         }else if(paramType.equals("NUMBER") || paramType.equals("FLOAT")){
            return ESqlDataType.Float;
         }else if(paramType.equals("DATETIME")){
            return ESqlDataType.DateTime;
         }else if(paramType.equals("DATE")){
            return ESqlDataType.Date;
         }else if(paramType.equals("CHAR") || paramType.equals("CHAR2") || paramType.equals("NCHAR") || paramType.equals("VARCHAR") || paramType.equals("VARCHAR2") || paramType.equals("LONG")){
            return ESqlDataType.String;
         }else if(paramType.indexOf("LONG RAW") > 0){
            return ESqlDataType.Unknown;
         }else if(paramType.indexOf("%ROWTYPE") > 0){
            return ESqlDataType.Unknown;
         }else if(paramType.indexOf("%TYPE") > 0){
            paramType = paramType.substring(0, paramType.length() - 5);
            if(_typeMap.contains(paramType)){
               return databaseType(_typeMap.get(paramType));
            }else{
               String[] arParamType = paramType.split("\\.");
               if(arParamType.length == 2){
                  IDatabaseConsole databaseConsole = RAop.find(IDatabaseConsole.class);
                  ISqlConnection connection = null;
                  try{
                     connection = databaseConsole.alloc();
                     // 执行表的所有字段列表
                     FSqlDatasetMeta datasetMeta = connection.fetchTableMeta(arParamType[0] + "_DS");
                     if(null != datasetMeta){
                        ESqlDataType dataType;
                        for(FSqlField field : datasetMeta.fields()){
                           dataType = field.typeCd();
                           if(dataType == ESqlDataType.Unknown){
                              _typeMap.set(arParamType[0] + "." + field.name(), "UNKNOWN");
                           }else if(dataType == ESqlDataType.DateTime){
                              _typeMap.set(arParamType[0] + "." + field.name(), "DATETIME");
                           }else if(dataType == ESqlDataType.Integer){
                              _typeMap.set(arParamType[0] + "." + field.name(), "INTEGER");
                           }else if(dataType == ESqlDataType.Float){
                              _typeMap.set(arParamType[0] + "." + field.name(), "FLOAT");
                           }else if(dataType == ESqlDataType.Char){
                              _typeMap.set(arParamType[0] + "." + field.name(), "CHAR2");
                           }else if(dataType == ESqlDataType.String){
                              _typeMap.set(arParamType[0] + "." + field.name(), "VARCHAR2");
                           }
                        }
                        return databaseType(_typeMap.get(paramType));
                     }
                  }catch(Exception e){
                     _logger.error(null, "Get database type  error.", e);
                  }finally{
                     if(null != connection){
                        databaseConsole.free(connection);
                     }
                  }
               }
            }
         }
      }catch(Exception e){
         throw new FFatalError(e, "Parse databaseType error. (line={0})", paramType);
      }
      return ESqlDataType.Unknown;
   }

   public String findDataType(String paramType){
      return databaseType(paramType).toString();
   }
}
