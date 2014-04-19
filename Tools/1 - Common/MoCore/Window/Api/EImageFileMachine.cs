using System;
using System.Collections.Generic;
using System.Text;

namespace MO.Core.Window.Api {

   public enum EImageFileMachine : ushort {

      Unknown = 0x0000,
      I386 = 0x014c,
      R3000 = 0x0162,
      R4000 = 0x0166,
      R10000 = 0x0168,
      WCEMIPSV2 = 0x0169,
      Alpha = 0x0184,
      SH3 = 0x01a2,
      SH3DSP = 0x01a3,
      SH3E = 0x01a4,
      SH4 = 0x01a6,
      SH5 = 0x01a8,
      ARM = 0x01c0,
      THUMB = 0x01c2,
      AM33 = 0x01d3,
      PowerPC = 0x01F0,
      PowerPCFP = 0x01f1,
      IA64 = 0x0200,
      MIPS16 = 0x0266,
      Alpha64 = 0x0284,
      MIPSFPU = 0x0366,
      MIPSFPU16 = 0x0466,
      AXP64 = Alpha64,
      Tricore = 0x0520,
      CEF = 0x0CEF,
      EBC = 0x0EBC,
      AMD64 = 0x8664,
      M32R = 0x9041,
      CEE = 0xC0EE

   }

}
