/*
 * @(#)FMethodDescriptor.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.web.core.process;

import org.mo.web.core.container.AContainer;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import org.mo.com.lang.FFatalError;
import org.mo.web.core.action.AWebLogin;

/**
 * <T>页面命令函数的描述器。</T>
 * 
 * @author MAOCY
 * @version 1.00 - 2008/12/19
 */
public class FWebMethodDescriptor
{

   // 表单描述器数组
   private AContainer[] _forms;

   // 用户登录的描述器
   private AWebLogin _loginDescriptor;

   // 调用函数对象
   private Method _method;

   // 类描述对象
   private Class<?>[] _types;

   /**
    * <T>生成某个函数描述器的实例。</T>
    * 
    * @param method 函数对象
    */
   public FWebMethodDescriptor(Method method){
      _method = method;
      build();
   }

   // 建立内部信息
   protected void build(){
      // 获得函数的描述器
      Annotation[] methodAnnotations = _method.getAnnotations();
      if(null != methodAnnotations){
         for(Annotation annotation : methodAnnotations){
            if(annotation instanceof AWebLogin){
               _loginDescriptor = (AWebLogin)annotation;
            }
         }
      }
      // 获得函数的参数信息
      _types = _method.getParameterTypes();
      _forms = new AContainer[_types.length];
      // 获得函数的参数描述器
      Annotation[][] annos = _method.getParameterAnnotations();
      for(int n = 0; n < _types.length; n++){
         for(Annotation anno : annos[n]){
            if(anno instanceof AContainer){
               _forms[n] = (AContainer)anno;
               break;
            }
         }
      }
   }

   /**
    * <T>获得函数中的表单描述器数组。</T>
    * 
    * @return 表单描述器数组
    */
   public AContainer[] forms(){
      return _forms;
   }

   /**
    * <T>进行函数调用的处理。</T>
    * 
    * @param action 页面命令实例
    * @param params 参数集合
    * @return 调用结果
    */
   public String invoke(Object action,
                        Object[] params){
      try{
         return (String)_method.invoke(action, params);
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   /**
    * <T>获得用户登录描述器。</T>
    * 
    * @return 用户登录描述器
    */
   public AWebLogin loginDescriptor(){
      return _loginDescriptor;
   }

   /**
    * <T>获得函数中的函数对象。</T>
    * 
    * @return 函数对象
    */
   public Method method(){
      return _method;
   }

   /**
    * <T>获得函数中的参数类型数组。</T>
    * 
    * @return 参数类型数组
    */
   public Class<?>[] types(){
      return _types;
   }

}
