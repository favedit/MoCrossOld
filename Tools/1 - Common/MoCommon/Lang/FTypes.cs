using System;
using System.Collections.Generic;
using System.Text;
using Collections = System.Collections;

namespace MO.Common.Lang
{
   //============================================================
   // <T>类型集合。</T>
   //
   // @author MAOCY 120223
   //============================================================
   public class FTypes<T> : FObject, IEnumerable<T>, IDisposable
   {
      // 初始化容量
      public const int CAPABILITY = 32;

      // 类型内存
      protected T[] _memory;

      // 类型个数
      protected int _length;

      //============================================================
      // <T>构造类型集合。</T>
      //============================================================
      public FTypes() {
         EnsureSize(CAPABILITY);
      }

      //============================================================
      // <T>构造类型集合。</T>
      //
      // @param capacity 初始化大小
      //============================================================
      public FTypes(int capacity) {
         EnsureSize(Math.Max(CAPABILITY, capacity));
      }

      //============================================================
      // <T>构造类型集合。</T>
      //
      // @param memory 类型集合
      // @param offset 开始位置
      // @param length 类型个数
      //============================================================
      public FTypes(T[] memory, int offset, int length) {
         EnsureSize(Math.Max(CAPABILITY, length));
         Append(memory, offset, length);
      }

      //============================================================
      // <T>检查是否存在类型。</T>
      // <P>如果为空则产生例外。</P>
      //
      // @inner
      //============================================================
      protected void InnerCheckExists() {
         if(0 == _length) {
            throw new Exception("Type is not exists.");
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
         if(index < 0 || index >= _length) {
            throw new Exception("Index range error. (length=" + _length + ", index=" + index + ")");
         }
      }

      //============================================================
      // <T>获得或设定索引位置的类型。</T>
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
         get { return _memory.Length; }
      }

      //============================================================
      // <T>获得长度。</T>
      //
      // @return 长度
      //============================================================
      public int Length {
         get { return _length; }
      }

      //============================================================
      // <T>获得内存。</T>
      //
      // @return 内存
      //============================================================
      public T[] Memory {
         get { return _memory; }
      }

      //============================================================
      // <T>判断是否为空。</T>
      //
      // @return 是否为空
      //============================================================
      public bool IsEmpty {
         get { return (0 == _length); }
      }

      //============================================================
      // <T>判断是否含有指定内容。</T>
      //
      // @param value 类型内容
      // @return 是否相等
      //============================================================
      public bool Contains(T value) {
         int index = IndexOf(value);
         return (-1 != index);
      }

      //============================================================
      // <T>判断是否和指定类型集合相等。</T>
      //
      // @param memory 类型集合
      // @return 是否相等
      //============================================================
      public bool Equals(T[] memory) {
         // 检查数组是否为空
         if(null == memory) {
            return false;
         }
         // 检查类型是否相等
         return Equals(memory, 0, memory.Length);
      }

      //============================================================
      // <T>判断是否和指定类型数组相等。</T>
      //
      // @param memory 类型数组
      // @param offset 开始位置
      // @param length 类型个数
      // @return 是否相等
      //============================================================
      public bool Equals(T[] memory, int offset, int length) {
         // 检查数组是否为空
         if(null == memory) {
            return false;
         }
         // 检查长度
         if(_length != length) {
            return false;
         }
         // 检查相等
         for(int n = 0; n < length; n++) {
            if(!_memory[n].Equals(memory[offset++])) {
               return false;
            }
         }
         return true;
      }

      //============================================================
      // <T>判断是否和指定类型集合相等。</T>
      //
      // @param memory 类型集合
      // @return 是否相等
      //============================================================
      public bool Equals(FTypes<T> memory) {
         // 检查数组是否为空
         if(null == memory) {
            return false;
         }
         // 检查类型是否相等
         return Equals(memory.Memory, 0, memory.Length);
      }

      //============================================================
      // <T>查找类型的位置。</T>
      // <P>如果不存在则返回-1。</P>
      //
      // @param value 类型
      // @return 所在位置
      //============================================================
      public int IndexOf(T value) {
         return Array.IndexOf<T>(_memory, value, 0, _length);
      }

      //============================================================
      // <T>查找类型的位置。</T>
      // <P>如果不存在则返回-1。</P>
      //
      // @param value 类型
      // @param offset 开始位置
      // @return 所在位置
      //============================================================
      public int IndexOf(T value, int offset) {
         return Array.IndexOf<T>(_memory, value, offset, _length);
      }

      //============================================================
      // <T>反向查找类型的位置。</T>
      // <P>如果不存在则返回-1。</P>
      //
      // @param value 类型
      // @return 所在位置
      //============================================================
      public int LastIndexOf(T value) {
         return Array.LastIndexOf<T>(_memory, value, 0, _length);
      }

