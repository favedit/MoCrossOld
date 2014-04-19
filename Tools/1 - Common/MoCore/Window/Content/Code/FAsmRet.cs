
using MO.Common.Collection;

namespace MO.Core.Window.Context.Asm.Code {

   public class FAsmRet : FAsmOpCode {

      public override bool Parse(FAsmCommand command, FBytes memory, int offset) {
         int code = memory[offset];
         if(code == 0xC3) {
            // 11000011
            command.Size = 3;
            command.Attributes = new object[] { memory.GetUint16(offset + 1) };
         } else if(code == 0xC2) {
            // 11000010
            command.Size = 3;
            command.Attributes = new object[] { memory.GetUint16(offset + 1) };
         }
         return true;
      }

   }

}
