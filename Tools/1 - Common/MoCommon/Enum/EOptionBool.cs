using MO.Common.Lang;

namespace MO.Common.Enum
{
   //============================================================
   public class EOptionBool
   {
      // 否
      public const int No = 0x00;

      // 是
      public const int Yes = 0x01;

      // 继承
      public const int Inherits = 0x02;

      //============================================================
      public static int TryParse(string type) {
         switch (type) {
            case "Y":
               return Yes;
            case "N":
               return No;
         }
         return Inherits;
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
      public static string ToString(int typeCd) {
         switch (typeCd) {
            case Yes:
               return "Y";
            case No:
               return "N";
         }
         return "";
      }
   }
}
