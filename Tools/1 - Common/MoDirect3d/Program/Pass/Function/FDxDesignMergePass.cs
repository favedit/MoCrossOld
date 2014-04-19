using MO.DX.Core.Common;
using MO.DX.Core.Model.Geometry;
using SlimDX.Direct3D11;
using SlimDX.DXGI;

namespace MO.DX.Core.Program.Pass.Function
{
   //============================================================
   public class FDxDesignMergePass : FDxAbsCameraPass
   {
      protected FDxBufferedTexture _textureFlags;

      protected FDxBufferedTexture _textureColor;

      protected FDxBufferedTexture _textureColorUi;

      protected FDxBufferedTexture _texturePosition;

      protected FDxBufferedTexture _textureNormal;

      protected FDxBufferedTexture _textureSelected;

      protected FDxProgram _program;

      protected FDxRectangleGeometry _rectangle;

      //============================================================
      public FDxDesignMergePass() {
         _name = "design.merge";
      }

      //============================================================
      public FDxBufferedTexture TextureFlags {
         set { _textureFlags = value; }
      }

      //============================================================
      public FDxBufferedTexture TextureColor {
         set { _textureColor = value; }
      }

      //============================================================
      public FDxBufferedTexture TextureColorUi {
         set { _textureColorUi = value; }
      }

      //============================================================
      public FDxBufferedTexture TexturePosition {
         set { _texturePosition = value; }
      }

      //============================================================
      public FDxBufferedTexture TextureNormal {
         set { _textureNormal = value; }
      }

      //============================================================
      public FDxBufferedTexture TextureSelected {
         set { _textureSelected = value; }
      }

      //============================================================
      // <T>设置渲染过程。</T>
      //============================================================
      public override void Setup() {
         base.Setup();
         _program = RDxCore.EffectConsole.Get(_device, "design.merge");
         _rectangle = new FDxRectangleGeometry();
         _rectangle.Device = _device;
         _rectangle.Setup();
      }
      
      //============================================================
      // <T>开始当前渲染过程。</T>
      //
      // @param region 区域
      // @param target 是否为最终目标
      //============================================================
      public override void Begin(FDxRegion region) {
         _device.ModeWireFrame = false;
         _device.SetRenderTarget();
         _device.Clear(0.0f, 0.0f, 0.0f, 1.0f);
      }

      //============================================================
      public override void DrawRegion(FDxRegion region) {
         _device.Program = _program;
         // 设置输入数据信息
         _device.NativeDevice.InputAssembler.PrimitiveTopology = PrimitiveTopology.TriangleList;
         _device.NativeDevice.InputAssembler.SetVertexBuffers(0, _rectangle.VertexBuffer.NativeBuffers);
         _device.NativeDevice.InputAssembler.SetIndexBuffer(_rectangle.FaceBuffer.NativeBuffer, Format.R32_UInt, 0);
         // 绑定纹理列表
         _program.NativeEffect.GetVariableByName("_textureFlags").AsResource().SetResource(_textureFlags.NativeResource);
         _program.NativeEffect.GetVariableByName("_textureColor").AsResource().SetResource(_textureColor.NativeResource);
         _program.NativeEffect.GetVariableByName("_textureColorUi").AsResource().SetResource(_textureColorUi.NativeResource);
         _program.NativeEffect.GetVariableByName("_texturePosition").AsResource().SetResource(_texturePosition.NativeResource);
         _program.NativeEffect.GetVariableByName("_textureNormal").AsResource().SetResource(_textureNormal.NativeResource);
         _program.NativeEffect.GetVariableByName("_textureSelected").AsResource().SetResource(_textureSelected.NativeResource);
         // 提交效果设置
         _program.NativePass.Apply(_device.NativeDevice);
         // 绘制几何体
         _device.NativeDevice.DrawIndexed(_rectangle.FaceBuffer.count, 0, 0);
      }
   }
}
