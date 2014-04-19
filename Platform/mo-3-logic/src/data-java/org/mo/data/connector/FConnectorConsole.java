package org.mo.data.connector;

import org.mo.data.connector.common.XConnection;
import org.mo.data.connector.common.XDatabase;

import org.mo.com.data.ISqlConnection;
import org.mo.com.lang.FDictionary;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObjects;
import org.mo.com.lang.FString;
import org.mo.com.lang.INamePair;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.IXmlObject;
import org.mo.core.aop.face.AProperty;
import org.mo.eng.data.FConnectionConsole;
import org.mo.eng.data.IConnectionConsole;
import org.mo.eng.store.FXmlConfigConsole;

//============================================================
// <T>数据库链接管理类。</T>
// <P>
//    同时管理多个数据库链接缓冲池。
//    负责收集未使用的数据库链接。
// </P>
//
// @return 链接控制台集合
//============================================================
public class FConnectorConsole
      extends FXmlConfigConsole<XDatabase>
      implements
         IConnectorConsole
{
   // 日志输出接口
   private static final ILogger _logger = RLogger.find(FConnectorConsole.class);

   // 链接标志
   private static String FLAG_CONNECTION_CONSOLE = "connection.console";

   // 默认的数据库控制台
   @AProperty
   protected String _default;
   
   @AProperty
   protected int _interval1;

   // 数据库控制台哈希表
   protected FDictionary<IConnectionConsole> _consoles = new FDictionary<IConnectionConsole>(IConnectionConsole.class);

   //============================================================
   // <T>创建类型数组。</T>
   //
   // @return 类型数组
   //============================================================
   @Override
   protected FObjects<XDatabase> createCollection(){
      return new FObjects<XDatabase>(XDatabase.class);
   }

   //============================================================
   // <T>获得链接控制台集合。</T>
   //
   // @return 链接控制台集合
   //============================================================
   @Override
   public IConnectionConsole[] connectionConsoles(){
      return _consoles.toObjects();
   }

   //============================================================
   // <T>根据名称获得一个链接控制台。</T>
   //
   // @param name 数据库名称
   // @return 链接控制台
   //============================================================ 
   @Override
   public IConnectionConsole connectionConsole(String name){
      IConnectionConsole iconsole = _consoles.find(name);
      if(iconsole == null){
         // 查找指定名称的链接
         XConnection xfind = null;
         XDatabase[] xdatabases = list();
         for(XDatabase xdatabase : xdatabases){
            String databaseName = xdatabase.innerGet("name");
            for(IXmlObject xobject : xdatabase.children()){
               if(XConnection.isInstance(xobject)){
                  XConnection xconnection = (XConnection)xobject;
                  String connectionName = databaseName + "." + xconnection.innerGet("name");
                  if(name.equals(connectionName)){
                     xfind = xconnection;
                     break;
                  }
               }
            }
         }
         // 检查查找到定义信息
         if(xfind == null){
            throw new FFatalError("Can't find connection define. (name={1})", name);
         }
         // 建立链接控制台
         FConnectionConsole console = new FConnectionConsole();
         console.setName(name);
         console.setPassport(xfind.getPassport());
         console.setPassword(xfind.getPassword());
         console.setDriverName(xfind.getDriverName());
         console.setDriverClass(xfind.getDriverClass());
         console.setUrl(xfind.getUrl());
         // 存储设置
         _consoles.set(name, console);
         iconsole = console;
      }
      return iconsole;
   }

   //============================================================
   // <T>增加一个链接控制台。</T>
   //
   // @param connectionConsole 链接控制台
   //============================================================ 
   @Override
   public void add(IConnectionConsole connectionConsole){
      _consoles.set(connectionConsole.name(), connectionConsole);
   }

   //============================================================
   // <T>收集一个未使用的数据库链接。</T>
   //
   // @return 数据库链接
   //============================================================ 
   @Override
   public ISqlConnection alloc(){
      return alloc(_default);
   }

   //============================================================
   // <T>收集一个未使用的数据库链接。</T>
   //
   // @param name 数据库名称
   // @return 数据库链接
   //============================================================ 
   @Override
   public ISqlConnection alloc(String name){
      // 获得数据库链接管理器
      IConnectionConsole connectionConsole = connectionConsole(name);
      // 获得数据库链接
      ISqlConnection connection = connectionConsole.alloc();
      if(_logger.debugAble()){
         _logger.debug(this, "alloc", "Alloc connection. (connection={1})", connection);
      }
      connection.attributes().set(FLAG_CONNECTION_CONSOLE, connectionConsole);
      // 开始事务
      connection.beginTransaction();
      // 返回收集好的链接
      return connection;
   }

   //============================================================
   // <T>回收一个未使用的数据库链接。</T>
   //
   // @param connection 数据库链接
   //============================================================ 
   @Override
   public void free(ISqlConnection connection){
      IConnectionConsole connectionConsoel = (IConnectionConsole)connection.attributes().get(FLAG_CONNECTION_CONSOLE);
      connectionConsoel.free(connection);
   }

   //============================================================
   // <T>释放一个未使用的数据库链接。</T>
   //
   // @param connection 数据库链接
   //============================================================ 
   @Override
   public void releaseConnection(ISqlConnection connection){
      IConnectionConsole connectionConsoel = (IConnectionConsole)connection.attributes().get(FLAG_CONNECTION_CONSOLE);
      connectionConsoel.release(connection);
   }

   //============================================================
   // <T>释放一个指定名称的链接控制台。</T>
   //
   // @param name 链接控制台名称
   //============================================================ 
   @Override
   public void release(String name){
      IConnectionConsole console = _consoles.find(name);
      if(null != console){
         console.release();
      }
   }

   //============================================================
   // <T>释放一个全部的链接控制台名称。</T>
   //============================================================ 
   @Override
   public void releaseAll(){
      for(IConnectionConsole console : _consoles.values()){
         console.release();
      }
   }

   //============================================================
   // <T>运行时获得当前实例的内部信息。</T>
   //
   // @return 内部信息 
   //============================================================
   @Override
   public String dump(){
      FString dump = new FString();
      for(INamePair<IConnectionConsole> pair : _consoles){
         dump.append("Database console: ");
         dump.append(pair.name());
         dump.append("=");
         dump.append(pair.value());
      }
      return dump.toString();
   }
}
