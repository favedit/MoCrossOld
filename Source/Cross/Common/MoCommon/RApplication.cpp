#ifdef _MO_WINDOWS
#endif
#ifdef _MO_LINUX
#ifndef __CYGWIN__
#include <execinfo.h>
#include <ucontext.h>
#endif
#include <sys/file.h>
#include <unistd.h>
#include <dlfcn.h>
#ifndef NO_CPP_DEMANGLE
#include <cxxabi.h>
#endif
#ifdef REG_RIP
#define SIGSEGV_STACK_IA64
#elif defined(REG_EIP)
#define SIGSEGV_STACK_X86
#else
#define SIGSEGV_STACK_GENERIC
#endif
#endif
#include "MoCmSystem.h"

#define MO_BACKTRACE_MAXCOUNT 64

MO_NAMESPACE_BEGIN

//============================================================
// <T>获得系统当前路径</T>
//
// @return 系统当前路径
//============================================================
TFsPath RApplication::GetCurrentDirectory(){
   TFsPath path;
#ifdef _MO_WINDOWS
   ::GetCurrentDirectory(path.Size(), path.Memory());
#else
   TChar* pResult = getcwd(path.Memory(), path.Size());
   MO_ASSERT(pResult);
#endif
   path.Fix();
   return path;
}

//============================================================
// <T>获得可执行文件的当前路径。</T>
//
// @return 当前路径
//============================================================
TFsPath RApplication::GetExecuteDirectory(){
   TFsPath path;
#ifdef _MO_WINDOWS
   // TODO:
#else
   TInt length = readlink("//proc/self/exe", path.Memory(), path.Size());
   MO_ASSERT(length < path.Size());
   path.SetLength(length);
#endif
   return path;
}

#ifdef _MO_WINDOWS
//============================================================
// <T>获得当前应用程序的句柄。</T>
//
// @history 100424 MAOCY 创建
//============================================================
TInstance RApplication::Hinstance(){
   return _pInstance->Hinstance();
}

//============================================================
TInt RApplication::CommandShow(){
   return _pInstance->CommandShow();
}
#endif

//============================================================
// <T>安装扑捉器。</T>
//
// @return 处理结果
//============================================================
 TInt RApplication::InstallCatcher(){
   return _pInstance->InstallCatcher();
}

//============================================================
// <T>获得可执行文件的当前路径。</T>
//
// @history 100302 MAOCY 创建
//============================================================
TInt RApplication::InstallDaemon(){
   return _pInstance->InstallDaemon();
}

MO_NAMESPACE_END
