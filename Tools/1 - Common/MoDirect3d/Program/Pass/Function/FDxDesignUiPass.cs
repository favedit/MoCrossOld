using MO.Common.Lang;
using MO.DX.Core.Common;
using MO.DX.Core.Model;
using SlimDX;
using SlimDX.Direct3D11;
using SlimDX.DXGI;
using MO.Common.Geom;
using MO.DX.Core.Program.Effect;

namespace MO.DX.Core.Program.Pass.Function
{
   //============================================================
   public class FDxDesignUiPass : FDxAbsCameraPass
   {
      // 深度纹理
      public FDxBufferedDepth _depthTexture = new FDxBufferedDepth();

      // 标识纹理 (r = renderableId, g = faceId, b = coord.x, a = coord.y)
      public FDxBufferedTexture _textureFlags = new FDxBufferedTexture();

      // 标识纹理 (r = renderableId, g = faceId, b = coord.x, a = coord.y)
      public FDxBufferedTexture _textureColor = new FDxBufferedTexture();

      // 目标列表
      public FDxBufferedTexture[] _targets;

      //============================================================
      public FDxDesignUiPass() {
         _name = "design.ui";
      }

      //============================================================
      public FDxBufferedTexture TextureFlags {
         get { return _textureFlags; }
      }

      //============================================================
      public FDxBufferedTexture TextureColor {
         get { return _textureColor; }
      }

      //============================================================
      // <T>设置渲染过程。</T>
      //============================================================
      public override void Setup() {
         base.Setup();
         SIntSize size = new SIntSize(2048, 2048);
         FObjects<FDxBufferedTexture> textures = new FObjects<FDxBufferedTexture>();
         // 创建深度检测区
         _depthTexture.Device = _device;
         _depthTexture.Create(size.Width, size.Height);
         // 创建渲染目标纹理区 (标识)
         _textureFlags.Device = _device;
         _textureFlags.NativeFormat = Format.R32G32B32A32_SInt;
         _textureFlags.Create(size.Width, size.Height);
         textures.Push(_textureFlags);
         // 创建渲染目标纹理区 (颜色，透明度)
         _textureColor.Device = _device;
         _textureColor.NativeFormat = Format.R8G8B8A8_UNorm;
         _textureColor.Create(size.Width, size.Height);
         textures.Push(_textureColor);
         // 建立纹理列表
         _targets = textures.ToArray();
      }
      
      //============================================================
      // <T>开始当前渲染过程。</T>
      //
      // @param region 区域
      // @param target 是否为最终目标
      //============================================================
      public override void Begin(FDxRegion region) {
         _device.SetRenderTarget(_depthTexture, _targets);
         _depthTexture.Clear();
         _textureFlags.Clear(0, 0, 0, 0);
         _textureColor.Clear(0, 0, 0, 0);
      }

      //============================================================
      public override void DrawRenderable(FDxRegion region, FDxRenderable renderable, FDxEffect effect) {
         // 是否为UI对象
         if(!renderable.IsUi) {
            return;
         }
         // 设置输入数据缓冲
         _device.NativeDevice.InputAssembler.PrimitiveTopology = PrimitiveTopology.TriangleList;
         _device.NativeDevice.InputAssembler.SetVertexBuffers(0, renderable.VertexBuffer.NativeBuffers);
         _device.NativeDevice.InputAssembler.SetIndexBuffer(renderable.FaceBuffer.NativeBuffer, Format.R32_UInt, 0);
         // 设置渲染对象参数
         FDxGeometry geometry = renderable as FDxGeometry;
         FDxMaterial material = geometry.Material;
         effect.NativeEffect.GetVariableByName("_flags").AsVector().Set(new Vector4(renderable.Id, 0, 0, 0));
         effect.NativeEffect.GetVariableByName("_status").AsVector().Set(new Vector4(renderable.IsSelected ? 1 : 0, 0, 0, 0));
         effect.SetMatrix("_modelMatrix", renderable.ModelMatrix);
         effect.SetNativeMatrix("_viewMatrix", region.Camera.Matrix.Native);
         effect.SetNativeMatrix("_projectionMatrix", region.Camera.Viewport.Matrix);
         effect.NativeEffect.GetVariableByName("_cameraPosition").AsVector().Set(new Vector3(region.Camera.Position.X, region.Camera.Position.Y, region.Camera.Position.Z));
         effect.NativeEffect.GetVariableByName("_lightPosition").AsVector().Set(new Vector3(region.LightDirectional.Position.X, region.LightDirectional.Position.Y, region.LightDirectional.Position.Z));
         effect.NativeEffect.GetVariableByName("_ambientColor").AsVector().Set(new Vector4(material.AmbientColor.R, material.AmbientColor.G, material.AmbientColor.B, material.AmbientColor.A));
         effect.NativeEffect.GetVariableByName("_diffuseColor").AsVector().Set(new Vector4(material.DiffuseColor.R, material.DiffuseColor.G, material.DiffuseColor.B, material.DiffuseColor.A));
         effect.NativeEffect.GetVariableByName("_specularColor").AsVector().Set(new Vector4(material.SpecularColor.R, material.SpecularColor.G, material.SpecularColor.B, material.SpecularColor.A));
         // 提交效果设置
         effect.NativePass.Apply(_device.NativeDevice);
         // 绘制几何体
         _device.NativeDevice.DrawIndexed(renderable.FaceBuffer.count, 0, 0);
      }
   }
}
