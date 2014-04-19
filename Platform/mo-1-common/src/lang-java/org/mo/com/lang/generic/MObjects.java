package org.mo.com.lang.generic;

import java.lang.reflect.Array;
import java.util.Iterator;
import org.mo.com.lang.IDisposable;
import org.mo.com.lang.IDump;
import org.mo.com.lang.IObjects;
import org.mo.com.lang.RObject;
import org.mo.com.lang.face.AObjects;

//============================================================
// <T>基础类型集合。</T>
//============================================================
@AObjects
public abstract class MObjects<T>
      implements
         IDisposable,
         IObjects<T>,
         IDump,
         Iterable<T>
{
   // 容量
   public static final int CAPACITY = 8;

   // 类对象
   protected Class<T> _clazz;

   // 长度
   protected int _count = 0;

   // 内存
   protected T[] _items;

   //============================================================
   // <T>构造基础类型集合。</T>
   //============================================================
   protected MObjects(){
   }

   //============================================================
   // <T>构造基础类型集合。</T>
   //============================================================
   public MObjects(Class<T> clazz){
      _clazz = clazz;
      ensureSize(CAPACITY);
   }

   //============================================================
   // <T>构造基础类型集合。</T>
   //
   // @param capacity 初始容量
   //============================================================
   public MObjects(Class<T> clazz,
                   int capacity){
      _clazz = clazz;
      ensureSize(capacity);
   }

   //============================================================
   // <T>判断来源和目标是否相等。</T>
   //
   // @param source 来源
   // @param target 目标
   // @return 是否相等
   //============================================================
   protected boolean innerEquals(T source,
                                 T target){
      return source == target;
   }

   //============================================================
   // <T>判断是否为空。</T>
   //
   // @return 是否为空
   //============================================================
   public boolean isEmpty(){
      return (0 == _count);
   }

   //============================================================
   // <T>获得对象总数。</T>
   //
   // @return 对象总数
   //============================================================
   public int count(){
      return _count;
   }

   //============================================================
   // <T>设置数据长度。</T>
   //
   // @param length 长度
   //============================================================
   public void setLength(int length){
      ensureSize(length);
      _count = length;
   }

   //============================================================
   // <T>获得内存。</T>
   //
   // @return 内存
   //============================================================
   public T[] memory(){
      return _items;
   }

   //============================================================
   // <T>判断是否含有指定数据内容。</T>
   //
   // @param value 数据内容
   // @return 是否含有
   //============================================================
   public boolean contains(T value){
      return (-1 != indexOf(value));
   }

   //============================================================
   // <T>查找指定数据内容在集合中首次出现的位置。</T>
   //
   // @param value 数据内容
   // @return 是否含有
   //============================================================
   public int indexOf(T value){
      return indexOf(value, 0);
   }

   //============================================================
   // <T>查找指定数据内容在集合中首次出现的位置。</T>
   //
   // @param value 数据内容
   // @param offset 数据位置
   //============================================================
   public int indexOf(T value,
                      int offset){
      int p = offset - 1;
      while(++p < _count){
         if(innerEquals(_items[p], value)){
            return p;
         }
      }
      return -1;
   }

   //============================================================
   // <T>查找指定数据内容在集合中最后出现的位置。</T>
   //
   // @param value 数据内容
   // @return 是否含有
   //============================================================
   public int lastIndexOf(T value){
      return lastIndexOf(value, _count);
   }

   //============================================================
   // <T>查找指定数据内容在集合中最后出现的位置。</T>
   //
   // @param value 数据内容
   // @param offset 数据位置
   //============================================================
   public int lastIndexOf(T value,
                          int last){
      int p = last;
      while(--p >= 0){
         if(innerEquals(_items[p], value)){
            return p;
         }
      }
      return -1;
   }

   //============================================================
   // <T>获得首对象。</T>
   //
   // @return 对象
   //============================================================
   public T first(){
      return (_count > 0) ? _items[_count - 1] : null;
   }

   //============================================================
   // <T>获得尾对象。</T>
   //
   // @return 对象
   //============================================================
   public T last(){
      return (_count > 0) ? _items[_count - 1] : null;
   }

   //============================================================
   // <T>获得迭代器。</T>
   //
   // @return 迭代器
   //============================================================
   @Override
   public Iterator<T> iterator(){
      return new FObjectIterator<T>(this);
   }

   //============================================================
   // <T>确保基础类型集合有指定大小的容量。</T>
   //
   // @param length 长度
   //============================================================
   @SuppressWarnings("unchecked")
   public void ensureSize(int length){
      if(null == _items){
         int total = Math.max(length, CAPACITY);
         _items = (T[])Array.newInstance(_clazz, total);
      }else if(length > _items.length){
         int total = Math.max(length + (length >> 1), CAPACITY);
         T[] alloc = (T[])Array.newInstance(_clazz, total);
         if(_count > 0){
            System.arraycopy(_items, 0, alloc, 0, _count);
         }
         _items = alloc;
      }
   }

   //============================================================
   // <T>接收基础类型集合全部内容。</T>
   //
   // @param values 基础类型集合
   //============================================================
   public void assign(MObjects<T> values){
      assign(values._items, 0, values._count);
   }

   //============================================================
   // <T>接收基础类型集合全部内容。</T>
   //
   // @param values 数据内容
   // @param offset 开始位置 
   // @param length 数据长度
   //============================================================
   public void assign(T[] values,
                      int offset,
                      int length){
      if((null != values) && (length > 0)){
         ensureSize(length);
         System.arraycopy(values, offset, _items, 0, length);
         _count = length;
      }
   }

   //============================================================
   // <T>追加基础类型数据集合。</T>
   //
   // @param value... 数据
   //============================================================
   public void append(T[] values){
      append(values, 0, values.length);
   }

   //============================================================
   // <T>追加指定基础类型集合全部内容。</T>
   //
   // @param values 数据内容
   // @param offset 开始位置 
   // @param length 数据长度
   //============================================================
   public void append(T[] values,
                      int offset,
                      int count){
      if((null != values) && (count > 0)){
         ensureSize(_count + count);
         System.arraycopy(values, offset, _items, _count, count);
         _count += count;
      }
   }

   //============================================================
   // <T>追加指定基础类型集合全部内容。</T>
   //
   // @param values 基础类型集合
   //============================================================
   public void append(MObjects<T> values){
      append(values._items, 0, values._count);
   }

   //============================================================
   // <T>获得索引位置的数据内容。</T>
   //
   // @param index 索引位置
   // @return 数据内容
   //============================================================
   public T get(int index){
      return ((index >= 0) && (index < _count)) ? _items[index] : null;
   }

   //============================================================
   // <T>设置索引位置的数据内容。</T>
   //
   // @param index 索引位置
   // @return value 数据内容
   //============================================================
   public void set(int index,
                   T value){
      if(index >= 0 && index < _count){
         _items[index] = value;
      }
   }

   //============================================================
   // <T>确认大小后，设置索引位置的数据内容。</T>
   //
   // @param index 索引位置
   // @return value 数据内容
   //============================================================
   public void ensureSet(int index,
                         T value){
      // 确认容量
      ensureSize(index + 1);
      // 设置大小
      if(index + 1 > _count){
         _count = index + 1;
      }
      _items[index] = value;
   }

   //============================================================
   // <T>追加一个数据到尾部。</T>
   //
   // @param value 数据
   //============================================================
   public void push(T value){
      ensureSize(_count + 1);
      _items[_count++] = value;
   }

   //============================================================
   // <T>弹出尾部数据内容。</T>
   //
   // @return 数据内容
   //============================================================
   public T pop(){
      T value = _items[_count - 1];
      _count--;
      return value;
   }

   //============================================================
   // <T>插入指定基础类型数据到指定位置。</T>
   //
   // @param value 数据内容
   //============================================================
   public void insert(T value){
      insert(value, 0);
   }

   //============================================================
   // <T>插入指定基础类型数据到指定位置。</T>
   //
   // @param value 数据内容
   // @param index 开始位置 
   //============================================================
   public void insert(T value,
                      int index){
      if(index >= 0 && index < _count){
         ensureSize(_count + 1);
         System.arraycopy(_items, index, _items, index + 1, _count - index - 1);
         _count++;
         _items[index] = value;
      }
   }

   //============================================================
   // <T>移除指定索引位置的数据。</T>
   //
   // @param index 索引位置 
   // @return 对象
   //============================================================
   public T erase(int index){
      T value = _items[index];
      int move = _count - index - 1;
      if(move > 0){
         System.arraycopy(_items, index + 1, _items, index, move);
      }
      _count--;
      return value;
   }

   //============================================================
   // <T>移除指定对象的数据。</T>
   //
   // @param value 对象 
   //============================================================
   public void remove(T value){
      int n = -1;
      int position = 0;
      while(++n < _count){
         T find = _items[n];
         if(find != value){
            _items[position++] = find;
         }
      }
      _count = position;
   }

   //============================================================
   // <T>交换两个指定索引位置的数据内容。</T>
   //
   // @param from 开始索引 
   // @param to 结束索引 
   //============================================================
   public void swap(int from,
                    int to){
      if((from != to) && (from >= 0) && (from < _count) && (to >= 0) && (to < _count)){
         T swap = _items[from];
         _items[from] = _items[to];
         _items[to] = swap;
      }
   }

   //============================================================
   // <T>复制数据内容到指定内存位置。</T>
   //
   // @param items 对象数组
   // @param offset 索引位置
   // @param length 数据长度
   //============================================================
   public void copy(T[] items,
                    int offset,
                    int length){
      System.arraycopy(_items, 0, items, offset, length);
   }

   //============================================================
   // <T>获得对象数组。</T>
   //
   // @return 对象数组
   //============================================================
   @Override
   @SuppressWarnings("unchecked")
   public T[] toObjects(){
      T[] alloc = (T[])Array.newInstance(_clazz, _count);
      System.arraycopy(_items, 0, alloc, 0, _count);
      return alloc;
   }

   //============================================================
   // <T>清除全部数据内容。</T>
   //============================================================
   public void clear(){
      _count = 0;
   }

   //============================================================
   // <T>释放数据内容。</T>
   //============================================================
   @Override
   public void dispose(){
      _clazz = null;
      RObject.clear(_items);
      _items = null;
   }

   //============================================================
   // <T>获得运行信息。</T>
   //
   // @return 运行信息
   //============================================================
   @Override
   public String dump(){
      TDumpInfo info = new TDumpInfo(this);
      return dump(info).toString();
   }

   //============================================================
   // <T>生成运行信息。</T>
   //
   // @param info 运行信息
   // @return 运行信息
   //============================================================
   @Override
   public TDumpInfo dump(TDumpInfo info){
      return info;
   }
}
