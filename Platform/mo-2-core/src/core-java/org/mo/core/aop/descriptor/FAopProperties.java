package org.mo.core.aop.descriptor;

import org.mo.com.lang.FDictionary;

//============================================================
// <T>AOP属性器字典。</T>
//============================================================
public class FAopProperties
      extends FDictionary<FAopProperty>
{
   //============================================================
   // <T>构造AOP属性器字典。</T>
   //============================================================
   public FAopProperties(){
      super(FAopProperty.class);
   }
}
