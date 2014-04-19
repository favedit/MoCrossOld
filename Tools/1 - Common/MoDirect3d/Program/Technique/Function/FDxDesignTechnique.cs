using MO.DX.Core.Common;
using MO.DX.Core.Program.Pass.Function;
using SlimDX;
using SlimDX.DXGI;

namespace MO.DX.Core.Program.Technique.Function
{
   //============================================================
   public class FDxDesignTechnique : FDxTechnique
   {
      protected FDxDesignScenePass _passDesign = new FDxDesignScenePass();

      protected FDxDesignUiPass _passUi = new FDxDesignUiPass();

      protected FDxDesignMergePass _passMerge = new FDxDesignMergePass();

      public FDxBufferedTexture _buffer = new FDxBufferedTexture();

      //============================================================
      public FDxDesignTechnique() {
         _name = "design";
      }

      //============================================================
      public int HitTest(FDxRegion region, int x, int y) {
         // 绘制结果
         _passDesign.Draw(region);
         _passUi.Draw(region);
         // 计算位置
         int renderableId = 0;
         int cx = (int)((float)x / _device.Size.Width * _buffer.Size.Width);
         int cy = (int)((float)y / _device.Size.Height * _buffer.Size.Height);
         // 测试物件选中
         _device.NativeDevice.CopyResource(_passDesign.TextureFlags.NativeTexture, _buffer.NativeTexture);
         DataRectangle designData = _buffer.NativeTexture.AsSurface().Map(SlimDX.DXGI.MapFlags.Read);
         unsafe {
            int* pData = (int*)designData.Data.DataPointer.ToPointer();
            pData += 4 * (_buffer.Size.Width * cy + cx);
            int r = *pData++;
            int g = *pData++;
            int b = *pData++;
            int a = *pData++;
            renderableId = r;
         }
         _buffer.NativeTexture.AsSurface().Unmap();
         // 测试物件选中
         _device.NativeDevice.CopyResource(_passUi.TextureFlags.NativeTexture, _buffer.NativeTexture);
         DataRectangle uiData = _buffer.NativeTexture.AsSurface().Map(SlimDX.DXGI.MapFlags.Read);
         unsafe {
            int* pData = (int*)uiData.Data.DataPointer.ToPointer();
            pData += 4 * (_buffer.Size.Width * cy + cx);
            int r = *pData++;
            int g = *pData++;
            int b = *pData++;
            int a = *pData++;
            if(r > 0) {
               renderableId = r;
            }
         }
         _buffer.NativeTexture.AsSurface().Unmap();
         return renderableId;
      }

      //============================================================
      public override void Setup() {
         base.Setup();
         _buffer.Device = _device;
         _buffer.NativeFormat = Format.R32G32B32A32_SInt;
         _buffer.Create2(2048, 2048);
         // 建立设计渲染过程
         _passDesign.Technique = this;
         _passDesign.Device = _device;
         _passDesign.Setup();
         RegisterPass(_passDesign);
         // 建立界面渲染过程
         _passUi.Technique = this;
         _passUi.Device = _device;
         _passUi.Setup();
         RegisterPass(_passUi);
         // 建立合并渲染过程
         _passMerge.Technique = this;
         _passMerge.Device = _device;
         _passMerge.Setup();
         RegisterPass(_passMerge);
         // 关联渲染过程
         _passMerge.TextureFlags = _passDesign.TextureFlags;
         _passMerge.TextureColor = _passDesign.TextureColor;
         _passMerge.TexturePosition = _passDesign.TexturePosition;
         _passMerge.TextureNormal = _passDesign.TextureNormal;
         _passMerge.TextureSelected = _passDesign.TextureSelected;
         _passMerge.TextureColorUi = _passUi.TextureColor;
      }
   }
}
