package org.mo.core.aop.descriptor;

import org.mo.com.lang.FDictionary;

//============================================================
// <T>AOP描述器字典。</T>
//============================================================
public class FAopDescriptors
      extends FDictionary<IAopDescriptor>
{
   //============================================================
   // <T>构造AOP描述器字典。</T>
   //============================================================
   public FAopDescriptors(){
      super(IAopDescriptor.class);
   }
}
