/*
 * @(#)FLogicSubscribeDeployThread.java
 *
 * Copyright 2008 microbject. All Rights Reserved.
 *
 */
package org.mo.logic.subscribe;

import org.mo.com.data.ISqlConnection;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.system.FThread;
import org.mo.com.system.RThread;
import org.mo.core.aop.RAop;
import org.mo.eng.data.IDatabaseConsole;
import org.mo.logic.data.ILgSubscribeDi;
import org.mo.logic.data.impl.FLgSubscribeImpl;

/**
 * <T>执行订阅包的代理方法线程类</T>
 * <P title='设置线程睡眠时间'>主要实现该订阅线程的睡眠时间的设置</P> 
 * <P title='线程的执行'>主要实现执行PLSQL包里的Do_Deploy方法</P>
 * 
 * 
 * @author YJHUA
 * @version 1.00 - 2008/10/29
 * @see <R link='ps-m:LG_SUBSCRIBE_DI|Do_Deploy'>LG_SUBSCRIBE_DI.Do_Deploy</R>
 */
public class FLogicSubscribeDeployThread
      extends FThread
{
   private final static ILogger _logger = RLogger.find(FLogicSubscribeDeployThread.class);

   // 执行时间间隔
   protected long _interval;

   /**
    * <T>线程启动后执行该方法主要实现执行包的代理方法</T>
    *
    * @return 返回线程是否继续执行
    *    <L value='true'>该线程没有处于停止状态，继续执行</L>
    *    <L value='false'>该线程处于停止状态,不继续执行</L>
    * @see <R link='jv-m:com.linekong.euis.data.impl.FLgSubscribePkg|doDeploy'>FLgSubscribePkg.doDeploy</R>
    */
   @Override
   public boolean execute(){
      /// 获得数据库连接
      IDatabaseConsole databaseConsole = RAop.find(IDatabaseConsole.class);
      while(!RThread.checkStop()){
         ISqlConnection connection = null;
         try{
            connection = databaseConsole.alloc();
            /// 连接包的代理方法类
            ILgSubscribeDi subscribe = new FLgSubscribeImpl(connection);
            /// 调用包的java代理方法De_Deploy(logic);
            subscribe.doDeploy(null);
            /// 设置线程睡眠时间
            RThread.sleep(_interval);
         }catch(Exception e){
            _logger.error(this, "execute", e, "Connect database failure. (connection={0})", connection);
         }finally{
            if(null != connection){
               /// 释放数据库连接
               databaseConsole.free(connection);
            }
         }
      }
      return true;
   }

   /**
    * <T>设置线程的睡眠时间</T>
    *
    * @param interval 线程的睡眠时间
    */
   void setInterval(long interval){
      _interval = interval;
   }
}
