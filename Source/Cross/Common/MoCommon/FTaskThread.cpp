#include "MoCmTask.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造任务线程。</T>
//============================================================
FTaskThread::FTaskThread(){
   _name = TC("Task Thread");
   _interval = 10000;
   MO_CLEAR(_pTask);
}

//============================================================
// <T>析构任务线程。</T>
//============================================================
FTaskThread::~FTaskThread(){
}

//============================================================
// <T>执行处理。</T>
//
// @return 处理结果
//============================================================
TResult FTaskThread::Process(){
   while(!IsStop()){
      _pTask = _pConsole->PopTask();
      if(_pTask != NULL){
         _pTask->Process();
         MO_DELETE(_pTask);
      }
      SleepMicro(_interval);
   }
   return ESuccess;
}

MO_NAMESPACE_END
