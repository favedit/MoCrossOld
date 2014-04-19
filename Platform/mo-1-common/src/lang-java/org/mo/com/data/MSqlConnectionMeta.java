/*
 * @(#)MSqlConnectionMeta.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.com.data;

import org.mo.com.collections.FDataset;
import org.mo.com.collections.FRow;

public abstract class MSqlConnectionMeta
      implements
         ISqlConnectionMeta
{
   public final static String SQL_TABLE_FIELDS = "table.fields";

   public final static String SQL_TABLES = "tables";

   public final static String SQL_VIEW_FIELDS = "view.fields";

   public final static String SQL_VIEWS = "views";

   protected ISqlConnection _connection;

   public MSqlConnectionMeta(ISqlConnection connection){
      _connection = connection;
   }

   protected abstract FSql findSql(String type);

   @Override
   public FSqlTable findTable(String tableName,
                              boolean detail){
      FSql sql = findSql(SQL_TABLE_FIELDS);
      sql.bind("table", tableName.toUpperCase());
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
   public FSqlView findView(String table,
                            boolean detail){
      // TODO Auto-generated method stub
      return null;
   }

   @Override
   public FSqlTable[] listTables(){
      FSql sql = findSql(SQL_TABLES);
      FDataset ds = _connection.fetchDataset(sql.toString());
      int count = ds.count();
      FSqlTable[] tabels = new FSqlTable[count];
      for(int n = 0; n < count; n++){
         FRow row = ds.get(n);
         FSqlTable table = new FSqlTable();
         setTableMeta(row, table);
         tabels[n] = table;
      }
      return tabels;
   }

   @Override
   public FSqlView[] listViews(){
      FSql sql = findSql(SQL_VIEWS);
      FDataset ds = _connection.fetchDataset(sql.toString());
      int count = ds.count();
      FSqlView[] views = new FSqlView[count];
      for(int n = 0; n < count; n++){
         FRow row = ds.get(n);
         FSqlView view = new FSqlView();
         setViewMeta(row, view);
         views[n] = view;
      }
      return views;
   }

   protected abstract void setFieldMeta(FRow row,
                                        FSqlField field);

   protected abstract void setTableMeta(FRow row,
                                        FSqlTable table);

   protected abstract void setViewMeta(FRow row,
                                       FSqlView view);
}
