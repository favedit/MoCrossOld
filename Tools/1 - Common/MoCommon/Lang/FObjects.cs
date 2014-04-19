using System;
using System.Collections.Generic;
using Collections = System.Collections;

namespace MO.Common.Lang
{
   //============================================================
   // <T>对象集合。</T>
   //============================================================
   public class FObjects<T> : IObjects<T>
   {
      // 初始化容量
      public const int CAPABILITY = 8;

      // 对象内存
      protected T[] _items;

      // 对象个数
      protected int _count;

      //============================================================
      // <T>构造对象集合。</T>
      //============================================================
      public FObjects() {
         EnsureSize(CAPABILITY);
      }

      //============================================================
      // <T>构造对象集合。</T>
      //
      // @param capacity 初始化大小
      //============================================================
      public FObjects(int capacity) {
         EnsureSize(Math.Max(CAPABILITY, capacity));
      }

      //============================================================
      // <T>构造对象集合。</T>
      //
      // @param items 对象集合
      // @param offset 开始位置
      // @param count 对象个数
      //============================================================
      public FObjects(T[] items, int offset, int count) {
         EnsureSize(Math.Max(CAPABILITY, count));
         Append(items, offset, count);
      }

      //============================================================
      // <T>检查是否存在对象。</T>
      // <P>如果为空则产生例外。</P>
      //
      // @inner
      //============================================================
      protected void InnerCheckExists() {
         if(0 == _count) {
            throw new Exception("Object is not exists.");
         }
      }

      //============================================================
      // <T>检查索引位置是否在合法范围内。</T>
      // <P>如果范围超出则产生例外。</P>
      //
      // @inner
      // @param index 索引位置
      //============================================================
      protected void InnerCheckRange(int index) {
         if(index < 0 || index >= _count) {
            throw new Exception("Index range error. (count=" + _count + ", index=" + index + ")");
         }
      }

      //============================================================
      // <T>获得或设定索引位置的对象。</T>
      //
      // @param index 索引位置
      //============================================================
      public T this[int index] {
         get { return Get(index); }
         set { Set(index, value); }
      }

      //============================================================
      // <T>获得总大小。</T>
      //
      // @return 总大小
      //============================================================
      public int Size {
         get { return _items.Length; }
      }

      //============================================================
      // <T>获得长度。</T>
      //
      // @return 长度
      //============================================================
      public int Count {
         get { return _count; }
      }

