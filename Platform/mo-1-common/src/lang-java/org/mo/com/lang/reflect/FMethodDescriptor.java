package org.mo.com.lang.reflect;

import java.lang.annotation.Annotation;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObject;

//============================================================
// <T>函数描述器。</T>
//============================================================
public class FMethodDescriptor
      extends FObject
{
   // 函数对象
   protected FMethod _method;

   // 类型集合
   protected Class<?>[] _types;

   //============================================================
   // <T>构造函数描述器。</T>
   //============================================================
   public FMethodDescriptor(){
   }

   //============================================================
   // <T>获得函数对象。</T>
   //
   // @return 函数对象
   //============================================================
   public FMethod method(){
      return _method;
   }

   //============================================================
   // <T>关联处理。</T>
   //
   // @param method 函数对象
   //============================================================
   public void link(FMethod method){
      _method = method;
      _types = method.nativeObject().getParameterTypes();
   }

   //============================================================
   // <T>获得类型集合。</T>
   //
   // @return 类型集合
   //============================================================
   public Class<?>[] parameterTypes(){
      return _types;
   }

   //============================================================
   // <T>获得参数个数。</T>
   //
   // @return 参数个数
   //============================================================
   public int parameterCount(){
      return _types.length;
   }

   //============================================================
   // <T>获得参数类型。</T>
   //
   // @param index 索引位置
   // @return 参数类型
   //============================================================
   public Class<?> parameterType(int index){
      return _types[index];
   }

   //============================================================
   // <T>获得参数描述。</T>
   //
   // @param index 索引位置
   // @param type 类型
   // @return 参数描述
   //============================================================
   public <A extends Annotation> A parameterAnnotation(int index,
                                                       Class<A> type){
      Class<?> clazz = _types[index];
      return clazz.getAnnotation(type);
   }

   //============================================================
   // <T>创建参数集合。</T>
   //
   // @return 参数集合
   //============================================================
   public Object[] createParameters(){
      return new Object[_types.length];
   }

   //============================================================
   // <T>调用处理。</T>
   //
   // @param item 对象
   // @param params 参数集合
   // @return 处理结果
   //============================================================
   public Object invoke(Object item,
                        Object[] params){
      try{
         return _method.nativeObject().invoke(item, params);
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }
}
