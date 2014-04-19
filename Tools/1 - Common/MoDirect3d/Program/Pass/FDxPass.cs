using MO.Common.Lang;
using MO.DX.Core.Common;
using MO.DX.Core.Model;
using MO.DX.Core.Program.Effect;
using MO.DX.Core.Program.Technique;
using SlimDX;
using SlimDX.Direct3D11;
using SlimDX.DXGI;

namespace MO.DX.Core.Program.Pass
{
   //============================================================
   public class FDxPass : FDxContextObject
   {
      private static ILogger _logger = RLogger.Find(typeof(FDxPass));

      protected string _space;

      protected string _name;

      protected FDxProgramBinder _binder;

      protected bool _isChanged;

      protected int interval = 1;

      protected bool target;

      protected bool finished;

      protected bool modeBuffer;

      protected bool modeClear;

      protected FDxTechnique _technique;

      protected FDxPassContext _context = new FDxPassContext();

      protected FDxEffectContext _effectContext = new FDxEffectContext();

      //============================================================
      // <T>构造渲染过程。</T>
      //============================================================
      public FDxPass() {
      }

      //============================================================
      public FDxTechnique Technique {
         get { return _technique; }
         set { _technique = value; }
      }

      //============================================================
      public bool IsChanged {
         get { return _isChanged; }
         set { _isChanged = value; }
      }

      //============================================================
      // <T>设置渲染过程。</T>
      //============================================================
      public virtual void Setup() {
         // effectContext.name = name;
         // _binder = binder;
      }

      //============================================================
      // <T>开始当前渲染过程。</T>
      //
      // @param region 区域
      // @param target 是否为最终目标
      //============================================================
      public virtual void Begin(FDxRegion region) {
         _device.SetRenderTarget();
         _device.Clear(0.0f, 0.0f, 0.0f, 1.0f);
      }

      //============================================================
      // <T>清除内容。</T>
      //============================================================
      public virtual void OnDrawBegin() {
         //device.clearPower(region.color);
      }

      //============================================================
      // <T>清除内容。</T>
      //============================================================
      public virtual void OnDrawEnd() {
      }


      //============================================================
      // <T>绘制区域内所有节点集合。</T>
      //
      // @param p:region 区域
      //============================================================
      public virtual void DrawRenderable(FDxRegion region, FDxRenderable renderable, FDxEffect effect) {
         // 设置顶点缓冲
         FDxGeometry geometry = renderable as FDxGeometry;
         effect.NativeEffect.GetVariableByName("_modelMatrix").AsMatrix().SetMatrix(renderable.Matrix.Native);
         effect.NativeEffect.GetVariableByName("_viewMatrix").AsMatrix().SetMatrix(region.Camera.Matrix.Native);
         effect.NativeEffect.GetVariableByName("_projectionMatrix").AsMatrix().SetMatrix(region.Camera.Viewport.Matrix);
         effect.NativeEffect.GetVariableByName("_cameraPosition").AsVector().Set(new Vector3(region.Camera.Position.X, region.Camera.Position.Y, region.Camera.Position.Z));
         effect.NativeEffect.GetVariableByName("_lightPosition").AsVector().Set(new Vector3(region.LightDirectional.Position.X, region.LightDirectional.Position.Y, region.LightDirectional.Position.Z));
         int textureCount = geometry.Material.Textures.Count;
         for (int i = 0; i < textureCount; i++) {
            FDxTexture texture = geometry.Material.Textures[i];
            if (null != texture) {
               string textureName = "_texture" + RString.FirstUpper(texture.TypeName);
               effect.NativeEffect.GetVariableByName(textureName).AsResource().SetResource(texture.NativeResource);
            }
         }
         _device.NativeDevice.InputAssembler.PrimitiveTopology = PrimitiveTopology.TriangleList;
         _device.NativeDevice.InputAssembler.SetVertexBuffers(0, renderable.VertexBuffer.NativeBuffers);
         _device.NativeDevice.InputAssembler.SetIndexBuffer(renderable.FaceBuffer.NativeBuffer, Format.R32_UInt, 0);
         effect.NativePass.Apply(_device.NativeDevice);
         effect.NativeEffect.GetVariableByName("_flags").AsVector().Set(new Vector4(0, 0, 0, 0));
         _device.ModeWireFrame = false;
         _device.NativeDevice.DrawIndexed(renderable.FaceBuffer.count, 0, 0);

         //program.NativeEffect.GetVariableByName("_flags").AsVector().Set(new Vector4(1, 0, 0, 0));
         //_device.ModeWireFrame = true;
         //_device.NativeDevice.DrawIndexed(renderable.FaceBuffer.count, 0, 0);
      }

      //============================================================
      // <T>绘制区域内所有节点集合。</T>
      //
      // @param p:region 区域
      //============================================================
      public virtual void DrawRegion(FDxRegion region) {
         // 绘制处理
         FDxRenderableCollection renderables = region.Renderables;
         int count = renderables.Count;
         for (int n = 0; n < count; n++) {
            FDxRenderable renderable = renderables[n];
            // 设置程序
            FDxProgram program = renderable.Programs.Find(_name);
            if (null == program) {
               program = RDxCore.EffectConsole.Get(_device, _name);
               renderable.Programs.Set(_name, program);
            }
            _device.Program = program;
            // 设置顶点缓冲
            DrawRenderable(region, renderable, program as FDxEffect);
         }
      }

      //============================================================
      // <T>绘制区域内所有节点集合。</T>
      //
      // @param p:region 区域
      //============================================================
      public virtual void Draw(FDxRegion region) {
         // 开始处理
         Begin(region);
         // 绘制处理
         DrawRegion(region);
         // 结束处理
         End(region);
      }

      //============================================================
      // <T>结束当前渲染过程。</T>
      //
      // @param region 区域
      //============================================================
      public virtual void End(FDxRegion region) {
      }

      //============================================================
      // <T>获得信息内容。</T>
      //
      // @return 内容
      //============================================================
      public string toString() {
         return _name;
      }
   }
}
