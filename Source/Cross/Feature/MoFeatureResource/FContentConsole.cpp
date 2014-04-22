#include "MoFrContent.h"
#include "MoFrContentPipeline.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FContentConsole, FLoader);

//============================================================
// <T>构造内容控制台。</T>
//============================================================
FContentConsole::FContentConsole(){
   _pipelineFactory = FClassInstanceFactory::InstanceCreate();
   _pipelineFactory->SetDefaultClass(FContentAssetPipeline::Class());
}

//============================================================
// <T>析构内容控制台。</T>
//============================================================
FContentConsole::~FContentConsole(){
}

//============================================================
// <T>增加一个加载器。</T>
//
// @param pLoader 加载器
// @return 处理结果
//============================================================
TResult FContentConsole::PushLoader(FContentLoader* pLoader){
   pLoader->SetConsole(this);
   RLoaderManager::Instance().PushWaitLoader(pLoader);
   return ESuccess;
}

MO_NAMESPACE_END
