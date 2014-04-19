package org.mo.eng.data.common;

import org.mo.com.data.FSqlConnections;
import org.mo.com.data.ISqlConnection;
import org.mo.com.lang.FObject;
import org.mo.com.lang.RString;
import org.mo.eng.data.IDatabaseConsole;

//============================================================
// <T>数据环境。</T>
//============================================================
public class FSqlContext
      extends FObject
      implements
         ISqlContext
{
   // 默认名称
   protected String _defaultName;

   // 数据库链接
   protected ISqlConnection _connection;

   // 数据库链接列表
   protected FSqlConnections _connections;

   // 数据库管理器
   protected IDatabaseConsole _databaseConsole;

   //============================================================
   // <T>构造数据环境。</T>
   //
   // @param databaseConsole 数据库控制台
   //============================================================
   public FSqlContext(IDatabaseConsole databaseConsole){
      _databaseConsole = databaseConsole;
   }

   //============================================================
   // <T>获得代码。</T>
   //
   // @return 代码
   //============================================================
   @Override
   public String code(){
      return null;
   }

   //============================================================
   // <T>获得默认名称。</T>
   //
   // @return 默认名称
   //============================================================
   @Override
   public String defaultName(){
      return _defaultName;
   }

   //============================================================
   // <T>设置默认名称。</T>
   //
   // @param defaultName 默认名称
   //============================================================
   @Override
   public void setDefaultName(String defaultName){
      _defaultName = defaultName;
   }

   //============================================================
   // <T>获得默认的数据链接。</T>
   //
   // @return 数据链接
   //============================================================
   @Override
   public ISqlConnection activeConnection(){
      // 收集默认的数据连接
      if(RString.isEmpty(_defaultName)){
         if(_connection == null){
            _connection = _databaseConsole.alloc();
         }
         return _connection;
      }
      // 收集默认名称的数据连接
      return activeConnection(_defaultName);
   }

   //============================================================
   // <T>获得指定名称的数据链接。</T>
   //
   // @param name 名称
   // @return 数据链接
   //============================================================
   @Override
   public ISqlConnection activeConnection(String name){
      if(_connections == null){
         _connections = new FSqlConnections();
      }
      ISqlConnection cnn = _connections.find(name);
      if(cnn == null){
         cnn = _databaseConsole.alloc(name);
         if(cnn != null){
            _connections.set(name, cnn);
         }
      }
      return cnn;
   }

   //============================================================
   // <T>回滚一个指定数据链接。</T>
   //
   // @param connection 数据链接
   //============================================================
   public void rollback(ISqlConnection connection){
      if(null != connection){
         connection.rollback();
         _databaseConsole.free(connection);
      }
   }

   //============================================================
   // <T>回滚处理。</T>
   //============================================================
   @Override
   public void rollback(){
      rollback(_connection);
      if(_connections != null){
         int count = _connections.count();
         for(int n = 0; n < count; n++){
            rollback(_connections.value(n));
         }
      }
   }

   //============================================================
   // <T>释放一个指定的数据链接。</T>
   //
   // @param connection 数据链接
   //============================================================
   public void release(ISqlConnection connection){
      if(connection != null){
         connection.commit();
         _databaseConsole.free(connection);
      }
   }

   //============================================================
   // <T>释放处理。</T>
   //============================================================
   @Override
   public void release(){
      // 释放默认数据链接
      if(_connection != null){
         release(_connection);
      }
      // 释放集合数据链接
      if(_connections != null){
         int count = _connections.count();
         for(int n = 0; n < count; n++){
            ISqlConnection connection = _connections.value(n);
            if(connection != null){
               release(connection);
            }
         }
         _connections.clear();
      }
   }
}
