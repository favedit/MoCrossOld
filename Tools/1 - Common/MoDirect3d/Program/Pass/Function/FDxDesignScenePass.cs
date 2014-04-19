using MO.Common.Geom;
using MO.Common.Lang;
using MO.DX.Core.Common;
using MO.DX.Core.Model;
using MO.DX.Core.Program.Effect;
using SlimDX;
using SlimDX.Direct3D11;
using SlimDX.DXGI;

namespace MO.DX.Core.Program.Pass.Function
{
   //============================================================
   public class FDxDesignScenePass : FDxAbsCameraPass
   {
      // 深度纹理
      public FDxBufferedDepth _depthTexture = new FDxBufferedDepth();

      // 标识纹理 (r = renderableId, g = faceId, b = coord.x, a = coord.y)
      public FDxBufferedTexture _textureFlags = new FDxBufferedTexture();

      // 颜色纹理
      public FDxBufferedTexture _textureColor = new FDxBufferedTexture();

      // 位置纹理 (r = position.x, g = position.y, b = position.z)
      public FDxBufferedTexture _texturePosition = new FDxBufferedTexture();

      // 法线纹理 (r = normal.x, g = normal.y, b = normal.z)
      public FDxBufferedTexture _textureNormal = new FDxBufferedTexture();

      // 选中纹理 (w = face_id)
      public FDxBufferedTexture _textureSelected = new FDxBufferedTexture();

      // 目标列表
      public FDxBufferedTexture[] _targets;

      //============================================================
      public FDxDesignScenePass() {
         _name = "design.scene";
      }

      //============================================================
      public FDxBufferedDepth DepthTexture {
         get { return _depthTexture; }
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
      public FDxBufferedTexture TexturePosition {
         get { return _texturePosition; }
      }

      //============================================================
      public FDxBufferedTexture TextureNormal {
         get { return _textureNormal; }
      }

      //============================================================
      public FDxBufferedTexture TextureSelected {
         get { return _textureSelected; }
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
         // 创建渲染目标纹理区 (顶点坐标)
         _texturePosition.Device = _device;
         _texturePosition.NativeFormat = Format.R32G32B32A32_Float;
         _texturePosition.Create(size.Width, size.Height);
         textures.Push(_texturePosition);
         // 创建渲染目标纹理区 (法线)
         _textureNormal.Device = _device;
         _textureNormal.NativeFormat = Format.R8G8B8A8_UNorm;
         _textureNormal.Create(size.Width, size.Height);
         textures.Push(_textureNormal);
         // 创建渲染目标纹理区 (选中)
         _textureSelected.Device = _device;
         _textureSelected.NativeFormat = Format.R32G32B32A32_Float;
         _textureSelected.Create(size.Width, size.Height);
         textures.Push(_textureSelected);
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
         _textureColor.Clear(region.BackgroundColor);
         _texturePosition.Clear(1, 1, 1, 0);
         _textureNormal.Clear(1, 1, 1, 0);
         _textureSelected.Clear(0, 0, 0, 0);
      }

      //============================================================
      public override void DrawRenderable(FDxRegion region, FDxRenderable renderable, FDxEffect effect) {
         if(renderable.IsUi) {
            return;
         }
         // 设置输入数据缓冲
         _device.NativeDevice.InputAssembler.PrimitiveTopology = PrimitiveTopology.TriangleList;
         _device.NativeDevice.InputAssembler.SetVertexBuffers(0, renderable.VertexBuffer.NativeBuffers);
         _device.NativeDevice.InputAssembler.SetIndexBuffer(renderable.FaceBuffer.NativeBuffer, Format.R32_UInt, 0);
         // 设置渲染对象参数
         Vector4 flags = new Vector4(renderable.IsSelected ? 1 : 0, renderable.IsUi ? 1 : 0, 0, 0);
         // 设置渲染对象参数
         FDxGeometry geometry = renderable as FDxGeometry;
         FDxMaterial material = geometry.Material;
         effect.NativeEffect.GetVariableByName("_flags").AsVector().Set(new Vector4(renderable.Id, 0, 0, 0));
         effect.NativeEffect.GetVariableByName("_status").AsVector().Set(flags);
         effect.SetMatrix("_modelMatrix", renderable.ModelMatrix);
         effect.SetNativeMatrix("_viewMatrix", region.Camera.Matrix.Native);
         effect.SetNativeMatrix("_projectionMatrix", region.Camera.Viewport.Matrix);
         effect.NativeEffect.GetVariableByName("_projectionMatrix").AsMatrix().SetMatrix(region.Camera.Viewport.Matrix);
         effect.NativeEffect.GetVariableByName("_cameraPosition").AsVector().Set(new Vector3(region.Camera.Position.X, region.Camera.Position.Y, region.Camera.Position.Z));
         effect.NativeEffect.GetVariableByName("_lightPosition").AsVector().Set(new Vector3(region.LightDirectional.Position.X, region.LightDirectional.Position.Y, region.LightDirectional.Position.Z));
         effect.NativeEffect.GetVariableByName("_lightDirection").AsVector().Set(new Vector3(region.LightDirectional.Camera.Direction.X, region.LightDirectional.Camera.Direction.Y, region.LightDirectional.Camera.Direction.Z));
         // 设置渲染材质参数
         effect.NativeEffect.GetVariableByName("_ambientColor").AsVector().Set(new Vector4(material.AmbientColor.R, material.AmbientColor.G, material.AmbientColor.B, material.AmbientColor.A));
         effect.NativeEffect.GetVariableByName("_diffuseColor").AsVector().Set(new Vector4(material.DiffuseColor.R, material.DiffuseColor.G, material.DiffuseColor.B, material.DiffuseColor.A));
         effect.NativeEffect.GetVariableByName("_specularColor").AsVector().Set(new Vector4(material.SpecularColor.R, material.SpecularColor.G, material.SpecularColor.B, material.SpecularColor.A));
         // 设置纹理列表
         int textureCount = geometry.Material.Textures.Count;
         for (int i = 0; i < textureCount; i++) {
            FDxTexture texture = geometry.Material.Textures[i];
            if (null != texture) {
               string textureName = "_texture" + RString.FirstUpper(texture.TypeName);
               effect.NativeEffect.GetVariableByName(textureName).AsResource().SetResource(texture.NativeResource);
            }
         }
         // 提交效果设置
         effect.NativePass.Apply(_device.NativeDevice);
         // 绘制几何体
         _device.NativeDevice.DrawIndexed(renderable.FaceBuffer.count, 0, 0);
      }
   }
}
