#include "MoCmSystem.h"

MO_NAMESPACE_BEGIN

// ------------------------------------------------------------
TInt RProcess::CurrentId(){
#ifdef _MO_WINDOWS
   TInt processId = GetCurrentProcessId();
#else
   TInt processId = 0;
#endif
   return processId;
}

// ------------------------------------------------------------
// 检查当前进程权限
TBool RProcess::AdjustProcessPrivileges(EProcessPrivileges privileges){
#ifdef _MO_WINDOWS
   // 得到进程的令牌句柄
   TAny* hToken = NULL;
   TAny* hProcess = GetCurrentProcess();
   if(!OpenProcessToken(hProcess, TOKEN_ADJUST_PRIVILEGES | TOKEN_QUERY, &hToken)) {
      return EFalse;
   }
   // 查询进程的权限
   LPCWSTR szPrivilegesName = NULL;
   switch(privileges){
      case EProcessPrivileges_Debug:
         //szPrivilegesName = SE_DEBUG_NAME;
         break;
   }
   TOKEN_PRIVILEGES tokenPrivileges;
   //if(!LookupPrivilegeValue(NULL, szPrivilegesName, &tokenPrivileges.Privileges[0].Luid)){
      //CloseHandle(hToken);
      //return EFalse;
   //}
   // 判断令牌权限
   tokenPrivileges.PrivilegeCount = 1;
   tokenPrivileges.Privileges[0].Attributes = SE_PRIVILEGE_ENABLED;
   if(!AdjustTokenPrivileges(hToken, EFalse, &tokenPrivileges, sizeof(TOKEN_PRIVILEGES), NULL, NULL)){
      CloseHandle(hToken);
      return EFalse;
   }
   CloseHandle(hToken);
#endif
   return ETrue;
}

// ------------------------------------------------------------
// 检查当前进程总内存
TInt RProcess::GetTotalMemory(){
#ifdef _MO_LINUX
   int fd;
   if((fd = open("/proc/self/status", O_RDONLY)) < 0) {
      perror("open");
      return -1;
   }
   int len = 0;
   char buf[2048];
   for(int i = 0; (i = read(fd, &buf[len], sizeof(buf) - len)) > 0;) {
      len += i;
   }
   close(fd);
   buf[len] = 0;
   char *p;
   TUint vms = 0;
   if((p = strstr(buf, "VmSize:"))) {
      p += 7;
      for(; isblank(*p); ++p) ;
      vms = (TUint)strtoul(p, 0, 10);
   }
   return vms * 1000;
#else
   return 0;
#endif
}

MO_NAMESPACE_END
