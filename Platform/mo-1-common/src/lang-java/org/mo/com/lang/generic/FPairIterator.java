package org.mo.com.lang.generic;

import java.util.Iterator;

//============================================================
// <T>名称值对象叠代器。</T>
//
// @type P 数据对类型
// @type N 名称类型
// @type V 内容类型
// @history 051013 创建
//============================================================
public class FPairIterator<P extends IPair<N, V>, N, V>
      implements
         Iterator<P>,
         IPairIterator
{
   // 数据位置
   private int _offset;

   // 数据个数
   private int _count;

   // 数据总数
   private int _total;

   // 当前位置
   private int _position;

   // 名称内容
   private N[] _names;

   // 值内容
   private V[] _values;

   // 名称值对象
   private FPair<N, V> _pair;

   //============================================================
   // <T>构造名称值对象叠代器。</T>
   //
   // @param size 数据大小
   // @param names 名称内容
   // @param values 值内容
   //============================================================
   public FPairIterator(FPair<N, V> pair,
                        N[] names,
                        V[] values,
                        int offset,
                        int count){
      _offset = offset;
      _count = count;
      _total = offset + count;
      _position = offset;
      _names = names;
      _values = values;
      _pair = pair;
      pair.link(this);
   }

   //============================================================
   // <T>获得总数。</T>
   //
   // @return 总数
   //============================================================
   public int count(){
      return _count;
   }

   //============================================================
   // <T>获得位置。</T>
   //
   // @return 位置
   //============================================================
   public int position(){
      return _position;
   }

   //============================================================
   // <T>判断是否开始位置。</T>
   //
   // @return 是否开始
   //============================================================
   public boolean isFirst(){
      return (_position == _offset);
   }

   //============================================================
   // <T>判断是否结束位置。</T>
   //
   // @return 是否结束
   //============================================================
   public boolean isLast(){
      return (_position == _total - 1);
   }

   //============================================================
   // <T>判断是否有下一个对象。</T>
   //
   // @return 是否有
   //============================================================
   public boolean hasNext(){
      return (_total > _position);
   }

   //============================================================
   // <T>>获得下一个对象。</T>
   //
   // @return 下一个对象
   //============================================================
   @SuppressWarnings("unchecked")
   public P next(){
      _pair.set(_names[_position], _values[_position]);
      _position++;
      return (P)_pair;
   }

   //============================================================
   // <T>移除当前对象。</T>
   //============================================================
   public void remove(){
      throw new NoSuchMethodError();
   }
}
