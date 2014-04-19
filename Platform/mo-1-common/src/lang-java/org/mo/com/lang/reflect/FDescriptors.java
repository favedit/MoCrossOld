package org.mo.com.lang.reflect;

import org.mo.com.lang.FDictionary;

//============================================================
// <T>描述器集合。</T>
//============================================================
public class FDescriptors
      extends FDictionary<MDescriptor>
{
   //============================================================
   // <T>构造描述器集合。</T>
   //============================================================
   public FDescriptors(){
      super(MDescriptor.class);
   }
}
