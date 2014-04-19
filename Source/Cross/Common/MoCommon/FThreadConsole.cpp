#ifdef _MO_LINUX
#include <unistd.h>
#endif
#include "MoCmThread.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>创建线程管理器。</T>
//============================================================
FThreadConsole::FThreadConsole(){
   _index = 0;
   _pThreads = MO_PTR_CREATE(FThreadSet);
}

//============================================================
// <T>释放线程管理器。</T>
//============================================================
FThreadConsole::~FThreadConsole(){
   MO_PTR_DELETE(_pThreads);
}

//============================================================
// <T>注册线程对象。</T>
//
// @param pThread 线程对象
//============================================================
void FThreadConsole::Register(FThread* pThread){
   MO_ASSERT(pThread);
   // 获得线程标识
   TThreadId threadId = pThread->ThreadId();
   // 检查是否重复
   _locker.Enter();
   if(_pThreads->Contains(threadId)){
      MO_THROW("Thread id duplicate.");
   }
   // 存储线程
   _pThreads->Set(threadId, pThread);
   pThread->SetIndex(++_index);
   _locker.Leave();
}

//============================================================
// <T>注销线程对象。</T>
//
// @param pThread 线程对象
//============================================================
void FThreadConsole::Unregister(FThread* pThread){
   MO_ASSERT(pThread);
   TThreadId threadId = pThread->ThreadId();
   // 检查是否重复
   if(!_pThreads->Contains(threadId)){
      MO_THROW("Thread id is not founded.");
   }
   _locker.Enter();
   _pThreads->Remove(threadId);
   _locker.Leave();
}

//============================================================
// <T>获得当前线程对象。</T>
//
// @param threadId 线程标识
//============================================================
FThread* FThreadConsole::Current(){
   TThreadId threadId = RThread::CurrentId();
   return Find(threadId);
}

//============================================================
// <T>根据线程标识获得线程对象。</T>
//
// @param threadId 线程标识
// @return 线程对象
//============================================================
FThread* FThreadConsole::Get(TThreadId threadId){
   FThread* pThread = NULL;
   _locker.Enter();
   pThread = _pThreads->Get(threadId);
   MO_ASSERT(pThread);
   _locker.Leave();
   return pThread;
}

//============================================================
// <T>根据线程标识获得线程对象。</T>
//
// @param threadId 线程标识
// @return 线程对象
//============================================================
FThread* FThreadConsole::Find(TThreadId threadId){
   FThread* pThread = NULL;
   _locker.Enter();
   pThread = _pThreads->Find(threadId);
   _locker.Leave();
   return pThread;
}

//============================================================
/*void RThreadManager::Track(){
   if(NULL == _pTrackThread){
      _pTrackThread = MO_CREATE(FThreadMemoryTrackThread);
      _pTrackThread->Start();
   }
}*/

MO_NAMESPACE_END
