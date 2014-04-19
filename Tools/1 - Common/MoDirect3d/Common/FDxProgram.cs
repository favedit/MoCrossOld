using MO.Common.Lang;
using MO.DX.Core.Device;
using SlimDX.D3DCompiler;
using SlimDX.Direct3D11;
using SlimDX.DXGI;

namespace MO.DX.Core.Common
{
   //============================================================
   public class FDxProgram : FDxContextObject
   {
      protected FDxVertexShader _vertexShader;

      protected FDxGeometryShader _geometryShader;

      protected FDxPixelShader _pixelShader;

      protected InputLayout _nativeInputLayout;

      protected Effect _nativeEffect;

      protected EffectPass _nativePass;

      protected EffectTechnique _nativeTechnique;

      //============================================================
      public FDxProgram() {
      }

      //============================================================
      public Effect NativeEffect {
         get { return _nativeEffect; }
      }

      //============================================================
      public EffectPass NativePass {
         get { return _nativePass; }
      }

      //============================================================
      public EffectTechnique NativeTechnique {
         get { return _nativeTechnique; }
      }

      //============================================================
      public void LoadFile(string fileName) {
         try{
            var bytecode = ShaderBytecode.CompileFromFile(fileName, "fx_5_0", ShaderFlags.None, EffectFlags.None);
            _nativeEffect = new Effect(_device.NativeAdapter, bytecode);
         }catch (System.Exception ex){
            throw new FFatalException(ex);
         }
         _nativeTechnique = _nativeEffect.GetTechniqueByIndex(0);
         _nativePass = _nativeTechnique.GetPassByIndex(0);
      }

      //============================================================
      public InputLayout NativeInputLayout {
         get { return _nativeInputLayout; }
      }

      //============================================================
      public FDxVertexShader VertexShader {
         get { return _vertexShader; }
         set { _vertexShader = value; }
      }

      //============================================================
      public FDxGeometryShader GeometryShader {
         get { return _geometryShader; }
         set { _geometryShader = value; }
      }

      //============================================================
      public FDxPixelShader PixelShader {
         get { return _pixelShader; }
         set { _pixelShader = value; }
      }

      //============================================================
      public void LoadVertexShader(string fileName) {
         _vertexShader = new FDxVertexShader(_device);
         _vertexShader.LoadFile(fileName);
      }

      //============================================================
      public void LoadGeometryShader(string fileName) {
         _geometryShader = new FDxGeometryShader(_device);
         _geometryShader.LoadFile(fileName);
      }

      //============================================================
      public void LoadPixelShader(string fileName) {
         _pixelShader = new FDxPixelShader(_device);
         _pixelShader.LoadFile(fileName);
      }
   }
}
