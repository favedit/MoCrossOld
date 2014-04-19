using MO.Common.Collection;

namespace MO.Core.Window.Context.Asm.Code {

   public class FAsmNop : FAsmOpCode {

      public override bool Parse(FAsmCommand cmd, FBytes memory, int offset) {
         // 10010000
         return true;
      }

   }

}
