/*
 * @(#)ISqlSessionContext.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.logic.session;

import org.mo.com.message.FMessages;
import org.mo.eng.data.common.ISqlContext;

/**
 * <T>线程环境接口类。</T>
 * <P>保存与线程相关的环境信息。</P>
 * 
 * @author HYKUN
 * @version 1.01 - 2008/12/29
 */
public interface ISqlSessionContext
      extends
         ISqlContext
{
   /**
    * <T>根据线程标识取得登陆信息。</T>
    * 
    * @param sessionId
    */
   void link(String sessionId);

   /**
    * <T>获得消息列表。</T>
    * 
    */
   FMessages messages();

   /**
    * <T>从session取得消息信息构造消息列表和日志信息并大约日志信息。</T>
    * 
    */
   void unlink();
}
