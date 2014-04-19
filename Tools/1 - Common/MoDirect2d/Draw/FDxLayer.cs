using System.Drawing;
using MO.Common.Geom;
using MO.Direct2d.Common;
using SlimDX;
using SlimDX.Direct2D;

namespace MO.Direct2d.Draw
{
   //============================================================
   // <T>层。</T>
   //============================================================
   public class FDxLayer : FDxObject2d
   {
      // 本地对象
      protected Layer _native;

      // 本地参数
      protected LayerParameters _nativeParameters = new LayerParameters();

      // 大小
      protected SFloatSize _size = new SFloatSize();

      //============================================================
      // <T>构造层。</T>
      //============================================================
      public FDxLayer() {
      }

      //============================================================
      // <T>获得本地对象。</T>
      //============================================================
      public Layer Native {
         get { return _native; }
      }

      //============================================================
      // <T>获得大小。</T>
      //============================================================
      public SFloatSize Size {
         get { return _size; }
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
         if(null != _native) {
            _native.Dispose();
         }
         // 创建对象
         if(_size.IsEmpty()) {
            _native = new Layer(_deviceTarget);
         } else {
            _native = new Layer(_deviceTarget, new SizeF(_size.Width, _size.Height));
         }
      }

      //============================================================
      // <T>开始处理。</T>
      //
      // @param x 横向偏移
      // @param y 纵向偏移
      //============================================================
      public void Begin(float x, float y, float width, float height) {
         _nativeParameters.ContentBounds = new RectangleF(0, 0, width, height);
         _nativeParameters.Opacity = 1;
         _deviceTarget.Transform = Matrix3x2.Translation(x, y);
         _deviceTarget.PushLayer(_native, _nativeParameters);
      }

      //============================================================
      // <T>结束处理。</T>
      //============================================================
      public void End() {
         _deviceTarget.PopLayer();
      }

      //============================================================
      // <T>释放资源。</T>
      //============================================================
      public override void Dispose() {
         if(_native != null) {
            _native.Dispose();
            _native = null;
         }
         base.Dispose();
      }
   }
}
