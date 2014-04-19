/*
 * @(#)FMasterAction.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.game.editor.face.apl.page;

import org.mo.web.protocol.context.IWebContext;

/**
 * <p>处理结束页面</p>
 * 
 * @author MAOCY
 */
public class FInnerAction
      implements
         IInnerAction{

   @Override
   public String success(IWebContext context){
      return "InnerSuccess";
   }

}
