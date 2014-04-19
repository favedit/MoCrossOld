using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MO.DX.Core.Model
{
   public interface IDxModelFactory
   {
      FDxModel CreateModel(string type);

      FDxSpatial CreateSpatial(string type);
   }
}
