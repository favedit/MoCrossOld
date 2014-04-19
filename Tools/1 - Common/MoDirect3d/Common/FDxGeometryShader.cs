using System.IO;
using MO.DX.Core.Device;

namespace MO.DX.Core.Common
{
   //============================================================
   public class FDxGeometryShader
   {
      protected FDxDevice3D _device;

      //protected GeometryShader _nativeShader;

      //============================================================
      public FDxGeometryShader(FDxDevice3D device) {
         _device = device;
      }

      //============================================================
      //public GeometryShader NativeShader {
      //   get { return _nativeShader; }
      //}

      //============================================================
      public void LoadFile(string fileName) {
         using(Stream stream = new FileStream(fileName, FileMode.Open)) {
            //_nativeShader = _device._nativeAdapter.CreateGeometryShader(stream);
         }
      }
   }
}
