#ifdef _MO_WINDOWS
#endif
#ifdef _MO_LINUX
#ifndef __CYGWIN__
#include <execinfo.h>
#include <ucontext.h>
#endif
#ifndef NO_CPP_DEMANGLE
#include <cxxabi.h>
#endif
#include <sys/file.h>
#include <unistd.h>
#include <dlfcn.h>
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
FListeners* FApplication::_pInterrruptListeners = NULL;
FListeners* FApplication::_pReloadListeners = NULL;
FListeners* FApplication::_pUnloadListeners = NULL;
FListeners* FApplication::_pShutdownListeners = NULL;

//============================================================
// <T>构造应用程序管理。</T>
//============================================================
FApplication::FApplication(){
   _pParameters = MO_PTR_CREATE(FApplicationParameters);
#ifdef _MO_WINDOWS
   _hInstance = NULL;
   _commandShow = 0;
#endif
}

//============================================================
// <T>析构应用程序管理。</T>
//============================================================
FApplication::~FApplication(){
   MO_PTR_DELETE(_pParameters);
   MO_PTR_DELETE(_pInterrruptListeners);
   MO_PTR_DELETE(_pReloadListeners);
   MO_PTR_DELETE(_pUnloadListeners);
   MO_PTR_DELETE(_pShutdownListeners);
}

#ifdef _MO_WINDOWS
//============================================================
// <T>获得当前应用程序的句柄。</T>
//
// @history 100424 MAOCY 创建
//============================================================
TInstance FApplication::Hinstance(){
   return _hInstance;
}

//============================================================
void FApplication::SetHinstance(HINSTANCE instance){
   _hInstance = instance;
}

//============================================================
TInt FApplication::CommandShow(){
   return _commandShow;
}

//============================================================
void FApplication::SetCommandShow(TInt commandShow){
   _commandShow = commandShow;
}
#endif

//============================================================
// <T>处理例外信号。</T>
//
// @param signum 中断号
// @param info 信息
// @param ptr 指针
//============================================================
#ifdef _MO_LINUX
void FApplication::OnCapture(int signum, siginfo_t* info, TAny* ptr){
   // 处理子进程状态改变信号
   if(SIGCLD == signum){
      MO_STATIC_ERROR(TC("Application capture signal. (code=%d:%s)"), signum, strsignal(signum));
      MO_STATIC_DUMP(TC("Application capture signal. (code=%d:%s)"), signum, strsignal(signum));
#ifndef __CYGWIN__
      wait();
#endif
      return;
   }
   //............................................................
   TFsDump dump;
   dump.Append(TC("Application failure being. -----------------------------------------------------\n"));
   RSystem::Dump(&dump);
   //............................................................
   // 获得参数
#ifndef __CYGWIN__
   // 基础信息
   dump.AppendFormat("System crash. [ *>o<* ] you are die!!!\n");
   dump.AppendFormat("Signal failure. (code=%d:%s)\n", signum, strsignal(signum));
   dump.AppendFormat("   info.si_signo = %d\n", info->si_signo);
   dump.AppendFormat("   info.si_errno = %d\n", info->si_errno);
   dump.AppendFormat("   info.si_code  = %d\n", info->si_code);
   dump.AppendFormat("   info.si_addr  = %p\n", info->si_addr);
   // 寄存器信息
   ucontext_t* pUcontext = (ucontext_t*)ptr;
   TInt columnCount = 5;
   TInt rowCount = NGREG / columnCount;
   if(NGREG % columnCount != 0){
      rowCount++;
   }
   if(rowCount > 0){
      TInt n = 0;
      for(TInt y = 0; y < rowCount; y++){
         dump.Append("   -- ");
         for(TInt x = 0; x < columnCount; x++){
            dump.AppendFormat("reg[%02" MO_FMT_INT "] = 0x%16" MO_FMT_HEX, n, pUcontext->uc_mcontext.gregs[n]);
            if(x != columnCount - 1){
               dump.Append("  |  ");
            }
            n++;
         }
         dump.Append("\n");
      }
   }
#endif
   //............................................................
   // 显示堆栈
   dump.Append("Stack trace begin.\n");
   RSystem::FetchStackDetail(&dump);
   dump.Append("Stack trace end.\n");
   dump.Append(TC("Application failure end. -------------------------------------------------------"));
   MO_STATIC_ERROR(TC("Application capture.\n%s"), (TCharC*)dump);
   MO_STATIC_DUMP(TC("Application capture.\n%s"), (TCharC*)dump);
   // 刷新日志
   RLoggerManager::Instance().Flush();
   //............................................................
   // 卸载所有信号
   RApplication::Instance().UninstallCatcher();
   //............................................................
   // 处理终端监听
   if(NULL != _pInterrruptListeners){
      _pInterrruptListeners->Process();
      _pInterrruptListeners->Clear();
   }
   // 刷新日志
   RLoggerManager::Instance().Flush();
   //............................................................
   // 处理关闭监听
   if(NULL != _pShutdownListeners){
      _pShutdownListeners->Process();
      _pShutdownListeners->Clear();
   }
   //............................................................
   // 产生崩溃，生成core文件用
   abort();
}
#endif // _MO_LINUX

