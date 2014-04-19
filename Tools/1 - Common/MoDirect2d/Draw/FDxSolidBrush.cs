using MO.Common.Geom;
using SlimDX;
using SlimDX.Direct2D;

namespace MO.Direct2d.Draw
{
   //============================================================
   // <T>固定色刷。</T>
   //
   // @history MAOCY 120223
   //============================================================
   public class FDxSolidBrush : FDxBrush
   {
      // 颜色
      protected SColor _color = new SColor();

      //============================================================
      // <T>构造固定色刷。</T>
      //============================================================
      public FDxSolidBrush() {
      }

      //============================================================
      // <T>获得颜色。</T>
      //============================================================
      public SColor Color {
         get { return _color;  }
      }

      //============================================================
      // <T>设置处理。</T>
      //============================================================
      public override void Setup() {
         Refresh();
      }

      //============================================================
      // <T>重新加载。</T>
      //============================================================
      public void Refresh() {
         // 释放对象
         if (_native != null) {
            _native.Dispose();
         }
         // 创建对象
         float r = _color.ValueR;
         float g = _color.ValueG;
         float b = _color.ValueB;
         _native = new SolidColorBrush(_device.Target, new Color4(1.0f, r, g, b));
         _native.Opacity = _color.ValueA;
      }

      //============================================================
      // <T>释放资源。</T>
      //============================================================
      public override void Dispose() {
         _color = null;
         base.Dispose();
      }
   }
}
