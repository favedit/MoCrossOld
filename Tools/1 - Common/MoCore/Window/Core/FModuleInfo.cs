using System;
using MO.Core.Window.Core;
using MO.Core.Window.Context;
using System.Runtime.InteropServices;
using MO.Core.Window.Api;
using MO.Common.Lang;

namespace MO.Core.Window.Core {

   public class FModuleInfo {

      private FProcessInfo _process;

      private IntPtr _handle;

      public uint NameAddress;

      public string Name;

      private string _location;

      public uint OriginalFirstThunk;

      public uint TimeDateStamp;

      public uint ForwarderChain;

      public uint FirstThunk;

      public uint BaseAddress;

      public uint BaseSize;

      public uint ModuleID;

      public uint ProcessID;

      public uint GlblcntUsage;

      public uint ProccntUsage;

      private SImageDosHeader _dosHeader;

      private SImageNtHeaders _ntHeaders;

      private FTrunkInfoCollection _trunks;

      private FPeFile _peFile;

      public IntPtr Handle {
         get { return _handle; }
         set { _handle = value; }
      }

      public string Location {
         get { return _location; }
         set { _location = value; }
      }

      public FProcessInfo Process {
         get { return _process; }
         set { _process = value; }
      }

      public FTrunkInfoCollection Trunks {
         get {
            if (_trunks == null) {
               _trunks = new FTrunkInfoCollection();
            }
            return _trunks;
         }
      }

      public SImageDosHeader DosHeader {
         get { return _dosHeader; }
      }

      public SImageNtHeaders NtHeaders {
         get { return _ntHeaders; }
      }

      public FPeFile PeFile {
         get {
            if (_peFile == null) {
               _peFile = new FPeFile(_location);
            }
            return _peFile;
         }
      }

      public bool Open() {
         // Dos header
         SImageDosHeader dosHeader = _process.ReadStructure<SImageDosHeader>(_handle);
         if (dosHeader.e_magic != (uint)EImageSignature.Dos) {
            return false;
         }
         _dosHeader = dosHeader;
         // Nt header
         IntPtr pNtHeader = (IntPtr)(_handle.ToInt32() + dosHeader.e_lfanew);
         SImageNtHeaders ntHeaders = _process.ReadStructure<SImageNtHeaders>(pNtHeader);
         if (ntHeaders.Signature != (uint)EImageSignature.Nt) {
            return false;
         }
         _ntHeaders = ntHeaders;
         // Fetch trunks
         SImageDataDirectory idd = ntHeaders.OptionalHeader.DataDirectory[(int)EImageDirectoryEntry.Import];
         if (idd.VirtualAddress == 0) {
            return false;
         }
         // Import
         uint maddress = (uint)_handle.ToInt32();
         IntPtr pIdHeader = (IntPtr)(maddress + idd.VirtualAddress);
         SImageImportDescriptor impDesc = _process.ReadStructure<SImageImportDescriptor>(pIdHeader);
         if (impDesc.Name == 0) {
            return false;
         }
         // Get module Name
         // IntPtr moduleNamePtr = (IntPtr)(maddress + impDesc.Name);
         // Trunk
         IntPtr pOrgFt = (IntPtr)(maddress + impDesc.OriginalFirstThunk);
         IntPtr pFt = (IntPtr)(maddress + impDesc.FirstThunk);
         int ftSize = Marshal.SizeOf(typeof(SImageThunkData32));
         int miSize = Marshal.SizeOf(typeof(SMemoryBasicInformation));
         _trunks = new FTrunkInfoCollection();
         while (true) {
            SImageThunkData32 origThunk = _process.ReadStructure<SImageThunkData32>(pOrgFt);
            SImageThunkData32 realThunk = _process.ReadStructure<SImageThunkData32>(pFt);
            if (origThunk.Function == 0) {
               break;
            }
            if ((origThunk.Ordinal & 0x80000000) == 0x80000000) {
               break;
            }
            // Read name
            IntPtr pName = (IntPtr)(maddress + origThunk.AddressOfData);
            SImageImportByName byName = _process.ReadStructure<SImageImportByName>(pName);
            if (byName.Name[0] == 0) {
               break;
            }
            // Read memory state
            SMemoryBasicInformation mbi = new SMemoryBasicInformation();
            //RKernel32.VirtualQuery((uint)pFt.ToInt32(), ref mbi, miSize);
            RKernel32.VirtualQueryEx(_process.Handle, realThunk.Function, ref mbi, miSize);
            // TrunkInfo
            FTrunkInfo trunk = new FTrunkInfo();
            trunk.Name = RAscii.GetString(byName.Name);
            trunk.Address = origThunk.Function;
            //info.Entry = (IntPtr)(maddress + origThunk.Function);
            trunk.Entry = (IntPtr)realThunk.Function;
            trunk.EntryPtr = pFt;
            trunk.Hint = byName.Hint;
            trunk.MemAllocationBase = mbi.AllocationBase;
            trunk.MemAllocationProtect = mbi.AllocationProtect;
            trunk.MemBaseAddress = mbi.BaseAddress;
            trunk.MemProtect = mbi.Protect;
            trunk.MemRegionSize = mbi.RegionSize;
            trunk.MemState = mbi.State;
            trunk.MemType = mbi.Type;
            _trunks.Push(trunk);
            // Loop
            pOrgFt = (IntPtr)(pOrgFt.ToInt32() + ftSize);
            pFt = (IntPtr)(pFt.ToInt32() + ftSize);
         }
         return true;
      }

      public override string ToString() {
         return Handle.ToInt32().ToString("X8") + " " + Name + " " + Location;
      }

   }

}
