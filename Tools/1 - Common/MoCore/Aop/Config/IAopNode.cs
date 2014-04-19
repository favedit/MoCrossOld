using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Lang;
using MO.Common.Content;

namespace MO.Core.Aop.Config
{
   public interface IAopNode : IDump
   {
      IAopConfigConsole Console { get; set; }

      string Id { get;}

      void LoadConfig(FXmlNode config);
   }
}
