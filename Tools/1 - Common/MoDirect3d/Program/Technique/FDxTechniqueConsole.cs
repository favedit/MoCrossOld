using MO.Common.Collection;
using MO.Common.Lang;
using MO.Common.System;
using MO.DX.Core.Device;
using MO.DX.Core.Program.Technique.Function;

namespace MO.DX.Core.Program.Technique
{
   //============================================================
   public class FDxTechniqueConsole : FConsole
   {
      protected FDictionary<FDxTechnique> _techniques = new FDictionary<FDxTechnique>();

      //============================================================
      public FDxTechniqueConsole() {
      }

      //============================================================s
      public FDxTechnique Create(string type) {
         switch(type) {
            case "growing":
               return new FDxGrowingTechnique();
            case "normal.map":
               return new FDxNormalMapTechnique();
            case "light.map":
               return new FDxLightMapTechnique();
            case "simple":
               return new FDxSimpleTechnique();
            case "design":
               return new FDxDesignTechnique();
         }
         throw new FFatalException("Unknown type. (type={0})", type);
      }

      //============================================================s
      public FDxTechnique Get(FDxDevice3D device, string type) {
         FDxTechnique technique = _techniques.Find(type);
         if(null == technique) {
            technique = Create(type);
            technique.Device = device;
            technique.Setup();
            _techniques.Set(type, technique);
         }
         return technique;
      }

      //============================================================s
      public FDxTechnique Find(string type) {
         return _techniques.Find(type);
      }
   }
}
