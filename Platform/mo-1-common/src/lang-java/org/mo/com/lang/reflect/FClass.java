package org.mo.com.lang.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObject;
import org.mo.com.lang.FObjects;

//============================================================
// <T>类对象。</T>
//
// @type C 类型
//============================================================
public class FClass<C>
      extends FObject
{
   // 类对象
   protected Class<C> _class;

   // 全部字段集合
   protected FField[] _allFields;

   // 全部函数集合
   protected FMethod[] _allMethods;

   //============================================================
   // <T>构造类对象。</T>
   //
   // @param clazz 类对象
   //============================================================
   protected FClass(Class<C> clazz){
      _class = clazz;
   }

   //============================================================
   // <T>获得本地对象。</T>
   //
   // @return 本地对象
   //============================================================
   public Class<?> nativeObject(){
      return _class;
   }

   //============================================================
   // <T>获得名称。</T>
   //
   // @return 名称
   //============================================================
   public String name(){
      return _class.getName();
   }

   //============================================================
   // <T>判断是否为接口。</T>
   //
   // @return 是否为接口
   //============================================================
   public boolean isInterface(){
      return _class.isInterface();
   }

   //============================================================
   // <T>创建新类实例。</T>
   //
   // @return 类实例
   //============================================================
   public C newInstance(){
      try{
         return _class.newInstance();
      }catch(Exception e){
         throw new FFatalError(e, "Create instance error. (class_name={1})", _class.getName());
      }
   }

   //============================================================
   // <T>获得所有声明的字段集合。</T>
   //
   // @return 字段集合
   //============================================================
   public FField[] allDeclaredFields(){
      FObjects<FField> fields = new FObjects<FField>(FField.class);
      Class<?> loop = _class;
      while(loop != null){
         for(Field field : loop.getDeclaredFields()){
            fields.push(new FField(field));
         }
         loop = loop.getSuperclass();
      }
      return fields.toObjects();
   }

   //============================================================
   // <T>获得所有声明的函数集合。</T>
   //
   // @return 函数集合
   //============================================================
   public FMethod[] allDeclaredMethods(){
      FObjects<FMethod> methods = new FObjects<FMethod>(FMethod.class);
      Class<?> loop = _class;
      while(loop != null){
         for(Method method : loop.getDeclaredMethods()){
            methods.push(new FMethod(method));
         }
         loop = loop.getSuperclass();
      }
      return methods.toObjects();
   }

   //============================================================
   // <T>获得所有字段集合。</T>
   //
   // @return 字段集合
   //============================================================
   public FField[] allFields(){
      if(null == _allFields){
         Class<?> loop = _class;
         FObjects<FField> fields = new FObjects<FField>(FField.class);
         while(null != loop){
            for(Field field : loop.getFields()){
               fields.push(new FField(field));
            }
            loop = loop.getSuperclass();
         }
         _allFields = fields.toObjects();
      }
      return _allFields;
   }

   //============================================================
   // <T>获得所有函数集合。</T>
   //
   // @return 函数集合
   //============================================================
   public FMethod[] allMethods(){
      if(null == _allMethods){
         Class<?> loop = _class;
         FObjects<FMethod> methods = new FObjects<FMethod>(FMethod.class);
         while(null != loop){
            for(Method method : loop.getMethods()){
               methods.push(new FMethod(method));
            }
            loop = loop.getSuperclass();
         }
         _allMethods = methods.toObjects();
         methods.dispose();
      }
      return _allMethods;
   }

   //============================================================
   // <T>获得函数集合。</T>
   //
   // @return 函数集合
   //============================================================
   public FMethod[] methods(boolean pool){
      FObjects<FMethod> methods = new FObjects<FMethod>(FMethod.class);
      for(Method method : _class.getMethods()){
         methods.push(new FMethod(method));
      }
      return methods.toObjects();
   }

   //============================================================
   // <T>转换为类对象。</T>
   //
   // @return 类对象
   //============================================================
   public Class<?> toClass(){
      try{
         return _class;
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }
   //
   //   public FField[] declaredFields(boolean pool){
   //      FObjects<FField> fields = new FObjects<FField>(FField.class);
   //      for(Field field : _class.getDeclaredFields()){
   //         fields.push(new FField(field));
   //      }
   //      return fields.toObjects();
   //   }
   //
   //   public FMethod[] declaredMethods(boolean pool){
   //      FObjects<FMethod> methods = new FObjects<FMethod>(FMethod.class);
   //      for(Method method : _class.getDeclaredMethods()){
   //         methods.push(new FMethod(method));
   //      }
   //      return methods.toObjects();
   //   }
   //
   //   public FField[] fields(boolean pool){
   //      FObjects<FField> fields = new FObjects<FField>(FField.class);
   //      for(Field field : _class.getFields()){
   //         fields.push(new FField(field));
   //      }
   //      return fields.toObjects();
   //   }
}
