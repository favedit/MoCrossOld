#include "MoEgProcessor.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FProcessor, FInstance);

//============================================================
// <T>构造实体3D动画管理器。</T>
//============================================================
FProcessor::FProcessor(){
   _pThread = MO_CREATE(FProcessorThread);
}

//============================================================
// <T>析构实体3D动画管理器。</T>
//============================================================
FProcessor::~FProcessor(){
}

//============================================================
// <T>注册一个处理器。</T>
//
// @param pProcessor 处理器
// @return 处理结果
//============================================================
TResult FProcessor::Register(IProcessor* pProcessor){
   TResult result = _pThread->Register(pProcessor);
   return result;
}

//============================================================
// <T>注销一个处理器。</T>
//
// @param pProcessor 处理器
// @return 处理结果
//============================================================
TResult FProcessor::Unregister(IProcessor* pProcessor){
   TResult result = _pThread->Unregister(pProcessor);
   return result;
}

//============================================================
// <T>启动处理。</T>
//
// @return 处理结果
//============================================================
TResult FProcessor::Startup(){
   _pThread->Start();
   return ESuccess;
}

//============================================================
// <T>启动处理。</T>
//
// @return 处理结果
//============================================================
TResult FProcessor::Shutdown(){
   _pThread->Stop();
   _pThread->Terminate();
   return ESuccess;
}

//============================================================
// <T>清空处理。</T>
//
// @return 处理结果
//============================================================
TResult FProcessor::Clear(){
   TResult result = _pThread->Clear();
   return result;
}

MO_NAMESPACE_END
