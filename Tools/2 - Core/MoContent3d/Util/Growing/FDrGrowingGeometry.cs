using MO.Common.IO;
using MO.DX.Core.Common;
using MO.DX.Core.Model;

namespace MO.Content3d.Util.Light
{
   public class FDrGrowingGeometry : FDxGeometry
   {
      //============================================================
      public FDrGrowingGeometry() {
         _material = new FDxMaterial();
      }

      //============================================================
      public void LoadResource() {
         // 创建数据
         FByteStream vertexs = new FByteStream();
         float[] vertexData = new float[]{
            -1.0f,  1.0f, 0.0f, 1.0f, 0.0f, 1.0f, 
             1.0f,  1.0f, 0.0f, 1.0f, 1.0f, 1.0f, 
             1.0f, -1.0f, 0.0f, 1.0f, 1.0f, 0.0f, 
            -1.0f, -1.0f, 0.0f, 1.0f, 0.0f, 0.0f, 
         };
         foreach (float value in vertexData) {
            vertexs.WriteFloat(value);
         }
         // 输出索引数据
         FByteStream faces = new FByteStream();
         int[] faceData = new int[]{
            0, 1, 2,
            0, 2, 3,
         };
         foreach (int value in faceData) {
            faces.WriteInt32(value);
         }
         // 创建顶点流
         _vertexBuffer = new FDxVertexBuffer();
         _vertexBuffer.Device = _device;
         _vertexBuffer.UploadNormal(6, vertexs.Memory, 0, vertexs.Length);
         // 创建索引流
         _faceBuffer = new FDxFaceBuffer();
         _faceBuffer.Device = _device;
         _faceBuffer.Upload32(faces.Memory, 0, faces.Length);
      }
   }
}
