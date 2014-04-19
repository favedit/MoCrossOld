using System.ComponentModel;
using MO.Common.Geom;

namespace MO.Core.Forms.Geom
{
   //============================================================
   // <T>整数边界。</T>
   //============================================================
   [TypeConverter(typeof(FIntPaddingConverter))]
   public class FIntPadding : SIntPadding
   {
      //============================================================
      // <T>构造整数二维尺寸。</T>
      //
      // @param left 左边宽度
      // @param top 上边宽度
      // @param right 右边宽度
      // @param bottom 下边宽度
      //============================================================
      public FIntPadding(int left = 0, int top = 0, int right = 0, int bottom = 0)
         : base(left, top, right, bottom) {
      }

      //============================================================
      // <T>获得或设置左边宽度。</T>
      //============================================================
      [Browsable(true)]
      [Description("左边宽度")]
      [DefaultValue(0)]
      public new int Left {
         get { return base.Left; }
         set { base.Left = value; }
      }

      //============================================================
      // <T>获得或设置上边宽度。</T>
      //============================================================
      [Browsable(true)]
      [Description("上边宽度")]
      [DefaultValue(0)]
      public new int Top {
         get { return base.Top; }
         set { base.Top = value; }
      }

      //============================================================
      // <T>获得或设置右边宽度。</T>
      //============================================================
      [Browsable(true)]
      [Description("右边宽度")]
      [DefaultValue(0)]
      public new int Right {
         get { return base.Right; }
         set { base.Right = value; }
      }

      //============================================================
      // <T>获得或设置下边宽度。</T>
      //============================================================
      [Browsable(true)]
      [Description("下边宽度")]
      [DefaultValue(0)]
      public new int Bottom {
         get { return base.Bottom; }
         set { base.Bottom = value; }
      }
   }
}
