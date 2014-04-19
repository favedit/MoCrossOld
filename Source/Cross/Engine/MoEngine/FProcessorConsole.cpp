#include "MoEgProcessor.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造异步处理控制台。</T>
//============================================================
FProcessorConsole::FProcessorConsole(){
   _pProcessores = MO_CREATE(FProcessorCollection);
}

//============================================================
// <T>析构异步处理控制台。</T>
//============================================================
FProcessorConsole::~FProcessorConsole(){
   MO_DELETE(_pProcessores);
}

//============================================================
// <T>注册一个处理器。</T>
//
// @param pProcessor 处理器
// @return 处理结果
//============================================================
TResult FProcessorConsole::Register(FProcessor* pProcessor){
   _pProcessores->Push(pProcessor);
   return ESuccess;
}

//============================================================
// <T>注销一个处理器。</T>
//
// @param pProcessor 处理器
// @return 处理结果
//============================================================
TResult FProcessorConsole::Unregister(FProcessor* pProcessor){
   _pProcessores->Remove(pProcessor);
   return ESuccess;
}

//============================================================
// <T>启动处理。</T>
//
// @return 处理结果
//============================================================
TResult FProcessorConsole::Startup(){
   FProcessorCollection::TIteratorC iterator = _pProcessores->IteratorC();
   while(iterator.Next()){
      FProcessor* pProcessor = *iterator;
      pProcessor->Startup();
   }
   return ESuccess;
}

//============================================================
// <T>启动处理。</T>
//
// @return 处理结果
//============================================================
TResult FProcessorConsole::Shutdown(){
   FProcessorCollection::TIteratorC iterator = _pProcessores->IteratorC();
   while(iterator.Next()){
      FProcessor* pProcessor = *iterator;
      pProcessor->Shutdown();
   }
   return ESuccess;
}

MO_NAMESPACE_END
