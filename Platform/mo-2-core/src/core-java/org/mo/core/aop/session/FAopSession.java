package org.mo.core.aop.session;

import org.mo.com.lang.FDictionary;
import org.mo.com.lang.FObject;

//============================================================
// <T>AOP会话。</T>
//============================================================
public class FAopSession
      extends FObject
{
   // 对象集合
   protected final FDictionary<Object> _objects = new FDictionary<Object>(Object.class);

   //============================================================
   // <T>构造AOP会话。</T>
   //============================================================
   public FAopSession(){
   }

   //============================================================
   // <T>根据接口名称查找对象。</T>
   //
   // @param face 接口名称
   // @return 对象
   //============================================================
   @SuppressWarnings("unchecked")
   public <V> V find(String face){
      return (V)_objects.get(face);
   }

   //============================================================
   // <T>设置接口名称和对象。</T>
   //
   // @param face 接口名称
   // @param value 对象
   //============================================================
   public void set(String face,
                   Object value){
      _objects.set(face, value);
   }
}
