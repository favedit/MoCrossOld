package org.mo.com.lang;

import org.mo.com.lang.generic.INamed;

//============================================================
// <T>命名对象。</T>
//============================================================
public class FNamedObject<T>
      extends FObject
      implements
         INamed<T>
{
   // 名称
   protected T _name;

   //============================================================
   // <T>获得名称。</T>
   //
   // @return 名称
   //============================================================
   public T name(){
      return _name;
   }
}
