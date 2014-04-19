package org.mo.com.lang;

import org.mo.com.lang.type.RBaseObject;

//============================================================
// <T>对象工具类。</T>
//
// @history 071209 创建
//============================================================
public class RObject
      extends RBaseObject
{
   // 初始化容量
   public static final int CAPACITY = 8;

   // 最小排序分割
   public static final int MINIMUM_SORT_SPLITTER = 7;

   // 扩充比率
   public static final int MULTIPLIER = 2;

   //============================================================
   // <T>计算扩充容量。</T>
   // <P>
   //    当增长率<0时，返回目标容量
   //    当增长率=0时，返回至少大于最小容量的目标容量
   //    当增长率=1时，返回以最小值倍数增长的目标容量
   //    当增长率>1时，返回以指定增长率增长目标容量
   // </P>
   //
   // @param size 目标容量
   // @param capacity 最小容量
   // @param multiplier 增长率
   // @history 051012 创建
   //============================================================
   public static int ensureCapacity(int size,
                                    int capacity,
                                    int multiplier){
      if(multiplier < 0){
         // 指定大小
         return size;
      }else if(multiplier == 0){
         // 至少大于最小值
         return (size < capacity) ? capacity : size;
      }else if(multiplier == 1){
         // 以最小值倍数增长
         return (size < capacity) ? capacity : size + capacity;
      }
      // 以当前大小的增长率增长
      int allocSize = size * multiplier;
      return (allocSize < capacity) ? capacity : allocSize;
   }

   //============================================================
   // <T>判断指定对象是否为空，如果是则返回默认值。</T>
   //
   // @param value 指定对象集合
   // @param nvl 默认值
   // @return 非空对象
   //============================================================
   public static Object nvl(Object value,
                            Object nvl){
      if(null == value){
         return nvl;
      }
      return value;
   }

   //============================================================
   // <T>判断指定对象是否为空，如果是则返回默认值。</T>
   //
   // @param values 指定对象集合
   // @return 非空对象
   //============================================================
   @SafeVarargs
   public static <V> V nvl(V... values){
      if(null != values){
         int count = values.length;
         for(int n = 0; n < count; n++){
            if(null != values[n]){
               return values[n];
            }
         }
      }
      return null;
   }

   //============================================================
   // <T>判断指定类对象是否为空，如果是则返回默认值。</T>
   //
   // @param values 指定类对象集合
   // @return 非空类对象
   //============================================================
   public static Class<?> nvl(Class<?>... values){
      if(null != values){
         int count = values.length;
         for(int n = 0; n < count; n++){
            if(null != values[n]){
               return values[n];
            }
         }
      }
      return null;
   }

   //============================================================
   // <T>根据对象的名称进行排序。</T>
   //
   // @param source 来源集合
   // @param target 目标集合
   // @param begin 开始位置
   // @param end 结束位置
   // @param offset 位置
   // @param params 参数集合
   //============================================================
   protected static <V extends IComparable<V>> void innerSort(V[] source,
                                                              V[] target,
                                                              int begin,
                                                              int end,
                                                              int offset,
                                                              Object... params){
      // 对小于最小分隔数的数据排序
      V swap = null;
      int length = end - begin;
      if(length < MINIMUM_SORT_SPLITTER){
         for(int m = begin; m < end; m++){
            for(int n = m; n > begin && (target[n - 1]).compare(target[n], params) > 0; n--){
               swap = target[n];
               target[n] = target[n - 1];
               target[n - 1] = swap;
            }
         }
         return;
      }
      // 使用快速排序法
      int tgbegin = begin;
      int tgend = end;
      begin += offset;
      end += offset;
      int middle = (begin + end) >> 1;
      innerSort(target, source, begin, middle, -offset, params);
      innerSort(target, source, middle, end, -offset, params);
      if(((IComparable<V>)source[middle - 1]).compare(source[middle], params) <= 0){
         System.arraycopy(source, begin, target, tgbegin, length);
      }else{
         for(int i = tgbegin, j = begin, k = middle; i < tgend; i++){
            if(k >= end || j < middle && ((IComparable<V>)source[j]).compare(source[k], params) <= 0){
               target[i] = source[j++];
            }else{
               target[i] = source[k++];
            }
         }
      }
   }

   //============================================================
   // <T>根据对象的名称进行排序。</T>
   //
   // @param source 集合
   // @param asc 排序方式
   // @history 050217 MAOCY 创建
   //============================================================
   public static <V extends IComparable<V>> void sort(V[] source,
                                                      boolean asc){
      sort(source, 0, source.length, asc);
   }

   //============================================================
   // <T>根据对象的名称进行排序。</T>
   //
   // @param source 集合
   // @param asc 排序方式
   // @param params 参数集合
   // @history 050217 MAOCY 创建
   //============================================================
   public static <V extends IComparable<V>> void sort(V[] source,
                                                      boolean asc,
                                                      Object... params){
      sort(source, 0, source.length, asc, params);
   }

   //============================================================
   // <T>根据对象的名称进行排序。</T>
   //
   // @param source 集合
   // @param offset 位置
   // @param count 个数
   // @param asc 排序方式
   // @history 050217 MAOCY 创建
   //============================================================
   public static <V extends IComparable<V>> void sort(V[] source,
                                                      int offset,
                                                      int length,
                                                      boolean asc){
      sort(source, 0, source.length, asc);
   }

   //============================================================
   // <T>根据对象的名称进行排序。</T>
   // <P>
   //    排序方式为真,表示升序排序<br>
   //    排序方式为假,表示降序排序
   // </P>
   //
   // @param source 集合
   // @param offset 位置
   // @param count 个数
   // @param asc 排序方式
   // @param params 参数集合
   // @history 050217 MAOCY 创建
   //============================================================
   public static <V extends IComparable<V>> void sort(V[] source,
                                                      int offset,
                                                      int count,
                                                      boolean asc,
                                                      Object... params){
      if(offset < count){
         innerSort(source.clone(), source, offset, count, offset, params);
         if(!asc){
            V swap = null;
            for(int n = offset; n < count; n++){
               swap = source[n];
               source[n] = source[count - n - 1];
               source[count - n - 1] = swap;
            }
         }
      }
   }

   //============================================================
   // <T>尝试释放所有实例集合。</T>
   //
   // @param instances 实例集合
   // @return 例外
   //============================================================
   public static Exception tryRelease(Object[] instances){
      Exception exception = null;
      for(Object instance : instances){
         if(instance instanceof IRelease){
            try{
               ((IRelease)instance).release();
            }catch(Exception e){
               exception = e;
            }
         }
      }
      return exception;
   }
   //   public static boolean isEmpty(Object item){
   //      if(item == null){
   //         return true;
   //      }else if(item instanceof String){
   //         return ((String)item).length() == 0;
   //      }else if(item instanceof IEmpty){
   //         return ((IEmpty)item).isEmpty();
   //      }
   //      return false;
   //   }
   //
   //   // 根据对象的名称进行排序
   //   private static <V extends IStringNamed> void namingInnerSort(V[] source, V[] target, int begin, int end, int offset){
   //      // 对小于最小分隔数的数据排序
   //      V oSwap = null;
   //      int nLength = end - begin;
   //      if(nLength < MINIMUM_SORT_SPLITTER){
   //         for(int m = begin; m < end; m++){
   //            for(int n = m; n > begin && (target[n - 1]).name().compareTo(target[n].name()) > 0; n--){
   //               oSwap = target[n];
   //               target[n] = target[n - 1];
   //               target[n - 1] = oSwap;
   //            }
   //         }
   //         return;
   //      }
   //      // 使用快速排序法
   //      int nTargetBegin = begin;
   //      int nTargetEnd = end;
   //      begin += offset;
   //      end += offset;
   //      int nMiddle = (begin + end) >> 1;
   //      namingInnerSort(target, source, begin, nMiddle, -offset);
   //      namingInnerSort(target, source, nMiddle, end, -offset);
   //      if((source[nMiddle - 1]).name().compareTo(source[nMiddle].name()) <= 0){
   //         System.arraycopy(source, begin, target, nTargetBegin, nLength);
   //      }else{
   //         for(int i = nTargetBegin, j = begin, k = nMiddle; i < nTargetEnd; i++){
   //            if(k >= end || j < nMiddle && (source[j]).name().compareTo(source[k].name()) <= 0){
   //               target[i] = source[j++];
   //            }else{
   //               target[i] = source[k++];
   //            }
   //         }
   //      }
   //   }
   //
   //   /**
   //    * <p>根据对象的名称进行排序</p>
   //    * <p>排序方式为真,表示升序排序<br>
   //    * 排序方式为假,表示降序排序</p>
   //    * <p>create date:2005/02/17</p>
   //    * 
   //    * @param source 排序源
   //    * @param asc 排序方式
   //    */
   //   public static <V extends IStringNamed> void namingSort(V[] source, boolean asc){
   //      namingSort(source, 0, source.length, asc);
   //   }
   //
   //   /**
   //    * <p>根据对象的名称进行排序</p>
   //    * <p>排序方式为真,表示升序排序<br>
   //    * 排序方式为假,表示降序排序</p>
   //    * <p>create date:2005/02/17</p>
   //    * 
   //    * @param source 排序源
   //    * @param offset 开始位置
   //    * @param length 数据长度
   //    * @param asc 排序方式
   //    */
   //   public static <V extends IStringNamed> void namingSort(V[] source, int offset, int length, boolean asc){
   //      if(offset < length){
   //         namingInnerSort(source.clone(), source, offset, length, offset);
   //         if(!asc){
   //            V oSwap = null;
   //            for(int n = offset; n < length; n++){
   //               oSwap = source[n];
   //               source[n] = source[length - n - 1];
   //               source[length - n - 1] = oSwap;
   //            }
   //         }
   //      }
   //   }
   //   /**
   //    * <p>范围检查，
   //    * 如果索引位置不在指定位置和长度之间，则产生错误</p>
   //    * <p>create date:2005/10/12</p>
   //    * 
   //    * @param nOffset 指定位置
   //    * @param nLength 长度
   //    * @param nIndex 索引位置
   //    */
   //   public static void rangeCheck(int nOffset, int nLength, int nIndex){
   //      if(nIndex < nOffset || nIndex >= nLength){
   //         throw new IndexOutOfBoundsException("Index: " + nIndex + " / Size: " + nLength);
   //      }
   //   }
   //
   //   /**
   //    * <p>范围检查，
   //    * 如果开始位置和结束位置不在指定位置和长度之间，则产生错误</p>
   //    * <p>create date:2005/10/12</p>
   //    * 
   //    * @param nOffset 指定位置
   //    * @param nLength 长度
   //    * @param nStart 开始位置
   //    * @param nEnd 结束位置
   //    */
   //   public static void rangeCheck(int nOffset, int nLength, int nStart, int nEnd){
   //      if(nStart < nOffset || nStart >= nLength){
   //         throw new IndexOutOfBoundsException("Start: " + nStart + " / Size: " + nLength);
   //      }
   //      if(nEnd < nOffset || nEnd >= nLength){
   //         throw new IndexOutOfBoundsException("End: " + nEnd + " / Size: " + nLength);
   //      }
   //      if(nStart >= nEnd){
   //         throw new IllegalArgumentException("Start(" + nStart + ") is lager or equale end(" + nEnd + ")");
   //      }
   //   }
   //
   //   public static void release(Object[] source){
   //      release(source, 0, source.length);
   //   }
   //
   //   public static void release(Object[] source, int offset, int count){
   //      int n = -1;
   //      while(++n < count){
   //         Object object = source[offset + n];
   //         if(object != null && object instanceof IRelease){
   //            ((IRelease)object).release();
   //         }
   //         source[n] = null;
   //      }
   //   }
   //
   //   public static Object[] remove(Object[] source, int start, int end){
   //      int length = source.length;
   //      int move = start - end;
   //      Object[] old = new Object[move];
   //      int moved = length - end - 1;
   //      if(moved > 0){
   //         System.arraycopy(source, start + 1, old, 0, move);
   //         System.arraycopy(source, end + 1, source, start + 1, moved);
   //      }
   //      int p = start + moved - 1;
   //      while(++p < length){
   //         source[p] = 0;
   //      }
   //      return old;
   //   }
   //
   //   public static int remove(Object[] source, int offset, int length, Object remove){
   //      int index = offset;
   //      for(int n = offset; n < length; n++){
   //         if(source[n] != remove){
   //            if(index != n){
   //               source[index] = source[n];
   //            }
   //            index++;
   //         }
   //      }
   //      return index;
   //   }
   //
   //   public static int remove(Object[] source, int offset, int length, Object[] target, int targetOffset, int targetLength){
   //      if(source != null && length > 0 && target != null && targetLength > 0){
   //         int p = -1;
   //         int index = 0;
   //         int loop = length - targetLength;
   //         Object first = target[targetOffset];
   //         while(++p <= loop){
   //            if(source[p] == first){
   //               boolean same = true;
   //               int i = targetOffset;
   //               while(++i < targetLength){
   //                  if(source[p + i] != target[i]){
   //                     same = false;
   //                     break;
   //                  }
   //               }
   //               if(same){
   //                  p += targetLength - 1;
   //               }else{
   //                  source[index++] = source[p];
   //               }
   //            }else{
   //               source[index++] = source[p];
   //            }
   //         }
   //         p--;
   //         while(++p < length){
   //            source[index++] = source[p];
   //         }
   //         return index;
   //      }
   //      return length;
   //   }
   //
   //   public static int removeIndex(Object[] source, int offset, int length, int index){
   //      if(index >= offset && index < length){
   //         System.arraycopy(source, index + 1, source, index, length - index);
   //         return length - 1;
   //      }
   //      return length;
   //   }
   //
   //   /**
   //    * <p>复制一个数组成为一个新数组</p>
   //    * <p>create date:2005/10/13</p>
   //    *
   //    * @param arValue 内存数组
   //    * @param oClass 对象类型
   //    * @return 复制后的新数组
   //    */
   //   @SuppressWarnings("unchecked")
   //   public static <V>V[] copy(V[] arValue,
   //                             Class oClass)
   //         throws FException{
   //      return copy(arValue, oClass, 0, arValue.length);
   //   }
   //
   //   /**
   //    * <p>复制一个数组成为一个新数组</p>
   //    * <p>create date:2005/10/13</p>
   //    *
   //    * @param arValue 内存数组
   //    * @param oClass 对象类型
   //    * @param nOffset 开始位置
   //    * @param nLength 数组长度
   //    * @return 复制后的新数组
   //    */
   //   @SuppressWarnings("unchecked")
   //   public static <V>V[] copy(V[] arValue,
   //                             Class oClass,
   //                             int nOffset,
   //                             int nLength)
   //         throws FException{
   //      V[] arMemory = FMemoryUtil.alloc(oClass, nLength);
   //      for(int n = nOffset; n < nLength; n++){
   //         if(arValue[n] instanceof ICloneable){
   //            arMemory[n] = (V)((ICloneable)arValue[n]).copy();
   //         }else{
   //            arMemory[n] = arValue[n];
   //         }
   //      }
   //      return arMemory;
   //   }
   //
   //
   //   /**
   //    * <p>将当前对象内存数组中移除指定位置的对象</p>
   //    * <p>create date:2005/02/15</p>
   //    *
   //    * @param arValue 对象内存数组
   //    * @param nIndex 指定位置
   //    * @return 移除后长度
   //    */
   //   public static <V>int remove(V[] arValue,
   //                               int nIndex){
   //      return remove(arValue, 0, arValue.length, nIndex);
   //   }
   //
   //   /**
   //    * <p>将当前对象内存数组中移除指定位置的对象</p>
   //    * <p>create date:2005/02/15</p>
   //    *
   //    * @param arValue 内存数组
   //    * @param nOffset 对象位置
   //    * @param nLength 对象长度
   //    * @param nIndex 指定位置
   //    * @return 移除后长度
   //    */
   //   public static <V>int remove(V[] arValue,
   //                               int nOffset,
   //                               int nLength,
   //                               int nIndex){
   //      int nMoved = nLength - nIndex - 1;
   //      if(nMoved > 0){
   //         System.arraycopy(arValue, nIndex + 1, arValue, nIndex, nMoved);
   //      }
   //      return nLength - 1;
   //   }
   //
   //   /**
   //    * <p>将当前对象内存数组中移除指定的对象</p>
   //    * <p>create date:2005/02/15</p>
   //    *
   //    * @param arValue 内存数组
   //    * @param nOffset 对象位置
   //    * @param nLength 对象长度
   //    * @param oRemove 移除对象
   //    * @return 移除后长度
   //    */
   //   public static <V>int remove(V[] arValue,
   //                               int nOffset,
   //                               int nLength,
   //                               V oRemove){
   //      int nIndex = nOffset;
   //      for(int n = nOffset; n < nLength; n++){
   //         if(arValue[n] != oRemove){
   //            if(nIndex != n){
   //               arValue[nIndex] = arValue[n];
   //            }
   //            nIndex++;
   //         }
   //      }
   //      return nIndex;
   //   }
   //
   //   /**
   //    * <p>将当前对象内存数组中移除指定的对象</p>
   //    * <p>create date:2005/02/15</p>
   //    *
   //    * @param arValue 内存数组
   //    * @param oRemove 移除对象
   //    * @return 移除后长度
   //    */
   //   public static <V>int remove(V[] arValue,
   //                               V oRemove){
   //      return remove(arValue, 0, arValue.length, oRemove);
   //   }
   //
   //   /**
   //    * <p>将当前对象内存中移除指定的移除内存内所有对象</p>
   //    * <p>create date:2005/02/15</p>
   //    *
   //    * @param arValue 对象内存数组
   //    * @param nOffset 对象内存位置
   //    * @param nLength 对象内存长度
   //    * @param arRemove 移除内存数组
   //    * @param nRemoveOffset 移除内存位置
   //    * @param nRemoveLength 移除内存长度
   //    * @return 移除后长度
   //    */
   //   public static <V>int removeAll(V[] arValue,
   //                                  int nOffset,
   //                                  int nLength,
   //                                  V[] arRemove,
   //                                  int nRemoveOffset,
   //                                  int nRemoveLength){
   //      boolean bRemove = false;
   //      int nIndex = nOffset;
   //      for(int m = nOffset; m < nLength; m++){
   //         bRemove = false;
   //         for(int n = nRemoveOffset; n < nRemoveLength; n++){
   //            if(arValue[m] == arRemove[n]){
   //               bRemove = true;
   //               break;
   //            }
   //         }
   //         if(!bRemove){
   //            if(nIndex != m){
   //               arValue[nIndex] = arValue[m];
   //            }
   //            nIndex++;
   //         }
   //      }
   //      return nIndex;
   //   }
   //
   //   /**
   //    * <p>将当前对象内存中移除指定的移除内存内所有对象</p>
   //    * <p>create date:2005/02/15</p>
   //    *
   //    * @param arValue 对象内存数组
   //    * @param arRemove 移除内存数组
   //    * @return 移除后长度
   //    */
   //   public static <V>int removeAll(V[] arValue,
   //                                  V[] arRemove){
   //      return removeAll(arValue, 0, arValue.length, arRemove, 0, arRemove.length);
   //   }
}
