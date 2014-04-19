package org.mo.web.core.service.common;

import org.mo.web.core.container.AContainer;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObject;
import org.mo.web.core.action.AWebLogin;

//============================================================
// <T>服务函数描述器。</T>
//============================================================
public class FServiceMethodDescriptor
      extends FObject
{
   // 调用函数对象
   private final Method _method;

   // 表单描述器数组
   private AContainer[] _forms;

   // 类描述对象
   private Class<?>[] _types;

   // 用户登录的描述器
   private AWebLogin _loginDescriptor;

   // 页面命令的描述器
   private FServiceDescriptor _serviceDescriptor;

   //============================================================
   // <T>构造服务函数描述器。</T>
   //
   // @param method 函数
   //============================================================
   public FServiceMethodDescriptor(Method method){
      _method = method;
      build();
   }

   //============================================================
   // <T>建立内部信息。</T>
   //============================================================
   protected void build(){
      // 获得函数的描述器
      Annotation[] methodAnnotations = _method.getAnnotations();
      if(methodAnnotations != null){
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

   //============================================================
   // <T>获得类型集合。</T>
   //
   // @return 类型集合
   //============================================================
   public Class<?>[] types(){
      return _types;
   }

   //============================================================
   // <T>获得表单集合。</T>
   //
   // @return 表单集合
   //============================================================
   public AContainer[] forms(){
      return _forms;
   }

   //============================================================
   // <T>获得用户登录描述器。</T>
   //
   // @return 用户登录描述器
   //============================================================
   public AWebLogin loginDescriptor(){
      return _loginDescriptor;
   }

   //============================================================
   // <T>设置服务描述器。</T>
   //
   // @param serviceDescriptor 服务描述器
   //============================================================
   public void setServiceDescriptor(FServiceDescriptor serviceDescriptor){
      _serviceDescriptor = serviceDescriptor;
   }

   //============================================================
   // <T>测试是否需要用户登录。</T>
   //
   // @return 是否需要
   //============================================================
   public boolean testRequireLogin(){
      if(_loginDescriptor != null){
         return _loginDescriptor.require();
      }
      return _serviceDescriptor.testRequireLogin();
   }

   //============================================================
   // <T>函数调用处理。</T>
   //
   // @param action 命令对象
   // @param params 参数集合
   // @return 表单集合
   //============================================================
   public String invoke(Object action,
                        Object[] params){
      try{
         return (String)_method.invoke(action, params);
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }
}
