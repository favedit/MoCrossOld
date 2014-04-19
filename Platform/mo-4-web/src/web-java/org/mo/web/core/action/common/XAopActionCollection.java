package org.mo.web.core.action.common;

import org.mo.core.aop.config.XAopNodeCollection;

public class XAopActionCollection
      extends XAopNodeCollection<XAopAction>
{

   public XAopActionCollection(){
      super(XAopAction.class);
   }

}
