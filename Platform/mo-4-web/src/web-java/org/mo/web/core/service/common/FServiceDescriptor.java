package org.mo.web.core.service.common;

import java.lang.annotation.Annotation;
import org.mo.com.lang.FDictionary;
import org.mo.com.lang.FObject;
import org.mo.web.core.action.AWebLogin;

//============================================================
// <T>服务描述器。</T>
//============================================================
public class FServiceDescriptor
      extends FObject
{
   // 类实例
   protected final Class<?> _clazz;

   // 用户登录的描述器
   protected AWebLogin _loginDescriptor;

   // 函数字典
   protected FDictionary<FServiceMethodDescriptor> _methods = new FDictionary<FServiceMethodDescriptor>(FServiceMethodDescriptor.class);

   //============================================================
   // <T>构造服务描述器。</T>
   //
   // @param clazz 类对象
   //============================================================
   public FServiceDescriptor(Class<?> clazz){
      _clazz = clazz;
      build();
   }

   //============================================================
   // <T>建立内部信息。</T>
   //============================================================
   protected void build(){
      // 获得函数的描述器
      Annotation[] methodAnnotations = _clazz.getAnnotations();
      if(null != methodAnnotations){
         for(Annotation annotation : methodAnnotations){
            if(annotation instanceof AWebLogin){
               _loginDescriptor = (AWebLogin)annotation;
            }
         }
      }
   }

   //============================================================
   // <T>获得用户登录描述器。</T>
   //
   // @return 获得用户登录描述器
   //============================================================
   public AWebLogin loginDescriptor(){
      return _loginDescriptor;
   }

   //============================================================
   // <T>测试是否需要用户登录。</T>
   //
   // @return 是否需要
   //============================================================
   public boolean testRequireLogin(){
      return (null != _loginDescriptor) ? _loginDescriptor.require() : false;
   }

   //============================================================
   // <T>判断是否含有指定名称。</T>
   //
   // @param name 名称
   // @return 是否含有
   //============================================================
   public boolean contains(String name){
      return _methods.contains(name);
   }

   //============================================================
   // <T>根据函数名称查找函数描述器。</T>
   //
   // @param name 函数名称
   // @return 函数描述器
   //============================================================
   public FServiceMethodDescriptor find(String method){
      return _methods.find(method);
   }

   //============================================================
   // <T>增加一个函数描述器。</T>
   //
   // @param methodName 函数名称
   // @param methodDescriptor 函数描述器
   //============================================================
   public void push(String methodName,
                    FServiceMethodDescriptor methodDescriptor){
      if(methodDescriptor != null){
         methodDescriptor.setServiceDescriptor(this);
         _methods.set(methodName, methodDescriptor);
      }
   }
}
