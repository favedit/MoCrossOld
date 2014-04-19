/*
 * @(#)FDatabaseMonitor.java
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
public abstract class FDatabaseMonitor
{
   //   // 序列化标志
   //   private static final long serialVersionUID = 1L;
   //
   //   /**
   //    * <p>执行业务的逻辑</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param oContext 环境变量
   //    * @return TRUE：成功<br>FALSE：失败
   //    * @exception FException 应用程序例外
   //    */
   //   public abstract boolean execute(FContext oContext)
   //         throws FException;
   //
   //   /**
   //    * <p>执行事件的逻辑</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @return TRUE：成功<br>FALSE：失败
   //    * @exception FException 应用程序例外
   //    */
   //   public boolean execute()
   //         throws FException {
   //      FDBConnection oConnection =
   //            FDatabaseManager.getInstance().allocateConnection("Monitor");
   //      if (oConnection == null) {
   //         throw new FFatalException(
   //               this, "Allocate connection failed in event " + this);
   //      }
   //      boolean bResult = false;
   //      try {
   //         FContext oContext = new FContext();
   //         oContext.setActiveConnection(oConnection);
   //         FSysTransferPkg oSysTransferPkg = new FSysTransferPkg(oContext);
   //         FStringList oSessionUser = new FStringList();
   //         oSessionUser.setValue("session_user", -2);
   //         oSysTransferPkg.reset(oSessionUser.pack());
   //         bResult = execute(oContext);
   //         FDBProcedure oProcedure = oSysTransferPkg.fetchResult();
   //         String sLogger = oProcedure.getParameter("logger_").getValueAsString();
   //         if (FString.isNotEmpty(sLogger) && FLogger.canDebug()) {
   //            FStringList oLogger = new FStringList(true);
   //            oLogger.unpack(sLogger);
   //            int nCount = oLogger.getCount();
   //            for (int n = 0; n < nCount; n++) {
   //               FLogger.debug(this, oLogger.getKey(n), oLogger.getValue(n));
   //            }
   //         }
   //      } catch (Exception oException) {
   //         throw new FFatalException(this, oException);
   //      } finally {
   //         oConnection.close();
   //      }
   //      return bResult;
   //   }
   //
}
