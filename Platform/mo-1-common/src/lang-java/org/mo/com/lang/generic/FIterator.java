package org.mo.com.lang.generic;

import java.util.Iterator;

//============================================================
// <T>对象集合叠代器。</T>
//============================================================
public class FIterator<V>
      implements
         Iterator<V>
{
   // 内存
   protected V[] _memory;

   // 总数
   protected int _count;

   // 位置
   protected int _position;

   //============================================================
   // <T>构造对象集合叠代器。</T>
   //
   // @param memory 集合
   //============================================================
   public FIterator(V[] memory){
      _memory = memory;
      _position = 0;
      _count = memory.length;
   }

   //============================================================
   // <T>构造对象集合叠代器。</T>
   //
   // @param memory 集合
   // @param offset 位置
   // @param count 总数
   //============================================================
   public FIterator(V[] memory,
                    int offset,
                    int count){
      _memory = memory;
      _position = offset;
      _count = count;
   }

   //============================================================
   // <T>判断是否有下一个对象。</T>
   //
   // @return 是否有
   //============================================================
   @Override
   public boolean hasNext(){
      return (_position < _count);
   }

   //============================================================
   // <T>>获得下一个对象。</T>
   //
   // @return 下一个对象
   //============================================================
   @Override
   public V next(){
      return _memory[_position++];
   }

   //============================================================
   // <T>移除当前对象。</T>
   //============================================================
   @Override
   public void remove(){
      throw new NoSuchMethodError();
   }

}
