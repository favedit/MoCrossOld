package org.mo.com.lang.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import org.mo.com.collections.FObjectDictionary;
import org.mo.com.lang.FDictionary;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FString;
import org.mo.com.lang.RDump;
import org.mo.com.lang.RHex;
import org.mo.com.lang.RString;
import org.mo.com.lang.generic.MString;

//============================================================
// <T>类对象工具类。</T>
//============================================================
public class RClass
{
   // 分割字符
   public static final char SEPARATOR_CHAR = '.';

   // 类加载器
   protected static FAppClassLoader _classLoader = new FAppClassLoader();

   // 类对象字典
   protected static FObjectDictionary _classes = new FObjectDictionary();

   //============================================================
   // <T>获得类加载器。</T>
   //
   // @return 加载器
   //============================================================
   public static void setupClassLoader(ClassLoader parent){
      _classLoader = new FAppClassLoader(parent);
   }

   //============================================================
   // <T>获得类加载器。</T>
   //
   // @return 加载器
   //============================================================
   public static FAppClassLoader classLoader(){
      return _classLoader;
   }

   //============================================================
   // <T>获得类集合。</T>
   //
   // @return 类集合
   //============================================================
   public static FObjectDictionary classes(){
      return _classes;
   }

   //============================================================
   // <T>获得对象的包名称。</T>
   //
   // @param name 名称
   // @return 类包名称
   //============================================================
   public static String packageName(String name){
      if(null != name){
         int index = name.lastIndexOf(SEPARATOR_CHAR);
         if(-1 != index){
            return name.substring(0, index);
         }
         return RString.EMPTY;
      }
      return null;
   }

   //============================================================
   // <T>获得对象的包名称。</T>
   //
   // @param item 对象
   // @return 类包名称
   //============================================================
   public static String packageName(Object item){
      if(null != item){
         String name = null;
         if(item instanceof String){
            name = (String)item;
         }else if(item instanceof Class<?>){
            name = ((Class<?>)item).getName();
         }else{
            name = item.getClass().getName();
         }
         int index = name.lastIndexOf(SEPARATOR_CHAR);
         if(-1 != index){
            return name.substring(0, index);
         }
         return RString.EMPTY;
      }
      return null;
   }

   //============================================================
   // <T>获得类名称的短名称。</T>
   //
   // @param name 类名称
   // @return 类短名称
   //============================================================
   public static String shortName(String value){
      if(null != value){
         int index = value.lastIndexOf(SEPARATOR_CHAR);
         return (index != -1) ? value.substring(index + 1) : value;
      }
      return null;
   }

   //============================================================
   // <T>获得类对象的短名称。</T>
   //
   // @param clazz 类对象
   // @return 类短名称
   //============================================================
   public static String shortName(Class<?> clazz){
      if(null != clazz){
         return shortName(clazz.getName());
      }
      return null;
   }

   //============================================================
   // <T>获得对象的短名称。</T>
   //
   // @param item 对象
   // @return 类短名称
   //============================================================
   public static String shortName(Object item){
      if(null != item){
         String name = null;
         if(item instanceof Class<?>){
            name = ((Class<?>)item).getName();
         }else if(item instanceof String){
            name = (String)item;
         }else{
            name = item.getClass().getName();
         }
         int index = name.lastIndexOf(SEPARATOR_CHAR);
         return (index != -1) ? name.substring(index + 1) : name;
      }
      return null;
   }

   //============================================================
   // <T>获得指定对象的类的路径。</T>
   // <P>
   //    当传入参数为类对象时,认为是当前类的名称。<br>
   //    当传入参数为字符串时,认为是类的名称。<br>
   //    当传入参数为对象时,认为是实例的类的名称。
   // </P>
   //
   // @param item 对象
   // @return 类的路径
   //============================================================
   public static String classPath(Object item){
      if(item != null){
         String name = null;
         if(item instanceof Class){
            name = ((Class<?>)item).getName();
         }else if(item instanceof String){
            name = (String)item;
         }else{
            name = item.getClass().getName();
         }
         int lastIndex = name.lastIndexOf('.');
         if(-1 != lastIndex){
            return name.substring(0, lastIndex);
         }
         return name;
      }
      return null;
   }

   //============================================================
   // <T>判断指定的类名是否存在。</T>
   //
   // @param className 类名称
   // @return TRUE：存在<br>FALSE：不存在
   //============================================================
   public static boolean exists(String className){
      if(!RString.isEmpty(className)){
         try{
            Class.forName(className);
            return true;
         }catch(Exception oException){
         }
      }
      return false;
   }

