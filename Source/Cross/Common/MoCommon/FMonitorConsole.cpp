#include "MoCmMonitor.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造监视器控制台。</T>
//============================================================
FMonitorConsole::FMonitorConsole(){
   _machine = FMonitorMachine::InstanceCreate();
   MO_CLEAR(_pThread);
}

//============================================================
// <T>析构监视器控制台。</T>
//============================================================
FMonitorConsole::~FMonitorConsole(){
}

//============================================================
// <T>注册监视器。</T>
//
// @param pMonitor 监视器
//============================================================
TResult FMonitorConsole::Register(IMonitor* pMonitor){
   MO_CHECK(pMonitor, return ENull);
   TResult resultCd = _machine->Register(pMonitor);
   return resultCd;
}

//============================================================
// <T>注销监视器。</T>
//
// @param pMonitor 监视器
//============================================================
TResult FMonitorConsole::Unregister(IMonitor* pMonitor){
   MO_CHECK(pMonitor, return ENull);
   TResult resultCd = _machine->Unregister(pMonitor);
   return resultCd;
}

//============================================================
// <T>启动处理。</T>
//
// @return 处理结果
//============================================================
TResult FMonitorConsole::Startup(){
   MO_CHECK(!_pThread, return EFailure);
   // 启动处理机
   _machine->Startup();
   // 启动监听线程
   _pThread = MO_CREATE(FMonitorThread);
   _pThread->SetMachine(_machine);
   _pThread->Start();
   return ESuccess;
}

//============================================================
// <T>执行处理。</T>
//
// @return 处理结果
//============================================================
TResult FMonitorConsole::Execute(){
   _machine->Execute();
   return ESuccess;
}

//============================================================
// <T>关闭处理。</T>
//
// @return 处理结果
//============================================================
TResult FMonitorConsole::Shutdown(){
   MO_ASSERT(_pThread);
   _pThread->Stop();
   return ESuccess;
}

MO_NAMESPACE_END
