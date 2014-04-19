using MO.DX.Core.Common;
using MO.DX.Core.Model;
using MO.DX.Core.Program.Effect;
using SlimDX.Direct3D11;
using SlimDX.DXGI;
using SlimDX;

namespace MO.DX.Core.Program.Pass.Function
{
   //============================================================
   public class FDxGrowingPass : FDxAbsCameraPass
   {
      //============================================================
      public FDxGrowingPass() {
         _name = "growing";
      }

      //============================================================
      // <T>开始当前渲染过程。</T>
      //
      // @param region 区域
      // @param target 是否为最终目标
      //============================================================
      public override void Begin(FDxRegion region) {
         _device.Clear(0.0f, 0.0f, 0.0f, 0.0f);
      }
      
      //============================================================
      // <T>绘制区域内所有节点集合。</T>
      //
      // @param p:region 区域
      //============================================================
      public override void DrawRenderable(FDxRegion region, FDxRenderable renderable, FDxEffect effect) {
         // 设置顶点缓冲
         FDxGeometry geometry = renderable as FDxGeometry;
         _device.NativeDevice.InputAssembler.PrimitiveTopology = PrimitiveTopology.TriangleList;
         _device.NativeDevice.InputAssembler.SetVertexBuffers(0, renderable.VertexBuffer.NativeBuffers);
         _device.NativeDevice.InputAssembler.SetIndexBuffer(renderable.FaceBuffer.NativeBuffer, Format.R32_UInt, 0);
         FDxTexture orignTexture = renderable.Material.Textures[0];
         FDxTexture texture = renderable.Material.Textures[1];
         effect.NativeEffect.GetVariableByName("_textureSize").AsVector().Set(new Vector2(texture.Size.Width, texture.Size.Height));
         effect.NativeEffect.GetVariableByName("_textureOrigin").AsResource().SetResource(orignTexture.NativeResource);
         effect.NativeEffect.GetVariableByName("_textureColor").AsResource().SetResource(texture.NativeResource);
         effect.NativePass.Apply(_device.NativeDevice);
         _device.NativeDevice.DrawIndexed(renderable.FaceBuffer.count, 0, 0);
      }
   }
}
