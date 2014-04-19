package org.mo.core.aop;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.RDump;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.system.RThread;
import org.mo.core.aop.config.FAopConfigConsole;
import org.mo.core.aop.container.FAopComponent;
import org.mo.core.aop.container.FAopComponentConsole;
import org.mo.core.aop.descriptor.FAopDescriptorConsole;
import org.mo.core.aop.dispatcher.FAopDispatcherConsole;
import org.mo.core.aop.session.FAopSessionConsole;

//============================================================
// <T>AOP组件管理类。</T>
//============================================================
public class RAop
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(RAop.class);

   // 组件配置管理器
   private static FAopConfigConsole _configConsole;

   // 组件描述管理器
   private static FAopDescriptorConsole _descriptorConsole;

   // 组件管理器
   private static FAopComponentConsole _componentConsole;

   // 组件分发管理器
   private static FAopDispatcherConsole _dispatcherConsole;

   // 组件线程管理器
   private static FAopSessionConsole _sessionConsole;

   //============================================================
   // <T>获得组件配置管理器。</T>
   //
   // @return 组件配置管理器
   //============================================================
   public static FAopConfigConsole configConsole(){
      if(null == _configConsole){
         _configConsole = new FAopConfigConsole();
         _configConsole.loadDefaultConfig();
      }
      return _configConsole;
   }

   //============================================================
   // <T>获得组件描述管理器。</T>
   //
   // @return 组件描述管理器
   //============================================================
   public static FAopDescriptorConsole descriptorConsole(){
      if(null == _descriptorConsole){
         _descriptorConsole = new FAopDescriptorConsole();
      }
      return _descriptorConsole;
   }

   //============================================================
   // <T>获得组件管理器。</T>
   //
   // @return 组件管理器
   //============================================================
   public static FAopComponentConsole componentConsole(){
      if(null == _componentConsole){
         _componentConsole = new FAopComponentConsole();
         _componentConsole.initialize();
      }
      return _componentConsole;
   }

   //============================================================
   // <T>获得组件分发管理器。</T>
   //
   // @return 组件分发管理器
   //============================================================
   public static FAopDispatcherConsole dispatcherConsole(){
      if(null == _dispatcherConsole){
         _dispatcherConsole = new FAopDispatcherConsole();
      }
      return _dispatcherConsole;
   }

   //============================================================
   // <T>获得组件线程管理器。</T>
   //
   // @return 组件线程管理器
   //============================================================
   public static FAopSessionConsole sessionConsole(){
      if(null == _sessionConsole){
         _sessionConsole = new FAopSessionConsole();
      }
      return _sessionConsole;
   }

   //============================================================
   // <T>根据接口名称获得组件。</T>
   //
   // @param face 接口名称
   // @return 组件
   //============================================================
   public static FAopComponent findComponent(String face){
      return componentConsole().findComponent(face);
   }

   //============================================================
   // <T>根据类获得组件。</T>
   //
   // @param clazz 类
   // @return 组件
   //============================================================
   public static FAopComponent findComponent(Class<?> clazz){
      return componentConsole().findComponent(clazz);
   }

   //============================================================
   // <T>根据接口类名称，查找一个组件对象。</T>
   // <P>如果组件不存在，则产生例外。</P>
   //
   // @type V 组件类型
   // @param clazz 接口类对象
   // @return 组件对象
   //============================================================
   public static <V> V find(String face){
      try{
         return componentConsole().find(face);
      }catch(Exception e){
         throw new FFatalError(e, "Find component failed. (class={1})", face);
      }
   }

   //============================================================
   // <T>根据接口类对象，查找一个组件对象。</T>
   // <P>如果组件不存在，则产生例外。</P>
   //
   // @type V 组件类型
   // @param clazz 接口类对象
   // @return 组件对象
   //============================================================
   public static <V> V find(Class<V> clazz){
      try{
         FAopComponentConsole componentConsole = componentConsole();
         return componentConsole.find(clazz);
      }catch(Exception e){
         throw new FFatalError(e, "Find component failed. (class={1})", clazz.getName());
      }
   }

   //============================================================
   // <T>根据接口类名称，查找一个会话组件对象。</T>
   // <P>如果组件不存在，则产生例外。</P>
   //
   // @type V 组件类型
   // @param session 会话名称
   // @param clazz 接口类对象
   // @return 组件对象
   //============================================================
   @SuppressWarnings("unchecked")
   public static <V> V find(String session,
                            String face){
      try{
         return (V)componentConsole().find(session, face);
      }catch(Exception e){
         _logger.error(null, "find", e, "find class {1}", face);
         return null;
      }
   }

   //============================================================
   // <T>根据接口类对象，查找一个会话组件对象。</T>
   // <P>如果组件不存在，则产生例外。</P>
   //
   // @type V 组件类型
   // @param session 会话名称
   // @param clazz 接口类对象
   // @return 组件对象
   //============================================================
   public static <V> V find(String session,
                            Class<V> clazz){
      try{
         return componentConsole().find(session, clazz);
      }catch(Exception e){
         _logger.error(null, "find", e, "find class {1}", clazz);
         return null;
      }
   }

   //============================================================
   // <T>根据接口类名称，查找一个组件对象。</T>
   // <P>如果组件不存在，则返回空。</P>
   //
   // @type V 组件类型
   // @param clazz 接口类对象
   // @return 组件对象
   //============================================================
   public static <V> V tryFind(String face){
      return componentConsole().tryFind(face);
   }

   //============================================================
   // <T>根据接口类对象，查找一个组件对象。</T>
   // <P>如果组件不存在，则返回空。</P>
   //
   // @type V 组件类型
   // @param clazz 接口类对象
   // @return 组件对象
   //============================================================
   public static <V> V tryFind(Class<V> clazz){
      return componentConsole().tryFind(clazz);
   }

   //============================================================
   // <T>初始化环境内容。</T>
   //
   // @param fileName 设置文件名
   //============================================================
   public static void initialize(String fileName){
      if(_logger.debugAble()){
         _logger.debug(null, "initialize", RDump.lineString(" Start ").toString());
         _logger.debug(null, "initialize", "Context Initialized. [{1}]", fileName);
      }
      try{
         configConsole().loadFile(fileName);
         componentConsole();
      }catch(Throwable throwable){
         _logger.error(null, "initialize", throwable);
      }
   }

   //============================================================
   // <T>释放环境内容。</T>
   //============================================================
   public static void release(){
      RThread.stopAll();
      try{
         componentConsole().release();
      }catch(Throwable throwable){
         _logger.error(null, "release", throwable);
      }
      if(_logger.debugAble()){
         _logger.debug(null, "destroyed", "Context destroyed");
         _logger.debug(null, "destroyed", RDump.lineString(" Stop ").toString());
      }
   }
}
