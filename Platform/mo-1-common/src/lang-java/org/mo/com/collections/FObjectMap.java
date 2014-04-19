package org.mo.com.collections;

import org.mo.com.lang.generic.IPair;
import org.mo.com.lang.generic.MMap;

//============================================================
// <T>对象哈希表。</T>
//============================================================
public class FObjectMap
      extends MMap<IPair<Object, Object>, Object, Object>
{
   //============================================================
   // <T>构造对象哈希表。</T>
   //============================================================
   public FObjectMap(){
      super(Object.class, Object.class);
   }
}
