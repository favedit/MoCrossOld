using MO.Common.Collection;

namespace MO.Core.Window.Context.Asm.Code {

   public class FAsmAad : FAsmOpCode {

      // <OpCode flag="11010101" name="Aad" regs="Imm8" mode="Pentium" text="ASCII Adjust Register AX Before Division" />
      // <OpCode flag="1101010100001010" name="Aad" regs="" mode="8086" text="ASCII Adjust Register AX Before Division" />
      public override bool Parse(FAsmCommand cmd, FBytes memory, int offset) {
         return true;
      }

   }

}
