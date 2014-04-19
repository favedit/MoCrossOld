using MO.DX.Core.Program.Pass.Function;

namespace MO.DX.Core.Program.Technique.Function
{
   //============================================================
   public class FDxGrowingTechnique : FDxTechnique
   {
      protected FDxGrowingPass _passGrowing = new FDxGrowingPass();
      
      //============================================================
      public FDxGrowingTechnique() {
         _name = "growing";
      }

      //============================================================
      public override void Setup(){
         base.Setup();
         // 建立颜色渲染
         _passGrowing.Technique = this;
         _passGrowing.Device = _device;
         _passGrowing.Setup();
         RegisterPass(_passGrowing);
      }
}
}
