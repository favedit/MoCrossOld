package org.mo.core.aop.container;

import org.mo.com.console.FConsole;
import org.mo.com.lang.FAttributes;
import org.mo.com.lang.FFatalError;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.com.validator.RStringValidator;
import org.mo.com.validator.RValidator;
import org.mo.core.aop.RAop;
import org.mo.core.aop.config.EComponentDeploy;
import org.mo.core.aop.config.EComponentScope;
import org.mo.core.aop.config.XAopComponent;
import org.mo.core.aop.config.XAopComponentCollection;
import org.mo.core.aop.session.FAopSession;

//============================================================
// <T>组件管理控制台。</T>
// <P>负责系统内，组件的缓冲，查询和创建。</P>
//============================================================
public class FAopComponentConsole
      extends FConsole
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FAopComponentConsole.class);

   // 命令初始化
   public static String ACTION_INITIALIZE = "Initialize";

   // 命令释放
   public static String ACTION_RELEASE = "Release";

   // 节点组件
   public static String NODE_COMPONENT = "component";

   // 节点组件集合
   public static String NODE_COMPONENTS = "components";

   // 节点属性
   public static String NODE_PROPERTY = "Property";

   // 组件集合
   protected FAopComponents _components = new FAopComponents();

   // 未找到集合
   protected FAttributes _noMap = new FAttributes();

   //============================================================
   // <T>构造组件管理控制台。</T>
   //============================================================
   public FAopComponentConsole(){
   }

   //============================================================
   // <T>根据接口名称，查找一个组件对象。</T>
   // <P>如果组件不存在，则产生例外。</P>
   //
   // @type V 组件类型
   // @param face 接口名称
   // @return 组件对象
   //============================================================
   public <V> V find(String face){
      V component = tryFind(face);
      if(null == component){
         throw new FFatalError("Component is not exists. (face={1})", face);
      }
      return component;
   }

   //============================================================
   // <T>根据接口类对象，查找一个组件对象。</T>
   // <P>如果组件不存在，则产生例外。</P>
   //
   // @type V 组件类型
   // @param clazz 接口类对象
   // @return 组件对象
   //============================================================
   public <V> V find(Class<V> clazz){
      V component = tryFind(clazz);
      if(null == component){
         throw new FFatalError("Component is not exists. (class={1})", clazz);
      }
      return component;
   }

   //============================================================
   // <T>根据会话代码和接口名称，查找线程组件对象。</T>
   // <P>如果组件不存在，则产生例外。</P>
   //
   // @type V 组件类型
   // @param sessionCode 会话代码
   // @param face 接口名称
   // @return 组件对象
   //============================================================
   public <V> V find(String sessionCode,
                     String face){
      FAopSession session = RAop.sessionConsole().find(sessionCode);
      V value = session.find(face);
      if(null == value){
         value = find(face);
         session.set(face, value);
      }
      return value;
   }

   //============================================================
   // <T>根据会话代码和接口对象，查找线程组件对象。</T>
   // <P>如果组件不存在，则产生例外。</P>
   //
   // @type V 组件类型
   // @param sessionCode 会话代码
   // @param face 接口名称
   // @return 组件对象
   //============================================================
   public <V> V find(String sessionCode,
                     Class<V> clazz){
      return find(sessionCode, clazz.getName());
   }

   //============================================================
   // <T>根据接口名称，查找一个组件对象。</T>
   // <P>如果组件不存在，则返回空。</P>
   //
   // @type V 组件类型
   // @param face 接口名称
   // @return 组件对象
   //============================================================
   public <V> V tryFind(String face){
      RStringValidator.checkEmpty(face, "face");
      // 检查已经取过，如果不存在的话直接返回。
      if(!_noMap.contains(face)){
         FAopComponent component = _components.find(face);
         if(null != component){
            // 查找组件
            return findByComponent(component);
         }
         _noMap.set(face, null);
      }
      return null;
   }

   //============================================================
   // <T>根据接口对象，查找一个组件对象。</T>
   // <P>如果组件不存在，则返回空。</P>
   //
   // @type V 组件类型
   // @param clazz 接口对象
   // @return 组件对象
   //============================================================
   public <V> V tryFind(Class<V> clazz){
      RValidator.checkNull(clazz, "clazz");
      // 检查已经取过，如果不存在的话直接返回。
      String className = clazz.getName();
      if(!_noMap.contains(className)){
         // 查找组件
         FAopComponent component = null;
         if(clazz.isInterface()){
            // 查找对象接口
            component = _components.find(className);
         }else{
            // 查找对象类
            component = _components.findByClass(className);
         }
         if(null != component){
            // 查找组件
            return findByComponent(component);
         }
         _noMap.set(className, null);
      }
      return null;
   }

   //============================================================
   // <T>根据接口名称，查找组件定义对象。</T>
   //
   // @param face 接口名称
   // @return 组件对象
   //============================================================
   public FAopComponent findComponent(String face){
      return _components.find(face);
   }

   //============================================================
   // <T>根据接口类对象，查找组件定义对象。</T>
   //
   // @param clazz 接口对象
   // @return 组件对象
   //============================================================
   public FAopComponent findComponent(Class<?> clazz){
      return _components.find(clazz.getName());
   }

   //============================================================
   // <T>根据组件定义，查找一个组件对象。</T>
   //
   // @param clazz 接口对象
   // @return 组件对象
   //============================================================
   @SuppressWarnings("unchecked")
   private <V> V findByComponent(FAopComponent component){
      // Create component
      V instance = null;
      synchronized(component){
         EComponentScope scope = component.scopeCd();
         if(EComponentScope.Global == scope){
            instance = (V)component.instance();
         }else if(EComponentScope.Session == scope){
            instance = (V)component.newInstance();
         }else{
            instance = (V)component.newInstance();
         }
         if(_logger.debugAble()){
            _logger.debug(this, "find", "Find component ({1}:{2})", scope, instance);
         }
      }
      return instance;
   }

   //============================================================
   // <T>根据类对象，注册组件对象。</T>
   //
   // @param clazz 类对象
   // @param instance 组件对象
   //============================================================
   public void register(Class<?> clazz,
                        Object instance){
      String className = clazz.getName();
      FAopComponent component = new FAopComponent();
      component.setInstance(instance);
      component.setNativeInstance(instance);
      component.setScope(EComponentScope.Global);
      _components.set(className, component);
   }

   //============================================================
   // <T>根据接口名称，释放指定组件对象。</T>
   //
   // @param face 接口名称
   // @param instance 组件对象
   //============================================================
   public void releaseComponent(String face,
                                Object instance){
      if(null != instance){
         FAopComponent component = _components.find(face);
         if(null != component){
            component.release(instance);
         }
      }
   }

   //============================================================
   // <T>初始化处理。</T>
   //============================================================
   public void initialize(){
      // 布置所有启动组件
      XAopComponentCollection components = RAop.configConsole().componentCollection();
      if(null != components){
         for(XAopComponent component : components){
            if(EComponentDeploy.Start == component.deploy()){
               String face = component.face();
               _logger.debug(this, "initialize", "Deploy component (face={1})", face);
               find(face);
            }
         }
      }
   }

   //============================================================
   // <T>释放处理。</T>
   //============================================================
   public void release(){
      FAopComponent[] components = _components.toObjects();
      for(FAopComponent component : components){
         if(EComponentScope.Global == component.scopeCd()){
            component.release();
         }
      }
   }
}
