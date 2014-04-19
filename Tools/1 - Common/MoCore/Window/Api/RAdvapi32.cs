using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;

namespace MO.Core.Window.Api {

   public static class RAdvapi32 {

      /* WINADVAPI BOOL WINAPI AdjustTokenPrivileges (
       *    __in      HANDLE TokenHandle,
       *    __in      BOOL DisableAllPrivileges,
       *    __in_opt  PTOKEN_PRIVILEGES NewState,
       *    __in      DWORD BufferLength,
       *    __out_bcount_part_opt(BufferLength, *ReturnLength) PTOKEN_PRIVILEGES PreviousState,
       *    __out_opt PDWORD ReturnLength
       * ) */
      [DllImport("Advapi32.dll", ExactSpelling = true, SetLastError = true)]
      public static extern bool AdjustTokenPrivileges(
          IntPtr TokenHandle,
          bool DisableAllPrivileges,
          ref STokenPrivileges NewState,
          int BufferLength,
          IntPtr PreviousState,
          IntPtr ReturnLength);

      /* WINADVAPI BOOL WINAPI LookupPrivilegeValueA(
       *    __in_opt LPCSTR lpSystemName,
       *    __in     LPCSTR lpName,
       *    __out    PLUID   lpLuid
       * ) */
      [DllImport("Advapi32.dll", ExactSpelling = true, SetLastError = true, CharSet = CharSet.Ansi)]
      public static extern bool LookupPrivilegeValueA(
         string lpSystemName,
         string lpName,
         ref SLuidAndAttributes lpLuid);

      /* WINADVAPI BOOL WINAPI LookupPrivilegeValueW(
       *    __in_opt LPCWSTR lpSystemName,
       *    __in     LPCWSTR lpName,
       *    __out    PLUID   lpLuid
       * ) */
      [DllImport("Advapi32.dll", ExactSpelling = true, SetLastError = true, CharSet = CharSet.Unicode)]
      public static extern bool LookupPrivilegeValueW(
         string lpSystemName,
         string lpName,
         out SLuidAndAttributes lpLuid);

      /* WINADVAPI BOOL WINAPI OpenProcessToken (
       *    __in        HANDLE ProcessHandle,
       *    __in        DWORD DesiredAccess,
       *    __deref_out PHANDLE TokenHandle
       * ) */
      [DllImport("Advapi32.dll", ExactSpelling = true, SetLastError = true)]
      public static extern bool OpenProcessToken(
         IntPtr ProcessHandle,
         EProcessToken DesiredAccess,
         ref IntPtr TokenHandle);

   }

}
