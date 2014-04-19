package org.mo.core.aop.container;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObject;
import org.mo.com.lang.reflect.FClass;
import org.mo.core.aop.RAop;
import org.mo.core.aop.config.EComponentScope;
import org.mo.core.aop.config.XAopComponent;
import org.mo.core.aop.descriptor.FAopComponentBuildContext;
import org.mo.core.aop.descriptor.FAopComponentBuilder;
import org.mo.core.aop.descriptor.FAopComponentDescriptor;
import org.mo.core.aop.descriptor.IAopDescriptor;

//============================================================
// <T>AOP组件。</T>
//============================================================
public class FAopComponent
      extends FObject
{
   // 设置
   protected XAopComponent _config;

   // 描述器
   protected IAopDescriptor _descriptor;

   // 构建器
   private FAopComponentBuilder _builder;

   // 实体
   private Object _instance;

   // 本地实体
   private Object _nativeInstance;

   //============================================================
   // <T>构造AOP组件。</T>
   //============================================================
   public FAopComponent(){
   }

   //============================================================
   // <T>构造AOP组件。</T>
   //
   // @param xcomponent 组件配置
   //============================================================
   public FAopComponent(XAopComponent xcomponent){
      _config = xcomponent;
      if(null == _config){
         throw new FFatalError("Component config is null");
      }
   }

   //============================================================
   // <T>获得接口名称。</T>
   //
   // @return 接口名称
   //============================================================
   public String face(){
      return _config.face();
   }

   //============================================================
   // <T>获得接口类对象。</T>
   //
   // @return 接口类对象
   //============================================================
   public FClass<?> faceClass(){
      return _config.faceClass();
   }

   //============================================================
   // <T>获得范围。</T>
   //
   // @return 范围
   //============================================================
   public EComponentScope scopeCd(){
      return _config.scopeCd();
   }

   //============================================================
   // <T>设置范围。</T>
   //
   // @param scopeCd 范围
   //============================================================
   public void setScope(EComponentScope scopeCd){
      _config.setScopeCd(scopeCd);
   }

   //============================================================
   // <T>获得配置节点。</T>
   //
   // @return 配置节点
   //============================================================
   public XAopComponent config(){
      return _config;
   }

   //============================================================
   // <T>获得描述器。</T>
   //
   // @return 描述器
   //============================================================
   public IAopDescriptor descriptor(){
      return _descriptor;
   }

   //============================================================
   // <T>获得构建器。</T>
   //
   // @return 构建器
   //============================================================
   public FAopComponentBuilder builder(){
      return _builder;
   }

   //============================================================
   // <T>设置构建器。</T>
   //
   // @param builder 构建器
   //============================================================
   public void setBuilder(FAopComponentBuilder builder){
      _builder = builder;
   }

   //============================================================
   // <T>获得对象实例。</T>
   //
   // @return 对象实例
   //============================================================
   @SuppressWarnings("unchecked")
   public <V> V instance(){
      if(null == _instance){
         synchronized(this){
            _nativeInstance = _descriptor.newInstance();
            _builder.build(new FAopComponentBuildContext(_config, _nativeInstance));
            if(_config.hasFace()){
               _instance = RAop.dispatcherConsole().createDispatchObject(_nativeInstance, _config.faceClass());
            }else{
               _instance = _nativeInstance;
            }
         }
      }
      return (V)_instance;
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
   // <T>获得本地对象实例。</T>
   //
   // @return 本地对象实例
   //============================================================
   public Object nativeInstance(){
      return _nativeInstance;
   }

   //============================================================
   // <T>设置本地实例。</T>
   //
   // @param instance 本地实例
   //============================================================
   public void setNativeInstance(Object instance){
      _nativeInstance = instance;
   }

   //============================================================
   // <T>创建对象实例。</T>
   //
   // @return 对象实例
   //============================================================
   @SuppressWarnings("unchecked")
   public synchronized <V> V newInstance(){
      Object object = _descriptor.newInstance();
      _builder.build(new FAopComponentBuildContext(_config, object));
      V instance = null;
      if(_config.hasFace()){
         instance = RAop.dispatcherConsole().createDispatchObject(object, _config.faceClass());
      }else{
         instance = (V)object;
      }
      return instance;
   }

   //============================================================
   // <T>初始化处理。</T>
   //============================================================
   public void initialize(){
      // 获得接口
      if(_config.hasFace()){
         if(null == _config.faceClass()){
            throw new FFatalError("Face class is not find (name={0})", _config.face());
         }
      }
      // 获得描述器
      _descriptor = RAop.descriptorConsole().find(FAopComponentDescriptor.class, _config.className());
   }

   //============================================================
   // <T>释放处理。</T>
   //============================================================
   public void release(){
      if(null != _instance){
         _builder.release(_config, _nativeInstance);
         _instance = null;
      }
      _nativeInstance = null;
   }

   //============================================================
   // <T>释放实例。</T>
   //
   // @param instance 实例
   //============================================================
   public void release(Object instance){
      if(null != instance){
         _builder.release(_config, instance);
         instance = null;
      }
      _nativeInstance = null;
   }
}
