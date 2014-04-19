using MO.Common.IO;
using MO.Content3d.Resource.Model.Mesh;
using MO.DX.Core.Common;
using MO.DX.Core.Model;

namespace MO.Design3d.Util.Normal
{
   public class FDsNormalGeometry : FDxGeometry
   {
      //============================================================
      public FDsNormalGeometry() {
      }

      //============================================================
      public void LoadResource(FDrGeometry geometry) {
         // 创建数据
         int faceCount = geometry.FaceList.Count;
         FByteStream vertexs = new FByteStream(4 * 10 * 3 * faceCount);
         FByteStream faces = new FByteStream(3 * faceCount);
         for(int f = 0; f < faceCount; f++) {
            FDrFace face = geometry.FaceList[f];
            for (int n = 0; n < 3; n++) {
               // 输出顶点数据
               FDrVertex vertex = geometry.VertexList[face.VertexIndex[n]];
               FDrCoord coord = geometry.CoordList[face.CoordIndex[n]];
               FDrNormal normal = geometry.NormalList[face.NormalIndex[n]];
               vertexs.WriteFloat(vertex.Position.X);
               vertexs.WriteFloat(vertex.Position.Y);
               vertexs.WriteFloat(vertex.Position.Z);
               vertexs.WriteFloat(1.0f);
               vertexs.WriteFloat(coord.Coord.X);
               vertexs.WriteFloat(coord.Coord.Y + 1);
               vertexs.WriteFloat(normal.Direction.X);
               vertexs.WriteFloat(normal.Direction.Y);
               vertexs.WriteFloat(normal.Direction.Z);
               vertexs.WriteFloat(1.0f);
               // 输出索引数据
               faces.WriteInt32(3 * f + n);
            }
         }
         // 创建顶点流
         _vertexBuffer = new FDxVertexBuffer();
         _vertexBuffer.Device = _device;
         _vertexBuffer.UploadNormal(10, vertexs.Memory, 0, vertexs.Length);
         // 创建索引流
         _faceBuffer = new FDxFaceBuffer();
         _faceBuffer.Device = _device;
         _faceBuffer.Upload32(faces.Memory, 0, faces.Length);
      }
   }
}
