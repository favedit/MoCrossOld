using MO.Common.Collection;

namespace MO.Core.Window.Context.Asm.Code {

   public class FAsmAam : FAsmOpCode {

      // <OpCode flag="11010100" name="Aam" regs="Imm8" mode="Pentium" text="ASCII Adjust AX Register After Multiplication" />
      // <OpCode flag="1101010000001010" name="Aam" regs="" mode="8086" text="ASCII Adjust AX Register After Multiplication" />
      public override bool Parse(FAsmCommand cmd, FBytes memory, int offset) {
         return true;
      }

   }

}
