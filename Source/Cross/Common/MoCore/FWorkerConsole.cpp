#include "MoCrWorker.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造工作器控制台。</T>
//============================================================
FWorkerConsole::FWorkerConsole(){
   _pThread = MO_CREATE(FWorkerThread);
   _pWorkers = MO_CREATE(FWorkerVector);
}

//============================================================
// <T>析构工作器控制台。</T>
//============================================================
FWorkerConsole::~FWorkerConsole(){
   MO_DELETE(_pWorkers);
}

//============================================================
// <T>启动处理。</T>
//============================================================
void FWorkerConsole::Startup(){
   _pThread->Start();
}

//============================================================
// <T>关闭处理。</T>
//============================================================
void FWorkerConsole::Shutdown(){
   _pThread->Terminate();
}

//============================================================
// <T>获取全部工作器。/T>
//
// @param pWorker 工作器
//============================================================
void FWorkerConsole::FetchWorkers(FWorkerVector* pWorkers){
   // 放入等待的工作器
   _mutex.Enter();
   pWorkers->Assign(_pWorkers);
   _mutex.Leave();
}

//============================================================
// <T>放入一个工作器。</T>
//
// @param pWorker 工作器
//============================================================
void FWorkerConsole::Register(FWorker* pWorker){
   // 放入等待的工作器
   _mutex.Enter();
   _pWorkers->Push(pWorker);
   _mutex.Leave();
}

//============================================================
// <T>移除一个工作器。</T>
//
// @param pWorker 工作器
//============================================================
void FWorkerConsole::Unregister(FWorker* pWorker){
   _mutex.Enter();
   _pWorkers->Remove(pWorker);
   _mutex.Leave();
}

//============================================================
// <T>刷新处理。</T>
//============================================================
void FWorkerConsole::Refresh(){
   _pThread->Resume();
}

MO_NAMESPACE_END
