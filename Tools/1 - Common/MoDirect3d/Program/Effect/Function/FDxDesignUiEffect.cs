using MO.Common.Collection;
using SlimDX.Direct3D11;
using SlimDX.DXGI;

namespace MO.DX.Core.Program.Effect.Function
{
   //============================================================
   public class FDxDesignUiEffect : FDxEffect
   {
      //============================================================
      public FDxDesignUiEffect() {
      }

      //============================================================
      public override void Setup() {
         base.Setup();
         FVector<InputElement> elements = new FVector<InputElement>();
         elements.Push(new InputElement("POSITION", 0, Format.R32G32B32A32_Float, 0, 0));
         elements.Push(new InputElement("COLOR", 0, Format.R32G32B32A32_Float, 16, 0));
         elements.Push(new InputElement("COORD", 0, Format.R32G32_Float, 32, 0));
         elements.Push(new InputElement("NORMAL", 0, Format.R32G32B32A32_Float, 40, 0));
         elements.Push(new InputElement("BINORMAL", 0, Format.R32G32B32_Float, 56, 0));
         elements.Push(new InputElement("TANGENT", 0, Format.R32G32B32_Float, 68, 0));
         _nativeInputLayout = new InputLayout(_device.NativeAdapter, _nativePass.Description.Signature, elements.ToArray());
      }
   }
}
