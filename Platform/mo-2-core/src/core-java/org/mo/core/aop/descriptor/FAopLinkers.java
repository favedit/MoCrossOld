package org.mo.core.aop.descriptor;

import org.mo.com.lang.FDictionary;

//============================================================
// <T>AOP关联器集合。</T>
//============================================================
public class FAopLinkers
      extends FDictionary<FAopLinker>
{
   //============================================================
   // <T>构造AOP关联器集合。</T>
   //============================================================
   public FAopLinkers(){
      super(FAopLinker.class);
   }
}
