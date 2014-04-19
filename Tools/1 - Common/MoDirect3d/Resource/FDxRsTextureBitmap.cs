using MO.Common.IO;
using MO.DX.Core.Common;

namespace MO.DX.Core.Resource
{
   //============================================================
   public class FDxRsTextureBitmap : FDxObject
   {
      protected int _typeCd;

      protected string _source;

      //============================================================
      public FDxRsTextureBitmap() {
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
      public void Unserialize(IInput input) {
         _typeCd = input.ReadUint8();
         _source = input.ReadString();
      }
   }
}
