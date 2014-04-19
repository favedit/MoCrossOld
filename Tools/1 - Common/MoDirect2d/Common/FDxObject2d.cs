using MO.Common.Lang;
using MO.Direct2d.Device;
using SlimDX.Direct2D;

namespace MO.Direct2d.Common
{
   //============================================================
   // <T>基础2D对象。</T>
   //
   // @history MAOCY 120707
   //============================================================
   public class FDxObject2d : FObject
   {
      // 设备
      protected FDxDevice2d _device;

      // 渲染目标
      protected WindowRenderTarget _deviceTarget;

      //============================================================
      // <T>构造基础2D对象。</T>
      //============================================================
      public FDxObject2d() {
      }

      //============================================================
      // <T>获得或设置设备。</T>
      //============================================================
      public FDxDevice2d Device {
         get { return _device; }
         set {
            _device = value;
            _deviceTarget = value.Target;
         }
      }

      //============================================================
      // <T>设置处理。</T>
      //============================================================
      public virtual void Setup() {
      }

      //============================================================
      // <T>释放资源。</T>
      //============================================================
      public virtual void Dispose() {
         _device = null;
         _deviceTarget = null;
      }
   }
}
