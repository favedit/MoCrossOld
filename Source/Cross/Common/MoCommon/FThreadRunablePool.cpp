#include "MoCmThread.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造线程缓冲池。</T>
//
// @return 线程缓冲池
//============================================================
FThreadRunablePool::FThreadRunablePool(){
   //_pUsedWorks = MO_CREATE(FThreadWorkerPoolList);
   //_pUnusedWorks = MO_CREATE(FThreadWorkerPoolList);
   _incrementCapacity = 1;
}

//============================================================
// <T>析构线程缓冲池。</T>
//============================================================
FThreadRunablePool::~FThreadRunablePool(){
   MO_PTR_DELETE(_pUsedWorks);
   MO_PTR_DELETE(_pUnusedWorks);
}

//============================================================
// <T>获得激活个数。</T>
//
// @return 激活个数
//============================================================
TInt FThreadRunablePool::ActiveCount(){
   TInt count = 0;
   _locker.Enter();
   count = _pUsedWorks->Count();
   _locker.Leave();
   return count;
}

//============================================================
// <T>获得休眠个数。</T>
//
// @return 休眠个数
//============================================================
TInt FThreadRunablePool::SleepCount(){
   TInt count = 0;
   _locker.Enter();
   count = _pUnusedWorks->Count();
   _locker.Leave();
   return count;
}

//============================================================
void FThreadRunablePool::NotifyFree(FThreadRunableWorker& worker){
   _locker.Enter();
   _pUsedWorks->Remove(&worker);
   _pUnusedWorks->Push(&worker);
   _locker.Leave();
   MO_DEBUG(TC("Process thread (active=%d, sleep=%d)"), _pUsedWorks->Count(), _pUnusedWorks->Count());
}

//============================================================
//void FThreadPool::NotifyTimeout(FThreadRunableWorker& worker){
//}

//============================================================
// <T>执行处理。</T>
//
// @param runable 工作函数
//============================================================
void FThreadRunablePool::Process(TRunableHandle runable){
   //FThreadPoolWorker* pWorker = NULL;
   //_locker.Enter();
   //if(_pUnusedWorks->IsEmpty()){
   //   pWorker = MO_CREATE(FThreadWorker, this);
   //   pWorker->Start();
   //   _pUsedWorks->Push(pWorker);
   //}else{
   //   pWorker = _pUnusedWorks->Shift();
   //}
   //pWorker->SetRunable(runable);
   //_pUsedWorks->Push(pWorker);
   //pWorker->Resume();
   //_locker.Leave();
}

MO_NAMESPACE_END
