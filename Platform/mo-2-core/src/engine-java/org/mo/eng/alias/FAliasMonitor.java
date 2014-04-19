/*
 * @(#)FAliasMonitor.java
 *
 * Copyright 2004 Java Frame Studio Corporation.
 * All Rights Reserved.
 *
 */
package org.mo.eng.alias;

/**
 * <p>别称监视器</p>
 * <p>1. 当别称文件被修改时</p>
 * <p>   通知别称控制台重新录入被修改的别称文件</p>
 *
 * @author FEDT
 * @version 1.00 - 2005/02/18
 */
public class FAliasMonitor
{
   //   // 关联的别称控制台
   //   private FAliasConsole m_oAliasConsole = null;
   //
   //   /**
   //    * <p>创建别称监视器的实例</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    */
   //   public FAliasMonitor() {
   //   }
   //
   //   /**
   //    * <p>创建别称监视器的实例</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param oAliasConsole 别称控制台
   //    * @param sFileName 设置文件
   //    */
   //   public FAliasMonitor(FAliasConsole oAliasConsole,
   //                        String sFileName) {
   //      super(sFileName);
   //      m_oAliasConsole = oAliasConsole;
   //   }
   //
   //   /**
   //    * <p>响应发生变动的文档</p>
   //    * <p>create date:2005/02/18</p>
   //    *
   //    * @param sFileName 文件名称
   //    * @return TRUE：成功<br>FALSE：失败
   //    * @exception FException 应用程序例外
   //    */
   //   public boolean onFileChanged()
   //         throws FException {
   //      if (super.onFileChanged()) {
   //         return m_oAliasConsole.loadAlias();
   //      }
   //      return false;
   //   }
}
