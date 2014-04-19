#include "MoFgPipeline.h"

MO_NAMESPACE_BEGIN

//============================================================
// <T>构造渲染管道控制台。</T>
//============================================================
FPipelineConsole::FPipelineConsole(){
   _pFactory = MO_CREATE(FClassInstanceFactory);
   MO_CLEAR(_pActivePipeline);
}

//============================================================
// <T>析构渲染管道控制台。</T>
//============================================================
FPipelineConsole::~FPipelineConsole(){
   MO_DELETE(_pFactory);
}

//============================================================
// <T>选择激活的渲染管道。</T>
//
// @param pName 名称
// return 渲染管道
//============================================================
FPipeline* FPipelineConsole::SelectPipeline(TCharC* pName){
   FPipeline* pPipeline = (FPipeline*)_pFactory->Get(pName);
   pPipeline->Setup();
   _pActivePipeline = pPipeline;
   return pPipeline;
}

MO_NAMESPACE_END
