using MO.Common.Lang;

namespace MO.Content3d.Common
{
   //============================================================
   // <T>标志。</T>
   //============================================================
   public class EDrFlag
   {
      // 继承
      public const int Inherit = 0;

      // 否
      public const int No = 1;

      // 是
      public const int Yes = 2;

      //============================================================
      public static int TryParse(string type) {
         switch (type) {
            case "N":
               return No;
            case "Y":
               return Yes;
         }
         return Inherit;
      }
      
      //============================================================
      public static int Parse(string type) {
         int typeCd = TryParse(type);
         if (-1 == typeCd) {
            throw new FFatalException("Unknown type.");
         }
         return typeCd;
      }

      //============================================================
      public static int FromBoolean(bool value) {
         return value ? Yes : Inherit;
      }

      //============================================================
      public static string ToString(int typeCd) {
         switch (typeCd) {
            case Yes:
               return "Y";
            case No:
               return "N";
         }
         return "";
      }

      //============================================================
      public static bool ToBoolean(int typeCd) {
         switch (typeCd) {
            case Yes:
               return true;
         }
         return false;
      }
   }
}