      //============================================================
      // <T>反向查找类型的位置。</T>
      // <P>如果不存在则返回-1。</P>
      //
      // @param value 类型
      // @param offset 开始位置
      // @return 所在位置
      //============================================================
      public int LastIndexOf(T value, int offset) {
         return Array.LastIndexOf<T>(_memory, value, offset, _length);
      }

      //============================================================
      // <T>查找指定类型集合的位置。</T>
      // <P>如果不存在则返回-1。</P>
      //
      // @param memory 类型集合
      // @return 所在位置
      //============================================================
      public int Find(T[] memory) {
         return RArray<T>.Find(_memory, 0, _length, memory, 0, memory.Length);
      }

      //============================================================
      // <T>查找指定类型集合的位置。</T>
      // <P>如果不存在则返回-1。</P>
      //
      // @param memory 类型集合
      // @param offset 开始位置
      // @param length 类型个数
      // @return 所在位置
      //============================================================
      public int Find(T[] memory, int offset, int length) {
         return RArray<T>.Find(_memory, 0, _length, memory, offset, length);
      }

      //============================================================
      // <T>查找类型数组的位置。</T>
      // <P>如果不存在则返回-1。</P>
      //
      // @param memory 类型数组
      // @return 所在位置
      //============================================================
      public int Find(FTypes<T> memory) {
         return RArray<T>.Find(_memory, 0, _length, memory.Memory, 0, memory.Length);
      }

      //============================================================
      // <T>获得首类型。</T>
      //
      // @return 类型
      //============================================================
      public T First {
         get {
            // 检查是否存在类型
            InnerCheckExists();
            // 获得类型
            return _memory[0];
         }
      }

      //============================================================
      // <T>查找首类型。</T>
      //
      // @param length 类型个数
      //============================================================
      public T NvlFirst {
         get { return (_length > 0) ? _memory[0] : default(T); }
      }

      //============================================================
      // <T>获得尾类型。</T>
      //
      // @return 类型
      //============================================================
      public T Last {
         get {
            // 检查是否存在类型
            InnerCheckExists();
            // 获得类型
            return _memory[_length - 1];
         }
      }

      //============================================================
      // <T>查找尾类型。</T>
      //
      // @return 类型
      //============================================================
      public T NvlLast {
         get { return (_length > 0) ? _memory[_length - 1] : default(T); }
      }

      //============================================================
      // <T>获得类型枚举器。</T>
      //
      // @implement IEnumerable<T>
      //============================================================
      public IEnumerator<T> GetEnumerator() {
         return new FTypesEnumerator<T>(_memory, 0, _length);
      }

      //============================================================
      // <T>获得类型枚举器。</T>
      //
      // @implement IEnumerable<T>
      //============================================================
      Collections.IEnumerator Collections.IEnumerable.GetEnumerator() {
         return new FTypesEnumerator<T>(_memory, 0, _length);
      }

      //============================================================
      // <T>确保容量大小。</T>
      // <P>如果内存比指定内存大，则重新收集内存，并且保留原来数据。</P>
      //
      // @param size 容量大小
      //============================================================
      public void EnsureSize(int size) {
         if(null == _memory) {
            _memory = new T[size];
         } else if(size > _memory.Length) {
            // 计算新缓冲内存的大小
            size += _memory.Length;
            // 创建新缓冲内存
            T[] memory = new T[size];
            Array.Copy(_memory, 0, memory, 0, _length);
            _memory = memory;
         }
      }

      //============================================================
      // <T>获得索引位置的类型。</T>
      // <P>如果不存在产生例外。</P>
      //
      // @param index 索引位置
      // @return 类型
      //============================================================
      public T Get(int index) {
         // 检查范围
         InnerCheckRange(index);
         // 返回内容
         return _memory[index];
      }

      //============================================================
      // <T>尝试获得索引位置的类型。</T>
      // <P>如果不存在则返回该类型的默认值。</P>
      //
      // @param index 索引位置
      // @return 类型
      //============================================================
      public T TryGet(int index) {
         // 检查范围
         if(index < 0 || index >= _length) {
            return default(T);
         }
         // 返回内容
         return _memory[index];
      }

      //============================================================
      // <T>设置索引位置的类型。</T>
      // <P>如果不存在产生例外。</P>
      //
      // @param index 索引位置
      // @param value 类型
      //============================================================
      public void Set(int index, T value) {
         // 检查范围
         InnerCheckRange(index);
         // 设置内容
         _memory[index] = value;
      }

