using MO.Common.IO;
using MO.DX.Core.Common;

namespace MO.DX.Core.Model.Geometry
{
   public class FDxRectangleGeometry : FDxRenderable
   {
      //============================================================
      public FDxRectangleGeometry() {
         _name = "geometry.rectangle";
      }

      //============================================================
      public void Setup() {
         //------------------------------------------------------------
         float[] vertexData = new float[]{
            -1,  1, 0, 1, 0, 0,
            1,  1, 0, 1, 1, 0,
            1, -1, 0, 1, 1, 1,
            -1, -1, 0, 1, 0, 1};
         FByteStream vertexStream = new FByteStream();
         foreach (float value in vertexData) {
            vertexStream.WriteFloat(value);
         }
         // 创建顶点缓冲
         _vertexBuffer = new FDxVertexBuffer();
         _vertexBuffer.Device = _device;
         _vertexBuffer.Upload5(6, vertexStream.Memory, 0, vertexStream.Length);
         //------------------------------------------------------------
         int[] faceData = new int[] { 0, 1, 2, 0, 2, 3 };
         FByteStream faceStream = new FByteStream();
         foreach (int value in faceData) {
            faceStream.WriteInt32(value);
         }
         // 创建面缓冲
         _faceBuffer = new FDxFaceBuffer();
         _faceBuffer.Device = _device;
         _faceBuffer.Upload(faceStream.Memory, 0, faceStream.Length);
      }
   }
}
