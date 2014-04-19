using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace MO.Core.Window.Api
{
   public enum ETernaryRasterOperations
   {
      SrcCopy     = 0x00CC0020, /* dest = source*/
      SrcPaint    = 0x00EE0086, /* dest = source OR dest*/
      SrcAnd      = 0x008800C6, /* dest = source AND dest*/
      SrcInvert   = 0x00660046, /* dest = source XOR dest*/
      SrcErase    = 0x00440328, /* dest = source AND (NOT dest )*/
      NotSrcCopy  = 0x00330008, /* dest = (NOT source)*/
      NotSrcErase = 0x001100A6, /* dest = (NOT src) AND (NOT dest) */
      MergeCopy   = 0x00C000CA, /* dest = (source AND pattern)*/
      MergePaint  = 0x00BB0226, /* dest = (NOT source) OR dest*/
      PatCopy     = 0x00F00021, /* dest = pattern*/
      PatPaint    = 0x00FB0A09, /* dest = DPSnoo*/
      PatInvert   = 0x005A0049, /* dest = pattern XOR dest*/
      DstInvert   = 0x00550009, /* dest = (NOT dest)*/
      Blackness   = 0x00000042, /* dest = BLACK*/
      Whiteness   = 0x00FF0062, /* dest = WHITE*/
   }
}