      //============================================================
      // <T>尝试设置索引位置的类型。</T>
      // <P>如果不存在则不设置内容。</P>
      //
      // @param index 索引位置
      // @param value 类型
      //============================================================
      public void TrySet(int index, T value) {
         if(index >= 0 && index < _length) {
            _memory[index] = value;
         }
      }

      //============================================================
      // <T>设置索引位置的类型。</T>
      // <P>如果不存在则扩展类型大小，设置内容。</P>
      //
      // @param index 索引位置
      // @param value 类型
      //============================================================
      public void SetExtend(int index, T value) {
         if(index >= 0 && index < _length) {
            // 设置原有类型
            _memory[index] = value;
         } else if(index >= _length) {
            // 扩充容量
            EnsureSize(index + 1);
            _memory[index] = value;
            _length = index + 1;
         }
      }

      //============================================================
      // <T>设置类型个数。</T>
      //
      // @param length 类型个数
      //============================================================
      public void SetLength(int length) {
         EnsureSize(length);
         _length = length;
      }

      //============================================================
      // <T>接收类型数组。</T>
      //
      // @param memory 类型数组
      // @param offset 开始位置
      // @param length 类型个数
      //============================================================
      public void Assign(T[] memory, int offset, int length) {
         EnsureSize(length);
         Array.Copy(memory, offset, _memory, 0, length);
         _length = length;
      }

      //============================================================
      // <T>接收类型数组。</T>
      //
      // @param memory 类型数组
      //============================================================
      public void Assign(T[] memory) {
         Assign(memory, 0, memory.Length);
      }

      //============================================================
      // <T>接收类型数组。</T>
      //
      // @param memory 类型数组
      //============================================================
      public void Assign(FTypes<T> memory) {
         Assign(memory.Memory, 0, memory.Length);
      }

      //============================================================
      // <T>追加类型数组。</T>
      //
      // @param memory 类型数组
      // @param offset 开始位置
      // @param length 类型个数
      //============================================================
      public void Append(T[] memory, int offset, int length) {
         EnsureSize(_length + length);
         Array.Copy(memory, offset, _memory, _length, length);
         _length += length;
      }

      //============================================================
      // <T>追加类型数组。</T>
      //
      // @param memory 类型数组
      //============================================================
      public void Append(T[] memory) {
         Append(memory, 0, memory.Length);
      }

      //============================================================
      // <T>追加类型数组。</T>
      //
      // @param memory 类型数组
      //============================================================
      public void Append(FTypes<T> memory) {
         Append(memory.Memory, 0, memory.Length);
      }

      //============================================================
      // <T>弹出第一个类型。</T>
      //
      // @return 类型
      //============================================================
      public T Shift() {
         // 检查是否存在类型
         InnerCheckExists();
         // 删除第一个类型
         T value = _memory[0];
         Array.Copy(_memory, 1, _memory, 0, --_length);
         return value;
      }

      //============================================================
      // <T>尝试第一个类型。</T>
      //
      // @return 类型
      //============================================================
      public T TryShift() {
         // 检查长度
         if(0 == _length) {
            return default(T);
         }
         // 删除第一个类型
         T value = _memory[0];
         Array.Copy(_memory, 1, _memory, 0, --_length);
         return value;
      }

      //============================================================
      // <T>追加类型到第一个位置。</T>
      //
      // @param value 类型
      //============================================================
      public void Unshift(T value) {
         EnsureSize(_length + 1);
         // 删除第一个类型
         Array.Copy(_memory, 0, _memory, 1, _length++);
         _memory[0] = value;
      }

      //============================================================
      // <T>弹出最后一个类型。</T>
      //
      // @return 类型
      //============================================================
      public T Pop() {
         // 检查是否存在类型
         InnerCheckExists();
         // 删除最后一个类型
         return _memory[--_length];
      }

      //============================================================
      // <T>尝试弹出最后一个类型。</T>
      //
      // @return 类型
      //============================================================
      public T TryPop() {
         // 检查长度
         if(0 == _length) {
            return default(T);
         }
         // 删除最后一个类型
         return _memory[--_length];
      }

      //============================================================
      // <T>追加类型到尾位置。</T>
      //
      // @param value 类型
      //============================================================
      public void Add(T value) {
         EnsureSize(_length + 1);
         _memory[_length++] = value;
      }

      //============================================================
      // <T>移除指定索引位置的类型。</T>
      //
      // @param index 索引位置
      // @return 类型
      //============================================================
      public virtual T Erase(int index) {
         // 检查范围
         InnerCheckRange(index);
         // 删除类型
         T item = _memory[index];
         Array.Copy(_memory, index + 1, _memory, index, _length - index - 1);
         _length--;
         return item;
      }

