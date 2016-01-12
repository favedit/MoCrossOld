#include "MoCrFeature.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FFeature, FConsole);

//============================================================
// <T>构造功能。</T>
//============================================================
FFeature::FFeature(){
   _name = TC("Feature");
}

//============================================================
// <T>析构功能。</T>
//============================================================
FFeature::~FFeature(){
}

//============================================================
// <T>启动处理。</T>
//
// @return 处理结果
//============================================================
TResult FFeature::Startup(){
   MO_INFO(TC("Feature startup. (name=%s)"), (TCharC*)_name);
   return ESuccess;
}

//============================================================
// <T>挂起处理。</T>
//
// @return 处理结果
//============================================================
TResult FFeature::Suspend(){
   MO_INFO(TC("Feature suspend. (name=%s)"), (TCharC*)_name);
   return ESuccess;
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FFeature::Resume(){
   MO_INFO(TC("Feature resume. (name=%s)"), (TCharC*)_name);
   return ESuccess;
}

//============================================================
// <T>停止处理。</T>
//
// @return 处理结果
//============================================================
TResult FFeature::Shutdown(){
   MO_INFO(TC("Feature shutdown. (name=%s)"), (TCharC*)_name);
   return ESuccess;
}

MO_NAMESPACE_END
