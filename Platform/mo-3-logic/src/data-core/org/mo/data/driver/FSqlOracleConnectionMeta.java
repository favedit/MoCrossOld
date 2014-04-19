/*
 * @(#)FSqlOracleConnectionMeta.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.data.driver;

import org.mo.com.collections.FDataset;
import org.mo.com.collections.FRow;
import org.mo.com.data.ESqlDataType;
import org.mo.com.data.FSql;
import org.mo.com.data.FSqlField;
import org.mo.com.data.FSqlTable;
import org.mo.com.data.FSqlView;
import org.mo.com.data.ISqlConnection;
import org.mo.com.data.MSqlConnectionMeta;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RInteger;
import org.mo.com.lang.RString;
import org.mo.com.resource.IResource;
import org.mo.com.resource.RResource;

public class FSqlOracleConnectionMeta
      extends MSqlConnectionMeta
{
   private static IResource _resource = RResource.find(FSqlOracleConnectionMeta.class);

   private final static String FLD_FIELD_NAME = "FIELD_NAME";

   private final static String FLD_DATA_TYPE = "DATA_TYPE";

   private final static String FLD_TABLE_NAME = "TABLE_NAME";

   private final static String FLD_VIEW_NAME = "VIEW_NAME";

   public FSqlOracleConnectionMeta(ISqlConnection connection){
      super(connection);
   }

   @Override
   protected FSql findSql(String type){
      return new FSql(_resource.findString(type));
   }

   @Override
   public FSqlTable findTable(String tableName,
                              boolean detail){
      FSql sql = findSql(SQL_TABLE_FIELDS);
      sql.bindString("table", tableName.toUpperCase());
      FSqlTable table = new FSqlTable();
      table.setName(tableName);
      if(detail){
         FDataset ds = _connection.fetchDataset(sql.toString());
         int count = ds.count();
         for(int n = 0; n < count; n++){
            FRow row = ds.get(n);
            FSqlField field = new FSqlField();
            setFieldMeta(row, field);
            table.push(field);
         }
      }
      return table;
   }

   @Override
   protected void setFieldMeta(FRow row,
                               FSqlField field){
      field.setName(RString.toUpper(row.get(FLD_FIELD_NAME)));
      String type = row.get(FLD_DATA_TYPE);
      if(type.startsWith("int(")){
         field.setType(ESqlDataType.Integer);
         field.setSize(RInteger.parse(RString.mid(type, "(", ")")));
      }else if(type.startsWith("char(")){
         field.setType(ESqlDataType.String);
         field.setSize(RInteger.parse(RString.mid(type, "(", ")")));
      }else if(type.startsWith("varchar(")){
         field.setType(ESqlDataType.String);
         field.setSize(RInteger.parse(RString.mid(type, "(", ")")));
      }else if(type.equals("date")){
         field.setType(ESqlDataType.DateTime);
      }else if(type.equals("text")){
         field.setType(ESqlDataType.String);
      }
      field.setIsNull(RBoolean.parse(row.get("is_nullable")));
      field.setIsKey(RString.contains(row.get("column_key"), "PRI"));
      field.setDefaultValue(row.get("column_default"));
      field.setNote(row.get("extra"));
   }

   @Override
   protected void setTableMeta(FRow row,
                               FSqlTable table){
      table.setName(row.get(FLD_TABLE_NAME));
   }

   @Override
   protected void setViewMeta(FRow row,
                              FSqlView view){
      view.setName(row.get(FLD_VIEW_NAME));
   }
}
