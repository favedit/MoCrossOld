using System;
using System.Diagnostics;
using System.Runtime.InteropServices;
using MO.Common.Lang;
using MO.Core.Window.Api;
using MO.Core.Window.Context;

namespace MO.Core.Window.Core {

   public class RProcess {

      public static T PtrToStructure<T>(IntPtr hProcess, IntPtr hAddress) {
         Type type = typeof(T);
         int size = Marshal.SizeOf(type);
         byte[] buffer = new byte[size];
         int readed = 0;
         RKernel32.ReadProcessMemory(hProcess, (uint)hAddress.ToInt32(), buffer, size, out readed);
         if (readed != size) {
            throw new FFatalException("Read process memory error. (size={0} readed={1})", size, readed);
         }
         IntPtr ptr = Marshal.AllocHGlobal(size);
         try {
            Marshal.Copy(buffer, 0, ptr, size);
            return (T)Marshal.PtrToStructure(ptr, type);
         } finally {
            Marshal.FreeHGlobal(ptr);
         }
      }


      public static bool List(FProcessInfoCollection processes) {
         processes.Clear();
         IntPtr hSnap = RKernel32.CreateToolhelp32Snapshot(ETh32cs.SnapProcess, 0);
         if (RApi.IsValidHandle(hSnap)) {
            SProcessEntry32 pe32 = new SProcessEntry32();
            pe32.dwSize = (uint)Marshal.SizeOf(pe32);
            bool next = RKernel32.Process32First(hSnap, ref pe32);
            while (next) {
               FProcessInfo process = new FProcessInfo();
               process.Id = pe32.th32ProcessID;
               process.Threads = (int)pe32.cntThreads;
               process.FileName = pe32.szExeFile;
               process.PriClassBase = pe32.pcPriClassBase;
               processes.Push(process);
               next = RKernel32.Process32Next(hSnap, ref pe32);
            }
            RKernel32.CloseHandle(hSnap);
            return true;
         }
         return false;
      }

      public static bool ListThread(FThreadInfoCollection threads, int processId) {
         threads.Clear();
         IntPtr hSnap = RKernel32.CreateToolhelp32Snapshot(ETh32cs.SnapThread, processId);
         if (RApi.IsValidHandle(hSnap)) {
            SThreadEntry32 te32 = new SThreadEntry32();
            te32.dwSize = Marshal.SizeOf(te32);
            bool next = RKernel32.Thread32First(hSnap, ref te32);
            while (next) {
               if (te32.th32OwnerProcessID == processId) {
                  FThreadInfo thread = new FThreadInfo();
                  thread.ProcessID = te32.th32OwnerProcessID;
                  thread.Id = te32.th32ThreadID;
                  thread.Flags = te32.dwFlags;
                  thread.BasePri = te32.tpBasePri;
                  thread.DeltaPri = te32.tpDeltaPri;
                  thread.Usage = te32.cntUsage;
                  threads.Push(thread);
               }
               next = RKernel32.Thread32Next(hSnap, ref te32);
            }
            RKernel32.CloseHandle(hSnap);
            return true;
         }
         return false;
      }

      public static FProcessInfo[] Fetch() {
         IntPtr hSnap = RKernel32.CreateToolhelp32Snapshot(ETh32cs.SnapProcess, 0);
         if (!RApi.IsValidHandle(hSnap)) {
            return null;
         }
         FObjects<FProcessInfo> process = new FObjects<FProcessInfo>();
         SProcessEntry32 pe32 = new SProcessEntry32();
         pe32.dwSize = (uint)Marshal.SizeOf(pe32);
         bool next = RKernel32.Process32First(hSnap, ref pe32);
         while (next) {
            FProcessInfo info = new FProcessInfo();
            info.Id = pe32.th32ProcessID;
            info.Threads = (int)pe32.cntThreads;
            info.FileName = pe32.szExeFile;
            process.Push(info);
            next = RKernel32.Process32Next(hSnap, ref pe32);
         }
         RKernel32.CloseHandle(hSnap);
         return process.ToArray();
      }

      public static FMemoryInfos ListMemory(int processId) {
         FMemoryInfos memories = new FMemoryInfos();
         ListMemory(memories, processId);
         return memories;
      }

      public static bool ListMemory(FMemoryInfos memories, int processId) {
         memories.Clear();
         // List modules
         FModuleInfoCollection modules = RModule.ListProcess(processId);
         // List memory
         uint address = 0;
         SMemoryBasicInformation mbi = new SMemoryBasicInformation();
         int size = Marshal.SizeOf(mbi);
         IntPtr process = RKernel32.OpenProcess(EProcessAccess.QueryInformation, true, processId);
         if (!RApi.IsValidHandle(process)) {
            return false;
         }
         while (RKernel32.VirtualQueryEx(process, address, ref mbi, size) > 0) {
            FMemoryInfo memory = new FMemoryInfo();
            memory.AllocationBase = mbi.AllocationBase;
            memory.AllocationProtect = mbi.AllocationProtect;
            memory.BaseAddress = mbi.BaseAddress;
            memory.Protect = mbi.Protect;
            memory.RegionSize = mbi.RegionSize;
            memory.State = mbi.State;
            memory.Type = mbi.Type;
            memory.Module = modules.FindByAddress(mbi.AllocationBase);
            memories.Push(memory);
            address = mbi.BaseAddress + mbi.RegionSize;
         };
         RKernel32.CloseHandle(process);
         return true;
      }



   }
}
