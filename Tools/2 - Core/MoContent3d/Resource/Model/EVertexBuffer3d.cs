using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace MO.Content3d.Resource.Model
{
   public enum EVertexBuffer3d  : int
   {
      Unknown = 0,
      Instance = 1,
      Position = 2,
      Color = 3,
      Coord = 4,
      CoordLight = 5,
      Normal = 6,
      Binormal = 7,
      Tangent = 8,
      BoneIndex = 9,
      BoneWeight = 10,
      Count = 11,
   }
}
