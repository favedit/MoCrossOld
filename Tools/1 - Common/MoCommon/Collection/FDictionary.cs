using System;
using System.Collections;
using MO.Common.Lang;

namespace MO.Common.Collection
{
   //============================================================
   // <T>字典集合。</T>
   //============================================================
   public class FDictionary<V> : FMap<string, V>
   {
      // 名称比较方式
      protected StringComparison _comparison = StringComparison.CurrentCultureIgnoreCase;

      //============================================================
      // <T>构造字典集合。</T>
      //============================================================
      public FDictionary() {
      }

      //============================================================
      // <T>构造字典集合。</T>
      //
      // @param comparison 名称比较方式
      //============================================================
      public FDictionary(StringComparison comparison) {
         _comparison = comparison;
      }

      //============================================================
      // <T>构造字典集合。</T>
      //
      // @param size 初始化大小
      //============================================================
      public FDictionary(int size)
         : base(size) {
      }

      //============================================================
      // <T>构造字典集合。</T>
      //
      // @param size 初始化大小
      // @param comparison 名称比较方式
      //============================================================
      public FDictionary(int size, StringComparison comparison)
         : base(size) {
         _comparison = comparison;
      }

      //============================================================
      // <T>获得名称的哈希码。</T>
      //
      // @param name 名称
      // @return 哈希码
      //============================================================
      protected override int InnerCode(string name) {
         if (_comparison == StringComparison.CurrentCultureIgnoreCase) {
            return RHash.Code(name.ToLower());
         }
         return RHash.Code(name);
      }

      //============================================================
      // <T>比较名称是否相等。</T>
      //
      // @param source 名称
      // @param target 内容
      // @return 是否相等
      //============================================================
      protected override bool InnerEquals(string source, string target) {
         return source.Equals(target, _comparison);
      }

      //============================================================
      // <T>获得和设置名称比较方式。</T>
      //
      // @return 名称比较方式
      //============================================================
      public StringComparison Comparison {
         get { return _comparison; }
         set { _comparison = value; }
      }

      //============================================================
      // <T>计算所有名称的最大长度。</T>
      //
      // @return 最大长度
      //============================================================
      public int CalculateNameMaxLength() {
         int length = 0;
         for (int n = 0; n < _count; n++) {
            length = Math.Max(length, _names[n].Length);
         }
         return length;
      }

      //============================================================
      // <T>获得所有名称以指定字符串开始的字典集合。</T>
      //
      // @param value 字符串
      // @return 字典集合
      //============================================================
      public FDictionary<V> StartsWith(string value) {
         FDictionary<V> result = new FDictionary<V>();
         for (int n = 0; n < _count; n++) {
            if (_names[n].StartsWith(value)) {
               result.Set(_names[n], _values[n]);
            }
         }
         return result;
      }

      //============================================================
      // <T>获得所有名称以指定字符串结束的字典集合。</T>
      //
      // @param value 字符串
      // @return 字典集合
      //============================================================
      public FDictionary<V> EndsWith(string value) {
         FDictionary<V> result = new FDictionary<V>();
         for (int n = 0; n < _count; n++) {
            if (_names[n].EndsWith(value)) {
               result.Set(_names[n], _values[n]);
            }
         }
         return result;
      }

      //============================================================
      // <T>追加一个有名称的对象。</T>
      //
      // @param item 对象
      //============================================================
      public void Push(V item) {
         if (item is IStringName) {
            Set(((IStringName)item).Name, item);
         } else {
            throw new FFatalException("Unknown namine object.");
         }
      }

      #region IEnumerable implements

      //============================================================
      // <T>获得枚举器。</T>
      //============================================================
      public override IEnumerator GetEnumerator() {
         return new FNamePairEnumerator<V>(_names, _values, 0, _count);
      }

      #endregion
   }
}
