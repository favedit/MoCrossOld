#include "MoCmThread.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>获得线程标识。</T>
//
// @static
// @return 线程标识
//============================================================
TInt RThread::CurrentCode(){
   TInt id = 0;
#ifdef _MO_SYS_WINDOWS
   id = GetCurrentThreadId();
#endif // _MO_SYS_WINDOWS
#ifdef _MO_LINUX
#ifndef __CYGWIN__
   id = syscall(__NR_gettid);
#endif // __CYGWIN__
#endif // _MO_LINUX
   return id;
}

//============================================================
// <T>获得线程标识。</T>
//
// @static
// @return 线程标识
//============================================================
TThreadId RThread::CurrentId(){
#ifdef _MO_SYS_WINDOWS
   TThreadId id = GetCurrentThreadId();
#endif // _MO_SYS_WINDOWS
#ifdef _MO_SYS_LINUX
   TThreadId id = pthread_self();
#endif // _MO_SYS_LINUX
   return id;
}

//============================================================
// <T>在线程中运行指定函数。</T>
//
// @static
// @param cRunable 可运行函数
//============================================================
IThread* RThread::Process(TRunableHandle cRunable){
   FThreadRunable* pThread = MO_CREATE(FThreadRunable, cRunable);
   pThread->Start();
   return pThread;
}

MO_NAMESPACE_END
