#include "MoFrLoader.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造加载器控制台。</T>
//============================================================
FLoaderConsole::FLoaderConsole(){
   _pWorker = MO_CREATE(FLoaderWorker);
   _pWaitLoaders = MO_CREATE(FLoaderList);
}

//============================================================
// <T>析构加载器控制台。</T>
//============================================================
FLoaderConsole::~FLoaderConsole(){
   MO_DELETE(_pWorker);
   MO_DELETE(_pWaitLoaders);
}

//============================================================
// <T>启动处理。</T>
//============================================================
void FLoaderConsole::Startup(){
   RWorkerManager::Instance().Register(_pWorker);
}

//============================================================
// <T>关闭处理。</T>
//============================================================
void FLoaderConsole::Shutdown(){
   RWorkerManager::Instance().Unregister(_pWorker);
}

//============================================================
// <T>放入一个等待处理的加载器。</T>
//
// @param pLoader 加载器
//============================================================
void FLoaderConsole::PushWaitLoader(FLoader* pLoader){
   _locker.Enter();
   _pWaitLoaders->Push(pLoader);
   _locker.Leave();
   return;
}

//============================================================
// <T>弹出一个等待处理的加载器。</T>
//
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
