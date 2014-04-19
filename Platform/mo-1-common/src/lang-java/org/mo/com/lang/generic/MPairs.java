package org.mo.com.lang.generic;

import java.util.Iterator;
import org.mo.com.lang.FFatalError;
import org.mo.com.lang.IDump;
import org.mo.com.lang.IMap;
import org.mo.com.lang.IPairs;
import org.mo.com.lang.RArray;
import org.mo.com.lang.RDump;
import org.mo.com.lang.RObject;
import org.mo.com.lang.face.AObjects;

//============================================================
// <T>值对集合。</T>
//============================================================

@AObjects
public abstract class MPairs<P extends IPair<N, V>, N, V>
      implements
         IPairs<N, V>,
         IDump,
         Iterable<P>
{
   // 总数
   protected int _count;

   // 名称类对象
   protected Class<N> _nameClass;

   // 名称集合
   protected N[] _names;

   // 内容类对象
   protected Class<V> _valueClass;

   // 内容集合
   protected V[] _values;

   //============================================================
   // <T>构造值对集合。</T>
   //
   // @param nameClass 名称类名
   // @param valueClass 内容类名
   //============================================================
   public MPairs(Class<N> nameClass,
                 Class<V> valueClass){
      _nameClass = nameClass;
      _valueClass = valueClass;
      ensureSize(RObject.CAPACITY);
   }

   //============================================================
   // <T>构造值对集合。</T>
   //
   // @param nameClass 名称类名
   // @param valueClass 内容类名
   // @param capacity 容量
   //============================================================
   public MPairs(Class<N> nameClass,
                 Class<V> valueClass,
                 int capacity){
      _nameClass = nameClass;
      _valueClass = valueClass;
      ensureSize(Math.max(capacity, RObject.CAPACITY));
   }

   //============================================================
   // <T>判断对象是否相等。</T>
   //
   // @param source 来源
   // @param target 目标
   // @return 是否相等
   //============================================================
   protected boolean innerEquals(N source,
                                 N target){
      return source.equals(target);
   }

   //============================================================
   // <T>判断是否为空。</T>
   //
   // @return 是否为空
   //============================================================
   @Override
   public boolean isEmpty(){
      return (0 == _count);
   }

   //============================================================
   // <T>获得对象总数。</T>
   //
   // @return 对象总数
   //============================================================
   @Override
   public int count(){
      return _count;
   }

   //============================================================
   // <T>获得名称内存。</T>
   //
   // @return 名称内存
   //============================================================
   public N[] memoryNames(){
      return _names;
   }

   //============================================================
   // <T>获得内容内存。</T>
   //
   // @return 内容内存
   //============================================================
   public V[] memoryValues(){
      return _values;
   }

   //============================================================
   // <T>是否存在指定名称。</T>
   //
   // @param name 名称
   // @return 是否存在
   //============================================================
   @Override
   public boolean contains(N name){
      return (-1 != indexOf(name, 0));
   }

   //============================================================
   // <T>是否存在指定内容。</T>
   //
   // @param value 内容
   // @return 是否存在
   //============================================================
   public boolean containsValue(V value){
      return (-1 != find(value, 0));
   }

   //============================================================
   // <T>查找指定数据名称在集合中首次出现的位置。</T>
   //
   // @param name 数据名称
   //============================================================
   public int indexOf(N name){
      return indexOf(name, 0);
   }

   //============================================================
   // <T>查找指定数据名称在集合中首次出现的位置。</T>
   //
   // @param name 数据名称
   // @param offset 数据位置
   //============================================================
   public int indexOf(N name,
                      int offset){
      if(null != name){
         int n = offset - 1;
         while(++n < _count){
            if(innerEquals(name, _names[n])){
               return n;
            }
         }
      }
      return -1;
   }

   //============================================================
   // <T>查找指定数据内容在集合中首次出现的位置。</T>
   //
   // @param value 数据内容
   //============================================================
   public int find(V value){
      return find(value, 0);
   }

   //============================================================
   // <T>查找指定数据内容在集合中首次出现的位置。</T>
   //
   // @param value 数据内容
   // @param offset 数据位置
   //============================================================
   public int find(V value,
                   int offset){
      if(value != null){
         int n = offset - 1;
         while(++n < _count){
            if(value.equals(_values[n])){
               return n;
            }
         }
      }
      return -1;
   }

   //============================================================
   // <T>获得迭代器。</T>
   //
   // @return 迭代器
   //============================================================
   @Override
   public Iterator<P> iterator(){
      return new FPairIterator<P, N, V>(new FPair<N, V>(), _names, _values, 0, _count);
   }

   //============================================================
   // <T>确保基础类型集合有指定大小的容量。</T>
   //
   // @param count 总数
   //============================================================
   public void ensureSize(int count){
      if(null == _names){
         int total = Math.max(RObject.CAPACITY, count);
         _names = RArray.newInstance(_nameClass, total);
         _values = RArray.newInstance(_valueClass, total);
      }else if(count > _names.length){
         int total = Math.max(RObject.CAPACITY, count + count);
         _names = RArray.copy(_names, 0, _count, _nameClass, total);
         _values = RArray.copy(_values, 0, _count, _valueClass, total);
      }
   }

   //============================================================
   // <T>接收数据集合到自己内部。</T>
   //
   // @param map 数据集合
   //============================================================
   @Override
   public void assign(IMap<N, V> map){
      clear();
      append(map, 0, map.count());
   }

   //============================================================
   // <T>接收数据集合到自己内部。</T>
   //
   // @param map 数据集合
   // @param offset 位置
   // @param count 总数
   //============================================================
   public void assign(IMap<N, V> map,
                      int offset,
                      int count){
      clear();
      append(map, offset, count);
   }

   //============================================================
   // <T>追加数据集合到自己尾部。</T>
   //
   // @param map 集合
   //============================================================
   @Override
   public void append(IMap<N, V> map){
      append(map, 0, map.count());
   }

   //============================================================
   // <T>追加数据集合到自己尾部。</T>
   //
   // @param map 集合
   // @param offset 位置
   // @param count 总数
   //============================================================
   public void append(IMap<N, V> map,
                      int offset,
                      int count){
      if(null != map){
         int n = offset - 1;
         int loop = offset + count;
         while(++n < loop){
            set(map.name(n), map.value(n));
         }
      }
   }

   //============================================================
   // <T>追加数据集合到自己尾部。</T>
   //
   // @param names 名称集合
   // @param values 内容集合
   // @param offset 位置
   // @param count 总数
   //============================================================
   public void append(N[] names,
                      V[] values,
                      int offset,
                      int count){
      if((null != names) && (null != values)){
         int n = offset - 1;
         int loop = offset + count;
         while(++n < loop){
            set(names[n], values[n]);
         }
      }
   }

   //============================================================
   // <T>获得指定索引位置的数据名称。</T>
   //
   // @param index 索引位置
   // @return 数据名称
   //============================================================
   @Override
   public N name(int index){
      return ((index >= 0) && (index < _count)) ? _names[index] : null;
   }

   //============================================================
   // <T>设置指定索引位置的数据名称。</T>
   //
   // @param index 索引位置
   // @param value 数据名称
   //============================================================
   public void setName(int index,
                       N name){
      if(index >= 0 && index < _count){
         _names[index] = name;
      }
   }

   //============================================================
   // <T>获得指定索引位置的数据内容。</T>
   //
   // @param index 索引位置
   // @return 数据内容
   //============================================================
   @Override
   public V value(int index){
      return ((index >= 0) && (index < _count)) ? _values[index] : null;
   }

   //============================================================
   // <T>设置指定索引位置的数据内容。</T>
   //
   // @param index 索引位置
   // @param value 数据内容
   //============================================================
   public void setValue(int index,
                        V value){
      if((index >= 0) && (index < _count)){
         _values[index] = value;
      }
   }

   //============================================================
   // <T>根据名称获得内容。</T>
   //
   // @param name 名称
   // @return 内容
   //============================================================
   @Override
   public V get(N name){
      int index = indexOf(name);
      if(-1 != index){
         return _values[index];
      }
      throw new FFatalError("Can't find name. (name={1})", name);
   }

   //============================================================
   // <T>根据名称获得内容。</T>
   //
   // @param name 名称
   // @param defaultValue 默认内容
   // @return 内容
   //============================================================
   @Override
   public V get(N name,
                V defaultValue){
      int index = indexOf(name);
      if(-1 != index){
         return _values[index];
      }
      return defaultValue;
   }

   //============================================================
   // <T>增加名称和内容。</T>
   //
   // @param name 名称
   // @param value 内容
   //============================================================
   public void add(N name,
                   V value){
      ensureSize(_count + 1);
      _names[_count] = name;
      _values[_count++] = value;
   }

   //============================================================
   // <T>设置名称和内容。</T>
   //
   // @param name 名称
   // @param value 内容
   //============================================================
   @Override
   public void set(N name,
                   V value){
      int index = indexOf(name);
      if(-1 == index){
         add(name, value);
      }else{
         _values[index] = value;
      }
   }

   //============================================================
   // <T>获得名称集合。</T>
   //
   // @return 名称集合
   //============================================================
   public N[] names(){
      return RArray.copy(_names, 0, _count, _nameClass, _count);
   }

   //============================================================
   // <T>获得内容集合。</T>
   //
   // @return 内容集合
   //============================================================
   public V[] values(){
      return RArray.copy(_values, 0, _count, _valueClass, _count);
   }

   //============================================================
   // <T>擦除指定索引位置的数据内容。</T>
   //
   // @param index 索引位置
   // @return 数据内容
   //============================================================
   public V erase(int index){
      if((index >= 0) && (index < _count)){
         V old = _values[index];
         int move = _count - index - 1;
         if(move > 0){
            System.arraycopy(_names, index + 1, _names, index, move);
            System.arraycopy(_values, index + 1, _values, index, move);
         }
         _names[_count - 1] = null;
         _values[--_count] = null;
         return old;
      }
      return null;
   }

   //============================================================
   // <T>移除指定名称的数据内容。</T>
   //
   // @param name 数据名称
   // @return 数据内容
   //============================================================
   @Override
   public V remove(N name){
      return erase(indexOf(name));
   }

   //============================================================
   // <T>获得对象集合。</T>
   //
   // @return 对象集合
   //============================================================
   public V[] toObjects(){
      return RArray.copy(_values, 0, _count, _valueClass, _count);
   }

   //============================================================
   // <T>清除全部数据内容。</T>
   //============================================================
   @Override
   public void clear(){
      _count = 0;
   }

   //============================================================
   // <T>释放对象。</T>
   //============================================================
   @Override
   public void finalize() throws Throwable{
      _nameClass = null;
      if(null != _names){
         RObject.clear(_names, 0, _count);
         _names = null;
      }
      _valueClass = null;
      if(null != _values){
         RObject.clear(_values, 0, _count);
         _values = null;
      }
      super.finalize();
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
      RDump.dump(info, this);
      info.append(" [ Count:");
      info.appendInt(_count);
      info.append(" ]");
      if(_count > 0){
         info.append(" {\n");
         for(int n = 0; n < _count; n++){
            RDump.dump(info, name(n).toString(), value(n));
         }
         info.append("}");
      }
      return info;
   }
   //   public void compress(){
   //      int n = -1;
   //      int index = 0;
   //      N[] names = RArray.newInstance(_nameClass, _count);
   //      V[] values = RArray.newInstance(_valueClass, _count);
   //      while(++n < _count && !RObject.isEmpty(_values[n])){
   //         names[index] = _names[n];
   //         values[index++] = _values[n];
   //      }
   //      _count = 0;
   //      _names = RArray.copy(names, 0, index);
   //      _values = RArray.copy(values, 0, index);
   //   }
   //
   //   @Override
   //   public T copy(){
   //      T copy = RClass.newInstance(getClass());
   //      copy.assign(this);
   //      return copy;
   //   }
}
