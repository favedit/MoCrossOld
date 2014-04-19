namespace MO.Content2d.Common
{
   //============================================================
   // <T>超时类型。</T>
   //============================================================
   public class ERsTimeout
   {
      // 无
      public const string None = "none";

      // 短
      public const string Short = "short";

      // 中
      public const string Middle = "middle";

      // 长
      public const string Long = "long";

      // 无 (不释放)
      public const int NoneValue = 0;

      // 短 (1分钟)
      public const int ShortValue = 60000;

      // 中 (2分钟)
      public const int MiddleValue = 120000;

      // 长 (5分钟)
      public const int LongValue = 300000;

      // 无 (不释放)
      public const int NoneData = 0;

      // 短 (1分钟)
      public const int ShortData = 1;

      // 中 (2分钟)
      public const int MiddleData = 2;

      // 长 (5分钟)
      public const int LongData = 3;

      //============================================================
      // <T>解析字符串。</T>
      //
      // @param value 字符串
      // @return 内容
      //============================================================
      public static int Parse(string value) {
         switch (value) {
            case Short:
               return ShortValue;
            case Middle:
               return MiddleValue;
            case Long:
               return LongValue;
         }
         return NoneValue;
      }
      //============================================================
      // <T>解析字符串。</T>
      //
      // @param value 字符串
      // @return 内容
      //============================================================
      public static int Parse2d(string value) {
         switch (value) {
            case Short:
               return ShortData;
            case Middle:
               return MiddleData;
            case Long:
               return LongData;
         }
         return NoneData;
      }
   }
}
