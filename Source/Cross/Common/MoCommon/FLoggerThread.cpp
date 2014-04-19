#include "MoCmSystem.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造日志刷新线程。</T>
//============================================================
FLoggerThread::FLoggerThread(){
   // 默认间隔为1秒
   _interval = 1000;
   MO_CLEAR(_pWriters);
}

//============================================================
// <T>析构日志刷新线程。</T>
//============================================================
FLoggerThread::~FLoggerThread(){
}

//============================================================
// <T>刷新处理。</T>
//
// @return 处理结果
//============================================================
TResult FLoggerThread::Process(){
   while(!IsStop()){
      // 刷新所有日志输出器
      _locker.Enter();
      TListIteratorC<ILoggerWriter*> iterator = _pWriters->IteratorC();
      while(iterator.Next()){
         iterator->Refresh();
      }
      _locker.Leave();
      // 休眠处理
      Sleep((TInt)_interval);
   }
   return ESuccess;
}

MO_NAMESPACE_END
