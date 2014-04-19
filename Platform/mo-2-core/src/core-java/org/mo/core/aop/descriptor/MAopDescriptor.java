package org.mo.core.aop.descriptor;

import org.mo.com.lang.reflect.FClass;

//============================================================
// <T>AOP描述器。</T>
//============================================================
public abstract class MAopDescriptor
      implements
         IAopDescriptor
{
   // 类对象
   protected FClass<?> _class;

   //============================================================
   // <T>构造AOP描述器。</T>
   //============================================================
   public MAopDescriptor(){
   }

   //============================================================
   // <T>关联类对象。</T>
   //
   // @param clazz 类对象
   //============================================================
   @Override
   public void linkClass(FClass<?> clazz){
      _class = clazz;
   }
}
