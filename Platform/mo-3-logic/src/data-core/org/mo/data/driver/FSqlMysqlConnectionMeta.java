/*
 * @(#)FSqlMysqlConnectionMeta.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.data.driver;

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

public class FSqlMysqlConnectionMeta
      extends MSqlConnectionMeta
{
   private static IResource _resource = RResource.find(FSqlMysqlConnectionMeta.class);

   public FSqlMysqlConnectionMeta(ISqlConnection connection){
      super(connection);
   }

   @Override
   protected FSql findSql(String type){
      return new FSql(_resource.findString(type));
   }

   @Override
   protected void setFieldMeta(FRow row,
                               FSqlField field){
      field.setName(RString.toUpper(row.get("column_name")));
      String type = row.get("column_type");
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
      table.setName(RString.toUpper(row.value(0)));
   }

   @Override
   protected void setViewMeta(FRow row,
                              FSqlView view){
      view.setName(RString.toUpper(row.value(0)));
   }
}
