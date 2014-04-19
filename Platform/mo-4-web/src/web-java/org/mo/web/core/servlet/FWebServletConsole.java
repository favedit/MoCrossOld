/*
 * @(#)FWebServletConsole.java
 *
 * Copyright 2008 microbject, All Rights Reserved.
 *
 */
package org.mo.web.core.servlet;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FMap;
import org.mo.com.lang.IRelease;
import org.mo.com.lang.RString;
import org.mo.com.lang.reflect.FClass;
import org.mo.com.lang.reflect.FMethod;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.message.FFatalMessage;
import org.mo.core.aop.RAop;
import org.mo.core.aop.face.ALink;
import org.mo.core.aop.face.AProperty;
import org.mo.core.bind.IBindConsole;
import org.mo.eng.data.IDatabaseConsole;
import org.mo.eng.data.common.FSqlContext;
import org.mo.eng.data.common.ISqlContext;
import org.mo.web.core.container.AContainer;
import org.mo.web.core.container.IWebContainerConsole;
import org.mo.web.core.container.common.FWebContainerItem;
import org.mo.web.core.servlet.common.FServletDescriptor;
import org.mo.web.core.servlet.common.FServletMethodDescriptor;
import org.mo.web.core.servlet.common.FWebServlet;
import org.mo.web.core.servlet.common.FWebServletMap;
import org.mo.web.core.servlet.common.IWebServletResponse;
import org.mo.web.core.servlet.common.XAopServlet;
import org.mo.web.protocol.context.IWebContext;

/**
 * <p>网络解析控制台</p>
 * 
 * @author ALEX
 */
public class FWebServletConsole
      implements
         IWebServletConsole
{

   private static ILogger _logger = RLogger.find(FWebServletConsole.class);

   @ALink
   protected IBindConsole _bindConsole;

   @ALink
   protected IDatabaseConsole _databaseConsole;

   @SuppressWarnings("rawtypes")
   protected FMap<Class, FServletDescriptor> _descriptors = new FMap<Class, FServletDescriptor>(Class.class, FServletDescriptor.class);

   // 传送数据时使用编码方式
   @AProperty
   protected String _encoding;

   @ALink
   protected IWebContainerConsole _formConsole;

   protected FWebServletMap _servlets = new FWebServletMap();

   public String encoding(){
      return _encoding;
   }

   @Override
   public void execute(String name,
                       IWebContext context,
                       IWebServletResponse response){
      FWebServlet servlet = findServlet(name);
      if(null == servlet){
         _logger.warn(this, "execute", "Can't find servlet config [{0} -> {1}]", name, servlet);
         return;
      }
      Object instance = findInstance(name);
      if(null == instance){
         _logger.warn(this, "execute", "Can't find servlet instance [{0} -> {1}]", name, instance);
         return;
      }
      // find invoke method
      String action = RString.nvl(context.parameter("do"), "process");
      FServletMethodDescriptor methodDsp = findMethod(servlet.faceClass(), action);
      if(null == methodDsp){
         _logger.warn(this, "execute", "Can't find method in servlet. [{0}.{1}]", instance, action);
         return;
      }
      _logger.debug(this, "execute", "Process servlet. {0}:{1}->{2}", name, instance, action);
      // Invoke method
      Class<?>[] types = methodDsp.types();
      AContainer[] aforms = methodDsp.forms();
      FWebContainerItem[] forms = new FWebContainerItem[types.length];
      Object[] params = new Object[types.length];
      try{
         for(int n = 0; n < types.length; n++){
            Class<?> type = types[n];
            Object value = null;
            if(type == IWebContext.class){
               value = context;
            }else if(type == ISqlContext.class){
               value = new FSqlContext(_databaseConsole);
            }else if(type == IWebServletResponse.class){
               value = response;
            }else if(type.isInterface()){
               value = RAop.find(type);
            }else if(aforms[n] != null){
               forms[n] = _formConsole.findContainer(context, aforms[n], type);
               value = forms[n].container();
               context.define(aforms[n].name(), value);
            }else{
               throw new FFatalError("Build param error. {0}", type);
            }
            params[n] = value;
         }
         methodDsp.invoke(instance, params);
      }catch(Exception e){
         context.messages().push(new FFatalMessage(e));
      }finally{
         // Release params
         for(Object param : params){
            if(param instanceof IRelease){
               try{
                  ((IRelease)param).release();
               }catch(Exception e){
                  _logger.error(this, "execute", e);
               }
            }
         }
      }
   }

   @Override
   public Object findInstance(String name){
      Object instance = null;
      FWebServlet servlet = findServlet(name);
      if(null != servlet){
         String face = servlet.face();
         if(!RString.isBlank(face)){
            instance = RAop.find(face);
            if(instance == null){
               _logger.debug(this, "execute", "Can't find servlet [face:{0}]", face);
            }
         }
      }
      if(_logger.debugAble()){
         _logger.debug(this, "execute", "Find servlet [{0}]->{1}]", name, instance);
      }
      return instance;
   }

   public FServletMethodDescriptor findMethod(FClass<?> clazz,
                                              String name){
      FServletDescriptor descriptor = _descriptors.get(clazz.nativeObject());
      if(descriptor == null){
         descriptor = new FServletDescriptor();
         _descriptors.set(clazz.nativeObject(), descriptor);
      }
      // Find Method Descriptor 
      name = (name != null) ? name.toLowerCase() : "process";
      FServletMethodDescriptor methodDsp = null;
      if(descriptor.contains(name)){
         methodDsp = descriptor.find(name);
      }else{
         for(FMethod method : clazz.methods(false)){
            if(name.equalsIgnoreCase(method.name())){
               methodDsp = new FServletMethodDescriptor(method.nativeObject());
               break;
            }
         }
         descriptor.push(name, methodDsp);
      }
      return methodDsp;
   }

   public FWebServlet findServlet(String name){
      name = RString.nvl(name).toLowerCase();
      FWebServlet servlet = _servlets.get(name);
      if(null == servlet){
         XAopServlet xservlet = RAop.configConsole().findNode(XAopServlet.NAME, name);
         if(xservlet != null){
            servlet = new FWebServlet();
            if(servlet.construct(xservlet)){
               _servlets.set(name, servlet);
            }
         }
      }
      return servlet;
   }

}
