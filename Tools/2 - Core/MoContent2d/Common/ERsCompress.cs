namespace MO.Content2d.Common
{
   //============================================================
   // <T>导出模式。</T>
   //============================================================
   public class ERsCompress
   {
      // 未压缩
      public const int NoneValue = 0;

      // Deflate压缩
      public const int DeflateValue = 1;

      // LZMA压缩（内建）
      public const int LzmaValue = 2;

      // 未压缩
      public const string None = "N";

      // Deflate压缩
      public const string Deflate = "D";

      // LZMA压缩（内建）
      public const string Lzma = "L";

      // LZMA压缩（炼金术）
      public const string LzmaAlchemy = "A";

      //============================================================
      public static int Parse(string code) {
         if(None == code) {
            return NoneValue;
         }
         if(Deflate == code) {
            return DeflateValue;
         }
         if(Lzma == code) {
            return LzmaValue;
         }
         if(LzmaAlchemy == code) {
            return LzmaValue;
         }
         return 0;
      }

      //============================================================
      public static string ToString(int code) {
         if(NoneValue == code) {
            return None;
         }
         if(DeflateValue == code) {
            return Deflate;
         }
         if(LzmaValue == code) {
            return Lzma;
         }
         return None;
      }
   }
}
