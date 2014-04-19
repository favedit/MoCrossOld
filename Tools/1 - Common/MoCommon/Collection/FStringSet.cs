using System;

namespace MO.Common.Collection
{
   //============================================================
   // <T>字符串集合。</T>
   //============================================================
   public class FStringSet : FSet<string>
   {
      // 字符串比较方式
      protected StringComparison _comparison = StringComparison.CurrentCultureIgnoreCase;

      //============================================================
      // <T>构造字符串集合。</T>
      //============================================================
      public FStringSet() {
      }

      //============================================================
      // <T>构造字符串集合。</T>
      //
      // @param size 初始化大小
      //============================================================
      public FStringSet(int size)
         : base(size) {
      }

      //============================================================
      // <T>构造字符串集合。</T>
      //
      // @param comparison 字符串比较方式
      //============================================================
      public FStringSet(StringComparison comparison) {
         _comparison = comparison;
      }

      //============================================================
      // <T>构造字符串集合。</T>
      //
      // @param comparison 字符串比较方式
      // @param size 初始化大小
      //============================================================
      public FStringSet(StringComparison comparison, int size)
         : base(size) {
         _comparison = comparison;
      }

      //============================================================
      // <T>[内部] 获得哈希码。</T>
      //
      // @param value 内容
      // @return 哈希码
      //============================================================
      protected override int InnerCode(string value) {
         return RHash.Code(value.ToLower());
      }

      //============================================================
      // <T>[内部] 比较两项是否相等。</T>
      //
      // @param source 源项目
      // @param target 目标项目
      // @return 是否相等
      //============================================================
      protected override bool InnerEquals(string source, string target) {
         return source.Equals(target, _comparison);
      }

      //============================================================
      // <T>获得或设置字符串比较方式。</T>
      //
      // @param value 字符串比较方式
      // @return 字符串比较方式
      //============================================================
      public StringComparison Comparison {
         get { return _comparison; }
         set { _comparison = value; }
      }
   }
}
