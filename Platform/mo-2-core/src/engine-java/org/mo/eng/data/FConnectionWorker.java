package org.mo.eng.data;

import java.sql.Connection;
import java.sql.SQLException;
import org.mo.com.data.MSqlConnection;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FObject;
import org.mo.com.lang.RBoolean;
import org.mo.com.lang.RDateTime;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;

//============================================================
// <T>数据库链接工作器。</T>
//============================================================ 
public class FConnectionWorker
      extends FObject
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FConnectionWorker.class);

   // 数据库链接
   protected MSqlConnection connection;

   // 使用中
   protected boolean inUsing = false;

   // 创建时间
   protected long createTime;

   // 激活时间
   protected long activeTime;

   // 自由时间
   protected long freeTime;

   // 测试命令
   protected String testSqlCmd;

   // 用户
   protected String user;

   //============================================================
   // <T>构造数据库链接工作器。</T>
   //============================================================ 
   public FConnectionWorker(){
      createTime = System.currentTimeMillis();
      activeTime = createTime;
      freeTime = createTime;
   }

   //============================================================
   // <T>判断是否链接。</T>
   //
   // @return 是否链接
   //============================================================ 
   public synchronized boolean isConnect(){
      if(null != connection){
         Connection sqlConnection = connection.sqlConnection();
         if(sqlConnection != null){
            try{
               if(!sqlConnection.isClosed()){
                  return true;
               }
            }catch(SQLException e){
               _logger.error(this, "isConnect", e);
            }
         }
      }
      return false;
   }

   //============================================================
   // <T>激活处理。</T>
   //============================================================ 
   public void active(){
      inUsing = true;
      activeTime = System.currentTimeMillis();
   }

   //============================================================
   // <T>释放处理。</T>
   //============================================================ 
   public synchronized void free(){
      inUsing = false;
      freeTime = System.currentTimeMillis();
   }

   //============================================================
   // <T>获得设置信息。</T>
   //
   // @return 设置信息
   //============================================================ 
   public FAttributes config(){
      FAttributes config = new FAttributes();
      config.set("activeTime", Long.toString(activeTime));
      config.set("createTime", RDateTime.format(createTime));
      config.set("freeTime", RDateTime.format(freeTime));
      config.set("inUsing", RBoolean.toString(inUsing));
      config.set("testSqlCmd", testSqlCmd);
      return config;
   }

   //============================================================
   // <T>链接测试。</T>
   //
   // @return 处理结果
   //============================================================ 
   public boolean connectTest(){
      return true;
   }

   //============================================================
   // <T>释放处理。</T>
   //============================================================ 
   public void release(){
      _logger.debug(this, "release", "Release sql connection. (connected={1}, connection={2})", isConnect(), connection);
      connection.close();
   }
}
