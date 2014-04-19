package org.mo.com.lang.reflect;

import java.lang.reflect.Method;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FObject;
import org.mo.com.lang.FStrings;
import org.mo.com.lang.RDump;
import org.mo.com.lang.generic.TDumpInfo;

//============================================================
// <T>描述器。</T>
//============================================================
public abstract class MDescriptor
      extends FObject
      implements
         IDescriptor
{
   // 获得前缀
   public final static String GETTER = "get";

   // 获得前缀长度
   public final static int GETTER_LENGTH = GETTER.length();

   // 设置前缀
   public final static String SETTER = "set";

   // 设置前缀长度
   public final static int SETTER_LENGTH = SETTER.length();

   // 获得函数过滤器
   public static FStrings FLT_GET_METHODS = new FStrings();

   // 设置函数过滤器
   public static FStrings FLT_SET_METHODS = new FStrings();

   //============================================================
   // <T>静态初始化处理。</T>
   //============================================================
   static{
      try{
         FLT_GET_METHODS.push(Object.class.getMethod("getClass").toString());
      }catch(Exception e){
         throw new FFatalError(e);
      }
   }

   // 类对象
   protected Class<?> _class;

   // 回调函数集合
   protected FInvokeMethods _methods = new FInvokeMethods();

   // 回调函数字典
   protected FInvokeMethodDictionary _methodDictionary = new FInvokeMethodDictionary();

   //============================================================
   // <T>构造描述器。</T>
   //============================================================
   public MDescriptor(){
   }

   //============================================================
   // <T>构造描述器。</T>
   //
   // @parma item 对象
   //============================================================
   public MDescriptor(Object item){
      link(RClass.findClass(item));
   }

   //============================================================
   // <T>关联类对象。</T>
   //
   // @param clazz 类对象
   //============================================================
   public void link(Class<?> clazz){
      _class = clazz;
      buildMethods();
   }

   //============================================================
   // <T>判断函数集合是否为空。</T>
   //
   // @return 是否为空
   //============================================================
   public boolean isEmpty(){
      return _methods.isEmpty();
   }

   //============================================================
   // <T>获得总数。</T>
   //
   // @return 总数
   //============================================================
   public int count(){
      return _methods.count();
   }

   //============================================================
   // <T>获得函数集合。</T>
   //
   // @return 函数集合
   //============================================================
   public FInvokeMethods methods(){
      return _methods;
   }

   //============================================================
   // <T>获得索引位置的函数。</T>
   //
   // @param index 索引位置
   // @return 函数
   //============================================================
   public FInvokeMethod method(int index){
      return _methodDictionary.value(index);
   }

   //============================================================
   // <T>获得指定名称的函数。</T>
   //
   // @param name 指定名称
   // @return 函数
   //============================================================
   public FInvokeMethod method(String name){
      return _methodDictionary.get(name);
   }

   //============================================================
   // <T>获得对象中指定索引的类型。</T>
   //
   // @param index 索引
   // @return 类型
   //============================================================
   public Class<?> type(int index){
      FInvokeMethod method = _methodDictionary.value(index);
      if(null != method){
         return method.type();
      }
      return null;
   }

   //============================================================
   // <T>获得对象中指定名称的类型。</T>
   //
   // @param name 名称
   // @return 类型
   //============================================================
   @Override
   public Class<?> type(String name){
      if(null != name){
         FInvokeMethod method = _methodDictionary.get(name);
         if(null != method){
            return method.type();
         }
      }
      return null;
   }

   //============================================================
   // <T>获得对象中指定索引的内容。</T>
   //
   // @param item 对象
   // @param index 索引
   // @return 内容
   //============================================================
   public Object get(Object item,
                     int index){
      FInvokeMethod method = _methodDictionary.value(index);
      return (method != null) ? method.get(item) : null;
   }

   //============================================================
   // <T>获得对象中指定名称的内容。</T>
   //
   // @param item 对象
   // @param name 名称
   // @return 内容
   //============================================================
   @Override
   public Object get(Object item,
                     String name){
      if((null != item) && (null != name)){
         FInvokeMethod method = _methodDictionary.find(name);
         if(null != method){
            return method.get(item);
         }else{
            throw new FFatalError("Can't find method in item. (item={1}, name={2})", item, name);
         }
      }
      return null;
   }

   //============================================================
   // <T>获得对象中的所有内容。</T>
   //
   // @param item 对象
   // @return 内容集合
   //============================================================
   public Object[] gets(Object item){
      Object[] values = null;
      int count = _methodDictionary.count();
      if(count > 0){
         values = new Object[count];
         for(int n = 0; n < count; n++){
            FInvokeMethod method = _methodDictionary.value(n);
            if(null != method){
               values[n] = method.get(item);
            }
         }
      }
      return values;
   }

   //============================================================
   // <T>设置对象中指定索引的内容。</T>
   //
   // @param item 对象
   // @param index 索引
   // @return 内容
   //============================================================
   public void set(Object item,
                   int index,
                   Object value){
      FInvokeMethod method = _methodDictionary.value(index);
      if(null != method){
         method.set(item, value);
      }
   }

   //============================================================
   // <T>设置对象中指定名称的内容。</T>
   //
   // @param item 对象
   // @param name 名称
   // @return 内容
   //============================================================
   @Override
   public void set(Object item,
                   String name,
                   Object value){
      if((null != item) && (null != name)){
         FInvokeMethod method = _methodDictionary.find(name);
         if(method != null){
            method.set(item, value);
         }
      }
   }

   //============================================================
   // <T>过滤获得函数是否可用。</T>
   //
   // @param method 函数对象
   // @return 是否可用
   //============================================================
   protected boolean filterGetMethod(Method method){
      if(null != method){
         int count = FLT_GET_METHODS.count();
         for(int n = 0; n < count; n++){
            if(FLT_GET_METHODS.get(n).equals(method.toString())){
               return false;
            }
         }
      }
      return true;
   }

   //============================================================
   // <T>过滤设置函数是否可用。</T>
   //
   // @param method 函数对象
   // @return 是否可用
   //============================================================
   protected boolean filterSetMethod(Method method){
      if(null != method){
         int count = FLT_SET_METHODS.count();
         for(int n = 0; n < count; n++){
            if(FLT_GET_METHODS.get(n).equals(method.toString())){
               return false;
            }
         }
      }
      return true;
   }

   //============================================================
   // <T>创建一个函数对象。</T>
   //
   // @parma name 名称
   // @parma getter 获得函数
   // @parma setter 设置函数
   // @return 函数对象
   //============================================================
   protected FInvokeMethod createMethod(String name,
                                        Method getter,
                                        Method setter){
      // 检查参数
      if(null == name){
         return null;
      }
      if((null == getter) && (null == setter)){
         return null;
      }
      if((null != getter) && !filterGetMethod(getter)){
         if(null == setter){
            return null;
         }
         getter = null;
      }
      if((null != setter) && !filterSetMethod(setter)){
         if(null == getter){
            return null;
         }
         setter = null;
      }
      // 检查存在性
      FInvokeMethod method = _methods.findByName(name);
      if(null != method){
         return method;
      }
      // 建立调用函数
      method = new FInvokeMethod();
      method.setName(name);
      method.setGetter(getter);
      method.setSetter(setter);
      // 设置类型
      if(getter != null){
         method.setType(getter.getReturnType());
      }else if(setter != null){
         Class<?>[] types = setter.getParameterTypes();
         if(1 == types.length){
            method.setType(types[0]);
         }else{
            throw new FFatalError("Invalid setter method. (name={1}, getter={1}, setter={2})", name, getter, setter);
         }
      }
      // 放入缓冲
      if(null != method){
         _methods.push(method);
      }
      return method;
   }

   //============================================================
   // <T>注册一个函数对象。</T>
   //
   // @parma name 名称
   // @parma method 函数对象
   //============================================================
   protected void registerMethod(String name,
                                 FInvokeMethod method){
      if(!_methodDictionary.contains(name)){
         _methodDictionary.set(name, method);
      }
   }

   //============================================================
   // <T>建立函数对象集合。</T>
   //============================================================
   protected abstract void buildMethods();

   //============================================================
   // <T>生成运行信息。</T>
   //
   // @param item 对象
   // @return 运行信息
   //============================================================
   public TDumpInfo dump(Object item){
      TDumpInfo info = new TDumpInfo(item);
      RDump.dump(info, this);
      info.append(" [ ");
      int n = -1;
      int count = _methods.count();
      while(++n < count){
         if(n != 0){
            info.append(", ");
         }
         FInvokeMethod method = _methods.get(n);
         String name = method.name();
         Object value = get(item, name);
         info.append(name);
         info.append("=");
         info.append(value);
      }
      info.append(" ]");
      return info;
   }
   //   public void setMap(Object item,
   //                      FNameObjectMap map){
   //      int count = map.count();
   //      for(int n = 0; n < count; n++){
   //         String name = map.name(n);
   //         FInvokeMethod info = _methods.get(name);
   //         if(null != info){
   //            info.set(item, map.get(name));
   //         }
   //      }
   //   }
   //
   //   public FNameObjectMap toNameMap(Object item){
   //      int n = -1;
   //      int count = _methods.count();
   //      FNameObjectMap map = new FNameObjectMap();
   //      while(++n < count){
   //         map.set(_methods.name(n), _methods.value(n).get(item));
   //      }
   //      return map;
   //   }
}
