
using MO.Common.Collection;

namespace MO.Core.Window.Context.Asm.Code {

   public class FAsmPop : FAsmOpCode {

      public override bool Parse(FAsmCommand cmd, FBytes memory, int offset) {
         int code = memory[offset];
         if ((code & 0xF8) == 0x58) {
            // 01011rrr
            cmd.Attributes = new string[] { RAsm.Reg32Name(code & 0x03) };
            return true;
         } else if (code == 0x9C) {
            // 10001111oo000mmm
            return true;
         } else if ((code & 0xC7) == 0x07) {
            // 00sss111
            cmd.Attributes = new string[] { RAsm.RegSssName((code & 0x38) >> 3) };
            return true;
         } else if (code == 0x0F) {
            int code2 = memory[offset + 1];
            if ((code2 & 0xC7) == 0x81) {
               // 0000 1111 10ss s001
               cmd.Size = 2;
               cmd.Attributes = new string[] { RAsm.RegSssName((code2 & 0x38) >> 3) };
               return true;
            }
         }
         return false;
      }

   }

}
