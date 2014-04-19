package org.mo.web.core.process;

import java.lang.reflect.Method;
import org.mo.com.data.MSqlConnect;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.IRelease;
import org.mo.com.lang.RString;
import org.mo.com.lang.RThrowable;
import org.mo.com.lang.reflect.RClass;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.message.FErrorMessage;
import org.mo.com.message.FMessages;
import org.mo.core.aop.RAop;
import org.mo.core.aop.face.ALink;
import org.mo.eng.data.IDatabaseConsole;
import org.mo.eng.data.common.FSqlContext;
import org.mo.eng.data.common.ISqlContext;
import org.mo.logic.session.FSqlSessionContext;
import org.mo.logic.session.ISqlSessionContext;
import org.mo.web.core.action.FActionConsole;
import org.mo.web.core.action.servlet.IActionConstant;
import org.mo.web.core.container.AContainer;
import org.mo.web.core.container.IWebContainerConsole;
import org.mo.web.core.container.common.FWebContainerItem;
import org.mo.web.core.session.IWebSession;
import org.mo.web.protocol.context.IWebContext;

public class FWebProcessConsole
      implements
         IWebProcessConsole
{

   // 日志接口
   private static ILogger _logger = RLogger.find(FActionConsole.class);

   public final static String DEFAULT_METHOD = "construct";

   // 数据库链接控制台
   @ALink
   protected IDatabaseConsole _databaseConsole;

   // 类描述器集合
   protected FWebMethodDescriptors _descriptors = new FWebMethodDescriptors();

   // 表单存储对象控制台
   @ALink
   protected IWebContainerConsole _formConsole;

   @Override
   public Object execute(String action,
                         FWebProcessArgs args){
      String[] invokes = RString.splitTwo(action, '@', false);
      if(null == invokes){
         throw new FFatalError("Action format is invalid. (action={0})", action);
      }
      return execute(invokes[1], invokes[0], args);
   }

   @Override
   public Object execute(String faceName,
                         String methodName,
                         FWebProcessArgs args){
      // 查询接口对象
      Object face = RAop.find(faceName);
      // 执行处理函数
      return executeFace(face, methodName, args);
   }

   @Override
   public Object executeFace(Object face,
                             String methodName,
                             FWebProcessArgs args){
      // 找到当前地址对应的函数描述器
      methodName = RString.nvl(methodName, IActionConstant.DEFAULT_METHOD);
      FWebMethodDescriptor methodDsp = findMethod(face.getClass(), methodName);
      if(null == methodDsp){
         _logger.warn(this, "execute", "Can't find method in action. [{0}@{1}]", methodName, face);
         return null;
      }
      // Invoke method
      Object result = null;
      Class<?>[] types = methodDsp.types();
      AContainer[] aforms = methodDsp.forms();
      FWebContainerItem[] forms = new FWebContainerItem[types.length];
      Object[] params = new Object[types.length];
      Exception exception = null;
      IWebContext webContext = args.getWebContext();
      ISqlContext sqlContext = null;
      ISqlSessionContext sqlSessionContext = null;
      try{
         // 创建调用的参数列表
         for(int n = 0; n < types.length; n++){
            Class<?> type = types[n];
            Object value = null;
            if(type == IWebContext.class){
               // 参数为网络环境对象时
               value = webContext;
            }else if(type == IWebSession.class){
               // 参数为网络线程对象时
               value = webContext.session();
            }else if(type == ISqlContext.class){
               // 参数为数据环境对象时
               if(null == sqlContext){
                  sqlContext = new FSqlContext(_databaseConsole);
               }
               value = sqlContext;
            }else if(type == ISqlSessionContext.class){
               // 参数为网络线程数据环境对象时
               if(null == sqlSessionContext){
                  sqlSessionContext = new FSqlSessionContext(_databaseConsole);
               }
               value = sqlSessionContext;
            }else if(type.isInterface()){
               String name = RClass.shortName(type);
               if(name.startsWith("I") && name.endsWith("Di")){
                  // 如果为数据对象接口时
                  if(null == sqlSessionContext){
                     sqlSessionContext = new FSqlSessionContext(_databaseConsole);
                  }
                  name = name.substring(1, name.length() - 2);
                  name = RClass.packageName(type) + ".impl.F" + name + "Impl";
                  MSqlConnect impl = RClass.newInstance(name);
                  impl.linkConnect(sqlSessionContext);
                  value = impl;
               }else{
                  // 如果为配置对象的接口时
                  value = RAop.find(type);
               }
            }else if(aforms[n] != null){
               // 参数为表单对象时
               forms[n] = _formConsole.findContainer(webContext, aforms[n], type);
               value = forms[n].container();
               webContext.define(aforms[n].name(), value);
            }else{
               // 未知参数时
               if(args.defineContains(type)){
                  value = args.defineGet(type);
               }else{
                  throw new FFatalError("Build parameter error. {0}", type);
               }
            }
            params[n] = value;
         }
         // 如果使用线程数据库对象，则关联数据库线程
         if(null != sqlSessionContext){
            sqlSessionContext.link(webContext.session().connectId());
         }
         // 动态函数调用
         result = methodDsp.invoke(face, params);
      }catch(Exception e){
         exception = e;
      }finally{
         // 释放所有参数
         for(Object param : params){
            if((param == sqlContext) || (param == sqlSessionContext)){
               continue;
            }
            if(param instanceof IRelease){
               try{
                  ((IRelease)param).release();
               }catch(Exception e){
                  exception = e;
               }
            }
         }
         // 释放数据库线程链接
         if(null != sqlSessionContext){
            if(null == exception){
               // 正常关闭数据库链接
               sqlSessionContext.unlink();
               FMessages messages = sqlSessionContext.messages();
               if(null != messages){
                  webContext.messages().append(messages);
               }
               sqlSessionContext.release();
            }else{
               // 回滚数据库链接
               sqlSessionContext.rollback();
            }
         }
         // 释放数据库链接
         if(null != sqlContext){
            if(null == exception){
               sqlContext.release();
            }else{
               sqlContext.rollback();
            }
         }
         // 建立错误信息
         if(null != exception){
            FErrorMessage errorMessage = new FErrorMessage();
            errorMessage.setMessage(RThrowable.rootThrowable(exception).getMessage());
            errorMessage.setDescription(RThrowable.buildDescription(exception).toString());
            webContext.messages().push(errorMessage);
         }
      }
      return result;
   }

   protected FWebMethodDescriptor findMethod(Class<?> clazz,
                                             String methodName){
      String action = methodName + "@" + clazz.getName();
      // 获得函数描述器
      FWebMethodDescriptor descriptor = _descriptors.get(action);
      if(null != descriptor){
         return descriptor;
      }
      // 递归类查找函数的描述对象 
      Class<?> findClazz = clazz;
      while(null != findClazz){
         for(Method method : findClazz.getMethods()){
            if(methodName.equalsIgnoreCase(method.getName())){
               FWebMethodDescriptor methodDsp = new FWebMethodDescriptor(method);
               _descriptors.set(action, methodDsp);
               return methodDsp;
            }
         }
         findClazz = findClazz.getSuperclass();
      }
      return null;
   }

}
