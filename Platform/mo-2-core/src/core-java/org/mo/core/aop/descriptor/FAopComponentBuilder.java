package org.mo.core.aop.descriptor;

import java.lang.reflect.Method;
import org.mo.com.lang.FDictionary;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObject;
import org.mo.com.lang.IInitialize;
import org.mo.com.lang.INamePair;
import org.mo.com.lang.IRelease;
import org.mo.com.lang.RConvert;
import org.mo.com.lang.RDump;
import org.mo.com.lang.RString;
import org.mo.com.lang.reflect.RClass;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.xml.FXmlNode;
import org.mo.core.aop.RAop;
import org.mo.core.aop.config.XAbsAopMethod;
import org.mo.core.aop.config.XAopComponent;
import org.mo.core.aop.config.XAopParameter;
import org.mo.core.aop.config.XAopParameterCollection;
import org.mo.core.aop.config.XAopProperty;
import org.mo.core.aop.config.XAopPropertyCollection;

//============================================================
// <T>组件构件器。</T>
//============================================================
public class FAopComponentBuilder
      extends FObject
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FAopComponentBuilder.class);

   //============================================================
   // <T>构造组件构件器。</T>
   //============================================================
   public FAopComponentBuilder(){
   }

   //============================================================
   // <T>创建组件。</T>
   //
   // @param config 设置
   //============================================================
   protected Object createComponent(XAopComponent config){
      String className = config.className();
      FAopComponentDescriptor descriptor = RAop.descriptorConsole().find(FAopComponentDescriptor.class, className);
      Object instance = descriptor.newInstance();
      build(new FAopComponentBuildContext(config, instance));
      return instance;
   }

   //============================================================
   // <T>建立调用参数。</T>
   //
   // @param config 设置
   // @param type 类型
   //============================================================
   protected Object buildInvokeParam(XAopParameter config,
                                     Class<?> type){
      if((null != config) && (null != type) && config.isComponent()){
         XAopComponent cmpCfg = (XAopComponent)config.value();
         String face = cmpCfg.face();
         if(RString.isBlank(face)){
            String clazz = cmpCfg.className();
            if(RString.isBlank(clazz)){
               throw new FFatalError("Invalid param. {1}", config);
            }
            return createComponent(cmpCfg);
         }else{
            return RAop.find(face);
         }
      }
      return null;
   }

   //============================================================
   // <T>建立调用函数。</T>
   //
   // @param config 设置
   // @param type 类型
   //============================================================
   protected void invokeMethod(XAbsAopMethod config,
                               Method method,
                               Object component){
      if(null != method){
         Class<?>[] types = method.getParameterTypes();
         int count = types.length;
         Object[] params = new Object[count];
         if(count > 0){
            XAopParameterCollection pmCfgs = config.parameters();
            if(types.length != pmCfgs.count()){
               throw new FFatalError("Method and config invalid.[{1}]-> {2}", method, config);
            }
            for(int n = 0; n < count; n++){
               Class<?> type = types[n];
               XAopParameter pmCfg = pmCfgs.get(n);
               if(pmCfg.isComponent()){
                  Object value = buildInvokeParam(pmCfg, type);
                  params[n] = value;
               }else if(pmCfg.isConfig()){
                  params[n] = pmCfg.value();
               }else if(type.isInterface()){
                  Object value = RAop.find(type);
                  params[n] = value;
               }else{
                  Object value = RConvert.convert(type, pmCfg.value());
                  params[n] = value;
               }
            }
         }
         try{
            method.invoke(component, params);
         }catch(Exception e){
            throw new FFatalError(e, "Invoke method (method={1}, componment={2}, params={3})", method, component, RDump.dump(params));
         }
      }
   }

   //============================================================
   // <T>建立组件属性。</T>
   //
   // @param context 环境
   //============================================================
   protected void buildProperties(FAopComponentBuildContext context){
      XAopComponent config = context.config();
      if(config.hasProperties()){
         XAopPropertyCollection propertiesConfig = config.properties();
         String className = config.className();
         Object instance = context.instance();
         FAopComponentDescriptor descriptor = RAop.descriptorConsole().find(FAopComponentDescriptor.class, className);
         FAopProperties properties = descriptor.properties();
         for(INamePair<FAopProperty> pair : properties){
            // 获得属性
            FAopProperty property = pair.value();
            String propertyName = property.name();
            // 获得设置
            XAopProperty propertyConfig = propertiesConfig.matchByKey(propertyName);
            if(null != propertyConfig){
               // 设置内容
               Object value = propertyConfig.value();
               if(value instanceof XAopComponent){
                  // 设置对象
                  XAopComponent xcomponent = (XAopComponent)value;
                  Object component = createComponent(xcomponent);
                  property.set(instance, component);
                  _logger.warn(this, "buildProperties", "Set property. ({1}.{2} = {3}))", RClass.shortName(instance.getClass()), propertyName, RClass.shortName(component));
               }else if(value instanceof String){
                  // 设置属性
                  String stringValue = (String)value;
                  if(_logger.debugAble()){
                     _logger.debug(this, "buildProperties", "Set property ({1}.{2} = {3})", RClass.shortName(instance.getClass()), propertyName, stringValue);
                  }
                  if(property.convert()){
                     stringValue = RAop.configConsole().parseDefine(stringValue);
                  }
                  property.set(instance, stringValue);
               }else{
                  _logger.warn(this, "buildProperties", "Invalid property config. {1}", value);
               }
            }
         }
      }
   }

   //============================================================
   // <T>建立组件关联器。</T>
   //
   // @param context 环境
   //============================================================
   protected void buildLinkers(FAopComponentBuildContext context){
      // 获得属性
      XAopComponent config = context.config();
      Object instance = context.instance();
      String className = config.className();
      // 获得描述器
      FAopComponentDescriptor descriptor = RAop.descriptorConsole().find(FAopComponentDescriptor.class, className);
      FAopLinkers linkers = descriptor.links();
      for(INamePair<FAopLinker> pair : linkers){
         FAopLinker linker = pair.value();
         try{
            // 获得关联器
            Class<?> typeClass = linker.type();
            Object link = context.findLinker(typeClass);
            if(null == link){
               link = RAop.find(typeClass);
            }
            // 设置关联器
            if(null == link){
               _logger.warn(this, "buildLinker", "Link method failed. (name={1})", linker.name());
            }else{
               if(_logger.debugAble()){
                  _logger.debug(this, "buildLinker", "Set link {1}. ({2}={3})", RClass.shortName(instance.getClass()), linker.name(), link);
               }
               context.setLinker(typeClass, instance);
               linker.set(instance, link);
            }
         }catch(Throwable e){
            throw new FFatalError(e, "instance={1}, config={1}", instance, config.dump());
         }
      }
   }

   //============================================================
   // <T>建立组件初始化。</T>
   //
   // @param context 环境
   //============================================================
   protected void buildInitializes(FAopComponentBuildContext context){
      if(context.hasInstance()){
         Object instance = context.instance();
         if(instance instanceof IInitialize){
            ((IInitialize)instance).initialize();
         }
         XAopComponent config = context.config();
         if(config.hasInitializes()){
            FDictionary<Method> methodMap = RClass.makeMethodMap(instance);
            for(XAbsAopMethod methodConfig : config.initializeMethods()){
               String methodName = methodConfig.name();
               Method method = methodMap.get(methodName);
               if(method != null){
                  if(_logger.debugAble()){
                     _logger.debug(this, "buildInitialize", "Initialize: {1}.{2}", RClass.shortName(instance), method.getName());
                  }
                  invokeMethod(methodConfig, method, instance);
               }
            }
         }
      }
   }

   //============================================================
   // <T>建立组件环境。</T>
   //
   // @param context 环境
   //============================================================
   public void build(FAopComponentBuildContext context){
      if(context.isValid()){
         try{
            // 建立属性
            buildProperties(context);
            // 建立关联
            buildLinkers(context);
            // 建立设置
            Object instance = context.instance();
            if(instance instanceof IAopXmlConfig){
               IAopXmlConfig xinstance = (IAopXmlConfig)instance;
               FXmlNode xconfig = context.config().config();
               xinstance.loadAopConfig(xconfig);
            }
            // 建立函数
            buildInitializes(context);
         }catch(Throwable t){
            throw new FFatalError(t);
         }
      }
   }

   //============================================================
   // <T>释放处理。</T>
   //
   // @param context 环境
   // @param instance 实例
   //============================================================
   public void release(XAopComponent config,
                       Object instance){
      if(null != instance){
         if(config.hasReleases()){
            FDictionary<Method> methodMap = RClass.makeMethodMap(instance);
            for(XAbsAopMethod methodConfig : config.releaseMethods()){
               String methodName = methodConfig.name();
               Method method = methodMap.get(methodName, null);
               if(method != null){
                  if(_logger.debugAble()){
                     _logger.debug(this, "release", "Release: {1}.{2}", RClass.shortName(instance), method.getName());
                  }
                  invokeMethod(methodConfig, method, instance);
               }
            }
         }
         if(instance instanceof IRelease){
            ((IRelease)instance).release();
         }
      }
   }
}
