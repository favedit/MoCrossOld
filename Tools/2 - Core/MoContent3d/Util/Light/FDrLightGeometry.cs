using MO.Common.Geom;
using MO.Common.IO;
using MO.DX.Core.Common;
using MO.DX.Core.Model;
using MO.Content3d.Resource.Model.Mesh;

namespace MO.Content3d.Util.Light
{
   public class FDrLightGeometry : FDxGeometry
   {
      //============================================================
      public FDrLightGeometry() {
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
               float coordX = coord.Coord.X;
               float coordY = coord.Coord.Y + 1.0f;
               if (geometry.Channels.Count > 1) {
                  FDrChannelFace channelFace = geometry.Channels[2].Indexs[face.Index];
                  SFloatPoint3 channelPoint = channelFace.Points[n];
                  coordX = channelPoint.X;
                  coordY = channelPoint.Y;
               }
               FDrColor color = geometry.ColorList[face.ColorIndex[n]];
               vertexs.WriteFloat(vertex.Position.X);
               vertexs.WriteFloat(vertex.Position.Y);
               vertexs.WriteFloat(vertex.Position.Z);
               vertexs.WriteFloat(1.0f);
               vertexs.WriteFloat(coordX);
               vertexs.WriteFloat(coordY);
               vertexs.WriteFloat(color.Color.R);
               vertexs.WriteFloat(color.Color.G);
               vertexs.WriteFloat(color.Color.B);
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
