using System;
using System.Collections;
using System.Collections.Generic;

namespace MO.Common.Lang
{
   //============================================================
   // <T>对象集合。</T>
   //============================================================
   public class FCollection : IList, ICollection, IEnumerable, IDump
   {
      // 初始化容量
      public const int CAPABILITY = 8;

      // 是否定长
      protected bool _fixed = false;

      // 是否只读
      protected bool _readOnly = false;

      // 对象内存
      protected object[] _items;

      // 对象个数
      protected int _count;

      //============================================================
      // <T>构造对象集合。</T>
      //============================================================
      public FCollection() {
         EnsureSize(CAPABILITY);
      }

      //============================================================
      // <T>构造对象集合。</T>
      //
      // @param capacity 初始化大小
      //============================================================
      public FCollection(int capacity) {
         EnsureSize(Math.Max(CAPABILITY, capacity));
      }

      //============================================================
      // <T>[内部] 检查是否存在对象。</T>
      // <P>如果为空则产生例外。</P>
      //============================================================
      protected void InnerCheckExists() {
         if (0 == _count) {
            throw new Exception("Memory is empty.");
         }
      }

      //============================================================
      // <T>[内部] 检查索引位置是否在合法范围内。</T>
      // <P>如果范围超出则产生例外。</P>
      //
      // @param index 索引位置
      //============================================================
      protected void InnerCheckRange(int index) {
         if (index < 0 || index >= _count) {
            throw new Exception("Index range error. (count=" + _count + ", index=" + index + ")");
         }
      }

      //============================================================
      // <T>获得大小。</T>
      //
      // @return 大小
      //============================================================
      public int Size {
         get { return _items.Length; }
      }

      //============================================================
      // <T>获得内存。</T>
      //
      // @return 内存
      //============================================================
      public object[] Items {
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
      // <T>判断是否和指定对象数组相等。</T>
      //
      // @param items 对象数组
      // @param offset 开始位置
      // @param count 对象个数
      // @return 是否相等
      //============================================================
      public bool Equals(object[] items, int offset, int count) {
         // 检查数组是否为空
         if (null == items) {
            return false;
         }
         // 检查长度
         if (_count != count) {
            return false;
         }
         // 检查对象
         for (int n = 0; n < count; n++) {
            if (!items[n].Equals(_items[n])) {
               return false;
            }
         }
         return true;
      }

      //============================================================
      // <T>判断是否和指定对象数组相等。</T>
      //
      // @param items 对象数组
      // @return 是否相等
      //============================================================
      public bool Equals(object[] items) {
         // 检查数组是否为空
         if (null == items) {
            return false;
         }
         // 检查对象是否相等
         return Equals(items, 0, items.Length);
      }

      //============================================================
      // <T>判断是否和指定对象数组相等。</T>
      //
      // @param items 对象数组
      // @return 是否相等
      //============================================================
      public bool Equals(FCollection items) {
         // 检查数组是否为空
         if (null == items) {
            return false;
         }
         // 检查对象是否相等
         return Equals(items._items, 0, items._count);
      }

      //============================================================
      // <T>查找对象的位置。</T>
      // <P>如果不存在则返回-1。</P>
      //
      // @param value 对象
      // @param offset 开始位置
      // @return 所在位置
      //============================================================
      public int IndexOf(object value, int offset) {
         return Array.IndexOf<object>(_items, value, offset, _count);
      }

      //============================================================
      // <T>反向查找对象的位置。</T>
      // <P>如果不存在则返回-1。</P>
      //
      // @param value 对象
      // @param offset 开始位置
      // @return 所在位置
      //============================================================
      public int LastIndexOf(object value, int offset) {
         return Array.LastIndexOf<object>(_items, value, offset, _count);
      }

      //============================================================
      // <T>反向查找对象的位置。</T>
      // <P>如果不存在则返回-1。</P>
      //
      // @param value 对象
      // @return 所在位置
      //============================================================
      public int LastIndexOf(object value) {
         return LastIndexOf(value, 0);
      }

