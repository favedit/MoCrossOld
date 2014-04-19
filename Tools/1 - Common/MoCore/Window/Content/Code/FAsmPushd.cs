using MO.Common.Collection;

namespace MO.Core.Window.Context.Asm.Code {

   public class FAsmPushd : FAsmOpCode {

      public override bool Parse(FAsmCommand cmd, FBytes memory, int offset) {
         int code = memory[offset];
         if (code == 0x68) {
            // 0110 1000 - Imm32
            cmd.Size = 1 + RAsm.W1_BYTES;
            cmd.Attributes = new string[] { memory.GetUint32(offset + 1).ToString("X8") };
            return true;
         }
         return false;
      }

   }

}