      //============================================================
      // <T>尝试移除指定索引位置的类型。</T>
      //
      // @param index 索引位置
      // @return 类型
      //============================================================
      public T TryErase(int index) {
         if(index >= 0 && index < _length) {
            // 删除类型
            T item = _memory[index];
            Array.Copy(_memory, index + 1, _memory, index, _length - index - 1);
            _length--;
            return item;
         }
         return default(T);
      }

      //============================================================
      // <T>移除指定类型。</T>
      // <P>如果未找到类型的话，产生例外。</P>
      //
      // @param value 类型
      //============================================================
      public bool Remove(T value) {
         bool result = false;
         int length = 0;
         for(int n = 0; n < _length; n++) {
            if(!_memory[n].Equals(value)) {
               _memory[length++] = _memory[n];
               result = true;
            }
         }
         _length = length;
         return result;
      }

      //============================================================
      // <T>替换类型中的内容为指定内容。</T>
      //
      // @param from 被替换内容
      // @param to 替换内容
      // @return 替换次数
      //============================================================
      public int Replace(T from, T to) {
         int length = 0;
         for(int n = 0; n < _length; n++) {
            T value = _memory[n];
            if(null != value) {
               if(value.Equals(from)) {
                  _memory[n] = to;
                  length++;
               }
            }
         }
         return length;
      }

      //============================================================
      // <T>反转所有类型。</T>
      //============================================================
      public void Reverse() {
         Array.Reverse(_memory, 0, _length);
      }

      //============================================================
      // <T>对内部类型进行排序。</T>
      //============================================================
      public virtual void Sort() {
         Array.Sort(_memory, 0, _length);
      }

      //============================================================
      // <T>对内部类型进行排序。</T>
      //
      // @param comparer 比较器
      //============================================================
      public void Sort(IComparer<T> comparer) {
         Array.Sort<T>(_memory, 0, _length, comparer);
      }

      //============================================================
      // <T>刷新类型块到外部。</T>
      //
      // @return 类型数组
      //============================================================
      public T[] Flush() {
         T[] memory = new T[_length];
         Array.Copy(_memory, 0, memory, 0, _length);
         _length = 0;
         Array.Clear(_memory, 0, _memory.Length);
         return memory;
      }

      //============================================================
      // <T>刷新类型块到外部。</T>
      //
      // @param memory 类型数组
      //============================================================
      public void Flush(FTypes<T> memory) {
         memory.Assign(_memory, 0, _length);
         Array.Clear(_memory, 0, _memory.Length);
         _length = 0;
      }

      //============================================================
      // <T>复制类型到新的内存块中。</T>
      //
      // @param offset 开始位置
      // @param length 类型
      //============================================================
      public T[] ToArray() {
         T[] memory = new T[_length];
         Array.Copy(_memory, 0, memory, 0, _length);
         return memory;
      }

      //============================================================
      // <T>复制类型到新的内存块中。</T>
      //
      // @param offset 开始位置
      // @param length 类型
      //============================================================
      public T[] ToArray(int offset, int length) {
         T[] memory = new T[length];
         Array.Copy(_memory, offset, memory, 0, length);
         return memory;
      }

      //============================================================
      // <T>复制类型到新的内存块中。</T>
      //
      // @param position 内部起始位置
      // @param memory 类型集合
      // @param offset 类型开始位置
      // @param length 类型复制长度
      //============================================================
      public int ToArray(int position, T[] memory, int offset, int length) {
         int copy = Math.Min(length, _length - position);
         Array.Copy(_memory, position, memory, offset, copy);
         return copy;
      }

      //============================================================
      // <T>清除所有数据内容。</T>
      //============================================================
      public virtual void Reset() {
         Array.Clear(_memory, 0, _memory.Length);
         _length = 0;
      }

      //============================================================
      // <T>清除所有类型。</T>
      //============================================================
      public virtual void Clear() {
         _length = 0;
      }

      //============================================================
      // <T>释放所有类型。</T>
      //============================================================
      public virtual void Release() {
         Array.Clear(_memory, 0, _memory.Length);
         _memory = new T[CAPABILITY];
         _length = 0;
      }

      //============================================================
      // <T>获得字符串信息。</T>
      //
      // @return 字符串信息
      //============================================================
      public override string ToString(){
         StringBuilder result = new StringBuilder();
         result.Append(_length);
         // 追加所有项目
         result.Append("{");
         for(int n = 0; n < _length; n++) {
            // 追加分隔符
            if(n > 0) {
               result.Append(", ");
            }
            // 追加内容
            result.Append(_memory[n]);
         }
         result.Append("{");
         return result.ToString();
      }

      //============================================================
      // <T>释放资源。</T>
      //============================================================
      public virtual void Dispose() {
         Array.Clear(_memory, 0, _memory.Length);
         _memory = null;
         _length = 0;
      }
   }
}
