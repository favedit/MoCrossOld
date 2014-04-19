package org.mo.com.lang;

import org.mo.com.lang.face.AObjects;
import org.mo.com.lang.generic.IPair;
import org.mo.com.lang.generic.MPairs;

//============================================================
// <T>值对集合。</T>
//============================================================

@AObjects
public class FPairs<N, V>
      extends MPairs<IPair<N, V>, N, V>
{
   //============================================================
   // <T>构造值对集合。</T>
   //
   // @param nameClass 名称类名
   // @param valueClass 内容类名
   //============================================================
   public FPairs(Class<N> nameClass,
                 Class<V> valueClass){
      super(nameClass, valueClass);
   }

   //============================================================
   // <T>构造值对集合。</T>
   //
   // @param nameClass 名称类名
   // @param valueClass 内容类名
   // @param capacity 容量
   //============================================================
   public FPairs(Class<N> nameClass,
                 Class<V> valueClass,
                 int capacity){
      super(nameClass, valueClass, capacity);
   }
}
