/*
 * @(#)EndProcessAction.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.jfa.face.inc.page;

import org.mo.web.protocol.context.IWebContext;

/**
 * <p>处理结束页面</p>
 * 
 * @author ALEX
 */
public class FEndDataAction
      implements
         IEndDataAction
{

   @Override
   public String construct(IWebContext context){
      return null;
   }

   @Override
   public String delete(IWebContext context){
      return "#/inc/page/EndDelete.jsp";
   }

   @Override
   public String insert(IWebContext context){
      return "#/inc/page/EndInsert.jsp";
   }

   @Override
   public String update(IWebContext context){
      return "#/inc/page/EndUpdate.jsp";
   }

}
