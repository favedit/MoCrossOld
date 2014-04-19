/*
 * @(#)FActionDescriptor.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.web.core.action.common;

import java.lang.annotation.Annotation;
import org.mo.com.lang.FDictionary;
import org.mo.web.core.action.AWebLogin;

/**
 * <T>页面命令的描述器。</T>
 * 
 * @author MAOCY
 * @version 1.00 - 2008/12/19
 */
public class FActionDescriptor
{

   // 类实例
   private Class<?> _clazz;

   // 用户登录的描述器
   private AWebLogin _loginDescriptor;

   // 函数字典
   private FDictionary<FMethodDescriptor> _methods = new FDictionary<FMethodDescriptor>(FMethodDescriptor.class);

   /**
    * <T>生成某个函数描述器的实例。</T>
    * 
    * @param method 函数对象
    */
   public FActionDescriptor(Class<?> clazz){
      _clazz = clazz;
      build();
   }

   // 建立内部信息
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

   /**
    * <T>是否含有某个函数内容。</T>
    * 
    * @param methodName 函数名称
    * @return 是否含有
    */
   public boolean contains(String methodName){
      return _methods.contains(methodName);
   }

   /**
    * <T>查找函数描述器。</T>
    * 
    * @param methodName 函数名称
    * @return 函数描述器
    */
   public FMethodDescriptor find(String methodName){
      return _methods.get(methodName);
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
    * <T>设置函数描述器。</T>
    * 
    * @param methodName 函数名称
    * @param methodDsp 函数描述器
    */
   public void push(String methodName,
                    FMethodDescriptor methodDsp){
      methodDsp.setActionDescriptor(this);
      _methods.set(methodName, methodDsp);
   }

   /**
    * <T>测试是否需要用户登录。</T>
    * 
    * @return 是否需要
    */
   public boolean testRequireLogin(){
      return (null != _loginDescriptor) ? _loginDescriptor.require() : false;
   }

}
