
using MO.Common.Collection;

namespace MO.Core.Window.Context.Asm.Code {

   public class FAsmInt : FAsmOpCode {

      public override bool Parse(FAsmCommand command, FBytes memory, int offset) {
         int code = memory[offset];
         if(code == 0xCC){
            // 1100 1100
            return true;
         } else if (code == 0xCD) {
            // 1100 1101
            command.Size = 2;
            command.Attributes = new string[] { memory[offset + 1].ToString("X2") };
            return true;
         }
         return false;
      }
   }

}
