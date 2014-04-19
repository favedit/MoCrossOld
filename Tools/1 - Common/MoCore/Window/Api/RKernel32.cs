using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;

namespace MO.Core.Window.Api {

   public class RKernel32 {

      /* BOOL WINAPI CloseHandle(
       *    __in  HANDLE hObject) */
      [DllImport("KERNEL32.DLL")]
      public static extern bool CloseHandle(
         IntPtr handle);

      /* HANDLE WINAPI CreateRemoteThread(
       *    __in   HANDLE hProcess,
       *    __in   LPSECURITY_ATTRIBUTES lpThreadAttributes,
       *    __in   SIZE_T dwStackSize,
       *    __in   LPTHREAD_START_ROUTINE lpStartAddress,
       *    __in   LPVOID lpParameter,
       *    __in   DWORD dwCreationFlags,
       *    __out  LPDWORD lpThreadId) */
      [DllImport("KERNEL32.DLL")]
      public static extern IntPtr CreateRemoteThread(
         IntPtr hProcess,
         int lpThreadAttributes,
         int dwStackSize,
         uint lpStartAddress,
         IntPtr lpParameter,
         int dwCreationFlags,
         int lpThreadId);

      /* HANDLE WINAPI CreateToolhelp32Snapshot(
       *    __in  DWORD dwFlags,
       *    __in  DWORD th32ProcessID) */
      [DllImport("KERNEL32.DLL")]
      public static extern IntPtr CreateToolhelp32Snapshot(
         ETh32cs dwFlags,
         int th32ProcessID);

      /* BOOL WINAPI FreeLibrary(
       *    __in  HMODULE hModule) */
      [DllImport("kernel32")]
      public extern static bool FreeLibrary(IntPtr hModule);

      /* DWORD WINAPI GetCurrentThreadId(void); */
      [DllImport("KERNEL32.DLL")]
      public static extern int GetCurrentThreadId();

      /* HMODULE GetModuleHandle(
       *    LPCTSTR lpModuleName
       *  ) */
      [DllImport("KERNEL32.DLL")]
      public static extern IntPtr GetModuleHandle(
         string lpModuleName);

      /* BOOL WINAPI GetExitCodeThread(
       *    __in  HANDLE  hThread,
       *    __out LPDWORD lpExitCode) */
      [DllImport("KERNEL32.DLL")]
      public static extern bool GetExitCodeThread(
         IntPtr hThread,
         ref uint lpExitCode);

      /* WINBASEAPI DWORD WINAPI GetLastError(VOID) */
      [DllImport("KERNEL32.DLL")]
      public static extern int GetLastError();

      /* FARPROC WINAPI GetProcAddress(
       *    __in  HMODULE hModule,
       *    __in  LPCSTR lpProcName) */
      [DllImport("kernel32", CharSet = CharSet.Ansi)]
      public extern static IntPtr GetProcAddress(
         IntPtr hModule,
         string lpProcName);

      /* void WINAPI GetSystemInfo(
       *    __out LPSYSTEM_INFO lpSystemInfo) */
      [DllImport("KERNEL32.DLL")]
      public static extern void GetSystemInfo(ref SSystemInfo pSI);

      /* PVOID WINAPI ImageDirectoryEntryToData(
       *    __in   PVOID Base,
       *    __in   BOOLEAN MappedAsImage,
       *    __in   USHORT DirectoryEntry,
       *    __out  PULONG Size) */
      [DllImport("KERNEL32.DLL")]
      public static extern IntPtr ImageDirectoryEntryToData(
         IntPtr Base,
         bool MappedAsImage,
         UInt16 DirectoryEntry,
         ref UInt32 Size);

      /* HMODULE WINAPI LoadLibrary(
       *    __in  LPCTSTR lpFileName) */
      [DllImport("KERNEL32.DLL")]
      public extern static IntPtr LoadLibrary(string lpLibFileName);

      /* BOOL WINAPI Module32First(
       *    __in     HANDLE hSnapshot,
       *    __inout  LPMODULEENTRY32 lpme) */
      [DllImport("KERNEL32.DLL")]
      public static extern bool Module32First(
         IntPtr hSnapshot,
         ref SModuleEntry32 lpme);

      /* BOOL WINAPI Module32Next(
       *    __in   HANDLE hSnapshot,
       *    __out  LPMODULEENTRY32 lpme) */
      [DllImport("KERNEL32.DLL")]
      public static extern bool Module32Next(
         IntPtr hSnapshot,
         ref SModuleEntry32 lpme);

      /* HANDLE WINAPI OpenProcess(
       *    __in  DWORD dwDesiredAccess,
       *    __in  BOOL bInheritHandle,
       *    __in  DWORD dwProcessId) */
      [DllImport("KERNEL32.DLL")]
      public static extern IntPtr OpenProcess(
         EProcessAccess dwDesiredAccess,
         bool bInheritHandle,
         int dwProcessId);

      /* void WINAPI OutputDebugString(
       *    __in_opt LPCTSTR lpOutputString) */
      [DllImport("KERNEL32.DLL", CharSet = CharSet.Auto, SetLastError = true)]
      public static extern void OutputDebugString(char[] message);

      /* BOOL WINAPI Process32First(
       *    __in     HANDLE hSnapshot,
       *    __inout  LPPROCESSENTRY32 lppe) */
      [DllImport("KERNEL32.DLL ")]
      public static extern bool Process32First(
         IntPtr handle,
         ref SProcessEntry32 pe);

