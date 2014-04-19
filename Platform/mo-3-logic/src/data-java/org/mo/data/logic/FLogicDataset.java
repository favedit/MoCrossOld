package org.mo.data.logic;

import org.mo.com.data.ISqlConnection;
import org.mo.com.lang.FObject;

//============================================================
// <T>逻辑数据集合。</T>
//============================================================
public class FLogicDataset
      extends FObject
{
   // 数据库链接
   protected ISqlConnection _connection;

   //============================================================
   // <T>构造逻辑数据集合。</T>
   //============================================================
   public FLogicDataset(){
   }

   //============================================================
   // <T>构造逻辑数据集合。</T>
   //
   // @param connection 数据库链接
   //============================================================
   public FLogicDataset(ISqlConnection connection){
      _connection = connection;
   }

   //============================================================
   // <T>获得数据链接。</T>
   //
   // @return 数据链接
   //============================================================
   public ISqlConnection connection(){
      return _connection;
   }

   //============================================================
   // <T>设置数据链接。</T>
   //
   // @param connection 数据链接
   //============================================================
   public void SetConnection(ISqlConnection connection){
      _connection = connection;
   }

   //============================================================
   // <T>构造逻辑数据集合。</T>
   //
   // @param connection 数据库链接
   //============================================================
   public boolean executeSql(String sql){
      return _connection.executeSql(sql);
   }
}
