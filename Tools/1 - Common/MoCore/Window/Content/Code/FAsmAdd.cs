using MO.Common.Collection;

namespace MO.Core.Window.Context.Asm.Code {

   public class FAsmAdd : FAsmOpCode {

      public override bool Parse(FAsmCommand cmd, FBytes memory, int offset) {
         byte code = memory[offset++];
         if ((code & 0xFE) == 0x02) {
            // 0000 001w oorrrmmm
            cmd.Size = 2;
            cmd.Attributes = RAsm.OoRrrMmmName(code & 0x01, memory[offset + 1]);
            return true;
         } else if ((code & 0xFE) == 0x00) {
            // 0000 000w oorrrmmm
            cmd.Size = 2;
            cmd.Attributes = RAsm.OoRrrMmmName(code & 0x01, memory[offset + 1]);
            return true;
         } else if ((code & 0xFE) == 0x04) {
            // 0000010w
            if ((code & 0x01) == 0x00) {
               cmd.Size = 1 + RAsm.W0_BYTES;
               cmd.Attributes = new string[] { memory[offset + 1].ToString("X2") };
            } else {
               cmd.Size = 1 + RAsm.W1_BYTES;
               cmd.Attributes = new string[] { memory.GetUint32(offset + 1).ToString("X8") };
            }
            return true;
         } else if ((code & 0xFE) == 0x82) {
            int code2 = memory[offset++];
            if((code2 & 0x38) == 0x00) {
               // 1000 001w oo00 0mmm
               cmd.Size = 2;
               RAsm.ParseOoMmm(cmd, code & 0x01, (code2 & 0xC0) >> 6, code2 & 0x07, memory, offset);
               return true;
            }
         } else if ((code & 0xFE) == 0x80) {
            int code2 = memory[offset + 1];
            if ((code2 & 0x38) == 0x00) {
               // 1000 000w oo000mmm
               cmd.Size = 2;
               return true;
            }
         }
         return false;
      }

   }

}
