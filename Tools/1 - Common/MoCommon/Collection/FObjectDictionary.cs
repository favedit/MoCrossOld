using System;

namespace MO.Common.Collection
{
   //============================================================
   // <T>对象字典集合。</T>
   //============================================================
   public class FObjectDictionary : FDictionary<object>
   {
      //============================================================
      // <T>构造对象字典集合。</T>
      //============================================================
      public FObjectDictionary() {
      }

      //============================================================
      // <T>构造对象字典集合。</T>
      //
      // @param comparison 名称比较方式
      //============================================================
      public FObjectDictionary(StringComparison comparison)
         : base(comparison) {
      }

      //============================================================
      // <T>构造对象字典集合。</T>
      //
      // @param size 初始化大小
      //============================================================
      public FObjectDictionary(int size)
         : base(size) {
      }

      //============================================================
      // <T>构造对象字典集合。</T>
      //
      // @param size 初始化大小
      // @param comparison 名称比较方式
      //============================================================
      public FObjectDictionary(int size, StringComparison comparison)
         : base(size, comparison) {
      }
   }
}
