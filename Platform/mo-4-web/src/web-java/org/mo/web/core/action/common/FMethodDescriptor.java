/*
 * @(#)FMethodDescriptor.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.web.core.action.common;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import org.mo.com.data.ASqlConnect;
import org.mo.com.lang.FFatalError;
import org.mo.web.core.action.AWebLogin;
import org.mo.web.core.container.AContainer;

/**
 * <T>页面命令函数的描述器。</T>
 * 
 * @author MAOCY
 * @version 1.00 - 2008/12/19
 */
public class FMethodDescriptor
{

   // 页面命令的描述器
   private FActionDescriptor _actionDescriptor;

   // 表单描述器数组
   private AContainer[] _forms;

   // 数据链接数组
   private ASqlConnect[] _sqlConnects;

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
   public FMethodDescriptor(Method method){
      _method = method;
      build();
   }

   /**
    * <T>设置页面命令描述器。</T>
    * 
    * @return 命令描述器
    */
   public FActionDescriptor actionDescriptor(){
      return _actionDescriptor;
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
      _sqlConnects = new ASqlConnect[_types.length];
      // 获得函数的参数描述器
      Annotation[][] annos = _method.getParameterAnnotations();
      for(int n = 0; n < _types.length; n++){
         for(Annotation anno : annos[n]){
            if(anno instanceof AContainer){
               _forms[n] = (AContainer)anno;
               break;
            }
            if(anno instanceof ASqlConnect){
               _sqlConnects[n] = (ASqlConnect)anno;
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

   public ASqlConnect[] sqlConnects(){
      return _sqlConnects;
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
    * <T>设置页面命令描述器。</T>
    * 
    * @param actionDescriptor 命令描述器
    */
   public void setActionDescriptor(FActionDescriptor actionDescriptor){
      _actionDescriptor = actionDescriptor;
   }

   /**
    * <T>测试是否需要用户登录。</T>
    * 
    * @return 是否需要
    */
   public boolean testRequireLogin(){
      if(null != _loginDescriptor){
         return _loginDescriptor.require();
      }
      return _actionDescriptor.testRequireLogin();
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
