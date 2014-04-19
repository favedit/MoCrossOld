package org.mo.com.lang.generic;

import java.lang.reflect.Array;
import java.util.Iterator;
import org.mo.com.lang.FObjectId;
import org.mo.com.lang.IDump;
import org.mo.com.lang.IMap;
import org.mo.com.lang.RArray;
import org.mo.com.lang.RDump;
import org.mo.com.lang.RHash;
import org.mo.com.lang.RObject;

//============================================================
// <T>哈希集合。</T>
//============================================================
public class MMap<P extends IPair<N, V>, N, V>
      extends FObjectId
      implements
         IMap<N, V>,
         IDump,
         Iterable<P>
{
   // 默认容量
   public static final int CAPACITY = 8;

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

   // 哈希表
   protected transient SHashIntEntry[] _table;

   //============================================================
   // <T>构造哈希集合。</T>
   //============================================================
   public MMap(){
   }

   //============================================================
   // <T>构造哈希集合。</T>
   //
   // @param nameClass 名称类对象
   // @param valueClass 内容类对象
   //============================================================
   public MMap(Class<N> nameClass,
               Class<V> valueClass){
      innerConstruct(nameClass, valueClass, CAPACITY);
   }

   //============================================================
   // <T>构造哈希集合。</T>
   //
   // @param nameClass 名称类对象
   // @param valueClass 内容类对象
   // @param capacity 容量
   //============================================================
   public MMap(Class<N> nameClass,
               Class<V> valueClass,
               int capacity){
      innerConstruct(nameClass, valueClass, capacity);
   }

   //============================================================
   // <T>构造数据内容。</T>
   //
   // @param nameClass 名称类对象
   // @param valueClass 内容类对象
   // @param capacity 容量
   //============================================================
   protected void innerConstruct(Class<N> nameClass,
                                 Class<V> valueClass,
                                 int capacity){
      if(null == nameClass){
         throw new NullPointerException("nameClass");
      }
      if(null == valueClass){
         throw new NullPointerException("valueClass");
      }
      _nameClass = nameClass;
      _valueClass = valueClass;
      ensureSize(Math.max(capacity, CAPACITY));
   }

   //============================================================
   // <T>根据名称获得变换后名称。</T>
   //
   // @param name 名称
   // @return 名称
   //============================================================
   protected N innerMakeName(N name){
      return name;
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
   // <T>获得总数。</T>
   //
   // @return 总数
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
   // <T>获得迭代器。</T>
   //
   // @return 迭代器
   //============================================================
   @Override
   public Iterator<P> iterator(){
      return new FPairIterator<P, N, V>(new FPair<N, V>(), _names, _values, 0, _count);
   }

   //============================================================
   // <T>是否存在指定名称。</T>
   //
   // @param name 名称
   // @return 是否存在
   //============================================================
   @Override
   public boolean contains(N name){
      return (-1 != indexOf(name));
   }

   //============================================================
   // <T>是否存在指定内容。</T>
   //
   // @param value 内容
   // @return 是否存在
   //============================================================
   public boolean containsValue(V value){
      return (-1 != indexOfValue(value, 0));
   }

   //============================================================
   // <T>查找指定数据名称在集合中首次出现的位置。</T>
   //
   // @param name 数据名称
   // @return 索引位置
   //============================================================
   public int indexOf(N name){
      if(name != null && _count > 0){
         N find = innerMakeName(name);
         int hash = RHash.hash(find);
         SHashIntEntry entry = _table[hash % _table.length];
         while(null != entry){
            if(entry.hash == hash){
               N entryName = innerMakeName(_names[entry.index]);
               if(find.equals(entryName)){
                  return entry.index;
               }
            }
            entry = entry.next;
         }
      }
      return -1;
   }

   //============================================================
   // <T>查找指定数据内容在集合中首次出现的位置。</T>
   //
   // @param value 数据内容
   // @return 索引位置
   //============================================================
   public int indexOfValue(V value){
      return indexOfValue(value, 0);
   }

   //============================================================
   // <T>查找指定数据内容在集合中首次出现的位置。</T>
   //
   // @param value 数据内容
   // @param offset 偏移位置
   // @return 索引位置
   //============================================================
   public int indexOfValue(V value,
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
   // <T>确保集合有指定大小的容量。</T>
   //
   // @param count 总数
   //============================================================
   @SuppressWarnings("unchecked")
   public void ensureSize(int count){
      if(null == _values){
         // 创建数据
         int total = Math.max(CAPACITY, count);
         _names = (N[])Array.newInstance(_nameClass, total);
         _values = (V[])Array.newInstance(_valueClass, total);
         _table = new SHashIntEntry[total];
      }else if(count > _values.length){
         // 计算内存大小
         int total = Math.max(CAPACITY, count + count);
         // 创建名称数组
         N[] names = (N[])Array.newInstance(_nameClass, total);
         System.arraycopy(_names, 0, names, 0, _count);
         _names = names;
         // 创建内容数组
         V[] values = (V[])Array.newInstance(_valueClass, total);
         System.arraycopy(_values, 0, values, 0, _count);
         _values = values;
         // 调整大小
         if(count > (_table.length << 2)){
            _table = RHash.resize(_table, _table.length << 2);
         }
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
   // @param offset 开始位置
   // @param count 数据个数
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
   // @param map 数据集合
   //============================================================
   @Override
   public void append(IMap<N, V> map){
      append(map, 0, map.count());
   }

   //============================================================
   // <T>追加数据集合到自己尾部。</T>
   //
   // @param map 数据集合
   // @param offset 开始位置
   // @param count 数据个数
   //============================================================
   public void append(IMap<N, V> map,
                      int offset,
                      int count){
      if(null != map){
         int loop = offset + count;
         for(int n = offset; n < loop; n++){
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
      return (index >= 0 && index < _count) ? _names[index] : null;
   }

   //============================================================
   // <T>获得指定索引位置的数据内容。</T>
   //
   // @param index 索引位置
   // @return 数据内容
   //============================================================
   @Override
   public V value(int index){
      return (index >= 0 && index < _count) ? _values[index] : null;
   }

   //============================================================
   // <T>设置指定索引位置的数据内容。</T>
   //
   // @param index 索引位置
   // @param value 数据内容
   //============================================================
   public void setValue(int index,
                        V value){
      if(index >= 0 && index < _count){
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
   // <T>根据名称查找内容。</T>
   //
   // @param name 名称
   // @return 内容
   //============================================================
   public V find(N name){
      int index = indexOf(name);
      return (-1 != index) ? _values[index] : null;
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
      if(-1 == index){
         throw new Error("Can't find value by name. (name=" + name + ")");
      }
      return _values[index];
   }

   //============================================================
   // <T>根据名称获得内容。</T>
   //
   // @param name 名称
   // @param value 内容
   // @return 内容
   //============================================================
   @Override
   public V get(N name,
                V value){
      int index = indexOf(name);
      return (-1 != index) ? _values[index] : value;
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
      if(null == name){
         throw new NullPointerException("name");
      }
      // 查找内容
      N find = innerMakeName(name);
      int hash = RHash.hash(find);
      int index = hash % _table.length;
      SHashIntEntry entry = _table[index];
      while(entry != null){
         if(entry.hash == hash && find.equals(innerMakeName(_names[entry.index]))){
            _values[entry.index] = value;
            return;
         }
         entry = entry.next;
      }
      // 创建节点
      if(_count + 1 > _values.length){
         ensureSize(_count + 1);
         index = hash % _table.length;
      }
      _table[index] = new SHashIntEntry(hash, _count, _table[index]);
      _names[_count] = name;
      _values[_count++] = value;
   }

   //============================================================
   // <T>擦除指定索引位置的数据内容。</T>
   //
   // @param index 索引位置
   // @return 数据内容
   //============================================================
   public V erase(int index){
      return (index >= 0 && index < _count) ? remove(_names[index]) : null;
   }

   //============================================================
   // <T>移除指定名称的数据内容。</T>
   //
   // @param name 数据名称
   // @return 数据内容
   //============================================================
   @Override
   public V remove(N name){
      if(null == name){
         throw new NullPointerException("name");
      }
      // find
      int index = -1;
      N find = innerMakeName(name);
      int hash = RHash.hash(find);
      int pos = hash % _table.length;
      SHashIntEntry prior = _table[pos];
      SHashIntEntry entry = prior;
      while(entry != null){
         if(entry.hash == hash){
            N make = innerMakeName(_names[entry.index]);
            if(entry.hash == hash && find.equals(make)){
               index = entry.index;
               if(entry == prior){
                  _table[pos] = entry.next;
               }else{
                  prior.next = entry.next;
               }
               break;
            }
         }
         prior = entry;
         entry = entry.next;
      }
      // remove
      if(index != -1){
         V old = _values[index];
         // Fix hash entry
         RHash.removeFix(_table, index);
         // Fix names/values
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
      RObject.clear(_table);
   }

   //============================================================
   // <T>清除全部数据内容。</T>
   //============================================================
   @Override
   public void finalize() throws Throwable{
      _count = 0;
      RObject.clear(_names);
      RObject.clear(_values);
      RObject.clear(_table);
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
      info.append(" [ count=");
      info.appendInt(_count);
      info.append(" ]");
      if(_count > 0){
         info.append(" { ");
         for(int n = 0; n < _count; n++){
            RDump.dump(info, name(n).toString(), value(n));
         }
         info.append(" }");
      }
      return info;
   }
   //   public void compress(){
   //      // Filter
   //      int count = 0;
   //      N[] names = RArray.newInstance(_nameClass, _count);
   //      V[] values = RArray.newInstance(_valueClass, _count);
   //      for(int n = 0; n < _count; n++){
   //         if(!RObject.isEmpty(_values[n])){
   //            names[count] = _names[n];
   //            values[count++] = _values[n];
   //         }
   //      }
   //      // Rebuild
   //      _count = count;
   //      _names = RArray.sub(_nameClass, names, 0, count);
   //      _values = RArray.sub(_valueClass, values, 0, count);
   //      rebuildTable();
   //   }
   //
   //   @Override
   //   public T copy(){
   //      T copy = RClass.newInstance(getClass());
   //      copy.assign(this);
   //      return copy;
   //   }
   //   /**
   //    * <T>删除指定位置的数据。</T>
   //    * <P>快速删除对象，不修改数据尺寸。<B/>
   //    * 当操作次数超过1000次的话，重新整理一下内存。
   //    * 整理时会修改数据大小。</P>
   //    * 
   //    * @param index 索引位置
   //    */
   //   public V delete(int index){
   //      V value = value(index);
   //      setValue(index, null);
   //      if(++_deleteCount > RInteger.SIZE_1K){
   //         compress();
   //         _deleteCount = 0;
   //      }
   //      return value;
   //   }
   //   /**
   //    * <T>删除指定名称的数据。</T>
   //    * <P>快速删除对象，不修改数据尺寸。<B/>
   //    * 当操作次数超过1000次的话，重新整理一下内存。
   //    * 整理时会修改数据大小。</P>
   //    * 
   //    * @param name 名称
   //    */
   //   public V delete(N name){
   //      V value = get(name);
   //      set(name, null);
   //      if(++_deleteCount > RInteger.SIZE_1K){
   //         compress();
   //         _deleteCount = 0;
   //      }
   //      return value;
   //   }
   //
   //   @SuppressWarnings("unused")
   //   private void onUnserialize(FXmlNode config){
   //      rebuildTable();
   //   }
   //   @SuppressWarnings("unchecked")
   //   private void readObject(ObjectInputStream inputStream)
   //         throws IOException{
   //      try{
   //         _nameClass = (Class<N>) inputStream.readObject();
   //         _valueClass = (Class<V>) inputStream.readObject();
   //         _count = inputStream.readInt();
   //         ensureSize(_count);
   //         for(int n = 0; n < _count; n++){
   //            set((N) inputStream.readObject(), (V) inputStream.readObject());
   //         }
   //      }catch(Exception e){
   //         throw new FFatalError(e);
   //      }
   //   }
   //   protected void rebuildTable(){
   //      _table = new FHashEntry[Math.max(_count, RObject.CAPACITY)];
   //      for(int n = 0; n < _count; n++){
   //         int hash = RHash.hash(makeName(_names[n]));
   //         int index = hash % _table.length;
   //         _table[index] = new FHashEntry(hash, n, _table[index]);
   //      }
   //   }
   //   public void writeObject(ObjectOutputStream outputStream)
   //         throws IOException{
   //      outputStream.writeObject(_nameClass);
   //      outputStream.writeObject(_valueClass);
   //      outputStream.writeInt(_count);
   //      for(int n = 0; n < _count; n++){
   //         outputStream.writeObject(_names[n]);
   //         outputStream.writeObject(_values[n]);
   //      }
   //   }
}
