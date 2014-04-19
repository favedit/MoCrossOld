package org.mo.eng.validator;

import org.mo.com.lang.FDictionary;

//============================================================
// <T>校验器集合。</T>
//============================================================
public class FValidators
      extends FDictionary<IValidator>
{
   //============================================================
   // <T>构造校验器集合。</T>
   //============================================================
   public FValidators(){
      super(IValidator.class);
   }
}
