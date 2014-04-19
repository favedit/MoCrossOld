using MO.Common.Collection;
using MO.Common.Geom;
using MO.Common.IO;
using MO.Common.Lang;

namespace MO.Content3d.Resource.Model.Mesh
{
   //============================================================
   // <T>几何体。</T>
   //============================================================
   public class FDrChannel : FObject
   {
      // 顶点列表
      protected FVector<FDrChannelFace> _indexs = new FVector<FDrChannelFace>();

      //============================================================
      // <T>构造网格。</T>
      //============================================================
      public FDrChannel() {
      }

      //============================================================
      // 顶点列表
      public FVector<FDrChannelFace> Indexs {
         get { return _indexs; }
      }

      //============================================================
      public void DataUnserialize(IInput input) {
         // 读取顶点坐标
         int count = input.ReadInt32();
         for (int n = 0; n < count; n++) {
            FDrChannelFace face = new FDrChannelFace();
            face.DataUnserialize(input);
            _indexs.Push(face);
         }
      }
   }
}
