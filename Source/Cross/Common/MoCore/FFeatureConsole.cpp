#include "MoCrFeature.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FFeatureConsole, FConsole);

//============================================================
// <T>构造功能控制台。</T>
//============================================================
FFeatureConsole::FFeatureConsole(){
   _name = TC("Feature.Console");
   _pClasses = MO_CREATE(FClassCollection);
}

//============================================================
// <T>析构功能控制台。</T>
//============================================================
FFeatureConsole::~FFeatureConsole(){
   MO_DELETE(_pClasses);
}

//============================================================
// <T>注册一个功能。</T>
//
// @param pClass 功能类
// @return 处理结果
//============================================================
TResult FFeatureConsole::Register(FClass* pClass){
   MO_CHECK(pClass, return ENull);
   _pClasses->Push(pClass);
   return ESuccess;
}

//============================================================
// <T>注销一个功能。</T>
//
// @param pClass 功能类
// @return 处理结果
//============================================================
TResult FFeatureConsole::Unregister(FClass* pClass){
   MO_CHECK(pClass, return ENull);
   _pClasses->Remove(pClass);
   return ESuccess;
}

//============================================================
// <T>构造类对象。</T>
//
// @return 处理结果
//============================================================
TResult FFeatureConsole::Construct(){
   TResult resultCd = FConsole::Construct();
   // 构造功能集合
   TInt count = _pClasses->Count();
   for(TInt n = 0; n < count; n++){
      FClass* pClass = _pClasses->Get(n);
      FFeature* pFeature =  pClass->InstanceInheritCreate<FFeature>();
      pFeature->Construct();
      _features.Push(pFeature);
   }
   return resultCd;
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

//============================================================
// <T>释放类对象。</T>
//
// @return 处理结果
//============================================================
TResult FFeatureConsole::Dispose(){
   TResult resultCd = FConsole::Dispose();
   // 析构功能集合
   TInt count = _features.Count();
   for(TInt n = 0; n < count; n++){
      FFeature* pFeature = _features.Get(n);
      pFeature->Dispose();
   }
   _features.Clear();
   return resultCd;
}

MO_NAMESPACE_END
