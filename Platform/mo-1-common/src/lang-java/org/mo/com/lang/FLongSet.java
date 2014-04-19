package org.mo.com.lang;

import org.mo.com.lang.generic.MLongSet;

//============================================================
// <T>长整数集合。</T>
//============================================================
public class FLongSet<V>
      extends MLongSet<V>
{
   //============================================================
   // <T>构造长整数集合。</T>
   //
   // @param clazz 类对象
   //============================================================
   public FLongSet(Class<V> clazz){
      super(clazz);
   }
}