//============================================================
// <T>处理例外信号。</T>
//
// @param signum 中断号
// @param info 信息
// @param ptr 指针
//============================================================
#ifdef _MO_LINUX
void FApplication::OnReload(int signum, siginfo_t* info, TAny* ptr){
   // 刷新日志
   RLoggerManager::Instance().Flush();
   MO_STATIC_INFO("Reload application begin. ------------------------------------------------------");
   if(NULL != _pReloadListeners){
      _pReloadListeners->Process();
   }
   MO_STATIC_INFO("Reload application end. --------------------------------------------------------");
   // 刷新日志
   RLoggerManager::Instance().Flush();
}
#endif

//============================================================
// <T>处理例外信号。</T>
//
// @param signum 中断号
// @param info 信息
// @param ptr 指针
//============================================================
#ifdef _MO_LINUX
void FApplication::OnUnload(int signum, siginfo_t* info, TAny* ptr){
   // 刷新日志
   RLoggerManager::Instance().Flush();
   MO_STATIC_INFO("Unload application begin. ------------------------------------------------------");
   if(NULL != _pUnloadListeners){
      _pUnloadListeners->Process();
   }
   MO_STATIC_INFO("Unload application end. --------------------------------------------------------");
   // 刷新日志
   RLoggerManager::Instance().Flush();
   // 正常退出
   exit(0);
}
#endif

//============================================================
// <T>处理结束信号。</T>
//
// @param signum 中断号
// @param info 信息
// @param ptr 指针
//============================================================
#ifdef _MO_LINUX
void FApplication::OnTerminate(int signum, siginfo_t* info, TAny* ptr){
   // 刷新日志
   RLoggerManager::Instance().Flush();
   // 卸载所有信号
   RApplication::Instance().UninstallCatcher();
   //............................................................
   // 终止处理
   MO_STATIC_INFO("Terminate application begin. ---------------------------------------------------");
   if(NULL != _pUnloadListeners){
      _pUnloadListeners->Process();
      _pUnloadListeners->Clear();
   }
   MO_STATIC_INFO("Terminate application end. -----------------------------------------------------");
   RLoggerManager::Instance().Flush();
   //............................................................
   // 处理关闭监听
   if(NULL != _pShutdownListeners){
      _pShutdownListeners->Process();
      _pShutdownListeners->Clear();
   }
   //............................................................
   // 离开程序
   exit(ESuccess);
}
#endif

//============================================================
// <T>获得中断监听器集合。</T>
//
// @return 监听器集合
//============================================================
FListeners* FApplication::InterruptListeners(){
   if(NULL == _pInterrruptListeners){
      _pInterrruptListeners = MO_PTR_CREATE(FListeners);
   }
   return _pInterrruptListeners;
}

//============================================================
// <T>获得重新加载监听器集合。</T>
//
// @return 监听器集合
//============================================================
FListeners* FApplication::ReloadListeners(){
   if(NULL == _pReloadListeners){
      _pReloadListeners = MO_PTR_CREATE(FListeners);
   }
   return _pReloadListeners;
}

//============================================================
// <T>获得卸载监听器集合。</T>
//
// @return 监听器集合
//============================================================
FListeners* FApplication::UnloadListeners(){
   if(NULL == _pUnloadListeners){
      _pUnloadListeners = MO_PTR_CREATE(FListeners);
   }
   return _pUnloadListeners;
}

