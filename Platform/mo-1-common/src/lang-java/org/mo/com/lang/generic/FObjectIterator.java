package org.mo.com.lang.generic;

import java.util.Iterator;

//============================================================
// <T>对象集合叠代器。</T>
//
// @type T 对象类型
//============================================================
public class FObjectIterator<T>
      implements
         Iterator<T>
{
   // 对象集合
   protected MObjects<T> _objects;

   // 总数
   protected int _count;

   // 内存
   protected T[] _memory;

   // 位置
   protected int _position;

   //============================================================
   // <T>构造对象集合叠代器。</T>
   //
   // @param objects 对象集合
   // @param offset 对象位置
   // @param count 对象总数
   //============================================================
   public FObjectIterator(MObjects<T> objects){
      _objects = objects;
      _count = objects.count();
      _memory = objects.memory();
      _position = 0;
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
   public T next(){
      return _memory[_position++];
   }

   //============================================================
   // <T>移除当前对象。</T>
   //============================================================
   @Override
   public void remove(){
      _objects.erase(_position);
      _count = _objects.count();
   }
}
