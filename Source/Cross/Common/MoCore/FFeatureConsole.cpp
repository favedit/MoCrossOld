#include "MoCrFeature.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FFeatureConsole, FConsole);

//============================================================
// <T>构造功能控制台。</T>
//============================================================
FFeatureConsole::FFeatureConsole(){
   _name = "Feature.Console";
}

//============================================================
// <T>析构功能控制台。</T>
//============================================================
FFeatureConsole::~FFeatureConsole(){
}

//============================================================
// <T>注册一个功能。</T>
//
// @param pFeature 功能
//============================================================
void FFeatureConsole::Register(FFeature* pFeature){
   _features.Push(pFeature);
}

//============================================================
// <T>注销一个功能。</T>
//
// @param pFeature 功能
//============================================================
void FFeatureConsole::Unregister(FFeature* pFeature){
   _features.Remove(pFeature);
}

//============================================================
// <T>启动处理。</T>
//
// @return 处理结果
//============================================================
TResult FFeatureConsole::Startup(){
   TInt count = _features.Count();
   for(TInt n = 0; n < count ; n++){
      FFeature* pFeature = _features.Get(n);
      pFeature->Startup();
   }
   return ESuccess;
}

//============================================================
// <T>挂起处理。</T>
//
// @return 处理结果
//============================================================
TResult FFeatureConsole::Suspend(){
   TInt count = _features.Count();
   for(TInt n = 0; n < count ; n++){
      FFeature* pFeature = _features.Get(n);
      pFeature->Suspend();
   }
   return ESuccess;
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FFeatureConsole::Resume(){
   TInt count = _features.Count();
   for(TInt n = 0; n < count ; n++){
      FFeature* pFeature = _features.Get(n);
      pFeature->Resume();
   }
   return ESuccess;
}

//============================================================
// <T>停止处理。</T>
//
// @return 处理结果
//============================================================
TResult FFeatureConsole::Shutdown(){
   TInt count = _features.Count();
   for(TInt n = 0; n < count ; n++){
      FFeature* pFeature = _features.Get(n);
      pFeature->Shutdown();
   }
   return ESuccess;
}

MO_NAMESPACE_END