   //============================================================
   // <T>判断指定的对象的函数是否存在。</T>
   //
   // @param item 对象
   // @param methodName 函数名称
   // @return TRUE：存在<br>FALSE：不存在
   //============================================================
   public static boolean existsMethod(Object item,
                                      String methodName){
      return (null != findMethod(item, methodName, false));
   }

   //============================================================
   // <T>判断指定的对象是否继承与指定类。</T>
   //
   // @param item 对象
   // @param clazz 类对象
   // @return TRUE：存在<br>FALSE：不存在
   //============================================================
   public static boolean isInherit(Object item,
                                   Class<?> clazz){
      if((null != item) && (null != clazz)){
         Class<?> find = (item instanceof Class<?>) ? (Class<?>)item : item.getClass();
         do{
            if(find == clazz){
               return true;
            }
            find = find.getSuperclass();
         }while(null != find);
      }
      return false;
   }

   //============================================================
   // <T>获得指定类名称的类对象。</T>
   //
   // @param name 类名称
   // @return 类实例
   //============================================================
   @SuppressWarnings("unchecked")
   public static <V> FClass<V> find(String className){
      // 检查参数
      if(RString.isEmpty(className)){
         return null;
      }
      // 查找类对象
      FClass<V> findClass = (FClass<V>)_classes.find(className);
      if(null != findClass){
         return findClass;
      }
      // 创建类对象
      try{
         Class<V> nativeClass = (Class<V>)_classLoader.loadClass(className);
         FClass<V> newClass = new FClass<V>(nativeClass);
         _classes.set(className, newClass);
         return newClass;
      }catch(Exception e){
         throw new FFatalError(e, "Can't find class. (class_name={1})", className);
      }
   }

   //============================================================
   // <T>获得指定类名称的类对象。</T>
   //
   // @param name 类名称
   // @return 类实例
   //============================================================
   public static <V> FClass<V> find(Class<V> clazz){
      return find(clazz.getName());
   }

   //============================================================
   // <T>获得指定类名称的类实例。</T>
   //
   // @param className 类名称
   // @return 类实例
   //============================================================
   @SuppressWarnings("unchecked")
   public static <V> Class<V> findClass(String className){
      Class<?> clazz = null;
      try{
         clazz = Class.forName(className);
      }catch(Exception e){
         throw new FFatalError(e, "Find class failure. (class_name={1})", className);
      }
      return (Class<V>)clazz;
   }

   //============================================================
   // <T>获得指定类名称的类实例。</T>
   // <P>
   //    当传入参数为类对象时,认为是当前类 <br>
   //    当传入参数为字符串时,认为是当前名称的类 <br>
   //    当传入参数为对象时,认为是实例的类
   // </P>
   //
   // @param item 对象
   // @return 类实例
   //============================================================
   @SuppressWarnings("unchecked")
   public static <V> Class<V> findClass(Object item){
      Class<?> clazz = null;
      if(null != item){
         if(item instanceof Class){
            clazz = (Class<?>)item;
         }else if(item instanceof String){
            try{
               clazz = Class.forName((String)item);
            }catch(Exception e){
               throw new FFatalError(e, "Find class failure. (item={1})", item);
            }
         }else{
            clazz = item.getClass();
         }
      }
      return (Class<V>)clazz;
   }

   //============================================================
   // <T>获得指定对象的类的字段对象。</T>
   //
   // @param item 指定对象
   // @param fieldName 方法名称
   // @return 字段对象
   //============================================================
   public static Field findField(Object oItem,
                                 String fieldName){
      return findField(oItem, fieldName, false);
   }

   //============================================================
   // <T>获得指定对象的类的字段对象。</T>
   // <P>
   //    当传入参数为类对象时,认为是当前类 <br>
   //    当传入参数为字符串时,认为是当前名称的类 <br>
   //    当传入参数为对象时,认为是实例的类
   // </P>
   //
   // @param item 指定对象
   // @param fieldName 方法名称
   // @param careCase 是否关心名称的大小写
   // @return 字段对象
   //============================================================
   public static Field findField(Object item,
                                 String fieldName,
                                 boolean careCase){
      try{
         if((null != item) && !RString.isEmpty(fieldName)){
            // 查找操作类
            Class<?> clazz = findClass(item);
            // 查找类中的字段
            Field field = null;
            Field[] fields = clazz.getDeclaredFields();
            int count = fields.length;
            for(int n = 0; n < count; n++){
               field = fields[n];
               if(careCase){
                  if(field.getName().equals(fieldName)){
                     return field;
                  }
               }else{
                  if(field.getName().equalsIgnoreCase(fieldName)){
                     return field;
                  }
               }
            }
         }
         return null;
      }catch(Exception exception){
         throw new FFatalError(exception);
      }
   }

