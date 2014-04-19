package org.mo.data.driver;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import org.mo.com.collections.FDataset;
import org.mo.com.collections.FRow;
import org.mo.com.data.ESqlDataType;
import org.mo.com.data.FSql;
import org.mo.com.data.FSqlDatasetMeta;
import org.mo.com.data.FSqlField;
import org.mo.com.data.FSqlFunction;
import org.mo.com.data.FSqlParameter;
import org.mo.com.data.FSqlParameters;
import org.mo.com.data.FSqlProcedure;
import org.mo.com.data.ISqlConnectionMeta;
import org.mo.com.data.MSqlConnection;
import org.mo.com.data.RSql;
import org.mo.com.lang.FError;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RInteger;
import org.mo.com.lang.RString;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.resource.IResource;
import org.mo.com.resource.RResource;

//============================================================
// <T>Oracle数据库链接。</T>
//============================================================
public class FSqlOracleConnection
      extends MSqlConnection
{
   // 日志输出接口
   private final static ILogger _logger = RLogger.find(FSqlOracleConnection.class);

   //============================================================
   // <T>构造Oracle数据库链接。</T>
   //============================================================
   public FSqlOracleConnection(){
   }

   //============================================================
   // <T>构造Oracle数据库链接。</T>
   //
   // @param sqlConnection SQL数据库链接
   //============================================================
   public FSqlOracleConnection(Connection sqlConnection){
      super(sqlConnection);
   }

   //============================================================
   // <T>创建链接描述器。</T>
   //
   // @return 链接描述器
   //============================================================
   @Override
   protected ISqlConnectionMeta createMeta(){
      return new FSqlOracleConnectionMeta(this);
   }

   //============================================================
   // <T>获得一个表的数据描述资源。</T>
   //
   // @param tableName 表名称
   // @return 数据描述资源
   //============================================================
   @Override
   public FSqlDatasetMeta fetchTableMeta(String tableName){
      IResource resource = RResource.find(FSqlOracleConnectionMeta.class);
      FSql sql = new FSql(resource.findString("table.columns"));
      sql.bindString("table", tableName.toString());
      FDataset dataset = fetchDataset(sql.toString());
      FSqlDatasetMeta meta = new FSqlDatasetMeta();
      for(FRow row : dataset){
         FSqlField field = new FSqlField(row.get("field_name"));
         String dataType = row.get("data_type");
         if("FLOAT".equals(dataType)){
            field.setType(ESqlDataType.Float);
         }else if("NUMBER".equals(dataType)){
            String dataScale = row.get("data_scale");
            if("0".equals(dataScale)){
               field.setType(ESqlDataType.Integer);
            }else{
               field.setType(ESqlDataType.Float);
            }
         }else if("DATE".equals(dataType)){
            field.setType(ESqlDataType.DateTime);
         }else if("CHAR".equals(dataType) || "CHAR2".equals(dataType)){
            field.setType(ESqlDataType.Char);
         }else if("VARCHAR".equals(dataType) || "VARCHAR2".equals(dataType)){
            field.setType(ESqlDataType.String);
         }else{
            field.setType(ESqlDataType.Unknown);
         }
         field.setIsNull(RBoolean.parse(row.get("is_null")));
         field.setSize(row.getInteger("data_length").intValue());
         meta.fields().push(field);
      }
      return meta;
   }

   //============================================================
   // <T>从数据类型装换到本地数据类型。</T>
   //
   // @param type 数据类型
   // @return 本地数据类型;
   //============================================================
   public String toSqlType(String type){
      if(!RString.isEmpty(type)){
         type = type.toLowerCase();
         if(type.equals("int") || type.equals("integer")){
            return "INTEGER";
         }else if(type.equals("long")){
            return "LONG";
         }else if(type.equals("bool") || type.equals("bit")){
            return "BOOLEAN";
         }else if(type.equals("number") || type.equals("float") || type.equals("singer")){
            return "FLOAT";
         }else if(type.equals("date")){
            return "DATE";
         }else if(type.equals("time")){
            return "TIME";
         }else if(type.equals("datetime")){
            return "DATETIME";
         }else if(type.equals("timestamp")){
            return "TIMESTAMP";
         }else if(type.equals("char") || type.equals("text") || type.equals("varchar") || type.equals("varchar2") || type.equals("nvarchar") || type.equals("nvarchar2")){
            return "STRING";
         }
      }
      return null;
   }

   //============================================================
   // <T>执行SQL命令，返回执行结果集。</T>
   // <P>
   //    数据按照PageSize大小进行分页，选取指定页号的数据。
   //    如果PageSize为负数，选取全部数据。
   //    如果Page为-1，选取最后一页数据。
   //    如果Page范围不在选取范围内，选取空数据集。
   // </P>
   //
   // @param sql SQL命令
   // @param pageSize 分页大小
   // @param page 页号
   // @return 结果集
   //============================================================
   @Override
   public FDataset fetchDataset(CharSequence sql,
                                int pageSize,
                                int page){
      // 是否全部获取
      if(pageSize < 0){
         return fetchDataset(sql.toString());
      }
      //............................................................
      // 格式化命令
      String formatSql = RSql.formatSql(sql.toString());
      long startTime = System.currentTimeMillis();
      if(_logger.debugAble()){
         _logger.debug(this, "fetchDataset", "Fetch dataset begin. (page={1}, pageSize={2}, sql={3})", page, pageSize, formatSql);
      }
      //............................................................
      // 修正数据
      if(pageSize < RSql.PAGE_MIN_SIZE){
         throw new FFatalError("Page Size too small. (page_size={1}, page_min_size={2})", pageSize, RSql.PAGE_MIN_SIZE);
      }
      if(pageSize > RSql.PAGE_MAX_SIZE){
         throw new FFatalError("Page Size too big. (page_size={1}, page_max_size={2})", pageSize, RSql.PAGE_MAX_SIZE);
      }
      //............................................................
      // 初始化参数
      int total = 0;
      PreparedStatement statement = null;
      PreparedStatement countStatement = null;
      ResultSet resultSet = null;
      ResultSet resultSetCount = null;
      Exception exception = null;
      FDataset dataset = null;
      try{
         dataset = new FDataset();
         dataset.setPageSize(pageSize);
         dataset.setPage(page);
         String countSql = "SELECT COUNT(*) AS TEMP_TOTAL_COUNT FROM (" + formatSql + ")";
         countStatement = sqlConnection().prepareStatement(countSql, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
         countStatement.setFetchSize(1);
         resultSetCount = countStatement.executeQuery();
         if(resultSetCount.next()){
            total = RInteger.parse(resultSetCount.getObject(1));
         }
         dataset.setTotal(total);
         if(total < pageSize * page){
            return dataset;
         }
         // 计算分页数
         int pageCount = 0;
         if(total > 0){
            pageCount = total / pageSize;
            if(total % pageSize > 0){
               pageCount++;
            }
         }
         dataset.setPageCount(pageCount);
         // 选取数据
         int startPosition = pageSize * page;
         int endPosition = pageSize * (page + 1);
         String executeSqlCmd = "SELECT * FROM (SELECT ROWNUM AS ID_ROWNUM, table__.* FROM (" + formatSql + ") table__ WHERE ROWNUM<=" + endPosition + " )" + " WHERE ID_ROWNUM>" + startPosition;
         statement = sqlConnection().prepareStatement(executeSqlCmd, ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
         statement.setFetchSize(pageSize);
         resultSet = statement.executeQuery();
         if(null != resultSet){
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            // 获得所有列信息
            String[] names = new String[columnCount];
            int[] types = new int[columnCount];
            for(int n = 0; n < columnCount; n++){
               names[n] = metaData.getColumnName(n + 1).toLowerCase();
               types[n] = metaData.getColumnType(n + 1);
            }
            // 获取所有行数据
            while(resultSet.next()){
               FRow row = new FRow();
               fillUnit(row, columnCount, names, types, resultSet);
               dataset.push(row);
            }
            long end = System.currentTimeMillis();
            if(_logger.debugAble()){
               _logger.debugFull(this, "fetchDataset", end - startTime, "Fetch dataset end. (count={0}, total={1}) {2}", dataset.count(), dataset.total(), formatSql);
            }
         }
      }catch(Exception e){
         exception = e;
         e.printStackTrace();
      }finally{
         // 释放使用的总数查询相关的数据对象
         if(resultSetCount != null){
            try{
               resultSetCount.close();
            }catch(Exception e){
               exception = e;
            }
         }
         if(countStatement != null){
            try{
               countStatement.close();
            }catch(Exception e){
               exception = e;
            }
         }
         // 释放使用的数据对象
         if(resultSet != null){
            try{
               resultSet.close();
            }catch(Exception e){
               exception = e;
            }
         }
         if(statement != null){
            try{
               statement.close();
            }catch(Exception e){
               exception = e;
            }
         }
         if(exception != null){
            throw new FFatalError(exception, "Fetch dataset failure. (sql={1}, page_size={2}, page={3})", formatSql, pageSize, page);
         }
      }
      return dataset;
   }

   //============================================================
   // <T>判断是否重新执行。</T>
   //
   // @param error 错误信息
   //============================================================
   protected boolean checkExecuteAgain(FError error){
      if(error != null){
         Throwable root = error.rootThrowable();
         if(null != root){
            String message = root.getMessage();
            // ORA-04061: package body "PKG.METHOD" 的当前状态失效
            if(message.contains("ORA-04061")){
               return true;
            }
            // ORA-04065: 未执行，已更改或删除 package
            if(message.contains("ORA-04065")){
               return true;
            }
         }
      }
      return false;
   }

   //============================================================
   // <T>尝试执行一个函数命令。</T>
   //
   // @param function 函数命令
   //============================================================
   protected void tryExecute(FSqlFunction function){
      long start = System.currentTimeMillis();
      Exception exception = null;
      CallableStatement statement = null;
      TDumpInfo callInfo = new TDumpInfo();
      TDumpInfo info = new TDumpInfo();
      try{
         FSqlParameters parameters = function.parameters();
         int parameterSize = parameters.count();
         StringBuffer callSql = new StringBuffer();
         callSql.append("BEGIN :1 := ");
         callSql.append(function.fullName());
         if(parameterSize > 0){
            callSql.append("(");
            for(int n = 1; n <= parameterSize; n++){
               if(n != 1){
                  callSql.append(",");
               }
               callSql.append(":" + (n + 1));
            }
            callSql.append(")");
         }
         callSql.append("; END;");
         // 准备调用函数
         statement = sqlConnection().prepareCall(callSql.toString());
         callInfo.append("Execute function : ");
         callInfo.append(function.fullName());
         callInfo.append("(");
         paramsToCallableStatement(parameters, statement, 1, callInfo);
         callInfo.append(")");
         FSqlParameter returnParam = function.parameterReturn();
         if(returnParam.isType(ESqlDataType.String)){
            statement.setString(1, null);
            statement.registerOutParameter(1, Types.LONGVARCHAR);
         }else if(returnParam.isType(ESqlDataType.Integer)){
            statement.setInt(1, 0);
            statement.registerOutParameter(1, Types.INTEGER);
         }else if(returnParam.isType(ESqlDataType.Float)){
            statement.setFloat(1, 0);
            statement.registerOutParameter(1, Types.NUMERIC);
         }else if(returnParam.isType(ESqlDataType.DateTime)){
            statement.setDate(1, null);
            statement.registerOutParameter(1, Types.DATE);
         }
         // 执行函数
         statement.execute();
         // 分析返回内容
         returnParam.set(statement.getString(1));
         info.append("Execute function : ");
         info.append(function.fullName());
         if(!parameters.isEmpty()){
            info.append('(');
            callableStatementToParams(parameters, statement, 1, info);
            info.append(')');
         }
         info.append(" = ");
         info.append(RSql.dumpValue(returnParam.type(), returnParam.asString()));
         if(_logger.debugAble()){
            long time = System.currentTimeMillis() - start;
            _logger.debugFull(this, "tryExecute", time, info.toString());
         }
      }catch(Exception e){
         exception = e;
         e.printStackTrace();
      }finally{
         try{
            if(statement != null){
               statement.close();
            }
         }catch(Exception e){
            exception = e;
         }
         if(null != exception){
            throw new FFatalError(exception, callInfo.toString());
         }
      }
   }

   //============================================================
   // <T>执行一个函数命令。</T>
   // <P>如果是包状态为未编译，则尝试再执行一次。</P>
   //
   // @param function 函数命令
   //============================================================
   @Override
   public void execute(FSqlFunction function){
      // 检查参数
      if(function == null){
         throw new FFatalError("Function is null.");
      }
      // 尝试执行
      for(int n = RSql.RETRY_MAX_COUNT; n >= 0; n--){
         try{
            tryExecute(function);
            break;
         }catch(FError e){
            if(!checkExecuteAgain(e)){
               throw e;
            }
         }
      }
   }

   //============================================================
   // <T>尝试执行一个存储过程。</T>
   //
   // @param procedure 存储过程
   //============================================================
   protected void tryExecute(FSqlProcedure procedure){
      long start = System.currentTimeMillis();
      Exception exception = null;
      CallableStatement statement = null;
      TDumpInfo infoBefore = new TDumpInfo();
      try{
         FSqlParameters parameters = procedure.parameters();
         int parameterSize = parameters.count();
         FSql sql = new FSql();
         sql.append("BEGIN ");
         sql.append(procedure.fullName());
         if(parameterSize > 0){
            sql.append("(");
            for(int n = 1; n <= parameterSize; n++){
               if(n != 1){
                  sql.append(',');
               }
               sql.append(":" + n);
            }
            sql.append(")");
         }
         sql.append("; END;");
         // 准备调用函数
         statement = sqlConnection().prepareCall(sql.toString());
         infoBefore.append(procedure.fullName());
         if(!parameters.isEmpty()){
            infoBefore.append('(');
            paramsToCallableStatement(parameters, statement, 0, infoBefore);
            infoBefore.append(')');
         }
         statement.execute();
         // 分析返回内容
         TDumpInfo infoAfter = new TDumpInfo();
         infoAfter.append(procedure.fullName());
         if(!parameters.isEmpty()){
            infoAfter.append('(');
            callableStatementToParams(parameters, statement, 0, infoAfter);
            infoAfter.append(')');
         }
         if(_logger.debugAble()){
            long time = System.currentTimeMillis() - start;
            if(infoBefore.toString().equals(infoAfter.toString())){
               _logger.debugFull(this, "tryExecute", time, "Execute procedure : {1}", infoBefore);
            }else{
               _logger.debugFull(this, "tryExecute", time, "Execute procedure BF : {1}", infoBefore);
               _logger.debugFull(this, "tryExecute", time, "Execute procedure AF : {1}", infoAfter);
            }
         }
      }catch(Exception e){
         exception = e;
         _logger.debug(this, "tryExecute", "Execute procedure : {1}", infoBefore);
         e.printStackTrace();
      }finally{
         try{
            if(statement != null){
               statement.close();
            }
         }catch(Exception e){
            exception = e;
         }
         if(null != exception){
            throw new FFatalError(exception, "Procedure {1} {2}", procedure, infoBefore);
         }
      }
   }

   //============================================================
   // <T>执行一个存储过程。</T>
   // <P>如果是包状态为未编译，则尝试再执行一次。</P>
   //
   // @param procedure 存储过程
   //============================================================
   @Override
   public void execute(FSqlProcedure procedure){
      // 检查参数
      if(procedure == null){
         throw new FFatalError("Procedure is null.");
      }
      // 尝试执行
      for(int n = RSql.RETRY_MAX_COUNT; n >= 0; n--){
         try{
            tryExecute(procedure);
            break;
         }catch(FError e){
            if(!checkExecuteAgain(e)){
               throw e;
            }
         }
      }
   }
}
