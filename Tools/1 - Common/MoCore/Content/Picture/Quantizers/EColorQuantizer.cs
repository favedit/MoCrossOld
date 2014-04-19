using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MO.Core.Content.Picture.Quantizers
{
   public enum EColorQuantizer : int
   {
      HSB,

      Median,

      Octree,

      Octree16,

      Popularity,

      Uniform,
   }
}
