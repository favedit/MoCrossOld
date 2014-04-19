using MO.Common.Collection;

namespace MO.Core.Window.Context.Asm.Code {

   public class FAsmSub : FAsmOpCode {

      public override bool Parse(FAsmCommand cmd, FBytes memory, int offset) {
         byte code = memory[offset];
         if ((code & 0xFE) == 0x2A) {
            // 0010 101w oorr rmmm - Reg,Reg
            cmd.Size = 2;
            cmd.Attributes = RAsm.OoRrrMmmName(code & 0x01, memory[offset + 1]);
            return true;
         } else if ((code & 0xFE) == 0x28) {
            // 0010 100w oorr rmmm - Mem,Reg
            cmd.Size = 2;
            cmd.Attributes = RAsm.OoRrrMmmName(code & 0x01, memory[offset + 1]);
            return true;
         } else if ((code & 0xFE) == 0x2C) {
            // 0010 110w - Acc,Imm
            cmd.Size = 1;
            return true;
         } else if ((code & 0xFE) == 0x82) {
            int code2 = memory[offset + 1];
            if ((code2 & 0x38) == 0x28) {
               // 1000 001w oo10 1mmm - Reg,Imm8
               cmd.Size = 2;
               return true;
            }
         } else if ((code & 0xFE) == 0x80) {
            int code2 = memory[offset + 1];
            if ((code2 & 0x38) == 0x28) {
               // 1000 000w oo10 1mmm - Reg,Imm
               cmd.Size = 2 + RAsm.GetWordBytes(code & 0x01);
               int oo = (code2 & 0xC0) >> 6;
               string des = RAsm.FuncName(code & 0x01, oo, code2 & 0x07);
               cmd.Attributes = new object[] { des, memory.GetUint32(offset + 2) };
               return true;
            }
         }
         return false;
      }

   }

}
