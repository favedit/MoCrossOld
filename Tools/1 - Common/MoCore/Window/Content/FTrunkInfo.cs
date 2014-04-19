using System;
using System.Collections.Generic;
using System.Text;
using MO.Common.Lang;

namespace MO.Core.Window.Context {

   public class FTrunkInfo {

      public string Name;

      public IntPtr Entry;

      public IntPtr EntryPtr;

      public uint EntryAddress {
         get { return (uint)Entry.ToInt32(); }
      }

      public uint Address;

      public uint Hint;

      public uint MemBaseAddress;
      public uint MemAllocationBase;
      public int MemAllocationProtect;
      public uint MemRegionSize;
      public int MemState;
      public int MemProtect;
      public int MemType;

      public override string ToString() {
         FString info = new FString();
         info.Append(Entry.ToInt32().ToString("X8"));
         info.Append(' ');
         info.Append(Name);
         return info.ToString();
      }

   }

}
