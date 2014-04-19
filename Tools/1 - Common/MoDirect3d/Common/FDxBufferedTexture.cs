using MO.Common.Geom;
using SlimDX;
using SlimDX.Direct3D11;
using SlimDX.DXGI;
using System.IO;

namespace MO.DX.Core.Common
{
   //============================================================
   public class FDxBufferedTexture : FDxTexture
   {
      protected RenderTargetView _nativeTarget;

      protected Viewport _nativeViewport;

      protected Format _nativeFormat = Format.R8G8B8A8_UNorm;

      //============================================================
      public FDxBufferedTexture() {
      }

      //============================================================
      public RenderTargetView NativeTarget {
         get { return _nativeTarget; }
         set { _nativeTarget = value; }
      }

      //============================================================
      public Viewport NativeViewport {
         get { return _nativeViewport; }
         set { _nativeViewport = value; }
      }

      //============================================================
      public Format NativeFormat {
         get { return _nativeFormat; }
         set { _nativeFormat = value; }
      }

      //============================================================
      // 创建缓冲
      //============================================================
      public void Create(int width, int height) {
         // 设置信息
         _size.Set(width, height);
         _nativeViewport = new Viewport(0, 0, width, height, 0, 1);
         // 创建纹理资源
         Texture2DDescription description = new Texture2DDescription {
            Format = _nativeFormat,
            Width = width,
            Height = height,
            ArraySize = 1,
            MipLevels = 1,
            BindFlags = BindFlags.RenderTarget | BindFlags.ShaderResource,
            Usage = ResourceUsage.Default,
            CpuAccessFlags = CpuAccessFlags.None,
            OptionFlags = ResourceOptionFlags.None,
            SampleDescription = new SampleDescription(1, 0),
         };
         _nativeTexture = new Texture2D(_device.NativeAdapter, description);
         // 创建渲染代码资源
         ShaderResourceViewDescription resourceDescription = new ShaderResourceViewDescription() {
            Format = description.Format,
            Dimension = ShaderResourceViewDimension.Texture2D,
            MostDetailedMip = 0,
            MipLevels = 1,
         };
         _nativeResource = new ShaderResourceView(_device.NativeAdapter, _nativeTexture, resourceDescription);
         // 创建渲染目标资源
         RenderTargetViewDescription targetDescription = new RenderTargetViewDescription() {
            Format = description.Format,
            Dimension = RenderTargetViewDimension.Texture2D,
            MipSlice = 0,
         };
         _nativeTarget = new RenderTargetView(_device.NativeAdapter, _nativeTexture, targetDescription);
      }
      
      //============================================================
      // 创建缓冲
      //============================================================
      public void Create2(int width, int height) {
         // 设置信息
         _size.Set(width, height);
         _nativeViewport = new Viewport(0, 0, width, height, 0, 1);
         // 创建纹理资源
         Texture2DDescription description = new Texture2DDescription {
            Format = _nativeFormat,
            Width = width,
            Height = height,
            ArraySize = 1,
            MipLevels = 1,
            BindFlags = BindFlags.None,
            Usage = ResourceUsage.Staging,
            CpuAccessFlags = CpuAccessFlags.Read,
            OptionFlags = ResourceOptionFlags.None,
            SampleDescription = new SampleDescription(1, 0),
         };
         _nativeTexture = new Texture2D(_device.NativeAdapter, description);
      }

      //============================================================
      // 清除颜色
      //============================================================
      public void Clear(SFloatColor4 color) {
         _device.NativeDevice.ClearRenderTargetView(_nativeTarget, new Color4(color.A, color.R, color.G, color.B));
      }

      //============================================================
      // 清除颜色
      //============================================================
      public void Clear(float r, float g, float b, float a) {
         _device.NativeDevice.ClearRenderTargetView(_nativeTarget, new Color4(a, r, g, b));
      }

      //============================================================
      // 清除颜色
      //============================================================
      public void SaveFile(string fileName) {
         Texture2D.ToFile(_device.NativeDevice, _nativeTexture, ImageFileFormat.Png, fileName);
      }

      //============================================================
      // 清除颜色
      //============================================================
      public void SaveStream(Stream stream) {
         stream.Position = 0;
         Texture2D.ToStream(_device.NativeDevice, _nativeTexture, ImageFileFormat.Png, stream);
      }
      
      //============================================================
      public void Dispose(){
         _nativeTexture.Dispose();
         _nativeTarget.Dispose();
      }
   }
}
