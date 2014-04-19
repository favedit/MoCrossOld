using MO.Common.Lang;
using MO.Common.Content;

namespace MO.Common.Geom
{
   //============================================================
   // <T>整数二维点。</T>
   //============================================================
   public class SIntPoint3
   {
      public int X;

      public int Y;

      public int Z;

      //============================================================
      // <T>构造二维点。</T>
      //
      // @param x X坐标
      // @param y Y坐标
      // @param Z Z坐标
      //============================================================
      public SIntPoint3() {
      }

      //============================================================
      // <T>构造二维点。</T>
      //
      // @param x X坐标
      // @param y Y坐标
      // @param Z Z坐标
      //============================================================
      public SIntPoint3(int x, int y, int z) {
         X = x;
         Y = y;
         Z = z;
      }

      //============================================================
      // <T>解析字符串。</T>
      //
      // @param value 字符串
      //============================================================
      public void Parse(string value) {
         if (null == value) {
            X = Y = Z = 0;
         } else {
            string[] items = value.Split(',');
            if (1 == items.Length) {
               X = Y = Z = RInt.Parse(value);
            } else if (2 == items.Length) {
               X = RInt.Parse(items[0]);
               Y = RInt.Parse(items[1]);
               Z = 0;
            } else if(3 == items.Length) {
               X = RInt.Parse(items[0]);
               Y = RInt.Parse(items[1]);
               Z = RInt.Parse(items[2]);
            } else {
               throw new FFatalException("Invalid string.");
            }
         }
      }

      //============================================================
      public void LoadConfig(FXmlNode config) {
         X = config.GetInteger("x");
         Y = config.GetInteger("y");
         Z = config.GetInteger("z");
      }
   }
}
