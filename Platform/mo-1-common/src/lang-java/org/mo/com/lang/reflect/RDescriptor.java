package org.mo.com.lang.reflect;

import org.mo.com.lang.IPairs;
import org.mo.com.text.EWordFormat;
import org.mo.com.text.RDatabaseFormat;
import org.mo.com.text.RJavaFormat;

//============================================================
// <T>描述器工具类。</T>
//============================================================
public class RDescriptor
{
   // 类描述器集合
   private static FClassDescriptors _classes = new FClassDescriptors();

   // 描述其字典集合
   private static FDescriptorsDictionary _descriptorsDictionary = new FDescriptorsDictionary();

   //============================================================
   // <T>根据类对象，查找指定对象的类描述器。</T>
   //
   // @param clazz 类对象
   // @param item 指定对象
   // @return 指定类描述器
   //============================================================
   @SuppressWarnings("unchecked")
   public static <V> V find(Class<V> clazz,
                            Object item){
      V descriptor = null;
      if((clazz != null) && (item != null)){
         // 获得描述器集合
         String className = clazz.getName();
         FDescriptors descriptors = _descriptorsDictionary.find(className);
         if(descriptors == null){
            descriptors = new FDescriptors();
            _descriptorsDictionary.set(className, descriptors);
         }
         // 获得描述器
         Class<?> itemClass = RClass.findClass(item);
         String itemName = itemClass.getName();
         MDescriptor itemDescriptor = descriptors.find(itemName);
         if(itemDescriptor == null){
            itemDescriptor = (MDescriptor)RClass.newInstance(clazz);
            itemDescriptor.link(itemClass);
            descriptors.set(itemName, itemDescriptor);
         }
         descriptor = (V)itemDescriptor;
      }
      return descriptor;
   }

   //============================================================
   // <T>查找指定对象的属性描述器。</T>
   //
   // @param item 指定对象
   // @return 属性描述器
   //============================================================
   public static FPropertyDescriptor findProperty(Object item){
      return find(FPropertyDescriptor.class, item);
   }

   //============================================================
   // <T>查找指定对象的类描述器。</T>
   //
   // @param item 指定对象
   // @return 类描述器
   //============================================================
   public static FClassDescriptor findClass(Object item){
      Class<?> clazz = RClass.findClass(item);
      String name = clazz.getName();
      FClassDescriptor descriptor = _classes.get(name);
      if(null == descriptor){
         descriptor = new FClassDescriptor();
         descriptor.link(clazz);
         _classes.set(name, descriptor);
      }
      return descriptor;
   }

   //============================================================
   // <T>查找指定对象的函数描述器。</T>
   //
   // @param item 指定对象
   // @return 函数描述器
   //============================================================
   public static FMethodDescriptor findMethod(Object item,
                                              String method){
      FClassDescriptor descriptor = findClass(item);
      return descriptor.findMethod(method);
   }

   @SuppressWarnings("rawtypes")
   public static void toObject(IPairs pairs,
                               Object item){
      toObject(pairs, item, EWordFormat.Default);
   }

   @SuppressWarnings("rawtypes")
   public static void toObject(IPairs pairs,
                               Object item,
                               EWordFormat format){
      if(null != item && null != pairs){
         FSetterDescriptor dsp = find(FSetterDescriptor.class, item);
         int count = pairs.count();
         for(int n = 0; n < count; n++){
            String name = (String)pairs.name(n);
            if(EWordFormat.JavaMethod == format){
               name = RDatabaseFormat.toJavaMethodName(name).toLowerCase();
            }
            dsp.set(item, name, pairs.value(n));
         }
      }
   }

   @SuppressWarnings("rawtypes")
   public static void toPairs(Object item,
                              IPairs pairs){
      toPairs(item, pairs, EWordFormat.Default);
   }

   @SuppressWarnings({"rawtypes", "unchecked"})
   public static void toPairs(Object item,
                              IPairs pairs,
                              EWordFormat format){
      if(null != item && null != pairs){
         FGetterDescriptor dsp = find(FGetterDescriptor.class, item);
         int count = dsp.count();
         for(int n = 0; n < count; n++){
            String name = dsp.method(n).name();
            if(EWordFormat.UnderlineFieldLower == format){
               name = RJavaFormat.toDatabaseObjectName(name).toLowerCase();
            }else if(EWordFormat.UnderlineFieldUpper == format){
               name = RJavaFormat.toDatabaseObjectName(name).toUpperCase();
            }
            pairs.set(name, dsp.get(item, n));
         }
      }
   }
}
