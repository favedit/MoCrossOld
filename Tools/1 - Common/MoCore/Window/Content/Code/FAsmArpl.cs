using MO.Common.Collection;

namespace MO.Core.Window.Context.Asm.Code {

   public class FAsmArpl : FAsmOpCode {

      public override bool Parse(FAsmCommand cmd, FBytes memory, int offset) {
         byte code = memory[offset];
         if (code == 0x63) {
            // 0110 0011 oorrrmmm
            cmd.Size = 2;
            cmd.Attributes = RAsm.OoRrrMmmName(1, memory[offset + 1]);
            return true;
         }
         return false;
      }

   }

}
