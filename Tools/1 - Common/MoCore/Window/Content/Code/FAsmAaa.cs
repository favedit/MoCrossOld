using MO.Common.Collection;

namespace MO.Core.Window.Context.Asm.Code {

   public class FAsmAaa : FAsmOpCode {

      //<OpCode flag="00110111" name="Aaa" regs="" mode="8086" text="ASCII Adjust After Addition" />
      public override bool Parse(FAsmCommand cmd, FBytes memory, int offset) {
         return true;
      }

   }

}
