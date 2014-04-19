package org.mo.com.xml.persistent;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import org.mo.com.lang.RArray;
import org.mo.com.lang.reflect.RClass;

public class FXmlClass
{
   private Class<?> _class;

   private FXmlFields _fields = new FXmlFields();

   private String _name;

   private FXmlMethod _serializeMethod;

   private FXmlMethod _unserializeMethod;

   public FXmlClass(Class<?> clazz){
      _class = clazz;
      _name = clazz.getName();
      loadFields(clazz);
      loadMethods(clazz);
   }

   public Class<?> clazz(){
      return _class;
   }

   public FXmlFields fields(){
      return _fields;
   }

   public FXmlField findField(String name){
      return _fields.get(name);
   }

   public Class<?> getComponentType(){
      return _class.getComponentType();
   }

   public boolean isArray(){
      return _class.isArray();
   }

   public boolean isEnum(){
      return _class.isEnum();
   }

   public void loadFields(Class<?> clazz){
      Field[] fields = clazz.getDeclaredFields();
      for(Field field : fields){
         if(testStore(field)){
            FXmlField xfield = new FXmlField(this, field);
            if(_fields.contains(xfield.name())){
               xfield.setBase(clazz.getName());
            }
            _fields.set(xfield.name(), xfield);
         }
      }
      // Child
      Class<?> superClass = clazz.getSuperclass();
      if(superClass != null){
         loadFields(superClass);
      }
   }

   public void loadMethods(Class<?> clazz){
      Method serialize = RClass.findMethod(clazz, "onSerialize");
      if(null != serialize){
         _serializeMethod = new FXmlMethod(this, serialize);
      }
      Method unserialize = RClass.findMethod(clazz, "onUnserialize");
      if(null != unserialize){
         _unserializeMethod = new FXmlMethod(this, unserialize);
      }
   }

   public String name(){
      return _name;
   }

   public Object[] newArrayInstance(int count){
      return RArray.newInstance(_class, count);
   }

   public Object newInstance(){
      if(!_class.isEnum()){
      }
      return RClass.newInstance(_class);
   }

   public FXmlMethod serializeMethod(){
      return _serializeMethod;
   }

   protected boolean testStore(Field field){
      int modifier = field.getModifiers();
      if((modifier & Modifier.STATIC) != 0){
         return false;
      }else if((modifier & Modifier.TRANSIENT) != 0){
         return false;
      }
      return true;
   }

   public FXmlMethod unserializeMethod(){
      return _unserializeMethod;
   }
}
