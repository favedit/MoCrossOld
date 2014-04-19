package org.mo.com.lang.generic;

import java.util.Iterator;
import org.mo.com.lang.INamePair;
import org.mo.com.lang.face.AObjects;

//============================================================
// <T>值对集合。</T>
//
// @type P 迭代器类型
// @type V 内容类型
//============================================================

@AObjects
public abstract class MNamePairs<P extends INamePair<V>, V>
      extends MPairs<P, String, V>
{
   // 比较大小写敏感
   protected boolean _careCase;

   //============================================================
   // <T>构造值对集合。</T>
   //
   // @param clazz 类对象
   //============================================================
   public MNamePairs(Class<V> clazz){
      super(String.class, clazz);
   }

   //============================================================
   // <T>构造值对集合。</T>
   //
   // @param clazz 类对象
   // @param capacity 容量
   //============================================================
   public MNamePairs(Class<V> clazz,
                     int capacity){
      super(String.class, clazz, capacity);
   }

   //============================================================
   // <T>获得比较大小写敏感。</T>
   //
   // @return 比较大小写敏感
   //============================================================
   public boolean careCase(){
      return _careCase;
   }

   //============================================================
   // <T>设置比较大小写敏感。</T>
   //
   // @param careCase 比较大小写敏感
   //============================================================
   public void setEqualsCase(boolean careCase){
      _careCase = careCase;
   }

   //============================================================
   // <T>判断对象是否相等。</T>
   //
   // @param source 来源
   // @param target 目标
   // @return 是否相等
   //============================================================
   @Override
   protected boolean innerEquals(String source,
                                 String target){
      return _careCase ? source.equals(target) : source.equalsIgnoreCase(target);
   }

   //============================================================
   // <T>获得迭代器。</T>
   //
   // @return 迭代器
   //============================================================
   @Override
   public Iterator<P> iterator(){
      return new FPairIterator<P, String, V>(new FNamePair<V>(), _names, _values, 0, _count);
   }
}
