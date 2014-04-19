package org.mo.com.lang.reflect;

import java.lang.annotation.Annotation;
import org.mo.com.lang.FObject;

//============================================================
// <T>描述定义。</T>
//============================================================
public class FAnnotation
      extends FObject
{
   // 描述对象
   protected Annotation _annotation;

   //============================================================
   // <T>构造描述定义。</T>
   //============================================================
   public FAnnotation(Annotation annotation){
      _annotation = annotation;
   }

   //============================================================
   // <T>获得描述对象。</T>
   //
   // @return 描述对象
   //============================================================
   public Annotation annotation(){
      return _annotation;
   }

   //============================================================
   // <T>是否从指定类对象继承。</T>
   //
   // @param clazz 指定类对象
   // @return 是否继承
   //============================================================
   public boolean isClass(Class<?> clazz){
      return _annotation.annotationType().isAssignableFrom(clazz);
   }
}
