
using MO.Common.Collection;
using MO.Common.Lang;

namespace MO.Core.Window.Context.Asm.Code {

   public class FAsmMov : FAsmOpCode {

      public override bool Parse(FAsmCommand command, FBytes memory, int offset) {
         int code = memory[offset];
         if((code & 0xFE) == 0xA2) {
            // 1010 001w - MemOfs,Acc
            return true;
         } else if((code & 0xFE) == 0xA0) {
            // 1010 000w - Acc,MemOfs
            command.Size = 1 + RInt.BYTE_SIZE;
            command.Attributes = new object[] { "EAX", "dword ptr [" + memory.GetUint32(offset + 1).ToString("X8") + "]" };
            return true;
         } else if((code & 0xF0) == 0xB0) {
            // 1011 wrrr - Reg,Imm
            offset++;
            int w = (code & 0x08) >> 3;
            if(w == 0x01) {
               command.Size = 1 + RInt.BYTE_SIZE;
               command.Attributes = new object[] { RAsm.RegName(w, code & 0x07), memory.GetUint32(offset) };
            } else {
               command.Size = 1 + RByte.BYTE_SIZE;
               command.Attributes = new object[] { RAsm.RegName(w, code & 0x07), memory[offset] };
            }
            return true;
         } else if((code & 0xFE) == 0xC6) {
            int code2 = memory[offset + 1];
            if((code2 & 0x38) == 0x00) {
               // 1100 011w oo00 0mmm - Reg,Reg
               command.Size = 2;
               int w = code & 0x01;
               int oo = (code2 & 0xC0) >> 6;
               command.Attributes = new string[] { RAsm.FuncName(w, oo, code2 & 0x07) };
               return true;
            }
         } else if((code & 0xFE) == 0x8A) {
            // 1000 101w oorr rmmm
            int code2 = memory[offset + 1];
            command.Size = 2;
            int w = code & 0x01;
            int oo = (code2 & 0xC0) >> 6;
            string des = RAsm.RegName(w, (code2 & 0x38) >> 3);
            string src = RAsm.FuncName(w, oo, code2 & 0x07);
            command.Attributes = new string[] { des, src };
            return true;
         } else if((code & 0xFE) == 0x8A) {
            // 1000 101w oorr rmmm - Reg,Mem
            int code2 = memory[offset + 1];
            int w = code & 0x01;
            int oo = (code2 & 0xC0) >> 6;
            if(oo == 0x01) {
               command.Size = 3;
            } else {
               command.Size = 2;
            }
            string src = RAsm.RegName(w, (code2 & 0x38) >> 3);
            string des = RAsm.FuncName(w, oo, code2 & 0x07);
            command.Attributes = new string[] { des, src };
            return true;
         } else if((code & 0xFE) == 0x88) {
            // 1000 100w oorr rmmm - Mem,Reg
            int code2 = memory[offset + 1];
            int w = code & 0x01;
            int oo = (code2 & 0xC0) >> 6;
            if(oo == 0x01) {
               command.Size = 3;
            } else {
               command.Size = 2;
            }
            string src = RAsm.RegName(w, (code2 & 0x38) >> 3);
            string des = RAsm.FuncName(w, oo, code2 & 0x07);
            command.Attributes = new string[] { des, src };
            return true;
         } else if(code == 0x8C) {
            // 1000 1100 oosssmmm - Reg16,Seg
            int code2 = memory[offset + 1];
            int w = code & 0x01;
            int oo = (code2 & 0xC0) >> 6;
            if(oo == 0x01) {
               command.Size = 3;
            } else {
               command.Size = 2;
            }
            string src = RAsm.RegName(w, (code2 & 0x38) >> 3);
            string des = RAsm.FuncName(w, oo, code2 & 0x07);
            command.Attributes = new string[] { des, src };
            return true;
         } else if(code == 0x8E) {
            // 1000 1110 ooss smmm - Seg,Reg16
            int code2 = memory[offset + 1];
            int w = code & 0x01;
            int oo = (code2 & 0xC0) >> 6;
            if(oo == 0x01) {
               command.Size = 3;
            } else {
               command.Size = 2;
            }
            string src = RAsm.RegName(w, (code2 & 0x38) >> 3);
            string des = RAsm.FuncName(w, oo, code2 & 0x07);
            command.Attributes = new string[] { des, src };
            return true;
         } else if(code == 0x8C) {
            // 1000 1100 ooss smmm - Mem16,Seg
            int code2 = memory[offset + 1];
            int w = code & 0x01;
            int oo = (code2 & 0xC0) >> 6;
            if(oo == 0x01) {
               command.Size = 3;
            } else {
               command.Size = 2;
            }
            string src = RAsm.RegName(w, (code2 & 0x38) >> 3);
            string des = RAsm.FuncName(w, oo, code2 & 0x07);
            command.Attributes = new string[] { des, src };
            return true;
         } else if(code == 0x8E) {
            // 1000 1110 ooss smmm - Seg,Mem16
            int code2 = memory[offset + 1];
            int w = code & 0x01;
            int oo = (code2 & 0xC0) >> 6;
            if(oo == 0x01) {
               command.Size = 3;
            } else {
               command.Size = 2;
            }
            string src = RAsm.RegName(w, (code2 & 0x38) >> 3);
            string des = RAsm.FuncName(w, oo, code2 & 0x07);
            command.Attributes = new string[] { des, src };
            return true;
         } else if(code == 0x0F) {
            int code2 = memory[offset + 1];
            if(code2 == 0x20) {
               int code3 = memory[offset + 2];
               if((code3 & 0xC0) == 0xC0) {
                  // 0000 1111 0010 0000 11ss srrr - CRn,Reg32
                  command.Size = 3;
                  string des = RAsm.RegSssName((code3 & 0x38) >> 3);
                  string src = RAsm.Reg32Name(code3 & 0x07);
                  command.Attributes = new string[] { des, src };
                  return true;
               }
            } else if(code2 == 0x22) {
               int code3 = memory[offset + 2];
               if((code3 & 0xC0) == 0xC0) {
                  // 0000 1111 0010 0010 11ss srrr
                  command.Size = 3;
                  string des = RAsm.RegSssName((code3 & 0x38) >> 3);
                  string src = RAsm.Reg32Name(code3 & 0x07);
                  command.Attributes = new string[] { des, src };
                  return true;
               }
            } else if(code2 == 0x21) {
               int code3 = memory[offset + 2];
               if((code3 & 0xC0) == 0xC0) {
                  // 0000 1111 0010 0001 11ss srrr - Reg32,DRn
                  command.Size = 3;
                  string des = RAsm.Reg32Name(code3 & 0x07);
                  string src = RAsm.RegSssName((code3 & 0x38) >> 3);
                  command.Attributes = new string[] { des, src };
                  return true;
               }
            } else if(code2 == 0x23) {
               int code3 = memory[offset + 2];
               if((code3 & 0xC0) == 0xC0) {
                  // 0000 1111 0010 0011 11ss srrr - DRn,Reg32
                  command.Size = 3;
                  string src = RAsm.Reg32Name(code3 & 0x07);
                  string des = RAsm.RegSssName((code3 & 0x38) >> 3);
                  command.Attributes = new string[] { des, src };
                  return true;
               }
            } else if(code2 == 0x24) {
               int code3 = memory[offset + 2];
               if((code3 & 0xC0) == 0xC0) {
                  // 0000 1111 0010 0100 11ss srrr - Reg32,TRn
                  command.Size = 3;
                  string src = RAsm.Reg32Name(code3 & 0x07);
                  string des = RAsm.RegSssName((code3 & 0x38) >> 3);
                  command.Attributes = new string[] { des, src };
                  return true;
               }
            } else if(code2 == 0x26) {
               int code3 = memory[offset + 2];
               if((code3 & 0xC0) == 0xC0) {
                  // 0000 1111 0010 0110 11ss srrr - TRn,Reg32
                  command.Size = 3;
                  string src = RAsm.Reg32Name(code3 & 0x07);
                  string des = RAsm.RegSssName((code3 & 0x38) >> 3);
                  command.Attributes = new string[] { des, src };
                  return true;
               }
            }
         }
         return false;
      }

   }

}
