#include "MoCmThread.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造线程缓冲池。</T>
//
// @return 线程缓冲池
//============================================================
FThreadPool::FThreadPool(){
   _pInitializeWorkers = MO_CREATE(FThreadWorkerList);
   _pBusyWorkers = MO_CREATE(FThreadWorkerList);
   _pFreeWorkers = MO_CREATE(FThreadWorkerList);
   _busyTotal = 0;
   _waitTotal = 0;
   _refreshInterval = 10000000; // 10秒刷新
   _refreshDate = 0;
   _refreshTotal = 0;
}

//============================================================
// <T>析构线程缓冲池。</T>
//============================================================
FThreadPool::~FThreadPool(){
   MO_PTR_DELETE(_pInitializeWorkers);
   MO_PTR_DELETE(_pBusyWorkers);
   MO_PTR_DELETE(_pFreeWorkers);
}

//============================================================
// <T>获得初始化个数。</T>
//
// @return 初始化个数
//============================================================
TInt FThreadPool::InitializeCount(){
   TInt count = 0;
   _lockerInitialize.Enter();
   count = _pInitializeWorkers->Count();
   _lockerInitialize.Leave();
   return count;
}

//============================================================
// <T>获得工作个数。</T>
//
// @return 工作个数
//============================================================
TInt FThreadPool::BusyCount(){
   TInt count = 0;
   _lockerBusy.Enter();
   count = _pBusyWorkers->Count();
   _lockerBusy.Leave();
   return count;
}

//============================================================
// <T>获得自由个数。</T>
//
// @return 自由个数
//============================================================
TInt FThreadPool::FreeCount(){
   TInt count = 0;
   _lockerFree.Enter();
   count = _pFreeWorkers->Count();
   _lockerFree.Leave();
   return count;
}

//============================================================
// <T>注册一个线程工作器。</T>
//
// @param pWorker 线程工作器
//============================================================
void FThreadPool::Register(FThreadWorker* pWorker){
   pWorker->SetPool(this);
   pWorker->Start();
}

//============================================================
// <T>收集一个自由的线程工作器。</T>
//
// @return 线程工作器
//============================================================
FThreadWorker* FThreadPool::AllocFree(){
   FThreadWorker* pWorker = NULL;
   _lockerFree.Enter();
   if(!_pFreeWorkers->IsEmpty()){
      pWorker = _pFreeWorkers->Shift();
   }
   _lockerFree.Leave();
   return pWorker;
}

//============================================================
// <T>初始化处理。</T>
//
// @param pWorker 线程工作器
// @return 处理结果
//============================================================
TBool FThreadPool::DoInitialize(FThreadWorker* pWorker){
   _lockerInitialize.Enter();
   _pInitializeWorkers->Push(pWorker);
   _lockerInitialize.Leave();
   return ETrue;
}

//============================================================
// <T>启动线程工作器。</T>
//
// @param pWorker 线程工作器
// @return 处理结果
//============================================================
TBool FThreadPool::DoBusy(FThreadWorker* pWorker){
   pWorker->SetBusy(ETrue);
   // 从自由中移除
   _lockerFree.Enter();
   _pFreeWorkers->Remove(pWorker);
   _lockerFree.Leave();
   // 放入工作线程
   _lockerBusy.Enter();
   if(!_pBusyWorkers->Contains(pWorker)){
      _pBusyWorkers->Push(pWorker);
   }
   _lockerBusy.Leave();
   // 开始处理
   _busyTotal++;
   pWorker->Resume();
   return ETrue;
}

//============================================================
// <T>启动线程工作器。</T>
//
// @param pWorker 线程工作器
// @return 处理结果
//============================================================
TBool FThreadPool::DoWait(FThreadWorker* pWorker){
   pWorker->SetBusy(EFalse);
   // 从工作中移除
   _lockerBusy.Enter();
   _pBusyWorkers->Remove(pWorker);
   _lockerBusy.Leave();
   // 放入自由线程
   _lockerFree.Enter();
   if(!_pFreeWorkers->Contains(pWorker)){
      _pFreeWorkers->Push(pWorker);
   }
   _lockerFree.Leave();
   // 从初始化中移除
   _lockerInitialize.Enter();
   if(_pInitializeWorkers->Contains(pWorker)){
      _pInitializeWorkers->Remove(pWorker);
   }else{
      _waitTotal++;
   }
   _lockerInitialize.Leave();
   // 开始等待
   pWorker->Suspend();
   return ETrue;
}

//============================================================
// <T>刷新处理。</T>
//============================================================
TBool FThreadPool::Refresh(){
   TDateTime current = RDateTime::Current();
   TTimeSpan span = current - _refreshDate;
   if(span > _refreshInterval){
      // 回收超时线程
      _lockerFree.Enter();
      TListIteratorC<FThreadWorker*> iterator = _pFreeWorkers->IteratorC();
      while(iterator.Next()){
         FThreadWorker* pWorker = *iterator;
         // 停止超时的线程
         if(pWorker->TestTimeout(current)){
            _pFreeWorkers->Remove(pWorker);
            pWorker->Stop();
            break;
         }
      }
      _lockerFree.Leave();
      // 刷新处理
      _refreshDate = current;
      _refreshTotal++;
   }
   return ETrue;
}

//============================================================
// <T>跟踪信息。</T>
//
// @param pTrack 字符串
// @return 执行结果
//============================================================
TBool FThreadPool::Track(MString* pTrack){
   pTrack->AppendFormat(TC("Thread pool. (initialize=%d, busy=%d, free=%d)"),
         _pInitializeWorkers->Count(), _pBusyWorkers->Count(), _pFreeWorkers->Count());
   return ETrue;
}

MO_NAMESPACE_END
