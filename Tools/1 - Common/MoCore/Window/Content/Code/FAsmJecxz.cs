using MO.Common.Collection;

namespace MO.Core.Window.Context.Asm.Code {

   public class FAsmJecxz : FAsmOpCode {

      public override bool Parse(FAsmCommand cmd, FBytes memory, int offset) {
         int code = memory[offset++];
         if(code == 0xE3) {
            // 11100011 - Short
            return true;
         }
         return false;
      }

   }

}
