package org.mo.core.aop.descriptor;

import org.mo.com.collections.FObjectMap;
import org.mo.com.lang.FObject;
import org.mo.core.aop.config.XAopComponent;

//============================================================
// <T>组件建立环境。</T>
//============================================================
public class FAopComponentBuildContext
      extends FObject
{
   // 组件设置
   protected XAopComponent _config;

   // 实例
   protected Object _instance;

   // 关联器集合
   protected FObjectMap _linkers = new FObjectMap();

   //============================================================
   // <T>构造组件建立环境。</T>
   //============================================================
   public FAopComponentBuildContext(){
   }

   //============================================================
   // <T>构造组件建立环境。</T>
   //
   // @param config 设置
   // @param instance 实例
   //============================================================
   public FAopComponentBuildContext(XAopComponent config,
                                    Object instance){
      _config = config;
      _instance = instance;
      if(null != config && null != config.faceClass()){
         _linkers.set(config.faceClass().nativeObject(), instance);
      }
   }

   //============================================================
   // <T>判断是否有效。</T>
   //
   // @return 是否有效
   //============================================================
   public boolean isValid(){
      return hasConfig() && hasInstance();
   }

   //============================================================
   // <T>是否含有设置。</T>
   //
   // @return 是否含有
   //============================================================
   public boolean hasConfig(){
      return (null != _config);
   }

   //============================================================
   // <T>获得设置对象。</T>
   //
   // @return 设置对象
   //============================================================
   public XAopComponent config(){
      return _config;
   }

   //============================================================
   // <T>设置设置对象。</T>
   //
   // @param config 设置对象
   //============================================================
   public void setConfig(XAopComponent config){
      _config = config;
   }

   //============================================================
   // <T>是否含有实体。</T>
   //
   // @return 是否含有
   //============================================================
   public boolean hasInstance(){
      return (null != _instance);
   }

   //============================================================
   // <T>获得实例。</T>
   //
   // @return 实例
   //============================================================
   public Object instance(){
      return _instance;
   }

   //============================================================
   // <T>设置实例。</T>
   //
   // @param instance 实例
   //============================================================
   public void setInstance(Object instance){
      _instance = instance;
   }

   //============================================================
   // <T>是否含有关联器。</T>
   //
   // @param key 主键
   // @return 是否含有
   //============================================================
   public boolean hasLinker(Object key){
      return _linkers.contains(key);
   }

   //============================================================
   // <T>查找关联器。</T>
   //
   // @param key 主键
   // @return 关联器
   //============================================================
   public Object findLinker(Object key){
      return _linkers.find(key);
   }

   //============================================================
   // <T>获得关联器集合。</T>
   //
   // @return 关联器集合
   //============================================================
   public FObjectMap linkers(){
      return _linkers;
   }

   //============================================================
   // <T>设置关联器。</T>
   //
   // @param key 主键
   // @param instance 实例
   //============================================================
   public void setLinker(Object key,
                         Object instance){
      _linkers.set(key, instance);
   }
}
