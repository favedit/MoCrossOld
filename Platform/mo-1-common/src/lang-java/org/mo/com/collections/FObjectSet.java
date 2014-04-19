package org.mo.com.collections;

import org.mo.com.lang.generic.MIntSet;

//============================================================
// <T>对象集合。</T>
//============================================================
public class FObjectSet
      extends MIntSet<Object>
{
   //============================================================
   // <T>构造对象集合。</T>
   //============================================================
   public FObjectSet(){
      super(Object.class);
   }
}
