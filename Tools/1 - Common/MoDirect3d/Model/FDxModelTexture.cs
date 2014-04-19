using MO.DX.Core.Common;
using MO.DX.Core.Resource;
using System;
using System.IO;
using System.Drawing.Imaging;
using SlimDX.Direct3D11;

namespace MO.DX.Core.Model
{
   //============================================================
   public class FDxModelTexture : FDxTexture
   {
      protected FDxRsTexturePack _resource;

      //============================================================
      public FDxModelTexture() {
      }

      //============================================================
      public FDxRsTexturePack Resource {
         get { return _resource; }
      }

      //============================================================
      public void LoadResource(FDxRsTextureBitmapPack resource) {
         using(MemoryStream stream = new MemoryStream()) {
            resource.Bitmap.Save(stream, ImageFormat.Png);
            stream.Position = 0;
            _nativeTexture = Texture2D.FromStream(_device.NativeAdapter, stream, (int)stream.Length);
            _nativeResource = new ShaderResourceView(_device.NativeAdapter, _nativeTexture);
         }
      }
   }
}
