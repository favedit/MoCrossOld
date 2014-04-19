using MO.Common.Geom;
using SlimDX;

namespace MO.DX.Core.Common.Geom
{
   //============================================================
   public class SDxMatrix : SFloatMatrix3D
   {
      protected Matrix _native = new Matrix();

      //============================================================
      public Matrix Native {
         get { return _native; }
      }

      //============================================================
      public void UpdateData() {
         data[0] = _native.M11;
         data[1] = _native.M12;
         data[2] = _native.M13;
         data[3] = _native.M14;
         data[4] = _native.M21;
         data[5] = _native.M22;
         data[6] = _native.M23;
         data[7] = _native.M24;
         data[8] = _native.M31;
         data[9] = _native.M32;
         data[10] = _native.M33;
         data[11] = _native.M34;
         data[12] = _native.M41;
         data[13] = _native.M42;
         data[14] = _native.M43;
         data[15] = _native.M44;
      }
      
      //============================================================
      public void UpdateNative() {
         _native.M11 = data[0];
         _native.M12 = data[1];
         _native.M13 = data[2];
         _native.M14 = data[3];
         _native.M21 = data[4];
         _native.M22 = data[5];
         _native.M23 = data[6];
         _native.M24 = data[7];
         _native.M31 = data[8];
         _native.M32 = data[9];
         _native.M33 = data[10];
         _native.M34 = data[11];
         _native.M41 = data[12];
         _native.M42 = data[13];
         _native.M43 = data[14];
         _native.M44 = data[15];
      }

      //============================================================
      public void AssignNative(Matrix matrix) {
         _native.M11 = matrix.M11;
         _native.M12 = matrix.M12;
         _native.M13 = matrix.M13;
         _native.M14 = matrix.M14;
         _native.M21 = matrix.M21;
         _native.M22 = matrix.M22;
         _native.M23 = matrix.M23;
         _native.M24 = matrix.M24;
         _native.M31 = matrix.M31;
         _native.M32 = matrix.M32;
         _native.M33 = matrix.M33;
         _native.M34 = matrix.M34;
         _native.M41 = matrix.M41;
         _native.M42 = matrix.M42;
         _native.M43 = matrix.M43;
         _native.M44 = matrix.M44;
      }
   }
}
