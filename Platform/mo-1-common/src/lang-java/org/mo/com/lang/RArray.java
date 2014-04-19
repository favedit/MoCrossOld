package org.mo.com.lang;

import java.lang.reflect.Array;

//============================================================
// <T>数组工具类。</T>
//============================================================
public class RArray
{
   //============================================================
   // <T>创建指定长度的新数组。</T>
   //
   // @param clazz 类对象
   // @param capacity 新数组容量
   // @return 新数组
   //============================================================
   @SuppressWarnings("unchecked")
   public static <V> V[] newInstance(Class<V> clazz,
                                     int capacity){
      return (V[])Array.newInstance(clazz, capacity);
   }

   //============================================================
   // <T>创建指定长度的新数组。</T>
   //
   // @param clazz 类对象
   // @param memory 内存
   // @param offset 位置
   // @param length 长度
   // @return 新数组
   //============================================================
   @SuppressWarnings("unchecked")
   public static <V> V[] sub(Class<V> clazz,
                             V[] memory,
                             int offset,
                             int length){
      V[] alloc = (V[])Array.newInstance(clazz, length);
      if(memory != null){
         System.arraycopy(memory, offset, alloc, 0, length);
      }
      return alloc;
   }

   //============================================================
   // <T>复制数组中的一部分成为新的数组。</T>
   //
   // @param memory 数组
   // @param offset 数组位置
   // @param length 数组长度
   //============================================================
   public static <V> V[] copy(V[] memory,
                              int offset,
                              int length){
      return copy(memory, offset, length, null, -1);
   }

   //============================================================
   // <T>复制数组中的一部分成为新的数组。</T>
   //
   // @param memory 数组
   // @param offset 数组位置
   // @param length 数组长度
   // @param capacity 新数组容量
   //============================================================
   public static <V> V[] copy(V[] memory,
                              int offset,
                              int length,
                              int capacity){
      return copy(memory, offset, length, null, capacity);
   }

   //============================================================
   // <T>复制数组中的一部分成为新的数组。</T>
   //
   // @param memory 数组
   // @param offset 数组位置
   // @param length 数组长度
   // @param clazz 类对象
   //============================================================
   public static <V> V[] copy(V[] memory,
                              int offset,
                              int length,
                              Class<?> clazz){
      return copy(memory, offset, length, clazz, -1);
   }

   //============================================================
   // <T>复制数组中的一部分成为新的数组。</T>
   //
   // @param memory 数组
   // @param offset 数组位置
   // @param length 数组长度
   // @param clazz 类对象
   // @param capacity 新数组容量
   //============================================================
   @SuppressWarnings("unchecked")
   public static <V> V[] copy(V[] memory,
                              int offset,
                              int length,
                              Class<?> clazz,
                              int capacity){
      // 检查参数
      if(null == memory){
         throw new NullPointerException("memory");
      }
      // 设置类对象
      if(null == clazz){
         clazz = memory.getClass().getComponentType();
      }
      // 设置容量
      if(capacity < 0){
         capacity = length;
      }
      // 创建数组
      V[] alloc = (V[])Array.newInstance(clazz, capacity);
      // 如果存在数据，则复制数据
      if(null != memory){
         System.arraycopy(memory, offset, alloc, 0, length);
      }
      return alloc;
   }
   //   @SuppressWarnings("unchecked")
   //   public static <V> V[] compress(Class<V> clazz, V[] memory, int offset, int length){
   //      V[] alloc = (V[])Array.newInstance(clazz, length);
   //      int index = 0;
   //      int total = offset + length;
   //      for(int n = offset; n < total; n++){
   //         //if(!RObject.isEmpty(memory[n])){
   //         //   alloc[index] = memory[n];
   //         //}
   //      }
   //      V[] result = (V[])Array.newInstance(clazz, index);
   //      System.arraycopy(alloc, 0, result, 0, index);
   //      return result;
   //   }
   //
   //   public static boolean inRange(int index, int count){
   //      return (index >= 0 && index < count);
   //   }
   //
   //   public static boolean inRange(int index, int offset, int count){
   //      return (index >= offset && index < count);
   //   }
}
