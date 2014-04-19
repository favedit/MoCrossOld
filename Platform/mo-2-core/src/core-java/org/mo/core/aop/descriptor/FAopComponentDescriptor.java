package org.mo.core.aop.descriptor;

import org.mo.com.lang.FFatalError;
import org.mo.com.lang.reflect.FAnnotation;
import org.mo.com.lang.reflect.FClass;
import org.mo.com.lang.reflect.FField;
import org.mo.com.lang.reflect.RClass;
import org.mo.com.lang.reflect.RField;
import org.mo.com.logging.ILogger;
import org.mo.com.logging.RLogger;
import org.mo.core.aop.face.AFactory;
import org.mo.core.aop.face.ALink;
import org.mo.core.aop.face.AProperty;

//============================================================
// <T>组件描述器。</T>
//============================================================
public class FAopComponentDescriptor
      extends MAopDescriptor
{
   // 日志输出接口
   private static ILogger _logger = RLogger.find(FAopComponentDescriptor.class);

   // 组件工厂
   protected IAopFactory _factory = new FAopComponentFactory();

   // 加载器
   protected FAopComponentLoader _loader = new FAopComponentLoader(this);

   // 属性集合
   protected FAopProperties _propertys = new FAopProperties();

   // 关联器集合
   protected FAopLinkers _links = new FAopLinkers();

   //============================================================
   // <T>构造组件描述器。</T>
   //============================================================
   public FAopComponentDescriptor(){
   }

   //============================================================
   // <T>构建属性。</T>
   //
   // @param clazz 类对象
   // @param field 字段对象
   // @param convert 转换器
   //============================================================
   protected void buildProperty(FClass<?> clazz,
                                FField field,
                                boolean convert){
      String fieldName = field.name();
      String propertyName = RField.format(fieldName);
      // Check duplicate
      if(_propertys.contains(propertyName)){
         throw new FFatalError("Property is duplicate. ({1}).{2}", clazz.name(), propertyName);
      }
      // Create property
      FAopProperty property = new FAopProperty();
      property.setName(propertyName);
      property.link(field.nativeObject());
      property.setConvert(convert);
      if(_logger.debugAble()){
         _logger.debug(this, "buildProperty", "Build property (class={1}, name={2}", clazz.name(), propertyName);
      }
      _propertys.set(propertyName, property);
   }

   //============================================================
   // <T>构建关联器。</T>
   //
   // @param clazz 类对象
   // @param field 字段对象
   //============================================================
   protected void buildLinker(FClass<?> clazz,
                              FField field){
      String fieldName = field.name();
      String name = RField.format(fieldName);
      // Check duplicate
      if(_links.contains(name)){
         throw new FFatalError("Linker is duplicate. ({1}).{2}", clazz.name(), name);
      }
      // Add method
      FAopLinker linker = new FAopLinker();
      linker.setName(name);
      linker.link(field.nativeObject());
      if(_logger.debugAble()){
         _logger.debug(this, "buildLink", "Build linker. (class={1}, name={2})", clazz.name(), name);
      }
      _links.set(name, linker);
   }

   //============================================================
   // <T>获得类对象。</T>
   //
   // @return 类对象
   //============================================================
   public FClass<?> clazz(){
      return _class;
   }

   //============================================================
   // <T>获得属性集合。</T>
   //
   // @return 属性集合
   //============================================================
   public FAopProperties properties(){
      return _propertys;
   }

   //============================================================
   // <T>获得关联器集合。</T>
   //
   // @return 关联器集合
   //============================================================
   public FAopLinkers links(){
      return _links;
   }

   //============================================================
   // <T>创建实例。</T>
   //
   // @return 实例
   //============================================================
   @Override
   public <V> V newInstance(){
      return _factory.createInstance(_class);
   }

   //============================================================
   // <T>根据类名称加载类对象。</T>
   //
   // @param className 类名称
   //============================================================
   public void loadClass(String className){
      try{
         Class<?> clazz = _loader.loadClass(className);
         // 检查类工厂
         AFactory afactory = clazz.getAnnotation(AFactory.class);
         if(afactory != null){
            _factory = (IAopFactory)RClass.newInstance(afactory.clazz());
         }
         // 关联类
         FClass<?> fclazz = RClass.find(clazz);
         super.linkClass(fclazz);
         // 检查类的所有字段
         FField[] fields = _class.allDeclaredFields();
         for(FField field : fields){
            // 创建属性
            FAnnotation annotationProperty = field.findAnnotation(AProperty.class);
            if(annotationProperty != null){
               AProperty aproperty = (AProperty)annotationProperty.annotation();
               buildProperty(_class, field, aproperty.convert());
            }
            // 创建引用
            FAnnotation annotationLink = field.findAnnotation(ALink.class);
            if(annotationLink != null){
               buildLinker(_class, field);
            }
         }
      }catch(Exception e){
         throw new FFatalError(e, "Load class error (class={0})", className);
      }
   }
}
