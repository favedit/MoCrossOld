/*
 * @(#)ErrorAction.java
 *
 * Copyright 2005 microbject, All Rights Reserved.
 *
 */
package org.mo.jfa.face.inc.message;

import org.mo.com.lang.FError;
import org.mo.web.protocol.context.IWebContext;

/**
 * <p>业务错误页面</p>
 * 
 * @author ALEX
 * @version 1.00 - 2005/11/03
 */
public class ErrorAction
{

   /**
    * <p>执行业务逻辑</p>
    * <p>create date:2005/04/06</p>
    * 
    * @param iContext 环境对象
    * @exception FError 业务逻辑例外
    * @exception Exception 例外
    */
   public void doAction(IWebContext iContext){
      //      FMessageForm oMsgForm = (FMessageForm) iContext.form(FMessageForm.class);
      //      FErrorMessage oMessage = oMsgForm.errorMessage();
      //      if(oMessage != null){
      //         oMsgForm.set("message", oMessage.message());
      //         oMsgForm.set("description", oMessage.description());
      //         oMsgForm.commit();
      //      }
   }

}
