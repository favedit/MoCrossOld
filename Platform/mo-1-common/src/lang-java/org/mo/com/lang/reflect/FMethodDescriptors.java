package org.mo.com.lang.reflect;

import org.mo.com.lang.FDictionary;

//============================================================
// <T>函数描述器字典。</T>
//============================================================
public class FMethodDescriptors
      extends FDictionary<FMethodDescriptor>
{
   //============================================================
   // <T>构造函数描述器字典。</T>
   //============================================================
   public FMethodDescriptors(){
      super(FMethodDescriptor.class);
   }
}
