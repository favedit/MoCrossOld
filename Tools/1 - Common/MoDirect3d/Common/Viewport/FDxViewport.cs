using SlimDX;

namespace MO.DX.Core.Common
{
   //============================================================
   public class FDxViewport
   {
      protected Matrix _matrix = new Matrix();

      public float angle = 45;

      public float width = 800;

      public float height = 600;

      public float znear = 1.0f;

      public float zfar = 5001.0f;

      //============================================================
      public FDxViewport() {
      }

      //============================================================
      public Matrix Matrix {
         get { return _matrix; }
      }

      //============================================================
      public void Update() {
         _matrix = Matrix.PerspectiveFovLH(angle, width / height, znear, zfar);
      }
   }
}
