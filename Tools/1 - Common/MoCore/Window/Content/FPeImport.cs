using System;
using System.Collections.Generic;
using System.Text;
using MO.Core.Window.Core;

namespace MO.Core.Window.Context {
   
   public class FPeImport {

      internal FModuleInfoCollection _modules = new FModuleInfoCollection();

      public FModuleInfoCollection Modules {
         get { return _modules; }
      }

   }

}
