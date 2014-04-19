/*
 * @(#)FQueueConsole.java
 *
 * Copyright 2005 microbject, All Rights Reserved.
 *
 */
package org.mo.eng.queue;

import org.mo.com.lang.FError;
import org.mo.com.lang.RString;
import org.mo.com.lang.reflect.RClass;
import org.mo.com.xml.FXmlDocument;
import org.mo.com.xml.FXmlNode;

/**
 * <p>队列控制台</p>
 *
 * @author ALEX
 */
public class FQueueConsole
{
   // 监视线程优先度
   private final int m_nPriority = 0;

   // 监视器时间间隔
   private final long m_lThreadInterval = 0;

   // 监视器线程
   private FQueueThread m_oThread = null;

   // 监视器控制文件监视
   @SuppressWarnings("unused")
   private FConfigMonitor m_oMonitor = null;

   // 监视器控制文件
   private final String m_sConfigFileName = null;

   /**
    * <p>初始化前操作</p>
    * <p>设置初始化的参数</p>
    * <p>create date:2005/02/18</p>
    *
    * @return TRUE：成功<br>FALSE：失败
    * @exception FError 应用程序例外
    */
   public void initializeConfig() throws FError{
      //m_nPriority = RInteger.parse(configNode().nodeText("Priority"), RThread.DEFAULT_PRIORITY);
      //m_lThreadInterval = RLong.toLong(configNode().nodeText("Interval"), RThread.DEFAULT_INTERVAL);
      //m_sConfigFileName = configNode().nodeText("Config");
   }

   /**
    * <p>初始化操作</p>
    * <p>建立监视器线程</p>
    * <p>create date:2005/02/18</p>
    *
    * @return TRUE：成功<br>FALSE：失败
    * @exception FError 应用程序例外
    */
   public void initializeThread() throws FError{
      m_oThread = new FQueueThread(this);
      m_oThread.setPriority(m_nPriority);
      m_oThread.setInterval(m_lThreadInterval);
      m_oThread.start();
   }

   /**
    * <p>初始化后操作</p>
    * <p>启动监视器线程</p>
    * <p>create date:2005/02/18</p>
    *
    * @return TRUE：成功<br>FALSE：失败
    * @exception FError 应用程序例外
    */
   public void initializeMonitor() throws FError{
      if(!RString.isEmpty(m_sConfigFileName)){
         refreshConfig();
         m_oMonitor = new FConfigMonitor(this, m_sConfigFileName);
         //FMonitorManager.console().push(m_oMonitor);
      }
   }

   /**
    * <p>刷新监视器控制信息</p>
    * <p>create date:2005/02/18</p>
    *
    * @return TRUE：成功<br>FALSE：失败
    * @exception FError 应用程序例外
    */
   public boolean refreshConfig() throws FError{
      if(RString.isEmpty(m_sConfigFileName)){
         return false;
      }
      // 清除所有旧的监视器
      m_oThread.clear();
      // 配置新的监视器
      String sInstance = null;
      FXmlNode oQueueNode = null;
      FXmlDocument oConfigDocument = new FXmlDocument();
      //oConfigDocument.loadFromFile(m_sConfigFileName);
      FXmlNode oRootNode = oConfigDocument.root();
      int nCount = oRootNode.nodes().count();
      FQueue oQueue = null;
      for(int n = 0; n < nCount; n++){
         oQueueNode = oRootNode.node(n);
         sInstance = oQueueNode.get("instance");
         oQueue = RClass.newInstance(sInstance);
         //oQueue.setName(oQueueNode.attribute("name"));
         //oQueue.setConfigNode(oQueueNode);
         m_oThread.pushQueue(oQueue);
      }
      //FLogger.debug(this, "refreshConfig", "Load config: " + m_sConfigFileName);
      return false;
   }
}
