/*
 * @(#)FLogicSubscribeConsole.java
 *
 * Copyright 2008 microbject. All Rights Reserved.
 *
 */
package org.mo.logic.subscribe;

import org.mo.com.data.ISqlConnection;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.core.aop.RAop;
import org.mo.core.aop.face.AProperty;
import org.mo.eng.data.IDatabaseConsole;
import org.mo.logic.data.ILgSubscribeDi;
import org.mo.logic.data.impl.FLgSubscribeImpl;

/**
 * <T>订阅线程的初始化和启动线程与创建订阅的控制实例类</T>
 * <P title='订阅线程的初始化'>初始化订阅线程并启动订阅线程</P>
 * <P title='创建订阅'>根据订阅的设置调用订阅的java代理包方法来创建订阅</P> 
 * 
 * @author YJHUA
 * @version 1.00 - 2008/10/29
 * @see <R link='jv-c:org.mo.logic.subscribe.FLogicSubscribeDeployThread'>FLogicSubscribeDeployThread</R>
 * @see <R link='jv-m:org.mo.logic.data.impl.FLgSubscribeDiImpl|doPublish'>FLgSubscribeDiImpl.doPublish</R>
 */
public class FLogicSubscribeConsole
      implements
         ILogicSubscribeConsole
{
   // 订阅结构
   protected ILogicSubscribe _subscribe = new FLogicSubscribe();

   //日志
   private final static ILogger _logger = RLogger.find(FLogicSubscribeConsole.class);

   // 订阅线程的睡眠时间
   @AProperty
   protected long _interval;

   /* (non-Javadoc)
    * @see org.mo.logic.subscribe.ILogicSubscribeConsole#doSubscribe(org.mo.logic.subscribe.ILogicSubscribeAction)
    */
   @Override
   public void doSubscribe(ILogicSubscribeAction pack){
      _logger.debug(this, "do_Subscribe", "DoSubscribe method is excuted.");
      IDatabaseConsole databaseConsole = RAop.find(IDatabaseConsole.class);
      ISqlConnection connection = null;
      try{
         if(!pack.isEmpty()){
            // 获得数据库连接
            connection = databaseConsole.alloc();
            // 连接包的代理方法类
            ILgSubscribeDi subscribe = new FLgSubscribeImpl(connection);
            // 调用包的java代理方法De_Deploy(logic);
            subscribe.doSubscribe(null, _subscribe.userId(), _subscribe.typeId(), _subscribe.linkId(), pack.pack());
         }else{
            _logger.error(this, "doSubscribe", "Pack is null. (_pack={0})", pack);
         }
      }catch(Exception e){
         _logger.error(this, "execute", e, "Connect database failure. (connection={0})", connection);
      }finally{
         if(null != connection){
            // 释放数据库连接
            databaseConsole.free(connection);
         }
      }
   }

   /* (non-Javadoc)
    * @see org.mo.logic.subscribe.ILogicSubscribeConsole#doSubscribe(org.mo.logic.subscribe.ILogicSubscribeEvent)
    */
   @Override
   public void doSubscribe(ILogicSubscribeEvent pack){
      _logger.debug(this, "doSubscribe", "DoSubscribe method is excuted.");
      IDatabaseConsole databaseConsole = RAop.find(IDatabaseConsole.class);
      ISqlConnection connection = null;
      try{
         if(!pack.isEmpty()){
            // 获得数据库连接
            connection = databaseConsole.alloc();
            // 连接包的代理方法类
            ILgSubscribeDi subscribe = new FLgSubscribeImpl(connection);
            // 调用包的java代理方法De_Deploy(logic);
            subscribe.doSubscribe(null, _subscribe.userId(), _subscribe.typeId(), _subscribe.linkId(), _subscribe.eventId(), pack.pack());
         }else{
            _logger.error(this, "doSubscribe", "Pack is null. (pack={0})", pack);
         }
      }catch(Exception e){
         _logger.error(this, "execute", e, "Connect database failure. (connection={0})", connection);
      }finally{
         if(null != connection){
            // 释放数据库连接
            databaseConsole.free(connection);
         }
      }
   }

   /* (non-Javadoc)
    * @see org.mo.logic.subscribe.ILogicSubscribeConsole#doSubscribe(java.lang.String)
    */
   @Override
   public void doSubscribe(String parameters){
      _logger.debug(this, "doSubscribe", "DoSubscribe method is excuted.");
      IDatabaseConsole databaseConsole = RAop.find(IDatabaseConsole.class);
      ISqlConnection connection = null;
      try{
         // 获得数据库连接
         connection = databaseConsole.alloc();
         // 连接包的代理方法类
         ILgSubscribeDi subscribe = new FLgSubscribeImpl(connection);
         // 调用包的java代理方法De_Deploy(logic);
         subscribe.doSubscribe(null, _subscribe.userId(), _subscribe.typeId(), _subscribe.linkId(), _subscribe.actionId(), _subscribe.eventId(), parameters);
      }catch(Exception e){
         _logger.error(this, "execute", e, "Connect database failure. (connection={0})", connection);
      }finally{
         if(null != connection){
            // 释放数据库连接
            databaseConsole.free(connection);
         }
      }
   }

   /* (non-Javadoc)
    * @see org.mo.logic.subscribe.ILogicSubscribeConsole#initialize()
    */
   @Override
   public void initialize(){
      // 创建订阅线程实体
      FLogicSubscribeDeployThread thread = new FLogicSubscribeDeployThread();
      // 设置线程的睡眠时间
      thread.setInterval(_interval);
      // 线程开始执行
      thread.start();
   }

   /** 
    * <T>设置订阅容器</T>
    * 
    * @param subscribe 新的订阅容器
    */
   public void setSubscribe(FLogicSubscribe subscribe){
      _subscribe = subscribe;
   }
}
