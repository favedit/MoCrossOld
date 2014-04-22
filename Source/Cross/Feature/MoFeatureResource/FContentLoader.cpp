#include "MoFrContent.h"
#include "MoFrContentPipeline.h"

MO_NAMESPACE_BEGIN

MO_CLASS_IMPLEMENT_INHERITS(FContentLoader, FLoader);

//============================================================
// <T>构造内容加载器。</T>
//============================================================
FContentLoader::FContentLoader(){
   MO_CLEAR(_pConsole);
}

//============================================================
// <T>析构内容加载器。</T>
//============================================================
FContentLoader::~FContentLoader(){
}

//============================================================
// <T>数据处理。</T>
//
// @return 处理结果
//============================================================
TResult FContentLoader::Process(){
   TCharC* pTypeName = _content->TypeName();
   FInstance* pPipelineInstance = _pConsole->PipelineFactory()->Find(pTypeName);
   MO_CHECK(pPipelineInstance, return ENull);
   FContentPipeline* pPipeline = pPipelineInstance->Convert<FContentPipeline>();
   pPipeline->Process(this);
   return ESuccess;
}

MO_NAMESPACE_END
