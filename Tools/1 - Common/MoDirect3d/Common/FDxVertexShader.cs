using System.IO;
using MO.DX.Core.Device;

namespace MO.DX.Core.Common
{
   //============================================================
   public class FDxVertexShader
   {
      protected FDxDevice3D _device;

      //protected VertexShader _nativeShader;

      //============================================================
      public FDxVertexShader(FDxDevice3D device) {
         _device = device;
      }

      //============================================================
      //public VertexShader NativeShader {
         //get { return _nativeShader; }
      //}

      //============================================================
      public void LoadFile(string fileName) {
         using(Stream stream = new FileStream(fileName, FileMode.Open)) {
            //_nativeShader = _device._nativeAdapter.CreateVertexShader(stream);
         }
      }
   }
}
