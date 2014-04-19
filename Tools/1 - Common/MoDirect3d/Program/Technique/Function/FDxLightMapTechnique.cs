using MO.DX.Core.Program.Pass.Function;

namespace MO.DX.Core.Program.Technique.Function
{
   //============================================================
   public class FDxLightMapTechnique : FDxTechnique
   {
      protected FDxLightMapPass _lightPass = new FDxLightMapPass();
      
      //============================================================
      public FDxLightMapTechnique() {
         _name = "light.map";
      }

      //============================================================
      public override void Setup(){
         base.Setup();
         // 建立颜色渲染
         _lightPass.Technique = this;
         _lightPass.Device = _device;
         _lightPass.Setup();
         RegisterPass(_lightPass);
      }
}
}
