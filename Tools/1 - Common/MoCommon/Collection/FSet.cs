using System;
using System.Collections;
using MO.Common.Lang;

namespace MO.Common.Collection
{
   //============================================================
   // <T>集合。</T>
   //============================================================
   public class FSet<V> : IEnumerable, IDump
   {
      // 初始化容量
      public const int CAPABILITY = 8;

      // 总数
      protected int _count;

      // 哈希表
      protected RHash.FEntry[] _entries;

      // 内容列表
      protected V[] _values;

      //============================================================
      // <T>构造集合。</T>
      //============================================================
      public FSet() {
         EnsureSize(CAPABILITY);
      }

      //============================================================
      // <T>构造集合。</T>
      //
      // @param capacity 初始化大小
      //============================================================
      public FSet(int capacity) {
         EnsureSize(Math.Max(CAPABILITY, capacity));
      }

      //============================================================
      // <T>[内部] 获得哈希码。</T>
      //
      // @param value 内容
      // @return 哈希码
      //============================================================
      protected virtual int InnerCode(V value) {
         return RHash.Code(value);
      }

      //============================================================
      // <T>[内部] 比较两项是否相等。</T>
      //
      // @param source 源项目
      // @param target 目标项目
      // @return 是否相等
      //============================================================
      protected virtual bool InnerEquals(V source, V target) {
         return source.Equals(target);
      }

      //============================================================
      // <T>根据索引位置获得内容。</T>
      //
      // @param index 索引
      //============================================================
      public V this[int index] {
         get { return Get(index); }
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
      public bool IsEmpty() {
         return (0 == _count);
      }

      //============================================================
      // <T>是否含有内容。</T>
      //
      // @param value 内容
      // @return 是否含有
      //============================================================
      public bool Contains(V value) {
         return (-1 != IndexOf(value));
      }

      //============================================================
      // <T>获得内容的索引位置。</T>
      //
      // @param value 内容
      // @return 索引位置
      //============================================================
      public int IndexOf(V value) {
         if((null != value) && (_count > 0)) {
            int hash = InnerCode(value);
            int index = hash % _entries.Length;
            RHash.FEntry entry = _entries[index];
            while(null != entry) {
               if(entry._hash == hash){
                  if(InnerEquals(value, _values[entry._index])){
                     return entry._index;
                  }
               }
               entry = entry._next;
            }
         }
         return -1;
      }

      //============================================================
      // <T>获得索引位置的内容。</T>
      //
      // @param index 索引位置
      // @return 内容
      //============================================================
      public V Get(int index) {
         // 检查范围
         if(index < 0 && index >= _count) {
            throw new Exception("Index out range.");
         }
         // 返回值
         return _values[index];
      }

      //============================================================
      // <T>查找索引位置的内容。</T>
      //
      // @param index 索引位置
      // @return 内容
      //============================================================
      public V Find(int index) {
         return (index >= 0 && index < _count) ? _values[index] : default(V);
      }

      //============================================================
      // <T>查找索引位置的内容。</T>
      //
      // @param index 索引位置
      // @param value 默认内容
      // @return 内容
      //============================================================
      public V Find(int index, V value) {
         return (index >= 0 && index < _count) ? _values[index] : value;
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
            _values = new V[size];
         } else if(size > _values.Length) {
            // 计算大小
            size += _values.Length;
            // 生成内容数组
            V[] values = new V[size];
            Array.Copy(_values, 0, values, 0, _count);
            _values = values;
            // 调整哈希表
            int factor = _entries.Length * RHash.FACTOR;
            if (size > factor) {
               _entries = RHash.Resize(_entries, size);
            }
         }
      }

      //============================================================
      // <T>追加集合内容。</T>
      //
      // @param set 集合
      //============================================================
      public void Append(FSet<V> set) {
         int count = set.Count;
         for(int n = 0; n < count; n++) {
            Push(set.Get(n));
         }
      }

      //============================================================
      // <T>追加一个内容。</T>
      // <P>如果内容存在则产生例外。</P>
      // <P>内容不允许为空。</P>
      //
      // @param value 内容
      // @return 设置内容的位置
      //============================================================
      public virtual int Push(V value) {
         int index = TryPush(value);
         if(-1 == index) {
            // 产生重复例外
            throw new Exception("Duplicate value=" + value);
         }
         return index;
      }

      //============================================================
      // <T>尝试追加一个内容。</T>
      // <P>内容不允许为空。</P>
      //
      // @param value 内容
      // @return 设置内容的位置
      //============================================================
      public virtual int TryPush(V value) {
         // 检查内容
         if(null == value) {
            throw new NullReferenceException();
         }
         // 获得内容位置
         int hash = InnerCode(value);
         int index = hash % _entries.Length;
         RHash.FEntry entry = _entries[index];
         while(null != entry) {
            if (entry._hash == hash) {
               if (InnerEquals(value, _values[entry._index])) {
                  return -1;
               }
            }
            entry = entry._next;
         }
         // 调整哈希表大小
         if(_count + 1 > _values.Length) {
            EnsureSize(_count + 1);
            index = hash % _entries.Length;
         }
         // 新建内容
         _entries[index] = new RHash.FEntry(hash, _count, _entries[index]);
         _values[_count] = value;
         return _count++;
      }

      //============================================================
      // <T>移除指定名称的内容。</T>
      //
      // @param name 名称
      //============================================================
      public virtual V Remove(V value) {
         // 检查名称
         if (null == value) {
            throw new NullReferenceException();
         }
         // 查找节点
         int index = -1;
         int hash = InnerCode(value);
         int pos = hash % _entries.Length;
         RHash.FEntry prior = _entries[pos];
         RHash.FEntry entry = prior;
         while (null != entry) {
            if (entry._hash == hash) {
               if (InnerEquals(value, _values[entry._index])) {
                  index = entry._index;
                  if (entry == prior) {
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
         if (-1 != index) {
            V old = _values[index];
            // 修正哈希入口列表
            RHash.Remove(_entries, index);
            // 修正名称和内容数组
            int move = _count - index - 1;
            if (move > 0) {
               Array.Copy(_values, index + 1, _values, index, move);
            }
            // 修正最后元素
            _count--;
            _values[_count] = default(V);
            return old;
         }
         return default(V);
      }

      //============================================================
      // <T>转换为数组对象。</T>
      //============================================================
      public V[] ToArray() {
         V[] values = new V[_count];
         Array.Copy(_values, 0, values, 0, _count);
         return values;
      }

      //============================================================
      // <T>清除所有对象。</T>
      //============================================================
      public void Clear() {
         _count = 0;
         Array.Clear(_values, 0, _values.Length);
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
         return new FObjectsEnumerator<V>(_values, 0, _count);
      }

      #endregion

      #region IDump implements

      //============================================================
      // <T>获得调试转储信息。</T>
      //
      // @return 转储信息
      //============================================================
      public FDumpInfo Dump() {
         return Dump(new FDumpInfo(this));
      }

      //============================================================
      // <T>获得调试转储信息。</T>
      //
      // @param info 转储信息
      // @return 转储信息
      //============================================================
      public virtual FDumpInfo Dump(FDumpInfo info) {
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
