using MO.Common.Collection;

namespace MO.Core.Window.Context.Asm.Code {

   public class FAsmPush : FAsmOpCode {

      public override bool Parse(FAsmCommand command, FBytes memory, int offset) {
         int code = memory[offset];
         if ((code & 0xF8) == 0x50) {
            // 0101 0rrr - RegWord
            command.Attributes = new string[] { RAsm.Reg32Name(code & 0x07) };
            return true;
         } else if (code == 0xFF) {
            int code2 = memory[offset + 1];
            if ((code2 & 0x38) == 0x30) {
               // 1111 1111 oo11 0mmm - MemWord
               command.Size = 2;
               int oo = (code2 & 0xC0) >> 6;
               command.Attributes = new string[] { RAsm.FuncName(1, oo, code2 & 0x07) };
               return true;
            }
         } else if ((code & 0xC7) == 0x06) {
            // 00ss s110 - SegOld
            int sss = (code & 0x38) >> 3;
            command.Attributes = new string[] { RAsm.RegSssName(sss) };
            return true;
         } else if (code == 0x0F) {
            int code2 = memory[offset + 1];
            if ((code2 & 0xC7) == 0x80) {
               // 0000 1111 10ss s000 - Seg
               command.Size = 2;
               int sss = (code & 0x38) >> 3;
               command.Attributes = new string[] { RAsm.RegSssName(sss) };
               return true;
            }
         } else if ((code & 0x6A) == 0x6A) {
            // 01101010 - Imm8
            command.Size = 1 + RAsm.W0_BYTES;
            command.Attributes = new string[] { memory[offset + 1].ToString("X2") };
            return true;
         } else if ((code & 0x68) == 0x68) {
            // 01101000 - Imm
            command.Size = 1 + RAsm.W1_BYTES;
            command.Attributes = new string[] { memory.GetUint32(offset + 1).ToString("X8") };
            return true;
         }
         return false;
      }

   }

}
