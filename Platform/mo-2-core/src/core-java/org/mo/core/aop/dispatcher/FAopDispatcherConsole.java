package org.mo.core.aop.dispatcher;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import org.mo.com.console.FConsole;
import org.mo.com.lang.FObjects;
import org.mo.com.lang.FString;
import org.mo.com.lang.RString;
import org.mo.com.lang.reflect.FClass;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;

//============================================================
// <T>AOP处理器控制台。</T>
//============================================================
public class FAopDispatcherConsole
      extends FConsole
{
   // 日志输出接口
   protected static ILogger _logger = RLogger.find(FAopDispatcherConsole.class);

   // 处理器集合
   protected FObjects<IAopDispatcher> _dispatchers = new FObjects<IAopDispatcher>(IAopDispatcher.class);

   //============================================================
   // <T>构造AOP处理器控制台。</T>
   //============================================================
   public FAopDispatcherConsole(){
   }

   //============================================================
   // <T>创建处理对象。</T>
   //============================================================
   @SuppressWarnings("unchecked")
   public <V> V createDispatchObject(Object component,
                                     FClass<?> face){
      InvocationHandler handler = new FAopDispatcher(this, component);
      ClassLoader loader = component.getClass().getClassLoader();
      Class<?>[] faceClasses = new Class<?>[]{face.nativeObject()};
      return (V)Proxy.newProxyInstance(loader, faceClasses, handler);
   }

   //============================================================
   // <T>调用开始处理。</T>
   //
   // @param instance 实例
   // @param proxy 代理
   // @param method 函数
   // @param args 参数集合
   //============================================================
   public void invokeBefore(Object instance,
                            Object proxy,
                            Method method,
                            Object[] args){
      if(_logger.debugAble()){
         FString message = new FString();
         //message.append(proxy.getClass().getName());
         //message.append(".");
         //message.append(method.getName());
         if(args != null){
            int count = args.length;
            message.append("(");
            for(int n = 0; n < count; n++){
               if(n > 0){
                  message.append(", ");
               }
               message.append(args[n]);
            }
            message.append(") ");
         }
         _logger.debug(instance, method.getName() + " >", message.toString());
      }
   }

   //============================================================
   // <T>调用结束处理。</T>
   //
   // @param instance 实例
   // @param proxy 代理
   // @param method 函数
   // @param result 结果
   // @param args 参数集合
   //============================================================
   public void invokeAfter(Object instance,
                           Object proxy,
                           Method method,
                           Object result,
                           Object[] args){
      if(_logger.debugAble()){
         FString message = new FString();
         if(result instanceof String){
            result = RString.singleLine((String)result);
         }
         message.append(result);
         _logger.debug(instance, method.getName() + " <", message.toString());
      }
   }

   //============================================================
   // <T>调用例外处理。</T>
   //
   // @param instance 实例
   // @param proxy 代理
   // @param method 函数
   // @param args 参数集合
   // @param exception 例外处理
   //============================================================
   public boolean invokeException(Object instance,
                                  Object proxy,
                                  Method method,
                                  Object[] args,
                                  Exception exception){
      int count = _dispatchers.count();
      for(int n = 0; n < count; n++){
         if(_dispatchers.get(n).process(exception)){
            return true;
         }
      }
      FString message = new FString("exception......");
      if(args != null){
         count = args.length;
         message.append("(");
         for(int n = 0; n < count; n++){
            if(n > 0){
               message.append(", ");
            }
            message.append(args[n]);
         }
         message.append(") ");
      }
      _logger.error(this, "invokeException", exception, message.toString());
      return false;
   }
}
