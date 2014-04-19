using System;
using System.Diagnostics;
using MO.Core.Window.Api;

namespace MO.Core.Window.Core {
   
   public class RMemory {

      public static int ReadMemory(uint address, byte[] memory) {
         return ReadMemory(Process.GetCurrentProcess().Handle, address, memory, memory.Length);
      }

      public static int ReadMemory(uint address, byte[] memory, int length) {
         return ReadMemory(Process.GetCurrentProcess().Handle, address, memory, length);
      }

      public static int ReadMemory(IntPtr process, uint address, byte[] memory) {
         return ReadMemory(process, address, memory, memory.Length);
      }

      public static int ReadMemory(IntPtr process, uint address, byte[] memory, int length) {
         int read = 0;
         RKernel32.ReadProcessMemory(process, address, memory, length, out read);
         return read;
      }

   }

}
