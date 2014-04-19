using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;

namespace MO.Core.Window.Api {

   [StructLayout(LayoutKind.Sequential)]
   public struct SSystemInfo {
      public uint dwOemId;
      public uint dwPageSize;
      public uint lpMinimumApplicationAddress;
      public uint lpMaximumApplicationAddress;
      public uint dwActiveProcessorMask;
      public uint dwNumberOfProcessors;
      public uint dwProcessorType;
      public uint dwAllocationGranularity;
      public uint dwProcessorLevel;
      public uint dwProcessorRevision;
   }

}
