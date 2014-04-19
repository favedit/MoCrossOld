using MO.Common.Collection;

namespace MO.Core.Content.Picture.Quantizers.Octree16
{
   public class FOctreeNode16s : FVector<FOctreeNode16>
   {
      public override string ToString() {
         return "Node16s:" + _count.ToString();
      }
   }
}
