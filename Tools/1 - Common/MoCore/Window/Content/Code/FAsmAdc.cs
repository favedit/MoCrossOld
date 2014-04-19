using MO.Common.Collection;

namespace MO.Core.Window.Context.Asm.Code {

   public class FAsmAdc : FAsmOpCode {

      public override bool Parse(FAsmCommand cmd, FBytes memory, int offset) {
         byte code = memory[offset];
         if ((code & 0xFE) == 0x12) {
            // 0001 001w oorr rmmm
            cmd.Size = 2;
            cmd.Attributes = RAsm.OoRrrMmmName(code & 0x01, memory[offset + 1]);
            return true;
         } else if ((code & 0xFE) == 0x10) {
            // 0001 000w oorr rmmm
            cmd.Size = 2;
            cmd.Attributes = RAsm.OoRrrMmmName(code & 0x01, memory[offset + 1]);
            return true;
         } else if ((code & 0xFE) == 0x14) {
            // 0001 010w
            cmd.Size = 1;
            return true;
         } else if ((code & 0xFE) == 0x82) {
            int code2 = memory[offset + 1];
            if ((code2 & 0x38) == 0x10) {
               // 1000 001w oo01 0mmm
               cmd.Size = 2;
               return true;
            }
         } else if ((code & 0xFE) == 0x80) {
            int code2 = memory[offset + 1];
            if ((code2 & 0x38) == 0x10) {
               // 1000 000w oo01 0mmm
               cmd.Size = 2;
               return true;
            }
         }
         return false;
      }

   }

}
