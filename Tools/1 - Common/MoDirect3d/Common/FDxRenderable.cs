using MO.Common.Collection;
using MO.DX.Core.Common.Geom;

namespace MO.DX.Core.Common
{
   //============================================================
   public class FDxRenderable : FDxDrawable
   {
      protected FDxDisplay _display;

      protected string _meshName;

      protected string _materialName;

      protected string _effectName = "automatic";

      protected FDxMaterial _material;

      protected FDxProgram _program;

      protected FDictionary<FDxProgram> _programs = new FDictionary<FDxProgram>();

      protected FDxVertexBuffer _vertexBuffer;

      protected FDxFaceBuffer _faceBuffer;

      protected bool _isSelected;

      protected bool _isUi;

      protected SDxMatrix _storeMatrix = new SDxMatrix();

      //============================================================
      public FDxRenderable() {
      }

      //============================================================
      public FDxDisplay Display {
         get { return _display; }
         set { _display = value; }
      }

      //============================================================
      public string MeshName {
         get { return _meshName; }
      }

      //============================================================
      public FDictionary<FDxProgram> Programs {
         get { return _programs; }
      }

      //============================================================
      public FDxMaterial Material {
         get { return _material; }
      }

      //============================================================
      public FDxVertexBuffer VertexBuffer {
         get { return _vertexBuffer; }
      }

      //============================================================
      public FDxFaceBuffer FaceBuffer {
         get { return _faceBuffer; }
      }

      //============================================================
      public bool IsSelected {
         get { return _isSelected; }
         set { _isSelected = value; }
      }

      //============================================================
      public bool IsUi {
         get { return _isUi; }
         set { _isUi = value; }
      }

      //============================================================
      public void Store() {
         _storeMatrix.Assign(_matrix);
         _storeMatrix.UpdateNative();
      }

      //============================================================
      public void Restore() {
         _matrix.Assign(_storeMatrix);
         _matrix.UpdateNative();
      }

      //============================================================
      public void Update() {
         _modelMatrix.Assign(_matrix);
         if(null != _display) {
            _modelMatrix.Append(_display.ModelMatrix);
         }
      }
   }
}
