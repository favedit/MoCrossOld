package org.mo.eng.store;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObjects;
import org.mo.com.xml.EXmlConfig;
import org.mo.com.xml.EXmlConfigCopy;
import org.mo.com.xml.FXmlNode;
import org.mo.com.xml.IXmlObject;
import org.mo.com.xml.RXmlObject;
import org.mo.core.aop.face.ALink;
import org.mo.core.aop.face.AProperty;
import org.mo.eng.persistence.IPersistenceConsole;
import org.mo.eng.persistence.common.FXmlPersistence;

//============================================================
// <T>配置对象管理器。</T>
//============================================================
public abstract class FXmlConfigConsole<V extends IXmlObject>
      extends FAbstractXmlConfigConsole
      implements
         IXmlConfigConsole<V>
{
   // 属性名称
   public final static String PTY_NAME = "name";

   // 持久化对象类型名称
   @AProperty
   protected String _persistence;

   // 持久化对象管理器
   @ALink
   protected IPersistenceConsole _persistenceConsole;

   //============================================================
   // <T>创建类型数组。</T>
   //
   // @return 类型数组
   //============================================================
   protected abstract FObjects<V> createCollection();

   //============================================================
   // <T>根据对象实例获得存储用的名称。</T>
   //
   // @param xobject 实例
   // @return 名称
   //============================================================
   @Override
   protected String makeName(Object xobject){
      return ((IXmlObject)xobject).innerGet(PTY_NAME);
   }

   //============================================================
   // <T>根据配置节点创建一个配置对象。</T>
   //
   // @param config 配置节点
   // @param type 设置类型
   // @return 配置对象
   //============================================================
   @Override
   public IXmlObject createElement(FXmlNode config,
                                   EXmlConfig type){
      try{
         FXmlPersistence persistence = _persistenceConsole.find(FXmlPersistence.class, _persistence);
         IXmlObject xobject = persistence.loadObject(config, type);
         if(null == xobject){
            throw new FFatalError("Can't create element. (config={1},type={2})", config.xml(), type);
         }
         return xobject;
      }catch(Exception e){
         throw new FFatalError(e, "Create element error. (persistence={1})", _persistence);
      }
   }

   //============================================================
   // <T>查找指定名称的配置对象。</T>
   //
   // @param name 名称
   // @return 配置对象
   //============================================================
   @Override
   @SuppressWarnings("unchecked")
   public V find(String name){
      return (V)super.findInstance(name);
   }

   //============================================================
   // <T>获得指定名称的配置对象。</T>
   // <P>配置对象不存在时产生例外。</P>
   //
   // @param name 名称
   // @return 配置对象
   //============================================================
   @Override
   public V get(String name){
      V instance = find(name);
      if(null == instance){
         throw new FFatalError("Can't find instance. (name={1})", name);
      }
      return instance;
   }

   //============================================================
   // <T>列出所有的配置集合。</T>
   //
   // @return 配置集合
   //============================================================
   @Override
   public V[] list(){
      FObjects<V> array = createCollection();
      int count = _configs.count();
      for(int n = 0; n < count; n++){
         FXmlConfigMeta meta = _configs.value(n).meta();
         array.push(find(meta.name()));
      }
      return array.toObjects();
   }

   //============================================================
   // <T>将一个配置节点转换为配置对象。</T>
   //
   // @param instance 配置对象
   // @return 配置节点
   //============================================================
   @Override
   public FXmlNode convertInstanceToNode(Object instance){
      FXmlPersistence persistence = _persistenceConsole.find(FXmlPersistence.class, _persistence);
      return persistence.saveObject((IXmlObject)instance, EXmlConfig.Full);
   }

   //============================================================
   // <T>将一个配置节点转换为配置对象。</T>
   //
   // @param instance 配置对象
   // @param typeCd 类型
   // @return 配置节点
   //============================================================
   @Override
   public FXmlNode convertInstanceToNode(Object instance,
                                         EXmlConfig type){
      FXmlPersistence persistence = _persistenceConsole.find(FXmlPersistence.class, _persistence);
      return persistence.saveObject((IXmlObject)instance, type);
   }

   //============================================================
   // <T>将一个配置节点转换为配置对象。</T>
   //
   // @param xconfig 配置节点
   // @return 配置对象
   //============================================================
   @Override
   public Object convertNodeToInstance(FXmlNode config){
      FXmlPersistence persistence = _persistenceConsole.find(FXmlPersistence.class, _persistence);
      if(null == persistence){
         throw new FFatalError("Persistence is null.");
      }
      return persistence.loadObject(config, EXmlConfig.Full);
   }

   //============================================================
   // <T>将一个配置设置节点转换为配置对象。</T>
   //
   // @param xconfig 配置设置节点
   // @return 配置对象
   //============================================================
   @Override
   @SuppressWarnings("unchecked")
   public V convertConfigToInstance(FXmlConfig config){
      V xobject = (V)convertNodeToInstance(config._config);
      xobject.innerSet(PTY_NAME, config.meta().name());
      return xobject;
   }

   //============================================================
   // <T>新建一个数据对象。</T>
   //
   // @param xobject 数据对象
   //============================================================
   @Override
   public void persist(V xobject){
      persistInstance(xobject);
   }

   //============================================================
   // <T>存储一个数据对象。</T>
   //
   // @param xobject 数据对象
   //============================================================
   @Override
   public void store(V xobject){
      storeInstance(xobject);
   }

   //============================================================
   // <T>删除一个数据对象。</T>
   //
   // @param xobject 数据对象
   //============================================================
   @Override
   public void remove(V xobject){
      removeInstance(xobject);
   }

   //============================================================
   // <T>深层复制一个XML对象内容到另一个XML对象。</T>
   //
   // @param copyCd 复制模式
   // @param xsource 源XML对象
   // @param xtarget 目标XML对象
   // @param property 依赖属性名
   //============================================================
   @Override
   public void deepCopy(EXmlConfigCopy copyType,
                        IXmlObject xsource,
                        IXmlObject xtarget,
                        String property){
      // 复制节点的属性
      RXmlObject.copyAttributes(copyType, xsource, xtarget);
      // 递规建立所有子节点
      if(xsource.hasChild()){
         for(IXmlObject xchild : xsource.children()){
            String value = xchild.innerGet(property);
            IXmlObject xtargetChild = xtarget.find(property, value);
            if(null == xtargetChild){
               FXmlNode childNode = new FXmlNode(xchild.name());
               xtargetChild = createElement(childNode, EXmlConfig.Full);
               xtarget.children().push(xtargetChild);
            }
            deepCopy(copyType, xchild, xtargetChild, property);
         }
      }
   }
}
