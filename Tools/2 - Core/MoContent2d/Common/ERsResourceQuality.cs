namespace MO.Content2d.Common
{
   //============================================================
   // <T>品质类型。</T>
   //============================================================
   public class ERsResourceQuality
   {
      // 最低
      public const string Min = "min";

      // 低
      public const string Lower = "lower";

      // 中
      public const string Middle = "middle";

      // 高
      public const string High = "high";

      // 最高
      public const string Max = "max";

      // 最低
      public const int MinValue = 70;

      // 低
      public const int LowerValue = 78;

      // 中
      public const int MiddleValue = 85;

      // 高
      public const int HighValue = 92;

      // 最高
      public const int MaxValue = 100;

      // 合并宽度限制
      public const int MergeLimitWidth = 1024 * 2;

      // 合并高度限制
      public const int MergeLimitHeight = 1024 * 2;

      // 合并像素限制
      public const int MergeLimitPixel = 512 * 512 * 1;

      //============================================================
      // <T>解析字符串。</T>
      //
      // @param value 字符串
      // @return 内容
      //============================================================
      public static int ParseBitmapPixel(string value) {
         switch (value) {
            case Min:
               return 196 * 196;
            case Lower:
               return 128 * 128;
            case Middle:
               return 96 * 96;
            case High:
               return 64 * 64;
            case Max:
               return 32 * 32;
         }
         return 96 * 96;
      }

      //============================================================
      // <T>解析字符串。</T>
      //
      // @param value 字符串
      // @return 内容
      //============================================================
      public static int ParseJpgColor(string value) {
         switch (value) {
            case Min:
               return MinValue;
            case Lower:
               return LowerValue;
            case Middle:
               return MiddleValue;
            case High:
               return HighValue;
            case Max:
               return MaxValue;
         }
         return MiddleValue;
      }

      //============================================================
      // <T>解析字符串。</T>
      //
      // @param value 字符串
      // @return 内容
      //============================================================
      public static int ParseJpgAlpha(string value) {
         switch (value) {
            case Min:
               return MinValue - 5;
            case Lower:
               return LowerValue - 5;
            case Middle:
               return MiddleValue - 5;
            case High:
               return HighValue - 5;
            case Max:
               return MaxValue - 5;
         }
         return MiddleValue - 5;
      }

      //============================================================
      // <T>解析字符串。</T>
      //
      // @param value 字符串
      // @return 内容
      //============================================================
      public static int ParsePixel(string value) {
         switch (value) {
            case Min:
               return 10000;
            case Lower:
               return 8000;
            case Middle:
               return 6000;
            case High:
               return 2000;
            case Max:
               return 1000;
         }
         return MiddleValue;
      }

      //============================================================
      // <T>测试是否可以合并。</T>
      //
      // @param width 宽度
      // @param height 高度
      // @return 是否
      //============================================================
      public static bool TestMergeAble(int width, int height) {
         if (width > MergeLimitWidth) {
            return false;
         }
         if (height > MergeLimitHeight) {
            return false;
         }
         if (width * height > MergeLimitPixel) {
            return false;
         }
         return true;
      }
   }
}