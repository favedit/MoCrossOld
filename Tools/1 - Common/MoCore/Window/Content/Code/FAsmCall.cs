
using MO.Common.Collection;
using MO.Common.Lang;

namespace MO.Core.Window.Context.Asm.Code {

   public class FAsmCall : FAsmOpCode {

      public override bool Parse(FAsmCommand cmd, FBytes memory, int offset) {
         int code = memory[offset++];
         if(code == 0xFF) {
            int code2 = memory[offset + 1];
            if((code2 & 0x18) == 0x18) {
               // 1111 1111 oo01 1mmm - MemFar
               return true;
            }
         } else if(code == 0xE8) {
            // 1110 1000 - Near
            cmd.Size = 1 + RInt.BYTE_SIZE;
            cmd.Attributes = new object[] { memory.GetUint32(offset) };
            return true;
         } else if(code == 0x9A) {
            // 1001 1010 - Far
            cmd.Size = 1 + RAsm.W1_BYTES;
            cmd.Attributes = new object[] { memory.GetUint32(offset + 1) };
            return true;
         } else if(code == 0xFF) {
            // 1111 1111 oo01 0mmm - RegWord
            return true;
         } else if(code == 0xFF) {
            int code2 = memory[offset + 1];
            if((code2 & 0x38) == 0x10) {
               // 11111111oo010mmm - MemNear
            }
            return true;
         }
         return false;
      }

   }

}
