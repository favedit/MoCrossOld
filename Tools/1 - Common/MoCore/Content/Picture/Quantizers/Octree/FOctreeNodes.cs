using MO.Common.Collection;

namespace MO.Core.Content.Picture.Quantizers.Octree
{
   public class FOctreeNodes : FVector<FOctreeNode>
   {
      public override string ToString() {
         return "Nodes:" + _count.ToString();
      }
   }
}
