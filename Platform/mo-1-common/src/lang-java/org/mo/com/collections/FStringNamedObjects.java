package org.mo.com.collections;

import org.mo.com.lang.generic.IStringNamed;
import org.mo.com.lang.generic.MStringNamedObjects;

//============================================================
// <T>字符串循环链表。</T>
//============================================================
public class FStringNamedObjects<T extends IStringNamed>
      extends MStringNamedObjects<T>
{
   //============================================================
   // <T>构造命名对象集合。</T>
   //
   // @param clazz 类对象
   //============================================================
   public FStringNamedObjects(Class<T> clazz){
      super(clazz);
   }
}
