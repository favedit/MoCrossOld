/*
 * @(#)FEventThread.java
 *
 * Copyright 2005 microbject, All Rights Reserved.
 *
 */
package org.mo.eng.event;

import java.io.File;
import org.mo.com.io.FObjectFile;
import org.mo.com.lang.FObjects;
import org.mo.com.lang.RString;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.system.FThread;
import org.mo.com.system.RThread;

/**
 * <p>事件监视线程</p>
 * <p>1. 执行列表中的事件逻辑，执行完后，从事件列表中删除</p>
 *
 * @author ALEX
 * @version 1.00 - 2005/10/24
 */
public class FEventThread
      extends FThread
{
   private static ILogger _logger = RLogger.find(FEventThread.class);

   // 事件列表
   private final FObjects<FEvent> _events = new FObjects<FEvent>(FEvent.class);

   // 事件执行间隔
   private long _interval = 0;

   // 工作路径
   private String _workpath = null;

   /**
    * <p>运行当前线程的事务</p>
    * <p>执行列表中的事件逻辑，每执行完后，删除当前事件</p>
    * <p>create date:2005/02/18</p>
    *
    */
   @Override
   @SuppressWarnings("resource")
   public boolean execute(){
      try{
         while(true){
            if(RThread.checkStop()){
               break;
            }
            // 执行队列中的事件
            if(!_events.isEmpty()){
               FObjects<FEvent> executes = new FObjects<FEvent>(FEvent.class);
               synchronized(this){
                  executes.append(_events);
               }
               FObjects<FEvent> oExecuteEvents = new FObjects<FEvent>(FEvent.class);
               for(FEvent event : _events){
                  try{
                     event.execute();
                     if(RThread.checkStop()){
                        break;
                     }
                  }catch(Exception e){
                     _logger.error(this, "execute", e);
                  }
                  oExecuteEvents.push(event);
               }
               // 删除事件
               synchronized(this){
                  for(FEvent event : oExecuteEvents){
                     _events.remove(event);
                  }
               }
            }
            // 执行文件缓冲中的事件
            if(!RString.isEmpty(_workpath)){
               File dir = new File(_workpath);
               if(dir.isDirectory()){
                  FEvent event;
                  for(File eventFile : dir.listFiles()){
                     FObjectFile file = new FObjectFile(eventFile);
                     event = (FEvent)file.readObject();
                     try{
                        event.execute();
                     }catch(Exception e){
                        _logger.error(this, "execute", e);
                     }
                     eventFile.delete();
                  }
               }
            }
            // 进程休眠
            sleep(_interval);
         }
         return true;
      }catch(Exception e){
         _logger.error(this, "execute", e);
         return false;
      }
   }

   /**
    * <p>将一个事件加入到事件线程中</p>
    * <p>create date:2005/02/18</p>
    *
    * @param event 事件
    */
   public synchronized void push(FEvent event){
      _events.push(event);
   }

   /**
    * <p>设置事件执行间隔</p>
    * <p>create date:2005/02/18</p>
    *
    * @param interval 事件执行间隔
    */
   public void setInterval(long interval){
      _interval = interval;
   }

   /**
    * <p>设置工作路径</p>
    * <p>create date:2005/02/18</p>
    *
    * @param path 工作路径
    */
   public void setWorkPath(String path){
      _workpath = path;
   }
}
