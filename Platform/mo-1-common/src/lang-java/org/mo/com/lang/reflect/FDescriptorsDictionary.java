package org.mo.com.lang.reflect;

import org.mo.com.lang.FDictionary;

//============================================================
// <T>描述器集合字典。</T>
//============================================================
public class FDescriptorsDictionary
      extends FDictionary<FDescriptors>
{
   //============================================================
   // <T>构造描述器集合字典。</T>
   //============================================================
   public FDescriptorsDictionary(){
      super(FDescriptors.class);
   }
}
