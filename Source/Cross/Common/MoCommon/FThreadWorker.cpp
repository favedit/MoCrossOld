#include "MoCmThread.h"
#include "MoCmSystem.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造线程池的工作对象。</T>
//
// @param pPool 缓冲池
// @return 线程池的工作对象
//============================================================
FThreadWorker::FThreadWorker(){
   _typeCd = EThread_Worker;
   MO_CLEAR(_pPool);
   _busy = EFalse;
   _stop = EFalse;
   _interval = 1000; // 1毫秒
#ifdef _MO_DEBUG
   _timeout = 60000000; // 默认线程休闲1分钟
#else
   _timeout = 600000000; // 默认线程休闲10分钟
#endif
   _freeDate = 0;
}

//============================================================
// <T>执行处理。</T>
//
// @return 执行结果
//============================================================
TBool FThreadWorker::TestTimeout(TDateTime current){
   if(!_busy){
      if(0 != _freeDate){
         TTimeSpan span = current - _freeDate;
         if(span > _timeout){
            return ETrue;
         }
      }
   }
   return EFalse;
}

//============================================================
// <T>执行处理。</T>
//
// @return 处理结果
//============================================================
TResult FThreadWorker::OnProcess(){
   return ESuccess;
}

//============================================================
// <T>执行处理。</T>
//
// @return 处理结果
//============================================================
TResult FThreadWorker::Process(){
   // 检查工作池
   MO_ASSERT(_pPool);
   // 处理逻辑
   while(!_stop){
      // 执行处理
      TInt resultCd = ESuccess;
      if(_busy){
         resultCd = OnProcess();
         _busy = EFalse;
      }
      if(ESuccess != resultCd){
         MO_ERROR(TC("Thread worker process faliure. (result=%d)"), resultCd);
      }
      // 回归队列
      _pPool->DoWait(this);
   }
   return ESuccess;
}

//============================================================
// <T>跟踪信息。</T>
//
// @param pTrack 字符串
// @return 执行结果
//============================================================
TBool FThreadWorker::Track(MString* pTrack){
   pTrack->AppendFormat(TC("%s"), (TCharC*)_name);
   return ETrue;
}

MO_NAMESPACE_END
