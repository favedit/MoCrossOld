package org.mo.web.core.service.common;

import org.mo.core.aop.config.XAopNodeCollection;

public class XAopServiceCollection
      extends XAopNodeCollection<XAopService>
{

   public XAopServiceCollection(){
      super(XAopService.class);
   }

}
