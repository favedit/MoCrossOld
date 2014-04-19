/*
 * @(#)FSqlTableInfo.java
 *
 * Copyright 2005 microbject, All Rights Reserved.
 *
 */
package org.mo.eng.data.common;

import org.mo.com.collections.FAttributesList;
import org.mo.com.collections.FDataset;
import org.mo.com.collections.FRow;
import org.mo.com.data.ISqlConnect;
import org.mo.com.data.MSqlConnect;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FString;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RString;

/**
 * <p>表信息类</p>
 * 
 * @author ALEX
 * @version 1.00 - 2005/10/29
 */
public class FSqlTableInfo
      extends MSqlConnect
{
   private String _table = null;

   /**
    * <p>创建表信息类</p>
    * <p>create date:2005/10/29</p>
    * 
    * @param iConnectionAble 可获得链接的接口
    * @param sTableName 表名称
    */
   public FSqlTableInfo(ISqlConnect connect,
                        String table){
      super(connect);
      _table = table;
   }

   /**
    * <p>获得列信息</p>
    * <p>create date:2005/10/29</p>
    * 
    * @return 列信息
    */
   public FAttributesList fetchFieldsInfo(){
      FAttributesList list = new FAttributesList();
      FSqlQuery query = new FSqlQuery(this, propertySql("table.columns.detail"));
      query.bindString("table_name", _table);
      FDataset dataset = query.fetchDataset();
      for(FRow row : dataset){
         if(row.get("field_name").equalsIgnoreCase("obj_id") || row.get("field_name").equalsIgnoreCase("obj_fg")){
            continue;
         }
         row.set("auto_value", RBoolean.FALSE_STR);
         //row.setValue("sql_type", "ISqlType."
         //+ activeConnection().toSqlType(row.value("data_type")));
         FAttributes attrs = new FAttributes();
         attrs.append(row);
         list.push(attrs);
      }
      return list;
   }

   /**
    * <p>获得表信息</p>
    * <p>create date:2005/10/29</p>
    * 
    * @return 表信息
    */
   public FAttributes fetchTableInfo(){
      FSqlQuery oQuery = new FSqlQuery(this, propertySql("table.detail"));
      oQuery.bindString("table_name", _table);
      FRow row = oQuery.fetchRow();
      String logic = _table;
      if(_table.endsWith("_DS")){
         logic = logic.substring(0, logic.length() - 3);
      }
      String catalog = RString.replaceChars(logic.toLowerCase(), '_', '.');
      row.set("catalog", catalog);
      row.set("logic", logic);
      FAttributes attrs = new FAttributes();
      attrs.append(row);
      return attrs;
   }

   /**
    * <p>获得SQL属性</p>
    * <p>create date:2005/10/29</p>
    * 
    * @param name 属性
    * @return SQL属性
    */
   protected FString propertySql(String name){
      FString sql = new FString();
      sql.append("pty:database.oracle");
      //sql.append(activeConnection().databaseType());
      sql.append("|");
      sql.append(name);
      return sql;
   }

   /**
    * <p>获得表名称</p>
    * <p>create date:2005/10/29</p>
    * 
    * @return 表名称
    */
   public String tableName(){
      return _table;
   }
}
