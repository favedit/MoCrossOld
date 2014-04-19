using MO.Common.Collection;

namespace MO.Core.Window.Context.Asm.Code {

   public class FAsmLea : FAsmOpCode {

      public override bool Parse(FAsmCommand cmd, FBytes memory, int offset) {
         byte code = memory[offset];
         if(code == 0x8D) {
            // 1000 1101 oorr rmmm
            cmd.Size = 2;
            return RAsm.ParseOoRrrMmm(cmd, 1, memory[offset + 1], memory, offset + 2);
         }
         return false;
      }

   }

}
