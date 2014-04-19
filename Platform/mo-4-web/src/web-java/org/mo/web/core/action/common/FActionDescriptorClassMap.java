package org.mo.web.core.action.common;

import org.mo.com.lang.FMap;

@SuppressWarnings("rawtypes")
public class FActionDescriptorClassMap
      extends FMap<Class, FActionDescriptor>
{

   public FActionDescriptorClassMap(){
      super(Class.class, FActionDescriptor.class);
   }

}
