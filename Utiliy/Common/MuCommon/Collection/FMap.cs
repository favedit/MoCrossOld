using System;
using System.Collections;
using MO.Common.Lang;

namespace MO.Common.Collection
{
   //============================================================
   // <T>哈希集合。</T>
   //
   // @history MAOCY 140414
   //============================================================
   public class FMap<N, V> : IEnumerable, IDump
   {
      // 初始化容量
      public const int CAPABILITY = 8;

      // 总数
      protected int _count;

      // 哈希表
      protected RHash.FEntry[] _entries;

      // 名称列表
      protected N[] _names;

      // 内容列表
      protected V[] _values;

      //============================================================
      // <T>构造哈希集合。</T>
      //============================================================
      public FMap() {
         EnsureSize(CAPABILITY);
      }

      //============================================================
      // <T>构造哈希集合。</T>
      //
      // @param capacity 初始化大小
      //============================================================
      public FMap(int capacity) {
         EnsureSize(Math.Max(CAPABILITY, capacity));
      }

      //============================================================
      // <T>[内部] 获得名称的哈希码。</T>
      //
      // @param name 名称
      // @return 哈希码
      //============================================================
      protected virtual int InnerCode(N name) {
         return RHash.Code(name);
      }

      //============================================================
      // <T>[内部] 比较两项是否相等。</T>
      //
      // @param source 源项目
      // @param target 目标项目
      // @return 是否相等
      //============================================================
      protected virtual bool InnerEquals(N source, N target) {
         return source.Equals(target);
      }

      //============================================================
      // <T>根据索引获得和设置内容。</T>
      //
      // @param index 索引
      //============================================================
      public virtual V this[int index] {
         get { return Value(index); }
         set { SetValue(index, value); }
      }

      //============================================================
      // <T>根据名称获得和设置内容。</T>
      //
      // @param name 名称
      //============================================================
      public virtual V this[N name] {
         get { return Get(name); }
         set { Set(name, value); }
      }

      //============================================================
      // <T>获得总数。</T>
      //
      // @return 总数
      //============================================================
      public int Count {
         get { return _count; }
      }

      //============================================================
      // <T>获得名称内存。</T>
      //
      // @return 内存
      //============================================================
      public N[] MemoryNames {
         get { return _names; }
      }

      //============================================================
      // <T>获得内容内存。</T>
      //
      // @return 内存
      //============================================================
      public V[] MemoryValues {
         get { return _values; }
      }

      //============================================================
      // <T>判断是否为空。</T>
      //
      // @return 是否为空
      //============================================================
      public bool IsEmpty {
         get { return (0 == _count); }
      }

      //============================================================
      // <T>是否含有名称。</T>
      //
      // @param name 名称
      // @return 是否含有
      //============================================================
      public bool Contains(N name) {
         return (-1 != IndexOfName(name));
      }

      //============================================================
      // <T>是否含有内容。</T>
      //
      // @param value 内容
      // @return 是否含有
      //============================================================
      public bool ContainsValue(V value) {
         return (-1 != IndexOfValue(value));
      }

      //============================================================
      // <T>获得名称的索引位置。</T>
      //
      // @param name 名称
      // @return 索引位置
      //============================================================
      public int IndexOfName(N name) {
         if((null != name) && (_count > 0)) {
            int hash = InnerCode(name);
            int index = hash % _entries.Length;
            RHash.FEntry entry = _entries[index];
            while(null != entry) {
               if(entry._hash == hash) {
                  if(InnerEquals(name, _names[entry._index])) {
                     return entry._index;
                  }
               }
               entry = entry._next;
            }
         }
         return -1;
      }

      //============================================================
      // <T>获得内容的索引位置。</T>
      //
      // @param value 内容
      // @return 索引位置
      //============================================================
      public int IndexOfValue(V value) {
         if((null != value) && (_count > 0)) {
            for(int n = 0; n < _count; n++) {
               if(value.Equals(_values[n])) {
                  return n;
               }
            }
         }
         return -1;
      }

      //============================================================
      // <T>获得索引位置的名称。</T>
      //
      // @param index 索引位置
      // @return 名称
      //============================================================
      public N Name(int index) {
         // 检查范围
         if(index < 0 && index >= _count) {
            throw new Exception("Name index is out range.");
         }
         // 返回值
         return _names[index];
      }

