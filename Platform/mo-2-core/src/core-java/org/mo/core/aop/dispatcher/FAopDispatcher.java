package org.mo.core.aop.dispatcher;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import org.mo.core.aop.face.ADispatcher;

//============================================================
// <T>构造AOP分发器。</T>
//============================================================
public class FAopDispatcher
      implements
         InvocationHandler
{
   // 分发控制台
   protected FAopDispatcherConsole _dispatcherConsole;

   // 委托对象
   protected Object _delegate;

   //============================================================
   // <T>构造AOP分发器。</T>
   //
   // @param dispatcherConsole 分发控制台
   // @param delegate 委托对象
   //============================================================
   public FAopDispatcher(FAopDispatcherConsole dispatcherConsole,
                         Object delegate){
      _dispatcherConsole = dispatcherConsole;
      _delegate = delegate;
   }

   //============================================================
   // <T>回调处理。</T>
   //
   // @param proxy 代理对象
   // @param method 函数
   // @param args 参数集合
   //============================================================
   @Override
   public Object invoke(Object proxy,
                        Method method,
                        Object[] args) throws Throwable{
      Object result = null;
      try{
         // 获得分发器
         ADispatcher adispatcher = method.getAnnotation(ADispatcher.class);
         // 开始处理
         if(null != adispatcher){
            _dispatcherConsole.invokeBefore(_delegate, proxy, method, args);
         }
         // 执行处理
         result = method.invoke(_delegate, args);
         // 结束处理
         if(null != adispatcher){
            _dispatcherConsole.invokeAfter(_delegate, proxy, method, result, args);
         }
      }catch(Exception e){
         // 例外处理
         if(!_dispatcherConsole.invokeException(_delegate, proxy, method, args, e)){
            throw e;
         }
      }
      return result;
   }
}
