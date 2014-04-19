#ifdef _MO_MODE_LINUX
#include <pthread.h>
#endif // _MO_MODE_LINUX
#include "MoCmLock.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>创建进程同步锁。</T>
//============================================================
TThreadMutex::TThreadMutex(){
#ifdef _MO_SYS_WINDOWS
   InitializeCriticalSection(&_section);
#endif // _MO_SYS_WINDOWS
#ifdef _MO_SYS_LINUX
   TInt result = pthread_mutex_init(&_section, NULL);
   if(ESuccess != result){
      MO_PFATAL(pthread_mutex_init);
   }
#endif // _MO_SYS_LINUX
}

//============================================================
// <T>释放进程同步锁。</T>
//============================================================
TThreadMutex::~TThreadMutex(){
#ifdef _MO_SYS_WINDOWS
   DeleteCriticalSection(&_section);
#endif // _MO_SYS_WINDOWS
#ifdef _MO_SYS_LINUX
   TInt result = pthread_mutex_destroy(&_section);
   if(ESuccess != result){
      MO_PFATAL(pthread_mutex_destroy);
   }
#endif // _MO_SYS_LINUX
}

//============================================================
// <T>进入临界段。</T>
//============================================================
void TThreadMutex::Enter(){
#ifdef _MO_SYS_WINDOWS
   EnterCriticalSection(&_section);
#endif // _MO_SYS_WINDOWS
#ifdef _MO_SYS_LINUX
   TInt result = pthread_mutex_lock(&_section);
   if(ESuccess != result){
      MO_PFATAL(pthread_mutex_lock);
   }
#endif // _MO_SYS_LINUX
}

//============================================================
// <T>进入临界段。</T>
//============================================================
TBool TThreadMutex::TryEnter(){
   TInt result = EFailure;
#ifdef _MO_SYS_WINDOWS
   MO_FATAL_UNSUPPORT();
#endif // _MO_SYS_WINDOWS
#ifdef _MO_SYS_LINUX
   result = pthread_mutex_trylock(&_section);
   if(ESuccess != result){
      MO_PFATAL(pthread_mutex_trylock);
   }
#endif // _MO_SYS_LINUX
   return (ESuccess == result);
}

//============================================================
// <T>离开临界段。</T>
//============================================================
void TThreadMutex::Leave(){
#ifdef _MO_SYS_WINDOWS
   LeaveCriticalSection(&_section);
#endif // _MO_SYS_WINDOWS
#ifdef _MO_SYS_LINUX
   TInt result = pthread_mutex_unlock(&_section);
   if(ESuccess != result){
      MO_PFATAL(pthread_mutex_unlock);
   }
#endif // _MO_SYS_LINUX
}

MO_NAMESPACE_END
