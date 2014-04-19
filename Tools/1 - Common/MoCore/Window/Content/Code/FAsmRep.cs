using MO.Common.Collection;

namespace MO.Core.Window.Context.Asm.Code {

   public class FAsmRep : FAsmOpCode {

      // <OpCode name="Rep" flag="11110011" regs="" mode="8086" text="Repeat Following String Operation" />
      public override bool Parse(FAsmCommand cmd, FBytes memory, int offset) {
         int code = memory[offset];
         if(code == 0xF3) {
            int code2 = memory[offset + 1];
            cmd.Size = 2;
         }
         return true;
      }

   }

}
