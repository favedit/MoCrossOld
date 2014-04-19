using System.Collections.Generic;

namespace MO.Core.Content.Picture.Quantizers.Octree
{
   public class FOctreeNodeSorter : IComparer<FOctreeNode>
   {
      public int Compare(FOctreeNode source, FOctreeNode target) {
         return source.ActiveNodesPixelCount - target.ActiveNodesPixelCount;
      }
   }
}
