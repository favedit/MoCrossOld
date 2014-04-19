/*
 * @(#)FQueueMonitor.java
 *
 * Copyright 2005 microbject, All Rights Reserved.
 *
 */
package org.mo.core.monitor.sql;

/**
 * <p>数据库处理监视器</p>
 *
 * @author FEDT
 * @version 1.00 - 2005/02/18
 */
public class FQueueMonitor
{
   //   // 序列化标志
   //   private static final long serialVersionUID = 1L;
   //
   //   // 序列化标志
   //   private FXmlNode m_oQueueNode = null;
   //
   //   /**
   //    * <p>获取队列类型的设置信息节点</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @return 设置信息节点
   //    * @exception FException 应用程序例外
   //    */
   //   public FXmlNode findQueueType(String sTypeName)
   //         throws FException {
   //      FXmlNode oFindNode = null;
   //      if (m_oQueueNode != null) {
   //         oFindNode = m_oQueueNode.getNodes().findNode("Type", "name", sTypeName);
   //      }
   //      if (oFindNode == null) {
   //         throw new FFatalException(
   //               this, "Can't find queue type config node. [" + sTypeName + "]");
   //      }
   //      return oFindNode;
   //   }
   //
   //   /**
   //    * <p>初始化监视器</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @return TRUE：成功<br>FALSE：失败
   //    * @exception FException 应用程序例外
   //    */
   //   public boolean initialize()
   //         throws FException {
   //      if (super.initialize()) {
   //         m_oQueueNode = getConfigNode().getNodes().findNode("Queue");
   //         return true;
   //      }
   //      return false;
   //   }
   //
   //   /**
   //    * <p>执行业务的逻辑</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param oContext 环境变量
   //    * @return TRUE：成功<br>FALSE：失败
   //    * @exception FException 应用程序例外
   //    */
   //   public boolean execute(FContext oContext)
   //         throws FException {
   //      String sInstance = null;
   //      FXmlNode oTypeNode = null;
   //      FDatabaseQueue oQueue = null;
   //      FStringList oProperty = new FStringList();
   //      FStringList oMessage = new FStringList();
   //      FStringList oParam = new FStringList();
   //      TmQueuePKG oTmQueuePKG = new TmQueuePKG(oContext);
   //      TmQueueLU oTmQueueLU = new TmQueueLU(oContext);
   //      TmQueueUnit oUnit = null;
   //      TmQueueDS oDataset = oTmQueueLU.fetchDataset(
   //            TmQueueLU.CURRENT_STATE.toString() + "='W'",
   //            TmQueueLU.LIST_ID.toString());
   //      int nCount = oDataset.getCount();
   //      for (int n = 0; n < nCount; n++) {
   //         try {
   //            // 准备数据
   //            oUnit = oDataset.getLogicUnit(n);
   //            oTypeNode = findQueueType(oUnit.getQueueType());
   //            sInstance = oTypeNode.getAttribute().getValue("instance");
   //            oProperty.unpack(oUnit.getProperty());
   //            oQueue = (FDatabaseQueue) FClass.newInstance(sInstance);
   //            // 开始执行队列
   //            oParam.unpack(oTmQueuePKG.doStartQueue(
   //                  null, oUnit.getListId(), null).getParameter("param_").getValueAsString());
   //            if (FBoolean.isTrue(oParam.getValue("RUN"))) {
   //               oProperty.append(oParam);
   //               try {
   //                  // 执行业务逻辑
   //                  oQueue.execute(oProperty, oMessage, oUnit.getContent());
   //                  // 正常停止队列
   //                  oTmQueuePKG.doStopQueue(null, oUnit.getListId(), oMessage.pack());
   //               } catch (Exception oException) {
   //                  // 异常停止队列
   //                  oTmQueuePKG.doErrorQueue(null, oUnit.getListId(), oMessage.pack());
   //                  throw oException;
   //               }
   //            }
   //         } catch (Exception oException) {
   //            FLogger.error(this, "FQueueMonitor.execute", oException);
   //         }
   //      }
   //      return true;
   //   }
}
