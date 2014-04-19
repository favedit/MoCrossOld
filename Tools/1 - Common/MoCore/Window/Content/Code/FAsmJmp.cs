using MO.Common.Collection;

namespace MO.Core.Window.Context.Asm.Code {

   public class FAsmJmp : FAsmOpCode {

      public override bool Parse(FAsmCommand command, FBytes memory, int offset) {
         int code = memory[offset++];
         if(code == 0xFF) {
            int code2 = memory[offset++];
            if((code2 & 0x38) == 0x28) {
               // 1111 1111 oo10 1mmm - MemFar
               command.Size = 2;
            } else if((code2 & 0x38) == 0x20) {
               // 1111 1111 oo10 0mmm - RegWord / MemNear
            }
            return true;
         } else if(code == 0xEB) {
            // 1110 1011 - Short
            return true;
         } else if(code == 0xE9) {
            // 1110 1001 - Near
            return true;
         } else if(code == 0xEA) {
            // 1110 1010 - Far
            return true;
         }
         return false;
      }

   }

}
