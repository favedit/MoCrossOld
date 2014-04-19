using MO.Common.Collection;

namespace MO.Core.Window.Context.Asm.Code {

   public class FAsmPushw : FAsmOpCode {

      public override bool Parse(FAsmCommand cmd, FBytes memory, int offset) {
         int code = memory[offset];
         if (code == 0x68) {
            // 0110 1000 - Imm16
            cmd.Size = 3;
            cmd.Attributes = new string[] { memory.GetUint16(offset + 1).ToString("X4") };
            return true;
         }
         return false;
      }

   }

}
