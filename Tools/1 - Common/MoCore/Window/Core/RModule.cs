using System;
using System.Runtime.InteropServices;
using System.Diagnostics;
using MO.Common.Lang;
using MO.Core.Window.Api;
using MO.Core.Window.Context;

namespace MO.Core.Window.Core {

   public class RModule {

      public static Nullable<SImageDosHeader> GetDosHeader(IntPtr hModule) {
         SImageDosHeader header = (SImageDosHeader)Marshal.PtrToStructure(hModule, typeof(SImageDosHeader));
         if (header.e_magic != (uint)EImageSignature.Dos) {
            return null;
         }
         return header;
      }

      public static Nullable<SImageNtHeaders> GetNtHeaders(IntPtr hModule) {
         Nullable<SImageDosHeader> dosHeader = GetDosHeader(hModule);
         if (dosHeader.HasValue) {
            IntPtr pHeader = (IntPtr)(hModule.ToInt32() + dosHeader.Value.e_lfanew);
            SImageNtHeaders ntHeaders = (SImageNtHeaders)Marshal.PtrToStructure(pHeader, typeof(SImageNtHeaders));
            if (ntHeaders.Signature != (uint)EImageSignature.Nt) {
               return null;
            }
            return ntHeaders;
         }
         return null;
      }

      public static FModuleInfoCollection ListProcess(int processId) {
         FModuleInfoCollection modules = new FModuleInfoCollection();
         List(modules, processId);
         return modules;
      }

      public static bool List(FModuleInfoCollection modules) {
         return List(modules, Process.GetCurrentProcess().Id);
      }

      public static bool List(FModuleInfoCollection modules, int processId) {
         modules.Clear();
         IntPtr hSnap = RKernel32.CreateToolhelp32Snapshot(ETh32cs.SnapModule, processId);
         if (!RApi.IsValidHandle(hSnap)) {
            return false;
         }
         SModuleEntry32 me32 = new SModuleEntry32();
         me32.dwSize = Marshal.SizeOf(me32);
         bool next = RKernel32.Module32First(hSnap, ref me32);
         while (next) {
            FModuleInfo module = new FModuleInfo();
            module.Handle = me32.hModule;
            module.Name = me32.szModule;
            module.Location = me32.szExePath;
            module.BaseAddress = me32.modBaseAddr;
            module.BaseSize = me32.modBaseSize;
            module.ModuleID = me32.th32ModuleID;
            module.GlblcntUsage = me32.GlblcntUsage;
            module.ProccntUsage = me32.ProccntUsage;
            modules.Push(module);
            next = RKernel32.Module32Next(hSnap, ref me32);
         }
         RKernel32.CloseHandle(hSnap);
         return true;
      }

      public static SModuleEntry32[] ListAll() {
         return ListAll(Process.GetCurrentProcess().Id);
      }

      public static SModuleEntry32[] ListAll(int processId) {
         IntPtr hSnap = RKernel32.CreateToolhelp32Snapshot(ETh32cs.SnapModule, processId);
         if (!RApi.IsValidHandle(hSnap)) {
            return null;
         }
         FObjects<SModuleEntry32> modules = new FObjects<SModuleEntry32>();
         SModuleEntry32 me32 = new SModuleEntry32();
         me32.dwSize = Marshal.SizeOf(me32);
         bool next = RKernel32.Module32First(hSnap, ref me32);
         while (next) {
            SModuleEntry32 module = new SModuleEntry32();
            module = me32;
            modules.Push(module);
            next = RKernel32.Module32Next(hSnap, ref me32);
         }
         RKernel32.CloseHandle(hSnap);
         return modules.ToArray();
      }

      public static SModuleEntry32[] ListModule(IntPtr hModule) {
         FObjects<SModuleEntry32> mes = new FObjects<SModuleEntry32>();
         Nullable<SImageNtHeaders> ntHeaders = GetNtHeaders(hModule);
         SImageDataDirectory idd = ntHeaders.Value.OptionalHeader.DataDirectory[(int)EImageDirectoryEntry.Import];
         if (idd.VirtualAddress == 0) {
            return mes.ToArray();
         }
         // Import
         uint maddress = (uint)hModule.ToInt32();
         IntPtr pIdHeader = (IntPtr)(maddress + idd.VirtualAddress);
         int idSize = Marshal.SizeOf(typeof(SImageImportDescriptor));
         while (true) {
            SImageImportDescriptor impDesc = (SImageImportDescriptor)Marshal.PtrToStructure(pIdHeader, typeof(SImageImportDescriptor));
            if (impDesc.Name == 0) {
               break;
            }
            IntPtr namePtr = (IntPtr)(maddress + impDesc.Name);
            SModuleEntry32 me = new SModuleEntry32();
            me.modBaseAddr = impDesc.FirstThunk;
            me.szModule = Marshal.PtrToStringAnsi(namePtr, 260);
            mes.Push(me);
            pIdHeader = (IntPtr)(pIdHeader.ToInt32() + idSize);
         }
         return mes.ToArray();
      }

