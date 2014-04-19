using MO.Common.Collection;
using MO.Common.Content;

namespace MO.Core.Window.Context.Asm {

   public abstract class FAsmOpObject {

      public abstract void LoadConfig(FXmlNode config);

      public abstract bool Parse(FAsmCommand cmd, FBytes bytes, int offset, int bits);

   }

}
