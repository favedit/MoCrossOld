using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.System;
using MO.Common.Content;
using MO.Common.Lang;

namespace MO.Core.Aop.Formator {

   /*public class FAopEnvFormator : FFormator {

      public override void LoadConfig(FXmlNode config) {
         base.LoadConfig(config);
      }

      public override bool Format(FFormatorParameters parameters) {
         string value = RString.Nvl(parameters.Value);
         if (value == "application.path") {
            parameters.Value = RSystem.Location();
            return true;
         }
         throw new FFatalException("Unknown value type. (value={0})", parameters.Value);
      }
   }*/

}