      //============================================================
      // <T>获得索引位置的名称。</T>
      //
      // @param index 索引位置
      // @param name 默认名称
      // @return 名称
      //============================================================
      public N Name(int index, N name) {
         return (index >= 0 && index < _count) ? _names[index] : name;
      }

      //============================================================
      // <T>获得索引位置的内容。</T>
      //
      // @param index 索引位置
      // @return 内容
      //============================================================
      public virtual V Value(int index) {
         // 检查范围
         if(index < 0 && index >= _count) {
            throw new Exception("Value index is out range.");
         }
         // 返回值
         return _values[index];
      }

      //============================================================
      // <T>获得索引位置的内容。</T>
      //
      // @param index 索引位置
      // @param value 默认内容
      // @return 内容
      //============================================================
      public virtual V Value(int index, V value) {
         return (index >= 0 && index < _count) ? _values[index] : value;
      }

      //============================================================
      // <T>设置索引位置的内容。</T>
      //
      // @param index 索引位置
      // @param value 内容
      //============================================================
      public virtual void SetValue(int index, V value) {
         // 检查范围
         if(index < 0 && index >= _count) {
            throw new Exception("Out range.");
         }
         // 设置值
         _values[index] = value;
      }

      //============================================================
      // <T>获得指定名称的内容。</T>
      //
      // @param name 名称
      // @return 内容
      //============================================================
      public virtual V Get(N name) {
         int index = IndexOfName(name);
         if(-1 == index) {
            throw new Exception("Can't find value by name. (name=" + name + ")");
         }
         return _values[index];
      }

      //============================================================
      // <T>查找指定名称的内容。</T>
      //
      // @param name 名称
      // @return 内容
      //============================================================
      public virtual V Find(N name) {
         int index = IndexOfName(name);
         return (-1 != index) ? _values[index] : default(V);
      }

      //============================================================
      // <T>查找指定名称的内容。</T>
      //
      // @param name 名称
      // @param value 默认内容
      // @return 内容
      //============================================================
      public virtual V Find(N name, V value) {
         int index = IndexOfName(name);
         return (-1 != index) ? _values[index] : value;
      }

      //============================================================
      // <T>根据名称，设置内容。</T>
      // <P>如果内容存在则替换内容，不存在则新建名称和内容。</P>
      // <P>名称不允许为空。</P>
      //
      // @param name 名称
      // @param value 内容
      //============================================================
      public virtual void Set(N name, V value) {
         // 检查名称
         if(null == name) {
            throw new NullReferenceException();
         }
         // 获得名称位置
         int hash = InnerCode(name);
         int index = hash % _entries.Length;
         RHash.FEntry entry = _entries[index];
         while(null != entry) {
            if(entry._hash == hash) {
               if(InnerEquals(name, _names[entry._index])) {
                  // 名称存在的情况
                  _values[entry._index] = value;
                  return;
               }
            }
            entry = entry._next;
         }
         // 调整哈希表大小
         if(_count + 1 > _values.Length) {
            EnsureSize(_count + 1);
            index = hash % _entries.Length;
         }
         // 新建名称和内容
         _entries[index] = new RHash.FEntry(hash, _count, _entries[index]);
         _names[_count] = name;
         _values[_count] = value;
         _count++;
      }

      //============================================================
      // <T>移除指定名称的内容。</T>
      //
      // @param name 名称
      //============================================================
      public virtual V Remove(N name) {
         // 检查名称
         if(null == name) {
            throw new NullReferenceException();
         }
         // 查找节点
         int index = -1;
         int hash = InnerCode(name);
         int pos = hash % _entries.Length;
         RHash.FEntry prior = _entries[pos];
         RHash.FEntry entry = prior;
         while(null != entry) {
            if(entry._hash == hash) {
               if(InnerEquals(name, _names[entry._index])) {
                  index = entry._index;
                  if(entry == prior) {
                     _entries[pos] = entry._next;
                  } else {
                     prior._next = entry._next;
                  }
                  break;
               }
            }
            prior = entry;
            entry = entry._next;
         }
         // 有移除操作
         if(-1 != index) {
            V old = _values[index];
            // 修正哈希入口列表
            RHash.Remove(_entries, index);
            // 修正名称和内容数组
            int move = _count - index - 1;
            if(move > 0) {
               Array.Copy(_names, index + 1, _names, index, move);
               Array.Copy(_values, index + 1, _values, index, move);
            }
            // 修正最后元素
            _count--;
            _names[_count] = default(N);
            _values[_count] = default(V);
            return old;
         }
         return default(V);
      }

