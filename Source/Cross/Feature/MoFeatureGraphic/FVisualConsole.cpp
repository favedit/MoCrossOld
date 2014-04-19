#include "MoFgVisual.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FVisualConsole, FConsole);

//============================================================
// <T>构造可见控制台。</T>
//============================================================
FVisualConsole::FVisualConsole(){
   _pThread = MO_CREATE(FVisualThread);
   _pRegions = MO_CREATE(FVisualRegionCollection);
}

//============================================================
// <T>析构可见控制台。</T>
//============================================================
FVisualConsole::~FVisualConsole(){
   MO_DELETE(_pRegions);
}

//============================================================
// <T>注册一个可见区域。</T>
//
// @param pRegion 可见区域
// @return 处理结果
//============================================================
TResult FVisualConsole::RegionRegister(FVisualRegion* pRegion){
   _regionLocker.Enter();
   _pRegions->Push(pRegion);
   _regionLocker.Leave();
   return ESuccess;
}

//============================================================
// <T>注销一个可见区域。</T>
//
// @param pRegion 可见区域
// @return 处理结果
//============================================================
TResult FVisualConsole::RegionUnregister(FVisualRegion* pRegion){
   _regionLocker.Enter();
   _pRegions->Remove(pRegion);
   _regionLocker.Leave();
   return ESuccess;
}

//============================================================
// <T>启动处理。</T>
//
// @return 处理结果
//============================================================
TResult FVisualConsole::Startup(){
   _pThread->SetConsole(this);
   _pThread->Start();
   return ESuccess;
}

//============================================================
// <T>逻辑处理。</T>
//
// @return 处理结果
//============================================================
TResult FVisualConsole::Process(){
   _regionLocker.Enter();
   FVisualRegionCollection::TIteratorC iterator = _pRegions->IteratorC();
   while(iterator.Next()){
      FVisualRegion* pRegion = *iterator;
      pRegion->Process();
   }
   _regionLocker.Leave();
   return ESuccess;
}

//============================================================
// <T>关闭处理。</T>
//
// @return 处理结果
//============================================================
TResult FVisualConsole::Shutdown(){
   _pThread->Terminate();
   return ESuccess;
}

MO_NAMESPACE_END