      public static Nullable<SModuleEntry32> Find(string name) {
         IntPtr hSnap = RKernel32.CreateToolhelp32Snapshot(ETh32cs.SnapModule, 0);
         if (!RApi.IsValidHandle(hSnap)) {
            return null;
         }
         Nullable<SModuleEntry32> module = null;
         SModuleEntry32 me32 = new SModuleEntry32();
         me32.dwSize = Marshal.SizeOf(me32);
         bool next = RKernel32.Module32First(hSnap, ref me32);
         while (next) {
            if (me32.szModule == name) {
               module = me32;
               break;
            }
            next = RKernel32.Module32Next(hSnap, ref me32);
         }
         RKernel32.CloseHandle(hSnap);
         return module;
      }

      public static FTrunkInfo[] FetchTrunks(IntPtr hModule) {
         Nullable<SImageNtHeaders> ntHeaders = GetNtHeaders(hModule);
         SImageDataDirectory idd = ntHeaders.Value.OptionalHeader.DataDirectory[(int)EImageDirectoryEntry.Import];
         if (idd.VirtualAddress == 0) {
            return null;
         }
         // Import
         uint maddress = (uint)hModule.ToInt32();
         IntPtr pIdHeader = (IntPtr)(maddress + idd.VirtualAddress);
         SImageImportDescriptor impDesc = (SImageImportDescriptor)Marshal.PtrToStructure(pIdHeader, typeof(SImageImportDescriptor));
         if (impDesc.Name == 0) {
            return null;
         }
         // Get module Name 
         // IntPtr moduleNamePtr = (IntPtr)(maddress + impDesc.Name);
         // Trunk
         IntPtr pOrgFt = (IntPtr)(maddress + impDesc.OriginalFirstThunk);
         IntPtr pFt = (IntPtr)(maddress + impDesc.FirstThunk);
         int ftSize = Marshal.SizeOf(typeof(SImageThunkData32));
         int miSize = Marshal.SizeOf(typeof(SMemoryBasicInformation));
         FObjects<FTrunkInfo> infos = new FObjects<FTrunkInfo>();
         while (true) {
            SImageThunkData32 origThunk = (SImageThunkData32)Marshal.PtrToStructure(pOrgFt, typeof(SImageThunkData32));
            SImageThunkData32 realThunk = (SImageThunkData32)Marshal.PtrToStructure(pFt, typeof(SImageThunkData32));
            if (origThunk.Function == 0) {
               break;
            }
            if ((origThunk.Ordinal & 0x80000000) == 0x80000000) {
               break;
            }
            /*uint arrd = (uint)(maddress + origThunk.AddressOfData);
            if ((arrd & 0x80000000) == 0x80000000) {
               break;
            }*/
            // Read name
            IntPtr pName = (IntPtr)(maddress + origThunk.AddressOfData);
            SImageImportByName byName = (SImageImportByName)Marshal.PtrToStructure(pName, typeof(SImageImportByName));
            if (byName.Name[0] == 0) {
               break;
            }
            // Read memory state
            SMemoryBasicInformation mbi = new SMemoryBasicInformation();
            //RKernel32.VirtualQuery((uint)pFt.ToInt32(), ref mbi, miSize);
            RKernel32.VirtualQuery(realThunk.Function, ref mbi, miSize);
            // TrunkInfo
            FTrunkInfo info = new FTrunkInfo();
            info.Name = RAscii.GetString(byName.Name);
            info.Address = origThunk.Function;
            //info.Entry = (IntPtr)(maddress + origThunk.Function);
            info.Entry = (IntPtr)realThunk.Function;
            info.Hint = byName.Hint;
            info.MemAllocationBase = mbi.AllocationBase;
            info.MemAllocationProtect = mbi.AllocationProtect;
            info.MemBaseAddress = mbi.BaseAddress;
            info.MemProtect = mbi.Protect;
            info.MemRegionSize = mbi.RegionSize;
            info.MemState = mbi.State;
            info.MemType = mbi.Type;
            infos.Push(info);
            // Loop
            pOrgFt = (IntPtr)(pOrgFt.ToInt32() + ftSize);
            pFt = (IntPtr)(pFt.ToInt32() + ftSize);
         }
         return infos.ToArray();
      }
   }

}
