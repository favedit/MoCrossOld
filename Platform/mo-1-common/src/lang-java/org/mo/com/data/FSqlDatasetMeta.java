package org.mo.com.data;

import org.mo.com.lang.FObject;

//============================================================
// <T>数据集描述对象。</T>
//
// @history 051015 MAOCY 创建
//============================================================
public class FSqlDatasetMeta
      extends FObject
{
   // 表名
   protected String _tableName;

   // 字段集合
   protected final FSqlFields _fields = new FSqlFields();

   //============================================================
   // <T>构造数据集描述对象。</T>
   //============================================================
   public FSqlDatasetMeta(){
   }

   //============================================================
   // <T>获得表名。</T>
   //
   // @return 表名
   //============================================================
   public String tableName(){
      return _tableName;
   }

   //============================================================
   // <T>设置表名。</T>
   //
   // @param tableName 表名
   //============================================================
   public void setTableName(String tableName){
      _tableName = tableName;
   }

   //============================================================
   // <T>获得字段集合。</T>
   //
   // @return 字段集合
   //============================================================
   public FSqlFields fields(){
      return _fields;
   }
}
