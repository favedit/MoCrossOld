using MO.Common.Collection;
using MO.Common.System;
using MO.DX.Core.Device;
using MO.DX.Core.Resource;

namespace MO.DX.Core.Model
{
   //============================================================
   public class FDxTextureConsole : FConsole
   {
      protected FDictionary<FDxModelTexture> _textures = new FDictionary<FDxModelTexture>();

      //============================================================
      public FDxTextureConsole() {
      }

      //============================================================
      public FDictionary<FDxModelTexture> Textures {
         get { return _textures; }
      }

      //============================================================
      public FDxModelTexture Get(FDxDevice3D device, string code, int typeCd) {
         string name = code + "|" + typeCd;
         FDxModelTexture texture = _textures.Find(name);
         if(null == texture) {
            // 创建模型
            texture = new FDxModelTexture();
            texture.Device = device;
            // 加载模型资源
            FDxRsTexturePack rsTexture = RDxCore.TextureResourceConsole.Get(code);
            FDxRsTextureBitmapPack rsPack = rsTexture.Packs[typeCd];
            texture.LoadResource(rsPack);
            // 存储模型
            _textures.Set(name, texture);
         }
         return texture;
      }
   }
}