      //============================================================
      // <T>查找对象数组的位置。</T>
      // <P>如果不存在则返回-1。</P>
      //
      // @param items 对象数组
      // @param offset 开始位置
      // @param count 对象个数
      // @return 所在位置
      //============================================================
      public int Find(object[] items, int offset, int count) {
         return RArray<object>.Find(_items, 0, _count, items, offset, count);
      }

      //============================================================
      // <T>查找对象数组的位置。</T>
      // <P>如果不存在则返回-1。</P>
      //
      // @param items 对象数组
      // @return 所在位置
      //============================================================
      public int Find(object[] items) {
         return RArray<object>.Find(_items, 0, _count, items, 0, items.Length);
      }

      //============================================================
      // <T>查找对象数组的位置。</T>
      // <P>如果不存在则返回-1。</P>
      //
      // @param items 对象数组
      // @return 所在位置
      //============================================================
      public int Find(FCollection items) {
         return RArray<object>.Find(_items, 0, _count, items._items, 0, items._count);
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
      // <T>获得首对象。</T>
      //
      // @return 对象
      //============================================================
      public object First {
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
      public object NvlFirst {
         get { return (_count > 0) ? _items[0] : null; }
      }

      //============================================================
      // <T>获得尾对象。</T>
      //
      // @return 对象
      //============================================================
      public object Last {
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
      public object NvlLast {
         get { return (_count > 0) ? _items[_count - 1] : null; }
      }

      //============================================================
      // <T>获得索引位置的对象。</T>
      // <P>如果不存在产生例外。</P>
      //
      // @param index 索引位置
      // @return 对象
      //============================================================
      public object Get(int index) {
         // 检查范围
         InnerCheckRange(index);
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
      public object TryGet(int index) {
         // 检查范围
         if (index < 0 || index >= _count) {
            return null;
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
      public void Set(int index, object value) {
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
      public void TrySet(int index, object value) {
         if (index >= 0 && index < _count) {
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
      public void SetExtend(int index, object value) {
         if (index >= 0 && index < _count) {
            // 设置原有对象
            _items[index] = value;
         } else if (index >= _count) {
            // 扩充容量
            EnsureSize(index + 1);
            _items[index] = value;
            _count = index + 1;
         }
      }

      //============================================================
      // <T>确保容量。</T>
      // <P>如果内存比指定内存大，则重新收集内存。</P>
      //
      // @param size 容量大小
      //============================================================
      public void EnsureSize(int size) {
         if (null == _items) {
            _items = new object[size];
         } else if (size > _items.Length) {
            // 计算新尺寸
            size += _items.Length;
            // 创建新内存
            object[] items = new object[size];
            Array.Copy(_items, 0, items, 0, _count);
            _items = items;
         }
      }

      //============================================================
      // <T>接收对象数组。</T>
      //
      // @param items 对象数组
      // @param offset 开始位置
      // @param count 对象个数
      //============================================================
      public void Assign(object[] items, int offset, int count) {
         EnsureSize(count);
         Array.Copy(items, offset, _items, 0, count);
         _count = count;
      }

      //============================================================
      // <T>接收对象数组。</T>
      //
      // @param items 对象数组
      //============================================================
      public void Assign(object[] items) {
         Assign(items, 0, items.Length);
      }

      //============================================================
      // <T>接收对象数组。</T>
      //
      // @param items 对象数组
      //============================================================
      public void Assign(FCollection items) {
         Assign(items._items, 0, items._count);
      }

      //============================================================
      // <T>追加对象数组。</T>
      //
      // @param items 对象数组
      // @param offset 开始位置
      // @param count 对象个数
      //============================================================
      public void Append(object[] items, int offset, int count) {
         EnsureSize(_count + count);
         Array.Copy(items, offset, _items, _count, count);
         _count += count;
      }

      //============================================================
      // <T>追加对象数组。</T>
      //
      // @param items 对象数组
      //============================================================
      public void Append(object[] items) {
         Append(items, 0, items.Length);
      }

      //============================================================
      // <T>追加对象数组。</T>
      //
      // @param items 对象数组
      //============================================================
      public void Append(FCollection items) {
         Append(items._items, 0, items._count);
      }

      //============================================================
      // <T>弹出第一个对象。</T>
      //
      // @return 对象
      //============================================================
      public object Shift() {
         // 检查是否存在对象
         InnerCheckExists();
         // 删除第一个对象
         object value = _items[0];
         Array.Copy(_items, 1, _items, 0, --_count);
         return value;
      }

      //============================================================
      // <T>尝试第一个对象。</T>
      //
      // @return 对象
      //============================================================
      public object TryShift() {
         // 检查长度
         if (0 == _count) {
            return null;
         }
         // 删除第一个对象
         object value = _items[0];
         Array.Copy(_items, 1, _items, 0, --_count);
         return value;
      }

      //============================================================
      // <T>追加对象到第一个位置。</T>
      //
      // @param value 对象
      //============================================================
      public void Unshift(object value) {
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
      public object Pop() {
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
      public object TryPop() {
         // 检查长度
         if (0 == _count) {
            return null;
         }
         // 删除最后一个对象
         return _items[--_count];
      }

      //============================================================
      // <T>追加对象到尾位置。</T>
      //
      // @param value 对象
      //============================================================
      public virtual void Push(object value) {
         EnsureSize(_count + 1);
         _items[_count++] = value;
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
      public void Sort(IComparer<object> comparer) {
         Array.Sort<object>(_items, 0, _count, comparer);
      }

      //============================================================
      // <T>反转所有对象。</T>
      //============================================================
      public void Reverse() {
         Array.Reverse(_items, 0, _count);
      }

      //============================================================
      // <T>替换对象中的内容为指定内容。</T>
      //
      // @param from 被替换内容
      // @param to 替换内容
      // @return 替换次数
      //============================================================
      public int Replace(object from, object to) {
         int count = 0;
         for (int n = 0; n < _count; n++) {
            object value = _items[n];
            if (null != value) {
               if (value.Equals(from)) {
                  _items[n] = to;
                  count++;
               }
            }
         }
         return count;
      }

      //============================================================
      // <T>移除指定索引位置的对象。</T>
      //
      // @param index 索引位置
      // @return 对象
      //============================================================
      public object Remove(int index) {
         // 检查范围
         InnerCheckRange(index);
         // 删除对象
         object item = _items[index];
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
      public object TryRemove(int index) {
         if (index >= 0 && index < _count) {
            // 删除对象
            object item = _items[index];
            Array.Copy(_items, index + 1, _items, index, _count - index - 1);
            _count--;
            return item;
         }
         return null;
      }

      //============================================================
      // <T>刷新对象块到外部。</T>
      //
      // @return 对象数组
      //============================================================
      public object[] Flush() {
         object[] memory = new object[_count];
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
      public void Flush(FCollection items) {
         items.Assign(_items, 0, _count);
         Array.Clear(_items, 0, _items.Length);
         _count = 0;
      }

      //============================================================
      // <T>复制对象到新的内存块中。</T>
      //
      // @param offset 开始位置
      // @param count 对象
      //============================================================
      public object[] ToArray(int offset, int count) {
         object[] memory = new object[count];
         Array.Copy(_items, offset, memory, 0, count);
         return memory;
      }

      //============================================================
      // <T>复制对象到新的内存块中。</T>
      //
      // @param offset 开始位置
      // @param count 对象
      //============================================================
      public object[] ToArray() {
         object[] memory = new object[_count];
         Array.Copy(_items, 0, memory, 0, _count);
         return memory;
      }

      #region IList members

      //============================================================
      // <T>获得是否定长。</T>
      //
      // @return 是否定长
      //============================================================
      public bool IsFixedSize {
         get { return _fixed; }
      }

      //============================================================
      // <T>获得是否只读。</T>
      //
      // @return 是否只读
      //============================================================
      public bool IsReadOnly {
         get { return _readOnly; }
      }

      //============================================================
      // <T>获得或设定索引位置的对象。</T>
      //
      // @param index 索引位置
      //============================================================
      public object this[int index] {
         get { return Get(index); }
         set { Set(index, value); }
      }

      //============================================================
      // <T>增加一个内容。</T>
      //
      // @param value 内容
      // @return 追加位置
      //============================================================
      public int Add(object value) {
         EnsureSize(_count + 1);
         _items[_count] = value;
         return ++_count;
      }

      //============================================================
      // <T>清除所有对象。</T>
      //============================================================
      public void Clear() {
         Array.Clear(_items, 0, _items.Length);
         _count = 0;
      }

      //============================================================
      // <T>判断是否含有指定内容。</T>
      //
      // @param value 对象内容
      // @return 是否相等
      //============================================================
      public bool Contains(object value) {
         return (-1 != IndexOf(value));
      }

      //============================================================
      // <T>查找对象的位置。</T>
      // <P>如果不存在则返回-1。</P>
      //
      // @param value 对象
      // @return 所在位置
      //============================================================
      public int IndexOf(object value) {
         return IndexOf(value, 0);
      }

      //============================================================
      // <T>在指定位置插入一个内容。</T>
      //
      // @param index 索引
      // @param value 内容
      //============================================================
      public void Insert(int index, object value) {
         int length = _count - index;
         EnsureSize(_count + 1);
         Array.Copy(_items, index, _items, index + 1, length);
         _count++;
      }

      //============================================================
      // <T>移除指定对象。</T>
      //
      // @param value 对象
      //============================================================
      public void Remove(object value) {
         int count = 0;
         for (int n = 0; n < _count; n++) {
            if (!_items[n].Equals(value)) {
               _items[count++] = _items[n];
            }
         }
         _count = count;
      }

      //============================================================
      // <T>移除指定索引位置的对象。</T>
      //
      // @param index 索引位置
      //============================================================
      public void RemoveAt(int index) {
         int length = _count - index;
         Array.Copy(_items, index + 1, _items, index, length);
         _count--;
      }

      #endregion

      #region ICollection members

      //============================================================
      // <T>获得长度。</T>
      //
      // @return 长度
      //============================================================
      public int Count {
         get { return _count; }
      }

      //============================================================
      // <T>是否支持同步。</T>
      //
      // @return 是否支持同步
      //============================================================
      public bool IsSynchronized {
         get { return false; }
      }

      //============================================================
      // <T>获得同步对象。</T>
      //
      // @return 同步对象
      //============================================================
      public object SyncRoot {
         get { return this; }
      }

      //============================================================
      // <T>复制数据到数组对象中。</T>
      //
      // @param array 数组对象
      // @param index 索引位置
      //============================================================
      public void CopyTo(Array array, int index) {
         Array.Copy(_items, 0, array, index, _count - index);
      }

      #endregion

      #region IEnumerable members

      //============================================================
      // <T>获得枚举器。</T>
      //============================================================
      public IEnumerator GetEnumerator() {
         return new FObjectsEnumerator<object>(_items, 0, _count);
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
         info.Append(" count=", _count);
         // 追加所有项目
         info.Append("{");
         for (int n = 0; n < _count; n++) {
            // 追加分隔符
            if (n > 0) {
               info.Append(", ");
            }
            // 追加内容
            object value = _items[n];
            if (value is IDump) {
               ((IDump)value).Dump(info);
            } else {
               info.Append(value);
            }
         }
         info.Append("{");
         info.End();
         return info;
      }
      #endregion
   }
}