      /* BOOL WINAPI Process32Next(
       *    __in   HANDLE hSnapshot,
       *    __out  LPPROCESSENTRY32 lppe) */
      [DllImport("KERNEL32.DLL ")]
      public static extern bool Process32Next(
         IntPtr handle,
         ref SProcessEntry32 pe);

      /* BOOL WINAPI ReadProcessMemory(
       *    __in   HANDLE hProcess,
       *    __in   LPCVOID lpBaseAddress,
       *    __out  LPVOID lpBuffer,
       *    __in   SIZE_T nSize,
       *    __out  SIZE_T* lpNumberOfBytesRead) */
      [DllImport("KERNEL32.DLL", SetLastError = true)]
      public static extern Int32 ReadProcessMemory(
         IntPtr hProcess,
         uint lpBaseAddress,
         byte[] buffer,
         int size,
         out int lpNumberOfBytesRead);

      /* BOOL WINAPI Thread32First(
       *    __in     HANDLE hSnapshot,
       *    __in_out LPTHREADENTRY32 lpte) */
      [DllImport("KERNEL32.DLL")]
      public static extern bool Thread32First(
         IntPtr hSnapshot,
         ref SThreadEntry32 lpte);

      /* BOOL WINAPI Thread32Next(
       *    __in  HANDLE hSnapshot,
       *    __out LPTHREADENTRY32 lpte) */
      [DllImport("KERNEL32.DLL")]
      public static extern bool Thread32Next(
         IntPtr hSnapshot,
         ref SThreadEntry32 lpte);

      /* LPVOID WINAPI VirtualAllocEx(
       *    __in      HANDLE hProcess,
       *    __in_opt  LPVOID lpAddress,
       *    __in      SIZE_T dwSize,
       *    __in      DWORD flAllocationType,
       *    __in      DWORD flProtect) */
      [DllImport("KERNEL32.DLL")]
      public static extern IntPtr VirtualAllocEx(
         IntPtr hProcess,
         int lpAddress,
         uint dwSize,
         int flAllocationType,
         int flProtect);

      /* BOOL WINAPI VirtualFreeEx(
       *    __in  HANDLE hProcess,
       *    __in  LPVOID lpAddress,
       *    __in  SIZE_T dwSize,
       *    __in  DWORD dwFreeType) */
      [DllImport("KERNEL32.DLL")]
      public static extern bool VirtualFreeEx(
         IntPtr hProcess,
         IntPtr lpAddress,
         uint dwSize,
         uint dwFreeType);

      /* BOOL VirtualProtect(
       *    LPVOID lpAddress,
       *    DWORD dwSize,
       *    DWORD flNewProtect,
       *    PDWORD lpflOldProtect) */
      [DllImport("KERNEL32.DLL ")]
      public static extern bool VirtualProtect(
         uint lpAddress,
         uint dwSize,
         uint flNewProtect,
         ref uint lpflOldProtect);

      /* BOOL WINAPI VirtualProtectEx(
       *    __in   HANDLE hProcess,
       *    __in   LPVOID lpAddress,
       *    __in   SIZE_T dwSize,
       *    __in   DWORD flNewProtect,
       *    __out  PDWORD lpflOldProtect) */
      [DllImport("KERNEL32.DLL ")]
      public static extern bool VirtualProtectEx(
         IntPtr hProcess,
         uint lpAddress,
         uint dwSize,
         uint flNewProtect,
         ref uint lpflOldProtect);

      /* SIZE_T WINAPI VirtualQuery(
       *    __in_opt  LPCVOID lpAddress,
       *    __out     PMEMORY_BASIC_INFORMATION lpBuffer,
       *    __in      SIZE_T dwLength) */
      [DllImport("KERNEL32.DLL", SetLastError = true)]
      public static extern int VirtualQuery(
         uint lpAddress,
         ref SMemoryBasicInformation lpBuffer,
         int dwLength);

      /* SIZE_T WINAPI VirtualQueryEx(
       *    __in      HANDLE hProcess,
       *    __in_opt  LPCVOID lpAddress,
       *    __out     PMEMORY_BASIC_INFORMATION lpBuffer,
       *    __in      SIZE_T dwLength) */
      [DllImport("KERNEL32.DLL", SetLastError = true)]
      public static extern int VirtualQueryEx(
         IntPtr hProcess,
         uint lpAddress,
         ref SMemoryBasicInformation lpBuffer,
         int dwLength
      );

      /* DWORD WINAPI WaitForSingleObject(
       *    __in  HANDLE hHandle,
       *    __in  DWORD  dwMilliseconds) */
      [DllImport("KERNEL32.DLL")]
      public static extern uint WaitForSingleObject(
         IntPtr hHandle,
         uint dwMilliseconds);

      /* BOOL WINAPI WriteProcessMemory(
       *    __in   HANDLE hProcess,
       *    __in   LPVOID lpBaseAddress,
       *    __in   LPCVOID lpBuffer,
       *    __in   SIZE_T nSize,
       *    __out  SIZE_T* lpNumberOfBytesWritten) */
      [DllImport("KERNEL32.DLL")]
      public static extern Int32 WriteProcessMemory(
         IntPtr hProcess,
         uint lpBaseAddress,
         byte[] lpBuffer,
         int nSize,
         out int lpNumberOfBytesWritten);

   }

}