   //============================================================
   // <T>获得指定对象的类的方法对象。</T>
   //
   // @param item 指定对象
   // @param name 方法名称
   // @return 方法对象
   //============================================================
   public static Method findMethod(Object item,
                                   String name){
      return findMethod(item, name, false);
   }

   //============================================================
   // <T>获得指定对象的类的方法对象。</T>
   // <P>
   //    当传入参数为类对象时,认为是当前类 <br>
   //    当传入参数为字符串时,认为是当前名称的类 <br>
   //    当传入参数为对象时,认为是实例的类
   // </P>
   //
   // @param item 指定对象
   // @param name 方法名称
   // @param careCase 是否关心名称的大小写
   // @return 方法对象
   //============================================================
   public static Method findMethod(Object item,
                                   String name,
                                   boolean careCase){
      if(RString.isEmpty(name)){
         return null;
      }
      try{
         // 查找操作类
         Class<?> clazz = findClass(item);
         while(null != clazz){
            // 查找类中的方法
            for(Method method : clazz.getDeclaredMethods()){
               if(careCase){
                  if(method.getName().equals(name)){
                     return method;
                  }
               }else{
                  if(name.equalsIgnoreCase(method.getName())){
                     return method;
                  }
               }
            }
            clazz = clazz.getSuperclass();
         }
         return null;
      }catch(Exception e){
         throw new FFatalError(e, "Find class method. (item={1}, name={2}, care_case={3})", item, name, careCase);
      }
   }

   //============================================================
   // <T>创建指定对象类的新实例。</T>
   //
   // @param clazz 类对象
   // @return 类实例
   //============================================================
   public static <V> V newInstance(Class<V> clazz){
      try{
         return clazz.newInstance();
      }catch(Exception e){
         throw new FFatalError(e, "Create instance from class failure. (class={1})", clazz);
      }
   }

   //============================================================
   // <T>创建指定对象类的新实例。</T>
   // <P>
   //    当传入参数为类对象时,认为是当前类 <br>
   //    当传入参数为字符串时,认为是当前名称的类 <br>
   //    当传入参数为对象时,认为是实例的类
   // </P>
   //
   // @param item 对象
   // @return 类实例
   //============================================================
   @SuppressWarnings("unchecked")
   public static <V> V newInstance(Object item){
      try{
         return (V)findClass(item).newInstance();
      }catch(Exception e){
         throw new FFatalError(e, "Create instance from object failure. (item={1})", item);
      }
   }

   //============================================================
   // <T>获得类的函数字典。</T>
   //
   // @param item 对象
   // @return 函数字典
   //============================================================
   public static FDictionary<Method> makeMethodMap(Object item){
      FDictionary<Method> methodMap = new FDictionary<Method>(Method.class);
      Class<?> clazz = findClass(item);
      for(Method method : clazz.getMethods()){
         methodMap.set(method.getName(), method);
      }
      return methodMap;
   }

   //============================================================
   // <T>增加类路径。</T>
   //
   // @param classPath 类路径
   //============================================================
   public static void appendClassPath(String classPath){
      _classLoader.appendClassPath(classPath);
   }

   //============================================================
   // <T>调用指定对象中的方法。</T>
   // <p>如果指定对象是类，则调用类中的静态方法</p>
   // <p>
   // 当传入参数为类对象时,认为是当前类 <br>
   // 当传入参数为字符串时,认为是当前名称的类 <br>
   // 当传入参数为对象时,认为是实例的类
   // </p>
   //
   // @param item 对象
   // @param name 方法名称
   // @return 调用结果
   //============================================================
   public static Object invokeGet(Object item,
                                  String name){
      try{
         Object result = null;
         if((null != item) && (null != name)){
            Class<?> clazz = item.getClass();
            for(Method method : clazz.getMethods()){
               if(method.getName().equalsIgnoreCase(name)){
                  result = method.invoke(item);
                  break;
               }
            }
         }
         return result;
      }catch(Exception e){
         throw new FFatalError(e, "Class invoke get. ({0}={1})", item, name);
      }
   }

