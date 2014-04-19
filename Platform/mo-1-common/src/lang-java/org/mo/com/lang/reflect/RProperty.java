package org.mo.com.lang.reflect;

import java.util.Date;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.FString;
import org.mo.com.lang.INameValue;
import org.mo.com.lang.RString;

//============================================================
// <T>属性工具类。</T>
//============================================================
public class RProperty
{
   // 基础类集合
   public static final Class<?>[] CON_CLASSES = {Boolean.class, Character.class, Short.class, Integer.class, Float.class, Date.class, String.class, FString.class};

   //============================================================
   // <T>判断是否可递归类。</T>
   //
   // @param container 容器对象
   // @return 是否可递归类
   //============================================================
   public static boolean isDeep(Object item){
      if(null != item){
         Class<?> clazz = item.getClass();
         for(Class<?> cls : CON_CLASSES){
            if(clazz == cls){
               return false;
            }
         }
         return true;
      }
      return false;
   }

   //============================================================
   // <T>根据名称获得内容。</T>
   //
   // @param container 容器
   // @param name 名称
   // @return 内容
   //============================================================
   @SuppressWarnings({"unchecked", "rawtypes"})
   public static Object get(Object item,
                            String name){
      Object result = null;
      try{
         if(null != item){
            if(item instanceof INameValue){
               INameValue nameValue = (INameValue)item;
               result = nameValue.get(name, null);
            }else{
               if(isDeep(item)){
                  FGetDescriptor descriptor = RDescriptor.find(FGetDescriptor.class, item.getClass());
                  if(null != descriptor){
                     result = descriptor.get(item, name);
                  }
               }
            }
         }
      }catch(Exception e){
         throw new FFatalError(e, "Get item value. (item={1}, name={2})", item, name);
      }
      return result;
   }

   //============================================================
   // <T>根据名称获得字符串内容。</T>
   //
   // @param container 容器
   // @param name 名称
   // @return 字符串内容
   //============================================================
   public static String getAsString(Object item,
                                    String name){
      Object value = get(item, name);
      return RString.toString(value);
   }

   //============================================================
   // <T>根据名称设置内容。</T>
   //
   // @param container 容器
   // @param name 名称
   // @param value 内容
   //============================================================
   @SuppressWarnings({"unchecked", "rawtypes"})
   public static void set(Object item,
                          String name,
                          Object value){
      if(null != item){
         if(item instanceof INameValue){
            ((INameValue)item).set(name, value);
         }else{
            if(isDeep(item)){
               FPropertyDescriptor descriptor = RDescriptor.findProperty(item.getClass());
               if(descriptor != null){
                  descriptor.set(item, name, value);
               }
            }
         }
      }
   }
}
