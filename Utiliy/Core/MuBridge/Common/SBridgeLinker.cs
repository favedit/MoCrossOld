using System;
using System.Runtime.InteropServices;

namespace MO.Bridge.Core
{
   //============================================================
   // <T>桥接关联器。</T>
   //
   // @history MAOCY 140512
   //============================================================
   [StructLayout(LayoutKind.Explicit)]
   public struct SBridgeLinker
   {
      // 实例编号
      [FieldOffset(0)]
      public IntPtr pointer;
   }
}