   //============================================================
   // <T>调用指定对象中的方法。</T>
   // <p>如果指定对象是类，则调用类中的静态方法</p>
   // <p>
   // 当传入参数为类对象时,认为是当前类 <br>
   // 当传入参数为字符串时,认为是当前名称的类 <br>
   // 当传入参数为对象时,认为是实例的类
   // </p>
   //
   // @param item 对象
   // @param name 方法名称
   // @param value 内容对象
   // @return 调用结果
   //============================================================
   public static boolean invokeSet(Object item,
                                   String name,
                                   Object value){
      try{
         if((null != item) && (null != name)){
            Class<?> clazz = item.getClass();
            for(Method method : clazz.getMethods()){
               if(method.getName().equalsIgnoreCase(name)){
                  method.invoke(item, value);
                  return true;
               }
            }
         }
         return false;
      }catch(Exception e){
         throw new FFatalError(e, "Class invoke set. ({0}={1})", item, name);
      }
   }

   //============================================================
   // <T>获得类的内部信息。</T>
   //
   // @param item 对象
   // @return 字符串
   //============================================================
   public static String dump(Object item){
      FString dump = new FString();
      dump(dump, item);
      return dump.toString();
   }

   //============================================================
   // <T>获得类的内部信息。</T>
   //
   // @param dump 字符串
   // @param item 对象
   //============================================================
   public static void dump(MString dump,
                           Object item){
      if(null == item){
         dump.append("@null");
      }else{
         dump.append(shortName(item.getClass().getName()));
         dump.append('@');
         dump.append(RHex.toString(item.hashCode(), 8));
      }
   }

