using System;
using System.Collections.Generic;
using System.Text;
using System.Runtime.InteropServices;
using MO.Core.Window.Api;
using System.Diagnostics;

namespace MO.Core.Window.Core {

   public static class RAdvapi {

      public const string SE_CREATE_TOKEN_NAME = "SeCreateTokenPrivilege";
      public const string SE_ASSIGNPRIMARYTOKEN_NAME = "SeAssignPrimaryTokenPrivilege";
      public const string SE_LOCK_MEMORY_NAME = "SeLockMemoryPrivilege";
      public const string SE_INCREASE_QUOTA_NAME = "SeIncreaseQuotaPrivilege";
      public const string SE_UNSOLICITED_INPUT_NAME = "SeUnsolicitedInputPrivilege";
      public const string SE_MACHINE_ACCOUNT_NAME = "SeMachineAccountPrivilege";
      public const string SE_TCB_NAME = "SeTcbPrivilege";
      public const string SE_SECURITY_NAME = "SeSecurityPrivilege";
      public const string SE_TAKE_OWNERSHIP_NAME = "SeTakeOwnershipPrivilege";
      public const string SE_LOAD_DRIVER_NAME = "SeLoadDriverPrivilege";
      public const string SE_SYSTEM_PROFILE_NAME = "SeSystemProfilePrivilege";
      public const string SE_SYSTEMTIME_NAME = "SeSystemtimePrivilege";
      public const string SE_PROF_SINGLE_PROCESS_NAME = "SeProfileSingleProcessPrivilege";
      public const string SE_INC_BASE_PRIORITY_NAME = "SeIncreaseBasePriorityPrivilege";
      public const string SE_CREATE_PAGEFILE_NAME = "SeCreatePagefilePrivilege";
      public const string SE_CREATE_PERMANENT_NAME = "SeCreatePermanentPrivilege";
      public const string SE_BACKUP_NAME = "SeBackupPrivilege";
      public const string SE_RESTORE_NAME = "SeRestorePrivilege";
      public const string SE_SHUTDOWN_NAME = "SeShutdownPrivilege";
      public const string SE_DEBUG_NAME = "SeDebugPrivilege";
      public const string SE_AUDIT_NAME = "SeAuditPrivilege";
      public const string SE_SYSTEM_ENVIRONMENT_NAME = "SeSystemEnvironmentPrivilege";
      public const string SE_CHANGE_NOTIFY_NAME = "SeChangeNotifyPrivilege";
      public const string SE_REMOTE_SHUTDOWN_NAME = "SeRemoteShutdownPrivilege";
      public const string SE_UNDOCK_NAME = "SeUndockPrivilege";
      public const string SE_SYNC_AGENT_NAME = "SeSyncAgentPrivilege";
      public const string SE_ENABLE_DELEGATION_NAME = "SeEnableDelegationPrivilege";
      public const string SE_MANAGE_VOLUME_NAME = "SeManageVolumePrivilege";
      public const string SE_IMPERSONATE_NAME = "SeImpersonatePrivilege";
      public const string SE_CREATE_GLOBAL_NAME = "SeCreateGlobalPrivilege";

      public static bool EnablePrivilege(string seName, bool enable) {
         IntPtr hProcess = Process.GetCurrentProcess().Handle;
         return EnablePrivilege(hProcess, seName, enable);
      }

      public static bool EnablePrivilege(IntPtr hProcess, string seName, bool enable) {
         IntPtr hToken = IntPtr.Zero;
         if (!RAdvapi32.OpenProcessToken(hProcess, EProcessToken.Query | EProcessToken.AdjustPrivileges, ref hToken)) {
            int error = RKernel32.GetLastError();
            return false;
         }
         // Check
         STokenPrivileges tokenPrivileges;
         tokenPrivileges.PrivilegeCount = 1;
         if (!RAdvapi32.LookupPrivilegeValueW(null, seName, out tokenPrivileges.Privileges)) {
            return true;
         }
         // Adjust
         tokenPrivileges.Privileges.Attributes = enable ? ESePrivilege.Enabled : ESePrivilege.None;
         if (!RAdvapi32.AdjustTokenPrivileges(hToken, false, ref tokenPrivileges, Marshal.SizeOf(tokenPrivileges), IntPtr.Zero, IntPtr.Zero)) {
            int error = RKernel32.GetLastError();
            return false;
         }
         RKernel32.CloseHandle(hToken);
         // Result
         int lastError = RKernel32.GetLastError();
         return (lastError == RApi.ErrorSuccess);
      }


   }

}
