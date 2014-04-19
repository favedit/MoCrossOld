using SlimDX.Direct3D11;
using SlimDX.DXGI;

namespace MO.DX.Core.Common
{
   //============================================================
   public class FDxBufferedDepth : FDxTexture
   {
      protected DepthStencilView _nativeDepth;

      //============================================================
      public FDxBufferedDepth() {
      }

      //============================================================
      public DepthStencilView NativeDepth {
         get { return _nativeDepth; }
      }

      //============================================================
      // 创建缓冲
      //============================================================
      public void Create(int width, int height) {
         // 设置信息
         _size.Set(width, height);
         // 创建纹理资源
         Texture2DDescription description = new Texture2DDescription {
            Format = Format.D32_Float,
            Width = width,
            Height = height,
            ArraySize = 1,
            MipLevels = 1,
            BindFlags = BindFlags.DepthStencil,
            Usage = ResourceUsage.Default,
            CpuAccessFlags = CpuAccessFlags.None,
            OptionFlags = ResourceOptionFlags.None,
            SampleDescription = new SampleDescription(1, 0),
         };
         _nativeTexture = new Texture2D(_device.NativeAdapter, description);
         // 创建渲染目标资源
         DepthStencilViewDescription depthDescription = new DepthStencilViewDescription() {
            Format = description.Format,
            Dimension = DepthStencilViewDimension.Texture2D,
            MipSlice = 0,
         };
         _nativeDepth = new DepthStencilView(_device.NativeAdapter, _nativeTexture, depthDescription);
      }
      
      //============================================================
      // 清除颜色
      //============================================================
      public void Clear() {
         _device.NativeDevice.ClearDepthStencilView(_nativeDepth, DepthStencilClearFlags.Depth | DepthStencilClearFlags.Stencil, 1, 0);
      }

      //============================================================
      public void Dispose(){
         _nativeTexture.Dispose();
         _nativeDepth.Dispose();
      }
   }
}