//============================================================
// <T>获得关闭监听器集合。</T>
//
// @return 监听器集合
//============================================================
FListeners* FApplication::ShutdownListeners(){
   if(NULL == _pShutdownListeners){
      _pShutdownListeners = MO_PTR_CREATE(FListeners);
   }
   return _pShutdownListeners;
}

//============================================================
// <T>拦截无效内存错误。</T>
//
// @history 100412 MAOCY 创建
//============================================================
TInt FApplication::InstallCatcher(){
   // 安装信号捕捉器
#ifdef _MO_LINUX
   struct sigaction action;
   memset(&action, 0, sizeof(struct sigaction));
#ifndef __CYGWIN__
   action.sa_flags = SA_SIGINFO;
   action.sa_sigaction = OnCapture;
#endif
   // 1:本信号在用户终端连接(正常或非正常)结束时发出,
   if(sigaction(SIGHUP, &action, NULL) < 0){
      perror("sigaction");
      return EError;
   }
   // 3: 当用户在终端上按退出键（一般采用Ctrl-/）时，产生此信号，并送至前台进程组中的所有进程。
   //    此信号不仅终止前台进程组（如SIGINT所做的那样），同时产生一个core文件。
   if(sigaction(SIGQUIT, &action, NULL) < 0){
      perror("sigaction");
      return EError;
   }
   // 4: 此信号指示进程已执行一条非法硬件指令。
   if(sigaction(SIGILL, &action, NULL) < 0){
      perror("sigaction");
      return EError;
   }
   // 5:指示一个实现定义的硬件故障。
   if(sigaction(SIGTRAP, &action, NULL) < 0){
      perror("sigaction");
      return EError;
   }
   // 6:调用Abort函数时产生此信号
   if(sigaction(SIGABRT, &action, NULL) < 0){
      perror("sigaction");
      return EError;
   }
   // 7:指示一个实现定义的硬件故障
   if(sigaction(SIGBUS, &action, NULL) < 0){
      perror("sigaction");
      return EError;
   }
   // 8:此信号表示一个算术运算异常，例如除以0，浮点溢出等。
   if(sigaction(SIGFPE, &action, NULL) < 0){
      perror("sigaction");
      return EError;
   }
   // 9:用来立即结束程序的运行. 本信号不能被阻塞、处理和忽略。
   // 如果管理员发现某个进程终止不了，可尝试发送这个信号。
   //if(sigaction(SIGKILL, &action, NULL) < 0){
   //   perror("sigaction");
   //   return EError;
   //}
   // 11:指示进程进行了一次无效的存储访问。
   if(sigaction(SIGSEGV, &action, NULL) < 0){
      perror("sigaction");
      return EError;
   }
   // 13:忽略管道破裂信号
   signal(SIGPIPE, SIG_IGN);
   // 14:时钟定时信号, 计算的是实际的时间或时钟时间. alarm函数使用该信号.
   //if(sigaction(SIGALRM, &action, NULL) < 0){
   //   perror("sigaction");
   //   return EError;
   //}
#ifndef __CYGWIN__
   // 16:堆栈错
   if(sigaction(SIGSTKFLT, &action, NULL) < 0){
      perror("sigaction");
      return EError;
   }
#endif
   // 18:让一个停止(stopped)的进程继续执行. 本信号不能被阻塞。
   // if(sigaction(SIGCONT, &action, NULL) < 0){
   //    perror("sigaction");
   //    return EError;
   // }
   // 20:停止进程的运行, 但该信号可以被处理和忽略。（控制终端（tty）上按下停止键 ）
   signal(SIGTSTP, SIG_IGN);
   // 23:有"紧急"数据或out-of-band数据到达socket时产生.
   signal(SIGURG, SIG_IGN);
   // 20:子进程结束时, 父进程会收到这个信号。
   if(sigaction(SIGCHLD, &action, NULL) < 0){
      perror("sigaction");
      return EError;
   }
   // 21:当后台作业要从用户终端读数据时, 该作业中的所有进程会收到SIGTTIN信号. 缺省时这些进程会停止执行.
   signal(SIGTTIN, SIG_IGN);
   // 22:类似于SIGTTIN, 但在写终端(或修改终端模式)时收到。
   signal(SIGTTOU, SIG_IGN);
   // 24:超过CPU时间资源限制. 这个限制可以由getrlimit/setrlimit来读取/改变。
   if(sigaction(SIGXCPU, &action, NULL) < 0){
      perror("sigaction");
      return EError;
   }
   // 25:当进程企图扩大文件以至于超过文件大小资源限制。
   if(sigaction(SIGXFSZ, &action, NULL) < 0){
      perror("sigaction");
      return EError;
   }
   // 29:文件描述符准备就绪, 可以开始进行输入/输出操作.
   if(sigaction(SIGIO, &action, NULL) < 0){
      perror("sigaction");
      return EError;
   }
   // 30:Power failure
   if(sigaction(SIGPWR, &action, NULL) < 0){
      perror("sigaction");
      return EError;
   }
   // 31:非法的系统调用。
   if(sigaction(SIGSYS, &action, NULL) < 0){
      perror("sigaction");
      return EError;
   }
   //............................................................
   // 2:程序终止(interrupt)信号, 在用户键入INTR字符(通常是Ctrl-C)时发出，用于通知前台进程组终止进程。
   struct sigaction actionInt;
   memset(&actionInt, 0, sizeof(struct sigaction));
#ifndef __CYGWIN__
   actionInt.sa_flags = SA_SIGINFO;
   actionInt.sa_sigaction = OnTerminate;
#endif
   if(sigaction(SIGINT, &actionInt, NULL) < 0){
      perror("sigaction");
      return EError;
   }
   // 15:程序结束(terminate)信号, 与SIGKILL不同的是该信号可以被阻塞和处理。
   //    通常用来要求程序自己正常退出，shell命令kill缺省产生这个信号。
   //    如果进程终止不了，我们才会尝试SIGKILL。
   struct sigaction actionTerm;
   memset(&actionTerm, 0, sizeof(struct sigaction));
#ifndef __CYGWIN__
   actionTerm.sa_flags = SA_SIGINFO;
   actionTerm.sa_sigaction = OnTerminate;
#endif
   if(sigaction(SIGTERM, &actionTerm, NULL) < 0){
      perror("sigaction");
      return EError;
   }
   //............................................................
   // 安装User1信号为重载
   struct sigaction actionUser1;
   memset(&actionUser1, 0, sizeof(struct sigaction));
#ifndef __CYGWIN__
   actionUser1.sa_flags = SA_SIGINFO;
   actionUser1.sa_sigaction = OnReload;
#endif
   if(sigaction(SIGUSR1, &actionUser1, NULL) < 0){
      perror("sigaction");
      return EError;
   }
   //............................................................
   // 31:安装User2信号为卸载
   struct sigaction actionUser2;
   memset(&actionUser2, 0, sizeof(struct sigaction));
#ifndef __CYGWIN__
   actionUser2.sa_flags = SA_SIGINFO;
   actionUser2.sa_sigaction = OnUnload;
#endif
   if(sigaction(SIGUSR2, &actionUser2, NULL) < 0){
      perror("sigaction");
      return EError;
   }
#endif
   return ESuccess;
}

