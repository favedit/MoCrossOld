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
import org.mo.com.data.FSqlFunction;
import org.mo.com.data.FSqlParameter;
import org.mo.com.data.FSqlParameters;
import org.mo.com.data.FSqlProcedure;
import org.mo.com.data.ISqlConnectionMeta;
import org.mo.com.data.MSqlConnection;
import org.mo.com.data.RSql;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RInteger;
import org.mo.com.lang.RString;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;

//============================================================
// <T>MYSQL数据库链接。</T>
//============================================================
public class FSqlMysqlConnection
      extends MSqlConnection
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FSqlMysqlConnection.class);

   //============================================================
   // <T>构造MYSQL数据库链接。</T>
   //============================================================
   public FSqlMysqlConnection(){
      _databaseType = "mysql";
   }

   //============================================================
   // <T>构造MYSQL数据库链接。</T>
   //
   // @param sqlConnection 数据库链接
   //============================================================
   public FSqlMysqlConnection(Connection sqlConnection){
      super(sqlConnection);
   }

   //============================================================
   // <T>生成访问地址。</T>
   //
   // @param url 访问地址
   // @return 访问地址
   //============================================================ 
   @Override
   public String makeUrl(String url){
      if(url.indexOf('?') == -1){
         url += "?";
      }
      if(url.indexOf('&') != -1){
         url += "&";
      }
      return url + "useUnicode=true&characterEncoding=utf-8&zeroDateTimeBehavior=convertToNull&transformedBitIsBoolean=true";
   }

   //============================================================
   // <T>创建数据库描述对象。</T>
   //
   // @return 描述对象
   //============================================================ 
   @Override
   protected ISqlConnectionMeta createMeta(){
      return new FSqlMysqlConnectionMeta(this);
   }

   //============================================================
   // <T>选择存储。</T>
   //
   // @param name 名称
   //============================================================ 
   @Override
   public void selectStorage(String name){
      executeSql("USE " + name);
   }

   //============================================================
   // <T>格式化字符串为命令内容。</T>
   //
   // @param value 内容
   // @return 命令内容
   //============================================================
   @Override
   public String formatValue(String value){
      if(!RString.isEmpty(value)){
         StringBuffer sql = new StringBuffer();
         for(char ch : value.toCharArray()){
            if('\'' == ch){
               sql.append("\\'");
            }else if('"' == ch){
               sql.append("\\\"");
            }else if('\\' == ch){
               sql.append("\\\\");
            }else if('\t' == ch){
               sql.append("\\t");
            }else if('\r' == ch){
               sql.append("\\r");
            }else if('\n' == ch){
               sql.append("\\n");
            }else{
               sql.append(ch);
            }
         }
         return sql.toString();
      }
      return RString.EMPTY;
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
      if(pageSize < 0){
         return fetchDataset(sql.toString());
      }
      FSql formatSql = new FSql(sql.toString());
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
         String countSql = "SELECT COUNT(*) FROM (" + formatSql + ") T LIMIT 1";
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
         String executeSqlCmd = formatSql + " LIMIT " + pageSize + " OFFSET " + (pageSize * page);
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
               _logger.debugFull(this, "fetchDataset", end - startTime, "Fetch dataset end. (count={1}, total={2}, sql={3})", dataset.count(), dataset.total(), formatSql);
            }
         }
      }catch(Exception e){
         exception = e;
         e.printStackTrace();
      }finally{
         // 释放使用的总数查询相关的数据对象
         if(null != resultSetCount){
            try{
               resultSetCount.close();
            }catch(Exception e){
               exception = e;
            }
         }
         if(null != countStatement){
            try{
               countStatement.close();
            }catch(Exception e){
               exception = e;
            }
         }
         // 释放使用的数据对象
         if(null != resultSet){
            try{
               resultSet.close();
            }catch(Exception e){
               exception = e;
            }
         }
         if(null != statement){
            try{
               statement.close();
            }catch(Exception e){
               exception = e;
            }
         }
         if(exception != null){
            throw new FFatalError(exception, "SqlCmd[{0}],PageSize[{1}],Page[{2}]", formatSql, pageSize, page);
         }
      }
      return dataset;
   }

   //============================================================
   // <T>尝试执行一个函数命令。</T>
   //
   // @param function 函数命令
   //============================================================
   @Override
   public void execute(FSqlFunction function){
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
   // <T>尝试执行一个存储过程。</T>
   //
   // @param procedure 存储过程
   //============================================================
   @Override
   public void execute(FSqlProcedure procedure){
      long start = System.currentTimeMillis();
      Exception exception = null;
      CallableStatement statement = null;
      TDumpInfo infoBefore = new TDumpInfo();
      try{
         FSqlParameters parameters = procedure.parameters();
         int parameterSize = parameters.count();
         FSql sql = new FSql();
         sql.append("CALL `");
         sql.append(procedure.fullName());
         sql.append("`(");
         for(int n = 1; n <= parameterSize; n++){
            if(n != 1){
               sql.append(',');
            }
            sql.append("?");
         }
         sql.append(")");
         sql.append(";");
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
               _logger.debugFull(this, "execute", time, "Execute procedure. (info={1})", infoBefore);
            }else{
               _logger.debugFull(this, "execute", time, "Execute procedure BF. (info={1})", infoBefore);
               _logger.debugFull(this, "execute", time, "Execute procedure AF. (info={1})", infoAfter);
            }
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
            throw new FFatalError(exception, "Execute procedure failure. (procedure={1}, info={2})", procedure, infoBefore);
         }
      }
   }
}
