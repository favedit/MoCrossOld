package org.mo.com.lang.reflect;

import org.mo.com.lang.FDictionary;

//============================================================
// <T>类对象描述器字典。</T>
//============================================================
public class FClassDescriptors
      extends FDictionary<FClassDescriptor>
{
   //============================================================
   // <T>构造类对象描述器字典。</T>
   //============================================================
   public FClassDescriptors(){
      super(FClassDescriptor.class);
   }
}