      //============================================================
      // <T>获得名称数组。</T>
      //
      // @return 名称数组
      //============================================================
      public N[] Names {
         get {
            N[] names = new N[_count];
            Array.Copy(_names, 0, names, 0, _count);
            return names;
         }
      }

      //============================================================
      // <T>获得内容数组。</T>
      //
      // @return 内容数组
      //============================================================
      public V[] Values {
         get {
            V[] values = new V[_count];
            Array.Copy(_values, 0, values, 0, _count);
            return values;
         }
      }

      //============================================================
      // <T>确保容量。</T>
      // <P>如果内存比指定内存大，则重新收集内存。</P>
      //
      // @param size 容量大小
      //============================================================
      public void EnsureSize(int size) {
         if(null == _values) {
            // 初次构造时
            _entries = new RHash.FEntry[size];
            _names = new N[size];
            _values = new V[size];
         } else if(size > _values.Length) {
            // 计算大小
            size += _values.Length;
            // 生成名称数组
            N[] names = new N[size];
            Array.Copy(_names, 0, names, 0, _count);
            _names = names;
            // 生成内容数组
            V[] values = new V[size];
            Array.Copy(_values, 0, values, 0, _count);
            _values = values;
            // 调整哈希表
            int factor = _entries.Length * RHash.FACTOR;
            if(size > factor) {
               _entries = RHash.Resize(_entries, size);
            }
         }
      }

      //============================================================
      // <T>接收哈希集合内容。</T>
      //
      // @param map 哈希集合
      //============================================================
      public void Assign(FMap<N, V> map) {
         // 清除所有数据
         Clear();
         // 追加数据
         Append(map);
      }

      //============================================================
      // <T>追加哈希集合内容。</T>
      //
      // @param map 哈希集合
      //============================================================
      public void Append(FMap<N, V> map) {
         int count = map.Count;
         for(int n = 0; n < count; n++) {
            Set(map.Name(n), map.Value(n));
         }
      }

      //============================================================
      // <T>重置所有对象。</T>
      //============================================================
      public void Reset() {
         _count = 0;
         Array.Clear(_names, 0, _names.Length);
         Array.Clear(_values, 0, _values.Length);
         Array.Clear(_entries, 0, _entries.Length);
      }

      //============================================================
      // <T>清除所有对象。</T>
      //============================================================
      public void Clear() {
         _count = 0;
         Array.Clear(_entries, 0, _entries.Length);
      }

      //============================================================
      // <T>转换为字符串。</T>
      //
      // @return 字符串
      //============================================================
      public override string ToString() {
         return Dump().ToString();
      }

      #region IEnumerable implements

      //============================================================
      // <T>获得枚举器。</T>
      //
      // @return 枚举器
      //============================================================
      public virtual IEnumerator GetEnumerator() {
         return new FPairEnumerator<N, V>(_names, _values, 0, _count);
      }

      #endregion

      #region IDump implements

      //============================================================
      // <T>获得调试转储信息。</T>
      //
      // @return 转储信息
      //============================================================
      public FDump Dump() {
         return Dump(new FDump(this));
      }

      //============================================================
      // <T>获得调试转储信息。</T>
      //
      // @param info 转储信息
      // @return 转储信息
      //============================================================
      public virtual FDump Dump(FDump info) {
         info.Begin();
         info.Append("count=" + _count);
         // 追加所有项目
         info.Append(" {");
         for(int n = 0; n < _count; n++) {
            // 追加分隔符
            if(n > 0) {
               info.Append(", ");
            }
            // 追加内容
            V value = _values[n];
            info.Append(_names[n]);
            info.Append("=");
            if(value is IDump) {
               ((IDump)value).Dump(info);
            } else {
               info.Append(value);
            }
         }
         info.Append("}");
         info.End();
         return info;
      }

      #endregion
   }
}
