package org.mo.core.aop;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RString;
import org.mo.com.lang.reflect.FMethodDescriptor;
import org.mo.com.lang.reflect.RDescriptor;
import org.mo.core.aop.container.FAopComponent;

//============================================================
// <T>AOP描述管理类。</T>
//============================================================
public class RAopDescriptor
{
   //============================================================
   // <T>查找函数描述器。</T>
   //
   // @param source 来源
   // @return 函数描述器
   //============================================================
   public static FMethodDescriptor findMethod(String source){
      String[] invokes = RString.splitTwo(source, '@', true);
      if(null != invokes){
         FAopComponent component = RAop.findComponent(invokes[1]);
         return RDescriptor.findMethod(component.faceClass().nativeObject(), invokes[0]);
      }
      return null;
   }

   //============================================================
   // <T>查找函数描述器。</T>
   //
   // @param face 接口
   // @param method 函数名称
   // @return 函数描述器
   //============================================================
   public static FMethodDescriptor findMethod(String face,
                                              String method){
      FAopComponent component = RAop.findComponent(face);
      return RDescriptor.findMethod(component.faceClass().nativeObject(), method);
   }

   //============================================================
   // <T>获得函数描述器。</T>
   //
   // @param source 来源
   // @return 函数描述器
   //============================================================
   public static FMethodDescriptor getMethod(String source){
      FMethodDescriptor descriptor = findMethod(source);
      if(null == descriptor){
         throw new FFatalError("Can't find method descriptor (source={0})", source);
      }
      return descriptor;
   }

   //============================================================
   // <T>获得函数描述器。</T>
   //
   // @param face 接口
   // @param method 函数名称
   // @return 函数描述器
   //============================================================
   public static FMethodDescriptor getMethod(String face,
                                             String method){
      FMethodDescriptor descriptor = findMethod(face, method);
      if(null == descriptor){
         throw new FFatalError("Can't find method descriptor (face={0}, method={1})", face, method);
      }
      return descriptor;
   }
}
