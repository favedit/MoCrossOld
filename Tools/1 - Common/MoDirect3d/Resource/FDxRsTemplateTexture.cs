using MO.Common.IO;
using MO.Common.Lang;

namespace MO.DX.Core.Resource
{
   //============================================================
   public class FDxRsTemplateTexture : FObject
   {
      protected int _typeCd;

      protected string _source;

      protected string _sourceType;

      protected FDxRsTexturePack _texture;

      //============================================================
      public FDxRsTemplateTexture() {
      }

      //============================================================
      public int TypeCd {
         get { return _typeCd; }
      }

      //============================================================
      public string Source {
         get { return _source; }
      }

      //============================================================
      public string SourceType {
         get { return _sourceType; }
      }

      //============================================================
      public void Unserialize(IInput input) {
         // 读取设置
         _typeCd = input.ReadUint8();
         _source = input.ReadString();
         _sourceType = input.ReadString();
         // 设置对象
         _texture = RDxCore.TextureResourceConsole.Get(_source);
      }
   }
}
