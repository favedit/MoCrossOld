#include "MoFrLoader.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FLoaderConsole, FConsole);

//============================================================
// <T>构造加载器控制台。</T>
//============================================================
FLoaderConsole::FLoaderConsole(){
   _monitor = FLoaderMonitor::InstanceCreate();
   _pWaitLoaders = MO_CREATE(FLoaderList);
}

//============================================================
// <T>析构加载器控制台。</T>
//============================================================
FLoaderConsole::~FLoaderConsole(){
   MO_DELETE(_pWaitLoaders);
}

//============================================================
// <T>启动处理。</T>
//
// @return 处理结果
//============================================================
TResult FLoaderConsole::Startup(){
   RMonitorManager::Instance().Register(_monitor);
   return ESuccess;
}

//============================================================
// <T>关闭处理。</T>
//
// @return 处理结果
//============================================================
TResult FLoaderConsole::Shutdown(){
   RMonitorManager::Instance().Unregister(_monitor);
   return ESuccess;
}

//============================================================
// <T>放入一个等待处理的加载器。</T>
//
// @param pLoader 加载器
// @return 处理结果
//============================================================
TResult FLoaderConsole::PushWaitLoader(FLoader* pLoader){
   MO_CHECK(pLoader, return ENull);
   _locker.Enter();
   _pWaitLoaders->Push(pLoader);
   _locker.Leave();
   return ESuccess;
}

//============================================================
// <T>弹出一个等待处理的加载器。</T>
//
// @return 加载器
//============================================================
FLoader* FLoaderConsole::PopWaitLoader(){
   FLoader* pLoader = NULL;
   _locker.Enter();
   if(!_pWaitLoaders->IsEmpty()){
      pLoader = _pWaitLoaders->Shift();
   }
   _locker.Leave();
   return pLoader;
}

MO_NAMESPACE_END
