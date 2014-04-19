using MO.Common.Collection;

namespace MO.Core.Window.Context.Asm.Code {

   public class FAsmAnd : FAsmOpCode {

      public override bool Parse(FAsmCommand cmd, FBytes memory, int offset) {
         byte code = memory[offset];
         if ((code & 0xFE) == 0x42) {
            // 0010 001w oorrrmmm
            cmd.Size = 2;
            cmd.Attributes = RAsm.OoRrrMmmName(code & 0x01, memory[offset + 1]);
            return true;
         } else if ((code & 0xFE) == 0x20) {
            // 0010 000w oorrrmmm
            cmd.Size = 2;
            cmd.Attributes = RAsm.OoRrrMmmName(code & 0x01, memory[offset + 1]);
            return true;
         } else if ((code & 0xFE) == 0x22) {
            // 0010 001w oorrrmmm
            cmd.Size = 2;
            cmd.Attributes = RAsm.OoRrrMmmName(code & 0x01, memory[offset + 1]);
            return true;
         } else if ((code & 0xFE) == 0x24) {
            // 0010 010w
            if ((code & 0x01) == 0x00) {
               cmd.Size = 1 + RAsm.W0_BYTES;
               cmd.Attributes = new string[] { memory[offset + 1].ToString("X2") };
            } else {
               cmd.Size = 1 + RAsm.W1_BYTES;
               cmd.Attributes = new string[] { memory.GetUint32(offset + 1).ToString("X8") };
            }
            return true;
         } else if ((code & 0xFE) == 0x82) {
            int code2 = memory[offset + 1];
            if ((code2 & 0x38) == 0x20) {
               // 1000 001w oo100mmm
               cmd.Size = 2;
               cmd.Attributes = RAsm.OoRrrMmmName(code & 0x01, memory[offset + 1]);
               return true;
            }
         } else if ((code & 0xFE) == 0x80) {
            int code2 = memory[offset + 1];
            if ((code2 & 0x38) == 0x20) {
               // 1000 000w oo100mmm
               cmd.Size = 2;
               cmd.Attributes = RAsm.OoRrrMmmName(code & 0x01, memory[offset + 1]);
               return true;
            }
         }
         return false;
      }

   }

}
