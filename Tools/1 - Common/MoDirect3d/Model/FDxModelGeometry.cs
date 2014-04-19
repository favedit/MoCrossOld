using MO.DX.Core.Common;
using MO.DX.Core.Resource;

namespace MO.DX.Core.Model
{
   //============================================================
   public class FDxModelGeometry : FDxRenderable
   {
      protected FDxRsGeometry _resource;

      //============================================================
      public FDxModelGeometry() {
      }

      //============================================================
      public FDxRsGeometry Resource {
         get { return _resource; }
      }

      //============================================================
      public void LoadResource(FDxRsGeometry resource) {
         _name = resource.Name;
         // 创建顶点缓冲
         _vertexBuffer = new FDxVertexBuffer();
         _vertexBuffer.Device = _device;
         _vertexBuffer.Upload(resource.VertexSize, resource.VertexBuffer.Memory, 0, resource.VertexBuffer.Length);
         // 创建面缓冲
         _faceBuffer = new FDxFaceBuffer();
         _faceBuffer.Device = _device;
         _faceBuffer.Upload(resource.FaceBuffer.Memory, 0, resource.FaceBuffer.Length);
      }
   }
}
