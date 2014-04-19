using MO.Common.Collection;

namespace MO.Core.Window.Context.Asm.Code {

   public class FAsmInc : FAsmOpCode{

      public override bool Parse(FAsmCommand cmd, FBytes memory, int offset){
         byte code = memory[offset];
         if ((code & 0xF8) == 0x40) {
            // 01000rrr
            cmd.Attributes = new string[] { RAsm.Reg32Name(code & 0x03) };
            return true;
         } else if ((code & 0xFE) == 0xFE) {
            // 1111111woo000mmm
            cmd.Size = 2;
            int code2 = memory[offset + 1];
            int w = code & 0x01;
            int oo = (code2 & 0xC0) >> 6;
            int rrr = (code2 & 0x38) >> 3;
            int mmm = (code2 & 0x07);
            string p1 = RAsm.RegName(w, rrr);
            string p2 = RAsm.FuncName(w, oo, mmm);
            cmd.Attributes = new string[] { p1, p2 };
            return true;
         }
         return false;
      }

   }

}
