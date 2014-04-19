using System;
using System.Collections.Generic;
using System.Text;
using System.Reflection;
using MO.Common.Content;
using MO.Common.Lang;
using MO.Common.Lang.Reflection;
using MO.Core.Aop.Component;

namespace MO.Core.Aop.Config {

   [Serializable]
   public class XAopComponents : XAbsAopComponent {

      public const string TAG = "Components";

      public override void LoadConfig(FXmlNode config) {
         base.LoadConfig(config);
      }
   
   }

}
