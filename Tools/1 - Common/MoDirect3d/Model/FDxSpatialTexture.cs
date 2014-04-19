using MO.DX.Core.Common;
using MO.DX.Core.Resource;

namespace MO.DX.Core.Model
{
      //============================================================
   public class FDxSpatialTexture : FDxTexture
   {
      protected FDxModelTexture _texture;

      //============================================================
      public FDxSpatialTexture() {
      }

      //============================================================
      public FDxModelTexture Texture {
         get { return _texture; }
      }

      //============================================================
      public void LoadResource(FDxRsTemplateTexture resource) {
         _typeName = EDxTexture.ToName(resource.TypeCd);
         int packCd = EDxTexture.ToPack(resource.TypeCd);
         FDxModelTexture _texture = RDxCore.TextureConsole.Get(_device, resource.Source, packCd);
         _nativeTexture = _texture.NativeTexture;
         _nativeResource = _texture.NativeResource;
      }
   }
}