//============================================================
// <T>拦截无效内存错误。</T>
//
// @history 100412 MAOCY 创建
//============================================================
TInt FApplication::UninstallCatcher(){
#ifdef _MO_LINUX
   // 1:本信号在用户终端连接(正常或非正常)结束时发出,
   signal(SIGHUP, SIG_DFL);
   // 2:程序终止(interrupt)信号, 在用户键入INTR字符(通常是Ctrl-C)时发出，用于通知前台进程组终止进程。
   signal(SIGINT, SIG_DFL);
   // 3: 当用户在终端上按退出键（一般采用Ctrl-/）时，产生此信号，并送至前台进程组中的所有进程。
   //    此信号不仅终止前台进程组（如SIGINT所做的那样），同时产生一个core文件。
   signal(SIGQUIT, SIG_DFL);
   // 4: 此信号指示进程已执行一条非法硬件指令。
   signal(SIGILL, SIG_DFL);
   // 5:指示一个实现定义的硬件故障。
   signal(SIGTRAP, SIG_DFL);
   // 6:调用Abort函数时产生此信号
   signal(SIGABRT, SIG_DFL);
   // 7:指示一个实现定义的硬件故障
   signal(SIGBUS, SIG_DFL);
   // 8:此信号表示一个算术运算异常，例如除以0，浮点溢出等。
   signal(SIGFPE, SIG_DFL);
   // 9:用来立即结束程序的运行. 本信号不能被阻塞、处理和忽略。
   // 如果管理员发现某个进程终止不了，可尝试发送这个信号。
   // 11:指示进程进行了一次无效的存储访问。
   signal(SIGSEGV, SIG_DFL);
   // 13:忽略管道破裂信号
   signal(SIGPIPE, SIG_IGN);
   // 14:时钟定时信号, 计算的是实际的时间或时钟时间. alarm函数使用该信号.
   signal(SIGALRM, SIG_DFL);
   // 15:程序结束(terminate)信号, 与SIGKILL不同的是该信号可以被阻塞和处理。
   //    通常用来要求程序自己正常退出，shell命令kill缺省产生这个信号。
   //    如果进程终止不了，我们才会尝试SIGKILL。
   signal(SIGTERM, SIG_DFL);
#ifndef __CYGWIN__
   // 16:堆栈错
   signal(SIGSTKFLT, SIG_DFL);
#endif
   // 18:让一个停止(stopped)的进程继续执行. 本信号不能被阻塞。
   signal(SIGCONT, SIG_DFL);
   // 20:停止进程的运行, 但该信号可以被处理和忽略。（控制终端（tty）上按下停止键 ）
   signal(SIGTSTP, SIG_DFL);
   // 23:有"紧急"数据或out-of-band数据到达socket时产生.
   signal(SIGURG, SIG_DFL);
   // 20:子进程结束时, 父进程会收到这个信号。
   signal(SIGCHLD, SIG_DFL);
   // 21:当后台作业要从用户终端读数据时, 该作业中的所有进程会收到SIGTTIN信号. 缺省时这些进程会停止执行.
   signal(SIGTTIN, SIG_DFL);
   // 22:类似于SIGTTIN, 但在写终端(或修改终端模式)时收到。
   signal(SIGTTOU, SIG_DFL);
   // 24:超过CPU时间资源限制. 这个限制可以由getrlimit/setrlimit来读取/改变。
   signal(SIGXCPU, SIG_DFL);
   // 25:当进程企图扩大文件以至于超过文件大小资源限制。
   signal(SIGXFSZ, SIG_DFL);
   // 29:文件描述符准备就绪, 可以开始进行输入/输出操作.
   signal(SIGIO, SIG_DFL);
   // 30:Power failure
   signal(SIGPWR, SIG_DFL);
   // 31:非法的系统调用。
   signal(SIGSYS, SIG_DFL);
   //............................................................
   signal(SIGUSR1, SIG_DFL);
   signal(SIGUSR2, SIG_DFL);
#endif // _MO_LINUX
   return ESuccess;
}

