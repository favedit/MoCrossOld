using MO.DX.Core.Common;
using MO.DX.Core.Resource;

namespace MO.DX.Core.Model
{
   //============================================================
   public class FDxGeometry : FDxRenderable
   {
      protected FDxModelGeometry _modelGeometry;

      protected FDxSpatialMaterial _modelMaterial;

      //============================================================
      public FDxGeometry() {
      }

      //============================================================
      public FDxModelGeometry ModelGeometry {
         get { return _modelGeometry; }
         set { _modelGeometry = value; }
      }

      //============================================================
      public FDxSpatialMaterial ModelMaterial {
         get { return _modelMaterial; }
         set {
            _material = value;
            _modelMaterial = value;
         }
      }

      //============================================================
      public void Setup() {
         _vertexBuffer = _modelGeometry.VertexBuffer;
         _faceBuffer = _modelGeometry.FaceBuffer;
      }

      //============================================================
      public void LoadRenderable(FDxRsTemplateRenderable resource) {
         _name = resource.ModelCode + " [" + resource.GeometryName + "]";
         _meshName = resource.GeometryName;
         _matrix.Assign(resource.WorldMatrix);
         _matrix.UpdateForce();
         _modelMatrix.Assign(_matrix);
         _modelMatrix.UpdateNative();
      }
   }
}
