/*
 * @(#)FThread.java
 *
 * Copyright 2005 microbject, All Rights Reserved.
 *
 */
package org.mo.core.version;

import org.mo.com.io.FStringFile;
import org.mo.com.lang.FFatalError;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.system.FThread;
import org.mo.com.system.RThread;

/**
 * <p>运行版本检查线程</p>
 * <p>1. 检查当前线程的版本，当线程标识发生变化时，通知相关线程结束</p>
 * 
 * @author ALEX
 * @version 1.00 - 2005/10/20
 */
public class FVersionThread
      extends FThread
{
   private static ILogger _logger = RLogger.find(FVersionThread.class);

   private final FStringFile _file = new FStringFile();

   // 事件时间间隔
   private long _interval = 0;

   private boolean _loopFlag = true;

   // 工作文件
   private String _workfile = null;

   /**
    * <p>运行当前线程的事务</p>
    * <p>create date:2005/10/20</p>
    *
    */
   @Override
   public boolean execute(){
      try{
         // 设置进程版本信息
         _file.setFileName(_workfile);
         _file.assign(toString());
         _file.store();
         if(_logger.debugAble()){
            _logger.debug(this, "execute", "Store current thread version ({0})", toString());
         }
         // 监视版本信息是否被修改
         while(_loopFlag){
            if(_file.reload()){
               if(_file.toString().equals(toString())){
                  RThread.stopAll();
                  break;
               }
            }else{
               RThread.stopAll();
               break;
            }
            // 检查当前线程状态是否已经改变
            if(RThread.checkStop()){
               break;
            }
            sleep(_interval);
         }
         RThread.stopAll();
      }catch(Exception e){
         throw new FFatalError(e);
      }
      return true;
   }

   /**
    * <p>设置事件执行间隔</p>
    * <p>create date:2005/10/20</p>
    *
    * @param value 事件执行间隔
    */
   public synchronized void setInterval(long value){
      _interval = value;
   }

   public void setLoopFlag(boolean flag){
      _loopFlag = flag;
   }

   /**
    * <p>设置工作文件</p>
    * <p>create date:2005/10/20</p>
    *
    * @param value 工作文件
    */
   public synchronized void setWorkfile(String value){
      _workfile = value;
   }
}
