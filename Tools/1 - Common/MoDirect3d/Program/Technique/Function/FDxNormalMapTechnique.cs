using MO.DX.Core.Program.Pass.Function;

namespace MO.DX.Core.Program.Technique.Function
{
   //============================================================
   public class FDxNormalMapTechnique : FDxTechnique
   {
      protected FDxNormalMapPass _normalPass = new FDxNormalMapPass();
      
      //============================================================
      public FDxNormalMapTechnique() {
         _name = "normal.map";
      }     

      //============================================================
      public override void Setup(){
         base.Setup();
         // 建立颜色渲染
         _normalPass.Technique = this;
         _normalPass.Device = _device;
         _normalPass.Setup();
         RegisterPass(_normalPass);
      }
}
}
