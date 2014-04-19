package org.mo.web.core.action.common;

import org.mo.com.lang.FMap;
import org.mo.web.protocol.context.FPageConfig;

@SuppressWarnings("rawtypes")
public class FPageConfigClassMap
      extends FMap<Class, FPageConfig>
{

   public FPageConfigClassMap(){
      super(Class.class, FPageConfig.class);
   }
}
