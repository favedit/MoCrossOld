using MO.DX.Core.Common;
using SlimDX.Direct3D11;
using MO.Common.IO;
using MO.Common.Lang;
using MO.Common.Collection;

namespace MO.DX.Core.Resource
{
   //============================================================
   public class FDxRsTexturePack : FDxObject
   {
      private static ILogger _logger = RLogger.Find(typeof(FDxRsTexturePack));

      protected string _name;

      protected FObjects<FDxRsTextureBitmap> _bitmaps = new FObjects<FDxRsTextureBitmap>();

      protected FVector<FDxRsTextureBitmapPack> _packs = new FVector<FDxRsTextureBitmapPack>();

      //============================================================
      public FDxRsTexturePack() {
      }

      //============================================================
      public string Name {
         get { return _name; }
      }

      //============================================================
      public FObjects<FDxRsTextureBitmap> Bitmaps {
         get { return _bitmaps; }
      }

      //============================================================
      public FVector<FDxRsTextureBitmapPack> Packs {
         get { return _packs; }
      }

      //============================================================
      public void Unserialize(IInput input) {
         _name = input.ReadString();
         //............................................................
         // 读取材质列表
         int bitmapCount = input.ReadInt16();
         for(int n = 0; n < bitmapCount; n++) {
            FDxRsTextureBitmap bitmap = new FDxRsTextureBitmap();
            bitmap.Unserialize(input);
            _bitmaps.Push(bitmap);
         }
         //............................................................
         // 读取材质列表
         int count = input.ReadUint8();
         for(int n = 0; n < count; n++) {
            // 读取数据
            int packTypeCd = input.ReadUint8();
            FDxRsTextureBitmapPack pack = null;
            if(EDxTexture.Environment == packTypeCd) {
               pack = new FDxRsTextureCubePack();
            } else {
               pack = new FDxRsTextureBitmapPack();
            }
            pack.Unserialize(input);
            _packs.SetExtend(packTypeCd, pack);
         }
      }

      //============================================================
      public void LoadFile(string fileName) {
         _logger.Debug(this, "LoadFile", "Load resource texture file. (file_name={0})", fileName);
         FCompressFile file = new FCompressFile();
         file.BlockDecompress(fileName);
         Unserialize(file);
      }
   }
}