//============================================================
// <T>获得可执行文件的当前路径。</T>
//
// @history 100302 MAOCY 创建
//============================================================
TInt FApplication::InstallDaemon(){
#ifdef _MO_LINUX
   /*TFsPath path = RApplication::GetCurrentDirectory();
   // 更改指定文件的时间属性为当前时间
   system("touch application");
   // 打开文件
   TInt handle = open("./application.lock", O_RDWR | O_CREAT, 0640);
   if(handle < 0){
      MO_STATIC_PFATAL(open);
   }
   // 锁定文件
   TInt result = flock(handle, LOCK_EX | LOCK_NB);
   if(result < 0){
      MO_STATIC_PFATAL(flock);
   }
   // 克隆进程
   pid_t pid = fork();
   if(ESuccess != pid){
      exit(ESuccess);
   }
   // 设置信号
   setsid();
   signal(SIGHUP, SIG_IGN);
   signal(SIGINT, SIG_IGN);
   signal(SIGQUIT, SIG_IGN);
   signal(SIGCHLD, SIG_IGN);
   signal(SIGTERM, SIG_IGN);
   struct sigaction sig;
   sig.sa_handler = SIG_IGN;
   sig.sa_flags = 0;
   sigemptyset(&sig.sa_mask);
   sigaction(SIGHUP, &sig, NULL);
   // 克隆进程
   pid = fork();
   if(ESuccess != pid){
      exit(ESuccess);
   }
   // 尝试更改路径
   if(chdir(path.MemoryC())){
      MO_STATIC_DEBUG("Can't change run dir to %s after initialize daemon.", path.MemoryC());
      exit(2);
   }
   // 设置合理文件权限
   umask(0);
   // 设置进程组
   setpgrp();*/
#endif // _MO_LINUX
   return ESuccess;
}

MO_NAMESPACE_END
