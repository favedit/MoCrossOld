#include "MoCmThread.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造线程池的工作对象。</T>
//
// @param pPool 缓冲池
// @return 线程池的工作对象
//============================================================
FThreadRunableWorker::FThreadRunableWorker(IThreadRunablePool* pPool){
   MO_ASSERT(pPool);
   _pPool = pPool;
   _stop = EFalse;
}

//============================================================
// <T>执行处理。</T>
//
// @return 处理结果
//============================================================
TResult FThreadRunableWorker::Process(){
   while(!_stop){
      // 执行处理
      if(NULL != _runableHandle){
         (*_runableHandle)(this);
         MO_CLEAR(_runableHandle);
      }
      // 挂起，等待下一个执行命令
      Suspend();
   }
   return ESuccess;
}

MO_NAMESPACE_END
