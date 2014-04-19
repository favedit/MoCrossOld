
using MO.Common.Collection;
using MO.Common.Lang;

namespace MO.Core.Window.Context.Asm {

   public class RAsm {

      public const int W0_BYTES = 1;
      public const int W1_BYTES = 4;

      public static int GetWordBytes(int w) {
         return (w == 0) ? W0_BYTES : W1_BYTES;
      }

      public const int REG_EAX = 0x00; // AL, AX, EAX

      public const int REG_ECX = 0x01; // CL, CX, ECX

      public const int REG_EDX = 0x02; // DL, DX, EDX

      public const int REG_EBX = 0x03; // BL, BX, EBX

      public const int REG_ESP = 0x04; // AH, SP, ESP

      public const int REG_EBP = 0x05; // CH, BP, EBP

      public const int REG_ESI = 0x06; // DH, SI, ESI

      public const int REG_EDI = 0x07; // BH, DI, EDI

      public static bool ParseOoMmm(FAsmCommand cmd, int w, int oo, int mmm, FBytes memory, int offset) {
         switch(oo) {
            case 0x00:
               if(mmm == 0x06) {
                  cmd.Size += RInt.BYTE_SIZE;
                  cmd.Attributes = new object[] { memory.GetInt32(offset) };
               }
               return true;
            case 0x01:
               cmd.Size += RByte.BYTE_SIZE;
               cmd.Attributes = new object[] { memory[offset] };
               return true;
            case 0x02:
               cmd.Size += RInt.BYTE_SIZE;
               cmd.Attributes = new object[] { memory.GetInt32(offset) };
               return true;
            case 0x03:
               cmd.Size += RByte.BYTE_SIZE;
               string des = RAsm.RegName(w, mmm);
               cmd.Attributes = new object[] { des, memory[offset] };
               return true;
         }
         return false;
      }

      public static bool ParseOoRrrMmm(FAsmCommand cmd, int w, int code, FBytes memory, int offset) {
         int oo = (code & 0xC0) >> 6;
         int rrr = (code & 0x38) >> 3;
         int mmm = (code & 0x07);
         switch(oo) {
            case 0x00:
               if(mmm == 0x06) {
                  cmd.Size += RInt.BYTE_SIZE;
                  cmd.Attributes = new object[] { memory.GetInt32(offset) };
               }
               return true;
            case 0x01:
               cmd.Size += RByte.BYTE_SIZE;
               cmd.Attributes = new object[] { RAsm.RegName(w, rrr), memory[offset] };
               return true;
            case 0x02:
               cmd.Size += RInt.BYTE_SIZE;
               cmd.Attributes = new object[] { RAsm.RegName(w, rrr), memory.GetInt32(offset) };
               return true;
            case 0x03:
               string des = RAsm.RegName(w, rrr);
               string src = RAsm.FuncName(w, oo, mmm);
               cmd.Attributes = new object[] { des, src };
               return true;
         }
         return false;
      }

      public static string[] OoRrrMmmName(int w, int code) {
         int oo = (code & 0xC0) >> 6;
         int rrr = (code & 0x38) >> 3;
         int mmm = (code & 0x07);
         string p1 = RAsm.RegName(w, rrr);
         string p2 = RAsm.FuncName(w, oo, mmm);
         return new string[] { p1, p2 };
      }

      public static string RegSssName(int flag) {
         switch (flag) {
            case 0x00:
               return "ES";
            case 0x01:
               return "CS";
            case 0x02:
               return "SS";
            case 0x03:
               return "DS";
            case 0x04:
               return "FS";
            case 0x05:
               return "GS";
         }
         return "??";
      }
      
      public static string RegName(int w, int flag) {
         if (w == 1) {
            return Reg32Name(flag);
         }
         return Reg8Name(flag);
      }

      public static string Reg8Name(int flag) {
         switch(flag) {
            case REG_EAX:
               return "AL";
            case REG_ECX:
               return "CL";
            case REG_EDX:
               return "DL";
            case REG_EBX:
               return "BL";
            case REG_ESP:
               return "AH";
            case REG_EBP:
               return "CH";
            case REG_ESI:
               return "DH";
            case REG_EDI:
               return "BH";
         }
         return "??";
      }

      public static string Reg16Name(int flag) {
         switch(flag) {
            case REG_EAX:
               return "AX";
            case REG_ECX:
               return "CX";
            case REG_EDX:
               return "DX";
            case REG_EBX:
               return "BX";
            case REG_ESP:
               return "SP";
            case REG_EBP:
               return "BP";
            case REG_ESI:
               return "SI";
            case REG_EDI:
               return "DI";
         }
         return "??";
      }

      public static string Reg32Name(int flag) {
         switch(flag) {
            case REG_EAX:
               return "EAX";
            case REG_ECX:
               return "ECX";
            case REG_EDX:
               return "EDX";
            case REG_EBX:
               return "EBX";
            case REG_ESP:
               return "ESP";
            case REG_EBP:
               return "EBP";
            case REG_ESI:
               return "ESI";
            case REG_EDI:
               return "EDI";
         }
         return "???";
      }

      public static string FuncName(int w, int oo, int flag) {
         if (oo == 0x03) {
            return RegName(w, flag);
         }
         return FuncMmmName(flag);
      }

      public static string FuncMmmName(int flag) {
         switch(flag) {
            case 0x00:
               return "DS:[BX+SI]";
            case 0x01:
               return "DS:[BX+DI]";
            case 0x02:
               return "SS:[BP+SI]";
            case 0x03:
               return "SS:[BP+DI]";
            case 0x04:
               return "DS:[SI]";
            case 0x05:
               return "DS:[DI]";
            case 0x06:
               return "SS:[BP]";
            case 0x07:
               return "DS:[BX]";
         }
         return "??";
      }

      public FAsmCommands Parse(byte[] data) {
         FAsmParser parser = new FAsmParser();
         return parser.Parse(0, data, 0, data.Length);
      }

   }

}
