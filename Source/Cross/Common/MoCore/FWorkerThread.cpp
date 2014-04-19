#include "MoCrWorker.h"

#define MO_WORKER_SLEEP      10
#define MO_WORKER_LOOP_COUNT 20

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造工作器线程。</T>
//============================================================
FWorkerThread::FWorkerThread(){
   _name = TC("worker.thread");
   _pWorkers = MO_CREATE(FWorkerVector);
}

//============================================================
// <T>析构工作器线程。</T>
//============================================================
FWorkerThread::~FWorkerThread(){
   MO_DELETE(_pWorkers);
}

//============================================================
// <T>数据处理。</T>
//
// @return 处理结果
//============================================================
TResult FWorkerThread::Process(){
   while(!IsStop()){
      TInt loop = 0;
      // 获取工作器集合
      RWorkerManager::Instance().FetchWorkers(_pWorkers);
      // 处理工作器
      TBool result = ETrue;
      TInt count = _pWorkers->Count();
      for(TInt n = 0; n < count; n++){
         // 工作器处理
         FWorker* pWorker = _pWorkers->Get(n);
         if(!pWorker->Process()){
            result = EFalse;
         }
      }
      // 挂起处理
      if(loop > MO_WORKER_LOOP_COUNT){
         // 超过循环限制
         Sleep(MO_WORKER_SLEEP);
      }else if(result){
         // 所有工作完成
         Sleep(MO_WORKER_SLEEP);
      }
   }
   return ESuccess;
}

MO_NAMESPACE_END
