#include "MoFlLogger.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FLoggerFeature, FFeature);

//============================================================
// <T>构造日志功能。</T>
//============================================================
 FLoggerFeature::FLoggerFeature(){
}

//============================================================
// <T>析构日志功能。</T>
//============================================================
FLoggerFeature::~FLoggerFeature(){
}

//============================================================
// <T>配置处理。</T>
//
// @return 处理结果
//============================================================
TResult FLoggerFeature::Setup(){
   _netLoggerConsole = FNetLoggerConsole::InstanceCreate();
   return ESuccess;
}

//============================================================
// <T>启动处理。</T>
//
// @return 处理结果
//============================================================
TResult FLoggerFeature::Startup(){
   _netLoggerConsole->Startup();
   return ESuccess;
}

//============================================================
// <T>挂起处理。</T>
//
// @return 处理结果
//============================================================
TResult FLoggerFeature::Suspend(){
   _netLoggerConsole->Suspend();
   return ESuccess;
}

//============================================================
// <T>继续处理。</T>
//
// @return 处理结果
//============================================================
TResult FLoggerFeature::Resume(){
   _netLoggerConsole->Resume();
   return ESuccess;
}

//============================================================
// <T>停止处理。</T>
//
// @return 处理结果
//============================================================
TResult FLoggerFeature::Shutdown(){
   _netLoggerConsole->Shutdown();
   return ESuccess;
}

MO_NAMESPACE_END
