using MO.DX.Core.Common;
using SlimDX.DXGI;
using MO.DX.Core.Model;
using SlimDX;
using SlimDX.Direct3D11;
using MO.Common.Lang;
using MO.Common.Geom;
using MO.DX.Core.Program.Effect;

namespace MO.DX.Core.Program.Pass.Function
{
   //============================================================
   public class FDxDesignLinePass : FDxAbsCameraPass
   {
      public FDxBufferedDepth _depthTexture = new FDxBufferedDepth();

      public FDxBufferedTexture _lineTexture = new FDxBufferedTexture();

      //============================================================
      public FDxDesignLinePass() {
         _name = "design.line";
      }

      //============================================================
      public FDxBufferedTexture LineTexture {
         get { return _lineTexture; }
      }

      //============================================================
      // <T>设置渲染过程。</T>
      //============================================================
      public override void Setup() {
         base.Setup();
         SIntSize size = new SIntSize(2048, 2048);
         // 创建深度检测区
         _depthTexture.Device = _device;
         _depthTexture.Create(size.Width, size.Height);
         // 创建渲染目标纹理区 (颜色，透明度)
         _lineTexture.Device = _device;
         _lineTexture.NativeFormat = Format.R8G8B8A8_UNorm;
         _lineTexture.Create(size.Width, size.Height);
      }

      //============================================================
      // <T>开始当前渲染过程。</T>
      //
      // @param region 区域
      // @param target 是否为最终目标
      //============================================================
      public override void Begin(FDxRegion region) {
         _device.ModeWireFrame = true;
         //_device.SetRenderTarget(_depthTexture, _lineTexture);
         //_depthTexture.Clear();
         _device.NativeDevice.OutputMerger.SetTargets(_device.NativeDevice.OutputMerger.GetDepthStencilView(), _lineTexture.NativeTarget);
         _lineTexture.Clear(0, 0, 0, 1);
      }

      //============================================================
      public override void DrawRenderable(FDxRegion region, FDxRenderable renderable, FDxEffect effect) {
         // 设置顶点缓冲
         FDxGeometry geometry = renderable as FDxGeometry;
         effect.NativeEffect.GetVariableByName("_modelMatrix").AsMatrix().SetMatrix(renderable.Matrix.Native);
         effect.NativeEffect.GetVariableByName("_viewMatrix").AsMatrix().SetMatrix(region.Camera.Matrix.Native);
         effect.NativeEffect.GetVariableByName("_projectionMatrix").AsMatrix().SetMatrix(region.Camera.Viewport.Matrix);
         effect.NativeEffect.GetVariableByName("_cameraPosition").AsVector().Set(new Vector3(region.Camera.Position.X, region.Camera.Position.Y, region.Camera.Position.Z));
         effect.NativeEffect.GetVariableByName("_lightPosition").AsVector().Set(new Vector3(region.LightDirectional.Position.X, region.LightDirectional.Position.Y, region.LightDirectional.Position.Z));
         effect.NativeEffect.GetVariableByName("_lightDirection").AsVector().Set(new Vector3(region.LightDirectional.Camera.Direction.X, region.LightDirectional.Camera.Direction.Y, region.LightDirectional.Camera.Direction.Z));
         _device.NativeDevice.InputAssembler.PrimitiveTopology = PrimitiveTopology.TriangleList;
         _device.NativeDevice.InputAssembler.SetVertexBuffers(0, renderable.VertexBuffer.NativeBuffers);
         _device.NativeDevice.InputAssembler.SetIndexBuffer(renderable.FaceBuffer.NativeBuffer, Format.R32_UInt, 0);
         int textureCount = geometry.Material.Textures.Count;
         for (int i = 0; i < textureCount; i++) {
            FDxTexture texture = geometry.Material.Textures[i];
            if (null != texture) {
               string textureName = "_texture" + RString.FirstUpper(texture.TypeName);
               effect.NativeEffect.GetVariableByName(textureName).AsResource().SetResource(texture.NativeResource);
            }
         }
         effect.NativePass.Apply(_device.NativeDevice);
         _device.NativeDevice.DrawIndexed(renderable.FaceBuffer.count, 0, 0);
      }
   }
}
