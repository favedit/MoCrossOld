using System;
using System.Collections;
using System.Windows.Forms;
using MO.Common.Lang;

namespace MO.Core.Forms.Common
{
   //============================================================
   // <T>基础集合。</T>
   //============================================================
   public class FBaseCollection<T> : BaseCollection, IBaseCollection<T>
   {
      public delegate void HEvent(T value);

      public event HEvent OnAdd;

      public event HEvent OnRemove;

      protected FObjects<T> _collection = new FObjects<T>();

      //============================================================
      // <T>判断是否为空。</T>
      //
      // @return 是否为空
      //============================================================
      public bool IsEmpty {
         get { return _collection.IsEmpty(); }
      }
      
      //============================================================
      // <T>判断是否固定大小。</T>
      //
      // @return 是否固定大小
      //============================================================
      public bool IsFixedSize {
         get { return false; }
      }

      //============================================================
      // <T>获得对象总数。</T>
      //
      // @return 对象总数
      //============================================================
      public override int Count {
         get { return _collection.Count; }
      }

      //============================================================
      // <T>判断是否含有指定对象。</T>
      //
      // @param value 对象
      // @return 是否含有指定对象
      //============================================================
      public bool Contains(object value) {
         return _collection.Contains((T)value);
      }

      //============================================================
      // <T>查找指定对象的索引位置。</T>
      //
      // @param value 对象
      // @return 索引位置
      //============================================================
      public int IndexOf(object value) {
         return _collection.IndexOf((T)value);
      }

      //============================================================
      // <T>获得或设置指定索引位置的对象。</T>
      //
      // @param index 索引位置
      //============================================================
      public object this[int index] {
         get { return _collection.Get(index); }
         set { _collection.Set(index, (T)value); }
      }

      //============================================================
      // <T>获得对象内存。</T>
      //
      // @return 对象内存
      //============================================================
      public T[] Items {
         get { return _collection.Items; }
      }

      //============================================================
      // <T>获得迭代器。</T>
      //============================================================
      public new IEnumerator GetEnumerator() {
         return _collection.GetEnumerator();
      }

      //============================================================
      // <T>追加对象到当前集合中。</T>
      //
      // @param value 对象
      // @return 索引位置
      //============================================================
      public virtual int Add(object value) {
         T item = (T)value;
         if(null != OnAdd) {
            OnAdd(item);
         }
         return _collection.Add(item);
      }

      //============================================================
      // <T>追加对象到当前集合中。</T>
      //
      // @param value 对象
      // @return 索引位置
      //============================================================
      public virtual int Add(T item) {
         if(null != OnAdd) {
            OnAdd(item);
         }
         return _collection.Add(item);
      }

      //============================================================
      // <T>追加对象集合到当前集合中。</T>
      //
      // @param values 对象集合
      //============================================================
      public virtual void AddRange(params object[] values) {
         foreach(object value in values) {
            Add(value);
         }
      }

      //============================================================
      // <T>追加对象集合到当前集合中。</T>
      //
      // @param values 对象集合
      //============================================================
      public virtual void AddRange(params T[] items) {
         foreach(T item in items) {
            Add(item);
         }
      }

      //============================================================
      // <T>追加对象到尾位置。</T>
      //
      // @param value 对象
      //============================================================
      public virtual void Push(T value) {
         _collection.Push(value);
      }
      
      //============================================================
      // <T>在指定的索引位置插入对象。</T>
      //
      // @param index 索引位置
      // @param value 对象
      //============================================================
      public void Insert(int index, object value) {
         _collection.Insert(index, (T)value);
      }

      //============================================================
      // <T>在指定的索引位置插入对象。</T>
      //
      // @param index 索引位置
      // @param value 对象
      //============================================================
      public void Insert(int index, T value) {
         _collection.Insert(index, value);
      }

      //============================================================
      // <T>移除指定的对象。</T>
      //
      // @param value 对象
      //============================================================
      public virtual void Remove(object value) {
         T item = (T)value;
         if(null != OnRemove) {
            OnRemove(item);
         }
         _collection.Remove(item);
      }

      //============================================================
      // <T>移除指定的对象。</T>
      //
      // @param value 对象
      //============================================================
      public virtual void Remove(T item) {
         if(null != OnRemove) {
            OnRemove(item);
         }
         _collection.Remove(item);
      }

      //============================================================
      // <T>移除指定的索引位置的对象。</T>
      //
      // @param index 索引位置
      //============================================================
      public void RemoveAt(int index) {
         _collection.Erase(index);
      }

      //============================================================
      // <T>复制数据到指定数组集合。</T>
      //
      // @param array 数组集合
      // @param index 索引位置
      //============================================================
      public new void CopyTo(Array array, int index) {
         Array.Copy(_collection.Items, 0, array, index, _collection.Count);
      }

      //============================================================
      // <T>清除所有对象集合。</T>
      //============================================================
      public void Clear() {
         _collection.Clear();
      }
   }
}
