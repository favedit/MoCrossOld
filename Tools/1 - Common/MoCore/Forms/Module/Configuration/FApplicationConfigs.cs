using MO.Common.Collection;

namespace MO.Core.Forms.Common
{

   public class FApplicationConfigs : FDictionary<FApplicationConfig> {

      public override FApplicationConfig Get(string name) {
         FApplicationConfig config = base.Get(name);
         if (config == null) {
            config = new FApplicationConfig(name);
            Set(name, config);
         }
         return config;
      }
   }

}
