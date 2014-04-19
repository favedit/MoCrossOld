using MO.DX.Core.Common;
using MO.DX.Core.Model;
using MO.DX.Core.Program.Effect;
using SlimDX;
using SlimDX.Direct3D11;
using SlimDX.DXGI;

namespace MO.DX.Core.Program.Pass.Function
{
   //============================================================
   public class FDxNormalMapPass : FDxAbsCameraPass
   {
      //============================================================
      public FDxNormalMapPass() {
         _name = "normal.map";
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
         effect.NativeEffect.GetVariableByName("_modelMatrix").AsMatrix().SetMatrix(renderable.Matrix.Native);
         effect.NativeEffect.GetVariableByName("_viewMatrix").AsMatrix().SetMatrix(region.Camera.Matrix.Native);
         effect.NativeEffect.GetVariableByName("_projectionMatrix").AsMatrix().SetMatrix(region.Camera.Viewport.Matrix);
         _device.NativeDevice.InputAssembler.PrimitiveTopology = PrimitiveTopology.TriangleList;
         _device.NativeDevice.InputAssembler.SetVertexBuffers(0, renderable.VertexBuffer.NativeBuffers);
         _device.NativeDevice.InputAssembler.SetIndexBuffer(renderable.FaceBuffer.NativeBuffer, Format.R32_UInt, 0);
         effect.NativePass.Apply(_device.NativeDevice);
         _device.NativeDevice.DrawIndexed(renderable.FaceBuffer.count, 0, 0);
      }
   }
}
