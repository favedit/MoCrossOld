package org.mo.com.collections;

import org.mo.com.lang.FDictionary;

//============================================================
// <T>对象字典。</T>
//============================================================
public class FObjectDictionary
      extends FDictionary<Object>
{
   //============================================================
   // <T>构造对象字典。</T>
   //============================================================
   public FObjectDictionary(){
      super(Object.class);
   }
}
