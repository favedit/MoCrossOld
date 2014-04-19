package org.mo.com.lang;

import java.lang.reflect.Field;

//============================================================
// <T>枚举工具类。</T>
//============================================================
public class REnum
{
   //============================================================
   // <T>根据字符串获得枚举内容。</T>
   //
   // @param clazz 类对象
   // @param name 名称
   // @return 内容
   //============================================================
   @SuppressWarnings("unchecked")
   public static <T> T parse(Class<T> clazz,
                             String name){
      try{
         for(Field field : clazz.getFields()){
            if(field.getName().equalsIgnoreCase(name)){
               return (T)field.get(clazz);
            }
         }
      }catch(Exception e){
         throw new FFatalError("Parse enum type error. (type={1}, name={2})", clazz, name);
      }
      return null;
   }

   //============================================================
   // <T>根据字符串获得枚举内容。</T>
   //
   // @param clazz 类对象
   // @param name 名称
   // @param defaultCd 默认类型
   // @return 内容
   //============================================================
   @SuppressWarnings("unchecked")
   public static <T> T parse(Class<T> clazz,
                             String name,
                             T defaultCd){
      try{
         for(Field field : clazz.getFields()){
            if(field.getName().equalsIgnoreCase(name)){
               return (T)field.get(clazz);
            }
         }
      }catch(Exception e){
         throw new FFatalError("Parse enum type error. (type={1}, name={2})", clazz, name);
      }
      return defaultCd;
   }

   //============================================================
   // <T>根据字符串内容获得枚举内容。</T>
   //
   // @param clazz 类对象
   // @param name 名称
   // @return 内容
   //============================================================
   @SuppressWarnings("unchecked")
   public static <T> T parseByString(Class<T> clazz,
                                     String name){
      try{
         for(Field field : clazz.getFields()){
            T value = (T)field.get(clazz);
            if(value.toString().equals(name)){
               return value;
            }
         }
      }catch(Exception e){
         throw new FFatalError("Parse enum type error. (type={1}, name={2})", clazz, name);
      }
      return null;
   }

   //============================================================
   // <T>根据字符串的开始部分获得枚举内容。</T>
   //
   // @param clazz 类对象
   // @param name 名称
   // @return 内容
   //============================================================
   @SuppressWarnings("unchecked")
   public static <T> T parseStart(Class<T> clazz,
                                  String name){
      try{
         for(Field field : clazz.getFields()){
            if(field.getName().startsWith(name)){
               return (T)field.get(clazz);
            }
         }
      }catch(Exception e){
         throw new FFatalError("Parse enum type error. (type={1}, name={2})", clazz, name);
      }
      return null;
   }

   //============================================================
   // <T>获得类的字符串。</T>
   //
   // @param clazz 类对象
   // @return 字符串
   //============================================================
   public static String toString(Class<?> clazz){
      try{
         return clazz.getName();
      }catch(Exception e){
         throw new FFatalError("Change enum type error. (type={1})", clazz);
      }
   }

   //============================================================
   // <T>获得类的字符串。</T>
   //
   // @param clazz 类对象
   // @return 字符串
   //============================================================
   public static String toString(Object item){
      try{
         return item.toString();
      }catch(Exception e){
         throw new FFatalError("Change enum type error. (item={1})", item);
      }
   }
}
