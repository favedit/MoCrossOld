package org.mo.com.lang.generic;

//============================================================
// <T>命名对象集合。</T>
//============================================================
public abstract class MStringNamedObjects<T extends IStringNamed>
      extends MNamedObjects<T, String>
{
   //============================================================
   // <T>构造命名对象集合。</T>
   //
   // @param clazz 类对象
   //============================================================
   public MStringNamedObjects(Class<T> clazz){
      super(clazz);
   }
}
