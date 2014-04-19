#include "MoCmTask.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FTaskConsole, FInstance);

//============================================================
// <T>构造任务控制台。</T>
//============================================================
FTaskConsole::FTaskConsole(){
   _pTasks = MO_CREATE(FTaskList);
   _pThreads = MO_CREATE(FTaskThreadCollection);
   _threadCount = 2;
}

//============================================================
// <T>析构任务控制台。</T>
//============================================================
FTaskConsole::~FTaskConsole(){
   MO_DELETE(_pTasks);
   MO_DELETE(_pThreads);
}

//============================================================
// <T>弹出一个任务。</T>
//
// @return 任务
//============================================================
FTask* FTaskConsole::PopTask(){
   FTask* pTask = NULL;
   _locker.Enter();
   if(!_pTasks->IsEmpty()){
      pTask = _pTasks->Shift();
   }
   _locker.Leave();
   return pTask;
}

//============================================================
// <T>增加一个任务。</T>
//
// @param pTask 任务
// @return 处理结果
//============================================================
TResult FTaskConsole::PushTask(ITask* pTask){
   MO_CHECK(pTask, return ENull);
   _locker.Enter();
   FTaskInvoker* pTaskInvoker = FTaskInvoker::InstanceCreate();
   pTaskInvoker->SetTask(pTask);
   _pTasks->Push(pTaskInvoker);
   _locker.Leave();
   return ESuccess;
}

//============================================================
// <T>增加一个任务。</T>
//
// @param pTask 任务
// @return 处理结果
//============================================================
TResult FTaskConsole::PushTask(FTask* pTask){
   MO_CHECK(pTask, return ENull);
   _locker.Enter();
   _pTasks->Push(pTask);
   _locker.Leave();
   return ESuccess;
}

//============================================================
// <T>启动处理。</T>
//
// @return 处理结果
//============================================================
TResult FTaskConsole::Startup(){
   for(TInt n = 0; n < _threadCount; n++){
      FTaskThread* pThread = MO_CREATE(FTaskThread);
      pThread->SetConsole(this);
      pThread->Start();
      _pThreads->Push(pThread);
   }
   return ESuccess;
}

//============================================================
// <T>关闭处理。</T>
//
// @return 处理结果
//============================================================
TResult FTaskConsole::Shutdown(){
   TInt count = _pThreads->Count();
   for(TInt n = 0; n < count; n++){
      FTaskThread* pThread = _pThreads->Get(n);
      pThread->Stop();
   }
   _pThreads->Clear();
   return ESuccess;
}

MO_NAMESPACE_END
