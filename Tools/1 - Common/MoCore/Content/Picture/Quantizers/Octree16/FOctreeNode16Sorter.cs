using System.Collections.Generic;

namespace MO.Core.Content.Picture.Quantizers.Octree16
{
   public class FOctreeNode16Sorter : IComparer<FOctreeNode16>
   {
      public int Compare(FOctreeNode16 source, FOctreeNode16 target) {
         return source.TotalPixelCount - target.TotalPixelCount;
      }
   }
}
