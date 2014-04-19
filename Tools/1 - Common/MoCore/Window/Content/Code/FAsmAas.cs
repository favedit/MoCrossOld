using MO.Common.Collection;

namespace MO.Core.Window.Context.Asm.Code {

   public class FAsmAas : FAsmOpCode {

      // <OpCode flag="00111111" name="Aas" regs="" mode="8086" text="ASCII Adjust AL Register After Substraction" />
      public override bool Parse(FAsmCommand cmd, FBytes memory, int offset) {
         return true;
      }

   }

}
