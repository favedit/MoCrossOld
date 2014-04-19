using MO.Common.Geom;
using SlimDX;
using SlimDX.Direct2D;

namespace MO.DirectX.Draw
{
   //============================================================
   // <T>固定色刷。</T>
   //
   // @history MAOCY 120223
   //============================================================
   public class FDxSolidBrush : FDxBrush
   {
      // 颜色
      protected SFloatColor3 _color = new SFloatColor3();

      //============================================================
      // <T>构造固定色刷。</T>
      //============================================================
      public FDxSolidBrush() {
      }

      //============================================================
      // <T>获得颜色。</T>
      //============================================================
      public SFloatColor3 Color {
         get { return _color;  }
      }

      //============================================================
      // <T>设置处理。</T>
      //============================================================
      public override void Setup() {
         float r = (float)_color.R / 255.0f;
         float g = (float)_color.G / 255.0f;
         float b = (float)_color.B / 255.0f;
         _native = new SolidColorBrush(_device.Target, new Color4(0, 0, 0));
      }
   }
}
