/*
 * @(#)FSqlAccessConnection.java
 *
 * Copyright 2009 microbject, All Rights Reserved.
 *
 */
package org.mo.data.driver;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import org.mo.com.collections.FDataset;
import org.mo.com.collections.FRow;
import org.mo.com.data.ESqlDataType;
import org.mo.com.data.FSql;
import org.mo.com.data.FSqlDatasetMeta;
import org.mo.com.data.FSqlField;
import org.mo.com.data.FSqlFunction;
import org.mo.com.data.FSqlProcedure;
import org.mo.com.data.ISqlConnectionMeta;
import org.mo.com.data.MSqlConnection;
import org.mo.com.data.RSql;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RInteger;
import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.resource.IResource;
import org.mo.com.resource.RResource;

/**
 * <T>ACCESS数据库链接。</T>
 * 
 * @history 090904 MAOCY 创建
 */
public class FSqlAccessConnection
      extends MSqlConnection
{
   private final static ILogger _logger = RLogger.find(FSqlAccessConnection.class);

   /**
    * <p>创建Oracle数据库链接</p>
    *
    */
   public FSqlAccessConnection(){
   }

   /**
    * <p>创建Oracle数据库链接</p>
    *
    * @param sqlConnection SQL数据库链接
    */
   public FSqlAccessConnection(Connection sqlConnection){
      super(sqlConnection);
   }

   @Override
   protected ISqlConnectionMeta createMeta(){
      return new FSqlOracleConnectionMeta(this);
   }

   @Override
   public void execute(FSqlFunction function){
   }

   @Override
   public void execute(FSqlProcedure procedure){
   }

   @Override
   public FDataset fetchDataset(CharSequence sql,
                                int pageSize,
                                int page){
      if(pageSize < 0){
         return fetchDataset(sql.toString());
      }
      long startTime = System.currentTimeMillis();
      if(_logger.debugAble()){
         _logger.debug(this, "tryFetchDataset", "Fetch dataset begin (page={0}, pageSize={1}) {2}", page, pageSize, sql);
      }
      // 修正数据
      if(pageSize < RSql.PAGE_MIN_SIZE){
         throw new FFatalError("Page Size < DEFAULT_PAGE_MIN_SIZE({0})", RSql.PAGE_MIN_SIZE);
      }
      if(pageSize > RSql.PAGE_MAX_SIZE){
         throw new FFatalError("Page Size > DEFAULT_PAGE_MAX_SIZE({0})", RSql.PAGE_MAX_SIZE);
      }
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
         String countSql = "SELECT COUNT(*) AS TEMP_TOTAL_COUNT FROM (" + sql + ")";
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
         String executeSqlCmd = "SELECT * FROM (SELECT ROWNUM AS ID_ROWNUM, table__.* FROM (" + sql + ") table__ WHERE ROWNUM<=" + endPosition + " )" + " WHERE ID_ROWNUM>" + startPosition;
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
               _logger.debugFull(this, "tryFetchDataset", end - startTime, "Fetch dataset end (count={0}, total={1}) {2}", dataset.count(), dataset.total(), new FSql(sql.toString()));
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
            throw new FFatalError(exception, "SqlCmd[{0}],PageSize[{1}],Page[{2}]", sql, pageSize, page);
         }
      }
      return dataset;
   }

   /**
    * <p>获得一个表的数据描述资源</p>
    *
    * @param table 表名称
    * @return 数据描述资源
    */
   @Override
   public FSqlDatasetMeta fetchTableMeta(String table){
      IResource resource = RResource.find(FSqlOracleConnectionMeta.class);
      FSql sql = new FSql(resource.findString("table.columns"));
      sql.bindString("table", table.toString());
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

   /**
    * <p>从数据类型装换到SQL类型</p>
    *
    * @param type 数据类型 
    */
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
}
