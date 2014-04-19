/*
 * @(#)EndProcessAction.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.jfa.face.apl.page.process;

import org.mo.web.protocol.context.IWebContext;

/**
 * <p>处理结束页面</p>
 * 
 * @author ALEX
 */
public class FEndProcessAction
      implements
         IEndProcessAction
{

   @Override
   public String construct(IWebContext context){
      return null;
   }

   @Override
   public String delete(IWebContext context){
      return "#/apl/page/process/EndDelete.jsp";
   }

   @Override
   public String insert(IWebContext context){
      return "#/apl/page/process/EndInsert.jsp";
   }

   @Override
   public String update(IWebContext context){
      return "#/apl/page/process/EndUpdate.jsp";
   }
}