   //============================================================
   // <T>获得类的内部信息。</T>
   //
   // @param dump 字符串
   // @param item 对象
   //============================================================
   public static void dumpClass(MString dump,
                                Object item){
      Class<?> clazz = findClass(item);
      dump.appendLine(clazz);
      dump.appendLine(RDump.LINE_L2);
      dump.appendLine("Interface:");
      for(Class<?> face : clazz.getInterfaces()){
         dump.append("   ");
         dump.append(face);
         dump.append("\n");
      }
      dump.appendLine(RDump.LINE_L2);
      dump.appendLine("Field:");
      for(Field filed : clazz.getFields()){
         dump.append("   ");
         dump.append(filed);
         dump.append("\n");
      }
      dump.appendLine(RDump.LINE_L2);
      dump.appendLine("Method:");
      for(Method method : clazz.getMethods()){
         dump.append("   ");
         dump.append(method);
         dump.append("\n");
      }
   }
   //
   //   //============================================================
   //   // <T>判断指定的类名是否存在。</T>
   //   //
   //   // @param className 类名称
   //   // @return TRUE：存在<br>FALSE：不存在
   //   //============================================================
   //   public static void check(String className){
   //      try{
   //         Class.forName(className);
   //      }catch(Exception e){
   //         throw new FFatalError(e, "Can't find class. (class_name={1})", className);
   //      }
   //   }
   //
   //
   //   //============================================================
   //   // <T>获得指定对象中的字段内容。</T>
   //   //
   //   // @param item 指定对象
   //   // @param fieldName 字段名称
   //   // @return 字段内容
   //   //============================================================
   //   public static String invokeFieldAsString(Object item,
   //                                            String fieldName){
   //      return invokeFieldAsString(item, fieldName, false);
   //   }
   //
   //   //============================================================
   //   // <T>获得指定对象中的字段内容。</T>
   //   // <p>如果指定对象是类，则调用类中的静态方法</p>
   //   // <p>
   //   // 当传入参数为类对象时,认为是当前类 <br>
   //   // 当传入参数为字符串时,认为是当前名称的类 <br>
   //   // 当传入参数为对象时,认为是实例的类
   //   // </p>
   //   //
   //   // @param item 指定对象
   //   // @param fieldName 字段名称
   //   // @param careCase 是否关心名称的大小写
   //   // @return 字段内容
   //   //============================================================
   //   public static String invokeFieldAsString(Object item,
   //                                            String fieldName,
   //                                            boolean careCase){
   //      try{
   //         Class<?> clazz = findClass(item);
   //         Field field = findField(clazz, fieldName, careCase);
   //         return field.get(clazz).toString();
   //      }catch(Exception exception){
   //         throw new FFatalError(exception);
   //      }
   //   }
   //
   //   //============================================================
   //   // <T>调用指定对象中的方法。</T>
   //   //
   //   // @param item 指定对象
   //   // @param methodName 方法名称
   //   // @param params 参数集合
   //   // @return 调用结果
   //   //============================================================
   //   public static Object invokeMethod(Object item,
   //                                     String methodName,
   //                                     Object[] params){
   //      return invokeMethod(item, methodName, false, params);
   //   }
   //
   //   //============================================================
   //   // <T>调用指定对象中的方法。</T>
   //   // <p>如果指定对象是类，则调用类中的静态方法</p>
   //   // <p>
   //   // 当传入参数为类对象时,认为是当前类 <br>
   //   // 当传入参数为字符串时,认为是当前名称的类 <br>
   //   // 当传入参数为对象时,认为是实例的类
   //   // </p>
   //   //
   //   // @param item 指定对象
   //   // @param methodName 方法名称
   //   // @param careCase 是否关心名称的大小写
   //   // @param params 参数集合
   //   // @return 调用结果
   //   //============================================================
   //   public static Object invokeMethod(Object item,
   //                                     String methodName,
   //                                     boolean careCase,
   //                                     Object[] params){
   //      try{
   //         Object result = null;
   //         if((null != item) && !RString.isEmpty(methodName)){
   //            Class<?> clazz = findClass(item);
   //            Method[] methods = clazz.getMethods();
   //            int count = methods.length;
   //            for(int n = 0; n < count; n++){
   //               Method method = methods[n];
   //               if(careCase){
   //                  if(method.getName().equals(methodName)){
   //                     result = method.invoke(item, params);
   //                     break;
   //                  }
   //               }else{
   //                  if(method.getName().equalsIgnoreCase(methodName)){
   //                     result = method.invoke(item, params);
   //                     break;
   //                  }
   //               }
   //            }
   //         }
   //         return result;
   //      }catch(Throwable throwable){
   //         throw new FFatalError(throwable);
   //      }
   //   }
   //   //
   //   //   public static String classFilename(String path, String className){
   //   //      className = RString.replaceChars(className, '.', '/');
   //   //      return RFile.makeFilename(path, className) + ".java";
   //   //   }
   //   //
   //   //   @SuppressWarnings("unchecked")
   //   //   public static FClass<?> createClass(String name){
   //   //      if(_classes.contains(name)){
   //   //         throw new FFatalError("Class {0} is exists.", name);
   //   //      }
   //   //      try{
   //   //         FClass<?> clazz = new FClass(Class.forName(name));
   //   //         _classes.set(name, clazz);
   //   //         return clazz;
   //   //      }catch(Exception e){
   //   //         throw new FFatalError(e, "Class name [{0}]", name);
   //   //      }
   //   //   }
   //   //
   //   //   @SuppressWarnings("unchecked")
   //   //   public static FClass<?> createClass(String name, FClass<?> superClass){
   //   //      if(_classes.contains(name)){
   //   //         throw new FFatalError("Class {0} is exists.", name);
   //   //      }
   //   //      try{
   //   //         FClass<?> clazz = new FClass(Class.forName(name));
   //   //         _classes.set(name, clazz);
   //   //         return clazz;
   //   //      }catch(Exception e){
   //   //         throw new FFatalError(e, "Class name [{0}]", name);
   //   //      }
   //   //   }
   //   //
   //   //   @SuppressWarnings("unchecked")
   //   //   public static <V> FClass<V> findFromPool(String name){
   //   //      if(!RString.isEmpty(name)){
   //   //         FClass clazz = _classes.get(name);
   //   //         if(clazz == null){
   //   //            try{
   //   //               clazz = new FClass(Class.forName(name));
   //   //               _classes.set(name, clazz);
   //   //            }catch(Exception e){
   //   //               throw new FFatalError(e, "class=[{0}]", name);
   //   //            }
   //   //         }
   //   //         return clazz;
   //   //      }
   //   //      return null;
   //   //   }
   //   //
   //   //   public static <V> FClass<V> findWithoutPool(Class<?> clazz){
   //   //      return findWithoutPool(clazz.getName());
   //   //   }
   //   //
   //   //   @SuppressWarnings("unchecked")
   //   //   public static <V> FClass<V> findWithoutPool(String name){
   //   //      if(!RString.isEmpty(name)){
   //   //         FClass clazz = _classes.get(name);
   //   //         if(clazz == null){
   //   //            try{
   //   //               clazz = new FClass(Class.forName(name));
   //   //               _classes.set(name, clazz);
   //   //            }catch(Exception e){
   //   //               throw new FFatalError(e, "class=[{0}]", name);
   //   //            }
   //   //         }
   //   //         return clazz;
   //   //      }
   //   //      return null;
   //   //   }
   //   //
}
