using MO.Common.Lang;
using MO.DX.Core.Common;

namespace MO.DX.Core.Resource
{
   //============================================================
   public class FDxRsMaterial : FDxContextObject
   {
      protected FObjects<FDxRsTexturePack> _textures = new FObjects<FDxRsTexturePack>();

      //============================================================
      public FDxRsMaterial() {
      }

      //============================================================
      public FObjects<FDxRsTexturePack> Textures {
         get { return _textures; }
      }
   }
}
