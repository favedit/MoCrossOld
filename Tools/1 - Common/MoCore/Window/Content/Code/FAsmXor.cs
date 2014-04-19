
using MO.Common.Collection;

namespace MO.Core.Window.Context.Asm.Code {

   public class FAsmXor : FAsmOpCode {

      public override bool Parse(FAsmCommand command, FBytes memory, int offset) {
         int code = memory[offset];
         if((code & 0xFE) == 0x34) {
            // 0011 010w - Acc,Imm
            return true;
         }else if((code & 0xFE) == 0x32) {
            // 0011 001w oorr rmmm - Reg,Reg
            //33 C5            xor         eax,ebp 
            command.Size = 2;
            int code2 = memory[offset + 1];
            int w = code & 0x01;
            int oo = (code2 & 0xC0) >> 6;
            string des = RAsm.RegName(w, (code2 & 0x38) >> 3);
            string src = RAsm.FuncName(w, oo, code2 & 0x07);
            command.Attributes = new string[] { des, src };
            return true;
         }else if((code & 0xFE) == 0x30) {
            // 0011 000w oorr rmmm - Mem,Reg
            command.Size = 2;
            int code2 = memory[offset + 1];
            int w = code & 0x01;
            int oo = (code2 & 0xC0) >> 6;
            string des = RAsm.RegName(w, (code2 & 0x38) >> 3);
            string src = RAsm.FuncName(w, oo, code2 & 0x07);
            command.Attributes = new string[] { des, src };
            return true;
         } else if ((code & 0xFE) == 0x32) {
            // 0011 001w oor rrmmm - Reg,Mem
            int code2 = memory[offset + 1];
            if((code2 & 0x38) == 0x30) {
               command.Size = 2;
               int w = code & 0x01;
               int oo = (code2 & 0xC0) >> 6;
               string des = RAsm.Reg32Name((code2 & 0x38) >> 3);
               string src = RAsm.FuncName(w, oo, code2 & 0x07);
               command.Attributes = new string[] { des, src };
               return true;
            }
         }else if((code & 0xFE) == 0x82) {
            // 1000 001w oo11 0mmm - Reg,Imm8
            int code2 = memory[offset + 1];
            if ((code2 & 0x38) == 0x30) {
               command.Size = 2;
               int w = code & 0x01;
               int oo = (code2 & 0xC0) >> 6;
               string des = RAsm.Reg32Name((code2 & 0x38) >> 3);
               string src = RAsm.FuncName(w, oo, code2 & 0x07);
               command.Attributes = new string[] { des, src };
               return true;
            }
         }else if((code & 0xFE) == 0x82) {
            int code2 = memory[offset + 1];
            if ((code2 & 0x38) == 0x30) {
               // 1000 001w oo11 0mmm - Mem,Imm8
               command.Size = 2;
               int w = code & 0x01;
               int oo = (code2 & 0xC0) >> 6;
               string des = RAsm.Reg32Name((code2 & 0x38) >> 3);
               string src = RAsm.FuncName(w, oo, code2 & 0x07);
               command.Attributes = new string[] { des, src };
               return true;
            }
         }else if((code & 0xFE) == 0x80) {
            int code2 = memory[offset + 1];
            if ((code2 & 0x38) == 0x30) {
               // 1000 000w oo11 0mmm - Reg,Imm
               command.Size = 2;
               int w = code & 0x01;
               int oo = (code2 & 0xC0) >> 6;
               string des = RAsm.Reg32Name((code2 & 0x38) >> 3);
               string src = RAsm.FuncName(w, oo, code2 & 0x07);
               command.Attributes = new string[] { des, src };
               return true;
            }
         }else if((code & 0xFE) == 0x80) {
            int code2 = memory[offset + 1];
            if ((code2 & 0x38) == 0x30) {
               // 1000 000w oo11 0mmm - Mem,Imm
               command.Size = 2;
               int w = code & 0x01;
               int oo = (code2 & 0xC0) >> 6;
               string des = RAsm.Reg32Name((code2 & 0x38) >> 3);
               string src = RAsm.FuncName(w, oo, code2 & 0x07);
               command.Attributes = new string[] { des, src };
               return true;
            }
         }
         return false;
      }

   }

}
