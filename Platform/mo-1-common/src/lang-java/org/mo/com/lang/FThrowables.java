package org.mo.com.lang;

import org.mo.com.lang.generic.MObjects;

//============================================================
// <T>抛出例外集合。</T>
//============================================================
public class FThrowables
      extends MObjects<Throwable>
{
   //============================================================
   // <T>构造抛出例外集合。</T>
   //============================================================
   public FThrowables(){
      super(Throwable.class);
   }
}
