using System.ComponentModel;
using MO.Common.Geom;

namespace MO.Core.Forms.Geom
{
   //============================================================
   // <T>整数二维点。</T>
   //
   // @param value 坐标。
   // @return 坐标。
   //============================================================
   [TypeConverter(typeof(FIntPoint2Converter))]
   public class FIntPoint2 : SIntPoint2
   {
      //============================================================
      // <T>构造二维点。</T>
      //
      // @param x X坐标
      // @param y Y坐标
      //============================================================
      public FIntPoint2(int x = 0, int y = 0)
         : base(x, y) {
      }

      //============================================================
      // <T>获得或设置横坐标。</T>
      //============================================================
      [Browsable(true)]
      [DefaultValue(0)]
      [Description("横坐标")]
      public new int X {
         get { return base.X; }
         set { base.X = value; }
      }

      //============================================================
      // <T>获得或设置纵坐标。</T>
      //============================================================
      [Browsable(true)]
      [DefaultValue(0)]
      [Description("纵坐标")]
      public new int Y {
         get { return base.Y; }
         set { base.Y = value; }
      }
   }
}
