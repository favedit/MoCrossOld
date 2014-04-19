package org.mo.com.lang.reflect;

import org.mo.com.lang.FDictionary;

//============================================================
// <T>调用函数对象字典。</T>
//============================================================
public class FInvokeMethodDictionary
      extends FDictionary<FInvokeMethod>
{
   //============================================================
   // <T>构造调用函数对象字典。</T>
   //============================================================
   public FInvokeMethodDictionary(){
      super(FInvokeMethod.class);
   }
}
