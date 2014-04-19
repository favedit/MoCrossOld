using MO.Common.Collection;

namespace MO.Core.Window.Context.Asm.Code {

   public class FAsmJcc : FAsmOpCode {

      public override bool Parse(FAsmCommand cmd, FBytes memory, int offset) {
         int code = memory[offset++];
         if((code & 0xF0) == 0x70) {
            // 0111 cccc - Short
            return true;
         } else if(code == 0x0F) {
            int code2 = memory[offset++];
            if((code2 & 0xF0) == 0x80) {
               // 0000 1111 1000 cccc - Near
               cmd.Size = 2;
            }
         }
         return false;
      }

   }

}
