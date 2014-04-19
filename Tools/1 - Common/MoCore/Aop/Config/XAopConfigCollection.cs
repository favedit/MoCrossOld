using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Content;
using MO.Common.Lang;

namespace MO.Core.Aop.Config {

   public class XAopConfigCollection : XAopNodeCollection<XAopConfig> {

      public XAopConfigCollection() {
         _duplicate = false;
      }

   }

}
