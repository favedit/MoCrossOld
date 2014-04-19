package org.mo.com.lang.reflect;

import org.mo.com.lang.FObjects;

//============================================================
// <T>调用函数对象集合。</T>
//============================================================
public class FInvokeMethods
      extends FObjects<FInvokeMethod>
{
   //============================================================
   // <T>构造调用函数对象字典。</T>
   //============================================================
   public FInvokeMethods(){
      super(FInvokeMethod.class);
   }

   //============================================================
   // <T>根据名称查找调用函数对象。</T>
   //
   // @param name 名称
   // @return 调用函数对象
   //============================================================
   public FInvokeMethod findByName(String name){
      for(int n = 0; n < _count; n++){
         FInvokeMethod method = _items[n];
         if(null != method){
            if(name.equals(method.name())){
               return method;
            }
         }
      }
      return null;
   }
}
