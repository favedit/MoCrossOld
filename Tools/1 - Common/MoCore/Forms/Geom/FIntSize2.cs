using System.ComponentModel;
using MO.Common.Geom;

namespace MO.Core.Forms.Geom
{
   //============================================================
   // <T>整数二维尺寸。</T>
   //============================================================
   [TypeConverter(typeof(FIntSize2Converter))]
   public class FIntSize2 : SIntSize2
   {
      //============================================================
      // <T>构造整数二维尺寸。</T>
      //
      // @param width 宽度
      // @param height 高度
      //============================================================
      public FIntSize2(int width = 0, int height = 0)
         : base(width, height) {
      }

      //============================================================
      // <T>获得或设置横坐标。</T>
      //============================================================
      [Browsable(true)]
      [Description("宽度")]
      [DefaultValue(0)]
      public new int Width {
         get { return base.Width; }
         set { base.Width = value; }
      }

      //============================================================
      // <T>获得或设置纵坐标。</T>
      //============================================================
      [Browsable(true)]
      [Description("高度")]
      [DefaultValue(0)]
      public new int Height {
         get { return base.Height; }
         set { base.Height = value; }
      }
   }
}
