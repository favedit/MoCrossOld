using System.Collections;
using MO.Common.Lang;

namespace MO.Common.Collection
{
   //============================================================
   // <T>链表集合。</T>
   //============================================================
   public class FList<T> : IEnumerable, IDump
   {
      // 总数
      protected int _count;

      // 首对象
      protected FListEntry<T> _first;

      // 尾对象
      protected FListEntry<T> _last;

      //============================================================
      // <T>构造链表集合。</T>
      //============================================================
      public FList() {
      }

      //============================================================
      // <T>移除链表中的一个入口对象。</T>
      //
      // @param entry 入口对象
      //============================================================
      protected void InnerRemove(FListEntry<T> entry) {
         FListEntry<T> prior = entry.prior;
         FListEntry<T> next = entry.next;
         if(null != prior) {
            prior.next = next;
         } else {
            _first = next;
         }
         if(null != next) {
            next.prior = prior;
         } else {
            _last = prior;
         }
         _count--;
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
      // <T>判断是否为空。</T>
      //
      // @return 是否为空
      //============================================================
      public bool IsEmpty {
         get { return (0 == _count); }
      }

      //============================================================
      // <T>是否含有内容。</T>
      //
      // @param value 内容
      // @return 是否含有
      //============================================================
      public bool Contains(T value) {
         if((null != value) && (_count > 0)) {
            FListEntry<T> entry = _first;
            while(null != entry) {
               if(value.Equals(entry.value)) {
                  return true;
               }
               entry = entry.next;
            }
         }
         return false;
      }

      //============================================================
      // <T>是否和指定链表内容相等。</T>
      //
      // @param list 链表
      // @return 是否相等
      //============================================================
      public bool Equals(FList<T> list) {
         // 检查参数
         if(null == list) {
            return false;
         }
         // 检查总数
         if(_count != list._count) {
            return false;
         }
         // 检查内容
         if(_count > 0) {
            int index = 0;
            FListEntry<T> entry = _first;
            while(null != entry) {
               T value = list.Get(index);
               if(!value.Equals(entry.value)) {
                  return false;
               }
               entry = entry.next;
               index++;
            }
         }
         return true;
      }

      //============================================================
      // <T>获得首数据内容。</T>
      //
      // @return 数据内容
      //============================================================
      public T First {
         get { return (null != _first) ? _first.value : default(T); }
      }

      //============================================================
      // <T>获得尾数据内容。</T>
      //
      // @return 数据内容
      //============================================================
      public T Last {
         get { return (null != _last) ? _last.value : default(T); }
      }

      //============================================================
      // <T>获得内容的索引位置。</T>
      //
      // @param value 内容
      // @return 索引位置
      //============================================================
      public int IndexOf(T value) {
         if(_count > 0) {
            int index = 0;
            FListEntry<T> entry = _first;
            while(null != entry) {
               if(value.Equals(entry.value)) {
                  return index;
               }
               entry = entry.next;
               index++;
            }
         }
         return -1;
      }

      //============================================================
      // <T>获得索引位置的数据内容。</T>
      //
      // @param index 索引位置
      // @return 数据内容
      //============================================================
      public T Get(int index) {
         if(_count > 0) {
            int position = 0;
            FListEntry<T> entry = _first;
            while(null != entry) {
               entry = entry.next;
               if(position == index) {
                  return entry.value;
               }
               position++;
            }
         }
         return default(T);
      }

      //============================================================
      // <T>设置指定位置的值。</T>
      //
      // @params int 索引
      // @return Object 值
      //============================================================
      public void Set(int index, T value) {
         int _index = 0;
         T result = default(T);
         if(_index > 0 && _index < _count) {
            FListEntry<T> entry = _first;
            while(null != entry) {
               if(index == _index) {
                  result = entry.value;
                  entry.value = value;
                  break;
               }
               entry = entry.next;
               index++;
            }
         }
      }

      //============================================================
      // <T>弹出首数据内容。</T>
      //
      // @return 数据内容
      //============================================================
      public T Shift() {
         T value = default(T);
         FListEntry<T> entry = _first;
         if(null != entry) {
            value = entry.value;
            FListEntry<T> next = entry.next;
            if(null != next) {
               next.prior = null;
            } else {
               _last = null;
            }
            _first = next;
            _count--;
         }
         return value;
      }

      //============================================================
      // <T>压入数据内容到首部。</T>
      //
      // @parma value 数据内容
      //============================================================
      public void Unshift(T value) {
         if(null != value) {
            FListEntry<T> entry = new FListEntry<T>();
            entry.value = value;
            FListEntry<T> first = _first;
            if(null != first) {
               first.prior = entry;
            } else {
               _last = entry;
            }
            entry.prior = null;
            entry.next = first;
            _first = entry;
            _count++;
         }
      }

      //============================================================
      // <T>弹出尾数据内容。</T>
      //
      // @return 数据内容
      //============================================================
      public T Pop() {
         T value = default(T);
         FListEntry<T> entry = _last;
         if(null != entry) {
            value = entry.value;
            FListEntry<T> prior = entry.prior;
            if(null != prior) {
               prior.next = null;
            } else {
               _first = null;
            }
            _last = prior;
            _count--;
         }
         return value;
      }

      //============================================================
      // <T>压入数据内容到尾部。</T>
      //
      // @parma value 数据内容
      //============================================================
      public void Push(T value) {
         if(null != value) {
            FListEntry<T> entry = new FListEntry<T>();
            entry.value = value;
            FListEntry<T> last = _last;
            if(null != last) {
               last.next = entry;
            } else {
               _first = entry;
            }
            entry.prior = last;
            entry.next = null;
            _last = entry;
            _count++;
         }
      }

      //============================================================
      // <T>追加一个链表。</T>
      //
      // @params list 链表
      //============================================================
      public void Append(FList<T> list) {
         if((null != list) && !list.IsEmpty) {
            FListEntry<T> entry = list._first;
            while(null != entry) {
               Push(entry.value);
               entry = entry.next;
            }
         }
      }

      //============================================================
      // <T>清空指定索引位置的数据内容。</T>
      //
      // @params index 索引位置
      //============================================================
      public object Erase(int index) {
         return null;
      }

      //============================================================
      // <T>移除链表中的所有指定数据内容的项目。</T>
      //
      // @params value 数据内容
      //============================================================
      public void Remove(T value) {
         FListEntry<T> entry = _first;
         while(null != entry) {
            FListEntry<T> next = entry.next;
            if(entry.value.Equals(value)) {
               InnerRemove(entry);
            }
            entry = next;
         }
      }

      //============================================================
      // <T>清除所有数据内容。</T>
      //============================================================
      public void Clear() {
         _first = _last = null;
         _count = 0;
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
         return new FListEnumerator<T>(_first);
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
         bool isFirst = true;
         FListEntry<T> entry = _first;
         while(null != entry) {
            // 追加分隔符
            if(isFirst) {
               isFirst = false;
            } else {
               info.Append(", ");
            }
            // 追加内容
            T value = entry.value;
            if(value is IDump) {
               ((IDump)value).Dump(info);
            } else {
               info.Append(value);
            }
            entry = entry.next;
         }
         info.Append("}");
         info.End();
         return info;
      }

      #endregion
   }
}
