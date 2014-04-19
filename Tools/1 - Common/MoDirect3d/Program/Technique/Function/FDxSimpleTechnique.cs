using MO.DX.Core.Device;
using MO.DX.Core.Program.Pass.Function;

namespace MO.DX.Core.Program.Technique.Function
{
   //============================================================
   public class FDxSimpleTechnique : FDxTechnique
   {
      protected FDxSimplePass _passSimple = new FDxSimplePass();
      
      //============================================================
      public FDxSimpleTechnique(){
         _name = "simple";
      }     

      //============================================================
      public override void Setup(){
         base.Setup();
         // 建立颜色渲染
         _passSimple.Technique = this;
         _passSimple.Device = _device;
         _passSimple.Setup();
         RegisterPass(_passSimple);
      }
}
}
