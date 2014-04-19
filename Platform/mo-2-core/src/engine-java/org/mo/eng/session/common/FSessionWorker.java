/*
 * @(#)FSessionWorker.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.eng.session.common;

import java.io.Serializable;
import org.mo.com.lang.FObject;
import org.mo.com.lang.IDump;
import org.mo.com.lang.RDateTime;
import org.mo.com.lang.generic.TDumpInfo;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;

/**
 * <p>线程托管对象。</p>
 * <p>负责线程对象的管理和时间检查。</p>
 * 
 * @author maocy
 * @version 1.00 - 2008/11/18
 */
public class FSessionWorker
      extends FObject
      implements
         Serializable,
         IDump
{
   // 序列化版本号
   private static final long serialVersionUID = 1L;

   // 日志输出接口
   @SuppressWarnings("unused")
   private static ILogger _logger = RLogger.find(FSessionWorker.class);

   // 创建时间
   private final long _createTime;

   // 最后使用时间
   private long _current;

   // 超时设定
   private long _timeout;

   // 线程标识
   private String _id;

   // 线程信息
   private ISession _session;

   // 存储标识 
   private String _storeId;

   /**
    * <T>新建线程工作对象。</T>
    */
   public FSessionWorker(){
      _createTime = System.currentTimeMillis();
   }

   /**
    * <T>获得当前线程的创建时间。</T>
    * 
    * @return 创建时间
    */
   public long createTime(){
      return _createTime;
   }

   /* (non-Javadoc)
    * @see org.mo.com.lang.IDump#dump(org.mo.com.lang.TDumpInfo)
    */
   @Override
   public TDumpInfo dump(TDumpInfo info){
      long now = System.currentTimeMillis();
      info.append(_id);
      info.append("[");
      info.append(_storeId);
      info.append("]:");
      info.append(_current + "/" + _timeout);
      info.append("->(");
      info.append(RDateTime.format(_createTime));
      info.append("-");
      info.append(RDateTime.format(now));
      info.append("=");
      info.appendInt((int)((now - _createTime) / 1000));
      info.append("s)");
      if(null != _session){
         info.append(" [");
         info.append(_session);
         info.append("]");
      }
      return info;
   }

   /**
    * <T>清除线程信息对象。</T>
    */
   public void free(){
      _session = null;
   }

   /**
    * <T>获得当前线程的标识。</T>
    * 
    * @return 线程标识
    */
   public String id(){
      return _id;
   }

   /**
    * <T>测试当前线程是否活动。</T>
    * 
    * @return 是否活动
    */
   public boolean isActive(){
      return (null != _session);
   }

   /**
    * <T>刷新当前线程对象。</T>
    */
   public void refresh(){
      _current = _timeout;
   }

   /**
    * <T>获得当前线程信息。</T>
    * 
    * @return 线程信息对象
    */
   @SuppressWarnings("unchecked")
   public <V extends ISession> V session(){
      return (V)_session;
   }

   /**
    * <T>设置当前线程的线程标识。</T>
    * 
    * @param id 线程标识
    */
   public void setId(String id){
      _id = id;
   }

   /**
    * <T>设置当前线程信息。</T>
    * 
    * @param session 线程信息
    */
   public void setSession(ISession session){
      _session = session;
   }

   /**
    * <T>设置当前线程的存储标识。</T>
    * 
    * @param storeId 存储标识
    */
   public void setStoreId(String storeId){
      _storeId = storeId;
   }

   public void setTimeout(long timeout){
      _timeout = timeout;
      _current = timeout;
   }

   /**
    * <T>获得当前线程的存储标识。</T>
    * 
    * @return 存储标识
    */
   public String storeId(){
      return _storeId;
   }

   /**
    * <T>测试当前线程是否超时。</T>
    *
    * @param interval 时间间隔
    * @return TRUE：是<B/>FALSE：否
    */
   public boolean testTimeout(long interval){
      _current -= interval;
      return (_current < 0);
   }

   public long timeout(){
      return _timeout;
   }
}
