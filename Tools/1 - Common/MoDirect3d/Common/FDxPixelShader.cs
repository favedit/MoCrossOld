using System.IO;
using MO.DX.Core.Device;

namespace MO.DX.Core.Common
{
   //============================================================
   public class FDxPixelShader
   {
      protected FDxDevice3D _device;

      //protected PixelShader _nativeShader;

      //============================================================
      public FDxPixelShader(FDxDevice3D device) {
         _device = device;
      }

      //============================================================
      //public PixelShader NativeShader {
      //   get { return _nativeShader; }
      //}

      //============================================================
      public void LoadFile(string fileName) {
         using(Stream stream = new FileStream(fileName, FileMode.Open)) {
            //_nativeShader = _device._nativeAdapter.CreatePixelShader(stream);
         }
      }
   }
}

