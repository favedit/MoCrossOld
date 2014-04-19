using System;
using System.Runtime.InteropServices;
using System.Security;

namespace MO.Core.Window.Api {

   [StructLayout(LayoutKind.Sequential, Pack = 4)]
   public struct SDetectEncodingInfo {

      public uint nLangID;

      public uint nCodePage;

      public int nDocPercent;

      public int nConfidence;
   }
}