      //============================================================
      // <T>获得内存。</T>
      //
      // @return 内存
      //============================================================
      public T[] Items {
         get { return _items; }
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
      // <T>判断是否含有指定内容。</T>
      //
      // @param value 对象内容
      // @return 是否相等
      //============================================================
      public bool Contains(T value) {
         int index = IndexOf(value);
         return (-1 != index);
      }

      //============================================================
      // <T>判断是否和指定对象数组相等。</T>
      //
      // @param items 对象数组
      // @param offset 开始位置
      // @param count 对象个数
      // @return 是否相等
      //============================================================
      public bool Equals(T[] items, int offset, int count) {
         // 检查数组是否为空
         if(null == items) {
            return false;
         }
         // 检查长度
         if(_count != count) {
            return false;
         }
         // 检查相等
         for(int n = 0; n < count; n++) {
            if(!_items[n].Equals(items[offset++])) {
               return false;
            }
         }
         return true;
      }

      //============================================================
      // <T>判断是否和指定对象集合相等。</T>
      //
      // @param items 对象集合
      // @return 是否相等
      //============================================================
      public bool Equals(T[] items) {
         // 检查数组是否为空
         if(null == items) {
            return false;
         }
         // 检查对象是否相等
         return Equals(items, 0, items.Length);
      }

      //============================================================
      // <T>判断是否和指定对象集合相等。</T>
      //
      // @param items 对象集合
      // @return 是否相等
      //============================================================
      public bool Equals(IObjects<T> items) {
         // 检查数组是否为空
         if(null == items) {
            return false;
         }
         // 检查对象是否相等
         return Equals(items.Items, 0, items.Count);
      }

      //============================================================
      // <T>查找对象的位置。</T>
      // <P>如果不存在则返回-1。</P>
      //
      // @param value 对象
      // @param offset 开始位置
      // @return 所在位置
      //============================================================
      public int IndexOf(T value, int offset) {
         return Array.IndexOf<T>(_items, value, offset, _count);
      }

      //============================================================
      // <T>查找对象的位置。</T>
      // <P>如果不存在则返回-1。</P>
      //
      // @param value 对象
      // @return 所在位置
      //============================================================
      public int IndexOf(T value) {
         return Array.IndexOf<T>(_items, value, 0, _count);
      }

      //============================================================
      // <T>反向查找对象的位置。</T>
      // <P>如果不存在则返回-1。</P>
      //
      // @param value 对象
      // @param offset 开始位置
      // @return 所在位置
      //============================================================
      public int LastIndexOf(T value, int offset) {
         return Array.LastIndexOf<T>(_items, value, offset, _count);
      }

      //============================================================
      // <T>反向查找对象的位置。</T>
      // <P>如果不存在则返回-1。</P>
      //
      // @param value 对象
      // @return 所在位置
      //============================================================
      public int LastIndexOf(T value) {
         return Array.LastIndexOf<T>(_items, value, 0, _count);
      }

      //============================================================
      // <T>查找指定对象集合的位置。</T>
      // <P>如果不存在则返回-1。</P>
      //
      // @param items 对象集合
      // @param offset 开始位置
      // @param count 对象个数
      // @return 所在位置
      //============================================================
      public int Find(T[] items, int offset, int count) {
         return RArray<T>.Find(_items, 0, _count, items, offset, count);
      }

      //============================================================
      // <T>查找指定对象集合的位置。</T>
      // <P>如果不存在则返回-1。</P>
      //
      // @param items 对象集合
      // @return 所在位置
      //============================================================
      public int Find(T[] items) {
         return RArray<T>.Find(_items, 0, _count, items, 0, items.Length);
      }

      //============================================================
      // <T>查找对象数组的位置。</T>
      // <P>如果不存在则返回-1。</P>
      //
      // @param items 对象数组
      // @return 所在位置
      //============================================================
      public int Find(IObjects<T> items) {
         return RArray<T>.Find(_items, 0, _count, items.Items, 0, items.Count);
      }

      //============================================================
      // <T>获得首对象。</T>
      //
      // @return 对象
      //============================================================
      public T First {
         get {
            // 检查是否存在对象
            InnerCheckExists();
            // 获得对象
            return _items[0];
         }
      }

      //============================================================
      // <T>查找首对象。</T>
      //
      // @param count 对象个数
      //============================================================
      public T NvlFirst {
         get {
            return (_count > 0) ? _items[0] : default(T);
         }
      }

      //============================================================
      // <T>获得尾对象。</T>
      //
      // @return 对象
      //============================================================
      public T Last {
         get {
            // 检查是否存在对象
            InnerCheckExists();
            // 获得对象
            return _items[_count - 1];
         }
      }

      //============================================================
      // <T>查找尾对象。</T>
      //
      // @return 对象
      //============================================================
      public T NvlLast {
         get {
            return (_count > 0) ? _items[_count - 1] : default(T);
         }
      }

      //============================================================
      // <T>获得对象枚举器。</T>
      //
      // @implement IEnumerable<T>
      //============================================================
      public IEnumerator<T> GetEnumerator() {
         return new FObjectsEnumerator<T>(_items, 0, _count);
      }

      //============================================================
      // <T>获得对象枚举器。</T>
      //
      // @implement IEnumerable<T>
      //============================================================
      Collections.IEnumerator Collections.IEnumerable.GetEnumerator() {
         return new FObjectsEnumerator<T>(_items, 0, _count);
      }

      //============================================================
      // <T>确保容量大小。</T>
      // <P>如果内存比指定内存大，则重新收集内存，并且保留原来数据。</P>
      //
      // @param size 容量大小
      //============================================================
      public void EnsureSize(int size) {
         if(null == _items) {
            _items = new T[size];
         } else if(size > _items.Length) {
            // 计算新缓冲内存的大小
            size += _items.Length;
            // 创建新缓冲内存
            T[] items = new T[size];
            Array.Copy(_items, 0, items, 0, _count);
            _items = items;
         }
      }

      //============================================================
      // <T>获得索引位置的对象。</T>
      // <P>如果不存在产生例外。</P>
      //
      // @param index 索引位置
      // @return 对象
      //============================================================
      public T Get(int index) {
         // 检查范围
         InnerCheckRange(index);
         // 返回内容
         return _items[index];
      }

      //============================================================
      // <T>获得索引位置的对象。</T>
      // <P>如果不存在则返回该类型的默认值。</P>
      //
      // @param index 索引位置
      // @param defaultValue 默认内容
      // @return 对象
      //============================================================
      public T Get(int index, T defaultValue) {
         // 检查范围
         if(index < 0 || index >= _count) {
            return defaultValue;
         }
         // 返回内容
         return _items[index];
      }

      //============================================================
      // <T>尝试获得索引位置的对象。</T>
      // <P>如果不存在则返回该类型的默认值。</P>
      //
      // @param index 索引位置
      // @return 对象
      //============================================================
      public T TryGet(int index) {
         // 检查范围
         if(index < 0 || index >= _count) {
            return default(T);
         }
         // 返回内容
         return _items[index];
      }

      //============================================================
      // <T>设置索引位置的对象。</T>
      // <P>如果不存在产生例外。</P>
      //
      // @param index 索引位置
      // @param value 对象
      //============================================================
      public void Set(int index, T value) {
         // 检查范围
         InnerCheckRange(index);
         // 设置内容
         _items[index] = value;
      }

      //============================================================
      // <T>尝试设置索引位置的对象。</T>
      // <P>如果不存在则不设置内容。</P>
      //
      // @param index 索引位置
      // @param value 对象
      //============================================================
      public void TrySet(int index, T value) {
         if(index >= 0 && index < _count) {
            _items[index] = value;
         }
      }

      //============================================================
      // <T>设置索引位置的对象。</T>
      // <P>如果不存在则扩展对象大小，设置内容。</P>
      //
      // @param index 索引位置
      // @param value 对象
      //============================================================
      public void SetExtend(int index, T value) {
         if(index >= 0 && index < _count) {
            // 设置原有对象
            _items[index] = value;
         } else if(index >= _count) {
            // 扩充容量
            EnsureSize(index + 1);
            _items[index] = value;
            _count = index + 1;
         }
      }

      //============================================================
      // <T>设置对象个数。</T>
      //
      // @param count 对象个数
      //============================================================
      public void SetCount(int count) {
         EnsureSize(count);
         _count = count;
      }

      //============================================================
      // <T>接收对象数组。</T>
      //
      // @param items 对象数组
      // @param offset 开始位置
      // @param count 对象个数
      //============================================================
      public void Assign(T[] items, int offset, int count) {
         EnsureSize(count);
         Array.Copy(items, offset, _items, 0, count);
         _count = count;
      }

      //============================================================
      // <T>接收对象数组。</T>
      //
      // @param items 对象数组
      //============================================================
      public void Assign(T[] items) {
         Assign(items, 0, items.Length);
      }

      //============================================================
      // <T>接收对象数组。</T>
      //
      // @param items 对象数组
      //============================================================
      public void Assign(IObjects<T> items) {
         Assign(items.Items, 0, items.Count);
      }

      //============================================================
      // <T>追加对象数组。</T>
      //
      // @param items 对象数组
      // @param offset 开始位置
      // @param count 对象个数
      //============================================================
      public void Append(T[] items, int offset, int count) {
         EnsureSize(_count + count);
         Array.Copy(items, offset, _items, _count, count);
         _count += count;
      }

      //============================================================
      // <T>追加对象数组。</T>
      //
      // @param items 对象数组
      //============================================================
      public void Append(T[] items) {
         Append(items, 0, items.Length);
      }

      //============================================================
      // <T>追加对象数组。</T>
      //
      // @param items 对象数组
      //============================================================
      public void Append(IObjects<T> items) {
         Append(items.Items, 0, items.Count);
      }

      //============================================================
      // <T>弹出第一个对象。</T>
      //
      // @return 对象
      //============================================================
      public T Shift() {
         // 检查是否存在对象
         InnerCheckExists();
         // 删除第一个对象
         T value = _items[0];
         Array.Copy(_items, 1, _items, 0, --_count);
         return value;
      }

      //============================================================
      // <T>尝试第一个对象。</T>
      //
      // @return 对象
      //============================================================
      public T TryShift() {
         // 检查长度
         if(0 == _count) {
            return default(T);
         }
         // 删除第一个对象
         T value = _items[0];
         Array.Copy(_items, 1, _items, 0, --_count);
         return value;
      }

      //============================================================
      // <T>追加对象到第一个位置。</T>
      //
      // @param value 对象
      //============================================================
      public void Unshift(T value) {
         EnsureSize(_count + 1);
         // 删除第一个对象
         Array.Copy(_items, 0, _items, 1, _count++);
         _items[0] = value;
      }

      //============================================================
      // <T>弹出最后一个对象。</T>
      //
      // @return 对象
      //============================================================
      public T Pop() {
         // 检查是否存在对象
         InnerCheckExists();
         // 删除最后一个对象
         return _items[--_count];
      }

      //============================================================
      // <T>尝试弹出最后一个对象。</T>
      //
      // @return 对象
      //============================================================
      public T TryPop() {
         // 检查长度
         if(0 == _count) {
            return default(T);
         }
         // 删除最后一个对象
         return _items[--_count];
      }

      //============================================================
      // <T>追加对象到尾位置。</T>
      //
      // @param value 对象
      // @return 追加位置
      //============================================================
      public virtual int Add(T value) {
         EnsureSize(_count + 1);
         int index = _count++;
         _items[index] = value;
         return index;
      }

      //============================================================
      // <T>在指定的索引位置插入新的对象。</T>
      //
      // @param index 索引位置
      // @param value 对象
      //============================================================
      public void Insert(int index, T value) {
         EnsureSize(_count + 1);
         Array.Copy(_items, index, _items, index + 1, _count - index);
         _items[index] = value;
         _count++;
      }

      //============================================================
      // <T>在指定的索引位置插入新的对象。</T>
      //
      // @param source 来源对象
      // @param target 目标对象
      //============================================================
      public void InsertBefore(T source, T target) {
         // 移除来源对象
         int sourceIndex = Array.IndexOf(_items, source);
         if(sourceIndex != -1) {
            Array.Copy(_items, sourceIndex + 1, _items, sourceIndex, _count - sourceIndex);
            _count--;
         }
         // 插入目标对象前
         int targetIndex = Array.IndexOf(_items, target);
         if(targetIndex != -1) {
            EnsureSize(_count + 1);
            Array.Copy(_items, targetIndex, _items, targetIndex + 1, _count - targetIndex);
            _items[targetIndex] = source;
            _count++;
         } else {
            Push(source);
         }
      }

      //============================================================
      // <T>在指定的索引位置插入新的对象。</T>
      //
      // @param source 来源对象
      // @param target 目标对象
      //============================================================
      public void InsertAfter(T source, T target) {
         // 移除来源对象
         int sourceIndex = Array.IndexOf(_items, source);
         if (sourceIndex != -1) {
            Array.Copy(_items, sourceIndex + 1, _items, sourceIndex, _count - sourceIndex);
            _count--;
         }
         // 插入目标对象后
         int targetIndex = Array.IndexOf(_items, target);
         if (targetIndex != -1) {
            EnsureSize(_count + 1);
            Array.Copy(_items, targetIndex + 1, _items, targetIndex + 2, _count - targetIndex - 1);
            _items[targetIndex + 1] = source;
            _count++;
         } else {
            Push(source);
         }
      }

      //============================================================
      // <T>追加对象到尾位置。</T>
      //
      // @param value 对象
      //============================================================
      public virtual void Push(T value) {
         EnsureSize(_count + 1);
         _items[_count++] = value;
      }

      //============================================================
      // <T>追加不重复的对象到尾位置。</T>
      //
      // @param value 对象
      //============================================================
      public virtual void PushUnique(T value) {
         if(!Contains(value)) {
            EnsureSize(_count + 1);
            _items[_count++] = value;
         }
      }

      //============================================================
      // <T>移除指定索引位置的对象。</T>
      //
      // @param index 索引位置
      // @return 对象
      //============================================================
      public virtual T Erase(int index) {
         // 检查范围
         InnerCheckRange(index);
         // 删除对象
         T item = _items[index];
         Array.Copy(_items, index + 1, _items, index, _count - index - 1);
         _count--;
         return item;
      }

      //============================================================
      // <T>尝试移除指定索引位置的对象。</T>
      //
      // @param index 索引位置
      // @return 对象
      //============================================================
      public T TryErase(int index) {
         if(index >= 0 && index < _count) {
            // 删除对象
            T item = _items[index];
            Array.Copy(_items, index + 1, _items, index, _count - index - 1);
            _count--;
            return item;
         }
         return default(T);
      }

      //============================================================
      // <T>移除指定对象。</T>
      // <P>如果未找到对象的话，产生例外。</P>
      //
      // @param value 对象
      //============================================================
      public virtual bool Remove(T value) {
         bool result = false;
         int count = 0;
         for(int n = 0; n < _count; n++) {
            if(!_items[n].Equals(value)) {
               _items[count++] = _items[n];
               result = true;
            }
         }
         _count = count;
         return result;
      }

      //============================================================
      // <T>替换对象中的内容为指定内容。</T>
      //
      // @param from 被替换内容
      // @param to 替换内容
      // @return 替换次数
      //============================================================
      public int Replace(T from, T to) {
         int count = 0;
         for(int n = 0; n < _count; n++) {
            T value = _items[n];
            if(null != value) {
               if(value.Equals(from)) {
                  _items[n] = to;
                  count++;
               }
            }
         }
         return count;
      }

      //============================================================
      // <T>反转所有对象。</T>
      //============================================================
      public void Reverse() {
         Array.Reverse(_items, 0, _count);
      }

      //============================================================
      // <T>对内部对象进行排序。</T>
      //============================================================
      public virtual void Sort() {
         Array.Sort(_items, 0, _count);
      }

      //============================================================
      // <T>对内部对象进行排序。</T>
      //
      // @param comparer 比较器
      //============================================================
      public void Sort(IComparer<T> comparer) {
         Array.Sort<T>(_items, 0, _count, comparer);
      }

      //============================================================
      // <T>刷新对象块到外部。</T>
      //
      // @return 对象数组
      //============================================================
      public T[] Flush() {
         T[] memory = new T[_count];
         Array.Copy(_items, 0, memory, 0, _count);
         _count = 0;
         Array.Clear(_items, 0, _items.Length);
         return memory;
      }

      //============================================================
      // <T>刷新对象块到外部。</T>
      //
      // @param items 对象数组
      //============================================================
      public void Flush(IObjects<T> items) {
         items.Assign(_items, 0, _count);
         Array.Clear(_items, 0, _items.Length);
         _count = 0;
      }

      //============================================================
      // <T>复制对象集合到新的内存块中。</T>
      //
      // @param position 内部起始位置
      // @param items 对象集合
      // @param offset 对象开始位置
      // @param count 对象复制个数
      //============================================================
      public int ToArray(int position, T[] items, int offset, int count) {
         int copy = Math.Min(count, _count - position);
         Array.Copy(_items, position, items, offset, copy);
         return copy;
      }

      //============================================================
      // <T>复制对象到新的内存块中。</T>
      //
      // @param offset 开始位置
      // @param count 对象
      //============================================================
      public T[] ToArray(int offset, int count) {
         T[] memory = new T[count];
         Array.Copy(_items, offset, memory, 0, count);
         return memory;
      }

      //============================================================
      // <T>复制对象到新的内存块中。</T>
      //
      // @param offset 开始位置
      // @param count 对象
      //============================================================
      public T[] ToArray() {
         T[] items = new T[_count];
         Array.Copy(_items, 0, items, 0, _count);
         return items;
      }

      //============================================================
      // <T>清除所有对象。</T>
      //============================================================
      public virtual void Clear() {
         _count = 0;
      }

      //============================================================
      // <T>清除所有数据内容。</T>
      //============================================================
      public virtual void Reset() {
         Array.Clear(_items, 0, _items.Length);
         _count = 0;
      }

      //============================================================
      // <T>释放所有对象。</T>
      //============================================================
      public virtual void Release() {
         Array.Clear(_items, 0, _items.Length);
         _items = new T[CAPABILITY];
         _count = 0;
      }

      //============================================================
      // <T>获得调试转储信息。</T>
      //
      // @implement IDump
      // @return 转储信息
      //============================================================
      public FDumpInfo Dump() {
         return Dump(new FDumpInfo(this));
      }

      //============================================================
      // <T>获得调试转储信息。</T>
      //
      // @implement IDump
      // @param info 转储信息
      // @return 转储信息
      //============================================================
      public virtual FDumpInfo Dump(FDumpInfo info) {
         info.Begin();
         info.Append(" count=", _count);
         // 追加所有项目
         info.Append("{");
         for(int n = 0; n < _count; n++) {
            // 追加分隔符
            if(n > 0) {
               info.Append(", ");
            }
            // 追加内容
            T value = _items[n];
            if(value is IDump) {
               ((IDump)value).Dump(info);
            } else {
               info.Append(value);
            }
         }
         info.Append("{");
         info.End();
         return info;
      }
   }
}
