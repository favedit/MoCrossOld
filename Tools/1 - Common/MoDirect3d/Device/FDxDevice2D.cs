using System;
using System.Drawing;
using MO.Common.Geom;
using MO.Common.Lang;
using MO.DirectX.Draw;
using SlimDX.Direct2D;

namespace MO.DirectX.Device
{
   //============================================================
   // <T>设备。</T>
   //============================================================
   public class FDxDevice2D : FObject
   {
      // 工厂
      protected Factory _factory;

      // 渲染目标
      protected WindowRenderTarget _target;

      // 句柄
      protected IntPtr _handle;

      // 尺寸
      protected SIntSize2 _size = new SIntSize2();

      //============================================================
      // <T>构造设备。</T>
      //============================================================
      public FDxDevice2D() {
      }

      //============================================================
      // <T>获得或设置句柄。</T>
      //============================================================
      public IntPtr Handle {
         get { return _handle; }
         set { _handle = value; }
      }

      //============================================================
      // <T>获得大小。</T>
      //============================================================
      public SIntSize2 Size {
         get { return _size; }
      }

      //============================================================
      // <T>获得渲染目标。</T>
      //============================================================
      public WindowRenderTarget Target {
         get { return _target; }
      }

      //============================================================
      // <T>设置处理。</T>
      //============================================================
      public void Setup() {
         // 设置属性
         WindowRenderTargetProperties properties = new WindowRenderTargetProperties();
         properties.Handle = _handle;
         properties.PixelSize = new Size(_size.Width, _size.Height);
         // 创建工厂
         _factory = new Factory();
         // 创建目标
         _target = new WindowRenderTarget(_factory, properties);
      }

      //============================================================
      // <T>创建色刷。</T>
      //============================================================
      public FDxSolidBrush CreateSolidBrush(Color color) {
         FDxSolidBrush brush = new FDxSolidBrush();
         brush.Device = this;
         brush.Color.Set(color.R, color.G, color.B);
         brush.Setup();
         return brush;
      }

      //============================================================
      // <T>创建色刷。</T>
      //============================================================
      public FDxSolidBrush CreateSolidBrush(float r, float g, float b) {
         FDxSolidBrush brush = new FDxSolidBrush();
         brush.Device = this;
         brush.Color.Set(r, g, b);
         brush.Setup();
         return brush;
      }
   }
}
